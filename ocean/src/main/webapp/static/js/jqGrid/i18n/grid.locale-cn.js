;(function($){
/**
 * jqGrid 中文 翻译
 * Tony Tomov tony@trirand.com
 * http://trirand.com/blog/ 
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
**/
$.jgrid = $.jgrid || {};
$.extend($.jgrid,{
	defaults : {
		recordtext: "View {0} - {1} of {2}",
		emptyrecords: "无可查看记录",
		loadtext: "加载...",
		pgtext : "Page {0} of {1}"
	},
	search : {
		caption: "搜索...",
		Find: "查询",
		Reset: "重置",
		odata: [
			{ oper:'eq', text:'等于'},
			{ oper:'ne', text:'不等于'},
			{ oper:'lt', text:'小于'},
			{ oper:'le', text:'小于或等于'},
			{ oper:'gt', text:'大于'},
			{ oper:'ge', text:'大于或等于'},
			{ oper:'bw', text:'以开始'},
			{ oper:'bn', text:'不以开始'},
			{ oper:'in', text:'存在'},
			{ oper:'ni', text:'不存在'},
			{ oper:'ew', text:'以结束'},
			{ oper:'en', text:'不以结束'},
			{ oper:'cn', text:'包含'},
			{ oper:'nc', text:'不包含'}],
		groupOps: [	{ op: "且", text: "所有" },	{ op: "或",  text: "任一" }	]
	},
	edit : {
		addCaption: "新增记录",
		editCaption: "编辑记录",
		bSubmit: "提交",
		bCancel: "取消",
		bClose: "关闭",
		saveData: "记录已经被修改！是否保存修改？",
		bYes : "确认",
		bNo : "否",
		bExit : "取消",
		msg: {
			required:"字段为必输",
			number:"请输入一个数字类型值",
			minValue:"数据必须大于或等于",
			maxValue:"数据必须小于或等于",
			email: "该数据不是一个邮箱",
			integer: "请输入一个整数类型值",
			date: "请输入一个时间类型值",
			url: "该数据不是一个 URL格式  (例如前缀应为：'http://' or 'https://')",
			nodefined : " is not defined! 未定义",
			novalue : "返回值是必须的",
			customarray : "客户端函数要求必须返回数组类型",
			customfcheck : "客户端函数应该出现在定制检查的情况下"
			
		}
	},
	view : {
		caption: "查看记录",
		bClose: "关闭"
	},
	del : {
		caption: "删除",
		msg: "确认删除选中的记录？",
		bSubmit: "删除",
		bCancel: "取消"
	},
	nav : {
		edittext: "",
		edittitle: "编辑选中的行",
		addtext:"",
		addtitle: "新增行",
		deltext: "",
		deltitle: "删除选中的行",
		searchtext: "",
		searchtitle: "查询记录",
		refreshtext: "",
		refreshtitle: "重新加载表格",
		alertcap: "警告",
		alerttext: "请选择行",
		viewtext: "",
		viewtitle: "查询选中的行"
	},
	col : {
		caption: "选择列",
		bSubmit: "确认",
		bCancel: "取消"
	},
	errors : {
		errcap : "错误",
		nourl : "无 URL被设置",
		norecords: "无可以用记录",
		model : "列名不等于列对象数"
	},
	formatter : {
		integer : {thousandsSeparator: ",", defaultValue: '0'},
		number : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0.00'},
		currency : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
		date : {
			dayNames:   [
				"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat",
				"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
			],
			monthNames: [
				"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
				"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
			],
			AmPm : ["am","pm","AM","PM"],
			S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th';},
			srcformat: 'Y-m-d',
			newformat: 'n/j/Y',
			parseRe : /[Tt\\\/:_;.,\t\s-]/,
			masks : {
				// see http://php.net/manual/en/function.date.php for PHP format used in jqGrid
				// and see http://docs.jquery.com/UI/Datepicker/formatDate
				// and https://github.com/jquery/globalize#dates for alternative formats used frequently
				// one can find on https://github.com/jquery/globalize/tree/master/lib/cultures many
				// information about date, time, numbers and currency formats used in different countries
				// one should just convert the information in PHP format
				ISO8601Long:"Y-m-d H:i:s",
				ISO8601Short:"Y-m-d",
				// short date:
				//    n - Numeric representation of a month, without leading zeros
				//    j - Day of the month without leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				// example: 3/1/2012 which means 1 March 2012
				ShortDate: "n/j/Y", // in jQuery UI Datepicker: "M/d/yyyy"
				// long date:
				//    l - A full textual representation of the day of the week
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				LongDate: "l, F d, Y", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy"
				// long date with long time:
				//    l - A full textual representation of the day of the week
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    s - Seconds, with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				FullDateTime: "l, F d, Y g:i:s A", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy h:mm:ss tt"
				// month day:
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				MonthDay: "F d", // in jQuery UI Datepicker: "MMMM dd"
				// short time (without seconds)
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				ShortTime: "g:i A", // in jQuery UI Datepicker: "h:mm tt"
				// long time (with seconds)
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    s - Seconds, with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				LongTime: "g:i:s A", // in jQuery UI Datepicker: "h:mm:ss tt"
				SortableDateTime: "Y-m-d\\TH:i:s",
				UniversalSortableDateTime: "Y-m-d H:i:sO",
				// month with year
				//    Y - A full numeric representation of a year, 4 digits
				//    F - A full textual representation of a month
				YearMonth: "F, Y" // in jQuery UI Datepicker: "MMMM, yyyy"
			},
			reformatAfterEdit : false
		},
		baseLinkUrl: '',
		showAction: '',
		target: '',
		checkbox : {disabled:true},
		idName : 'id'
	}
});
})(jQuery);