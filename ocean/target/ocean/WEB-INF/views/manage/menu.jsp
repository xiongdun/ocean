<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<a class="menu-toggler" id="menu-toggler" href="#">
	<span class="menu-text"></span>
</a>

<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button id="btn-success" class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button id="btn-info" class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button id="btn-warning" class="btn btn-warning" >
				<i class="icon-group"></i>
			</button>

			<button id="btn-danger" class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>

			<span class="btn btn-info"></span>

			<span class="btn btn-warning"></span>

			<span class="btn btn-danger"></span>
		</div>
	</div><!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li class="active">
			<a href="${ctx}/sys/index">
				<i class="icon-dashboard"></i>
				<span class="menu-text"> 控制台 </span>
			</a>
		</li>

		<li>
			<a href="${ctx}/sys/user/profile">
				<i class="icon-text-width"></i>
				<span class="menu-text"> 个人中心 </span>
			</a>
		</li>

		<li>
			<a href="#" class="dropdown-toggle">
				<i class="icon-list"></i>
				<span class="menu-text"> 系统管理 </span>

				<b class="arrow icon-angle-down"></b>
			</a>

			<ul class="submenu">
				
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>

						用户管理
						<b class="arrow icon-angle-down"></b>
					</a>

					<ul class="submenu">
						<li>
							<a href="${ctx}/sys/user/list">
								<i class="icon-group"></i>
								用户列表
							</a>
						</li>
						<li>
							<a href="${ctx}/sys/user/form">
								<i class="icon-hdd"></i>
								新增用户
							</a>
						</li>
					</ul>
				</li>
				
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>

						角色管理
						<b class="arrow icon-angle-down"></b>
					</a>

					<ul class="submenu">
						<li>
							<a href="${ctx}/sys/role/list">
								<i class="icon-food"></i>
								角色列表
							</a>
						</li>
						<li>
							<a href="${ctx}/sys/role/form">
								<i class="icon-gift"></i>
								新增角色
							</a>
						</li>
					</ul>
				</li>
				
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>

						菜单管理
						<b class="arrow icon-angle-down"></b>
					</a>

					<ul class="submenu">
						<li>
							<a href="${ctx}/sys/menu/list">
								<i class="icon-book"></i>
								菜单列表
							</a>
						</li>
						<li>
							<a href="${ctx}/sys/menu/form">
								<i class="icon-coffee"></i>
								新增菜单
							</a>
						</li>
					</ul>
				</li>
				
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>

						区域管理
						<b class="arrow icon-angle-down"></b>
					</a>

					<ul class="submenu">
						<li>
							<a href="${ctx}/sys/area/list">
								<i class="icon-eye-close"></i>
								区域列表
							</a>
						</li>
						<li>
							<a href="${ctx}/sys/area/form">
								<i class="icon-edit"></i>
								新增区域
							</a>
						</li>
					</ul>
				</li>
				
				<li>
					<a href="#" class="dropdown-toggle">
						<i class="icon-double-angle-right"></i>

						字典管理
						<b class="arrow icon-angle-down"></b>
					</a>

					<ul class="submenu">
						<li>
							<a href="${ctx}/sys/dict/pagelist">
								<i class="icon-globe"></i>
								字典列表
							</a>
						</li>
						<li>
							<a href="${ctx}/sys/dict/form">
								<i class="icon-glass"></i>
								新增字典
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="dropdown-toggle">
				<i class="icon-desktop"></i>
				<span class="menu-text"> 系统监控 </span>

				<b class="arrow icon-angle-down"></b>
			</a>

			<ul class="submenu">
				<li>
					<a href="${ctx}/sys/log/pagelist">
						<i class="icon-double-angle-right"></i>
						系统日志
					</a>
				</li>

				<li>
					<a href="${ctx}/sys/visithis/pagelist">
						<i class="icon-double-angle-right"></i>
						访问记录
					</a>
				</li>
				
				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						数据库性能
					</a>
				</li>
				
				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						定时任务
					</a>
				</li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="dropdown-toggle">
				<i class="icon-edit"></i>
				<span class="menu-text"> 工作空间 </span>

				<b class="arrow icon-angle-down"></b>
			</a>

			<ul class="submenu">
				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						邮件
					</a>
				</li>

				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						聊天室
					</a>
				</li>
				
				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						新闻评论
					</a>
				</li>
				
				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						消息通知
					</a>
				</li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="dropdown-toggle">
				<i class="icon-list-alt"></i>
				<span class="menu-text"> 资源发布 </span>

				<b class="arrow icon-angle-down"></b>
			</a>

			<ul class="submenu">
				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						新闻资讯
					</a>
				</li>

				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						博客
					</a>
				</li>
				
				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						产品商品
					</a>
				</li>
				
				<li>
					<a href="#">
						<i class="icon-double-angle-right"></i>
						广告
					</a>
				</li>
			</ul>
		</li>
		
		<li>
			<a href="${ctx}/sys/settings/userSettings">
				<i class="icon-cogs"></i>
				<span class="menu-text"> 系统设置 </span>
			</a>
		</li>
	</ul><!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>
<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li>
				<i class="icon-home home-icon"></i>
				<a href="${ctx}/sys/index">首页</a>
			</li>

			<li>
				<a href="${ctx}/sys/index">控制台</a>
			</li>
			<li class="active">查看 &amp; 管理</li>
		</ul><!-- .breadcrumb -->

		<div class="nav-search" id="nav-search">
			<form class="form-search">
				<span class="input-icon">
					<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
					<i class="icon-search nav-search-icon"></i>
				</span>
			</form>
		</div><!-- #nav-search -->
	</div>
</div>
<div class="ace-settings-container" id="ace-settings-container">
	<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
		<i class="icon-cog bigger-150"></i>
	</div>

	<div class="ace-settings-box" id="ace-settings-box">
		<div>
			<div class="pull-left">
				<select id="skin-colorpicker" class="hide">
					<option data-skin="default" value="#438EB9">#438EB9</option>
					<option data-skin="skin-1" value="#222A2D">#222A2D</option>
					<option data-skin="skin-2" value="#C6487E">#C6487E</option>
					<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
				</select>
			</div>
			<span>&nbsp; 选择皮肤</span>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
			<label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
			<label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
			<label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
			<label class="lbl" for="ace-settings-rtl">切换到左边</label>
		</div>

		<div>
			<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
			<label class="lbl" for="ace-settings-add-container">
				切换窄屏
				<b></b>
			</label>
		</div>
	</div>
</div><!-- /#ace-settings-container -->