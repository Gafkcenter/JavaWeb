/**
 * 商品
 * @auth 鲲鹏
 * @date 2020-06-09
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
            , {field: 'productTitle', width: 250, title: '商品标题', align: 'center'}
            , {field: 'productSn', width: 150, title: '商品编号', align: 'center'}
            , {field: 'cover', width: 100, title: '商品封面', align: 'center', templet: function (d) {
                var coverStr = "";
                if (d.coverUrl) {
                    coverStr = '<a href="' + d.coverUrl + '" target="_blank"><img src="' + d.coverUrl + '" height="26" /></a>';
                }
                return coverStr;
              }
            }
            , {field: 'productCategoryName', width: 100, title: '商品分类', align: 'center'}
            , {field: 'brandName', width: 100, title: '品牌名称', align: 'center'}
            , {field: 'status', width: 100, title: '状态', align: 'center', templet: '#statusTpl'}
            , {field: 'verifyStatus', width: 100, title: '审核状态', align: 'center', templet: '#verifyStatusTpl'}
            , {field: 'price', width: 120, title: '商品售价/元', align: 'center'}
            , {field: 'costPrice', width: 100, title: '成本价/元', align: 'center'}
            , {field: 'originalPrice', width: 100, title: '原价/元', align: 'center'}
            , {field: 'stock', width: 100, title: '库存', align: 'center'}
            , {field: 'lowStock', width: 100, title: '库存预警值', align: 'center'}
            , {field: 'sales', width: 100, title: '销量', align: 'center'}
            , {field: 'isHot', width: 100, title: '是否热卖', align: 'center', templet(d) {
                    var cls = "";
                    if (d.isHot == 1) {
                        // 是
                        cls = "layui-btn-normal";
                    } else if (d.isHot == 2) {
                        // 否
                        cls = "layui-btn-danger";
                    }
                    return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.isHotName+'</span>';
                }}
            , {field: 'isRecommand', width: 100, title: '是否推荐', align: 'center', templet(d) {
                    var cls = "";
                    if (d.isRecommand == 1) {
                        // 是
                        cls = "layui-btn-normal";
                    } else if (d.isRecommand == 2) {
                        // 否
                        cls = "layui-btn-danger";
                    }
                    return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.isRecommandName+'</span>';
                }}
            , {field: 'isNew', width: 100, title: '是否新品', align: 'center', templet(d) {
                    var cls = "";
                    if (d.isNew == 1) {
                        // 是
                        cls = "layui-btn-normal";
                    } else if (d.isNew == 2) {
                        // 否
                        cls = "layui-btn-danger";
                    }
                    return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.isNewName+'</span>';
                }}
            , {field: 'sort', width: 100, title: '排序', align: 'center'}
            , {field: 'giftGrowth', width: 120, title: '赠送成长值', align: 'center'}
            , {field: 'giftIntegral', width: 100, title: '赠送积分', align: 'center'}
            , {field: 'unit', width: 100, title: '计量单位', align: 'center'}
            , {field: 'volume', width: 100, title: '体积(m³)', align: 'center'}
            , {field: 'weight', width: 100, title: '重量(KG)', align: 'center'}
            , {field: 'isPostage', width: 100, title: '是否包邮', align: 'center', templet(d) {
                var cls = "";
                if (d.isPostage == 1) {
                    // 是
                    cls = "layui-btn-normal";
                } else if (d.isPostage == 2) {
                    // 否
                    cls = "layui-btn-danger";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.isPostageName+'</span>';
            }}
            , {field: 'browse', width: 100, title: '浏览量', align: 'center'}
            , {field: 'createTime', width: 180, title: '添加时间', align: 'center'}
            , {field: 'updateTime', width: 180, title: '更新时间', align: 'center'}
            , {fixed: 'right', width: 150, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

        //【设置弹框】
        func.setWin("商品");

        //【设置状态】
        func.formSwitch('status', null, function (data, res) {
            console.log("开关回调成功");
        });
    }
});
