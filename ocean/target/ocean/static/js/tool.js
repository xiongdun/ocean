/*
 * create at 2017-07-09 by yangwei
 * ajax and plugins of datatables tools to crud
 * ajax 异步请求和datatables 插件公共增删改查工具js文件
 * */

/**
 * 定义Tool 构造方法
 */
function Tool() {
	return this;
};
/**
 * 对外暴露所定义的公共方法
 */
Tool.prototype = {
	/* 定义按钮组方法 */
	btnGrop : function (arg, type, fn) {
		for (var i = 0; i < arg[1].data.length; i++) {
			(function(i) {
				var iData = arg[1].data[i];
				$('#' + type + iData.id).on('click', function(e) {
					fn.call(this, iData);
				})
			})(i)
		}
	},
	/* 公共的ajax 方法 */
	ajaxCom : function (option) {
		var baseOption = {
		   type : 'POST',
		   async : true,
		   datatype : "JSON", // 返回的数据格式：json/xml/html/script/jsonp/text
		   contentType: 'application/json'
		};
		var setting = $.extend(true, baseOption, option);
		var callback = $.ajax(setting);
		return callback;
	},
	/* 删除的方法 */
	del : function (url, data) {
		console.log("这是删除的方法");
	},
	del : function (url) {
		del(url, null);
	},
	/* 修改的方法 */
	editor : function (url, data) {
		console.log("这是修改的方法");
	},
	editor : function (url) {
		editor(url, null);
	} 
}
/**
 * 返回当前对象
 */
var tool = new Tool();
