package com.javaweb.system.widget.xmselect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import java.util.*;

public class WidgetXmSelectTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "xmSelect";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetXmSelectTagProcessor(String dialectPrefix) {
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
        // 父级ID
        String showPid = itemArr[5];

        // 已选中值的处理
        String[] strings = tagValue.split(",");
        List<String> stringList = Arrays.asList(strings);

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();
        model.add(modelFactory.createOpenElementTag(String.format("div id=\"%s\" style=\"max-width: 400px;\"", showName)));
        model.add(modelFactory.createCloseElementTag("div"));

        // 数据集合
        List<Map<String, Object>> mapList = new ArrayList<>();
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
                // 遍历结果集
                while (rs.next()) {
                    String id = rs.getString(showValue);
                    String name = rs.getString(showText);
                    String pid = "0";
                    if (!StringUtils.isEmpty(showPid)) {
                        pid = rs.getString(showPid);
                    }
                    Map<String, Object> map = new HashMap<>();
                    map.put("value", Integer.valueOf(id));
                    map.put("name", name);
                    map.put(showPid, pid);
                    if (stringList.contains(id)) {
                        map.put("selected", true);
                    }
                    mapList.add(map);
                }
                // 创建树结构
                JSONArray result = listToTree(JSONArray.parseArray(JSON.toJSONString(mapList)), "value", showPid, "children");
                // 引用JS
                model.add(modelFactory.createOpenElementTag("script type=\"text/javascript\""));
                model.add(modelFactory.createText("layui.use(['xmSelect'], function () {\n" +
                        "\t    var xmSelect = layui.xmSelect;\n" +
                        "        // 渲染多选下拉\n" +
                        "        xmSelect.render({\n" +
                        String.format("            el: '#%s',\n", showName) +
                        String.format("            tips: '%s',\n", showTips) +
                        "            empty: '呀, 没有数据呢',\n" +
                        "            toolbar: {show: true},\n" +
                        "            filterable: true,\n" +
                        String.format("            searchTips: '%s',\n", showTips) +
                        String.format("            radio: %s,\n", false) +
                        String.format("            clickClose: %s,\n", false) +
                        String.format("            max: '%s',\n", 5) +
                        "maxMethod(seles, item){\n" +
                        "\t\talert(`${item.name}不能选了, 已经超了`)\n" +
                        "\t}," +
                        String.format("            height: '%spx',\n", 300) +
                        String.format("            data: %s", JSON.toJSONString(result)) +
                        "        });" +
                        "\t});"));
                model.add(modelFactory.createCloseElementTag("script"));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.colseResource(conn, st, rs);
            }
        } else {
            // 初始化
            String[] dataArr = tagData.replaceAll("[\\s*|\\t|\\r|\\n{|}]+", "").split(",");
            for (String item : dataArr) {
                String[] subItem = item.split("=");
                Map<String, Object> map = new HashMap<>();
                if (StringUtils.isNumeric(subItem[0])) {
                    // 数字
                    map.put("value", Integer.valueOf(subItem[0]));
                    map.put("name", subItem[1]);
                } else {
                    // 字符串
                    map.put("value", subItem[0]);
                    map.put("name", subItem[1]);
                }
                if (stringList.contains(subItem[0])) {
                    map.put("selected", true);
                }
                mapList.add(map);
            }
            // 引用JS
            model.add(modelFactory.createOpenElementTag("script type=\"text/javascript\""));
            model.add(modelFactory.createText("layui.use(['xmSelect'], function () {\n" +
                    "\t    var xmSelect = layui.xmSelect;\n" +
                    "        // 渲染多选下拉\n" +
                    "        xmSelect.render({\n" +
                    String.format("            el: '#%s',\n", showName) +
                    String.format("            tips: '%s',\n", showTips) +
                    "            empty: '呀, 没有数据呢',\n" +
                    "            toolbar: {show: true},\n" +
                    "            filterable: true,\n" +
                    String.format("            searchTips: '%s',\n", showTips) +
                    String.format("            radio: %s,\n", false) +
                    String.format("            clickClose: %s,\n", false) +
                    String.format("            max: '%s',\n", 5) +
                    "maxMethod(seles, item){\n" +
                    "\t\talert(`${item.name}不能选了, 已经超了`)\n" +
                    "\t}," +
                    String.format("            height: '%spx',\n", 300) +
                    String.format("            data: %s", JSON.toJSONString(mapList)) +
                    "        });" +
                    "\t});"));
            model.add(modelFactory.createCloseElementTag("script"));
        }
        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }

    /**
     * listToTree
     * <p>方法说明<p>
     * 将JSONArray数组转为树状结构
     *
     * @param arr   需要转化的数据
     * @param id    数据唯一的标识键值
     * @param pid   父id唯一标识键值
     * @param child 子节点键值
     * @return JSONArray
     */
    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child) {
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for (int i = 0; i < arr.size(); i++) {
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for (int j = 0; j < arr.size(); j++) {
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if (hashVP != null) {
                //检查是否有child属性
                if (hashVP.get(child) != null) {
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                } else {
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put("optgroup", true);
                    hashVP.put("click", "SELECT");
                    hashVP.put(child, ch);
                }
            } else {
                r.add(aVal);
            }
        }
        return r;
    }
}
