<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<c:set var="title" value="系统资源维护" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>${title}</title>
	<%@ include file="/common/meta.inc"%>
	<%@ include file="/common/bizbase_js.inc"%>
	<%@ include file="/common/ext_js.inc"%>
	<%@ include file="/common/ui_table_js.inc"%>
	<%@ include file="/common/ui_table_form_plugin_js.inc"%>
	<%@ include file="/common/validity_js.inc"%>
	<script type="text/javascript">
	$(function(){
		var _form = $("#form_div").form({
			tableId : "table_flex",//table的ID
			delUrl:"${ctx }/admin/resource!delete.action",
			editUrl:"${ctx }/admin/resource!edit.action",
			saveUrl:"${ctx }/admin/resource!save.action",
			formOpenInit : function(){
			$("#entity\\.module\\.oid").val('${moduId}');
			},
			valid : function(){
				var pass = 3;
				var is = $("#entity\\.rcode").require();
				if(is.reduction.length != 0){
					 pass-=1;
					is.removeClass("validity-erroneous");
				}
				is = $("#entity\\.rname").require();
				if(is.reduction.length != 0){ 
					pass-=1;
					is.removeClass("validity-erroneous");
				}
				is = $("#entity\\.resString").require();
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
			url:"${ctx }/admin/resource!list.action",
			title:'${title}',
			colModel : [
						{display: 'OID', name : 'oid', width : 15, sortable : true, align: 'center',hide:true},
						{display: '资源标识', name : 'rcode', width : 150, sortable : true, align: 'center'},
						{display: '资源名称', name : 'rname', width : 150, sortable : true, align: 'center'},
						{display: '资源', name : 'resString', width : 300, sortable : true, align: 'left'},
						{display: '描述', name : 'descn', width : 300, sortable : true, align: 'left'}
						],
			searchitems : [
						{display: '资源名称', name : 'entity.rolecode',type:"text"}
						],
			addbuttons:[],
			params:[{name:'moduId',value:'${moduId}'}]
		});
	});
	</script>
  </head>
  
  <body>
    <table id="table_flex" style="display:none"></table>

    <div id="form_div" title="编辑系统资源">
	<form>
	<fieldset id="form_edit">
	<input type="hidden" id="entity.oid" name="entity.oid"/>
	<input type="hidden" id="entity.module.oid" name="entity.module.oid" />
	<input type="hidden" id="oid" name="oid"/>
		<label for="entity.rcode">资源标识</label><span id="tips" style="color: red;"></span>
		<input type="text" name="entity.rcode" id="entity.rcode" class="text ui-widget-content ui-corner-all" />
		<label for="entity.rname">资源名称</label>
		<input type="text" name="entity.rname" id="entity.rname" class="text ui-widget-content ui-corner-all" />
		<label for="entity.resString">资源</label>
		<input type="text" name="entity.resString" id="entity.resString" value="" class="text ui-widget-content ui-corner-all" />
		<label for="entity.descn">角色描述</label><br/>
		<textarea rows="5" cols="55" name="entity.descn" id="entity.descn" class="text ui-widget-content ui-corner-all"></textarea>
	</fieldset>
	</form>
</div>
  </body>
</html>
