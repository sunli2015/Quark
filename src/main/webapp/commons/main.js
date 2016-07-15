/**
 * main.jsp
 * 初始化菜单树
 */
$(function(){
	//初始化功能菜单
	$('#funtree').tree({
		onClick: function(node){
			var id = node.id;
			if(id != 'logout') $('#tt').show();
			/**
			 * 功能标识列表
			 */
			if(id == 'm_res') addTab(node.text,CONTEXT_PATH+"/module/index.do");//资源管理
			if(id == 'm_role') addTab(node.text,CONTEXT_PATH+"/role/index.do");//角色管理
			if(id == 'm_user') addTab(node.text,CONTEXT_PATH+"/dept/index.do");//用户管理
			if(id == 'pwd') addTab(node.text,CONTEXT_PATH+"/user/pwdedit.do");//修改密码
			if(id == 'logout') logout();//注销
			
		}
	});
	//初始化TABS的关闭事件
	$("#tt").tabs({
		onClose:function(title,index){
			//alert(index+"=>"+title);
			if($(this).tabs("tabs").length==0){
				$("#tt").hide();
			};
		}
	});
});
/**
 * 添加功能TAB
 * @param title 标签名称
 * @param url URL链接
 */
var tth = 0;
function addTab(title, url){
    if ($('#tt').tabs('exists', title)){
        $('#tt').tabs('select', title);
    } else {
        if($('#tt').tabs("tabs").length > 10){//判断TAB的数量
			$.messager.alert('警告','功能标签过多【10】，请关闭多余标签','warning');
			return ;
		};
        
		var _h = $("#region_center").height();
		//console.log("region_center:",_h);
		if(tth == 0) tth = $("#tt").height();
		//console.log("tt:",tth);
		var height = _h-tth;
		//console.log("height:",height);
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:'+height+';"></iframe>';
        $('#tt').tabs('add',{
	            title:title,
	            content:content,
	            closable:true
	        });
	    }
}
/**
 * 登出
 */
function logout(){
	 $.messager.confirm('注销','你确定要注销吗？',function(r){
		 if(r){
			 document.location.href = CONTEXT_PATH+'/logout';
		 }
	 });
}