<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<%@ include file="/common/meta.inc"%>
	<%@ include file="/common/bizbase_js.inc"%>
	<%@ include file="/common/ext_js.inc"%>
	<%@ include file="/common/ui_table_js.inc"%>
	<%@ include file="/common/ui_table_form_plugin_js.inc"%>
	<%@ include file="/common/validity_js.inc"%>
	<script src="${ctx}/dwr/interface/roleManager.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		var _form = $("#form_div").form({
			tableId : "table_flex",//table的ID
			delUrl:"${ctx }/admin/role!delete.action",
			editUrl:"${ctx }/admin/role!edit.action",
			saveUrl:"${ctx }/admin/role!save.action",
			valid : function(){
				var pass = 2;
				var is = $("#entity\\.rolecode").require();
				if(is.reduction.length != 0){
					 pass-=1;
					is.removeClass("validity-erroneous");
				}
				is = $("#entity\\.rolename").require();
				if(is.reduction.length != 0){ 
					pass-=1;
					is.removeClass("validity-erroneous");
				}
				return pass==0;
			}
		});
		$.fgtable.plugInForm = true;
		$.fgtable.form = _form;

		$("#table_flex").fgtable({
			url:"${ctx }/admin/role!list.action",
			title:"角色列表",
			colModel : [
						{display: 'OID', name : 'oid', width : 15, sortable : true, align: 'center',hide:true},
						{display: '角色编码', name : 'rolecode', width : 150, sortable : true, align: 'center'},
						{display: '角色名称', name : 'rolename', width : 150, sortable : true, align: 'left'},
						{display: '角色描述', name : 'descn', width : 300, sortable : true, align: 'left'}
						],
			searchitems : [
							{display: '角色编码', name : 'entity.rolecode',type:"text"},
							{display: '角色名称', name : 'entity.rolename', type:"text"}
							],
			addbuttons:[
						{id:"AddResource",name : '资源',bclass : 'add',onpress : function(){
							var rows = $("#table_flex").selectedSimpleRows();
							if(rows.length == 0){
								alert("请选择一条操作记录");
								return ;
								};
							res(rows[0].oid);
						}}
						]
		});
	});

	function res(oid){
		roleid = oid;
		var param = {
			'sTitle'	:	'资料浏览',	//窗口标题
			'sOpenSrc'	:	"${ctx}/admin/resource!selectIndex.action?roleid="+oid,	//打开的链接
			'bModel'	:	true, 		//可选,默认为模式窗口,false为无模式窗口
			'sWidth'	:	'780',		//可选
			'sHeight'	:	'580',		//可选
			'bMaximizable'	:	true,
			'oCallback'	:	changeRes
			}
		CoralWin.open(param);
	}
	var changeRes = new coral.BaseCallBack();
	changeRes.after = function(){
		if(change){
			//alert(checked.length+"......"+notchecked.length);
			roleManager.changeRes(roleid,checked,notchecked,function(){
				Ext.MessageBox.alert("提示","修改成功！");
			});
		}
	}
	</script>
  </head>
  
  <body>
    <table id="table_flex" style="display:none"></table>

    <div id="form_div" title="编辑角色">
	<form>
	<fieldset id="form_edit">
	<input type="hidden" id="entity.oid" name="entity.oid"/>
	<input type="hidden" id="entity.dept.oid" name="entity.dept.oid" />
	<input type="hidden" id="oid" name="oid"/>
		<label for="entity.rolecode">角色编码</label>
		<input type="text" name="entity.rolecode" id="entity.rolecode" class="text ui-widget-content ui-corner-all" />
		<label for="entity.rolename">角色名称</label>
		<input type="text" name="entity.rolename" id="entity.rolename" value="" class="text ui-widget-content ui-corner-all" />
		<label for="entity.descn">角色描述</label><br/>
		<textarea rows="5" cols="55" name="entity.descn" id="entity.descn" class="text ui-widget-content ui-corner-all"></textarea>
	</fieldset>
	</form>
</div>
  </body>
</html>
