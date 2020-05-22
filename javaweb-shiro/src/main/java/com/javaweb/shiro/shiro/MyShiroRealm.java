package com.javaweb.shiro.shiro;

import com.javaweb.common.exception.user.CaptchaException;
import com.javaweb.common.exception.user.UserNotExistsException;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.shiro.entity.Admin;
import com.javaweb.shiro.entity.Menu;
import com.javaweb.shiro.entity.Role;
import com.javaweb.shiro.service.IShiroLoginService;
import com.javaweb.shiro.service.IShiroMenuService;
import com.javaweb.shiro.service.IShiroRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private IShiroLoginService shiroLoginService;

    @Autowired
    private IShiroRoleService roleService;

    @Autowired
    private IShiroMenuService menuService;

    /**
     * 授权权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.warn("开始执行授权操作.......");
        Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 权限设置
        if (!admin.getId().equals(1)) {
            // 超级管理员
            simpleAuthorizationInfo.addStringPermission("*:*:*");
        } else {
            // 普通管理员及其他

            // 设置角色权限
            List<Role> roleList = roleService.getRoleListByAdminId(admin.getId());
            Set<String> roles = new HashSet<>();
            roleList.forEach(item -> {
                roles.add(item.getId().toString());
            });
            simpleAuthorizationInfo.addRoles(roles);

            // 设置菜单权限
            List<Menu> menuList = menuService.getMenuListByAdminId(admin.getId());
            Set<String> permissions = new HashSet<>();
            for (Menu menu : menuList) {
                if (StringUtils.isEmpty(menu.getPermission())) {
                    continue;
                }
                permissions.add(menu.getPermission());
                System.out.println("菜单权限：" + menu.getPermission());
            }
            simpleAuthorizationInfo.addStringPermissions(permissions);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }
        Admin admin = null;
        try {
            admin = shiroLoginService.login(username, password);
        } catch (CaptchaException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (LockedAccountException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        //进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                admin,                                  //用户名
                admin.getPassword(),                    //密码
                ByteSource.Util.bytes(""),      //设置盐值
                getName()
        );
        return authenticationInfo;
    }
}
