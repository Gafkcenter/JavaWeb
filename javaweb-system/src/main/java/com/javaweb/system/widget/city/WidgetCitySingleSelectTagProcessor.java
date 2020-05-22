package com.javaweb.system.widget.city;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.system.entity.City;
import com.javaweb.system.mapper.CityMapper;
import org.springframework.context.ApplicationContext;
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

public class WidgetCitySingleSelectTagProcessor extends AbstractElementTagProcessor {
    // 声明变量
    private CityMapper cityMapper;
    /**
     * 标签名
     */
    private static final String TAG_NAME = "citySingleSelect";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetCitySingleSelectTagProcessor(String dialectPrefix) {
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
        cityMapper = applicationContext.getBean(CityMapper.class);

        //获取标签的属性值
        String cityId = iProcessableElementTag.getAttributeValue("value");
        String tagLimit = iProcessableElementTag.getAttributeValue("limit");
        // 层次数
        Integer limit = Integer.valueOf(tagLimit);

        // 初始化城市层级数组
        List<Map<String, Object>> mapList = new ArrayList<>();
        // 添加省份
        Map<String, Object> map = new HashMap<>();
        map.put("title", "省");
        map.put("code", "province");
        map.put("list", this.getCityList(1));
        map.put("selected", 0);
        mapList.add(map);
        // 添加城市
        Map<String, Object> map1 = new HashMap<>();
        map1.put("title", "市");
        map1.put("code", "city");
        map1.put("list", new ArrayList<>());
        map1.put("selected", 0);
        mapList.add(map1);
        // 添加区县
        Map<String, Object> map2 = new HashMap<>();
        map2.put("title", "县/区");
        map2.put("code", "district");
        map2.put("list", new ArrayList<>());
        map2.put("selected", 0);
        mapList.add(map2);

        // 根据层级获取数组
        if (limit > mapList.size()) {
            limit = mapList.size();
        }
        mapList = mapList.subList(0, limit);

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();

        // 获取城市信息
        if (Integer.valueOf(cityId) > 0) {
            City cityInfo = cityMapper.selectById(cityId);
            Integer level = cityInfo.getLevel();
            while (level > 1) {
                Map<String, Object> map3 = mapList.get(level - 1);
                map3.put("list", this.getCityList(cityInfo.getPid()));
                map3.put("selected", cityInfo.getId());
                // 更新指定索引的数据
                mapList.set(level - 1, map3);
                // 获取上一层级数据
                cityInfo = cityMapper.selectById(cityInfo.getPid());
                level--;
            }
            // 设置第一级
            Map<String, Object> map4 = mapList.get(0);
            map4.put("selected", cityInfo.getId());
        }

        // 循环遍历
        if (!mapList.isEmpty()) {
            mapList.forEach(data -> {
                // 字段标题
                String title = data.get("title").toString();
                // 字段名称
                String code = data.get("code").toString();
                Integer selected = Integer.valueOf(data.get("selected").toString());

                // 创建起始元素
                model.add(modelFactory.createOpenElementTag("div class=\"layui-input-inline\""));
                model.add(modelFactory.createOpenElementTag(String.format("select name=\"%sId\" id=\"%sId\" lay-filter=\"%sId\" lay-search=\"\"", code, code, code)));
                model.add(modelFactory.createOpenElementTag("option value=''"));
                model.add(modelFactory.createText(String.format("【请选择%s】", title)));
                model.add(modelFactory.createCloseElementTag("option"));

                // 创建数据源
                List<City> list = (List<City>) data.get("list");
                if (!list.isEmpty()) {
                    list.forEach(item -> {
                        if (selected.equals(item.getId())) {
                            model.add(modelFactory.createOpenElementTag(String.format("option value=\"%s\" selected=\"\"", item.getId())));
                        } else {
                            model.add(modelFactory.createOpenElementTag(String.format("option value=\"%s\"", item.getId())));
                        }
                        model.add(modelFactory.createText(item.getName()));
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
        model.add(modelFactory.createText("layui.use(['form'],function(){\n" +
                "\n" +
                "\t// 声明变量\n" +
                "\tvar form = layui.form,\n" +
                "\t\t$ = layui.$;\n" +
                "\n" +
                "\t// 选择省份\n" +
                "\tform.on('select(provinceId)',function(data){\n" +
                "\t\tvar id = data.value;\n" +
                "\t\tconsole.log(\"省份ID:\"+id);\n" +
                "\t\tvar select = data.othis;\n" +
                "\t\tif (select[0]) {\n" +
                "\t\t\tif (id > 0) {\n" +
                "\t\t\t\t$.post(\"/city/getCityListByPid\", { 'pid':id }, function(data){\n" +
                "\t\t\t\t\tif (data.code == 0) {\n" +
                "\t\t\t\t\t\tvar str = \"\";\n" +
                "\t\tconsole.log(data.data);\n" +
                "\t\t\t\t\t\t$.each(data.data, function(i,item){\n" +
                "\t\t\t\t\t\t\tstr += \"<option value=\\\"\" + item.id + \"\\\" >\" + item.name + \"</option>\";\n" +
                "\t\t\t\t\t\t});\n" +
                "\t\t\t\t\t\t$(\"#cityId\").html('<option value=\"\">【请选择市】</option>' + str);\n" +
                "\t\t\t\t\t\t$(\"#districtId\").html('<option value=\"\">【请选择县/区】</option>');\n" +
                "\t\t\t\t\t\tform.render('select');\n" +
                "\t\t\t\t\t}else{\n" +
                "\t\t\t\t\t\tlayer.msg(data.msg,{ icon: 5 });\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t$(\"#cityId\").html('<option value=\"\">【请选择市】</option>');\n" +
                "\t\t\t\t\t\t$(\"#districtId\").html('<option value=\"\">【请选择县/区】</option>');\n" +
                "\t\t\t\t\t\tform.render('select');\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\treturn false;\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}, 'json');\n" +
                "\t\t\t} else {\n" +
                "\t\t\t\t\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t});\n" +
                "\t\n" +
                "\t// 选择城市\n" +
                "\tform.on('select(cityId)',function(data){\n" +
                "\t\tvar id = data.value;\n" +
                "\t\tconsole.log(\"城市ID:\"+id);\n" +
                "\t\tvar select = data.othis;\n" +
                "\t\tif (select[0]) {\n" +
                "\t\t\tif (id > 0) {\n" +
                "\t\t\t\t$.post(\"/city/getCityListByPid\", { 'pid':id }, function(data){\n" +
                "\t\t\t\t\tif (data.code == 0) {\n" +
                "\t\t\t\t\t\tvar str = \"\";\n" +
                "\t\t\t\t\t\t$.each(data.data, function(i,item){\n" +
                "\t\t\t\t\t\t\tstr += \"<option value=\\\"\" + item.id + \"\\\" >\" + item.name + \"</option>\";\n" +
                "\t\t\t\t\t\t});\n" +
                "\t\t\t\t\t\t$(\"#districtId\").html('<option value=\"\">【请选择县/区】</option>' + str);\n" +
                "\t\t\t\t\t\tform.render('select');\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}, 'json');\n" +
                "\t\t\t} else {\n" +
                "\t\t\t\tlayer.msg(data.msg,{ icon: 5 });\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t$(\"#districtId\").html('<option value=\"\">【请选择县/区】</option>');\n" +
                "\t\t\t\tform.render('select');\n" +
                "\n" +
                "\t\t\t\treturn false;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t});\n" +
                "\t\n" +
                "\t// 选择县区\n" +
                "\tform.on(\"select(districtId)\",function(data){\n" +
                "\t\tvar id = data.value;\n" +
                "\t\tconsole.log(\"县区ID:\"+id);\n" +
                "\t});\n" +
                "\t\n" +
                "});"));
        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }

    /**
     * 根据父级ID获取城市列表
     *
     * @param pid 父级ID
     * @return
     */
    private List<City> getCityList(Integer pid) {
        // 查询条件
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("mark", 1);
        // 查询数据列表
        List<City> cityList = cityMapper.selectList(queryWrapper);
        return cityList;
    }
}
