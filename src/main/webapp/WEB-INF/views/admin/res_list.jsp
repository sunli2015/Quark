<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<c:set var="title" value="系统资源维护" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>${title}</title>
	<%@ include file="/commons/public.inc"%>
	<script type="text/javascript" src="${ctx}/commons/crud.js"></script>
	<script type="text/javascript">
	$(function(){
		$('#dg').loadData({//加载表格数据
			isPagination:true,
			url:CONTEXT_PATH+"/resource/list.do?moduleId=${param.moduleId}",
			fnCallback:function(){
				loadSelected();
			}
		});
		
		<c:choose>
			<c:when test='${param.select==1 }'>
			var selectedlist = new Array();
			
			function loadSelected(){
				var url = CONTEXT_PATH+"/resource/queryByRoleId.do?roleId=${param.roleId}";
				$.post(url,null,function(result){
					console.log("result:",result);
					var list = result.data.data;
					$.each(list,function(i,d){
						var index = $('#dg').datagrid("getRowIndex",d.oid);
						//alert("oid:"+d.oid+"=>index:"+index);
						if(index != -1)
						$('#dg').datagrid("checkRow",index);
					});
					displaySelectedStr();
				}
				);
			}
			function displaySelectedStr(){
				var str = '';
				var id = '';
				$.each(selectedlist,function(i,d){
					str += d.rname+',';
					id += d.oid+',';
				});
				if(str != ''){
					str = str.substring(0,str.length-1);
					id = id.substring(0,id.length -1);
				} 
				
				$("#selectedResStr").html(str);
				$('#selectedlist').html(id);
				return ;
			}
			$('#dg').datagrid({
				onCheck:function(i,row){
					selectedlist.push(row);
					displaySelectedStr();
				},
				onUncheck:function(i,row){
					//console.log('row:'+i+"->",row);
					$.each(selectedlist,function(i,data){
						//console.log('onUncheck data:'+i,data);
						if(row.oid == data.oid){
							selectedlist.splice(i,1);
							return false;
						}
					});
					displaySelectedStr();
				},
				onSelectAll:function(rows){
					_handle(rows,function(index,row){
						if(index == -1){
							selectedlist.push(row);
						}
					});
					displaySelectedStr();
				},
				onUnselectAll:function(rows){
					_handle(rows,function(index,row){
						if(index != -1){
							selectedlist.splice(index,1);
						}
					});
					displaySelectedStr();
				}
			});
			
			function _handle(rows,fnCallback){
				$.each(rows,function(i,row){
					var index = -1;
					$.each(selectedlist,function(i,data){
						//console.log('onUncheck data:'+i,data);
						if(row.oid == data.oid){
							index = i;
							return false;
						}
					});
					if(fnCallback) fnCallback(index,row);
				});
			}
			
			$('#btnsave').click(function(){
				var chklist = $('#dg').datagrid("getChecked");
				
				console.log('row id:${param.roleid }');
			});
			
			</c:when>
			<c:otherwise>
			$('#btndel').click(function(){
				$('#dg').removeit({url:CONTEXT_PATH+"/resource/delete.do"});//删除
			});
			$('#btnappend').click(function(){
				$('#dg').appendit(function(){
						$('#moduleOid').val('${param.moduleId}');
					}
				);//初始化新增表单
			});
			$('#btnedit').click(function(){
				$('#dg').editit({//初始化编辑表单
					url: CONTEXT_PATH+"/resource/edit.do",
					fnSetMultiParam:function(data){
						$('#moduleOid').val(data.moduleOid);
					}
				});
			});
			$('#btnsave').click(function(){
				$('#dg').saveit({//保存
					url: CONTEXT_PATH+"/resource/save.do"
				});
			});
			</c:otherwise>
		</c:choose>
	});
	</script>
  </head>
  
  <body>
  	<c:set var="singleSelect" value="true"></c:set>
  	<c:if test="${param.select==1 }">
  	
  	<span id="selectedlist" style="display:none"></span>
  	<c:set var="singleSelect" value="false"></c:set>
  	</c:if>
  	
  	
  	<!-- list -->
    <table id="dg" title="资源列表" class="easyui-datagrid" style="width:100%;height:auto"
			toolbar="#tb" pagination="true" idField="oid"
            rownumbers="true" fitColumns="true" singleSelect="${singleSelect }">
        <thead>
            <tr>
            	<c:choose>
	    		<c:when test="${param.select==1 }">
	    		<th field="oid" data-options="checkbox:true" width="10%"></th>
	    		<th field="rcode" width="40%">资源编码</th>
                <th field="rname" width="50%">资源名称</th>
	    		</c:when>
	    		<c:otherwise>
	    		<th field="rcode" width="20%">资源编码</th>
                <th field="rname" width="20%">资源名称</th>
                <th field="resString" width="40%">资源URL</th>
                <th field="descn" width="20%">资源描述</th>
	    		</c:otherwise>
	    		</c:choose>
            </tr>
        </thead>
    </table>
    <div id="tb" style="height:auto">
    	<c:choose>
    		<c:when test="${param.select==1 }">
    		<a href="javascript:void(0)" class="easyui-linkbutton">所选资源：</a>
    		<span id="selectedResStr" style="width:100%; padding: 5px;"></span>
    		</c:when>
    		<c:otherwise>
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="btnappend">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btnedit">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="btndel">删除</a>
    		</c:otherwise>
    	</c:choose>
		
	</div>
    
    <c:if test="${param.select!=1 }">
    <!-- edit -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
	    <form id="ff" method="post">
	    	<input type="hidden" id="id" name="oid"/>
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
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" id="btnsave" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    </c:if>
  </body>
</html>
