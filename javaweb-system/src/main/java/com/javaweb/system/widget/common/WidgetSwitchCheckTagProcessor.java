package com.javaweb.system.widget.common;

import org.springframework.util.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetSwitchCheckTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "switchCheck";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetSwitchCheckTagProcessor(String dialectPrefix) {
        super(// 此处理器将仅应用于HTML模式
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

    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, IElementTagStructureHandler iElementTagStructureHandler) {
        // 组件名称
        String tagName = iProcessableElementTag.getAttributeValue("name");
        // 组件数据源
        String tagData = iProcessableElementTag.getAttributeValue("data");
        // 组件参数值
        String tagValue = iProcessableElementTag.getAttributeValue("value");
        // 隐藏参数
        String tagHidden = iProcessableElementTag.getAttributeValue("hidden");

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer1 = new StringBuffer();
        if (!StringUtils.isEmpty(tagHidden)) {
            // 初始化显示、隐藏
            String[] itemArr = tagHidden.split(",");
            stringBuffer.append(String.format("        if (%s == 1) {\n", tagValue));
            for (String s : itemArr) {
                stringBuffer.append(String.format("            $(\".%s\").removeClass(\"layui-hide\");\n", s));
            }
            stringBuffer.append("        } else {\n");
            for (String s : itemArr) {
                stringBuffer.append(String.format("            $(\".%s\").addClass(\"layui-hide\");\n", s));
            }
            stringBuffer.append("        }");

            // 设置隐藏域控制参数
            stringBuffer1.append("var isSel = data.elem.checked;");
            stringBuffer1.append("if (isSel) {");
            for (String s : itemArr) {
                stringBuffer1.append(String.format("$(\".%s\").removeClass(\"layui-hide\");", s));
            }
            stringBuffer1.append("} else {");
            for (String s : itemArr) {
                stringBuffer1.append(String.format("$(\".%s\").addClass(\"layui-hide\")", s));
            }
            stringBuffer1.append("}");
        }

        // 创建组件
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append(String.format("input name=\"%s\" id=\"%s\" th:value=\"%s\" lay-skin=\"switch\" lay-filter=\"%s\" lay-text=\"%s\" type=\"checkbox\"", tagName, tagName, tagValue, tagName, tagData));
        if (Integer.valueOf(tagValue) == 1) {
            stringBuffer3.append("checked=\"\"");
        }
        // 创建将替换自定义标签的DOM结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        model.add(modelFactory.createOpenElementTag(stringBuffer3.toString()));

        // 添加JS
        model.add(modelFactory.createOpenElementTag("script th:inline=\"javascript\" type=\"text/javascript\""));
        model.add(modelFactory.createText("layui.use(['form'], function(){\n" +
                "\tvar form = layui.form,\n" +
                "\t\t$ = layui.$;\n" +
                stringBuffer + "\n" +
                String.format("    if (%s == 1) {\n", tagValue) +
                String.format("        $(\"#%s\").attr('type', 'hidden').val(1);\n", tagName) +
                "    } else {\n" +
                String.format("        $(\"#%s\").attr('type', 'hidden').val(2);\n", tagName) +
                "    }\n" +
                String.format("\tform.on('switch(%s)', function(data) {\n", tagName) +
                "\t\tconsole.log('switch开关选择状态：'+this.checked);\n" +
                "\t    $(data.elem).attr('type', 'hidden').val(this.checked ? 1 : 2);\n" +
                stringBuffer1 +
                "\t});\n" +
                "});"));
        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
