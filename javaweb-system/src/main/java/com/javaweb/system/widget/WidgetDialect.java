package com.javaweb.system.widget;

import com.javaweb.system.widget.button.*;
import com.javaweb.system.widget.checkbox.WidgetCheckboxSingleSelectTagProcessor;
import com.javaweb.system.widget.city.WidgetCityComplexSelectTagProcessor;
import com.javaweb.system.widget.city.WidgetCitySingleSelectTagProcessor;
import com.javaweb.system.widget.common.WidgetBtnSubmitTagProcessor;
import com.javaweb.system.widget.common.WidgetRadioSelectTagProcessor;
import com.javaweb.system.widget.common.WidgetSwitchCheckTagProcessor;
import com.javaweb.system.widget.common.WidgetTagsInputTagProcessor;
import com.javaweb.system.widget.editor.WidgetKindeditorTagProcessor;
import com.javaweb.system.widget.iconpicker.WidgetIconPickerTagProcessor;
import com.javaweb.system.widget.select.*;
import com.javaweb.system.widget.tab.WidgetTabSelectTagProcessor;
import com.javaweb.system.widget.upload.WidgetUploadMultipleImageTagProcessor;
import com.javaweb.system.widget.upload.WidgetUploadSingleImageTagProcessor;
import com.javaweb.system.widget.xmselect.WidgetXmSelectTagProcessor;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定标签注册类
 */
@Component
public class WidgetDialect extends AbstractProcessorDialect {
    /**
     * 定义方言名称
     */
    private static final String DIALECT_NAME = "Widget Dialect";
    /**
     * 定义方言前缀
     */
    private static final String PREFIX = "widget";

    /**
     * 构造函数
     */
    public WidgetDialect() {
        // 我们将设置此方言与“方言处理器”优先级相同
        // 标准方言, 以便处理器执行交错。
        super(DIALECT_NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    /**
     * 元素处理器
     *
     * @param dialectPrefix
     * @return
     */
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        // 添加自定义方言
        Set<IProcessor> processors = new HashSet<IProcessor>();
        // 下拉单选标签
        processors.add(new WidgetSingleSelectTagProcessor(dialectPrefix));
        // 两层下拉选择标签
        processors.add(new WidgetComplexSelectTagProcessor(dialectPrefix));
        // 城市下拉选择标签
        processors.add(new WidgetCitySingleSelectTagProcessor(dialectPrefix));
        // 城市复杂模式选择下拉
        processors.add(new WidgetCityComplexSelectTagProcessor(dialectPrefix));
        // 添加新增按钮标签
        processors.add(new WidgetBtnAddTagProcessor(dialectPrefix));
        // 添加查询按钮标签
        processors.add(new WidgetBtnQueryTagProcessor(dialectPrefix));
        // 添加导出按钮标签
        processors.add(new WidgetBtnExportTagProcessor(dialectPrefix));
        // 添加导入按钮标签
        processors.add(new WidgetBtnImportTagProcessor(dialectPrefix));
        // 添加一键复制按钮标签
        processors.add(new WidgetBtnCopyTagProcessor(dialectPrefix));
        // 添加重置缓存按钮标签
        processors.add(new WidgetBtnCacheTagProcessor(dialectPrefix));
        // 添加查看详情按钮标签
        processors.add(new WidgetBtnDetailTagProcessor(dialectPrefix));
        // 添加编辑按钮标签
        processors.add(new WidgetBtnEditTagProcessor(dialectPrefix));
        // 添加删除按钮标签
        processors.add(new WidgetBtnDeleteTagProcessor(dialectPrefix));
        // 添加子级按钮标签
        processors.add(new WidgetBtnAddZTagProcessor(dialectPrefix));
        // 添加批量删除按钮标签
        processors.add(new WidgetBtnDAllTagProcessor(dialectPrefix));
        // 添加批量复制按钮标签
        processors.add(new WidgetBtnCAllTagProcessor(dialectPrefix));
        // 添加批量启动按钮标签
        processors.add(new WidgetBtnEAllTagProcessor(dialectPrefix));
        // 添加批量禁用按钮标签
        processors.add(new WidgetBtnSAllTagProcessor(dialectPrefix));
        // t通用自定义按钮标签
        processors.add(new WidgetBtnFuncTagProcessor(dialectPrefix));
        // 添加JS自定义标签
        processors.add(new WidgetJsTagProcessor(dialectPrefix));
        // 添加CSS自定义标签
        processors.add(new WidgetCssTagProcessor(dialectPrefix));
        // 添加权限控制按钮标签
        processors.add(new WidgetShiroTagProcessor(dialectPrefix));
        // 单图上传
        processors.add(new WidgetUploadSingleImageTagProcessor(dialectPrefix));
        // 多图上传
        processors.add(new WidgetUploadMultipleImageTagProcessor(dialectPrefix));
        // 开关选择器自定义标签
        processors.add(new WidgetSwitchCheckTagProcessor(dialectPrefix));
        // 底部公共操作按钮自定义标签
        processors.add(new WidgetBtnSubmitTagProcessor(dialectPrefix));
        // 时间日期选择标签
        processors.add(new WidgetDateSelectTagProcessor(dialectPrefix));
        // 复选框【简单模式】
        processors.add(new WidgetCheckboxSingleSelectTagProcessor(dialectPrefix));
        // 单选框
        processors.add(new WidgetRadioSelectTagProcessor(dialectPrefix));
        // 站点选择标签
        processors.add(new WidgetItemSelectTagProcessor(dialectPrefix));
        // 富文本编辑器标签
        processors.add(new WidgetKindeditorTagProcessor(dialectPrefix));
        // 布局描述选择标签
        processors.add(new WidgetLayoutSelectTagProcessor(dialectPrefix));
        // TAB切换选择标签
        processors.add(new WidgetTabSelectTagProcessor(dialectPrefix));
        // Icon选择器
        processors.add(new WidgetIconPickerTagProcessor(dialectPrefix));
        // Tree树选择器
        processors.add(new WidgetTreeSelectTagProcessor(dialectPrefix));
        // 标签输入
        processors.add(new WidgetTagsInputTagProcessor(dialectPrefix));
        // 多选下拉选择
        processors.add(new WidgetXmSelectTagProcessor(dialectPrefix));
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }
}
