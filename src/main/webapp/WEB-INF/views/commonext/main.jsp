<%-- <%@page import="cn.org.core.test.Test"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Quark DEMO</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="" rel="stylesheet">
<script type="text/javascript" src="${ctx }/statics/AdminLTE/AdminLTE-2.4.3/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/statics/AdminLTE/AdminLTE-2.4.3/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/statics/AdminLTE/AdminLTE-2.4.3/dist/js/adminlte.min.js"></script>


<link rel="stylesheet" type="text/css" href="${ctx }/statics/AdminLTE/AdminLTE-2.4.3/dist/css/AdminLTE.min.css">
<link rel="stylesheet" type="text/css" href="${ctx }/statics/AdminLTE/AdminLTE-2.4.3/bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctx }/statics/AdminLTE/AdminLTE-2.4.3/bower_components/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${ctx }/statics/AdminLTE/AdminLTE-2.4.3/bower_components/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="${ctx }/statics/AdminLTE/AdminLTE-2.4.3/dist/css/skins/skin-blue-light.min.css">
<link rel="stylesheet" href="/Quark/statics/jquery-toastr/2.0/toastr.min.css?-06251059">
 <link rel="stylesheet" href="${ctx }/statics/common/jeesite.css">
 
<!-- tab -->
<link href="${ctx }/statics/wdScrollTab/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx }/statics/jQuery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/statics/wdScrollTab/js/TabPanel.js"></script>
<script type="text/javascript" src="${ctx }/statics/wdScrollTab/js/TabPanel.extend.js"></script>
<script type="text/javascript" src="${ctx }/statics/common/jeesite.js"></script>
<script src="/Quark/statics/jquery-toastr/2.0/toastr.min.js?-06251059"></script>

</head>
<body class="hold-transition skin-blue-light sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>方智</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>济南方智信息</b></span>
    </a>
	
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->

          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
          </li>
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar dist/img/user2-160x160-->
              <img src="../statics/user.jpg" class="user-image" alt="">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">超级管理员</span>
            </a>
            <ul class="dropdown-menu" style="width: 100%;">
              <li class="mt5" id="topAvater">
					<a id="userInfo" href="#" data-href="../mportals/adminext/userinfo/info.html?op=base" class="addTabPage">
					<i class="fa fa-user"></i> 个人中心</a>
				</li>
				<li>
					<a id="modifyPassword" href="#" data-href="../mportals/adminext/userinfo/info.html?op=pwd" class="addTabPage">
					<i class="fa fa-key"></i> 修改密码</a>
				</li>
				<li class="divider"></li>
				<li>
					<a href="${ctx }/logout">
					<i class="fa fa-sign-out"></i> 退出登录</a>
				</li>
				<li class="mt10"></li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <!-- <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
           -->
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="../statics/user.jpg" class="img-circle" alt="">
        </div>
        <div class="pull-left info">
          <p>超级管理员</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
          <a href="${ctx }/logout"><i class="fa fa-sign-out text-danger"></i> 注销</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <!-- <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="搜索">
          <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
        </div>
      </form> -->
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <!-- <li class="header">功能列表</li> -->
        <!-- Optionally, you can add icons to the links -->
        <!-- <li class="active"><a href="http://www.baidu.com"><i class="fa fa-link"></i> <span>百度</span></a></li>
        <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li> -->
        <li><a href="#" class="addTab" data-href="../mportals2/example/list.html"><i class="fa fa-link"></i> <span>样例</span></a></li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>系统管理</span>
            <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
          </a>
          <ul class="treeview-menu">
            <li style="margin-left:10px;"><a href="#" class="addTab" data-href="../dept/index.do"><i class="fa fa-link"></i><span>人事权限管理</span></a></li>
<<<<<<< HEAD
            <li style="margin-left:10px;"><a href="#" class="addTab" data-href="/Quark/mportals/adminext/roleinfo/list.html"><i class="fa fa-link"></i><span>角色管理</span></a></li>
=======
            <li style="margin-left:10px;"><a href="#" class="addTab" data-href="/Quark/mportals/adminext/user/index.html"><i class="fa fa-link"></i><span>用户管理</span></a></li>
            <li style="margin-left:10px;"><a href="#" class="addTab" data-href="../role/index.do"><i class="fa fa-link"></i><span>角色管理</span></a></li>
>>>>>>> f50fb598c7ed947598d1e9277a0459c579df2823
            <li style="margin-left:10px;"><a href="#" class="addTab" data-href="/Quark/mportals/adminext/resource/list.html"><i class="fa fa-link"></i><span>资源管理</span></a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
	<div id="tabpanel" style="overflow: hidden; width: 100%;"></div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer 
  <footer class="main-footer">-->
    <!-- To the right
    <div class="pull-right hidden-xs">
      Anything you want
    </div> -->
    <!-- Default to the left 
    <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
  </footer>-->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane active" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:;">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="pull-right-container">
                    <span class="label label-danger pull-right">70%</span>
                  </span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>

<script type="text/javascript" >
$(document).ready(function(){
	//解决窗口变化
	$(document).on('click', ".sidebar-toggle", function (e) {
		$(".sidebar-toggle").trigger("resize");
	  });
	//初始化首页
	js.initTabPage("tabpanel",{
        height: function() {
            var f = $(window).height(),
            d = $(".main-header:visible").outerHeight(),
            e = 0,//$(".main-footer:visible").outerHeight(),
            c = f - d - e;
            return c < 300 ? 300 : c
        }
    });
	js.addTabPage(null,'首页','../mportals/desktop.html',false);
	$(".addTab").on('click',function(){
		var _title = $(this).find("span").html();
		var _href=$(this).attr("data-href");
		js.addTabPage($(this),_title,_href);
	});
}); 

function toTabPage(obj,_title,_href){
	js.addTabPage(obj,_title,_href);
}

</script>

</body>
</html>