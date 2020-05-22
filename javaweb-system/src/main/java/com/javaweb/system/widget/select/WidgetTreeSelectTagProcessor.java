package com.javaweb.system.widget.select;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javaweb.common.utils.JDBCUtils;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.common.utils.TreeUtils;
import org.antlr.v4.runtime.misc.Interval;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidgetTreeSelectTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "treeSelect";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetTreeSelectTagProcessor(String dialectPrefix) {
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
        model.add(modelFactory.createOpenElementTag(String.format("select name='%s' id='%s' lay-verify='%s' lay-search='' lay-filter='%s'", showName, showName, isRequire, showName)));
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

                // 执行sql
                rs = st.executeQuery();
                List<Map<String, Object>> mapList = new ArrayList<>();
                // 遍历结果集
                while (rs.next()) {
                    String id = rs.getString(showValue);
                    String name = rs.getString(showText);
                    String pid = rs.getString("pid");
                    Map<String, Object> map = new HashMap<>();
                    map.put(showValue, id);
                    map.put(showText, name);
                    map.put("pid", pid);
                    mapList.add(map);
                }
                // 创建树结构
                JSONArray result = TreeUtils.listToTree(JSONArray.parseArray(JSON.toJSONString(mapList)), "id", "pid", "children");
                // 创建构造器
                for (Object item : result) {
                    String id = ((JSONObject) item).get(showValue).toString();
                    String name = ((JSONObject) item).get(showText).toString();
                    // 创建一级选择器
                    model.add(modelFactory.createOpenElementTag(String.format("option value='%s'", id)));
                    model.add(modelFactory.createText(name));
                    model.add(modelFactory.createCloseElementTag("option"));
                    // 获取二级数据
                    if (((JSONObject) item).get("children") == null) {
                        continue;
                    }
                    List<Map<String, Object>> children = (List<Map<String, Object>>) ((JSONObject) item).get("children");
                    if (children != null) {
                        for (Map<String, Object> child : children) {
                            String id2 = child.get(showValue).toString();
                            String name2 = child.get(showText).toString();
                            // 创建二级选择器
                            model.add(modelFactory.createOpenElementTag(String.format("option value='%s'", id2)));
                            model.add(modelFactory.createText(String.format("|-- %s", name2)));
                            model.add(modelFactory.createCloseElementTag("option"));

                            // 获取三级数据
                            if (child.get("children") == null) {
                                continue;
                            }
                            List<Map<String, Object>> children2 = (List<Map<String, Object>>) child.get("children");
                            if (children2 != null) {
                                for (Map<String, Object> map : children2) {
                                    String id3 = map.get(showValue).toString();
                                    String name3 = map.get(showText).toString();
                                    // 创建三级选择器
                                    model.add(modelFactory.createOpenElementTag(String.format("option value='%s'", id3)));
                                    model.add(modelFactory.createText(String.format("|--|-- %s", name3)));
                                    model.add(modelFactory.createCloseElementTag("option"));
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.colseResource(conn, st, rs);
            }
        } else {
            // 初始化
            Map<Object, String> map = new HashMap<>();
            String[] dataArr = tagData.replaceAll("[\\s*|\\t|\\r|\\n{|}]+", "").split(",");
            for (String item : dataArr) {
                String[] subItem = item.split("=");
                if (StringUtils.isNumeric(subItem[0])) {
                    // 数字
                    map.put(Integer.valueOf(subItem[0]), subItem[1]);
                } else {
                    // 字符串
                    map.put(subItem[0], subItem[1]);
                }
            }

            // 循环遍历实体对象
            for (Map.Entry<Object, String> item : map.entrySet()) {
                if (tagValue.equals(item.getKey().toString())) {
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
