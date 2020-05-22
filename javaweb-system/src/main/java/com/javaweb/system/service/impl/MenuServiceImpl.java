package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.shiro.utils.ShiroUtils;
import com.javaweb.system.constant.MenuConstant;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.mapper.MenuMapper;
import com.javaweb.system.query.MenuQuery;
import com.javaweb.system.service.IMenuService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.system.vo.MenuListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-07
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MenuQuery menuQuery = (MenuQuery) query;
        // 查询条件
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        // 菜单名称
        if (!StringUtils.isEmpty(menuQuery.getName())) {
            queryWrapper.like("name", menuQuery.getName());
        }
        // 类型：1模块 2导航 3菜单 4节点
        if (menuQuery.getType() != null && menuQuery.getType() > 0) {
            queryWrapper.eq("type", menuQuery.getType());
        }
        // 是否显示：1显示 2不显示
        if (menuQuery.getStatus() != null && menuQuery.getStatus() > 0) {
            queryWrapper.eq("status", menuQuery.getStatus());
        }
        // 是否公共：1是 2否
        if (menuQuery.getIsPublic() != null && menuQuery.getIsPublic() > 0) {
            queryWrapper.eq("is_public", menuQuery.getIsPublic());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        List<MenuListVo> menuListVoList = new ArrayList<>();
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                MenuListVo menuListVo = new MenuListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, menuListVo);
                // 类型描述
                if (menuListVo.getType() != null && menuListVo.getType() > 0) {
                    menuListVo.setTypeName(MenuConstant.MENU_TYPE_LIST.get(menuListVo.getType()));
                }
                // 是否显示描述
                if (menuListVo.getStatus() != null && menuListVo.getStatus() > 0) {
                    menuListVo.setStatusName(MenuConstant.MENU_STATUS_LIST.get(menuListVo.getStatus()));
                }
                // 是否公共描述
                if (menuListVo.getIsPublic() != null && menuListVo.getIsPublic() > 0) {
                    menuListVo.setIsPublicName(MenuConstant.MENU_ISPUBLIC_LIST.get(menuListVo.getIsPublic()));
                }
                // 创建人名称
                if (menuListVo.getCreateUser() != null && menuListVo.getCreateUser() > 0) {
                    menuListVo.setCreateUserName(AdminUtils.getName((menuListVo.getCreateUser())));
                }
                // 更新人名称
                if (menuListVo.getUpdateUser() != null && menuListVo.getUpdateUser() > 0) {
                    menuListVo.setUpdateUserName(AdminUtils.getName((menuListVo.getUpdateUser())));
                }
                // 默认展开
                if (menuListVo.getType() <= 2) {
                    menuListVo.setOpen(true);
                } else {
                    menuListVo.setOpen(false);
                }
                menuListVoList.add(menuListVo);
            });
        }
        return JsonResult.success("操作成功", menuListVoList);
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Menu entity = (Menu) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Menu entity) {
        if (entity == null) {
            return JsonResult.error("实体对象不存在");
        }
        boolean result = false;
        if (entity.getId() != null && entity.getId() > 0) {
            // 修改记录
            entity.setUpdateUser(ShiroUtils.getAdminId());
            entity.setUpdateTime(DateUtils.now());
            result = this.updateById(entity);
        } else {
            // 新增记录
            entity.setCreateUser(ShiroUtils.getAdminId());
            entity.setCreateTime(DateUtils.now());
            entity.setMark(1);
            result = this.save(entity);
        }
        if (!result) {
            return JsonResult.error();
        }

        // 菜单节点处理
        if (!StringUtils.isEmpty(entity.getUrl()) && !entity.getUrl().equals("#")) {
            String[] urlItem = entity.getUrl().split("/");
            // 模块名
            String module = urlItem[1];
            if (!StringUtils.isEmpty(entity.getFuncIds())) {
                String[] item = entity.getFuncIds().split(",");
                for (String s : item) {
                    Menu funcInfo = new Menu();
                    if (s.equals("1")) {
                        // 列表
                        funcInfo.setName("列表");
                        funcInfo.setUrl(String.format("/%s/list", module));
                        funcInfo.setPermission(String.format("sys:%s:list", module));
                    } else if (s.equals("5")) {
                        // 添加
                        funcInfo.setName("添加");
                        funcInfo.setUrl(String.format("/%s/add", module));
                        funcInfo.setPermission(String.format("sys:%s:add", module));
                    } else if (s.equals("10")) {
                        // 修改
                        funcInfo.setName("修改");
                        funcInfo.setUrl(String.format("/%s/update", module));
                        funcInfo.setPermission(String.format("sys:%s:update", module));
                    } else if (s.equals("15")) {
                        // 删除
                        funcInfo.setName("删除");
                        funcInfo.setUrl(String.format("/%s/delete", module));
                        funcInfo.setPermission(String.format("sys:%s:delete", module));
                    } else if (s.equals("20")) {
                        // 状态
                        funcInfo.setName("状态");
                        funcInfo.setUrl(String.format("/%s/setStatus", module));
                        funcInfo.setPermission(String.format("sys:%s:status", module));
                    }
                    funcInfo.setPid(entity.getId());
                    funcInfo.setType(4);
                    funcInfo.setStatus(1);
                    funcInfo.setIsPublic(2);
                    funcInfo.setSort(Integer.valueOf(s));
                    menuMapper.insert(funcInfo);
                }
            }
        }
        return JsonResult.success("操作成功");
    }

    /**
     * 删除记录
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public JsonResult deleteById(Integer id) {
        if (id == null || id == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        Menu entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(Menu entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 获取导航菜单
     *
     * @return
     */
    @Override
    public List<MenuListVo> getNavbarMenu() {
        // 顶部导航
        List<MenuListVo> menuListVoList = menuMapper.getNavbarMenu(0);
        if (!menuListVoList.isEmpty()) {
            menuListVoList.forEach(item -> {
                // 模块
                List<MenuListVo> menuListVoList1 = menuMapper.getNavbarMenu(item.getId());
                if (!menuListVoList1.isEmpty()) {
                    menuListVoList1.forEach(subItem -> {
                        // 菜单
                        List<MenuListVo> menuListVoList2 = menuMapper.getNavbarMenu(subItem.getId());
                        if (!menuListVoList2.isEmpty()) {
                            menuListVoList2.forEach(menu -> {
                                // 节点
                                List<MenuListVo> menuListVoList3 = menuMapper.getNavbarMenu(menu.getId());
                                menu.setChildren(menuListVoList3);
                            });
                        }
                        subItem.setChildren(menuListVoList2);
                    });
                    item.setChildren(menuListVoList1);
                }
            });
        }
        return menuListVoList;
    }
}