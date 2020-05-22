package com.javaweb.system.widget.select;

import com.javaweb.common.utils.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetDateSelectTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "dateSelect";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 组件前缀
     */
    public WidgetDateSelectTagProcessor(String dialectPrefix) {
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
        //获取标签的属性值
        String tagName = iProcessableElementTag.getAttributeValue("name");
        // 组件值
        String tagValue = iProcessableElementTag.getAttributeValue("value");

        // 对组件名称进行分隔处理
        String[] itemArr = tagName.split("\\|");
        // 组件名称
        String showName = itemArr[0];
        // 是否必填
        String format = itemArr[1];
        if (StringUtils.isEmpty(format)) {
            format = "date";
        }
        // 显示值
        String showTips = itemArr[2];

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        // 这里是将字典的内容拼装成一个下拉框
        model.add(modelFactory.createOpenElementTag(String.format("input name=\"%s\" id=\"%s\" value=\"%s\" lay-verify=\"%s\" placeholder=\"%s\" autocomplete=\"off\" class=\"layui-input date-icon\" type=\"text\"", showName, showName, tagValue, format, showTips)));

        // 创建JS
        model.add(modelFactory.createOpenElementTag("script th:inline=\"javascript\" type=\"text/javascript\""));
        model.add(modelFactory.createText("layui.use(['func'], function () {\n" +
                "    var func = layui.func;\n" +

                String.format("        func.initDate(['%s|%s'], function (value, date) {\n", showName, format) +
                "            console.log(\"当前选择日期:\" + value);\n" +
                "            console.log(\"日期详细信息：\" + JSON.stringify(date));\n" +
                "        });" +
                "});"));

        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
