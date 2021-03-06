<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<c:set var="title" value="修改密码" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<%@ include file="/commons/public.inc"%>
<script type="text/javascript">
function submitForm(){
	var url =CONTEXT_PATH+"/pwd/pwd!modifyPwd.action";
	var oldPwd = $("#oldPwd").val();
	var newPwd = $("#newPwd").val();
	var newPwdAgain = $("#newPwdAgain").val();
	var param = {"oldPwd":oldPwd,"newPwd":newPwd,"newPwdAgain":newPwdAgain};
	$.getJSON(url,param,function(result){
		alert(result.operResult);
	});
}
</script>
</head>
<body>
<div class="easyui-panel" title="${title }" style="width: 400px;">
	<div style="padding:10px 60px 20px 60px;">
    <form id="ff" class="easyui-form" method="post" data-options="validate:true">
    	<table cellpadding="5">
    		<tr>
    			<td>请输入旧密码:</td>
    			<td><input class="easyui-textbox" type="password" id="oldPwd" name="oldPwd" data-options="required:true" title="旧密码"></input></td>
    		</tr>
    		<tr>
    			<td>请输入新密码:</td>
    			<td><input class="easyui-textbox" type="password" id="newPwd" name="newPwd" data-options="required:true" title="新密码"></input></td>
    		</tr>
    		<tr>
    			<td>再次确认密码:</td>
    			<td><input class="easyui-textbox" type="password" id="newPwdAgain" name="newPwdAgain" data-options="required:true" title="确认密码"></input></td>
    		</tr>
    	</table>
    </form>
    <div style="text-align:center;padding:5px">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
    </div>
</div>
</body>
</html>

