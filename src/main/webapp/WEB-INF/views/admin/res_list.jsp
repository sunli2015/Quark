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
		if(curPage) param += "&curPage="+curPage;
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
		
	}
	function edit(){
		
	}
	function removeit(){
		 var row = $('#dg').datagrid('getSelected');
		 console.log("row:",row);
	}
	</script>
  </head>
  
  <body>
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
    
    <div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
    
  </body>
</html>
