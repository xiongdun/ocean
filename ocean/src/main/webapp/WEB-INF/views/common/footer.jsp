<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
	<i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
<script charset="utf8" src="${ctxStatic}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<%-- <script src="${ctxStatic}/js/jquery-2.0.3.min.js" type="text/javascript"></script> --%>
<script src="${ctxStatic}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/bootbox.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/bootstrap-colorpicker.min.js" type="text/javascript"></script>
<%-- <script src="${ctxStatic}/js/jquery.dataTables.bootstrap.js" type="text/javascript"></script> --%>
<script src="${ctxStatic}/js/jquery.easy-pie-chart.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/respond.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/select2.min.js" type="text/javascript"></script>
<%-- <script src="${ctxStatic}/js/jquery.dataTables.min.js" type="text/javascript"></script> 处理列表table专用 --%>
<script src="${ctxStatic}/js/html5shiv.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/typeahead-bs2.min.js"></script>
<script src="${ctxStatic}/js/x-editable/ace-editable.min.js"></script>
<script src="${ctxStatic}/js/x-editable/bootstrap-editable.min.js"></script>
<script src="${ctxStatic}/js/fuelux/fuelux.spinner.min.js"></script>
<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
  <script src="${ctxStatic}/js/excanvas.min.js"></script>
<![endif]-->
<script src="${ctxStatic}/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${ctxStatic}/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctxStatic}/js/jquery.gritter.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/jquery.slimscroll.min.js"></script>
<script src="${ctxStatic}/js/jquery.sparkline.min.js"></script>
<script src="${ctxStatic}/js/flot/jquery.flot.min.js"></script>
<script src="${ctxStatic}/js/flot/jquery.flot.pie.min.js"></script>
<script src="${ctxStatic}/js/flot/jquery.flot.resize.min.js"></script>

<script src="${ctxStatic}/js/ace.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/ace-elements.min.js"></script>
<!-- ace scripts -->

<!-- basic scripts -->
<!--[if !IE]> -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script> -->

<!-- <![endif]-->

<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctxStatic}/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctxStatic}/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if ("ontouchend" in document) document.write("<script src='${ctxStatic}/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn-success").on("click", function() {
			window.location.href="${ctx}/sys/index";
		});
		$("#btn-info").on("click", function() {
			window.location.href="${ctx}/sys/work/index";
		});
		$("#btn-warning").on("click", function() {
			window.location.href="${ctx}/sys/user/profile";
		});
		$("#btn-danger").on("click", function() {
			window.location.href="${ctx}/sys/settings/userSettings";
		});
	})
</script>