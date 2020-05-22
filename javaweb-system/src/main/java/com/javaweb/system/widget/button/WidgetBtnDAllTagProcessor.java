package com.javaweb.system.widget.button;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetBtnDAllTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名称
     */
    private static final String TAG_NAME = "btnDAll";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetBtnDAllTagProcessor(String dialectPrefix) {
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

    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, IElementTagStructureHandler iElementTagStructureHandler) {
        //获取标签的属性值
        String tagName = iProcessableElementTag.getAttributeValue("name");

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        // 这里是将字典的内容拼装成一个下拉框
        model.add(modelFactory.createOpenElementTag("a href=\"javascript:\" class=\"layui-btn btnOption layui-btn-danger layui-btn-small btnBatchDrop\" id=\"batchDrop\" data-param=\"[]\" lay-event=\"batchDrop\""));
        model.add(modelFactory.createOpenElementTag("i class=\"layui-icon layui-icon-delete\""));
        model.add(modelFactory.createCloseElementTag("i"));
        model.add(modelFactory.createText(tagName));
        model.add(modelFactory.createCloseElementTag("a"));
        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
