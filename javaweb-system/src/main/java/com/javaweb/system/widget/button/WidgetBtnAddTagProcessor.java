package com.javaweb.system.widget.button;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.ArrayList;
import java.util.List;

public class WidgetBtnAddTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "btnAdd";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 组件前缀
     *                      templateMode: 模板模式，这里使用HTML模板。
     *                      dialectPrefix: 标签前缀。即xxx:text中的xxx。
     *                      elementName：匹配标签元素名。举例来说如果是div，则我们的自定义标签只能用在div标签中。为null能够匹配所有的标签。
     *                      prefixElementName: 标签名是否要求前缀。
     *                      attributeName: 自定义标签属性名。这里为text。
     *                      prefixAttributeName：属性名是否要求前缀，如果为true，Thymeeleaf会要求使用text属性时必须加上前缀，即xxx:text。
     *                      precedence：标签处理的优先级，此处使用和Thymeleaf标准方言相同的优先级。
     *                      removeAttribute：标签处理后是否移除自定义属性。
     */
    public WidgetBtnAddTagProcessor(String dialectPrefix) {
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
        // 参数
        String tagValue = iProcessableElementTag.getAttributeValue("value");

        // 自定义参数
        String paramStr = "";
        if (!StringUtils.isEmpty(tagValue)) {
            String[] itemArr = tagValue.split(",");
            List<String> stringList = new ArrayList<>();
            for (String s : itemArr) {
                stringList.add(String.format("\"%s\"", s));
            }
            paramStr = StringUtils.join(stringList, ",");
        }

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        // 这里是将字典的内容拼装成一个下拉框
        model.add(modelFactory.createOpenElementTag(String.format("a href=\"javascript:\" class=\"layui-btn btnOption  layui-btn-small btnAdd\" id=\"add\" data-param='[%s]' lay-event=\"add\"", paramStr)));
        model.add(modelFactory.createOpenElementTag("i class=\"layui-icon layui-icon-add-1\""));
        model.add(modelFactory.createCloseElementTag("i"));
        model.add(modelFactory.createText(tagName));
        model.add(modelFactory.createCloseElementTag("a"));
        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
