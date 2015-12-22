<?xml version='1.0' encoding='UTF-8'?>
<%@ include file="/common/taglibs.inc"%>
<%response.setContentType("text/xml");%>
<tree id="0">
	<item text="主页" id="index" select="1"/>
	<item text="通知维护" id="cms"/>
	<item text="订单管理" id="order"/>
	<item text="一卡通管理" id="yikatong"/>
	<coral:auth res="ADMIN">
	<item text="系统管理" id="admin">
		<item text="系统资源管理" id="admin_res"/>
		<item text="角色管理" id="admin_role"/>
		<item text="人事权限管理" id="admin_user"/>
		<%--<item text="首页模块维护" id="cms9"/> --%>
	</item>
	</coral:auth>
	<item text="修改我的密码" id="pwd"/>
	<item text="注销" id="logout"/>	
</tree>
