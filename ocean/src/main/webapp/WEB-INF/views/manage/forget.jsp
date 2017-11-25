<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style type="text/css">
    
</style>
</head>
<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<spring:message code="chose.language" />: <a href="?lang=zh_CN">
								<spring:message code="language.cn" />
							</a> - 
							<a href="?lang=en_US">
								<spring:message code="language.en" />
							</a>
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i>
								<span class="red"><spring:message code="project.title.name"/></span>
								<span class="white"><spring:message code="project.title.desc"/></span>
							</h1>
							<h4 class="blue">&copy; <spring:message code="company.name"/></h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="icon-key"></i>
											<spring:message code="forgetpassword.box.title" />
										</h4>

										<div class="space-6"></div>
										<p>
											<spring:message code="forgetpassword.box.inputdesc" />
										</p>

										<form action="${ctx}/sys/user/forget" method="post">
											<fieldset>
												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="email" class="form-control" placeholder="<spring:message code="forgetpassword.box.placeholder" />" />
														<i class="icon-envelope"></i>
													</span>
												</label>

												<div class="clearfix">
													<button type="submit" class="width-35 pull-right btn btn-sm btn-danger">
														<i class="icon-lightbulb"></i>
														<spring:message code="forgetpassword.box.sendbutton" />
													</button>
												</div>
											</fieldset>
										</form>
									</div><!-- /widget-main -->

									<div class="toolbar center">
										<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
											<spring:message code="forgetpassword.box.backlogin" />
											<i class="icon-arrow-right"></i>
										</a>
									</div>
								</div><!-- /widget-body -->
							</div><!-- /forgot-box -->
						</div><!-- /position-relative -->
					</div>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div>
	</div><!-- /.main-container -->
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script src="${ctxStatic}/js/jquery.tips.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript">
	function show_box(id) {
		jQuery('.widget-box.visible').removeClass('visible');
		jQuery('#'+id).addClass('visible');
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#login").on("click",function() {
			if (check()) {
				$("#login").submit();
			}
		});
	});
	function check() {

		if ($("#name").val() == "" || $("#name").val() == undefined) {
			
			$("#name").tips({
				side : 2,
				msg : '用户名不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#name").focus();
			return false;
		} else {
			$("#name").val(jQuery.trim($('#username').val()));
		}

		if ($("#password").val() == "" || $("#name").val() == undefined) {

			$("#password").tips({
				side : 2,
				msg : '密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#password").focus();
			return false;
		}
		
		/* if ($("#validateCode").val() == "" && "${isValidateCodeLogin}" == "true") {

			$("#validateCode").tips({
				side : 1,
				msg : '验证码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#validateCode").focus();
			return false;
		} */

		/* $("#loginbox").tips({
			side : 1,
			msg : '正在登录 , 请稍后 ...',
			bg : '#68B500',
			time : 10
		}); */

		return true;
	}
	jQuery(function() {
		// 显示登陆错误信息
		if("${message}".length > 0){
			if("${message}".indexOf("验证码") > -1){
				$("#validateCode").tips({
					side : 1,
					msg : "${message}",
					bg : '#FF5080',
					time : 15
				});
				$("#validateCode").focus();
			}else{
				$("#username").tips({
					side : 1,
					msg : "${message}",
					bg : '#FF5080',
					time : 15
				});
				$("#username").focus();
			}	
		}
		
		var loginname = $.cookie('username');
		var password = $.cookie('password');
		if (typeof(loginname) != "undefined" && typeof(password) != "undefined") {
			$("#username").val(loginname);
			$("#password").val(password);
			$("#saveid").attr("checked", true);
			$("#validateCode").focus();
		}
	})
</script>