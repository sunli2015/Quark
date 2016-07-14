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
			url:CONTEXT_PATH+"/user/list.do?deptId=${param.deptId}",
		});
		
		$('#btndel').click(function(){
			$('#dg').removeit({url:CONTEXT_PATH+"/user/delete.do"});//删除
		});
		$('#btnappend').click(function(){
			$('#dg').appendit(function(){
					$('#deptOid').val('${param.deptId}');
					$("#logid").textbox({
						readonly:false
					});
					
					$('#cc').combobox('setValue', '1');
				}
			);//初始化新增表单
		});
		$('#btnedit').click(function(){
			$('#dg').editit({//初始化编辑表单
				url: CONTEXT_PATH+"/user/edit.do",
				fnSetMultiParam:function(data){
					$('#deptOid').val(data.deptOid);
					$("#logid").textbox({
						readonly:true
					});
					if(data.status==1){
						$('#cc').combobox('setValue', '1');
					} else {
						$('#cc').combobox('setValue', '0');
					}
				}
			});
		});
		$('#btnsave').click(function(){
			$('#dg').saveit({//保存
				url: CONTEXT_PATH+"/user/save.do",
				fnOnSubmit:function(){
					var isvalid = $("#ff").form('enableValidation').form('validate');
					return isvalid;
				}
			});
		});
		<coral:auth res="admin_role">
		$('#btngrant').click(function(){
			var row = $('#dg').datagrid('getSelected');
			console.log("row:",row);
			if(null == row){
				$.messager.alert('提示','请选择一条记录','info');
				return ;
			}

			$("#grant_userid").val(row.oid);
			var url = CONTEXT_PATH+'/role/listByUserId.do?userid='+row.oid;
			var tree= new Array();
			$.post(url,{},function(result){
				var list = result.data.data;
				console.log("rolelist:",list);
				$.each(list,function(i,d){
					tree.push({'id':d.oid,'text':d.rolename,'checked':d.checked});
				});
				console.log("tree:"+tree);
				$('#tt').tree({
				    data:tree
				});
				$('#roledialog').dialog('open');
			});
		});
		</coral:auth>
		$("#search").click(function(){
			var qname = $("#search_name").val();
			$("#dg").loadData({
				url:CONTEXT_PATH+"/user/list.do?deptId=${param.deptId}",
				qparam:{'name':qname}
			});
		});
	});
	<coral:auth res="admin_role">
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
	};
	</coral:auth>
	function formatOper(val,row,index){
		if(val == 1){
			return '<a href="#" onclick="modstatus(\''+row.oid+'\',0);">禁用</a>';
		} 
		return '<a href="#" onclick="modstatus(\''+row.oid+'\',1);">启用</a>';
	}
	function modstatus(oid,status){
		var msg = "禁用";
		if(status == 1){
			msg = "启用";
		}
		$.messager.confirm('提示','你确定要'+msg+'吗？',function(r){
			 if(r){
				 var url = CONTEXT_PATH+'/user/modifystatus.do';
					$.post(url,{
						oid:oid,
						status:status
					},function(result){
						console.log('result:',result);
						if(result.code == '0'){
							$.messager.alert('提示','修改成功','info',function(){
								var qname = $("#search_name").val();
								$('#dg').reloadData({
									qparam:{'name':qname}
								});
							});
						} else {
							$.messager.alert('提示','修改失败：'+result.errMsg,'info');
						}
						
					});
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
                <th field="idno" width="15%">工号</th>
                <th field="mobile" width="15%">手机号码</th>
                <th field="email" width="15%">邮箱</th>
                <th field="status" width="10%" data-options="formatter:formatOper">操作</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="height:auto">
    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="btnappend">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btnedit">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="btndel">删除</a>
		<coral:auth res="admin_role"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="btngrant">权限</a></coral:auth>
		<div style="padding-left: 5px;">
		姓名: <input class="easyui-textbox" style="width:110px" id="search_name">
		<a href="#" class="easyui-linkbutton" id="search" iconCls="icon-search">查询</a>
		</div>
	</div>

    <!-- edit -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
	    <form id="ff" method="post">
	    	<input type="hidden" id="id" name="oid"/>
	    	<input type="hidden" id="deptOid" name="dept.oid" />
	    	<table cellpadding="5">
	    		<tr>
	    			<td>登陆号:</td>
	    			<td><input class="easyui-textbox" type="text" id="logid" name="logid" data-options="required:true"></input></td>
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
	    		<tr>
	    			<td>状态:</td>
	    			<td><input class="easyui-combobox" id="cc" name="status" 
	    			data-options="panelHeight:40,valueField:'lable',textField:'value',data:[{lable:'1',value:'启用'},{lable:'0',value:'禁用'}]"/></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" id="btnsave" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    <coral:auth res="admin_role">
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
	</coral:auth>
  </body>
</html>
