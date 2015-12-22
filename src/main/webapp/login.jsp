<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<c:set var="title" value="AA-SOFT统一管理平台" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/themes/base/jquery.ui.all.css"/>
	<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.8.3.min.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.core.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.datepicker.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
	<link rel="stylesheet" href="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/demos/demos.css">
	<title>AA-SOFT统一管理平台</title>
	<script type="text/javascript">
	$(
function(){
	//var x= "xxx";
	//alert($(x).text());

	
	/*var h = $("<input>",{
		"type": "checkbox",
		"value":"xxx",
		name:"oid",
		id:"id_xxx",
		click:function(){
			alert("click");
		}
		}).appendTo("#xxx");*/
	var xxx = "<input type='checkbox' name='oid' value='xxx'>";
	alert(xxx);
	$(xxx).appendTo("#xxx");
	$("input[name='oid']").bind("click",function(){
		alert("yyy");
		alert($(this).val());
		});
	/*$("input[name='oid']").click(function(){
		alert("xxxx");
		});*/
	//alert($("#xxx").html());
	var yy = [{name:"1",value:"1"},{name:"2",value:"2"}];
	yy.push({name:"3",value:"3"});
	$.each(yy,function(i,d){
		alert(d.name+":"+d.value);
	});
	yy.splice(1,2);
	$.each(yy,function(i,d){
		alert(d.name+":"+d.value);
	});

	$( "#datepicker" ).datepicker( $.datepicker.regional[ "zh-CN" ] );
	
}

         
			);
	
	</script>
  </head>
  
  <body>
		<div id="all">
		<div id="logo">
		 
		</div>
		<div style="color: red;">
			<c:if test="${not empty param.login_error}">&nbsp;&nbsp;&nbsp;&nbsp;${msg}<br>
			</c:if>
		</div>
		<form name="myform" method="post" action='<c:url value="/j_spring_security_check" />'>
		<table id="from">
		<tr><td width="11%">用户名：</td><td width="12%"></td>
		<td width="22%"><input id="input_zh" name="j_username" type="text"  /></td>
		<td width="12%">密码</td>
		<td width="43%"><input id="input_mm" name="j_password" type="password" /></td></tr>
		</table>
		
		 <div id="checkp">
		  <p >
		   <input type="checkbox" name="_spring_security_remember_me" id="rememberme" />记住密码两周之内不必登陆
		   
		  </p></div>
		<div id="button">
		<input type="submit" value="登陆">
		</div>
</form>
		
		
		
		<div>
		
		
		</div>
		</div>
		<div id="xxx"></div>
		<input type='checkbox' name='e' value='"+iHTML+"' onclick='function(){alert("eee");}'>xxx
		<input name="xx" type="radio" value="1">xxx
		<input name="xx" type="radio" value="2">yyy
		<input type="text" id="datepicker"/>
	</body>
</html>
