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
			url:CONTEXT_PATH+"/user/list.do?deptId=${param.deptId}",
		});
		
		$('#btndel').click(function(){
			$('#dg').removeit({url:CONTEXT_PATH+"/user/delete.do"});//删除
		});
		$('#btnappend').click(function(){
			$('#dg').appendit(function(){
					$('#deptOid').val('${param.deptId}');
				}
			);//初始化新增表单
		});
		$('#btnedit').click(function(){
			$('#dg').editit({//初始化编辑表单
				url: CONTEXT_PATH+"/user/edit.do",
				fnSetMultiParam:function(data){
					$('#deptOid').val(data.deptOid);
				}
			});
		});
		$('#btnsave').click(function(){
			$('#dg').saveit({//保存
				url: CONTEXT_PATH+"/user/save.do"
			});
		});
		$('#btngrant').click(function(){
			var row = $('#dg').datagrid('getSelected');
			console.log("row:",row);
			if(null == row){
				$.messager.alert('提示','请选择一条记录','info');
				return ;
			}
			
			
			
			$("#grant_userid").val(row.oid);
			var url = CONTEXT_PATH+'/role/list.do';
			var tree= new Array();
			$.post(url,{},function(result){
				var list = result.data.data;
				console.log("rolelist:",list);
				$.each(list,function(i,d){
					tree.push({'id':d.oid,'text':d.rolename});
				});
				console.log("tree:"+tree);
				$('#tt').tree({
				    data:tree
				});
				$('#roledialog').dialog('open');
			});
			
			

		});
	});
	var grant = function(){
		var userid = $('#grant_userid').val();
		console.log('userid',userid);
		var nodes = $('#tt').tree('getChecked');
		var roleids = "";
		$.each(nodes,function(i,d){
			roleids += d.id+",";
		});
		
		var url = CONTEXT_PATH+'/user/grant.do';
		$.post(url,{
			userid:userid,
			roleids:roleids
		},function(result){
			console.log('result:',result);
			if(result.code == '0'){
				$.messager.alert('提示','保存成功','info',function(){
					$('#roledialog').dialog('close');
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
    <table id="dg" title="用户列表" class="easyui-datagrid" style="width:100%;height:auto"
			toolbar="#tb" pagination="true" idField="oid"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
	    		<th field="logid" width="20%">登陆号</th>
                <th field="cname" width="20%">姓名</th>
                <th field="idno" width="10%">工号</th>
                <th field="mobile" width="20%">手机号码</th>
                <th field="email" width="20%">邮箱</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="height:auto">
    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="btnappend">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btnedit">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="btndel">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btngrant">权限</a>
	</div>
    
    <!-- edit -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
	    <form id="ff" method="post">
	    	<input type="hidden" id="id" name="oid"/>
	    	<input type="hidden" id="deptOid" name="dept.oid" />
	    	<table cellpadding="5">
	    		<tr>
	    			<td>登陆号:</td>
	    			<td><input class="easyui-textbox" type="text" name="logid" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td><input class="easyui-textbox" type="text" name="cname" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>工号:</td>
	    			<td><input class="easyui-textbox" type="text" name="idno" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>电话号码:</td>
	    			<td><input class="easyui-textbox" type="text" name="mobile"></input></td>
	    		</tr>
	    		<tr>
	    			<td>邮箱:</td>
	    			<td><input class="easyui-textbox" type="text" name="email"></input></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" id="btnsave" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    
    <!-- grant -->
    <input id="grant_userid" type="hidden"/>
    <div id="roledialog" class="easyui-dialog" style="width:390px;height:355px;padding-bottom: 0" closed="true"  data-options="title:'选择权限',modal:true,
			toolbar:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){grant();}
			},{
				text:'关闭',
				iconCls:'icon-close',
				handler:function(){$('#roledialog').dialog('close');}
			}]">
    <ul id="tt" class="easyui-tree" data-options="animate:true,checkbox:true,cascadeCheck:true"></ul>
	</div>
	
  </body>
</html>
