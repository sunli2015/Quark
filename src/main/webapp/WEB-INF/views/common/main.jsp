<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<div data-options="region:'west',split:true" title="功能菜单" style="width:25%;">
		<%--
		功能列表菜单
		 --%>
		<ul id="funtree" class="easyui-tree">
			<li>
				<span>系统管理</span>
				<ul>
					<li id="m_res">系统资源管理</li>
					<li id="m_role">角色管理</li>
					<li id="m_user">人事权限管理</li>
				</ul>
			</li>
			<li id="pwd">修改密码</li>
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
	<div id="region_center" data-options="region:'center'"  title="登陆用户：ADMIN">
		<div id="tt" class="easyui-tabs" style="display: none;"></div>
	</div>
</body>
</html>
