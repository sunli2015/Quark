<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<c:set var="title" value="添加子部门信息" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<%@ include file="/common/meta.inc"%>
<%@ include file="/common/bizbase_js.inc"%>
<%@ include file="/common/ui_form_js.inc"%>
<%@ include file="/common/validity_js.inc"%>
<script src="${ctx}/dwr/interface/deptManager.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$("#dialog-form").openWin({type:'twoBtn'});
	
	$("#editform").validity(function() {
		$("#entity\\.deptname").require().maxLength(50);
		$("#entity\\.deptid").require().maxLength(50).match(function(val){
			var oid = $("#entity\\.oid").val();
			return validDept(oid,val);
			},"正在校验部门编码");//该部门编码已存在
	}); 
});
function validDept(oid,val){
	dwr.engine.setAsync(false);
	var pass = false;
	deptManager.validDept(oid,val,function(result){
		pass = result ; 
	});
	return pass;
}
</script>
</head>
<body>
<DIV ID="message"><%@ include file="/common/messages.inc"%></DIV>
<c:if test="${not empty actionMessages}">
    <script>parent.location.reload();</script>
</c:if>
<s:set var="poid" value="%{#request.poid}"></s:set>
<c:if test="${empty param.poid}">
<s:set var="poid" value="%{#request.entity.parentDept.oid}"></s:set>
<c:set var="title" value="修改部门信息" />
</c:if>

 <div id="dialog-form" title="${title }">
	<s:form method="post" action="dept!save.action" namespace="/admin" theme="simple" id="editform">
	<fieldset>
	<s:hidden name="oid" id="oid" value="%{#request.entity.oid}"/>
	<s:hidden name="entity.oid" id="entity.oid"/>
	<s:hidden name="entity.parentDept.oid" value="%{#request.poid}"/>
		<label for="logid">部门名称</label>
		<s:textfield name="entity.deptname" id="entity.deptname" cssClass="text ui-widget-content ui-corner-all" title="部门名称"/>
		<label for="cname">部门编码</label>
		<s:textfield name="entity.deptid" id="entity.deptid" cssClass="text ui-widget-content ui-corner-all" title="部门编码"/>
		<label for="cname">部门简称</label>
		<s:textfield name="entity.abbreviation" id="entity.abbreviation" cssClass="text ui-widget-content ui-corner-all" title="部门简称"/>
	</fieldset>
	</s:form>
</div>
</body>
</html>

