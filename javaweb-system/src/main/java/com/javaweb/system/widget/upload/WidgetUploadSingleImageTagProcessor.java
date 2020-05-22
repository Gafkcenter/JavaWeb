package com.javaweb.system.widget.upload;

import org.springframework.util.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetUploadSingleImageTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "uploadSingleImage";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetUploadSingleImageTagProcessor(String dialectPrefix) {
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
        // 这里是将字典的内容拼装成一个下拉框
        model.add(modelFactory.createOpenElementTag("div class=\"layui-input-block\""));

        model.add(modelFactory.createOpenElementTag("div class=\"layui-upload-drag\""));

        if (StringUtils.isEmpty(tagValue)) {
            model.add(modelFactory.createOpenElementTag("a href=\"javascript:void(0);\""));
            model.add(modelFactory.createOpenElementTag(String.format("img id=\"%s_show_id\" src=\"/static/images/default_upload.png\" alt=\"上传图片\" width=\"%s\" height=\"%s\"", tagName, Integer.valueOf(itemSize[0]), Integer.valueOf(itemSize[1]))));
        } else {
            model.add(modelFactory.createOpenElementTag(String.format("a href=\"%s\" target=\"black\"", tagValue)));
            model.add(modelFactory.createOpenElementTag(String.format("img id=\"%s_show_id\" src=\"%s\" alt=\"点击查看大图\" width=\"%s\" height=\"%s\"", tagName, tagValue, Integer.valueOf(itemSize[0]), Integer.valueOf(itemSize[1]))));
        }
        model.add(modelFactory.createCloseElementTag("a"));
        model.add(modelFactory.createOpenElementTag(String.format("input type=\"hidden\" id=\"%s\" name=\"%s\" value=\"%s\"", tagName, tagName, tagValue)));
        model.add(modelFactory.createCloseElementTag("div"));

        model.add(modelFactory.createOpenElementTag("div style=\"margin-top:10px;\""));
        model.add(modelFactory.createOpenElementTag(String.format("button type=\"button\" class=\"layui-btn\" id=\"btnUploadImg_%s\"", tagName)));
        model.add(modelFactory.createOpenElementTag("i class=\"layui-icon\""));
        model.add(modelFactory.createText("&#xe67c;"));
        model.add(modelFactory.createCloseElementTag("i"));
        model.add(modelFactory.createText(String.format("上传%s", tagTitle)));
        model.add(modelFactory.createCloseElementTag("button"));
        model.add(modelFactory.createCloseElementTag("div"));

        // 创建建议尺寸
        if (!StringUtils.isEmpty(sizeTips)) {
            model.add(modelFactory.createOpenElementTag("div class=\"layui-form-mid layui-word-aux\""));
            model.add(modelFactory.createText(String.format("建议尺寸：%s", sizeTips)));
            model.add(modelFactory.createCloseElementTag("div"));
        }
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
                "\t}"));
        model.add(modelFactory.createCloseElementTag("style"));

        // 创建JS
        model.add(modelFactory.createOpenElementTag("script th:inline=\"javascript\" type=\"text/javascript\""));
        model.add(modelFactory.createText("layui.use(['upload','croppers'],function(){\n" +
                "\t//声明变量\n" +
                "\tvar layer = layui.layer\n" +
                "\t,upload = layui.upload\n" +
                "\t,croppers = layui.croppers\n" +
                "\t,$ = layui.$;\n" +
                "\t\n" +
                "\tif(true) {\n" +
                "\t\t\n" +
                "\t\t//图片裁剪组件\n" +
                "\t    croppers.render({\n" +
                String.format("\t        elem: '#btnUploadImg_%s'\n", tagName) +
                String.format("\t        ,name:\"%s\"\n", tagName) +
                "\t        ,saveW:450     //保存宽度\n" +
                "\t        ,saveH:300\n" +
                "\t        ,mark:1/1    //选取比例\n" +
                "\t        ,area:['750px','500px']  //弹窗宽度\n" +
                "\t        ,url: \"/upload/uploadImage?name=admin\"\n" +
                "\t        ,done: function(url){ \n" +
                "\t        \t//上传完毕回调\n" +
                String.format("\t            $('#%s').val(url);\n", tagName) +
                String.format("\t            $('#%s_show_id').attr('src',url);\n", tagName) +
                "\t        }\n" +
                "\t    });\n" +
                "\t\t\n" +
                "\t}else{\n" +
                "\t\t\n" +
                "\t\t/**\n" +
                "\t\t * 普通图片上传\n" +
                "\t\t */\n" +
                "\t\tvar uploadInst = upload.render({\n" +
                String.format("\t\t    elem: '#btnUploadImg_%s'\n", tagName) +
                "\t\t\t,url: \"/upload/uploadImage?name=admin\"\n" +
                "\t\t\t,accept:'images'\n" +
                "\t\t\t,acceptMime:'image/*'\n" +
                "\t\t\t,exts: \"jpg|png|gif|bmp|jpeg\"\n" +
                "\t\t\t,field:'file'//文件域字段名\n" +
                "\t\t\t,size: 1024 * 10 //最大允许上传的文件大小\n" +
                "\t\t\t,before: function(obj){\n" +
                "\t\t\t\t//预读本地文件\n" +
                "\t\t\t}\n" +
                "\t\t\t,done: function(res){\n" +
                "\t\t\t\t//上传完毕回调\n" +
                "\t\t\t\t\n" +
                "\t\t\t\tif(res.code!=0){\n" +
                "\t\t\t\t\tlayer.msg(res.msg,{ icon: 5 });\n" +
                "\t\t\t\t\treturn false;\n" +
                "\t\t\t\t}\n" +
                "\n" +
                "\t\t\t\t//上传成功\n" +
                String.format("\t\t\t\t$('#%s_show_id').attr('src', res.data);\n", tagName) +
                String.format("\t    \t\t$('#%s').val(res.data);\n", tagName) +
                "\t\t\t}\n" +
                "\t\t\t,error: function(){\n" +
                "\t\t\t\t//请求异常回调\n" +
                "\t\t\t\treturn layer.msg('数据请求异常');\n" +
                "\t\t\t}\n" +
                "\t\t});\n" +
                "\t\t\n" +
                "\t}\n" +
                "\t\n" +
                "});"));
        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
