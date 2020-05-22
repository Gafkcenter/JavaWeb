package com.javaweb.system.widget;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class WidgetShiroTagProcessor extends AbstractAttributeTagProcessor {
    /**
     * 标签名
     */
    private static final String ATTR_NAME = "shiro";
    /**
     * 优先级
     */
    private static final int PRECEDENCE = 10000;

    /**
     * 构造函数
     *
     * @param dialectPrefix 前缀
     */
    public WidgetShiroTagProcessor(String dialectPrefix) {
        super(
                // 此处理器将仅应用于HTML模式
                TemplateMode.HTML,
                // 要应用于名称的匹配前缀
                dialectPrefix,
                // 标签名称：匹配此名称的特定标签
                null,
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

    /**
     * https://blog.csdn.net/z793397795/article/details/97389683
     * @param iTemplateContext
     * @param iProcessableElementTag
     * @param attributeName
     * @param attributeValue
     * @param iElementTagStructureHandler
     */
    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler iElementTagStructureHandler) {
//        String templateName = iProcessableElementTag.getTemplateName();
//        Map<String, Object> params = new HashMap<>();
//        params.put("url", "/" + templateName);//按钮所在的html
//        params.put("name", attributeValue);//按钮名称

        //如果是管理员，则忽略权限控制
//        MenuService menuService = getMenuService();
//        User user = UserUtil.getUser();
//        if("admin".equals(user.getLoginName())) {//如果是管理员，则忽略权限控制
//            return;
//        }
        //1、查询当前模板的按钮所拥有的权限,查询是否可以显示
//        List<Map<String, Object>> btnRole = menuService.findMenuBtnRole(params);
//        if (CollectionUtils.isEmpty(btnRole)) {
//            iElementTagStructureHandler.removeElement();//删除当前元素，不显示操作按钮
//            return;
//        }
        //2、获取当前用户的角色
//        Map<String, Object> userRole = getUserService().findRoleByUserId(user.getId() + "");
//        Set<String> userRoleSet = userRole.keySet();
        //3、比对当前用户是否拥有显示操作按钮的权限
//        for (int i = 0, len = btnRole.size(); i < len; i++) {
//            Map<String, Object> map = btnRole.get(i);
//            String roleIds = map.get("role_id").toString();//可能会包含逗号，比如 1，3，4
//            boolean boo = MenuUtil.permissionMatching(roleIds, userRoleSet);
//            if (boo) {//如果包含可拥有显示操作的按钮的权限，则逻辑结束，否则，不显示操作按钮
//                return;
//            }
//        }
//        iElementTagStructureHandler.removeElement();//删除当前元素
    }
}
