<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<c:set var="title" value="Dept Tree" />
<html>
<head>
<title>${title}</title>
<%@ include file="/common/meta.inc"%>
<script src="${ctx}/common2/prototype/prototype.js" type="text/javascript"></script>
<%@ include file="/common/ext_js.inc"%>
<%@ include file="/common/tree_js_1.inc"%>
<script src="${ctx}/dwr/interface/deptManager.js" type="text/javascript"></script>
<script type="text/javascript">
 var menu;
 var currentDeptid;
 var tree;

Ext.onReady(function(){
	var viewport = new Ext.Viewport({
            layout:'border',items:[
            {	region:'west',
                title:'部门(右击修改部门)',
                split:true,
                width: 200,
                minSize: 100,
                maxSize: 400,
                collapsible: true,
                contentEl:'application-tree',
                layoutConfig:{
                    animate:true
             }},{
             	region:'center',
                title:'',
                split:false,
                contentEl:'content'
             }]});
             	
             	tree=new dhtmlXTreeObject("application-tree","100%","100%",0);
             	//tree.setIconPath("${ctx}/common/tree/codebase/imgs/csh_books/");
				tree.setXMLAutoLoading("${ctx}/admin/userIndex!treeXml.action");
				tree.setOnClickHandler(onNodeSelect);
				//tree.attachEvent("onClick",onNodeSelect);
				tree.setOnRightClickHandler(onRightClick);
				//tree.attachEvent("onRightClick",onRightClick);
				tree.loadXML("${ctx}/admin/userIndex!treeXml.action");
});
function onNodeSelect(id){
	 Ext.get('application-info-iframe').dom.src='${ctx}/admin/user!index.action?deptId='+id;
}
function onRightClick(id){
	tree.selectItem(id,true);
	//alert(tree.event);
	currentDeptid = id;
	var _items = [
		          	'-',
		          	'部门：'+tree.getItemText(currentDeptid),
		          	'-',
	    			new Ext.menu.Item({id: 'update',text: '修改',handler: clickHandler})
		   		 ];
	if(currentDeptid != '1'){
		_items.push(new Ext.menu.Item({id:	'del',text: '删除',handler: clickHandler}));
	}
	_items.push(new Ext.menu.Item({id:	'add',text: '添加子部门',handler: clickHandler}));
	menu = new Ext.menu.Menu({
    				id: 'basicMenu',
			    	items: _items
					});
		try{
			menu.showAt([Event.pointerX(event),Event.pointerY(event)]); 
	      } catch(e){
	  		menu.showAt([100,100]);
	      }
}
function clickHandler(item){
	switch(item.id){
		case "del":
			if(currentDeptid == '1'){
				Ext.MessageBox.alert('错误提示','根部门:'+tree.getItemText(currentDeptid)+'不能删除！');
				return ;
			}
			Ext.MessageBox.confirm('提示', '您确定删除该部门\''+tree.getItemText(currentDeptid)+'\'么?', function(btn){
				if(btn == 'no'){
					return ;
					}
				deptManager.canDel(currentDeptid,function(rt){
					if(rt == true){
						deptManager.deleteDept(currentDeptid,function(){
							Ext.MessageBox.alert('提示', "删除成功！", function(){
								window.location.reload();
							});
							});
					}else{
						Ext.MessageBox.alert('错误提示','部门下有子部门或人员，不能删除！');
					}
				});
				
				});
			
			break;
		case "update":
			Ext.get('application-info-iframe').dom.src='${ctx}/admin/dept!edit.action?oid='+currentDeptid;
			break;
		case "add":
			Ext.get('application-info-iframe').dom.src='${ctx}/admin/dept!edit.action?poid='+currentDeptid;
			break;
		default:
		;
	}
}

</script>
</head>

<body>
	<div id="application-tree"></div>
	<div id="content" style="text-align: left;">
			<iframe id="application-info-iframe" src="" width="100%"
				height="100%" style="border:0px none;"></iframe>
	</div>
</body>
</html>

