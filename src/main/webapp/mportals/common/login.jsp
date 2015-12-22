<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<%--AA-SOFT统一管理平台 --%>
<c:set var="title" value="" />
<html>
	<head>
		<title>${title}</title>
		<link rel="stylesheet" type="text/css"
			href="${ctx}/style/default.css">
		<%@ include file="/mportals/common/login_error_message.inc"%>
		<style type="text/css">
/*主页面标题*/
#main-page-title {
	height: 1;
	filter: Glow(Color = #000000, Strength = 3);
	font-size: 32px;
	color: #CEE7FF;
	margin-left: 20;
	letter-spacing: 2;
	font-weight: bold;
	text-align: left;
	top: -25;
}
</style>

		<script>
		var hosturl = window.location.href;
		function enterLogin(eventTag){
			var event = eventTag||window.event;
			if(event.keyCode==13){
				myform.submit();
			}
		}
		document.onkeypress=enterLogin;
		</script>
	</head>

	<body>
	
		<center>
			<table border="0" width="800" id="table2" height="127"
				cellspacing="0" cellpadding="0">
				<tr>
					<td valign="bottom" align="right">
						&nbsp;
						<a href=#
							onClick="this.style.behavior='url(#default#homepage)';this.setHomePage(hosturl);">设为主页</a>
						&nbsp; &nbsp; &nbsp;
						<a
							href="javascript:window.external.AddFavorite(hosturl, '${title}')">加入收藏</a>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CEE7FF" height="3"></td>
				</tr>
				<tr>
					<td height="33">
						&nbsp;

					</td>
				</tr>

			</table>
			<table border="0" width="800" id="table1" height="157"
				cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" width="*">
						<img border="0" src="../images/Start_05.jpg"
							align="right">
						<br>
						<br>
						<br>
						<br>
						<br>
						<div id="main-page-title"
							style="font-family: 黑体; text-align: center;">
							<c:out value="${title}" />
						</div>
					</td>
					<td width="1" bgcolor="#C0C0C0">
					</td>
					<td width="30%" align="right">
						<div style="color: red;">
							<c:if test="${not empty param.login_error}">&nbsp;&nbsp;&nbsp;&nbsp;${msg}<br>
								<br>
							</c:if>
						</div>
						<div>

							<form name="myform" method="post"
								action='<c:url value="/login" />'>
								
								<table border="0" cellpadding="0" cellspacing="0" width="80%"
									id="table4" height="126">
									<tr>
										<td width="25%" align="right" height="25">
											用户名：
										</td>
										<td width="*" height="25">
											<input name="username" type="text" class="textbox" size="18">
										</td>
									</tr>
									<tr>
										<td align="right" height="25">
											密&nbsp;&nbsp;&nbsp;码：
										</td>
										<td height="25">
											<input name="password" type="password" class="textbox"
												size="18" onKeyPress="enterLogin();">
										</td>
									</tr>

									<tr>
										<td align="center" height="36">
										</td>
										<td align="left" height="36">
											<input id="rememberme" type="checkbox"
												name="remember-me">
											<label for="rememberme">
												保存登录信息
											</label>
										</td>

									</tr>
									<tr>
										<td align="center" colspan="2">
											<input type="submit" value=" . 登录 . " name="B2"
												class="guest_button">
											<input type="reset" value=" . 重置 . " name="B3"
												class="guest_button">
										</td>

									</tr>
								</table>
							</form>

						</div>

					</td>
				</tr>
			</table>
			<table border="0" width="800" id="table3" cellspacing="0"
				cellpadding="0" height="77">
				<tr>
					<td align="center">
						<hr style="height: 3px; color: #CEE7FF" width="100%">
						Copyright &nbsp; @2009-2011 All rights reserved.
						<br>
						Powered by 
					</td>
				</tr>
			</table>

		</center>

	</body>

</html>
<%
	request.getSession().invalidate();
%>