<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style type="text/css">
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/manage/message.jsp" %>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<div class="main-container-inner">
			<%@ include file="/WEB-INF/views/manage/menu.jsp" %>
			
			<div class="main-content">
				<div class="page-content">
					<div class="page-header">
						<h1>
							系统用户
							<small>
								<i class="icon-double-angle-right"></i>
								查看 &amp; 管理
							</small>
						</h1>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">

							<div class="row">
								<div class="col-xs-12">
									<!-- <h3 class="header smaller lighter blue">jQuery dataTables</h3> -->
									<div class="table-header">
										系统用户列表
									</div>

									<div class="table-responsive">
										<table id="dataList" class="table table-striped table-bordered">
									        <thead>
										        <tr>
										            <th>姓名</th>
										            <th>登录名</th>
										            <th>性别</th>
										            <th>年龄</th>
										            <th>电话号码</th>
										            <th>身份证号</th>
										            <th>电子邮箱</th>
										            <th>所在城市</th>
										            <th>所属部门</th>
										            <th>是否有效</th>
										            <th>操作</th>
										        </tr>
									        </thead>
									        <tbody>
									        	
									        </tbody>
									        <!-- tbody是必须的 -->
									    </table>
									</div>
								</div>
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div>
	</div>
</body>
</html>
<%-- <script src="${ctxStatic}/js/jquery.dataTables.min.js"></script> --%>
<%-- <script src="${ctxStatic}/js/jquery.dataTables.bootstrap.js"></script> --%>
<script src="${ctxStatic}/js/tool.js"></script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
<!--定义操作列按钮模板-->
<script type="text/javascript">
	var table;
	$(function () {
	    table = $('#dataList').DataTable({
	        ajax: {
	            url: "${ctx}/sys/user/pagelist"
	        },
	        lengthMenu : [ 20, 30, 40, 50],
	        serverSide: true,
	        columns: [
	            {
					"data" : "name",
					"defaultContent" : ""
	            },
	            {
					"data" : "loginName",
					"defaultContent" : ""
				},
	            {
					"data" : "sex",
					"defaultContent" : ""
				},
	            {
					"data" : "age",
					"defaultContent" : ""
	            },
	            {
					"data" : "phone",
					"defaultContent" : ""
				},
	            {
					"data" : "idCard",
					"defaultContent" : ""
	            },
	            {
					"data" : "email",
					"defaultContent" : ""
	            },
	            {
					"data" : "city.id",
					"defaultContent" : ""
	            },
	            {
					"data" : "office.id",
					"defaultContent" : ""
	            },
	            {"data" : "status"},
	            {"data" : null}
	        ],
	        columnDefs: [
	            {
	                targets: 10,
	                render: function (data) {
	                    return (
	                    	"<button type='button' class='btn btn-primary btn-sm' id='editor"+ data.id +"' onclick=''>修改</button>" + 
	                        "<button type='button' class='btn btn-danger btn-sm' id='del"+ data.id +"')'>删除</button>"
	                    )
	                }
	            }
	
	        ],
	        "language": {
	            "lengthMenu": "_MENU_ 条记录每页",
	            "zeroRecords": "没有找到记录",
	            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )，共  _TOTAL_ 条记录",
	            "infoEmpty": "无记录",
	            "infoFiltered": "(从 _MAX_ 条记录过滤)",
	            "paginate": {
	                "previous": "上一页",
	                "next": "下一页"
	            }
	        },
	        "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +
	                "t" +
	                "<'row'<'col-xs-6'i><'col-xs-6'p>>",
	        initComplete: function () {
				var arg = Array.prototype.slice.call(arguments);
					tool.btnGrop(arg,'del',del);
					tool.btnGrop(arg,'editor',edit);
	           	$("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">增加基础数据</button>&nbsp');
	            $("#mytool").append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>');
	            /*$("#datainit").on("click", initData); */
	        }
	
	    });
	
	   /*  $("#save").click(add);
	
	    $("#initData").click(initSingleData); */
	
	});
	/* function btnGrop(arg,type,fn){
		for(var i = 0; i < arg[1].data.length; i++){
			(function(i){
				var iData = arg[1].data[i];
				$('#'+ type + iData.id).on('click',function(e){
					fn.call(this,iData);
				})
			})(i)
		}
	} */
	// 删除
	function del(data) {
		console.info('删除',data.id);
		var user = {};
		user["id"] = data.id;
		tool.ajaxCom({
			//url:'${ctx}/sys/user/romove',
			url : '${ctx}/sys/user/remove',
			data: JSON.stringify(user),
			success : function (data) {
				table.ajax.reload();
				alert("删除成功！");
			},
			error : function (data) {
				table.ajax.reload();
				alert("删除失败！");
			}
		})
	}
	function edit(data) {
		console.info('编辑',data.id);
		tool.ajaxCom({
			url:'${ctx}/sys/user/save',
			data:{
				"id" : data.id
			}
		})
	}
	/* //异步
	function ajaxCom(option){
		var baseOption = {
		   type:'POST',
		   datatype:"JSON"
		};
		var setting = $.extend(true,baseOption,option);
		var callback = $.ajax(setting);
		return callback;
	} */
	/* /**
	 * 初始化基础数据
	 */
	/* function initData() {
	    var flag = confirm("本功能将添加数据到数据库，你确定要添加么？");
	    if (flag) {
	        $.get("/objects.txt", function (data) {
	            var jsondata = JSON.parse(data);
	            $(jsondata.data).each(function (index, obj) {
	                ajax(obj);
	            });
	        });
	    }
	} */
	
	/**
	 * 初始化基础数据
	 */
	/* function initSingleData() {
	    $("#name").val("http://dt.thxopen.com");
	    $("#position").val("ShiMen");
	    $("#salary").val("1");
	    $("#start_date").val("2015/04/01");
	    $("#office").val("Home");
	    $("#extn").val("001");
	} */
	
	/**
	 * 清除
	 */
	/* function clear() {
	    $("#name").val("").attr("disabled",false);
	    $("#position").val("");
	    $("#salary").val("");
	    $("#start_date").val("");
	    $("#office").val("");
	    $("#extn").val("");
	    editFlag = false;
	} */
	
	/**
	 * 添加数据
	 **/
	/* function add() {
	    var addJson = {
	        "name": $("#name").val(),
	        "position": $("#position").val(),
	        "salary": $("#salary").val(),
	        "start_date": $("#start_date").val(),
	        "office": $("#office").val(),
	        "extn": $("#extn").val()
	    };
	
	    ajax(addJson);
	} */
	
	/**
	 *编辑方法
	 **/
	/* function edit(name,position,salary,start_date,office,extn) {
	    console.log(name);
	    editFlag = true;
	    $("#myModalLabel").text("修改");
	    $("#name").val(name).attr("disabled",true);
	    $("#position").val(position);
	    $("#salary").val(salary);
	    $("#start_date").val(start_date);
	    $("#office").val(office);
	    $("#extn").val(extn);
	    $("#myModal").modal("show");
	} */
	
	/* function ajax(obj) {
	    var url ="/sys/user/save" ;
	    if (editFlag) {
	        url = "/sys/user/save";
	    }
	    $.ajax({
	        url:url ,
	        data: {
	            "name": obj.name,
	            "position": obj.position,
	            "salary": obj.salary,
	            "start_date": obj.start_date,
	            "office": obj.office,
	            "extn": obj.extn
	        }, success: function (data) {
	            table.ajax.reload();
	            $("#myModal").modal("hide");
	            $("#myModalLabel").text("新增");
	            clear();
	            console.log("结果" + data);
	        }
	    });
	} */
	
	
	/**
	 * 删除数据
	 * @param name
	 */
	/* function del(name) {
	    $.ajax({
	        url: "/sys/user/remove",
	        data: {
	            "name": name
	        }, success: function (data) {
	            table.ajax.reload();
	            console.log("删除成功" + data);
	        }
	    });
	} */
	$(document).ready(function() {
		
		
	}); 
</script>