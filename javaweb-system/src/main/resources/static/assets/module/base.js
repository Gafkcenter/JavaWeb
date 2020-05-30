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
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/base.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/base.js":
/*!*********************!*\
  !*** ./src/base.js ***!
  \*********************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("__webpack_require__(/*! ./js/base.js */ \"./src/js/base.js\");\n\n//# sourceURL=webpack:///./src/base.js?");

/***/ }),

/***/ "./src/js/base.js":
/*!************************!*\
  !*** ./src/js/base.js ***!
  \************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("/**\r\n * JS公共函数类\r\n * @author 牧羊人\r\n * @date 2019/6/11\r\n */\r\nlayui.define(['jquery'], function (exports) {\r\n    \"use strict\";\r\n\r\n    // 声明变量\r\n    var $ = layui.$;\r\n\r\n    var base = {\r\n        /**\r\n         * 判断字符串是否为空\r\n         */\r\n        isEmpty: function (str) {\r\n            if (str == null || typeof str == \"undefined\" || str == \"\") {\r\n                return true;\r\n            }\r\n            return false;\r\n        },\r\n        /**\r\n         * 邮箱格式验证\r\n         */\r\n        isEmail: function (str) {\r\n            var reg = /^[a-z0-9]([a-z0-9\\\\.]*[-_]{0,4}?[a-z0-9-_\\\\.]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+([\\.][\\w_-]+){1,5}$/i;\r\n            if (reg.test(str)) {\r\n                return true;\r\n            } else {\r\n                return false;\r\n            }\r\n        },\r\n        /**\r\n         * 手机号格式验证\r\n         */\r\n        isMobile: function (tel) {\r\n            var reg = /(^1[3|4|5|7|8][0-9]{9}$)/;\r\n            if (reg.test(tel)) {\r\n                return true;\r\n            } else {\r\n                return false;\r\n            }\r\n            ;\r\n        },\r\n        upCase: function (str) {\r\n            if (comm.isEmpty(str)) {\r\n                return;\r\n            }\r\n            return str.substring(0, 1).toUpperCase() + str.substring(1);\r\n        },\r\n        /**\r\n         * 金额数字转大写\r\n         */\r\n        upDigit: function (num) {\r\n            var fraction = ['角', '分', '厘'];\r\n            var digit = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'];\r\n            var unit = [\r\n                ['元', '万', '亿'],\r\n                ['', '拾', '佰', '仟']\r\n            ];\r\n            var head = num < 0 ? '欠人民币' : '人民币';\r\n            num = Math.abs(num);\r\n            var s = '';\r\n            for (var i = 0; i < fraction.length; i++) {\r\n                s += (digit[Math.floor(num * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');\r\n            }\r\n            s = s || '整';\r\n            num = Math.floor(num);\r\n            for (var i = 0; i < unit[0].length && num > 0; i++) {\r\n                var p = '';\r\n                for (var j = 0; j < unit[1].length && num > 0; j++) {\r\n                    p = digit[num % 10] + unit[1][j] + p;\r\n                    num = Math.floor(num / 10);\r\n                }\r\n                s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;\r\n                //s = p + unit[0][i] + s;\r\n            }\r\n            return head + s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/, '零元整');\r\n        },\r\n        /**\r\n         * 设置cookie\r\n         */\r\n        setCookie: function (name, value, iDay) {\r\n            var oDate = new Date();\r\n            oDate.setDate(oDate.getDate() + iDay);\r\n            document.cookie = name + '=' + value + ';expires=' + oDate;\r\n        },\r\n        /**\r\n         * 获取cookie\r\n         */\r\n        getCookie: function (name) {\r\n            var arr = document.cookie.split('; ');\r\n            for (var i = 0; i < arr.length; i++) {\r\n                var arr2 = arr[i].split('=');\r\n                if (arr2[0] == name) {\r\n                    return arr2[1];\r\n                }\r\n            }\r\n            return '';\r\n        }\r\n        /**\r\n         * 删除Cookie\r\n         */\r\n        , removeCookie: function (name) {\r\n            this.setCookie(name, 1, -1);\r\n        },\r\n        /**\r\n         * 显示\r\n         */\r\n        show: function (obj) {\r\n            var blockArr = ['div', 'li', 'ul', 'ol', 'dl', 'table', 'article', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'p', 'hr', 'header', 'footer', 'details', 'summary', 'section', 'aside', '']\r\n            if (blockArr.indexOf(obj.tagName.toLocaleLowerCase()) === -1) {\r\n                obj.style.display = 'inline';\r\n            } else {\r\n                obj.style.display = 'block';\r\n            }\r\n        },\r\n        /**\r\n         * 隐藏\r\n         */\r\n        hide: function (obj) {\r\n            obj.style.display = \"none\";\r\n        },\r\n        /**\r\n         * Ajax网络请求\r\n         */\r\n        ajax: function (obj) {\r\n            obj = obj || {};\r\n            obj.type = obj.type.toUpperCase() || 'POST';\r\n            obj.url = obj.url || '';\r\n            obj.async = obj.async || true;\r\n            obj.data = obj.data || null;\r\n            obj.success = obj.success || function () {\r\n            };\r\n            obj.error = obj.error || function () {\r\n            };\r\n            var xmlHttp = null;\r\n            if (XMLHttpRequest) {\r\n                xmlHttp = new XMLHttpRequest();\r\n            } else {\r\n                xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');\r\n            }\r\n            var params = [];\r\n            for (var key in obj.data) {\r\n                params.push(key + '=' + obj.data[key]);\r\n            }\r\n            var postData = params.join('&');\r\n            if (obj.type.toUpperCase() === 'POST') {\r\n                xmlHttp.open(obj.type, obj.url, obj.async);\r\n                xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');\r\n                xmlHttp.send(postData);\r\n            } else if (obj.type.toUpperCase() === 'GET') {\r\n                xmlHttp.open(obj.type, obj.url + '?' + postData, obj.async);\r\n                xmlHttp.send(null);\r\n            }\r\n            xmlHttp.onreadystatechange = function () {\r\n                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {\r\n                    obj.success(xmlHttp.responseText);\r\n                } else {\r\n                    obj.error(xmlHttp.responseText);\r\n                }\r\n            };\r\n        },\r\n        /**\r\n         * 数据类型判断\r\n         * 案例：istype([],'array')\r\n         */\r\n        istype: function (o, type) {\r\n            if (type) {\r\n                var _type = type.toLowerCase();\r\n            }\r\n            switch (_type) {\r\n                case 'string':\r\n                    return Object.prototype.toString.call(o) === '[object String]';\r\n                case 'number':\r\n                    return Object.prototype.toString.call(o) === '[object Number]';\r\n                case 'boolean':\r\n                    return Object.prototype.toString.call(o) === '[object Boolean]';\r\n                case 'undefined':\r\n                    return Object.prototype.toString.call(o) === '[object Undefined]';\r\n                case 'null':\r\n                    return Object.prototype.toString.call(o) === '[object Null]';\r\n                case 'function':\r\n                    return Object.prototype.toString.call(o) === '[object Function]';\r\n                case 'array':\r\n                    return Object.prototype.toString.call(o) === '[object Array]';\r\n                case 'object':\r\n                    return Object.prototype.toString.call(o) === '[object Object]';\r\n                case 'nan':\r\n                    return isNaN(o);\r\n                case 'elements':\r\n                    return Object.prototype.toString.call(o).indexOf('HTML') !== -1\r\n                default:\r\n                    return Object.prototype.toString.call(o)\r\n            }\r\n        },\r\n        /**\r\n         * 关键字加标签（多个关键词用空格隔开）\r\n         * 案例：findKey('守侯我oaks接到了来自下次你离开快乐吉祥留在开城侯','守侯 开','i')\r\n         */\r\n        findKey: function (str, key, el) {\r\n            var arr = null,\r\n                regStr = null,\r\n                content = null,\r\n                Reg = null,\r\n                _el = el || 'span';\r\n            arr = key.split(/\\s+/);\r\n            //alert(regStr); //    如：(前端|过来)\r\n            regStr = this.createKeyExp(arr);\r\n            content = str;\r\n            //alert(Reg);//        /如：(前端|过来)/g\r\n            Reg = new RegExp(regStr, \"g\");\r\n            //过滤html标签 替换标签，往关键字前后加上标签\r\n            content = content.replace(/<\\/?[^>]*>/g, '')\r\n            return content.replace(Reg, \"<\" + _el + \">$1</\" + _el + \">\");\r\n        },\r\n        /**\r\n         * 获取URL参数\r\n         * 调用：get_url_param('http://xxxx?draftId=122000011938')\r\n         */\r\n        get_url_param: function (url) {\r\n            url = url ? url : window.location.href;\r\n            var _pa = url.substring(url.indexOf('?') + 1),\r\n                _arrS = _pa.split('&'),\r\n                _rs = {};\r\n            for (var i = 0, _len = _arrS.length; i < _len; i++) {\r\n                var pos = _arrS[i].indexOf('=');\r\n                if (pos == -1) {\r\n                    continue;\r\n                }\r\n                var name = _arrS[i].substring(0, pos),\r\n                    value = window.decodeURIComponent(_arrS[i].substring(pos + 1));\r\n                _rs[name] = value;\r\n            }\r\n            return _rs;\r\n        },\r\n        /**\r\n         * 设置URL参数\r\n         * 调用：set_url_param({'a':1,'b':2})\r\n         */\r\n        set_url_param: function (obj) {\r\n            var _rs = [];\r\n            for (var p in obj) {\r\n                if (obj[p] != null && obj[p] != '') {\r\n                    _rs.push(p + '=' + obj[p])\r\n                }\r\n            }\r\n            return _rs.join('&');\r\n        },\r\n        /**\r\n         * 随机产生颜色\r\n         */\r\n        random_color: function () {\r\n            //randomNumber是下面定义的函数\r\n            //写法1\r\n            //return 'rgb(' + this.randomNumber(255) + ',' + this.randomNumber(255) + ',' + this.randomNumber(255) + ')';\r\n\r\n            //写法2\r\n            return '#' + Math.random().toString(16).substring(2).substr(0, 6);\r\n\r\n            //写法3\r\n            //var color='#',_index=this.randomNumber(15);\r\n            //for(var i=0;i<6;i++){\r\n            //color+='0123456789abcdef'[_index];\r\n            //}\r\n            //return color;\r\n        },\r\n        /**\r\n         * 随机返回一定范围的数字\r\n         */\r\n        random_number: function (n1, n2) {\r\n            //randomNumber(5,10)\r\n            //返回5-10的随机整数，包括5，10\r\n            if (arguments.length === 2) {\r\n                return Math.round(n1 + Math.random() * (n2 - n1));\r\n            }\r\n            //randomNumber(10)\r\n            //返回0-10的随机整数，包括0，10\r\n            else if (arguments.length === 1) {\r\n                return Math.round(Math.random() * n1)\r\n            }\r\n            //randomNumber()\r\n            //返回0-255的随机整数，包括0，255\r\n            else {\r\n                return Math.round(Math.random() * 255)\r\n            }\r\n        },\r\n        /**\r\n         * 数字排序\r\n         * 调用：array_sort(arr,'a,b')a是第一排序条件，b是第二排序条件\r\n         */\r\n        array_sort: function (arr, sort) {\r\n            if (!sort) {\r\n                return arr\r\n            }\r\n            var _sort = sort.split(',').reverse(),\r\n                _arr = arr.slice(0);\r\n            for (var i = 0, len = _sort.length; i < len; i++) {\r\n                _arr.sort(function (n1, n2) {\r\n                    return n1[_sort[i]] - n2[_sort[i]]\r\n                })\r\n            }\r\n            return _arr;\r\n        }\r\n    };\r\n\r\n    /**\r\n     * 输出模块(此模块接口是对象)\r\n     */\r\n    exports('base', base);\r\n});\r\n\n\n//# sourceURL=webpack:///./src/js/base.js?");

/***/ })

/******/ });