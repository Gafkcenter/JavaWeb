/**
 * 商品SKU
 * @auth 鲲鹏
 * @date 2020-06-11
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
            , {field: 'productId', width: 100, title: '商品ID', align: 'center'}
            , {field: 'skuCode', width: 100, title: 'SKU编码', align: 'center'}
            , {field: 'attributeValue', width: 100, title: '商品属性索引值 (attribute_value,attribute_value[,....])', align: 'center'}
            , {field: 'stock', width: 100, title: '属性对应的库存', align: 'center'}
            , {field: 'sales', width: 100, title: '销量', align: 'center'}
            , {field: 'lowStock', width: 100, title: '预警库存', align: 'center'}
            , {field: 'lockStock', width: 100, title: '锁定库存', align: 'center'}
            , {field: 'price', width: 100, title: '属性金额', align: 'center'}
            , {field: 'costPrice', width: 100, title: '成本价', align: 'center'}
            , {field: 'originalPrice', width: 100, title: '原价', align: 'center'}
            , {field: 'image', width: 100, title: '图片', align: 'center', templet: function (d) {
                var imageStr = "";
                if (d.imageUrl) {
                    imageStr = '<a href="' + d.imageUrl + '" target="_blank"><img src="' + d.imageUrl + '" height="26" /></a>';
                }
                return imageStr;
              }
            }
            , {field: 'weight', width: 100, title: '重量', align: 'center'}
            , {field: 'volume', width: 100, title: '体积', align: 'center'}
            , {field: 'type', width: 100, title: '活动类型', align: 'center', templet(d) {
                var cls = "";
                if (d.type == 1) {
                    // 商品
                    cls = "layui-btn-normal";
                } else if (d.type == 2) {
                    // 秒杀
                    cls = "layui-btn-danger";
                } else if (d.type == 3) {
                    // 砍价
                    cls = "layui-btn-warm";
                } else if (d.type == 4) {
                    // 拼团
                    cls = "layui-btn-primary";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.typeName+'</span>';
            }}
            , {field: 'quota', width: 100, title: '活动限购数量', align: 'center'}
            , {field: 'storeId', width: 100, title: '所属店铺ID', align: 'center'}
            , {field: 'createUserName', width: 100, title: '添加人', align: 'center'}
            , {field: 'createTime', width: 180, title: '添加时间', align: 'center'}
            , {field: 'updateUserName', width: 100, title: '更新人', align: 'center'}
            , {field: 'updateTime', width: 180, title: '更新时间', align: 'center'}
            , {fixed: 'right', width: 150, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

        //【设置弹框】
        func.setWin("商品SKU");

    }
});
