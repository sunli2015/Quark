<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="org.springframework.security.core.AuthenticationException"%>
<%@page import="org.springframework.security.web.WebAttributes"%>
	<c:if test="${not empty param.login_error}">
			<c:choose>		
				<c:when test="${param.login_error == 'disabled_user_error'}">
					 <c:set var="msg" value="该用户已被禁用！"/>
				</c:when>
				<c:when test="${param.login_error == 'user_not_found_error'}">
					 <c:set var="msg" value="该用户名不存在，请重试!"/>
				</c:when>
				<c:when test="${param.login_error == 'user_psw_error'}">
					 <c:set var="msg" value="无效密码，请重试！"/>
				</c:when>
				<c:when test="${param.login_error == 'too_many_user_error'}">
					 <c:set var="msg" value="多处使用同一用户名登陆,请等候！"/>
				</c:when>
				<c:otherwise>
					 <%String msg = ((AuthenticationException) session
										.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION))
										.getMessage();
						pageContext.setAttribute("msg","登陆时错误:"+msg);%>
				</c:otherwise>
			</c:choose>
	</c:if>	