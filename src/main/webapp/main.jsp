<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@ include file="/common/tree_js.inc"%>
	<%@ include file="/common/ext_js.inc"%>
	<script type="text/javascript">
	var contentPanel = null;
	var oViewport;
	Ext.onReady(function(){
		//页面超时
		//_timing = setTimeout('timing()', 1000);

		//Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

		//center panel
	    contentPanel = new Ext.TabPanel({
	  			id:'doc-body',
	              region:'center',
	              deferredRender:false,
	              enableTabScroll:true,
	              wheelIncrement:80,
	              activeTab:0,
	               items:[{
	               	 id: 'mainTab', 
	                 contentEl:'content',
	                 title:'当前登陆用户：${loginer.cname}',
	                 closable:false,
	                 autoScroll:true
	              } ]
	          });

		var viewport = new Ext.Viewport({
	            layout:'border',items:[
	            {	region:'west',
	                title:'功能菜单',
	                split:true,
	                width: 150,
	                minSize: 100,
	                maxSize: 400,
	                collapsible: true,
	                contentEl:'application-tree',
	                layoutConfig:{
	                    animate:true
	             }},{	
	             	region:'north',
	                split: true,
	                initialSize: 45,
	                minSize: 45,
	                maxSize: 45,
	                collapsible: true,
	                contentEl:'header'
	             },
	             contentPanel
	             ]
			});
	             
	    oViewport = viewport;
	          
	    tree=new dhtmlXTreeObject("application-tree","100%","100%",0);
		tree.loadXML("${ctx}/portals/common/menuxml.jsp");
		tree.setOnClickHandler(onNodeSelect);
		Ext.get('application-info-iframe').dom.src='${ctx}/cms/portal!list.do';
		

		Ext.get('mystatus').on('click', showallmsg);

	});
	</script>
  </head>
  
  <body>
    	<div id="application-tree"></div>
		<div id="content" style="text-align:center">
			<iframe id="application-info-iframe" src="" width="100%" frameborder="0"
				height="100%" style="border:0px none;"></iframe>
		</div>
  </body>
</html>
