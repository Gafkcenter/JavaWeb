package com.javaweb.system.widget.select;

import com.javaweb.common.utils.JDBCUtils;
import com.javaweb.common.utils.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WidgetComplexSelectTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "complexSelect";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetComplexSelectTagProcessor(String dialectPrefix) {
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
        String tagData = iProcessableElementTag.getAttributeValue("data");
        String tagValue = iProcessableElementTag.getAttributeValue("value");
        String tagSql = iProcessableElementTag.getAttributeValue("sql");

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
        String showTips = itemArr[2];
        // 显示文字
        String showText = itemArr[3];
        // 显示值
        String showValue = itemArr[4];

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        // 这里是将字典的内容拼装成一个下拉框
        model.add(modelFactory.createOpenElementTag(String.format("select name=\"%s\" id=\"%s\" lay-search=\"\"", showName, showName)));
        model.add(modelFactory.createOpenElementTag("option value=''"));
        model.add(modelFactory.createText(String.format("【请选择%s】", showTips)));
        model.add(modelFactory.createCloseElementTag("option"));

        // 执行SQL语句
        if (!StringUtils.isEmpty(tagSql)) {
            Connection conn = null;
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                // 获取连接
                conn = JDBCUtils.getConnection();
                // 编写sql
                String sql = tagSql;
                // 创建语句执行者
                st = conn.prepareStatement(sql);
                //设置参数
//                st.setString(1, "0");
                st.setInt(1, 0);
                // 执行sql
                rs = st.executeQuery();
                // 遍历结果集
                while (rs.next()) {
                    // 显示值
                    Integer id = rs.getInt(showValue);
                    // 显示标题
                    String name = rs.getString(showText);

                    model.add(modelFactory.createOpenElementTag(String.format("optgroup label=\"%s\"", name)));

                    // 获取二级参数
                    // 获取连接
                    Connection conn2 = JDBCUtils.getConnection();
                    // 编写sql
                    String sql2 = tagSql;
                    // 创建语句执行者
                    PreparedStatement st2 = conn2.prepareStatement(sql2);
                    //设置参数
                    st2.setInt(1, id);
                    // 执行sql
                    ResultSet rs2 = st2.executeQuery();

                    // 遍历结果集
                    while (rs2.next()) {
                        // 显示值
                        Integer cId = rs2.getInt(showValue);
                        // 显示标题
                        String cName = rs2.getString(showText);
                        if (Integer.valueOf(tagValue) == cId) {
                            model.add(modelFactory.createOpenElementTag(String.format("option value=\"%s\" selected", cId)));
                        } else {
                            model.add(modelFactory.createOpenElementTag(String.format("option value=\"%s\"", cId)));
                        }
                        model.add(modelFactory.createText(cName));
                        model.add(modelFactory.createCloseElementTag("option"));
                    }

                    // 关闭连接
                    JDBCUtils.colseResource(conn2, st2, rs2);

                    model.add(modelFactory.createCloseElementTag("optgroup"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.colseResource(conn, st, rs);
            }
        } else {
            // 初始化
            Map<Integer, String> map = new HashMap<>();
            String[] dataArr = tagData.split(",");
            for (String item : dataArr) {
                String[] subItem = item.split("=");
                map.put(Integer.valueOf(subItem[0]), subItem[1]);
            }

            // 循环遍历实体对象
            for (Map.Entry<Integer, String> item : map.entrySet()) {
                if (Integer.valueOf(tagValue) == item.getKey()) {
                    model.add(modelFactory.createOpenElementTag(String.format("option value='%s' %s", item.getKey().toString(), "selected=''")));
                } else {
                    model.add(modelFactory.createOpenElementTag(String.format("option value='%s'", item.getKey().toString())));
                }
                model.add(modelFactory.createText(item.getValue()));
                model.add(modelFactory.createCloseElementTag("option"));
            }
        }
        model.add(modelFactory.createCloseElementTag("select"));
        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
