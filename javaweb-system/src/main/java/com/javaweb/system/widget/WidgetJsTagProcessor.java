package com.javaweb.system.widget;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetJsTagProcessor extends AbstractAttributeTagProcessor {
    /**
     * 属性名
     */
    private static final String ATTR_NAME = "js";
    /**
     * 元素名
     */
    private static final String ELE_NAME = "script";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetJsTagProcessor(String dialectPrefix) {
        super(
                // 此处理器将仅应用于HTML模式
                TemplateMode.HTML,
                // 要应用于名称的匹配前缀
                dialectPrefix,
                // 标签名称：匹配此名称的特定标签
                ELE_NAME,
                // 将标签前缀应用于标签名称
                false,
                // 无属性名称：将通过标签名称匹配
                ATTR_NAME,
                // 没有要应用于属性名称的前缀
                true,
                // 优先(内部方言自己的优先)
                PRECEDENCE,
                true);
    }

    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, AttributeName attributeName, String s, IElementTagStructureHandler iElementTagStructureHandler) {
        final IEngineConfiguration configuration = iTemplateContext.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(iTemplateContext, s);
        final String url = (String) expression.execute(iTemplateContext);
        iElementTagStructureHandler.setAttribute("src", url);
    }
}
