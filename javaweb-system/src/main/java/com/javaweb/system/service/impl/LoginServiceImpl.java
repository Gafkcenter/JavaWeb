package com.javaweb.system.service.impl;

import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.system.entity.Admin;
import com.javaweb.system.mapper.AdminMapper;
import com.javaweb.system.service.ILoginService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-31
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl<AdminMapper, Admin> implements ILoginService {

//    @Autowired
//    private AdminMapper adminMapper;
//
//    @Autowired
//    private RoleMapper roleMapper;
//
//    @Autowired
//    private MenuMapper menuMapper;
//
//    @Override
//    public Admin getadminByName(String adminname) {
//        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("adminname", adminname);
//        queryWrapper.eq("mark", 1);
//        Admin admin = adminMapper.selectOne(queryWrapper);
//        return admin;
//    }
//
//    @Override
//    public List<Menu> getAuthRule() {
//        Admin entity = (Admin) SecurityUtils.getSubject().getPrincipal();
//        Integer adminId = entity.getId();
//        Admin adminInfo = adminMapper.selectById(adminId);
//
//        // 权限数组
//        List<String> stringList = new ArrayList<>();
//        if (!StringUtils.isEmpty(adminInfo.getRules())) {
//            String[] itemArr = adminInfo.getRules().split(",");
//            for (String s : itemArr) {
//                stringList.add(s);
//            }
//        }
//
//        // 获取用户角色数组
//        List<Role> roleList = roleMapper.getAdminRoleList(adminId);
//        if (!roleList.isEmpty()) {
//            roleList.forEach(item -> {
//                if (!StringUtils.isEmpty(item.getRules())) {
//                    String[] subItem = item.getRules().split(",");
//                    for (String s : subItem) {
//                        if (!stringList.contains(s)) {
//                            stringList.add(s);
//                        }
//                    }
//                }
//            });
//        }
//
//        // 查询权限
//        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("id", org.apache.commons.lang3.StringUtils.join(stringList, ","));
//        queryWrapper.eq("status", 1);
//        queryWrapper.eq("mark", 1);
//        List<Menu> menuList = menuMapper.selectList(queryWrapper);
//        return menuList;
//    }
}
