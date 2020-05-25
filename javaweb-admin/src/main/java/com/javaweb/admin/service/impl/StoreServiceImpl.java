package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.admin.constant.StoreConstant;
import com.javaweb.admin.entity.Store;
import com.javaweb.admin.mapper.StoreMapper;
import com.javaweb.admin.query.StoreQuery;
import com.javaweb.admin.service.IStoreService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.StoreListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-10
 */
@Service
public class StoreServiceImpl extends BaseServiceImpl<StoreMapper, Store> implements IStoreService {

    @Autowired
    private StoreMapper storeMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        StoreQuery storeQuery = (StoreQuery) query;
        // 查询条件
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        // 店铺类型：1公司 2个人
        if (storeQuery.getStoreType() != null && storeQuery.getStoreType() > 0) {
            queryWrapper.eq("store_type", storeQuery.getStoreType());
        }
        // 是否推荐：1是 2否
        if (storeQuery.getStoreRecommend() != null && storeQuery.getStoreRecommend() > 0) {
            queryWrapper.eq("store_recommend", storeQuery.getStoreRecommend());
        }
        // 店铺状态：1正常 2审核中 2关闭
        if (storeQuery.getStoreStatus() != null && storeQuery.getStoreStatus() > 0) {
            queryWrapper.eq("store_status", storeQuery.getStoreStatus());
        }
        // 是否平台店铺：1是 2否
        if (storeQuery.getIsPlatform() != null && storeQuery.getIsPlatform() > 0) {
            queryWrapper.eq("is_platform", storeQuery.getIsPlatform());
        }
        // 申请状态：0信息已保存 10已提交申请 20资料审核成功 30资料审核失败 40缴费完成 50缴费审核失败 60审核通过开店
        if (storeQuery.getStatus() != null && storeQuery.getStatus() > 0) {
            queryWrapper.eq("status", storeQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Store> page = new Page<>(storeQuery.getPage(), storeQuery.getLimit());
        IPage<Store> data = storeMapper.selectPage(page, queryWrapper);
        List<Store> storeList = data.getRecords();
        List<StoreListVo> storeListVoList = new ArrayList<>();
        if (!storeList.isEmpty()) {
            storeList.forEach(item -> {
                StoreListVo storeListVo = new StoreListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, storeListVo);
                // 店铺类型描述
                if (storeListVo.getStoreType() != null && storeListVo.getStoreType() > 0) {
                    storeListVo.setStoreTypeName(StoreConstant.STORE_STORETYPE_LIST.get(storeListVo.getStoreType()));
                }
                // 店铺LOGO地址
                if (!StringUtils.isEmpty(storeListVo.getStoreLogo())) {
                    storeListVo.setStoreLogoUrl(CommonUtils.getImageURL(storeListVo.getStoreLogo()));
                }
                // 店铺头像地址
                if (!StringUtils.isEmpty(storeListVo.getStoreAvatar())) {
                    storeListVo.setStoreAvatarUrl(CommonUtils.getImageURL(storeListVo.getStoreAvatar()));
                }
                // 是否推荐描述
                if (storeListVo.getStoreRecommend() != null && storeListVo.getStoreRecommend() > 0) {
                    storeListVo.setStoreRecommendName(StoreConstant.STORE_STORERECOMMEND_LIST.get(storeListVo.getStoreRecommend()));
                }
                // 店铺状态描述
                if (storeListVo.getStoreStatus() != null && storeListVo.getStoreStatus() > 0) {
                    storeListVo.setStoreStatusName(StoreConstant.STORE_STORESTATUS_LIST.get(storeListVo.getStoreStatus()));
                }
                // 是否平台店铺描述
                if (storeListVo.getIsPlatform() != null && storeListVo.getIsPlatform() > 0) {
                    storeListVo.setIsPlatformName(StoreConstant.STORE_ISPLATFORM_LIST.get(storeListVo.getIsPlatform()));
                }
                // 申请状态描述
                if (storeListVo.getStatus() != null && storeListVo.getStatus() > 0) {
                    storeListVo.setStatusName(StoreConstant.STORE_STATUS_LIST.get(storeListVo.getStatus()));
                }
                // 添加人名称
                if (storeListVo.getCreateUser() != null && storeListVo.getCreateUser() > 0) {
                    storeListVo.setCreateUserName(AdminUtils.getName((storeListVo.getCreateUser())));
                }
                // 更新人名称
                if (storeListVo.getUpdateUser() != null && storeListVo.getUpdateUser() > 0) {
                    storeListVo.setUpdateUserName(AdminUtils.getName((storeListVo.getUpdateUser())));
                }
                storeListVoList.add(storeListVo);
            });
        }
        return JsonResult.success("操作成功", storeListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Store entity = (Store) super.getInfo(id);
        // 店铺LOGO解析
        if (!StringUtils.isEmpty(entity.getStoreLogo())) {
            entity.setStoreLogo(CommonUtils.getImageURL(entity.getStoreLogo()));
        }
        // 店铺头像解析
        if (!StringUtils.isEmpty(entity.getStoreAvatar())) {
            entity.setStoreAvatar(CommonUtils.getImageURL(entity.getStoreAvatar()));
        }
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Store entity) {
        // 店铺LOGO
        if (entity.getStoreLogo().contains(CommonConfig.imageURL)) {
            entity.setStoreLogo(entity.getStoreLogo().replaceAll(CommonConfig.imageURL, ""));
        }
        // 店铺头像
        if (entity.getStoreAvatar().contains(CommonConfig.imageURL)) {
            entity.setStoreAvatar(entity.getStoreAvatar().replaceAll(CommonConfig.imageURL, ""));
        }
        return super.edit(entity);
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
        Store entity = this.getById(id);
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
    public JsonResult setStatus(Store entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }
}