/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/index.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/index.js":
/*!**********************!*\
  !*** ./src/index.js ***!
  \**********************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("__webpack_require__(/*! ./js/index.js */ \"./src/js/index.js\");\n\n//# sourceURL=webpack:///./src/index.js?");

/***/ }),

/***/ "./src/js/index.js":
/*!*************************!*\
  !*** ./src/js/index.js ***!
  \*************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("/** EasyWeb iframe v3.1.8 date:2020-05-04 License By http://easyweb.vip */\r\n\r\nlayui.define(['layer', 'element', 'admin'], function (exports) {\r\n    var $ = layui.jquery;\r\n    var layer = layui.layer;\r\n    var element = layui.element;\r\n    var admin = layui.admin;\r\n    var setter = admin.setter;\r\n    var headerDOM = '.layui-layout-admin>.layui-header';\r\n    var sideDOM = '.layui-layout-admin>.layui-side>.layui-side-scroll';\r\n    var bodyDOM = '.layui-layout-admin>.layui-body';\r\n    var tabDOM = bodyDOM + '>.layui-tab';\r\n    var titleDOM = bodyDOM + '>.layui-body-header';\r\n    var tabFilter = 'admin-pagetabs';\r\n    var navFilter = 'admin-side-nav';\r\n    var tabEndCall = {};  // Tab关闭的事件回调\r\n    var mIsAddTab = false;  // 是否是添加Tab，添加Tab的时候切换不自动刷新\r\n    var index = {homeUrl: undefined, mTabPosition: undefined, mTabList: []};\r\n\r\n    /** 渲染主体部分 */\r\n    index.loadView = function (param) {\r\n        if (!param.menuPath) return layer.msg('url不能为空', {icon: 2, anim: 6});\r\n        if (setter.pageTabs) {  // 多标签模式\r\n            var flag;  // 选项卡是否已添加\r\n            $(tabDOM + '>.layui-tab-title>li').each(function () {\r\n                if ($(this).attr('lay-id') === param.menuPath) flag = true;\r\n            });\r\n            if (!flag) {  // 添加选项卡\r\n                if (index.mTabList.length + 1 >= setter.maxTabNum) {\r\n                    layer.msg('最多打开' + setter.maxTabNum + '个选项卡', {icon: 2, anim: 6});\r\n                    return admin.activeNav(index.mTabPosition);\r\n                }\r\n                mIsAddTab = true;\r\n                element.tabAdd(tabFilter, {\r\n                    id: param.menuPath, title: '<span class=\"title\">' + (param.menuName || '') + '</span>',\r\n                    content: '<iframe class=\"admin-iframe\" lay-id=\"' + param.menuPath + '\" src=\"' + param.menuPath +\r\n                        '\" onload=\"layui.index.hideLoading(this);\" frameborder=\"0\"></iframe>'\r\n                });\r\n                admin.showLoading({elem: $('iframe[lay-id=\"' + param.menuPath + '\"]').parent(), size: ''});\r\n                if (param.menuPath !== index.homeUrl) index.mTabList.push(param);  // 记录tab\r\n                if (setter.cacheTab) admin.putTempData('indexTabs', index.mTabList);  // 缓存tab\r\n            }\r\n            if (!param.noChange) element.tabChange(tabFilter, param.menuPath);  // 切换到此tab\r\n        } else {  // 单标签模式\r\n            admin.activeNav(param.menuPath);\r\n            var $contentDom = $(bodyDOM + '>div>.admin-iframe');\r\n            if ($contentDom.length === 0) {\r\n                $(bodyDOM).html([\r\n                    '<div class=\"layui-body-header\">',\r\n                    '   <span class=\"layui-body-header-title\"></span>',\r\n                    '   <span class=\"layui-breadcrumb pull-right\" lay-filter=\"admin-body-breadcrumb\" style=\"visibility: visible;\"></span>',\r\n                    '</div>',\r\n                    '<div style=\"-webkit-overflow-scrolling: touch;\">',\r\n                    '   <iframe class=\"admin-iframe\" lay-id=\"', param.menuPath, '\" src=\"', param.menuPath, '\"',\r\n                    '      onload=\"layui.index.hideLoading(this);\" frameborder=\"0\"></iframe>',\r\n                    '</div>'].join(''));\r\n                admin.showLoading({elem: $('iframe[lay-id=\"' + param.menuPath + '\"]').parent(), size: ''});\r\n            } else {\r\n                admin.showLoading({elem: $contentDom.parent(), size: ''});\r\n                $contentDom.attr('lay-id', param.menuPath).attr('src', param.menuPath);\r\n            }\r\n            $('[lay-filter=\"admin-body-breadcrumb\"]').html(index.getBreadcrumbHtml(param.menuPath));\r\n            index.mTabList.splice(0, index.mTabList.length);\r\n            if (param.menuPath === index.homeUrl) {\r\n                index.mTabPosition = undefined;\r\n                index.setTabTitle($(param.menuName).text() || $(sideDOM + ' [lay-href=\"' + index.homeUrl + '\"]').text() || '主页');\r\n            } else {\r\n                index.mTabPosition = param.menuPath;\r\n                index.mTabList.push(param);\r\n                index.setTabTitle(param.menuName);\r\n            }\r\n            if (!setter.cacheTab) return;\r\n            admin.putTempData('indexTabs', index.mTabList);\r\n            admin.putTempData('tabPosition', index.mTabPosition);\r\n        }\r\n        if (admin.getPageWidth() <= 768) admin.flexible(true); // 移动端自动收起侧导航\r\n    };\r\n\r\n    /** 加载主页 */\r\n    index.loadHome = function (param) {\r\n        var cacheTabs = admin.getTempData('indexTabs');  // 获取缓存tab\r\n        var cachePosition = admin.getTempData('tabPosition');\r\n        var recover = (param.loadSetting === undefined || param.loadSetting) && (setter.cacheTab && cacheTabs && cacheTabs.length > 0);\r\n        index.homeUrl = param.menuPath;\r\n        param.noChange = cachePosition ? recover : false;\r\n        if (setter.pageTabs || !recover) index.loadView(param);\r\n        if (recover) {  // 恢复缓存tab\r\n            for (var i = 0; i < cacheTabs.length; i++) {\r\n                cacheTabs[i].noChange = cacheTabs[i].menuPath !== cachePosition;\r\n                if (!cacheTabs[i].noChange || (setter.pageTabs && !param.onlyLast)) index.loadView(cacheTabs[i]);\r\n            }\r\n        }\r\n        admin.removeLoading(undefined, false);\r\n    };\r\n\r\n    /** 打开tab */\r\n    index.openTab = function (param) {\r\n        if (window !== top && !admin.isTop() && top.layui && top.layui.index) return top.layui.index.openTab(param);\r\n        if (param.end) tabEndCall[param.url] = param.end;\r\n        index.loadView({menuPath: param.url, menuName: param.title});\r\n    };\r\n\r\n    /** 关闭tab */\r\n    index.closeTab = function (url) {\r\n        if (window !== top && !admin.isTop() && top.layui && top.layui.index) return top.layui.index.closeTab(url);\r\n        element.tabDelete(tabFilter, url);\r\n    };\r\n\r\n    /** 设置是否记忆Tab */\r\n    index.setTabCache = function (isCache) {\r\n        if (window !== top && !admin.isTop() && top.layui && top.layui.index) return top.layui.index.setTabCache(isCache);\r\n        admin.putSetting('cacheTab', isCache);\r\n        if (!isCache) return index.clearTabCache();\r\n        admin.putTempData('indexTabs', index.mTabList);\r\n        admin.putTempData('tabPosition', index.mTabPosition);\r\n    };\r\n\r\n    /** 清除tab记忆 */\r\n    index.clearTabCache = function () {\r\n        admin.putTempData('indexTabs', null);\r\n        admin.putTempData('tabPosition', null);\r\n    };\r\n\r\n    /** 设置tab标题 */\r\n    index.setTabTitle = function (title, tabId) {\r\n        if (window !== top && !admin.isTop() && top.layui && top.layui.index) return top.layui.index.setTabTitle(title, tabId);\r\n        if (setter.pageTabs) {\r\n            if (!tabId) tabId = $(tabDOM + '>.layui-tab-title>li.layui-this').attr('lay-id');\r\n            if (tabId) $(tabDOM + '>.layui-tab-title>li[lay-id=\"' + tabId + '\"] .title').html(title || '');\r\n        } else if (title) {\r\n            $(titleDOM + '>.layui-body-header-title').html(title);\r\n            $(titleDOM).addClass('show');\r\n            $(headerDOM).css('box-shadow', '0 1px 0 0 rgba(0, 0, 0, .03)');\r\n        } else {\r\n            $(titleDOM).removeClass('show');\r\n            $(headerDOM).css('box-shadow', '');\r\n        }\r\n    };\r\n\r\n    /** 自定义tab标题 */\r\n    index.setTabTitleHtml = function (html) {\r\n        if (window !== top && !admin.isTop() && top.layui && top.layui.index) return top.layui.index.setTabTitleHtml(html);\r\n        if (setter.pageTabs) return;\r\n        if (!html) return $(titleDOM).removeClass('show');\r\n        $(titleDOM).html(html);\r\n        $(titleDOM).addClass('show');\r\n    };\r\n\r\n    /** 获取面包屑 */\r\n    index.getBreadcrumb = function (tabId) {\r\n        if (!tabId) tabId = $(bodyDOM + '>div>.admin-iframe').attr('lay-id');\r\n        var breadcrumb = [];\r\n        var $href = $(sideDOM).find('[lay-href=\"' + tabId + '\"]');\r\n        if ($href.length > 0) breadcrumb.push($href.text().replace(/(^\\s*)|(\\s*$)/g, ''));\r\n        while (true) {\r\n            $href = $href.parent('dd').parent('dl').prev('a');\r\n            if ($href.length === 0) break;\r\n            breadcrumb.unshift($href.text().replace(/(^\\s*)|(\\s*$)/g, ''));\r\n        }\r\n        return breadcrumb;\r\n    };\r\n\r\n    /** 获取面包屑结构 */\r\n    index.getBreadcrumbHtml = function (tabId) {\r\n        var breadcrumb = index.getBreadcrumb(tabId);\r\n        var htmlStr = tabId === index.homeUrl ? '' : ('<a ew-href=\"' + index.homeUrl + '\">首页</a>');\r\n        for (var i = 0; i < breadcrumb.length - 1; i++) {\r\n            if (htmlStr) htmlStr += '<span lay-separator=\"\">/</span>';\r\n            htmlStr += ('<a><cite>' + breadcrumb[i] + '</cite></a>');\r\n        }\r\n        return htmlStr;\r\n    };\r\n\r\n    /** 关闭loading */\r\n    index.hideLoading = function (url) {\r\n        if (typeof url !== 'string') url = $(url).attr('lay-id');\r\n        admin.removeLoading($('iframe[lay-id=\"' + url + '\"],' + bodyDOM + ' iframe[lay-id]').parent(), false);\r\n    };\r\n\r\n    /** 移动设备遮罩层 */\r\n    var siteShadeDom = '.layui-layout-admin .site-mobile-shade';\r\n    if ($(siteShadeDom).length === 0) $('.layui-layout-admin').append('<div class=\"site-mobile-shade\"></div>');\r\n    $(siteShadeDom).click(function () {\r\n        admin.flexible(true);\r\n    });\r\n\r\n    /** 补充tab的dom */\r\n    if (setter.pageTabs && $(tabDOM).length === 0) {\r\n        $(bodyDOM).html([\r\n            '<div class=\"layui-tab\" lay-allowClose=\"true\" lay-filter=\"', tabFilter, '\" lay-autoRefresh=\"', setter.tabAutoRefresh == 'true', '\">',\r\n            '   <ul class=\"layui-tab-title\"></ul><div class=\"layui-tab-content\"></div>',\r\n            '</div>',\r\n            '<div class=\"layui-icon admin-tabs-control layui-icon-prev\" ew-event=\"leftPage\"></div>',\r\n            '<div class=\"layui-icon admin-tabs-control layui-icon-next\" ew-event=\"rightPage\"></div>',\r\n            '<div class=\"layui-icon admin-tabs-control layui-icon-down\">',\r\n            '   <ul class=\"layui-nav\" lay-filter=\"admin-pagetabs-nav\">',\r\n            '      <li class=\"layui-nav-item\" lay-unselect>',\r\n            '         <dl class=\"layui-nav-child layui-anim-fadein\">',\r\n            '            <dd ew-event=\"closeThisTabs\" lay-unselect><a>关闭当前标签页</a></dd>',\r\n            '            <dd ew-event=\"closeOtherTabs\" lay-unselect><a>关闭其它标签页</a></dd>',\r\n            '            <dd ew-event=\"closeAllTabs\" lay-unselect><a>关闭全部标签页</a></dd>',\r\n            '         </dl>',\r\n            '      </li>',\r\n            '   </ul>',\r\n            '</div>'\r\n        ].join(''));\r\n        element.render('nav', 'admin-pagetabs-nav');\r\n    }\r\n\r\n    /** 侧导航点击监听 */\r\n    element.on('nav(' + navFilter + ')', function (elem) {\r\n        var $that = $(elem);\r\n        var href = $that.attr('lay-href');\r\n        if (!href || href === '#') return;\r\n        if (href.indexOf('javascript:') === 0) return new Function(href.substring(11))();\r\n        var name = $that.attr('ew-title') || $that.text().replace(/(^\\s*)|(\\s*$)/g, '');\r\n        var end = $that.attr('ew-end');\r\n        try {\r\n            if (end) end = new Function(end);\r\n            else end = undefined;\r\n        } catch (e) {\r\n            console.error(e);\r\n        }\r\n        index.openTab({url: href, title: name, end: end});\r\n        layui.event.call(this, 'admin', 'side({*})', {href: href});\r\n    });\r\n\r\n    /** tab切换监听 */\r\n    element.on('tab(' + tabFilter + ')', function () {\r\n        var layId = $(this).attr('lay-id');\r\n        index.mTabPosition = (layId !== index.homeUrl ? layId : undefined);  // 记录当前Tab位置\r\n        if (setter.cacheTab) admin.putTempData('tabPosition', index.mTabPosition);\r\n        admin.activeNav(layId);\r\n        admin.rollPage('auto');\r\n        if ($(tabDOM).attr('lay-autoRefresh') == 'true' && !mIsAddTab) admin.refresh(layId, true);  // 切换tab刷新\r\n        mIsAddTab = false;\r\n        layui.event.call(this, 'admin', 'tab({*})', {layId: layId});\r\n    });\r\n\r\n    /** tab删除监听 */\r\n    element.on('tabDelete(' + tabFilter + ')', function (data) {\r\n        var mTab = index.mTabList[data.index - 1];\r\n        if (mTab) {\r\n            index.mTabList.splice(data.index - 1, 1);\r\n            if (setter.cacheTab) admin.putTempData('indexTabs', index.mTabList);\r\n            tabEndCall[mTab.menuPath] && tabEndCall[mTab.menuPath].call();\r\n            layui.event.call(this, 'admin', 'tabDelete({*})', {layId: mTab.menuPath});\r\n        }\r\n        if ($(tabDOM + '>.layui-tab-title>li.layui-this').length === 0)\r\n            $(tabDOM + '>.layui-tab-title>li:last').trigger('click');  // 解决删除后可能无选中bug\r\n    });\r\n\r\n    /** 多系统切换事件 */\r\n    $(document).off('click.navMore').on('click.navMore', '[nav-bind]', function () {\r\n        var navId = $(this).attr('nav-bind');\r\n        $('ul[lay-filter=\"' + navFilter + '\"]').addClass('layui-hide');\r\n        $('ul[nav-id=\"' + navId + '\"]').removeClass('layui-hide');\r\n        $(headerDOM + '>.layui-nav .layui-nav-item').removeClass('layui-this');\r\n        $(this).parent('.layui-nav-item').addClass('layui-this');\r\n        if (admin.getPageWidth() <= 768) admin.flexible(false);  // 展开侧边栏\r\n        layui.event.call(this, 'admin', 'nav({*})', {navId: navId});\r\n    });\r\n\r\n    /** 开启Tab右键菜单 */\r\n    if (setter.openTabCtxMenu && setter.pageTabs) {\r\n        layui.use('contextMenu', function () {\r\n            if (!layui.contextMenu) return;\r\n            $(tabDOM + '>.layui-tab-title').off('contextmenu.tab').on('contextmenu.tab', 'li', function (e) {\r\n                var layId = $(this).attr('lay-id');\r\n                layui.contextMenu.show([{\r\n                    icon: 'layui-icon layui-icon-refresh',\r\n                    name: '刷新当前',\r\n                    click: function () {\r\n                        element.tabChange(tabFilter, layId);\r\n                        if ('true' != $(tabDOM).attr('lay-autoRefresh')) admin.refresh(layId);\r\n                    }\r\n                }, {\r\n                    icon: 'layui-icon layui-icon-close-fill ctx-ic-lg',\r\n                    name: '关闭当前',\r\n                    click: function () {\r\n                        admin.closeThisTabs(layId);\r\n                    }\r\n                }, {\r\n                    icon: 'layui-icon layui-icon-unlink',\r\n                    name: '关闭其他',\r\n                    click: function () {\r\n                        admin.closeOtherTabs(layId);\r\n                    }\r\n                }, {\r\n                    icon: 'layui-icon layui-icon-close ctx-ic-lg',\r\n                    name: '关闭全部',\r\n                    click: function () {\r\n                        admin.closeAllTabs();\r\n                    }\r\n                }], e.clientX, e.clientY);\r\n                return false;\r\n            });\r\n        });\r\n    }\r\n\r\n    exports('index', index);\r\n});\r\n\n\n//# sourceURL=webpack:///./src/js/index.js?");

/***/ })

/******/ });