<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.inc"%>
<%@ include file="/common/public.inc"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="title" value="莱芜一卡通业务系统" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${title }</title>
    
	<%@ include file="/common/meta.inc"%>
	<script src="${ctx}/common2/prototype/prototype.js" type="text/javascript"></script>
	<%@ include file="/common/tree_js.inc"%>
	<%@ include file="/common/ext_js.inc"%>
	<script src="${ctx}/mportals/common/menu.js" type="text/javascript"></script>
	<style type="text/css">
	#header{
	    background: url(${ctx}/mportals/common/images/header-bar.gif) repeat-x bottom;
	    padding:5px 4px;
	    text-align:left;
	    width: 100%;
	}
	
	/*主页面标题*/
	#main-page-title{
		FILTER: Glow(Color=#000000,Strength=3);
		font-size:30px;
		color:#FFFFFF;
		margin-left:38; 
		letter-spacing:5; 
		font-weight:bold;
		text-align:left;
		width: 400px;
	}
	#mystatus{
		width:30px;
		height: 22px;
	}
 	#application-tree{
 		text-align: left;
 	}
 	</style>
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
	    //tree.setImagePath("../../codebase/imgs/csh_bluebooks/");
	    tree.setIconPath("${ctx}/common/tree/codebase/imgs/csh_books/");
		tree.loadXML("${ctx}/mportals/common/menuxml.jsp");
		tree.setOnClickHandler(onNodeSelect);
		//tree.attachEvent("onClick",onNodeSelect(nodeId));
		Ext.get('application-info-iframe').dom.src='${ctx}/index1.jsp';

	});
	function logout(btn){
		if(btn=='yes')
			document.location.href = '${ctx}/j_spring_security_logout';
	}
	function addTabFrame(atitle, aurl, aframe) {  //alert('aurl:::'+aurl);
		if(contentPanel.items.length>10){
			coral.showInfoMsg({'title':'提示','msg':'标签页过多,请先关闭部分标签页!'});
			return;
		}
		if($(aframe)){
			contentPanel.setActiveTab(atitle);
			return;
		}
		var contentHtmlOng = "<iframe id=\"$[iframeId]\" src=\"$[openSrc]\" frameborder=\"0\" scrolling=\"auto\" style=\"height:100%;width:100%;border:0px none;\"></iframe>";
		openurl = aurl;
		var srframe = aframe;// + "-" + indx;
		var contentHtml = contentHtmlOng;
		contentHtml = contentHtml.replace("$[iframeId]", srframe);
		contentHtml = contentHtml.replace("$[openSrc]", openurl);
		var tabp = contentPanel.add({id:atitle, title:atitle, iconCls:"tabs", html:contentHtml, closable:true}).show();
		contentPanel.setActiveTab(tabp);
		//contentPanel.setActiveTab(0); 
		//Ext.get(srframe).dom.src = openurl;
		//indx++;
	}
	function updateTabFrame(atitle, aurl, aframe, refresh){
		/*
		if(contentPanel.getItem(atitle)){
			contentPanel.remove(atitle);
		}
		addTabFrame(atitle, aurl, aframe);
		*/
		if(contentPanel.getItem(atitle)){
			if(refresh)
				Ext.get(aframe).dom.src = aurl;
			contentPanel.setActiveTab(atitle);
		}else{
			addTabFrame(atitle, aurl, aframe);
		}
	}
	</script>
  </head>
  
  <body>
  	<div id="header">
  	<table width="100%"><tr><td align="left">
				<div align="right" id="mystatus"><img id="msgStatus" src='${ctx}/images/online.png' width='33' height='33' border='0' alt='消息查看！'/></div>
				</td><td align="left">
				<div id="main-page-title" style="font-family:黑体;">
					<s:url id="pwdUrl" action="pwd" namespace="/admin" />
					<s:a href="%{pwdUrl}">${title}</s:a>
				</div>
				</td>
				<td><%-- <a href="javascript:testVip()">VIP</a>--%></td>
				<td style="width: 80%;" align="right" valign="bottom">
				<div style="float :right;width: 100%;">
				<marquee behavior="scroll" scrollamount="4" direction="left" onmouseover="this.stop()" onmouseout="this.start()" id="scollContent" style="background-color: rgba(200, 54, 54, 0.5);"></marquee>
				</div>
				</td>
				</tr></table>
  	</div>
    	<div id="application-tree"></div>
		<div id="content" style="text-align:center">
			<iframe id="application-info-iframe" src="" width="100%" frameborder="0"
				height="100%" style="border:0px none;"></iframe>
		</div>
  </body>
</html>
