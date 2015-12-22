<?xml version='1.0' encoding='UTF-8'?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<%response.setContentType("text/xml");%>

<tree id="${id}">
<c:forEach items="${depts}" var="dept" varStatus="index">
<item text="${dept.deptname}" id="${dept.oid}" 
	<c:if test="${not empty dept.subDepts}">
		child="1"
	</c:if>
	<c:if test="${id eq '0'}">
		open="1"
	</c:if>
/>	
</c:forEach>
</tree>