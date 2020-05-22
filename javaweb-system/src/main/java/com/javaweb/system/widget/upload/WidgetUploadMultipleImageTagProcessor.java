package com.javaweb.system.widget.upload;

import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.ArrayList;
import java.util.List;

public class WidgetUploadMultipleImageTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "uploadMultipleImage";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetUploadMultipleImageTagProcessor(String dialectPrefix) {
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
        String tagValue = iProcessableElementTag.getAttributeValue("value");
        String tagSize = iProcessableElementTag.getAttributeValue("size");
        String[] itemSize = tagSize.split("x");
        String tagTitle = iProcessableElementTag.getAttributeValue("title");
        String sizeTips = iProcessableElementTag.getAttributeValue("tips");

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();

        // 图片渲染区
        List<String> stringList = new ArrayList<>();
        if (!StringUtils.isEmpty(tagValue)) {
            String[] strings = tagValue.split(",");
            for (String string : strings) {
                stringList.add(CommonUtils.getImageURL(string));
            }
        }
        for (String s : stringList) {
            model.add(modelFactory.createOpenElementTag("div class=\"layui-upload-drag\""));
            model.add(modelFactory.createOpenElementTag(String.format("div class=\"del_img\" onclick=\"remove_image_%s(this);\"", tagName)));
            model.add(modelFactory.createOpenElementTag("img src=\"/static/assets/images/delete.png\""));
            model.add(modelFactory.createCloseElementTag("div"));
            model.add(modelFactory.createOpenElementTag(String.format("a href=\"%s\" target=\"_blank\"", s)));
            model.add(modelFactory.createOpenElementTag(String.format("img name=\"img_src_%s\" src=\"%s\" alt=\"%s(点击放大预览)\" title=\"%s(点击放大预览)\" width=\"%s\" height=\"%s\"", tagName, s, tagTitle, tagTitle, itemSize[0], itemSize[1])));
            model.add(modelFactory.createCloseElementTag("a"));
            model.add(modelFactory.createCloseElementTag("div"));
        }
        model.add(modelFactory.createOpenElementTag(String.format("div class=\"layui-upload-drag img_upload_%s\"", tagName)));
        model.add(modelFactory.createOpenElementTag(String.format("img id=\"btnUploadImg_%s\" src=\"/static/assets/images/default_upload.png\" alt=\"上传%s\" title=\"上传%s\" width=\"%s\" height=\"%s\"", tagName, tagTitle, tagTitle, itemSize[0], itemSize[1])));
        model.add(modelFactory.createOpenElementTag(String.format("input type=\"hidden\" id=\"%s\" name=\"%s\" value=\"\"", tagName, tagName)));
        model.add(modelFactory.createCloseElementTag("div"));

        // 创建CSS
        model.add(modelFactory.createOpenElementTag("style type=\"text/css\""));
        model.add(modelFactory.createText(".layui-upload-drag {\n" +
                "\t    position: relative;\n" +
                "\t    padding: 10px;\n" +
                "\t    border: 1px dashed #e2e2e2;\n" +
                "\t    background-color: #fff;\n" +
                "\t    text-align: center;\n" +
                "\t    cursor: pointer;\n" +
                "\t    color: #999;\n" +
                "\t\tmargin-right:10px;\n" +
                "\t\tmargin-bottom:10px;\n" +
                "\t}\n" +
                "\t.del_img{\n" +
                "\t\tposition: absolute;\n" +
                "\t\tz-index: 99;\n" +
                "\t\tright: 0;\n" +
                "\t\ttop: 0;\n" +
                "\t\twidth: 25px;\n" +
                "\t\theight: 25px;\n" +
                "\t\tdisplay: block;\n" +
                "\t}\n" +
                "\t.del_img img{\n" +
                "\t\tposition: absolute;\n" +
                "\t\tz-index: 9;\n" +
                "\t\tright: 0px;\n" +
                "\t\ttop: 0px;\n" +
                "\t\twidth: 25px;\n" +
                "\t\theight: 25px;\n" +
                "\t\tdisplay: inline-block;\n" +
                "\t}"));
        model.add(modelFactory.createCloseElementTag("style"));

