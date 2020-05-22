package com.javaweb.system.widget.button;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetBtnFuncTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "btnFunc";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 组件前缀
     */
    public WidgetBtnFuncTagProcessor(String dialectPrefix) {
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
        String widgetName = iProcessableElementTag.getAttributeValue("name");
        // 组件参数
        String param = iProcessableElementTag.getAttributeValue("param");
        // 分裂字符串
        String[] item = widgetName.split("\\|");
        // 组件名称
        String tagName = item[0];
        // 组件ID
        String tagID = item[1];
        String tagIDName = item[1].substring(0, 1).toUpperCase() + item[1].substring(1);
        // 组件图标
        String tagIcon = item[2];
        // 组件背景
        String tagColor = item[3];
        // 组件模式：1小按钮组件 2大按钮组件
        Integer tagType = item[4] == null ? 1 : Integer.valueOf(item[4]);

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        // 这里是将字典的内容拼装成一个下拉框
        if (tagType == 1) {
            // 大组件
            model.add(modelFactory.createOpenElementTag(String.format("a href=\"javascript:\" class=\"layui-btn btnOption %s layui-btn-small btn%s\" id=\"%s\" data-param='%s' lay-event=\"%s\"", tagColor, tagIDName, tagID, param, tagID)));
        } else if (tagType == 2) {
            // 小组件
            model.add(modelFactory.createOpenElementTag(String.format("a href=\"javascript:\" class=\"layui-btn btnOption %s layui-btn-xs btn%s\" id=\"%s\" data-param='%s' lay-event=\"%s\"", tagColor, tagIDName, tagID, param, tagID)));
        }
        model.add(modelFactory.createOpenElementTag(String.format("i class=\"layui-icon %s\"", tagIcon)));
        model.add(modelFactory.createCloseElementTag("i"));
        model.add(modelFactory.createText(tagName));
        model.add(modelFactory.createCloseElementTag("a"));
        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
