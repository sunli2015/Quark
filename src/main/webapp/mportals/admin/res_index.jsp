<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<c:set var="title" value="Tree" />
<html>
<head>
<title>${title}</title>
<%@ include file="/common/meta.inc"%>
<script src="${ctx}/common2/prototype/prototype.js" type="text/javascript"></script>
<%@ include file="/common/ext_js.inc"%>
<%@ include file="/common/tree_js_1.inc"%>
<script src="${ctx}/dwr/interface/ModuManager.js" type="text/javascript"></script>
<script type="text/javascript">

 var menu;
 var currentDeptid;
 var tree;

Ext.onReady(function(){
	var viewport = new Ext.Viewport({
            layout:'border',items:[
            {	region:'west',
                title:'模块(右击修改)',
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
				tree.setXMLAutoLoading("${ctx}/admin/resourceIndex!treeXml.action");
				tree.setOnClickHandler(onNodeSelect);
				tree.setOnRightClickHandler(onRightClick);
				tree.loadXML("${ctx}/admin/resourceIndex!treeXml.action");
});
function onNodeSelect(id){
	 Ext.get('application-info-iframe').dom.src='${ctx}/admin/resource!index.action?moduId='+id;
}
function onRightClick(id){
	tree.selectItem(id,true);
	currentDeptid = id;
	menu = new Ext.menu.Menu({
    				id: 'basicMenu',
			    	items: ['-','模块：'+tree.getItemText(currentDeptid),'-',
			    			new Ext.menu.Item({
				            id: 'update',
				            text: '修改',
				            handler: clickHandler
					        }),new Ext.menu.Item({
					        id:	'del',
					        text: '删除',
					        handler: clickHandler
					        }),new Ext.menu.Item({
					        id:	'add',
					        text: '添加子模块',
					        handler: clickHandler
					        })
			   		 		]
					});
		try{
			menu.showAt([Event.pointerX(event),Event.pointerY(event)]);  
	      }catch(e){
	  		menu.showAt([100,100]);
	      }
}
function clickHandler(item){
	switch(item.id){
		case "del":
			Ext.MessageBox.confirm('提示', '您确定删除该模块\''+tree.getItemText(currentDeptid)+'\'么?', function(btn){
				if(btn=='yes'){
					ModuManager.deleteNode(currentDeptid,function(rt){
						Ext.MessageBox.alert('提示', rt, function(){
							window.location.reload();
						});
						});
				}
			});
			break;
		case "update":
			Ext.get('application-info-iframe').dom.src='${ctx}/admin/modu!edit.action?oid='+currentDeptid;
			break;
		case "add":
			Ext.get('application-info-iframe').dom.src='${ctx}/admin/modu!edit.action?poid='+currentDeptid;
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

