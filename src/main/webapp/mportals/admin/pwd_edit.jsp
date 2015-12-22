<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<c:set var="title" value="修改密码" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.8.3.min.js"></script>
<%@ include file="/common/ui_form_js.inc"%>
<%@ include file="/common/validity_js.inc"%>
<script type="text/javascript">
$(function() {
	$("#dialog-form").openWin({
		height: 280,
		type:'twoBtn'
		});
	
	$("#editform").validity(function() {
		$("#oldPwd").require().maxLength(30);
		$("#newPwd").require().maxLength(30);
		$("#newPwdAgain").require().maxLength(30);
		$("#newPwdAgain").match(function(val){
			return val == $("#newPwd").val();
			},"新密码与确认密码不符");
	}); 
});
</script>

</head>
<body>
<DIV ID="message"><%@ include file="/common/messages.inc"%></DIV>
<div id="dialog-form" title="修改密码">
	<s:form method="post" action="pwd" namespace="/admin" theme="simple" id="editform">
	<fieldset id="editForm">
	<s:hidden name="oid" value="%{#request.entity.oid}"/>
	<s:hidden name="entity.oid"/>
	<s:hidden name="entity.parentDept.oid" value="%{#request.poid}"/>
	<label for="oldPwd">请输入旧密码:</label>
	<s:password id="oldPwd" name="oldPwd" cssClass="text ui-widget-content ui-corner-all" title="旧密码"/>
	<label for="newPwd">请输入新密码:</label>
	<s:password id="newPwd" name="newPwd" cssClass="text ui-widget-content ui-corner-all" title="新密码"/>
	<label for="newPwdAgain">再次确认密码:</label>
	<s:password id="newPwdAgain" name="newPwdAgain" cssClass="text ui-widget-content ui-corner-all" title="确认新密码"/>
	</fieldset>
	</s:form>
</div>
</body>
</html>

