<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<c:set var="title" value="Tree" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<%@ include file="/commons/public.inc"%>
<script type="text/javascript" src="${ctx}/commons/crud.js"></script>
<script type="text/javascript">
$(function(){
	loadTreeData();

	$('#btnsave').click(function(){
		$('#region_center').saveit({//保存
			url: CONTEXT_PATH+"/module/save.do",
			fnOnSubmit:function(){
				var isvalid = $("#ff").form('enableValidation').form('validate');
				return isvalid;
			},
			fnCallback:function(obj){
				loadTreeData();
			}
		});
	});
	
});


function buildtree(r,d){
	var ch = d.subModule;
	if(ch.length == 0){
		return ;
	}
	$.each(ch,function(i,d){
		var _r = {'id':d.oid,'text':d.moduleName,'children':[]};
		r.children.push(_r);
		buildtree(_r,d);
	});
	return ;
}

function loadTreeData(){
	var url = CONTEXT_PATH+'/module/tree.do';
	var tree= new Array();
	
	$.post(url,{},function(result){
		var list = result.data.data;

		$.each(list,function(i,d){
			var r = {'id':d.oid,'text':d.moduleName,'children':[]};
			tree.push(r);
			buildtree(r,d);
		});
		$('#funtree').tree({
			data:tree,
			onClick: function(node){
				var id = node.id;
				showlist(id);
			},
		    onContextMenu: function(e, node){
				e.preventDefault();
				// select the node
				$('#funtree').tree('select', node.target);
				var obj = $('#funtree').tree('getSelected');
				if(obj.id == '1'){
					$("#mm_del").hide();
				} else {
					$("#mm_del").show();
				}
				//console.log("obj:",obj);
				// display context menu
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
		});
		showlist(1);
	});
}
function showlist(id){
	var height = $("#region_center").height();
	console.log("height:",height);
	var url = CONTEXT_PATH+"/resource/index.do?moduleId="+id+"&select=${param.select}&roleId=${param.roleId}";
	
	$("#region_center").empty();
    var content = '<iframe id="ifr_reslist" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;"></iframe>';
    $("#region_center").append(content);
    $('#ifr_reslist').height(height);
}
function append(){
	$('#region_center').appendit(function(){
		var obj = $('#funtree').tree('getSelected');
		$("#parentModuleOid").val(obj.id);
		
	});//初始化新增表单
}
function remove1(){
	var obj = $('#funtree').tree('getSelected');
	console.log(obj);
	$('#region_center').removeit({
			url:CONTEXT_PATH+"/module/delete.do?id="+obj.id,
			datagrid:false,
			fnCallback:function(){
				loadTreeData();
			}});//删除
}
function edit(){
	var obj = $('#funtree').tree('getSelected');
	$('#region_center').editit({//初始化编辑表单
		url: CONTEXT_PATH+"/module/edit.do?id="+obj.id,
		datagrid:false,
		fnSetMultiParam:function(data){
			$('#parentDeptOid').val(data.parentDeptOid);
		}
	});
}

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'west',split:true" title="模块功能菜单" style="width:25%;">
		<ul id="funtree" class="easyui-tree"></ul>
	</div>
	<div id="region_center" data-options="region:'center'"></div>
	
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
		<div onclick="edit()" data-options="iconCls:'icon-edit'">修改</div>
		<div onclick="remove1()" id="mm_del" data-options="iconCls:'icon-remove'">删除</div>
	</div>
	
	<!-- edit -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
	    <form id="ff" method="post">
	    	<input type="hidden" id="id" name="oid"/>
	    	<input type="hidden" id="parentModuleOid" name="parentModule.oid" />
	    	<table cellpadding="5">
	    		<tr>
	    			<td>模块名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="moduleName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>实体类名:</td>
	    			<td><input class="easyui-textbox" type="text" name="entityClassName" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" id="btnsave" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
</body>
</html>

