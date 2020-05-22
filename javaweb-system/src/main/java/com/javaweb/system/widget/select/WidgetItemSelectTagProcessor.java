package com.javaweb.system.widget.select;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.system.entity.Item;
import com.javaweb.system.entity.ItemCate;
import com.javaweb.system.mapper.ItemCateMapper;
import com.javaweb.system.mapper.ItemMapper;
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

public class WidgetItemSelectTagProcessor extends AbstractElementTagProcessor {
    /**
     * 站点Dao
     */
    private ItemMapper itemMapper;
    /**
     * 栏目Dao
     */
    private ItemCateMapper itemCateMapper;
    /**
     * 标签名
     */
    private static final String TAG_NAME = "itemSelect";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetItemSelectTagProcessor(String dialectPrefix) {
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
        //获取站点Mapper的bean
        itemMapper = applicationContext.getBean(ItemMapper.class);
        // 获取栏目Mappper的bean
        itemCateMapper = applicationContext.getBean(ItemCateMapper.class);

        //获取标签的属性值
        String tagItemId = iProcessableElementTag.getAttributeValue("value");
        String tagCateId = iProcessableElementTag.getAttributeValue("data");
        String tagLimit = iProcessableElementTag.getAttributeValue("limit");
        // 站点ID
        Integer itemId = 0;
        if (!StringUtils.isEmpty(tagItemId)) {
            itemId = Integer.valueOf(tagItemId);
        }
        // 栏目ID
        Integer cateId = 0;
        if (!StringUtils.isEmpty(tagCateId)) {
            cateId = Integer.valueOf(tagCateId);
        }
        // 层次数
        Integer limit = 1;
        if (!StringUtils.isEmpty(tagLimit)) {
            limit = Integer.valueOf(tagLimit);
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
        map1.put("title", "栏目");
        map1.put("code", "cate");
        map1.put("list", this.getItemCateList(itemId, 0));
        map1.put("selected", cateId);
        mapList.add(map1);

        // 根据层级获取数组
        if (limit > mapList.size()) {
            limit = mapList.size();
        }
        mapList = mapList.subList(0, limit);


        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();

        for (Map<String, Object> data : mapList) {
            // 字段标题
            String title = data.get("title").toString();
            // 字段名称
            String code = data.get("code").toString();
            // 选择ID
            Integer selected = Integer.valueOf(data.get("selected").toString());

            // 创建起始元素
            if (limit > 1) {
                model.add(modelFactory.createOpenElementTag("div class=\"layui-input-inline\""));
            }
            model.add(modelFactory.createOpenElementTag(String.format("select name=\"%sId\" id=\"%sId\" lay-filter=\"%sId\" lay-search=\"\"", code, code, code)));
            model.add(modelFactory.createOpenElementTag("option value=\"\""));
            model.add(modelFactory.createText(String.format("【请选择%s】", title)));
            model.add(modelFactory.createCloseElementTag("option"));

            // 创建数据源
            List<Map<String, Object>> list = (List<Map<String, Object>>) data.get("list");
            for (Map<String, Object> item : list) {
                if (selected.equals(item.get("id"))) {
                    model.add(modelFactory.createOpenElementTag(String.format("option value=\"%s\" selected=\"\"", item.get("id"))));
                } else {
                    model.add(modelFactory.createOpenElementTag(String.format("option value=\"%s\"", item.get("id"))));
                }
                model.add(modelFactory.createText(item.get("name").toString()));
                model.add(modelFactory.createCloseElementTag("option"));
            }
            model.add(modelFactory.createCloseElementTag("select"));
            // 创建闭合元素
            if (limit > 1) {
                model.add(modelFactory.createCloseElementTag("div"));
            }
        }
        // 创建JS
        model.add(modelFactory.createOpenElementTag("script type=\"text/javascript\""));
        model.add(modelFactory.createText("layui.use(['form'],function(){\n" +
                "\n" +
                "\t// 声明变量\n" +
                "\tvar layer = layui.layer\n" +
                "\t,form = layui.form\n" +
                "\t,$ = layui.$;\n" +
                "\n" +
                "\t// 选择站点\n" +
                "\tform.on('select(itemId)',function(data){\n" +
                "\t\tvar id = data.value;\n" +
                "\t\tconsole.log(\"站点ID:\"+id);\n" +
                "\t\tvar select = data.othis;\n" +
                "\t\tif (select[0]) {\n" +
                "\t\t\tif (id > 0) {\n" +
                "\t\t\t\t$.post(\"/itemcate/getItemCateListByItemId\", { 'itemId':id }, function(data){\n" +
                "\t\t\t\t\tif (data.code == 0) {\n" +
                "\t\t\t\t\t\tvar str = \"\";\n" +
                "\t\t\t\t\t\t$.each(data.data, function(i,item){\n" +
                "\t\t\t\t\t\t\tstr += \"<option value=\\\"\" + item.id + \"\\\" >\" + item.name + \"</option>\";\n" +
                "\t\t\t\t\t\t});\n" +
                "\t\t\t\t\t\t$(\"#cateId\").html('<option value=\"\">【请选择栏目】</option>' + str);\n" +
                "\t\t\t\t\t\tform.render('select');\n" +
                "\t\t\t\t\t}else{\n" +
                "\t\t\t\t\t\t$(\"#cateId\").html('');\n" +
                "\t\t\t\t\t\tform.render('select');\n" +
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
                "\t// 选择栏目\n" +
                "\tform.on(\"select(cateId)\",function(data){\n" +
                "\t\tvar id = data.value;\n" +
                "\t\tconsole.log(\"栏目ID:\"+id);\n" +
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
     * 获取栏目
     *
     * @param itemId   站点ID
     * @param pid 父级ID
     * @return
     */
    private List<Map<String, Object>> getItemCateList(Integer itemId, Integer pid) {
        QueryWrapper<ItemCate> queryWrapper = new QueryWrapper<>();
        if (itemId > 0) {
            queryWrapper.eq("item_id", itemId);
        }
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("sort");
        List<ItemCate> itemCateList = itemCateMapper.selectList(queryWrapper);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!itemCateList.isEmpty()) {
            itemCateList.forEach(item -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", item.getId());
                map.put("name", item.getName());
                mapList.add(map);

                // 获取子级
                List<Map<String, Object>> childrenList = this.getItemCateList(itemId, item.getId());
                if (!childrenList.isEmpty()) {
                    childrenList.forEach(subItem -> {
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("id", subItem.get("id"));
                        map1.put("name", "|--" + subItem.get("name"));
                        mapList.add(map1);
                    });
                }

            });
        }
        return mapList;
    }
}
