/**
 * 店铺表
 * @auth 鲲鹏
 * @date 2020-05-10
 */
layui.use(['func'], function () {

    //声明变量
    var func = layui.func
        , $ = layui.$;

    if (A == 'index') {
        //【TABLE列数组】
        var cols = [
              {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 80, title: 'ID', align: 'center', sort: true, fixed: 'left'}
            , {field: 'storeCategoryId', width: 100, title: '店铺分类ID', align: 'center'}
            , {field: 'storeCategoryName', width: 100, title: '店铺分类名称', align: 'center'}
            , {field: 'storeGradeId', width: 100, title: '店铺等级ID', align: 'center'}
            , {field: 'storeGradeName', width: 100, title: '店铺等级名称', align: 'center'}
            , {field: 'storeType', width: 100, title: '店铺类型', align: 'center', templet(d) {
                var cls = "";
                if (d.storeType == 1) {
                    // 公司
                    cls = "layui-btn-normal";
                } else if (d.storeType == 2) {
                    // 个人
                    cls = "layui-btn-danger";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.storeTypeName+'</span>';
            }}
            , {field: 'storeName', width: 100, title: '店铺名称', align: 'center'}
            , {field: 'storeIntro', width: 100, title: '店铺简介', align: 'center'}
            , {field: 'storeCompanyName', width: 100, title: '店铺公司名称', align: 'center'}
            , {field: 'storeCompanyArea', width: 100, title: '公司所在地区(如 江苏 南京)', align: 'center'}
            , {field: 'storeAddress', width: 100, title: '店铺地址', align: 'center'}
            , {field: 'storeZipcode', width: 100, title: '邮政编码', align: 'center'}
            , {field: 'storeLogo', width: 100, title: '店铺LOGO', align: 'center', templet: function (d) {
                var storeLogoStr = "";
                if (d.storeLogoUrl) {
                    storeLogoStr = '<a href="' + d.storeLogoUrl + '" target="_blank"><img src="' + d.storeLogoUrl + '" height="26" /></a>';
                }
                return storeLogoStr;
              }
            }
            , {field: 'storeBanner', width: 100, title: '店铺Banner', align: 'center'}
            , {field: 'storeAvatar', width: 100, title: '店铺头像', align: 'center', templet: function (d) {
                var storeAvatarStr = "";
                if (d.storeAvatarUrl) {
                    storeAvatarStr = '<a href="' + d.storeAvatarUrl + '" target="_blank"><img src="' + d.storeAvatarUrl + '" height="26" /></a>';
                }
                return storeAvatarStr;
              }
            }
            , {field: 'storeKeywords', width: 100, title: '店铺SEO关键字', align: 'center'}
            , {field: 'storeDescription', width: 100, title: '店铺SEO描述', align: 'center'}
            , {field: 'storeQq', width: 100, title: '店铺QQ', align: 'center'}
            , {field: 'storeWw', width: 100, title: '店铺WW', align: 'center'}
            , {field: 'storePhone', width: 100, title: '商家电话', align: 'center'}
            , {field: 'storeMainbusiness', width: 100, title: '主营商品', align: 'center'}
            , {field: 'storeRecommend', width: 100, title: '是否推荐', align: 'center', templet(d) {
                var cls = "";
                if (d.storeRecommend == 1) {
                    // 是
                    cls = "layui-btn-normal";
                } else if (d.storeRecommend == 2) {
                    // 否
                    cls = "layui-btn-danger";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.storeRecommendName+'</span>';
            }}
            , {field: 'storeTheme', width: 100, title: '店铺当前主题', align: 'center'}
            , {field: 'storeCredit', width: 100, title: '店铺信用', align: 'center'}
            , {field: 'storeDesccredit', width: 100, title: '描述相符度分数', align: 'center'}
            , {field: 'storeServicecredit', width: 100, title: '服务态度分数', align: 'center'}
            , {field: 'storeDeliverycredit', width: 100, title: '发货速度分数', align: 'center'}
            , {field: 'storeCollect', width: 100, title: '店铺收藏数量', align: 'center'}
            , {field: 'storeSales', width: 100, title: '店铺销量', align: 'center'}
            , {field: 'storePresales', width: 100, title: '售前客服', align: 'center'}
            , {field: 'storeAftersales', width: 100, title: '售后客服', align: 'center'}
            , {field: 'storeBusinessTime', width: 100, title: '营业时间', align: 'center'}
            , {field: 'storeStartTime', width: 100, title: '店铺开通时间', align: 'center'}
            , {field: 'storeEndTime', width: 100, title: '店铺关闭时间', align: 'center'}
            , {field: 'storeStatus', width: 100, title: '店铺状态', align: 'center', templet(d) {
                var cls = "";
                if (d.storeStatus == 1) {
                    // 正常
                    cls = "layui-btn-normal";
                } else if (d.storeStatus == 2) {
                    // 关闭
                    cls = "layui-btn-danger";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.storeStatusName+'</span>';
            }}
            , {field: 'sort', width: 100, title: '店铺排序', align: 'center'}
            , {field: 'merchantName', width: 100, title: '商铺名称', align: 'center'}
            , {field: 'merchantAddress', width: 100, title: '商家地址', align: 'center'}
            , {field: 'merchantTel', width: 100, title: '商铺电话', align: 'center'}
            , {field: 'merchantBus', width: 100, title: '公交线路', align: 'center'}
            , {field: 'isPlatform', width: 100, title: '是否平台店铺', align: 'center', templet(d) {
                var cls = "";
                if (d.isPlatform == 1) {
                    // 是
                    cls = "layui-btn-normal";
                } else if (d.isPlatform == 2) {
                    // 否
                    cls = "layui-btn-danger";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.isPlatformName+'</span>';
            }}
            , {field: 'status', width: 100, title: '申请状态', align: 'center', templet(d) {
                var cls = "";

				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.statusName+'</span>';
            }}
            , {field: 'note', width: 100, title: '审核备注', align: 'center'}
            , {field: 'createUserName', width: 100, title: '添加人', align: 'center'}
            , {field: 'createTime', width: 180, title: '添加时间', align: 'center'}
            , {field: 'updateUserName', width: 100, title: '更新人', align: 'center'}
            , {field: 'updateTime', width: 180, title: '更新时间', align: 'center'}
            , {fixed: 'right', width: 150, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

        //【设置弹框】
        func.setWin("店铺表");

        //【设置状态】
        func.formSwitch('status', null, function (data, res) {
            console.log("开关回调成功");
        });
    }
});
