package com.javaweb.system.widget;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Thymeleaf 方言配置
 */
@Configuration
public class WidgetDialectConfig {
    /**
     * 自定义方言注入
     * 字典下拉框下拉框
     *
     * @return
     */
    @Bean
    public WidgetDialect sysDialect() {
        return new WidgetDialect();
    }
}
