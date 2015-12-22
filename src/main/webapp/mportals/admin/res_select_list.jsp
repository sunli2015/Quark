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
	<style type="text/css">
	.flexigrid div.fbutton .add {
		background: url(${ctx}/js/Flexigrid/images/add.png) no-repeat center left;
	}
	.flexigrid div.fbutton .delete {
	    background: url(${ctx}/js/Flexigrid/images/close.png) no-repeat center left;
	}
	</style>
	<script type="text/javascript">
	$(function(){
		$("#table_flex").fgtable({
			url:"${ctx }/admin/resource!select.action",
			title:"资源列表",
			colModel : [
						{display: 'OID', name : 'oid', width : 15, sortable : true, align: 'center',type:'checkbox'},
						{display: '资源标识', name : 'rcode', width : 150, sortable : true, align: 'left'},
						{display: '资源名称', name : 'rname', width : 150, sortable : true, align: 'center'},
						{display: '资源', name : 'resString', width : 300, sortable : true, align: 'left'},
						{display: '资源描述', name : 'descn', width : 300, sortable : true, align: 'left'},
						{display: '选中', name : 'selected', width : 300, sortable : true, align: 'left',hide:true}
						],
			searchitems : [
						{display: '资源名称', name : 'entity.rname',type:"text"}
						],
			singleSelect:false,
			dataEvent:{
					"selected":function(tr,val){
					if(val == 'true'){
						$(tr).trigger("click");
						}
					}
				},
			params:[{name:'roleid',value:'${roleid}'}],
			buttons:[
						{id:"ResSelect",name : '资源选择',bclass : 'add',onpress : pressHandle},
						{id:"Cancel",name : '取消',bclass : 'delete',onpress : pressHandle}
					]
		});
	});
	function pressHandle(com,grid){
		if (com == 'ResSelect') {
			select();
		} else if(com == 'Cancel'){
			cancel();
		}
		}
	var checked = [];
	var notchecked = [];
	function select(){
		var _checked = $("#table_flex").selectedSimpleRows();
		var _notchecked = $("#table_flex").notSelectedSimpleRows();
		$.each(_checked,function(i,row){
			checked.push(row.oid);
			});
		$.each(_notchecked,function(i,row){
			notchecked.push(row.oid);
			});
		window.parent.change = true;
		window.parent.checked = checked;
		window.parent.notchecked = notchecked;
		window.parent.CoralWin.close();
		
	}
	function cancel(){
		window.parent.change = false;
		window.parent.CoralWin.close();
	}
	</script>
  </head>
  
  <body>
    <table id="table_flex" style="display:none"></table>
  </body>
</html>
