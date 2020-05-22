package com.javaweb.system.widget.editor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetKindeditorTagProcessor extends AbstractElementTagProcessor {
    /**
     * 标签名
     */
    private static final String TAG_NAME = "kindEditor";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetKindeditorTagProcessor(String dialectPrefix) {
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
        String kindId = iProcessableElementTag.getAttributeValue("name");
        String tagType = iProcessableElementTag.getAttributeValue("type");
        String tagWidth = iProcessableElementTag.getAttributeValue("width");
        String tagHeight = iProcessableElementTag.getAttributeValue("height");

        // 创建将替换自定义标签的 DOM 结构
        IModelFactory modelFactory = iTemplateContext.getModelFactory();
        IModel model = modelFactory.createModel();

        // 引入第三方JS
        model.add(modelFactory.createOpenElementTag("link rel=\"stylesheet\" href=\"/static/assets/libs/kindeditor/themes/default/default.css\""));
        if (tagType.equals("simple")) {
            model.add(modelFactory.createOpenElementTag("link rel=\"stylesheet\" href=\"/static/assets/libs/kindeditor/themes/simple/simple.css\""));
        }
        model.add(modelFactory.createOpenElementTag("script type=\"text/javascript\" src=\"/static/assets/libs/kindeditor/kindeditor-min.js\""));
        model.add(modelFactory.createCloseElementTag("script"));
        model.add(modelFactory.createOpenElementTag("script type=\"text/javascript\" src=\"/static/assets/libs/kindeditor/lang/zh_CN.js\""));
        model.add(modelFactory.createCloseElementTag("script"));

        // 创建CSS
        model.add(modelFactory.createOpenElementTag("style type=\"text/css\""));
        model.add(modelFactory.createText("\nform{ margin:0; }\n" +
                "textarea{ display:block;}\n"));
        model.add(modelFactory.createCloseElementTag("style\n"));

        // 创建JS
        model.add(modelFactory.createOpenElementTag("script th:inline=\"javascript\" type=\"text/javascript\""));
        model.add(modelFactory.createText("(function(){\n" +
                "\tvar editor;\n" +
                "\tvar items = {\n" +
                "\t\tsimple : \n" +
                "\t\t\t['source', 'preview', 'plainpaste', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',\n" +
                "\t\t\t'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',\n" +
                "\t\t\t'insertunorderedlist', 'indent', 'outdent', 'quickformat', '|', 'link']\n" +
                "\t\t,\n" +
                "\t\tdefault : \n" +
                "\t\t\t['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',\n" +
                "\t\t\t'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',\n" +
                "\t\t\t'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',\n" +
                "\t\t\t'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',\n" +
                "\t\t\t'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',\n" +
                "\t\t\t'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',\n" +
                "\t\t\t'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',\n" +
                "\t\t\t'anchor', 'link', 'unlink', '|', 'about']\n" +
                "\t\t\n" +
                "\t};\n" +
                "\t\t\n" +
                "\tvar options = {\n" +
                "\t\tallowFileManager : true,\n" +
                String.format("\t\twidth : \"%s\",\n", tagWidth) +
                String.format("\t\theight : %s,\n", tagHeight) +
                "\t\tdesignMode : true,\n" +
                "\t\tfullscreenMode : false,\n" +
                "\t\tfilterMode : true,\n" +
                "\t\twellFormatMode : true,\n" +
                "\t\tshadowMode : true,\n" +
                "\t\tloadStyleMode : true,\n" +
                String.format("\t\tthemeType : '%s',\n", tagType) +
                "\t\tlangType : 'zh_CN',\n" +
                "\t\turlType : '',\n" +
                "\t\tnewlineTag : 'p',\n" +
                "\t\tresizeType : 2,\n" +
                "\t\tsyncType : 'form',\n" +
                "\t\tpasteType : 2,\n" +
                "\t\tdialogAlignType : 'page',\n" +
                "\t\tuseContextmenu : true,\n" +
                "\t\tfullscreenShortcut : false,\n" +
                "\t\tbodyClass : 'ke-content',\n" +
                "\t\tindentChar : '\\t',\n" +
                "\t\tcssPath : '',\n" +
                "\t\tcssData : '',\n" +
                "\t\tminWidth : 650,\n" +
                "\t\tminHeight : 100,\n" +
                "\t\tminChangeSize : 50,\n" +
                "\t\tzIndex : 811213,\n" +
                String.format("\t\titems : items['%s'],\n", tagType) +
                "\t\tnoDisableItems : ['source', 'fullscreen'],\n" +
                "\t\tcolorTable : [\n" +
                "\t\t\t['#E53333', '#E56600', '#FF9900', '#64451D', '#DFC5A4', '#FFE500'],\n" +
                "\t\t\t['#009900', '#006600', '#99BB00', '#B8D100', '#60D978', '#00D5FF'],\n" +
                "\t\t\t['#337FE5', '#003399', '#4C33E5', '#9933E5', '#CC33E5', '#EE33EE'],\n" +
                "\t\t\t['#FFFFFF', '#CCCCCC', '#999999', '#666666', '#333333', '#000000']\n" +
                "\t\t],\n" +
                "\t\tfontSizeTable : ['9px', '10px', '12px', '14px', '16px', '18px', '24px', '32px'],\n" +
                "\t\thtmlTags : {\n" +
                "\t\t\tfont : ['id', 'class', 'color', 'size', 'face', '.background-color'],\n" +
                "\t\t\tspan : [\n" +
                "\t\t\t\t'id', 'class', '.color', '.background-color', '.font-size', '.font-family', '.background',\n" +
                "\t\t\t\t'.font-weight', '.font-style', '.text-decoration', '.vertical-align', '.line-height'\n" +
                "\t\t\t],\n" +
                "\t\t\tdiv : [\n" +
                "\t\t\t\t'id', 'class', 'align', '.border', '.margin', '.padding', '.text-align', '.color',\n" +
                "\t\t\t\t'.background-color', '.font-size', '.font-family', '.font-weight', '.background',\n" +
                "\t\t\t\t'.font-style', '.text-decoration', '.vertical-align', '.margin-left'\n" +
                "\t\t\t],\n" +
                "\t\t\ttable: [\n" +
                "\t\t\t\t'id', 'class', 'border', 'cellspacing', 'cellpadding', 'width', 'height', 'align', 'bordercolor',\n" +
                "\t\t\t\t'.padding', '.margin', '.border', 'bgcolor', '.text-align', '.color', '.background-color',\n" +
                "\t\t\t\t'.font-size', '.font-family', '.font-weight', '.font-style', '.text-decoration', '.background',\n" +
                "\t\t\t\t'.width', '.height', '.border-collapse'\n" +
                "\t\t\t],\n" +
                "\t\t\t'td,th': [\n" +
                "\t\t\t\t'id', 'class', 'align', 'valign', 'width', 'height', 'colspan', 'rowspan', 'bgcolor',\n" +
                "\t\t\t\t'.text-align', '.color', '.background-color', '.font-size', '.font-family', '.font-weight',\n" +
                "\t\t\t\t'.font-style', '.text-decoration', '.vertical-align', '.background', '.border'\n" +
                "\t\t\t],\n" +
                "\t\t\ta : ['id', 'class', 'href', 'target', 'name'],\n" +
                "\t\t\tembed : ['id', 'class', 'src', 'width', 'height', 'type', 'loop', 'autostart', 'quality', '.width', '.height', 'align', 'allowscriptaccess'],\n" +
                "\t\t\timg : ['id', 'class', 'src', 'width', 'height', 'border', 'alt', 'title', 'align', '.width', '.height', '.border'],\n" +
                "\t\t\t'p,ol,ul,li,blockquote,h1,h2,h3,h4,h5,h6' : [\n" +
                "\t\t\t\t'id', 'class', 'align', '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.background',\n" +
                "\t\t\t\t'.font-weight', '.font-style', '.text-decoration', '.vertical-align', '.text-indent', '.margin-left'\n" +
                "\t\t\t],\n" +
                "\t\t\tpre : ['id', 'class'],\n" +
                "\t\t\thr : ['id', 'class', '.page-break-after'],\n" +
                "\t\t\t'br,tbody,tr,strong,b,sub,sup,em,i,u,strike,s,del' : ['id', 'class'],\n" +
                "\t\t\tiframe : ['id', 'class', 'src', 'frameborder', 'width', 'height', '.width', '.height']\n" +
                "\t\t},\n" +
                "\t\tlayout : '<div class=\"container\"><div class=\"toolbar\"></div><div class=\"edit\"></div><div class=\"statusbar\"></div></div>',\n" +
                "\t\tafterBlur: function () { editor.sync(); }\n" +
                "\t};\n" +
                "\t\n" +
                "\tKindEditor.ready(function(K) {\n" +
                String.format("\t\teditor = K.create('textarea[name=\"%s\"]', options);\n", kindId) +
                "\t});\n" +
                "})();"));
        model.add(modelFactory.createCloseElementTag("script"));

        // 利用引擎替换整合标签
        iElementTagStructureHandler.replaceWith(model, false);
    }
}
