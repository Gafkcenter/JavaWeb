package com.javaweb.system.widget.common;

import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashMap;
import java.util.Map;

public class WidgetBtnSubmitTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "btnSubmit";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetBtnSubmitTagProcessor(String dialectPrefix) {
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
        String tagType = iProcessableElementTag.getAttributeValue("type");

        // 组件名称分裂处理
        Map<String, String> map = new HashMap<>();
        String[] itemArr = tagName.split(",");
        for (String item : itemArr) {
            String[] subItem = item.split("\\|");
            map.put(subItem[0], subItem[1]);
        }

        StringBuffer stringBuffer = new StringBuffer();
        if (!StringUtils.isEmpty(tagType)) {
            // 按钮随内容增加而下移
            stringBuffer.append("<div class=\"layui-form-item text-center\">");
        } else {
            // 按钮固定
            stringBuffer.append("<div class=\"layui-form-item text-center model-form-footer\">");
        }
        // 提交按钮
        if (CommonUtils.inArray("submit", map)) {
            stringBuffer.append(String.format("<button class=\"layui-btn\" lay-filter=\"submitForm\" lay-submit>%s</button>", map.get("submit")));
        }
        // 关闭按钮
        if (CommonUtils.inArray("close", map)) {
            stringBuffer.append(String.format("<button class=\"layui-btn layui-btn-primary\" type=\"button\" ew-event=\"closeDialog\">%s</button>", map.get("close")));
        }
        // 重置按钮
        if (CommonUtils.inArray("reset", map)) {
            stringBuffer.append(String.format("\t<button type=\"reset\" class=\"layui-btn layui-btn-normal\">%s</button>", map.get("reset")));
        }
        stringBuffer.append("</div>");
        // 创建将替换自定义标签的DOM结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        model.add(modelFactory.createText(stringBuffer.toString()));
        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
