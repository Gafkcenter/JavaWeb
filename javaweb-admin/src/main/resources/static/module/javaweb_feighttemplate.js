/**
 * 运费模版
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
            , {field: 'name', width: 200, title: '模板名称', align: 'center'}
            , {field: 'chargeType', width: 100, title: '计费类型', align: 'center', templet(d) {
                var cls = "";
                if (d.chargeType == 1) {
                    // 按重量
                    cls = "layui-btn-normal";
                } else if (d.chargeType == 2) {
                    // 按件数
                    cls = "layui-btn-danger";
                } 
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.chargeTypeName+'</span>';
            }}
            , {field: 'firstWeight', width: 100, title: '首重KG', align: 'center'}
            , {field: 'firstFee', width: 100, title: '首费/元', align: 'center'}
            , {field: 'continueWeight', width: 100, title: '后重量KG', align: 'center'}
            , {field: 'continueFee', width: 100, title: '后费用/元', align: 'center'}
            , {field: 'dest', width: 250, title: '目的地', align: 'center'}
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
        func.setWin("运费模版", 700, 400);

    }
});
