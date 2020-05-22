package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 店铺表查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-10
 */
@Data
public class StoreQuery extends BaseQuery {

    /**
     * 店铺类型：1公司 2个人
     */
    private Integer storeType;

    /**
     * 是否推荐：1是 2否
     */
    private Integer storeRecommend;

    /**
     * 店铺状态：1正常 2审核中 2关闭
     */
    private Integer storeStatus;

    /**
     * 是否平台店铺：1是 2否
     */
    private Integer isPlatform;

    /**
     * 申请状态：0信息已保存 10已提交申请 20资料审核成功 30资料审核失败 40缴费完成 50缴费审核失败 60审核通过开店
     */
    private Integer status;

}
