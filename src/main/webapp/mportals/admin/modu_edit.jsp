<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<c:set var="title" value="编辑模块信息" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.8.3.min.js"></script>
<%@ include file="/common/ui_form_js.inc"%>
</head>
<body>
<DIV ID="message"><%@ include file="/common/messages.inc"%></DIV>
<c:if test="${not empty actionMessages}">
    <script>parent.location.reload();</script>
</c:if>
<s:set var="poid" value="%{#request.poid}"></s:set>
<c:if test="${empty param.poid}">
<s:set var="poid" value="%{#request.entity.parentModule.oid}"></s:set>
<c:set var="title" value="修改部门信息" />
</c:if>

<div id="dialog-form" title="${title }">
	<s:form method="post" action="modu!save.action" namespace="/admin" theme="simple" id="editform">
	<fieldset>
	<s:hidden name="oid" value="%{#request.entity.oid}"/>
	<s:hidden name="entity.oid"/>
	<s:hidden name="entity.parentModule.oid" value="%{#request.poid}"/>
		<label for="entity.moduleName">模块名</label>
		<s:textfield name="entity.moduleName" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
		<label for="entity.orderid">排序号：</label>
		<s:textfield name="entity.orderid" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
		<label for="entity.entityClassName">对应实体</label>
		<s:textfield name="entity.entityClassName" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
		<label for="entity.processDefName">流程定义名</label>
		<s:textfield name="entity.processDefName" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
		<label for="entity.editPageUrl">编辑页面</label>
		<s:textfield name="entity.editPageUrl" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
		<label for="entity.viewPageUrl">浏览页面</label>
		<s:textfield name="entity.viewPageUrl" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
	</fieldset>
	</s:form>
</div>
</body>
</html>

