package com.javaweb.system.widget.city;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.entity.City;
import com.javaweb.system.mapper.CityMapper;
import com.javaweb.system.service.ICityService;
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

public class WidgetCityComplexSelectTagProcessor extends AbstractElementTagProcessor {

    // 声明变量
    private ICityService cityService;
    /**
     * 标签名
     */
    private static final String TAG_NAME = "cityComplexSelect";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetCityComplexSelectTagProcessor(String dialectPrefix) {
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
        cityService = applicationContext.getBean(ICityService.class);

        // 获取标签的属性值
        String tagName = iProcessableElementTag.getAttributeValue("name");
        String tagValue = iProcessableElementTag.getAttributeValue("value");
        String tagLimit = iProcessableElementTag.getAttributeValue("limit");

        // 对组件名称进行分隔处理
        String[] itemArr = tagName.split("\\|");
        String widgetName = itemArr[0];
        // 组件提示语
        String showTips = itemArr[1];
        // 是否必填
        String isRequire = "";
        if (Integer.valueOf(itemArr[2]) == 1) {
            isRequire = "required";
        }

        // 获取城市名称
        String[] cityItem = tagValue.split("/");

        // 层次数
        Integer limit = 3;
        if (!StringUtils.isEmpty(tagLimit)) {
            limit = Integer.valueOf(tagLimit);
        }

        // 城市选择成绩
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "province");
        map.put(2, "city");
        map.put(3, "district");

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();

        // 引入CSS
        model.add(modelFactory.createOpenElementTag("link rel=\"stylesheet\" type=\"text/css\" href=\"/static/assets/module/city-picker/city-picker.css\" media=\"all\""));
        // 引入JS
        model.add(modelFactory.createOpenElementTag("script type=\"text/javascript\" src=\"/static/assets/module/city-picker/city-picker.data.js\""));
        model.add(modelFactory.createCloseElementTag("script"));
        model.add(modelFactory.createOpenElementTag(String.format("input readonly id=\"%s\" name=\"%s\" lay-verify=\"%s\" placeholder=\"请选择%s\" autocomplete=\"off\" class=\"layui-input\" type=\"text\" data-toggle=\"city-picker\"", widgetName, widgetName, isRequire, showTips)));
        // 创建JS
        model.add(modelFactory.createOpenElementTag("script type=\"text/javascript\""));
        model.add(modelFactory.createText("layui.use(['citypicker'], function () {\n" +
                "\t    var citypicker = layui.citypicker,\n" +
                "\t        $ = layui.$;\n" +
                "\t    \n" +
                "\t    //初始化组件参数\n" +
                "\t    var options = {\n" +
                "\t            simple: false,\n" +
                "\t            responsive: false,\n" +
                String.format("\t            placeholder: '请选择%s',\n", showTips) +
                String.format("\t            level: \"%s\",// 级别\n", map.get(limit)) +
                String.format("\t            province: '%s',// 默认省份名称\n", cityItem[0]) +
                String.format("\t            city: '%s',// 默认地市名称\n", cityItem[1]) +
                String.format("\t            district: '%s'// 默认区县名称\n", cityItem[2]) +
                "\t        };\n" +
                String.format("\t    $(\"#%s\").citypicker(options);\n", widgetName) +
                "\t});"));
        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
