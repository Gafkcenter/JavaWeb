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
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/contextMenu.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/contextMenu.js":
/*!****************************!*\
  !*** ./src/contextMenu.js ***!
  \****************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("__webpack_require__(/*! ./js/contextMenu.js */ \"./src/js/contextMenu.js\");\n\n//# sourceURL=webpack:///./src/contextMenu.js?");

/***/ }),

/***/ "./src/js/contextMenu.js":
/*!*******************************!*\
  !*** ./src/js/contextMenu.js ***!
  \*******************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("/** 右键菜单模块 date:2019-02-08   License By http://easyweb.vip */\r\nlayui.define([\"jquery\"], function (exports) {\r\n    var $ = layui.jquery;\r\n\r\n    var contextMenu = {\r\n        // 绑定元素\r\n        bind: function (elem, items) {\r\n            $(elem).bind('contextmenu', function (e) {\r\n                contextMenu.show(items, e.clientX, e.clientY, e);\r\n                return false;\r\n            });\r\n        },\r\n        // 在指定坐标显示菜单\r\n        show: function (items, x, y, e) {\r\n            var xy = 'left: ' + x + 'px; top: ' + y + 'px;';\r\n            var htmlStr = '<div class=\"ctxMenu\" style=\"' + xy + '\">';\r\n            htmlStr += contextMenu.getHtml(items, '');\r\n            htmlStr += '   </div>';\r\n            contextMenu.remove();\r\n            $('body').append(htmlStr);\r\n            // 调整溢出位置\r\n            var $ctxMenu = $('.ctxMenu');\r\n            if (x + $ctxMenu.outerWidth() > contextMenu.getPageWidth()) {\r\n                x -= $ctxMenu.outerWidth();\r\n            }\r\n            if (y + $ctxMenu.outerHeight() > contextMenu.getPageHeight()) {\r\n                y = y - $ctxMenu.outerHeight();\r\n                if (y < 0) {\r\n                    y = 0;\r\n                }\r\n            }\r\n            $ctxMenu.css({'top': y, 'left': x});\r\n            // 添加item点击事件\r\n            contextMenu.setEvents(items, e);\r\n            // 显示子菜单事件\r\n            $('.ctxMenu-item').on('mouseenter', function (e) {\r\n                e.stopPropagation();\r\n                $(this).parent().find('.ctxMenu-sub').css('display', 'none');\r\n                if (!$(this).hasClass('haveMore')) return;\r\n                var $item = $(this).find('>a');\r\n                var $sub = $(this).find('>.ctxMenu-sub');\r\n                var top = $item.offset().top - $('body,html').scrollTop();\r\n                var left = $item.offset().left + $item.outerWidth() - $('body,html').scrollLeft();\r\n                if (left + $sub.outerWidth() > contextMenu.getPageWidth()) {\r\n                    left = $item.offset().left - $sub.outerWidth();\r\n                }\r\n                if (top + $sub.outerHeight() > contextMenu.getPageHeight()) {\r\n                    top = top - $sub.outerHeight() + $item.outerHeight();\r\n                    if (top < 0) {\r\n                        top = 0;\r\n                    }\r\n                }\r\n                $(this).find('>.ctxMenu-sub').css({\r\n                    'top': top,\r\n                    'left': left,\r\n                    'display': 'block'\r\n                });\r\n            })/*.on('mouseleave', function () {\r\n                $(this).find('>.ctxMenu-sub').css('display', 'none');\r\n            })*/;\r\n        },\r\n        // 移除所有\r\n        remove: function () {\r\n            var ifs = parent.window.frames;\r\n            for (var i = 0; i < ifs.length; i++) {\r\n                var tif = ifs[i];\r\n                try {\r\n                    tif.layui.jquery('body>.ctxMenu').remove();\r\n                } catch (e) {\r\n                }\r\n            }\r\n            try {\r\n                parent.layui.jquery('body>.ctxMenu').remove();\r\n            } catch (e) {\r\n            }\r\n        },\r\n        // 设置事件监听\r\n        setEvents: function (items, event) {\r\n            $('.ctxMenu').off('click').on('click', '[lay-id]', function (e) {\r\n                var itemId = $(this).attr('lay-id');\r\n                var item = getItemById(itemId, items);\r\n                item.click && item.click(e, event);\r\n            });\r\n\r\n            function getItemById(id, list) {\r\n                for (var i = 0; i < list.length; i++) {\r\n                    var one = list[i];\r\n                    if (id == one.itemId) {\r\n                        return one;\r\n                    } else if (one.subs && one.subs.length > 0) {\r\n                        var temp = getItemById(id, one.subs);\r\n                        if (temp) {\r\n                            return temp;\r\n                        }\r\n                    }\r\n                }\r\n            }\r\n        },\r\n        // 构建无限级\r\n        getHtml: function (items, pid) {\r\n            var htmlStr = '';\r\n            for (var i = 0; i < items.length; i++) {\r\n                var item = items[i];\r\n                item.itemId = 'ctxMenu-' + pid + i;\r\n                if (item.subs && item.subs.length > 0) {\r\n                    htmlStr += '<div class=\"ctxMenu-item haveMore\" lay-id=\"' + item.itemId + '\">';\r\n                    htmlStr += '<a>';\r\n                    if (item.icon) {\r\n                        htmlStr += '<i class=\"' + item.icon + ' ctx-icon\"></i>';\r\n                    }\r\n                    htmlStr += item.name;\r\n                    htmlStr += '<i class=\"layui-icon layui-icon-right icon-more\"></i>';\r\n                    htmlStr += '</a>';\r\n                    htmlStr += '<div class=\"ctxMenu-sub\" style=\"display: none;\">';\r\n                    htmlStr += contextMenu.getHtml(item.subs, pid + i);\r\n                    htmlStr += '</div>';\r\n                } else {\r\n                    htmlStr += '<div class=\"ctxMenu-item\" lay-id=\"' + item.itemId + '\">';\r\n                    htmlStr += '<a>';\r\n                    if (item.icon) {\r\n                        htmlStr += '<i class=\"' + item.icon + ' ctx-icon\"></i>';\r\n                    }\r\n                    htmlStr += item.name;\r\n                    htmlStr += '</a>';\r\n                }\r\n                htmlStr += '</div>';\r\n                if (item.hr == true) {\r\n                    htmlStr += '<hr/>';\r\n                }\r\n            }\r\n            return htmlStr;\r\n        },\r\n        // 获取css代码\r\n        getCommonCss: function () {\r\n            var cssStr = '.ctxMenu, .ctxMenu-sub {';\r\n            cssStr += '        max-width: 250px;';\r\n            cssStr += '        min-width: 110px;';\r\n            cssStr += '        background: white;';\r\n            cssStr += '        border-radius: 2px;';\r\n            cssStr += '        padding: 5px 0;';\r\n            cssStr += '        white-space: nowrap;';\r\n            cssStr += '        position: fixed;';\r\n            cssStr += '        z-index: 2147483647;';\r\n            cssStr += '        box-shadow: 0 2px 4px rgba(0, 0, 0, .12);';\r\n            cssStr += '        border: 1px solid #d2d2d2;';\r\n            cssStr += '        overflow: visible;';\r\n            cssStr += '   }';\r\n\r\n            cssStr += '   .ctxMenu-item {';\r\n            cssStr += '        position: relative;';\r\n            cssStr += '   }';\r\n\r\n            cssStr += '   .ctxMenu-item > a {';\r\n            cssStr += '        font-size: 14px;';\r\n            cssStr += '        color: #666;';\r\n            cssStr += '        padding: 0 26px 0 35px;';\r\n            cssStr += '        cursor: pointer;';\r\n            cssStr += '        display: block;';\r\n            cssStr += '        line-height: 36px;';\r\n            cssStr += '        text-decoration: none;';\r\n            cssStr += '        position: relative;';\r\n            cssStr += '   }';\r\n\r\n            cssStr += '   .ctxMenu-item > a:hover {';\r\n            cssStr += '        background: #f2f2f2;';\r\n            cssStr += '        color: #666;';\r\n            cssStr += '   }';\r\n\r\n            cssStr += '   .ctxMenu-item > a > .icon-more {';\r\n            cssStr += '        position: absolute;';\r\n            cssStr += '        right: 5px;';\r\n            cssStr += '        top: 0;';\r\n            cssStr += '        font-size: 12px;';\r\n            cssStr += '        color: #666;';\r\n            cssStr += '   }';\r\n\r\n            cssStr += '   .ctxMenu-item > a > .ctx-icon {';\r\n            cssStr += '        position: absolute;';\r\n            cssStr += '        left: 12px;';\r\n            cssStr += '        top: 0;';\r\n            cssStr += '        font-size: 15px;';\r\n            cssStr += '        color: #666;';\r\n            cssStr += '   }';\r\n\r\n            cssStr += '   .ctxMenu hr {';\r\n            cssStr += '        background-color: #e6e6e6;';\r\n            cssStr += '        clear: both;';\r\n            cssStr += '        margin: 5px 0;';\r\n            cssStr += '        border: 0;';\r\n            cssStr += '        height: 1px;';\r\n            cssStr += '   }';\r\n\r\n            cssStr += '   .ctx-ic-lg {';\r\n            cssStr += '        font-size: 18px !important;';\r\n            cssStr += '        left: 11px !important;';\r\n            cssStr += '    }';\r\n            return cssStr;\r\n        },\r\n        // 获取浏览器高度\r\n        getPageHeight: function () {\r\n            return document.documentElement.clientHeight || document.body.clientHeight;\r\n        },\r\n        // 获取浏览器宽度\r\n        getPageWidth: function () {\r\n            return document.documentElement.clientWidth || document.body.clientWidth;\r\n        },\r\n    };\r\n\r\n    // 点击任意位置关闭菜单\r\n    $(document).off('click.ctxMenu').on('click.ctxMenu', function () {\r\n        contextMenu.remove();\r\n    });\r\n\r\n    // 点击有子菜单的节点不关闭菜单\r\n    $(document).off('click.ctxMenuMore').on('click.ctxMenuMore', '.ctxMenu-item', function (e) {\r\n        if ($(this).hasClass('haveMore')) {\r\n            if (e !== void 0) {\r\n                e.preventDefault();\r\n                e.stopPropagation();\r\n            }\r\n        } else {\r\n            contextMenu.remove();\r\n        }\r\n    });\r\n\r\n    $('head').append('<style id=\"ew-css-ctx\">' + contextMenu.getCommonCss() + '</style>');\r\n    exports(\"contextMenu\", contextMenu);\r\n});\n\n//# sourceURL=webpack:///./src/js/contextMenu.js?");

/***/ })

/******/ });