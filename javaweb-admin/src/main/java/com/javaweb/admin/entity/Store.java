package com.javaweb.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_store")
public class Store extends BaseEntity {

    /**
     * 店铺分类ID
     */
    private Integer storeCategoryId;

    /**
     * 店铺分类名称
     */
    private String storeCategoryName;

    /**
     * 店铺等级ID
     */
    private Integer storeGradeId;

    /**
     * 店铺等级名称
     */
    private String storeGradeName;

    /**
     * 店铺类型：1公司 2个人
     */
    private Integer storeType;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 店铺简介
     */
    private String storeIntro;

    /**
     * 店铺公司名称
     */
    private String storeCompanyName;

    /**
     * 公司所在地区(如 江苏 南京)
     */
    private String storeCompanyArea;

    /**
     * 店铺地址
     */
    private String storeAddress;

    /**
     * 邮政编码
     */
    private String storeZipcode;

    /**
     * 店铺LOGO
     */
    private String storeLogo;

    /**
     * 店铺Banner
     */
    private String storeBanner;

    /**
     * 店铺头像
     */
    private String storeAvatar;

    /**
     * 店铺SEO关键字
     */
    private String storeKeywords;

    /**
     * 店铺SEO描述
     */
    private String storeDescription;

    /**
     * 店铺QQ
     */
    private String storeQq;

    /**
     * 店铺WW
     */
    private String storeWw;

    /**
     * 商家电话
     */
    private String storePhone;

    /**
     * 主营商品
     */
    private String storeMainbusiness;

    /**
     * 是否推荐：1是 2否
     */
    private Integer storeRecommend;

    /**
     * 店铺当前主题
     */
    private String storeTheme;

    /**
     * 店铺信用
     */
    private Integer storeCredit;

    /**
     * 描述相符度分数
     */
    private Float storeDesccredit;

    /**
     * 服务态度分数
     */
    private Float storeServicecredit;

    /**
     * 发货速度分数
     */
    private Float storeDeliverycredit;

    /**
     * 店铺收藏数量
     */
    private Integer storeCollect;

    /**
     * 店铺销量
     */
    private Integer storeSales;

    /**
     * 售前客服
     */
    private String storePresales;

    /**
     * 售后客服
     */
    private String storeAftersales;

    /**
     * 营业时间
     */
    private String storeBusinessTime;

    /**
     * 店铺开通时间
     */
    private Integer storeStartTime;

    /**
     * 店铺关闭时间
     */
    private Integer storeEndTime;

    /**
     * 店铺状态：1正常 2审核中 2关闭
     */
    private Integer storeStatus;

    /**
     * 店铺排序
     */
    private Integer sort;

    /**
     * 商铺名称
     */
    private String merchantName;

    /**
     * 商家地址
     */
    private String merchantAddress;

    /**
     * 商铺电话
     */
    private String merchantTel;

    /**
     * 公交线路
     */
    private String merchantBus;

    /**
     * 是否平台店铺：1是 2否
     */
    private Integer isPlatform;

    /**
     * 申请状态：0信息已保存 10已提交申请 20资料审核成功 30资料审核失败 40缴费完成 50缴费审核失败 60审核通过开店
     */
    private Integer status;

    /**
     * 审核备注
     */
    private String note;

}