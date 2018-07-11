<%@page import="cn.org.core.test.Test"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setAttribute("ctx", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>测试前端框架</title>
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
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the messages -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <!-- User Image -->
                        <img src="statics/user.jpg" class="img-circle" alt="">
                      </div>
                      <!-- Message title and timestamp -->
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <!-- The message -->
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                </ul>
                <!-- /.menu -->
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- Inner Menu: contains the notifications -->
                <ul class="menu">
                  <li><!-- start notification -->
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <!-- end notification -->
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks Menu -->
          <li class="dropdown tasks-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                <!-- Inner menu: contains the tasks -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <!-- Task title and progress text -->
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <!-- The progress bar -->
                      <div class="progress xs">
                        <!-- Change the css width attribute to simulate progress -->
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
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
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <!-- <li class="user-header">
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li> -->
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">个人中心</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">修改密码</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <!-- <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div> -->
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">退出登陆</a>
                </div>
              </li>
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
            <li style="margin-left:10px;"><a href="#" class="addTab" data-href="../role/index.do"><i class="fa fa-link"></i><span>角色管理</span></a></li>
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
  
  
	<div id="tabpanel" style="overflow: hidden; width: 100%;">
	
	</div>
    
    
    
    
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
window.tabPage;  
/*var jcTabs = [
'<iframe src="error.jsp" width="100%" height="100%" frameborder="0"></iframe>',
'1', '2', '3'
];

var _w = ($(top.window).width()-50)+"px";
var _h = ($(top.window).height()-50)+"px";
*/
$(document).ready(function(){
	
	js.initTabPage("tabpanel",{
        height: function() {
            var f = $(window).height(),
            d = $(".main-header:visible").outerHeight(),
            e = 0,//$(".main-footer:visible").outerHeight(),
            c = f - d - e;
            return c < 300 ? 300 : c
        }
    });
	js.addTabPage(null,'首页','error.jsp');
	$(".addTab").on('click',function(){
		var _title = $(this).find("span").html();
		var _href=$(this).attr("data-href");
		js.addTabPage($(this),_title,_href);
	});
	/*window.tabPage = new TabPanel({
        renderTo:'tabpanel',  
        //width: '100%', 
        height: _h, 
        widthResizable:true,
        //border:'none',  
        active : 0,
        //maxLength : 10,  
        items : [
            {id:'tp_1',title:'首页',html:jcTabs[0],closable: false}
        ]
    });*/
}); 

function toTabPage(obj,_title,_href){
	js.addTabPage(obj,_title,_href);
}
/*function addTab(t){
	
	
	
	console.log("tttttttttt",t);
	var _html = "";
	var _href="error.jsp";
	var _title = $(t.document).find("span").html();
	
	console.log("=========>title:"+_title);
	
	var index = $(obj).data("tabId");
	if(index == 1){
		_html = '<iframe src="error.jsp" width="100%" height="100%" frameborder="0"></iframe>';
	}
	if(index == 2 ){
		_html = '<iframe src="login.jsp" width="100%" height="100%" frameborder="0"></iframe>';
	}
	if(index == 3){
		_html = '<iframe src="main.jsp" width="100%" height="100%" frameborder="0"></iframe>';
	}
	if(index == 4){
		_title = "样例";
		_html = '<iframe src="/Quark/mportals2/example/list.html" width="100%" height="100%" frameborder="0"></iframe>';
		_href="/Quark/mportals2/example/list.html";
	}
	
	
	
	js.addTabPage($(obj),_title,_href);
	
	/*var _id = 'tp_'+(new Date()).getTime();
	$("li[id^='tp_']").each(function(i,d){
		var title = $(d).find(".title").html();
		var id = $(d).attr("id");
		console.log("===>"+i,title);
		if(title == _title){
			_id=id;
			return false;
		}
	})
	
	window.tabPage.addTab({
		id:_id,
		title: _title,
		html: _html,
		closable: true,
		disabled: false
	});
}*/
</script>

</body>
</html>