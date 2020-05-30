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
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/formX.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/formX.js":
/*!**********************!*\
  !*** ./src/formX.js ***!
  \**********************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("﻿__webpack_require__(/*! ./js/formX.js */ \"./src/js/formX.js\");\n\n//# sourceURL=webpack:///./src/formX.js?");

/***/ }),

/***/ "./src/js/formX.js":
/*!*************************!*\
  !*** ./src/js/formX.js ***!
  \*************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("﻿/** 表单扩展模块 date:2020-05-04   License By http://easyweb.vip */\r\nlayui.define(['form'], function (exports) {\r\n    var $ = layui.jquery;\r\n    var form = layui.form;\r\n    var verifyText = {\r\n        phoneX: '请输入正确的手机号',\r\n        emailX: '邮箱格式不正确',\r\n        urlX: '链接格式不正确',\r\n        numberX: '只能填写数字',\r\n        dateX: '日期格式不正确',\r\n        identityX: '请输入正确的身份证号',\r\n        psw: '密码必须5到12位，且不能出现空格',\r\n        equalTo: '两次输入不一致',\r\n        digits: '只能输入整数',\r\n        digitsP: '只能输入正整数',\r\n        digitsN: '只能输入负整数',\r\n        digitsPZ: '只能输入正整数和0',\r\n        digitsNZ: '只能输入负整数和0',\r\n        minlength: '最少输入{minlength}个字符',\r\n        maxlength: '最多输入{maxlength}个字符',\r\n        min: '值不能小于{min}',\r\n        max: '值不能大于{max}'\r\n    };\r\n\r\n    /** 扩展验证规则 */\r\n    var verifyList = {\r\n        /* 手机号 */\r\n        phoneX: function (value, item) {\r\n            var reg = /^1\\d{10}$/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.phoneX;\r\n            }\r\n        },\r\n        /* 邮箱 */\r\n        emailX: function (value, item) {\r\n            var reg = /^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.emailX;\r\n            }\r\n        },\r\n        /* 网址 */\r\n        urlX: function (value, item) {\r\n            var reg = /(^#)|(^http(s*):\\/\\/[^\\s]+\\.[^\\s]+)/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.urlX;\r\n            }\r\n        },\r\n        /* 数字 */\r\n        numberX: function (value, item) {\r\n            if (value && isNaN(value)) {\r\n                return verifyText.numberX;\r\n            }\r\n        },\r\n        /* 日期 */\r\n        dateX: function (value, item) {\r\n            var reg = /^(\\d{4})[-\\/](\\d{1}|0\\d{1}|1[0-2])([-\\/](\\d{1}|0\\d{1}|[1-2][0-9]|3[0-1]))*$/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.dateX;\r\n            }\r\n        },\r\n        /* 身份证 */\r\n        identityX: function (value, item) {\r\n            var reg = /(^\\d{15}$)|(^\\d{17}(x|X|\\d)$)/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.identityX;\r\n            }\r\n        },\r\n        /* 密码 */\r\n        psw: function (value, item) {\r\n            if (value && !/^[\\S]{5,12}$/.test(value)) {\r\n                return verifyText.psw\r\n            }\r\n        },\r\n        /* 重复 */\r\n        equalTo: function (value, item) {\r\n            if (value != $($(item).attr('lay-equalTo')).val()) {\r\n                var text = $(item).attr('lay-equalToText');\r\n                return text ? text : verifyText.equalTo;\r\n            }\r\n        },\r\n        /* 整数 */\r\n        digits: function (value, item) {\r\n            var reg = /^-?\\d+$/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.digits;\r\n            }\r\n        },\r\n        /* 正整数 */\r\n        digitsP: function (value, item) {\r\n            var reg = /^[1-9]\\d*$/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.digitsP;\r\n            }\r\n        },\r\n        /* 负整数 */\r\n        digitsN: function (value, item) {\r\n            var reg = /^-[1-9]\\d*$/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.digitsN;\r\n            }\r\n        },\r\n        /* 非负整数 */\r\n        digitsPZ: function (value, item) {\r\n            var reg = /^\\d+$/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.digitsPZ;\r\n            }\r\n        },\r\n        /* 非正整数 */\r\n        digitsNZ: function (value, item) {\r\n            var reg = /^-[1-9]\\d*|0/;\r\n            if (value && !reg.test(value)) {\r\n                return verifyText.digitsNZ;\r\n            }\r\n        },\r\n        /* h5 */\r\n        h5: function (value, item) {\r\n            if (value) {\r\n                var minlength = $(item).attr('minlength');\r\n                var maxlength = $(item).attr('maxlength');\r\n                var min = $(item).attr('min');\r\n                var max = $(item).attr('max');\r\n                if (minlength && value.length < minlength) {\r\n                    return verifyText.minlength.replace(/{minlength}/g, minlength);\r\n                }\r\n                if (maxlength && value.length > maxlength) {\r\n                    return verifyText.maxlength.replace(/{maxlength}/g, maxlength);\r\n                }\r\n                if (min && value * 1 < min * 1) {\r\n                    return verifyText.min.replace(/{min}/g, min);\r\n                }\r\n                if (max && value * 1 > max * 1) {\r\n                    return verifyText.max.replace(/{max}/g, max);\r\n                }\r\n            }\r\n        }\r\n    };\r\n\r\n    var formX = {\r\n        init: function () {\r\n            form.verify(verifyList);\r\n        },\r\n        /* 赋值表单，解决top.layui.form.val无效的问题 */\r\n        formVal: function (filter, object) {\r\n            formX.val(filter, object);\r\n        },\r\n        /* 赋值表单，解决top.layui.form.val无效的问题 */\r\n        val: function (filter, object) {\r\n            $('.layui-form[lay-filter=\"' + filter + '\"]').each(function () {\r\n                var $item = $(this);\r\n                for (var f in object) {\r\n                    if (!object.hasOwnProperty(f)) continue;\r\n                    var $elem = $item.find('[name=\"' + f + '\"]');\r\n                    if ($elem.length > 0) {\r\n                        var type = $elem[0].type;\r\n                        if (type === 'checkbox') {  // 如果为复选框\r\n                            $elem[0].checked = object[f];\r\n                        } else if (type === 'radio') { // 如果为单选框\r\n                            $elem.each(function () {\r\n                                if (this.value == object[f]) {\r\n                                    this.checked = true;\r\n                                }\r\n                            });\r\n                        } else { // 其它类型的表单\r\n                            $elem.val(object[f]);\r\n                        }\r\n                    }\r\n                }\r\n            });\r\n            form.render(null, filter);\r\n        },\r\n        /* 渲染select */\r\n        renderSelect: function (param) {\r\n            var defaultOption = {\r\n                elem: undefined,\r\n                data: [],\r\n                name: undefined,\r\n                value: undefined,\r\n                hint: '请选择',\r\n                initValue: undefined,\r\n                method: 'get',\r\n                where: undefined,\r\n                headers: undefined,\r\n                async: true,\r\n                done: undefined,\r\n                error: undefined\r\n            };\r\n            param = $.extend(defaultOption, param);\r\n            if (typeof param.data === 'string') {\r\n                $.ajax({\r\n                    url: param.data,\r\n                    type: param.method,\r\n                    data: param.where,\r\n                    dataType: 'json',\r\n                    headers: param.header || param.headers,\r\n                    async: param.async,\r\n                    success: function (result, status, xhr) {\r\n                        if (result.data) {\r\n                            param.data = result.data;\r\n                            formX.renderSelect(param);\r\n                        } else {\r\n                            param.error && param.error(xhr, result);\r\n                        }\r\n                    },\r\n                    error: param.error\r\n                });\r\n            } else {\r\n                var html = param.hint ? ('<option value=\"\">' + param.hint + '</option>') : '';\r\n                for (var i = 0; i < param.data.length; i++) {\r\n                    if (param.name && param.value) {\r\n                        html += ('<option value=\"' + param.data[i][param.value] + '\"' + (param.data[i][param.value] == param.initValue ? ' selected' : '') + '>' + param.data[i][param.name] + '</option>');\r\n                    } else {\r\n                        html += ('<option value=\"' + param.data[i] + '\"' + (param.data[i] == param.initValue ? ' selected' : '') + '>' + param.data[i] + '</option>');\r\n                    }\r\n                }\r\n                $(param.elem).html(html);\r\n                var $form = $(param.elem).parent('.layui-form');\r\n                if ($form.length === 0) {\r\n                    $form = $(param.elem).parentsUntil('.layui-form').last().parent();\r\n                }\r\n                form.render('select', $form.attr('lay-filter'));\r\n                param.done && param.done(param.data);\r\n            }\r\n        },\r\n        /* 验证码倒计时 */\r\n        startTimer: function (elem, time, format) {\r\n            if (!time) time = 60;\r\n            if (!format) {\r\n                format = function (t) {\r\n                    return t + 's';\r\n                }\r\n            }\r\n            if (formX.timers[elem]) clearInterval(formX.timers[elem]);\r\n            var orgHtml = $(elem).html();\r\n            $(elem).html(format(time));\r\n            $(elem).prop('disabled', true);\r\n            $(elem).addClass('layui-btn-disabled');\r\n            var timer = setInterval(function () {\r\n                time--;\r\n                if (time <= 0) {\r\n                    clearInterval(timer);\r\n                    $(elem).html(orgHtml);\r\n                    $(elem).removeProp('disabled');\r\n                    $(elem).removeClass('layui-btn-disabled');\r\n                } else {\r\n                    $(elem).html(format(time));\r\n                }\r\n            }, 1000);\r\n            formX.timers[elem] = timer;\r\n        },\r\n        timers: {},\r\n        /* 获取表单修改过的数据 */\r\n        formUpdatedField: function (field, oldField) {\r\n            if (typeof field == 'string') field = form.val(field);\r\n            for (var key in field) {\r\n                if (!field.hasOwnProperty(key)) continue;\r\n                if (field[key] === oldField[key]) delete field[key];\r\n            }\r\n            if (Object.keys(field).length > 0) return field;\r\n        }\r\n    };\r\n\r\n    formX.init();\r\n    exports('formX', formX);\r\n});\n\n//# sourceURL=webpack:///./src/js/formX.js?");

/***/ })

/******/ });