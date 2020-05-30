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
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/printer.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/js/printer.js":
/*!***************************!*\
  !*** ./src/js/printer.js ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("/** 打印模块 date:2020-05-04   License By http://easyweb.vip */\r\nlayui.define(['jquery'], function (exports) {\r\n    var $ = layui.jquery;\r\n    var hideClass = 'hide-print';  // 打印时隐藏\r\n    var printingClass = 'printing';  // 正在打印\r\n    var ieWebBrowser = '<object id=\"WebBrowser\" classid=\"clsid:8856F961-340A-11D0-A96B-00C04FD705A2\" width=\"0\" height=\"0\"></object>';\r\n    var printer = {\r\n        // 判断是否是ie\r\n        isIE: function () {\r\n            return (!!window.ActiveXObject || 'ActiveXObject' in window);\r\n        },\r\n        // 判断是否是Edge\r\n        isEdge: function () {\r\n            return navigator.userAgent.indexOf('Edge') !== -1;\r\n        },\r\n        // 判断是否是Firefox\r\n        isFirefox: function () {\r\n            return navigator.userAgent.indexOf('Firefox') !== -1;\r\n        }\r\n    };\r\n\r\n    /** 打印当前页面 */\r\n    printer.print = function (param) {\r\n        window.focus();  // 让当前窗口获取焦点\r\n        param || (param = {});\r\n        var hide = param.hide;  // 需要隐藏的元素\r\n        var horizontal = param.horizontal;  // 纸张是否是横向\r\n        var iePreview = param.iePreview;  // 兼容ie打印预览\r\n        var blank = param.blank;  // 是否打开新窗口\r\n        var close = param.close;  // 如果是打开新窗口，打印完是否关闭\r\n        // 设置参数默认值\r\n        (iePreview === undefined) && (iePreview = true);\r\n        (blank === undefined && window !== top && iePreview && printer.isIE()) && (blank = true);\r\n        (close === undefined && blank && !printer.isIE()) && (close = true);\r\n        // 打印方向控制\r\n        $('#page-print-set').remove();\r\n        if (horizontal !== undefined) {\r\n            var printSet = '<style type=\"text/css\" media=\"print\" id=\"page-print-set\">';\r\n            printSet += ('     @page { size: ' + (horizontal ? 'landscape' : 'portrait') + '; }');\r\n            printSet += '   </style>';\r\n            $('body').append(printSet);\r\n        }\r\n        // 隐藏打印时需要隐藏内容\r\n        printer.hideElem(hide);\r\n        // 打印\r\n        var pWindow;\r\n        if (blank) {\r\n            // 创建打印窗口\r\n            pWindow = window.open('', '_blank');\r\n            pWindow.focus();  // 让打印窗口获取焦点\r\n            // 写入内容到打印窗口\r\n            var pDocument = pWindow.document;\r\n            pDocument.open();\r\n            var blankHtml = '<!DOCTYPE html>' + document.getElementsByTagName('html')[0].innerHTML;\r\n            if (iePreview && printer.isIE()) {\r\n                blankHtml += ieWebBrowser;\r\n                blankHtml += ('<script>window.onload = function(){ WebBrowser.ExecWB(7, 1); ' + (close ? 'window.close();' : '') + ' }</script>');\r\n            } else {\r\n                blankHtml += ('<script>window.onload = function(){ window.print(); ' + (close ? 'window.close();' : '') + ' }</script>');\r\n            }\r\n            pDocument.write(blankHtml);\r\n            pDocument.close();\r\n        } else {\r\n            pWindow = window;\r\n            if (iePreview && printer.isIE()) {\r\n                ($('#WebBrowser').length === 0) && ($('body').append(ieWebBrowser));\r\n                WebBrowser.ExecWB(7, 1);\r\n            } else {\r\n                pWindow.print();\r\n            }\r\n        }\r\n        printer.showElem(hide);\r\n        return pWindow;\r\n    };\r\n\r\n    /** 打印html字符串 */\r\n    printer.printHtml = function (param) {\r\n        param || (param = {});\r\n        var html = param.html;  // 打印的html内容\r\n        var blank = param.blank;  // 是否打开新窗口\r\n        var close = param.close;  // 打印完是否关闭打印窗口\r\n        var print = param.print;  // 是否自动调用打印\r\n        var horizontal = param.horizontal;  // 纸张是否是横向\r\n        var iePreview = param.iePreview;  // 兼容ie打印预览\r\n        // 设置参数默认值\r\n        (print === undefined) && (print = true);\r\n        (iePreview === undefined) && (iePreview = true);\r\n        (blank === undefined && printer.isIE()) && (blank = true);\r\n        (close === undefined && blank && !printer.isIE()) && (close = true);\r\n        // 创建打印窗口\r\n        var pWindow, pDocument;\r\n        if (blank) {\r\n            pWindow = window.open('', '_blank');\r\n            pDocument = pWindow.document;\r\n        } else {\r\n            var printFrame = document.getElementById('printFrame');\r\n            if (!printFrame) {\r\n                $('body').append('<iframe id=\"printFrame\" style=\"display: none;\"></iframe>');\r\n                printFrame = document.getElementById('printFrame');\r\n            }\r\n            pWindow = printFrame.contentWindow;\r\n            pDocument = printFrame.contentDocument || printFrame.contentWindow.document;\r\n        }\r\n        pWindow.focus();  // 让打印窗口获取焦点\r\n        // 写入内容到打印窗口\r\n        if (html) {\r\n            // 加入公共css\r\n            html += ('<style>' + printer.getCommonCss(true) + '</style>');\r\n            // 打印方向控制\r\n            if (horizontal !== undefined) {\r\n                html += '<style type=\"text/css\" media=\"print\">';\r\n                html += ('  @page { size: ' + (horizontal ? 'landscape' : 'portrait') + '; }');\r\n                html += '</style>';\r\n            }\r\n            // 打印预览兼容ie\r\n            if (iePreview && printer.isIE()) {\r\n                html += ieWebBrowser;\r\n                if (print) {\r\n                    html += ('<script>window.onload = function(){ WebBrowser.ExecWB(7, 1); ' + (close ? 'window.close();' : '') + ' }</script>');\r\n                }\r\n            } else if (print) {\r\n                html += ('<script>window.onload = function(){ window.print(); ' + (close ? 'window.close();' : '') + ' }</script>');\r\n            }\r\n            // 写入html\r\n            pDocument.open();\r\n            pDocument.write(html);\r\n            pDocument.close();\r\n        }\r\n        return pWindow;\r\n    };\r\n\r\n    /** 分页打印 */\r\n    printer.printPage = function (param) {\r\n        param || (param = {});\r\n        var htmls = param.htmls;  // 打印的内容\r\n        var horizontal = param.horizontal;  // 纸张是否是横向\r\n        var style = param.style;  // 打印的样式\r\n        var padding = param.padding;  // 页边距\r\n        var blank = param.blank;  // 是否打开新窗口\r\n        var close = param.close;  // 打印完是否关闭打印窗口\r\n        var print = param.print;  // 是否自动调用打印\r\n        var width = param.width;  // 页面宽度\r\n        var height = param.height;  // 页面高度\r\n        var iePreview = param.iePreview;  // 兼容ie打印预览\r\n        var isDebug = param.debug;  // 调试模式\r\n        // 设置参数默认值\r\n        (print === undefined) && (print = true);\r\n        (iePreview === undefined) && (iePreview = true);\r\n        (blank === undefined && printer.isIE()) && (blank = true);\r\n        (close === undefined && blank && !printer.isIE()) && (close = true);\r\n        // 创建打印窗口\r\n        var pWindow, pDocument;\r\n        if (blank) {\r\n            pWindow = window.open('', '_blank');\r\n            pDocument = pWindow.document;\r\n        } else {\r\n            var printFrame = document.getElementById('printFrame');\r\n            if (!printFrame) {\r\n                $('body').append('<iframe id=\"printFrame\" style=\"display: none;\"></iframe>');\r\n                printFrame = document.getElementById('printFrame');\r\n            }\r\n            pWindow = printFrame.contentWindow;\r\n            pDocument = printFrame.contentDocument || printFrame.contentWindow.document;\r\n        }\r\n        pWindow.focus();  // 让打印窗口获取焦点\r\n        // 拼接打印内容\r\n        var htmlStr = '<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>打印窗口</title>';\r\n        style && (htmlStr += style);  // 写入自定义css\r\n        // 控制分页的css\r\n        htmlStr += printer.getPageCss(padding, width, height);\r\n        // 控制打印方向\r\n        if (horizontal !== undefined) {\r\n            htmlStr += '<style type=\"text/css\" media=\"print\">';\r\n            htmlStr += ('  @page { size: ' + (horizontal ? 'landscape' : 'portrait') + '; }');\r\n            htmlStr += '</style>';\r\n        }\r\n        htmlStr += '</head><body>';\r\n        // 拼接分页内容\r\n        if (htmls) {\r\n            var pageClass = isDebug ? ' page-debug' : '';  // 调试模式\r\n            htmlStr += '<div class=\"print-page' + pageClass + '\">';\r\n            for (var i = 0; i < htmls.length; i++) {\r\n                htmlStr += '<div class=\"print-page-item\">';\r\n                htmlStr += htmls[i];\r\n                htmlStr += '</div>';\r\n            }\r\n            htmlStr += '</div>';\r\n        }\r\n        // 兼容ie打印预览\r\n        if (iePreview && printer.isIE()) {\r\n            htmlStr += ieWebBrowser;\r\n            if (print) {\r\n                htmlStr += ('<script>window.onload = function(){ WebBrowser.ExecWB(7, 1); ' + (close ? 'window.close();' : '') + ' }</script>');\r\n            }\r\n        } else if (print) {\r\n            htmlStr += ('<script>window.onload = function(){ window.print(); ' + (close ? 'window.close();' : '') + ' }</script>');\r\n        }\r\n        htmlStr += '</body></html>';\r\n        // 写入打印内容\r\n        pDocument.open();\r\n        pDocument.write(htmlStr);\r\n        pDocument.close();\r\n        return pWindow;\r\n    };\r\n\r\n    /** 分页打印的css */\r\n    printer.getPageCss = function (padding, width, height) {\r\n        var pageCss = '<style>';\r\n        pageCss += 'body {';\r\n        pageCss += '    margin: 0 !important;';\r\n        pageCss += '} ';\r\n        // 自定义边距竖屏样式\r\n        pageCss += '.print-page .print-page-item {';\r\n        pageCss += '    page-break-after: always !important;';\r\n        pageCss += '    box-sizing: border-box !important;';\r\n        pageCss += '    border: none !important;';\r\n        padding && (pageCss += ('padding: ' + padding + ';'));\r\n        width && (pageCss += ('  width: ' + width + ';'));\r\n        height && (pageCss += (' height: ' + height + ';'));\r\n        pageCss += '} ';\r\n        // 调试模式样式\r\n        pageCss += '.print-page.page-debug .print-page-item {';\r\n        pageCss += '    border: 1px solid red !important;';\r\n        pageCss += '} ';\r\n        pageCss += printer.getCommonCss(true);  // 加入公共css\r\n        pageCss += '</style>';\r\n        return pageCss;\r\n    };\r\n\r\n    /** 隐藏元素 */\r\n    printer.hideElem = function (elems) {\r\n        $('.' + hideClass).addClass(printingClass);\r\n        if (!elems) {\r\n            return;\r\n        }\r\n        if (elems instanceof Array) {\r\n            for (var i = 0; i < elems.length; i++) {\r\n                $(elems[i]).addClass(hideClass + ' ' + printingClass);\r\n            }\r\n        } else {\r\n            $(elems).addClass(printingClass);\r\n        }\r\n    };\r\n\r\n    /** 取消隐藏 */\r\n    printer.showElem = function (elems) {\r\n        $('.' + hideClass).removeClass(printingClass);\r\n        if (!elems) {\r\n            return;\r\n        }\r\n        if (elems instanceof Array) {\r\n            for (var i = 0; i < elems.length; i++) {\r\n                $(elems[i]).removeClass(hideClass + ' ' + printingClass);\r\n            }\r\n        } else {\r\n            $(elems).removeClass(printingClass);\r\n        }\r\n    };\r\n\r\n    /** 打印公共样式 */\r\n    printer.getCommonCss = function (isPrinting) {\r\n        var cssStr = ('.' + hideClass + '.' + printingClass + ' {');\r\n        cssStr += '        visibility: hidden !important;';\r\n        cssStr += '   }';\r\n        // 表格样式\r\n        cssStr += '   .print-table {';\r\n        cssStr += '        border: none;';\r\n        cssStr += '        border-collapse: collapse;';\r\n        cssStr += '        width: 100%;';\r\n        cssStr += '   }';\r\n        cssStr += '   .print-table td, .print-table th {';\r\n        cssStr += '        color: #333;';\r\n        cssStr += '        padding: 9px 15px;';\r\n        cssStr += '        word-break: break-all;';\r\n        cssStr += '        border: 1px solid #333;';\r\n        cssStr += '   }';\r\n        //\r\n        if (isPrinting) {\r\n            cssStr += ('.' + hideClass + ' {');\r\n            cssStr += '     visibility: hidden !important;';\r\n            cssStr += '}';\r\n        }\r\n        return cssStr;\r\n    };\r\n\r\n    /** 拼接html */\r\n    printer.makeHtml = function (param) {\r\n        var title = param.title;\r\n        var style = param.style;\r\n        var body = param.body;\r\n        title == undefined && (title = '打印窗口');\r\n        var htmlStr = '<!DOCTYPE html><html lang=\"en\">';\r\n        htmlStr += '      <head><meta charset=\"UTF-8\">';\r\n        htmlStr += ('        <title>' + title + '</title>');\r\n        style && (htmlStr += style);\r\n        htmlStr += '      </head>';\r\n        htmlStr += '      <body>';\r\n        body && (htmlStr += body);\r\n        htmlStr += '      </body>';\r\n        htmlStr += '   </html>';\r\n        return htmlStr;\r\n    };\r\n\r\n    $('head').append('<style>' + printer.getCommonCss() + '</style>');\r\n    exports(\"printer\", printer);\r\n});\r\n\n\n//# sourceURL=webpack:///./src/js/printer.js?");

/***/ }),

/***/ "./src/printer.js":
/*!************************!*\
  !*** ./src/printer.js ***!
  \************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("__webpack_require__(/*! ./js/printer.js */ \"./src/js/printer.js\");\n\n//# sourceURL=webpack:///./src/printer.js?");

/***/ })

/******/ });