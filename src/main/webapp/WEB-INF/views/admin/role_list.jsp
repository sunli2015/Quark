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
			url:CONTEXT_PATH+"/role/list.do",
		});
		$('#btndel').click(function(){
			$('#dg').removeit({url:CONTEXT_PATH+"/role/delete.do"});//删除
		});
		$('#btnappend').click(function(){
			$('#dg').appendit();//初始化新增表单
		});
		$('#btnedit').click(function(){
			$('#dg').editit({//初始化编辑表单
				url: CONTEXT_PATH+"/role/edit.do"
			});
		});
		$('#btnsave').click(function(){
			$('#dg').saveit({//保存
				url: CONTEXT_PATH+"/role/save.do"
			});
		});
		
		$('#btngrant').click(function(){
			var row = $('#dg').datagrid('getSelected');
			console.log("row:",row);
			if(null == row){
				$.messager.alert('提示','请选择一条记录','info');
				return ;
			}
			$('#grant_roleid').val(row.oid);
			$('#ifr_resdialog').attr('src',CONTEXT_PATH+'/module/index.do?select=1');
			$('#resdialog').dialog('open');
		});
		
	});
	var grant = function(){
		var roleid = $('#grant_roleid').val();
		console.log('roleid',roleid);
		var checked = $('#ifr_resdialog').contents().find("#ifr_reslist").contents().find("#selectedlist").html();
		console.log('selectedlist:',checked);
		var url = CONTEXT_PATH+'/role/grant.do';
		$.post(url,{
			roleid:roleid,
			resourceIds:checked
		},function(result){
			console.log('result:',result);
			if(result.code == '0'){
				$.messager.alert('提示','保存成功','info',function(){
					$('#resdialog').dialog('close');
				});
			} else {
				$.messager.alert('提示','保存失败：'+result.errMsg,'info');
			}
			
		});
	}
	</script>
  </head>

  <body>
  	<!-- list -->
    <table id="dg" title="角色列表" class="easyui-datagrid" style="width:100%;height:auto"
            url=""
            toolbar="#tb" pagination="false"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="rolecode" width="50">角色编码</th>
                <th field="rolename" width="50">角色名称</th>
                <th field="descn" width="50">描述</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="btnappend">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btnedit">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="btndel">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btngrant">权限</a>
	</div>
    <!-- page -->
    <div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
    
    <!-- edit -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
	    <form id="ff" method="post">
	    	<input type="hidden" id="id" name="oid"/>
	    	<table cellpadding="5">
	    		<tr>
	    			<td>角色编码:</td>
	    			<td><input class="easyui-textbox" type="text" name="rolecode" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="rolename" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>描述:</td>
	    			<td><input class="easyui-textbox" name="descn" data-options="multiline:true" style="height:60px"></input></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" id="btnsave" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    
    
    <!-- grant -->
    <input id="grant_roleid" type="hidden"/>
    <div id="resdialog" class="easyui-dialog" style="width:650px;height:455px;padding-bottom: 0" closed="true"  data-options="title:'选择权限',modal:true,
			toolbar:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){grant();}
			},{
				text:'关闭',
				iconCls:'icon-close',
				handler:function(){$('#resdialog').dialog('close');}
			}]">
    <iframe id="ifr_resdialog" src="" scrolling="auto" frameborder="0" style="width:100%;height:385px;"></iframe>
	</div>
	
	
  </body>
</html>
