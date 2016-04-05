<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<c:set var="title" value="系统资源维护" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>${title}</title>
	<%@ include file="/commons/public.inc"%>
	<script type="text/javascript">
	$(function(){
		loadData(true);
	});
	function loadData(isPagination,curPage,pageSize){
		var param = "";
		if(curPage){
			param += "&curPage="+curPage
		} else {
			curPage=1;
		}
		if(pageSize) param += "&pageSize="+pageSize;
		console.log("param:"+param);
		var url = CONTEXT_PATH+"/resource/list.do?moduleId=${param.moduleId}"+param;
		console.log("url:",url);
		$.post(url,function(result){
			console.log("result",result);
			var list = result.data.data;
			$("#dg").datagrid("loadData",list);
			if(!isPagination){
				return ;
			}
			var _total = result.data.total;
			var _pageSize = result.data.pageSize;
			$('#pp').pagination({
			    total:_total,
			    pageSize:_pageSize,
			    pageNumber:curPage,
			    onSelectPage:function(pageNumber, pageSize){
					$(this).pagination('loading');
					console.log('pageNumber:'+pageNumber+',pageSize:'+pageSize);
					loadData(false,pageNumber,pageSize);
					$(this).pagination('loaded');
				}
			});

		});
	}
	function append(){
		$('#dlg').dialog('open').dialog('center').dialog('setTitle','新增');
        $('#ff').form('clear');
        $('#moduleOid').val('${param.moduleId}');
	}
	function edit(){
		var row = $('#dg').datagrid('getSelected');
		 console.log("row:",row);
		 if(null == row){
			 $.messager.alert('提示','请选择一条记录','info');
			 return ;
		 }
		 var url = CONTEXT_PATH+"/resource/edit.do?id="+row.oid;
		 $.get(url,function(result){
			 var data = result.data.data;
			 console.log("edit row:",data);
			 $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改');
			 $('#ff').form('load',data);
			 $('#moduleOid').val(data.moduleOid);
		 });
	}
	function removeit(){
		 var row = $('#dg').datagrid('getSelected');
		 console.log("row:",row);
		 if(null == row){
			 $.messager.alert('提示','请选择一条记录','info');
			 return ;
		 }
		 $.messager.confirm('删除','你确定要删除吗？',function(r){
			 if(r){
				 var url = CONTEXT_PATH+"/resource/delete.do?id="+row.oid;
				 $.get(url,function(result){
					 var code = result.code;
					 var errMsg = result.errMsg;
					 var msg = "删除成功 ";
					 if(code != '0'){
						 msg += "删除失败："+errMsg;
					 } 
					 $.messager.alert('提示',msg,'info',function(){
						 var pageSize = $('#pp').pagination('pageSize');
			    		 loadData(true,1,pageSize);
						
					 });
				 });
			 }
		 });
	}
	function save(){
		$('#ff').form('submit', {
		    url:CONTEXT_PATH+"/resource/save.do",
		    onSubmit: function(){
		        // do some check
		        // return false to prevent submit;
		    },
		    success:function(data){
		    	console.log("save===》"+data);
		    	var data = eval('(' + data + ')');  // change the JSON string to javascript object
		    	var msg = "保存成功";
		    	if(data.code != '0'){
		    		msg = '保存失败：'+data.errMsg;
		    	}
		    	$.messager.alert('提示',msg,'info',function(){
		    		 $('#dlg').dialog('close');
		    		 var oid = $('#oid').val();
		    		 var pageSize = $('#pp').pagination('options').pageSize;
		    		 var pageNumber = 1;
		    		 if(oid){
		    			 pageNumber = $('#pp').pagination('options').pageNumber;
		    			 console.log('edit save:'+pageNumber);
		    		 }
		    		 loadData(true,pageNumber,pageSize);
		    	});
		    }
		});
	}
	</script>
  </head>
  
  <body>
  	<!-- list -->
    <table id="dg" title="资源列表" class="easyui-datagrid" style="width:100%;height:auto"
            url=""
            toolbar="#tb" pagination="false"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="rcode" width="50">资源编码</th>
                <th field="rname" width="50">资源名称</th>
                <th field="resString" width="50">资源URL</th>
                <th field="descn" width="50">资源描述</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
	</div>
    <!-- page -->
    <div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
    
    <!-- edit -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
	    <form id="ff" method="post">
	    	<input type="hidden" id="oid" name="oid"/>
	    	<input type="hidden" id="moduleOid" name="module.oid" />
	    	<table cellpadding="5">
	    		<tr>
	    			<td>资源编码:</td>
	    			<td><input class="easyui-textbox" type="text" name="rcode" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>资源名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="rname" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>资源URL:</td>
	    			<td><input class="easyui-textbox" type="text" name="resString" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>资源描述:</td>
	    			<td><input class="easyui-textbox" name="descn" data-options="multiline:true" style="height:60px"></input></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    
  </body>
</html>
