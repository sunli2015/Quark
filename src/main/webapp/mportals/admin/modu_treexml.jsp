<?xml version='1.0' encoding='UTF-8'?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<%response.setContentType("text/xml");%>

<tree id="${id}">
<c:forEach items="${modules}" var="module" varStatus="index">
<item text="${module.moduleName}" id="${module.oid}" 
	<c:if test="${not empty module.subModules}">
		child="1"
	</c:if>
	<c:if test="${id eq '0'}">
		open="1"
	</c:if>
/>	
</c:forEach>
</tree>