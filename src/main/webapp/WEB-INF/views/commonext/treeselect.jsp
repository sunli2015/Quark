<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta charset="utf-8">
<meta content="webkit" name="renderer">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache"><meta http-equiv="Expires" content="0">
<meta content="width=device-width, initial-scale=1, user-scalable=1" name="viewport">
<meta content="e-Hualu 易华录  - Powered By JeeSite" name="description">
<meta content="ThinkGem, http://jeesite.com" name="author">
<title>选择资源 </title>
<!-- <script src="/itsm/global.min.js?ctx=/itsm"></script> -->
<script src="/Quark/statics/jQuery/jquery-1.12.4.min.js"></script>
<script src="/Quark/statics/jQuery/jquery-migrate-1.4.1.min.js"></script>
<!--[if lt IE 9]><script src="/Quark/statics/common/h5fix.min.js"></script><![endif]-->
<link rel="stylesheet" href="/Quark/statics/fonts/font-icons.min.css">
<link rel="stylesheet" href="/Quark/statics/AdminLTE/AdminLTE-2.4.3/bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/Quark/statics/select2/4.0/select2.css?-07071142">
<link rel="stylesheet" href="/Quark/statics/icheck/1.0/minimal/grey.css?-07071142">
<link rel="stylesheet" href="/Quark/statics/jquery-ztree/3.5/css/metro/zTreeStyle.css?-07071142">
<link rel="stylesheet" href="/Quark/statics/AdminLTE/AdminLTE-2.4.3/dist/css/AdminLTE.min.css?-07071142">
<link rel="stylesheet" href="/Quark/statics/common/jeesite.css?-07071142">
<!-- <link rel="stylesheet" href="/Quark/statics/common/common.css?-07071142"> -->
<link rel="stylesheet" href="/Quark/statics/layer/3.1/skin/default/layer.css?v=3.0.3303" id="layuicss-skinlayercss">
<link href="/Quark/statics/my97/skin/WdatePicker.css" rel="stylesheet" type="text/css">
</head>
<body class="hold-transition box box-main">
<div class="wrapper"><div class="treeShowHideButton" onclick="search();">
	<label id="btnShow" title="显示搜索" style="display:none;">︾</label>
	<label id="btnHide" title="隐藏搜索">︽</label>
</div>
<div class="treeSearchInput" id="search">
	<label for="keyword">关键字：</label><input type="text" class="empty" id="keyword" maxlength="50">
	<button class="btn" id="btn" onclick="searchNode()"> 搜索 </button>
</div>
<div class="treeExpandCollapse">
	<a href="javascript:" onclick="tree.expandAll(true);">展开</a> /
	<a href="javascript:" onclick="tree.expandAll(false);">折叠</a>
</div>
<div id="tree" class="ztree treeselect">
</div>
</div>

<a id="scroll-up" href="#" class="btn btn-sm"><i class="fa fa-angle-double-up"></i></a>
<script src="/Quark/statics/AdminLTE/AdminLTE-2.4.3/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/Quark/statics/select2/4.0/select2.js?-07071142"></script>
<script src="/Quark/statics/select2/4.0/i18n/zh_CN.js?-07071142"></script>
<script src="/Quark/statics/layer/3.1/layer.js?-07071142"></script>
<script src="/Quark/statics/my97/WdatePicker.js?-07071142"></script>
<script src="/Quark/statics/jquery-ztree/3.5/js/jquery.ztree.all-3.5.js?-07071142"></script>
<script src="/Quark/statics/common/jeesite.js?-07071142"></script>
<!-- <script src="/Quark/statics/common/i18n/jeesite_zh_CN.js?-07071142"></script>
<script src="/Quark/statics/common/common.js?-07071142"></script> -->
<script>
var setting = {
	view:{selectedMulti:false,dblClickExpand:false},
	check:{enable:${empty check?false:check},nocheckInherit:true},
	data:{simpleData:{enable:true}},
	callback:{
		onClick:function(event, treeId, treeNode){
			tree.expandNode(treeNode);
		},
		onDblClick: function(event, treeId, treeNode){
			if(!treeNode.isParent){
				js.layer.$('#' + window.name).closest('.layui-layer')
				.find(".layui-layer-btn0").trigger("click");
			}
			
		}
	}
}, tree, loadTree = function(){///Quark/resource/ext/tree
	$.get("${url}?t=" + new Date().getTime(), function(zNodes){
		tree = $.fn.zTree.init($("#tree"), setting, zNodes);//.expandAll(true);
		$.fn.zTree.expandNodeByLevel(tree, -1);
		if (zNodes && zNodes.length > 0){
			var attrName = zNodes[0].value ? 'value' : 'id';
			var selectCodes = "".split(",");
			selectCheckNode(attrName, selectCodes);
		}
	});
};loadTree();
function selectCheckNode(attrName, selectCodes){
	for(var i=0; i<selectCodes.length; i++) {
		var isLoadUser = false;
		var node = tree.getNodeByParam(attrName, (isLoadUser?"u_":"") + selectCodes[i]);
		if("false" == "true"){
			try{tree.checkNode(node, true, false);}catch(e){}
			tree.selectNode(node, false);
		}else{
			tree.selectNode(node, true);
		}
	}
}
var lastValue = "", nodeList = [], key = $("#keyword");
key.bind("focus", focusKey).bind("blur", blurKey).bind("change cut input propertychange", searchNode);
key.bind("keydown", function (e){if(e.which == 13){searchNode();}});
function focusKey(e) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}
function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("empty");
	}
	searchNode(e);
}
function searchNode() {
	var value = $.trim(key.get(0).value);
	var keyType = "name";
	if (lastValue === value) {
		return;
	}
	lastValue = value;
	var nodes = tree.getNodes();
	if (value == "") {
		showAllNode(nodes);
		return;
	}
	hideAllNode(nodes);
	nodeList = tree.getNodesByParamFuzzy(keyType, value);
	updateNodes(nodeList);
}
function hideAllNode(nodes){			
	nodes = tree.transformToArray(nodes);
	for(var i=nodes.length-1; i>=0; i--) {
		tree.hideNode(nodes[i]);
	}
}
function showAllNode(nodes){			
	nodes = tree.transformToArray(nodes);
	for(var i=nodes.length-1; i>=0; i--) {
		if(nodes[i].getParentNode()!=null){
			tree.expandNode(nodes[i],false,false,false,false);
		}else{
			tree.expandNode(nodes[i],true,true,false,false);
		}
		tree.showNode(nodes[i]);
		showAllNode(nodes[i].children);
	}
}
function updateNodes(nodeList) {
	tree.showNodes(nodeList);
	for(var i=0, l=nodeList.length; i<l; i++) {
		var treeNode = nodeList[i];
		showChildren(treeNode);
		showParent(treeNode)
	}
}
function showChildren(treeNode){
	if (treeNode.isParent){
		for(var idx in treeNode.children){
			var node = treeNode.children[idx];
			tree.showNode(node);
			showChildren(node);
		}
	}
}
function showParent(treeNode){
	var parentNode;
	while((parentNode = treeNode.getParentNode()) != null){
		tree.showNode(parentNode);
		tree.expandNode(parentNode, true, false, false);
		treeNode = parentNode;
	}
}
function search($this) {
	$('#search').slideToggle(200);
	$('#btnShow').toggle();
	$('#btnHide').toggle();
	$('#keyword').focus();
}
function getTreeObj(){
	return tree;
}
</script></body></html>