        // 创建JS
        model.add(modelFactory.createOpenElementTag("script th:inline=\"javascript\" type=\"text/javascript\""));
        model.add(modelFactory.createText("layui.use(['upload'],function(){\n" +
                "\n" +
                "\t// 声明变量\n" +
                "\tvar upload = layui.upload\n" +
                "\t\t,$ = layui.$;\n" +
                "\n" +
                "\t// 初始化图片隐藏域\n" +
                "\tvar ids = '';\n" +
                String.format("\t$('img[name=\"img_src_%s\"]').each(function(){\n", tagName) +
                "\t\tids += $(this).attr('src') + \",\"\n" +
                "\t});\n" +
                "\tids = ids.substr(0, (ids.length - 1));\n" +
                String.format("\t$(\"#%s\").val(ids);\n", tagName) +
                "\n" +
                "\t/**\n" +
                "\t * 普通图片上传\n" +
                "\t */\n" +
                "\tvar uploadInst = upload.render({\n" +
                String.format("\t    elem: '#btnUploadImg_%s'\n", tagName) +
                "\t\t,url: \"/upload/uploadImage?name=admin\"\n" +
                "\t\t,accept:'images'\n" +
                "\t\t,acceptMime:'image/*'\n" +
                "\t\t,exts: \"jpg|png|gif|bmp|jpeg\"\n" +
                "\t\t,field:'file'//文件域字段名\n" +
                "\t\t,size: 10*1024 //最大允许上传的文件大小\n" +
                "\t  \t,multiple: true\n" +
                "\t  \t,number: 10 //最大上传张数\n" +
                "\t\t,before: function(obj){\n" +
                "\t\t\t//预读本地文件\n" +
                "\n" +
                "\t\t}\n" +
                "\t\t,done: function(res){\n" +
                "\t\t\t//上传完毕回调\n" +
                "\n" +
                String.format("\t\t\tvar hideStr = $(\"#%s\").attr(\"value\");\n", tagName) +
                "\t\t\tvar itemArr = hideStr.split(',');\n" +
                "\t\t\tif(itemArr.length>=10){\n" +
                "\t\t\t\tlayer.msg(\"最多上传10张图片\",{ icon: 5,time: 1000}, function () {\n" +
                "                \t//TODO...\n" +
                "                });\n" +
                "\t\t\t\treturn false;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t//如果上传失败\n" +
                "\t\t\tif(res.status <= 0){\n" +
                "\t\t\t\treturn layer.msg('上传失败');\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t//渲染界面\n" +
                "\t\t\tvar attStr = '<div class=\"layui-upload-drag\">'+\n" +
                String.format("\t\t\t\t\t\t\t'<div class=\"del_img\" onclick=\"remove_image_%s(this);\">'+\n", tagName) +
                "\t\t\t\t\t\t\t\t'<img src=\"/static/assets/images/delete.png\"></img>'+\n" +
                "\t\t\t\t\t\t\t'</div>'+\n" +
                "\t\t\t\t\t\t\t'<a href=\"'+res.data+'\" target=\"_blank\">'+\n" +
                String.format("\t\t\t\t\t\t \t\t'<img name=\"img_src_%s\" src=\"'+res.data+'\" alt=\"%s(点击放大预览)\" title=\"%s(点击放大预览)\" width=\"%s\" height=\"%s\">'+\n", tagName, tagTitle, tagTitle, itemSize[0], itemSize[1]) +
                "\t\t\t\t\t\t \t'</a>'+\n" +
                "\t\t\t\t\t\t '</div>';\n" +
                String.format("\t\t\t$(\".img_upload_%s\").before(attStr);\n", tagName) +
                "\n" +
                "\t\t\t//获取最新的图集\n" +
                "\t\t\tvar ids = '';\n" +
                String.format("$('img[name=\"img_src_%s\"]').each(function(){", tagName) +
                "\t\t\t\tids += $(this).attr('src') + \",\"\n" +
                "\t\t\t});\n" +
                "\t\t\tids = ids.substr(0, (ids.length - 1));\n" +
                "\t\t\t//给隐藏域赋值\n" +
                String.format("\t\t\t$(\"#%s\").val(ids);\n", tagName) +
                "\n" +
                "\t\t\treturn false;\n" +
                "\t\t}\n" +
                "\t\t,error: function(){\n" +
                "\t\t\t//请求异常回调\n" +
                "\t\t\treturn layer.msg('数据请求异常');\n" +
                "\t\t}\n" +
                "\t});\n" +
                "\n" +
                "});\n" +
                "\n" +
                "// 删除图片\n" +
                String.format("function remove_image_%s(obj) {\n", tagName) +
                "\t//obj.remove();\n" +
                "\tlayui.$(obj).parent().remove();\n" +
                "\n" +
                "\t//获取最新的图集\n" +
                "\tvar ids = '';\n" +
                String.format("\tlayui.$('img[name=\"img_src_%s\"]').each(function(){\n", tagName) +
                "\t\tids += layui.$(this).attr('src') + \",\"\n" +
                "\t});\n" +
                "\tids = ids.substr(0, (ids.length - 1));\n" +
                "\t//给隐藏域赋值\n" +
                String.format("\tlayui.$(\"#%s\").val(ids);\n", tagName) +
                "}"));
        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
