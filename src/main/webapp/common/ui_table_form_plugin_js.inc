<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" href="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/themes/base/jquery.ui.all.css"/>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.core.js"></script>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.widget.js"></script>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.mouse.js"></script>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.button.js"></script>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.draggable.js"></script>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.position.js"></script>
<%--<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.resizable.js"></script> --%>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.effect.js"></script>
<style type="text/css">
.flexigrid div.fbutton .add {
	background: url(${ctx}/js/Flexigrid/images/add.png) no-repeat center left;
}
.flexigrid div.fbutton .delete {
    background: url(${ctx}/js/Flexigrid/images/close.png) no-repeat center left;
}
.flexigrid div.fbutton .edit {
	background: url(${ctx}/js/Flexigrid/images/edit.png) no-repeat center left;
}
</style>
<style type="text/css">
body { font-size: 62.5%; }
#form_div fieldset { padding:0; border:0; margin-top:10px; }
#form_div input.text { margin-bottom:12px; width:95%; padding: .4em; }
#form_div select { margin-bottom:12px; width:95%; padding: .2em; }
#form_div select.selectfont { font-family: Verdana,Arial,sans-serif/*{ffDefault}*/; font-size: 1.1em/*{fsDefault}*/;display: block; }
</style>
<script type="text/javascript">
(function($){
	var defaults = {
			autoOpen: false,
			height: 400,
			width: 400,
			modal: true,
			buttons: {
				"保存": function(){
					fsave();
				},
				"关闭": function(){
					$(this).dialog( "close" )
					fclose();
				}
			},
			close: function(){
				$(this).dialog( "close" )
				fclose();
			},
			fbuttons : [
		        		{id:"Add",name : '添加',bclass : 'add',onpress : fpressHandle},
		        		{id:"Edit",name : '修改',bclass : 'edit',onpress : fpressHandle},
		        		{id:"Delete",name : '删除',bclass : 'delete',onpress : fpressHandle},
		        		{separator : true}
		   	],
			instance : null,//dialog 实例
			tInstanceId : "",//table的ID
			saveUrl : "",//保存URL
			editUrl : "",//编辑URL
			delUrl : "",//删除URL
			formOpenInit:null,//表单打开时的初始化，自定义function(){}
			valid:null,//提交保存前校验,自定义function(){return true;}
			preSaveParam:null,//保存前重置参数，自定义function(){}
			editResetParam:null//编辑时重置参数，自定义function(){}
	};
	function fpressHandle(com,grid){
		var o = defaults.instance;
		var tableId = defaults.tableId;
		var rows = $("#"+tableId).selectedRows();
		if (com == 'Delete') {
			//btn_Delete(com,grid);
			if(rows.length == 0){
	        	alert("请选择要删除的记录");
	        	return ;
	        }
	        var conf = confirm('你确认要删除[' + rows.length + ']条记录?')
	        if(conf){
	        	var param = [];
	        	$.each(rows,function(i,row){
	            	$.each(row,function(j,r){
	                	if(r.Column == "oid"){
	                		//alert(r.Value);
	                		param.push({name:"oid",value:r.Value});
	                    	}
	                	});
	            	});
            	var delUrl = defaults.delUrl;
	        	$.ajax({
					type: "POST",
					url: delUrl,//"${ctx }/admin/user!delete.action",
					data: param,
					dataType: "json",
					success: function (data) {
						alert(data.operMsg);
						$("#"+tableId).flexReload();
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown.message);
					}
				});
	        }
		} else if(com == 'Edit'){
			if(rows.length != 1){
	        	alert("请选择要1条修改的记录");
	        	return ;
	        }
	    	var param = [];
	    	var row = rows[0];
	    	
	    	$.each(row,function(j,r){
	    		//alert(r.Column+":"+r.Value);
	        	if(r.Column == "oid"){
	        		//alert(r.Value);
	        		param.push({name:"oid",value:r.Value});
	            	}
	        	});
        	var editUrl = defaults.editUrl;
	    	$.ajax({
				type: "POST",
				url: editUrl,//"${ctx }/admin/user!edit.action",
				data: param,
				dataType: "json",
				success: function (data) {
					var entity = data.entity;
					//alert(entity);
					$.each(entity,function(key,val){
						//alert(key+":"+val);
						if($.type(val) == "object"){//json 对象
							//alert(key+":"+val);
							$.each(val,function(k,v){
	    						if(k == "oid")
									$("#entity\\."+key+"\\."+k).val(v);
	    						});
						} else {
							$("#entity\\."+key).val(val);
							if(key == "oid") $("#oid").val(val);
							if(defaults.editResetParam && $.isFunction(defaults.editResetParam)){
								defaults.editResetParam("entity."+key,val);
							}
							
	    				}
						
					});
					o.dialog("open");
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown.message);
				}
			});
		} else if(com == 'Add'){
			o.dialog("open");
	    	if($.isFunction(defaults.formOpenInit)) defaults.formOpenInit();
		} 
	}
	function fsave(){
		var isPass = true ;
		if(defaults.valid && $.isFunction(defaults.valid)){
			isPass = defaults.valid();
		}
		if(!isPass) return ;
		var param = [];
		var o = defaults.instance;
		o.find("[name^='entity']").each(function(i,data){
			var _name = $(data).attr("name");
			var _val = $(data).val();
			//alert(_data.attr("name")+":"+_data.val());
			if(defaults.preSaveParam && $.isFunction(defaults.preSaveParam)){
				var _preSaveParam = defaults.preSaveParam();
				$.each(_preSaveParam,function(key,val){
					if(key == _name){
						_val = val;//重置
					} 
				});
			}
			param.push({name:_name,value:_val}); 
			});
		var _oid = o.find("[name='oid']");
		param.push({name:_oid.attr("name"),value:_oid.val()});

		/*$.each(param,function(i,o){
			alert(o.name+"=>"+o.value);
		});*/
		
		var saveUrl = defaults.saveUrl;
		var tableId = defaults.tableId ;

		$.ajax({
			type: "POST",
			url: saveUrl,//"${ctx }/admin/user!save.action",
			data: param,
			dataType: "json",
			success: function (data) {
				if(defaults.saveSuccess && $.isFunction(defaults.saveSuccess)){//rewrite function:success
					defaults.saveSuccess(data);
					return ;
				}
				//default function : success
				alert(data.operMsg);
				o.dialog("close");
				$("#"+tableId).flexReload();
				o.find("[name^='entity']").each(function(i,data){
					$(data).val("");
					//alert(_data.attr("name")+":"+_data.val());
					var _name = $(data).attr("name");
					//alert(_data.attr("name")+":"+_data.val());

					if(defaults.resetParam && $.isFunction(defaults.resetParam)){
						defaults.resetParam(_name);
					}
					
					});
				o.find("[name='oid']").val("");
				
				//custom save succ function: saveSucc
				if(defaults.saveSuccAfter && $.isFunction(defaults.saveSuccAfter)){
					defaults.saveSuccAfter(data);
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown.message);
			}
		});
	}
	function fclose(){
		if($.validity) $.validity.clear();//校验开源的清理
		var o = defaults.instance;
		if(defaults.fclose && $.isFunction(defaults.fclose)){//rewrite function:close
			defaults.fclose();
			return ;
		}
		o.find("[name^='entity']").each(function(i,data){
			$(data).val("");
			var _name = $(data).attr("name");
			//alert(_data.attr("name")+":"+_data.val());

			if(defaults.resetParam && $.isFunction(defaults.resetParam)){
				defaults.resetParam(_name);
			}
			
			});
		o.find("[name='oid']").val("");
	}
	function dialog(t,opt){
		if(!opt) opt = {};
		$.extend(defaults, opt);
		defaults.instance = $(t);
		defaults.instanceId = t;
		$(t).dialog(defaults);
	}
	
	$.fn.extend({
		form : function(opt){
			return this.each(function(){
				dialog(this,opt);
			});
		},
		settings : defaults
	});
})(jQuery);
</script>