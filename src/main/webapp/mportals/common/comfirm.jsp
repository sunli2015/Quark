<%@ page contentType="text/html; charset=UTF-8"%>
<div id="dlg" class="easyui-dialog" title="注销" style="width:300px;height:150px;padding:10px;"
		data-options="
			iconCls: 'icon-save',
			buttons: '#dlg-buttons'
		">
	您确定要注销吗？
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:alert('save')">确定</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>