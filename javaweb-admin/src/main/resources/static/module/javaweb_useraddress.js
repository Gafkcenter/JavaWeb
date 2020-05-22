/**
 * 会员地址
 * @auth 鲲鹏
 * @date 2020-05-04
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
            , {field: 'realname', width: 100, title: '收货人姓名', align: 'center'}
            , {field: 'mobile', width: 130, title: '收货人电话', align: 'center'}
            , {field: 'cityArea', width: 200, title: '收货人所在城市', align: 'center'}
            , {field: 'address', width: 250, title: '收货人详细地址', align: 'center'}
            , {field: 'zipCode', width: 100, title: '邮编', align: 'center'}
            , {field: 'isDefault', width: 100, title: '默认地址', align: 'center', templet(d) {
                var cls = "";
                if (d.isDefault == 1) {
                    // 是
                    cls = "layui-btn-normal";
                } else if (d.isDefault == 2) {
                    // 否
                    cls = "layui-btn-danger";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.isDefaultName+'</span>';
            }}
            , {field: 'createTime', width: 180, title: '添加时间', align: 'center'}
            , {field: 'updateTime', width: 180, title: '更新时间', align: 'center'}
            , {fixed: 'right', width: 100, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

    }
});
