package com.javaweb.system.widget.common;

import org.springframework.util.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetTagsInputTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "tagsInput";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetTagsInputTagProcessor(String dialectPrefix) {
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
        // 数据源
        String tagData = iProcessableElementTag.getAttributeValue("data");
        // 组件参数值
        String tagValue = iProcessableElementTag.getAttributeValue("value");

        // 对组件名称进行分隔处理
        String[] itemArr = tagName.split("\\|");
        // 组件名称
        String showName = itemArr[0];
        // 是否必填
        String isRequire = "";
        if (Integer.valueOf(itemArr[1]) == 1) {
            isRequire = "required";
        }
        // 组件提示语
        String tagType = itemArr[2];

        // 创建将替换自定义标签的DOM结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        model.add(modelFactory.createOpenElementTag(String.format("input name=\"%s\" id=\"%s\" value=\"%s\" class=\"layui-hide\" lay-verType=\"tips\"\n" +
                " lay-verify=\"%s\"", showName, showName, tagValue, isRequire)));


        StringBuffer stringBuffer = new StringBuffer();
        if (tagType.equals("1")) {
            // 输入框样式
            stringBuffer.append(String.format("$('#%s').tagsInput();", showName));
        } else if (tagType.equals("2")) {
            // 无边框样式
            stringBuffer.append(String.format("$('#%s').tagsInput({skin: 'tagsinput-default'});", showName));
        } else if (tagType.equals("3")) {
            // BackSpace键可删除标签
            stringBuffer.append(String.format("$('#%s').tagsInput({removeWithBackspace: true});", showName));
        } else if (tagType.equals("4")) {
            // 输入列表提示
            stringBuffer.append(String.format("$('#%s').tagsInput({\n" +
                    "            skin: 'tagsinput-default',\n" +
                    "            autocomplete_url: '../../../json/tagsInput.json'\n" +
                    "        });", showName));
        } else if (tagType.equals("5")) {
            // 自定义数据
            stringBuffer.append(String.format("$('#%s').tagsInput({\n" +
                    "            skin: 'tagsinput-default',\n" +
                    String.format("            data: %s\n", tagData) +
                    "        });", showName));
        }

        // 添加JS
        model.add(modelFactory.createOpenElementTag("script th:inline=\"javascript\" type=\"text/javascript\""));
        model.add(modelFactory.createText("layui.use(['tagsInput'], function(){\n" +
                "\tvar tagsInput = layui.tagsInput,\n" +
                "\t\t$ = layui.$;\n" +
                stringBuffer + "\n" +
                "});"));
        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
