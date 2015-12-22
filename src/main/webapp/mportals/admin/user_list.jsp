<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<%@ include file="/common/meta.inc"%>
	<%@ include file="/common/bizbase_js.inc"%>
	<%@ include file="/common/ext_js.inc"%>
	<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.8.3.min.js"></script>
	<%@ include file="/common/ui_table_js.inc"%>
	<%@ include file="/common/ui_table_form_plugin_js.inc"%>
	<%@ include file="/common/validity_js.inc"%>
	<script src="${ctx}/dwr/interface/userManager.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		var _form = $("#form_div").form({
			tableId : "table_flex",//table的ID
			delUrl:"${ctx }/admin/user!delete.action",//删除URL
			editUrl:"${ctx }/admin/user!edit.action",//编辑URL
			saveUrl:"${ctx }/admin/user!save.action",//保存URL
			formOpenInit : function(){
				$("#entity\\.dept\\.oid").val('${deptId}');
			},
			valid : function(){
				$.validity.clear();
				$.validity.report = { errors:0, valid:true };
				//$.validity.report.errors = 0;
				$("#entity\\.logid").require();
				$("#entity\\.cname").require();
				
				var err = $.validity.report.errors
				return err == 0;
			}
		});
		$.fgtable.plugInForm = true;
		$.fgtable.form = _form;

		$("#table_flex").fgtable({
			url:"${ctx }/admin/user!list.action",
			title:"用户列表",
			colModel : [
						{display: 'OID', name : 'oid', width : 138, sortable : true, align: 'center',hide:true,key:true},
						{display: '用户名', name : 'logid', width : 138, sortable : true, align: 'center'},
						{display: '部门', name : 'dept.deptname', width : 138, sortable : true, align: 'left'},
						{display: '姓名', name : 'cname', width : 138, sortable : true, align: 'left'},
						{display: '工号', name : 'idno', width : 138, sortable : true, align: 'left'},
						{display: '手机号码', name : 'mobile', width : 138, sortable : true, align: 'left'},
						{display: '邮箱', name : 'email', width : 138, sortable : true, align: 'left'},
						{display: '状态', name : 'status', width : 50,sortable : true, align: 'center'},
						{display: '密码', name : 'oid', width : 50,sortable : true, align: 'center',type:'pwd'},
						{display: '权限', name : 'oid', width : 50,sortable : true, align: 'center',type:'role'}
						],
			searchitems : [
						{display: '姓名', name : 'entity.cname',type:"text"},
						{display: '用户名', name : 'entity.logid', type:"text"},
						{display: '工号', name : 'entity.idno',type:"text"},
						//{display: '状态', name : 'entity.status', type:"radio",option:{"1":"启用","0":"禁用"}},
						{display: '状态', name : 'entity.status',type:"select",option:{"":"请选择","1":"启用","0":"禁用"}}
						],
			datacol : {//数据中间件
				"status":function(primaryId ,model,col,iHTML){
					if(iHTML == '1') return "<img alt=\"禁用\" onclick=\"changeStatus('"+primaryId+"',0)\" style=\"cursor: pointer\" src=\"${ctx}/images/icon/CrystalClear/16/actions/dialog-apply.png\"/>";
					if(iHTML == '0') return "<img alt=\"激活\" onclick=\"changeStatus('"+primaryId+"',1)\" style=\"cursor: pointer\" src=\"${ctx}/images/icon/CrystalClear/16/actions/dialog-cancel.png\"/>";
					return iHTML; 
					},
				"oid":function(primaryId,model,col,iHTML){
						if(model.type=='pwd'){
							return "<img alt=\"修改密码\" id=\"pwd_"+iHTML+"\" onclick=\"showChangePwd('"+iHTML+"')\" style=\"cursor: pointer\" src=\"${ctx}/images/icon/CrystalClear/16/status/dialog-password.png\"/>";
							}
						if(model.type == 'role'){
							return "<img alt=\"修改权限\" id=\"role_"+iHTML+"\" onclick=\"selectRoles('"+iHTML+"')\" style=\"cursor: pointer\" src=\"${ctx}/images/icon/CrystalClear/16/actions/encrypt.png\"/>" 
							}
						return iHTML;
						}
					},
			params : [{name:'deptId',value:'${deptId}'}]});
	});
	function changeStatus(id,status){
		//alert(id+"="+status);
		var msg = "确定禁用该用户么？";
		if(status==1)
			msg = "确定激活该用户么？";
		Ext.MessageBox.confirm('提示', msg, function(btn){
		if(btn=='yes'){
			userManager.changeStatus(id,status,function(){
				$("#table_flex").flexReload();
			});
		}
		});
		
	}
	function showChangePwd(id){
		//alert(id);
		Ext.MessageBox.confirm('提示', "你确定要重置该用户的密码吗？", function(btn){
			if(btn=='yes'){
				userManager.changePwd(id,"",function(){
					alert("密码重置成功");
				});
			}
		});
	}
	var roledialog;
	function selectRoles(id){
		//alert(id);
		//var showBtn = Ext.get('role_'+oid);
		$('#useroid').val(id);
		$('#td-role').html("");
		if(!roledialog){
            roledialog = new Ext.Window({
					el:"role-dlg",
                    modal:true,
                    width:200,
                    height:430,
                    closeAction:'hide',
                    autoScroll:true,
              
                 buttons: [{
                  text:'保存',
                  handler: function(){
                  	changeRoles();
                  }
              },{
                  text: '关闭',
                  handler: function(){
                      roledialog.hide();
                  }
              }]
            });
	     }
		 roledialog.show();
		 userManager.getRoles(id,function(roles){
           	for(var i=0;i<roles.length;i++){
               	var _html = $('#td-role').html();
               	var _data = "<input id='role_"+roles[i][0]+"' type='checkbox' name='roles' value='"+roles[i][0]+"' "+roles[i][2]+"><label for='role_"+roles[i][0]+"'> "+roles[i][1]+"</label></br>";
           		$('#td-role').html(_html+_data);
           	}
		});
	}
	function changeRoles(){
		var setroles = new Array();
		var index = -1;
		var _roles = $("input[name='roles']:checked");
		_roles.each(function(){
			index++;
			setroles[index] = $(this).val();
		});
		var userid = $("#useroid").val();
		alert(index+"=>"+userid);
		userManager.saveRoles(userid,setroles,function(){
			Ext.MessageBox.alert('提示','修改成功，用户重登陆后权限生效！');
			roledialog.hide();
		});
		
		/*
		
		var roles=document.getElementsByName("roles");
		alert(roles
		for(var i=0;i<roles.length;i++){
			if(roles[i].checked){
				setroles[index] = roles[i].value;
				index++;
			}
		}
		*/
	}
	</script>
  </head>
  
  <body>
    <table id="table_flex" style="display:none"></table>
	
    <div id="form_div" title="编辑账户">
	<form id="editform">
	<fieldset id="form_edit">
	<input type="hidden" id="entity.oid" name="entity.oid"/>
	<input type="hidden" id="entity.dept.oid" name="entity.dept.oid" />
	<input type="hidden" id="oid" name="oid"/>
		<label for="logid">用户名</label>
		<input type="text" name="entity.logid" id="entity.logid" class="text ui-widget-content ui-corner-all" title="用户名"/>
		<label for="cname">姓名</label>
		<input type="text" name="entity.cname" id="entity.cname" value="" class="text ui-widget-content ui-corner-all" title="姓名"/>
		<label for="entity.idno">工号</label>
		<input type=text name="entity.idno" id="entity.idno" value="" class="text ui-widget-content ui-corner-all" title="工号"/>
		<label for="entity.mobile">手机号码</label>
		<input type="text" name="entity.mobile" id="entity.mobile" value="" class="text ui-widget-content ui-corner-all" title="手机号码"/>
		<label for="entity.email">邮箱</label>
		<input type="text" name="entity.email" id="entity.email" value="" class="text ui-widget-content ui-corner-all" title="邮箱"/>
		<label for="entity.status">状态</label>
		<select id="entity.status" name="entity.status" class="selectfont" title="状态">
			<option value="1" >启用</option>
			<option value="0" >禁用</option>
		</select>
		  
	</fieldset>
	</form>
</div>
<input type="hidden" id="useroid"/>
<div id="role-dlg" class="x-hidden">
	    <div class="x-window-header">修改用户对应权限</div>
	    <div class="x-window-body">
        	<div id="form-role">
				<table align="center" border="0" width="80%">
					<tr height="30">
					<td><div id="td-role"></div></td>
					</tr>
				</table>
		    </div>
	    </div>
	</div>
  </body>
</html>
