package com.javaweb.system.widget.select;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.system.entity.Item;
import com.javaweb.system.entity.LayoutDesc;
import com.javaweb.system.mapper.ItemMapper;
import com.javaweb.system.mapper.LayoutDescMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidgetLayoutSelectTagProcessor extends AbstractElementTagProcessor {

    private ItemMapper itemMapper;
    private LayoutDescMapper layoutDescMapper;

    /**
     * 标签名
     */
    private static final String TAG_NAME = "layoutSelect";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetLayoutSelectTagProcessor(String dialectPrefix) {
        super(
                // 此处理器将仅应用于HTML模式
                TemplateMode.HTML,
                // 要应用于名称的匹配前缀
                dialectPrefix,
                // 标签名称：匹配此名称的特定标签
                TAG_NAME,
                // 将标签前缀应用于标签名称
                true,
                // 无属性名称：将通过标签名称匹配
                null,
                // 没有要应用于属性名称的前缀
                false,
                // 优先(内部方言自己的优先)
                PRECEDENCE);
    }

    /**
     * 处理自定义标签 DOM 结构
     *
     * @param iTemplateContext            页面上下文
     * @param iProcessableElementTag      标签
     * @param iElementTagStructureHandler 执行句柄
     */
    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, IElementTagStructureHandler iElementTagStructureHandler) {
        // 获取 Spring上下文
        ApplicationContext applicationContext = SpringContextUtils.getApplicationContext(iTemplateContext);
        //获取字典service的bean
        itemMapper = applicationContext.getBean(ItemMapper.class);
        layoutDescMapper = applicationContext.getBean(LayoutDescMapper.class);

        //获取标签的属性值
        String tagLocId = iProcessableElementTag.getAttributeValue("locId");

        // 站点ID
        Integer itemId = 0;

        // 位置ID
        Integer locId = 0;
        if (!StringUtils.isEmpty(tagLocId)) {
            locId = Integer.valueOf(tagLocId);
            // 获取位置信息
            LayoutDesc layoutDesc = layoutDescMapper.selectById(locId);
            itemId = layoutDesc.getItemId();
        }

        // 初始化城市层级数组
        List<Map<String, Object>> mapList = new ArrayList<>();
        // 添加省份
        Map<String, Object> map = new HashMap<>();
        map.put("title", "站点");
        map.put("code", "item");
        map.put("list", this.getItemList());
        map.put("selected", itemId);
        mapList.add(map);
        // 添加城市
        Map<String, Object> map1 = new HashMap<>();
        map1.put("title", "页面位置");
        map1.put("code", "loc");
        map1.put("list", this.getLocListByItemId(itemId));
        map1.put("selected", locId);
        mapList.add(map1);

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();

        // 循环遍历
        if (!mapList.isEmpty()) {
            mapList.forEach(data -> {
                // 字段标题
                String title = data.get("title").toString();
                // 字段名称
                String code = data.get("code").toString();
                // 选择ID
                Integer selected = Integer.valueOf(data.get("selected").toString());

                // 创建起始元素
                model.add(modelFactory.createOpenElementTag("div class=\"layui-input-inline\""));
                model.add(modelFactory.createOpenElementTag(String.format("select name=\"%sId\" id=\"%sId\" lay-filter=\"%sId\" lay-search=\"\"", code, code, code)));
                model.add(modelFactory.createOpenElementTag("option value=''"));
                model.add(modelFactory.createText(String.format("【请选择%s】", title)));
                model.add(modelFactory.createCloseElementTag("option"));

                // 创建数据源
                List<Map<String, Object>> list = (List<Map<String, Object>>) data.get("list");
                if (!list.isEmpty()) {
                    list.forEach(item -> {
                        if (selected.equals(Integer.valueOf(item.get("id").toString()))) {
                            model.add(modelFactory.createOpenElementTag(String.format("option value=\"%s\" selected=\"\"", item.get("id"))));
                        } else {
                            model.add(modelFactory.createOpenElementTag(String.format("option value=\"%s\"", item.get("id"))));
                        }
                        model.add(modelFactory.createText(item.get("name").toString()));
                        model.add(modelFactory.createCloseElementTag("option"));
                    });
                }
                model.add(modelFactory.createCloseElementTag("select"));
                // 创建闭合元素
                model.add(modelFactory.createCloseElementTag("div"));
            });
        }

        // 创建JS
        model.add(modelFactory.createOpenElementTag("script type=\"text/javascript\""));
        model.add(modelFactory.createText("layui.use(['form','layer'],function(){\n" +
                "\n" +
                "\t//声明变量\n" +
                "\tvar layer = layui.layer,\n" +
                "\t\tform = layui.form,\n" +
                "\t\t$ = layui.$;\n" +
                "\n" +
                "\t//选择节点\n" +
                "\tform.on('select(itemId)',function(data){\n" +
                "\t\tvar id = data.value;\n" +
                "\t\tconsole.log(\"站点ID:\"+id);\n" +
                "\t\tvar select = data.othis;\n" +
                "\t\tif (select[0]) {\n" +
                "\t\t\tif (id > 0) {\n" +
                "\t\t\t\t$.post(\"/layoutdesc/getLocListByItemId\", { 'itemId':id }, function(data){\n" +
                "\t\t\t\t\tif (data.code == 0) {\n" +
                "\t\t\t\t\t\tvar str = \"\";\n" +
                "\t\t\t\t\t\t$.each(data.data, function(i,item){\n" +
                "\t\t\t\t\t\t\tstr += \"<option value=\\\"\" + item.id + \"\\\" >\" + item.name + \"</option>\";\n" +
                "\t\t\t\t\t\t});\n" +
                "\t\t\t\t\t\t$(\"#locId\").html('<option value=\"\">【请选择页面位置】</option>' + str);\n" +
                "\t\t\t\t\t\tform.render('select');\n" +
                "\t\t\t\t\t}else{\n" +
                "\t\t\t\t\t\t$(\"#locId\").html('<option value=\"\">【请选择页面位置】</option>');\n" +
                "\t\t\t\t\t\tlayer.msg(data.msg,{ icon: 5 });\n" +
                "\t\t\t\t\t\treturn false;\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}, 'json');\n" +
                "\t\t\t} else {\n" +
                "\t\t\t\t\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t});\n" +
                "\t\n" +
                "\t//选择节点\n" +
                "\tform.on(\"select(locId)\",function(data){\n" +
                "\t\tvar id = data.value;\n" +
                "\t\tconsole.log(\"节点ID:\"+id);\n" +
                "\t});\n" +
                "\t\n" +
                "});"));
        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }

    /**
     * 获取站点ID
     *
     * @return
     */
    private List<Map<String, Object>> getItemList() {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("sort");
        List<Item> itemList = itemMapper.selectList(queryWrapper);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!itemList.isEmpty()) {
            itemList.forEach(item -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", item.getId());
                map.put("name", item.getName());
                mapList.add(map);
            });
        }
        return mapList;
    }

    /**
     * 根据站点ID获取位置
     *
     * @param itemId 站点ID
     * @return
     */
    private List<Map<String, Object>> getLocListByItemId(Integer itemId) {
        // 查询条件
        QueryWrapper<LayoutDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemId);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        List<Map<String, Object>> mapList = new ArrayList<>();

        // 获取数据
        List<LayoutDesc> layoutDescList = layoutDescMapper.selectList(queryWrapper);
        if (!layoutDescList.isEmpty()) {
            layoutDescList.forEach(item -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", item.getId());
                map.put("name", item.getLocDesc());
                mapList.add(map);
            });
        }
        return mapList;
    }

}
