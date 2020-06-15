/**
 * 菜单
 * @auth 鲲鹏
 * @date 2020-05-07
 */
layui.use(['func'], function () {

    //声明变量
    var func = layui.func
        , $ = layui.$;

    if (A == 'index') {
        //【TABLE列数组】
        var cols = [
              {field: 'id', width: 80, title: 'ID', align: 'center', sort: true}
            , {field: 'name', width: 200, title: '菜单名称', align: 'left'}
            , {field: 'type', width: 80, title: '类型', align: 'center', templet(d) {
                var cls = "";
                if (d.type == 1) {
                    // 模块
                    cls = "layui-btn-normal";
                } else if (d.type == 2) {
                    // 导航
                    cls = "layui-btn-danger";
                } else if (d.type == 3) {
                    // 菜单
                    cls = "layui-btn-warm";
                } else if (d.type == 4) {
                    // 节点
                    cls = "layui-btn-primary";
                }
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.typeName+'</span>';
            }}
            , {field: 'icon', width: 80, title: '图标', align: 'center', templet: '<p><i class="layui-icon {{d.icon}}"></i></p>'}
            , {field: 'url', width: 150, title: 'URL地址', align: 'center'}
            , {field: 'permission', width: 180, title: '权限标识', align: 'center'}
            , {field: 'status', width: 100, title: '状态', align: 'center', templet: '#statusTpl'}
            , {field: 'isPublic', width: 100, title: '是否公共', align: 'center', templet(d) {
                var cls = "";
                if (d.isPublic == 1) {
                    // 是
                    cls = "layui-btn-normal";
                } else if (d.isPublic == 2) {
                    // 否
                    cls = "layui-btn-danger";
                }
				return '<span class="layui-btn ' + cls + ' layui-btn-xs">'+d.isPublicName+'</span>';
            }}
            , {field: 'sort', width: 90, title: '显示顺序', align: 'center'}
            , {fixed: 'right', width: 220, title: '功能操作', align: 'left', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.treetable(cols, "tableList");

        //【设置弹框】
        func.setWin("菜单", 700, 600);

        //【设置状态】
        func.formSwitch('status', null, function (data, res) {
            console.log("开关回调成功");
        });
    }
});
