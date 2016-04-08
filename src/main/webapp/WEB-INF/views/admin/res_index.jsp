<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<c:set var="title" value="Tree" />
<html>
<head>
<title>${title}</title>
<%@ include file="/commons/public.inc"%>
<script type="text/javascript">
$(function(){
	$('#funtree').tree({
		onClick: function(node){
			var id = node.id;
			var height = $("#region_center").height();
			//console.log("height:",height);
			var url = CONTEXT_PATH+"/resource/index.do?moduleId="+id+"&select=${param.select}";
	        var content = '<iframe id="ifr_reslist" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:'+height+';"></iframe>';
	        $("#region_center").append(content);
		}
	});
})
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'west',split:true" title="模块功能菜单" style="width:25%;">
		<ul id="funtree" class="easyui-tree">
		<jsp:include page="/module/tree.do"></jsp:include>
		</ul>
	</div>
	<div id="region_center" data-options="region:'center'" >
	</div>
</body>
</html>

