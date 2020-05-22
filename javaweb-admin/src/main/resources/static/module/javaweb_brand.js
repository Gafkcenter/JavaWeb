/**
 * 商品品牌
 * @auth 鲲鹏
 * @date 2020-05-05
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
            , {field: 'brandName', width: 100, title: '品牌名称', align: 'center'}
            , {field: 'brandLogo', width: 100, title: '品牌logo', align: 'center', templet: function (d) {
                var brandLogoStr = "";
                if (d.brandLogoUrl) {
                    brandLogoStr = '<a href="' + d.brandLogoUrl + '" target="_blank"><img src="' + d.brandLogoUrl + '" height="26" /></a>';
                }
                return brandLogoStr;
              }
            }
            , {field: 'brandIntro', width: 100, title: '品牌简介', align: 'center'}
            , {field: 'firstLetter', width: 100, title: '品牌首字母', align: 'center'}
            , {field: 'productCount', width: 100, title: '产品数量', align: 'center'}
            , {field: 'brandCompanyId', width: 100, title: '品牌商ID', align: 'center'}
            , {field: 'bigPic', width: 100, title: '专区大图', align: 'center', templet: function (d) {
                var bigPicStr = "";
                if (d.bigPicUrl) {
                    bigPicStr = '<a href="' + d.bigPicUrl + '" target="_blank"><img src="' + d.bigPicUrl + '" height="26" /></a>';
                }
                return bigPicStr;
              }
            }
            , {field: 'storeId', width: 100, title: '所属店铺', align: 'center'}
            , {field: 'status', width: 100, title: '状态', align: 'center', templet: '#statusTpl'}
            , {field: 'sort', width: 100, title: '排序号', align: 'center'}
            , {field: 'createUserName', width: 100, title: '添加人', align: 'center'}
            , {field: 'createTime', width: 180, title: '创建时间', align: 'center'}
            , {field: 'updateUserName', width: 100, title: '更新人', align: 'center'}
            , {field: 'updateTime', width: 180, title: '更新时间', align: 'center'}
            , {fixed: 'right', width: 150, title: '功能操作', align: 'center', toolbar: '#toolBar'}
        ];

        //【渲染TABLE】
        func.tableIns(cols, "tableList");

        //【设置弹框】
        func.setWin("商品品牌");

        //【设置状态】
        func.formSwitch('status', null, function (data, res) {
            console.log("开关回调成功");
        });
    }
});
