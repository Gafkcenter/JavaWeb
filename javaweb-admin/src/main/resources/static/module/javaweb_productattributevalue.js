/**
 * 商品属性值
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
            , {field: 'productId', width: 100, title: '商品ID', align: 'center'}
            , {field: 'productAttributeId', width: 100, title: '商品属性ID', align: 'center'}
            , {field: 'productAttributeName', width: 100, title: '商品属性名称', align: 'center'}
            , {field: 'productAttributeType', width: 100, title: '商品属性类型', align: 'center'}
            , {field: 'value', width: 100, title: '手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开', align: 'center'}
            , {field: 'storeId', width: 100, title: '所属店铺ID', align: 'center'}
            , {field: 'sort', width: 100, title: '排序号', align: 'center'}
            , {field: 'createUserName', width: 100, title: '添加人', align: 'center'}
            , {field: 'createTime', width: 180, title: '添加时间', align: 'center'}
            , {field: 'updateUserName', width: 100, title: '更新人', align: 'center'}
            , {field: 'updateTime', width: 180, title: '更新时间', align: 'center'}
            , {fixed: 'right', width: 150, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

        //【设置弹框】
        func.setWin("商品属性值");

    }
});
