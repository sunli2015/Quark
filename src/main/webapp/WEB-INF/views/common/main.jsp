<%@page import="cn.org.quark.core.login.Loginer"%>
<%@page import="cn.org.quark.core.login.LoginUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Loginer loginer = LoginUtil.getLoginer(request);
request.setAttribute("loginer", loginer);
%>
<c:set var="title" value="QUARK平台" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${title }</title>
	<%@ include file="/commons/public.inc"%>
	<script type="text/javascript" src="${ctx }/commons/main.js"></script>
  </head>
  <body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
	XXX
	</div>
	<div data-options="region:'west',split:true" title="功能菜单" style="width:20%;">
		<%--
		功能列表菜单
		 --%>
		<ul id="funtree" class="easyui-tree">
			<li id="example">样例</li>
			<li>
				<span>系统管理</span>
				<ul>
					<coral:auth res="admin_res"><li id="m_res">系统资源管理</li></coral:auth>
					<coral:auth res="admin_role"><li id="m_role">角色管理</li></coral:auth>
					<coral:auth res="admin_user"><li id="m_user">人事权限管理</li></coral:auth>
				</ul>
			</li>
			<coral:auth res="pwd_modify"><li id="pwd">修改密码</li></coral:auth>
			<li id="logout">注销</li>
		</ul>
		<%--
		<div class="easyui-accordion" data-options="fit:false,border:false">
			<div title="系统管理" style="padding:10px;">
				<ul class="easyui-tree">
					<li>系统资源管理</li>
					<li>角色管理</li>
					<li>人事权限管理</li>
				</ul>
			</div>
			<div title="修改密码" style="padding:10px;">
				修改密码
			</div>
			<div title="注销" style="padding:10px">
				注销
			</div>
		</div> --%>
	</div>
	<%--<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div> --%>
	<div id="region_center" data-options="region:'center'" title="登陆工号：${loginer.loginid } 姓名：${loginer.cname }">
		<div id="tt" class="easyui-tabs" style="display: none;"></div>
	</div>
</body>
</html>
