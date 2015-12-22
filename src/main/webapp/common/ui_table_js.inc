<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="${ctx }/js/Flexigrid/css/flexigrid.css">
<script type="text/javascript" src="${ctx }/js/Flexigrid/js/flexigrid.js"></script>
<script type="text/javascript">
(function($){
	var _buttons = [];	
	var defaults = {
		url: '',
		dataType: 'json',
		colModel : [],
		searchitems : [],
		buttons:_buttons,
		usepager: true,
		title: '用户列表',
		useRp: true,
		rp: 15,
		showTableToggleBtn: true,
		//width: 820,
		onSubmit: addFormData,
		height: 361,
		pagestat: '显示 {from} 到 {to} 共 {total} 记录',
		pagetext: '页',
		outof: '共',
		findtext: '查找',
		procmsg: '正在加载中，请等待 ...',
		nomsg: '无记录',
		showToggleBtn: false,
		singleSelect:true,//单条选择LEO UPDATE 2014-02-19
		searchGroupLen:4,//搜索选择的每组数量
		onError:function(XMLHttpRequest, textStatus, errorThrown){
			alert("ERROR:"+textStatus);
			alert(errorThrown);
			}
		};
	//
	var addFormData = function(p){
		var dt = [];
		$.each(p,function(key,val){
			var _p = {};
			_p['name'] = key;
			_p['value']= val;
			dt.push(_p);
		});  		
		$(this).flexOptions({params: dt});
	}
	
	var create = function(t,opt){
		if($.fgtable.plugInForm){
   	   		var _form = $.fgtable.form;
   	   		//alert(_form.settings().fbuttons);
   	   		if(_form.settings && _form.settings.fbuttons) 
   	   	   		_buttons = _form.settings.fbuttons;
   	   	}
   	   	
   		if(opt.buttons && opt.buttons.length>1){
   			$.each(opt.buttons,function(i,b){
   				_buttons.push(b);
   			});
   		}    		
   		if(opt.addbuttons && opt.addbuttons.length>=1){
   			$.each(opt.addbuttons,function(i,b){
   				_buttons.push(b);
   			});
   		}
   		defaults.buttons = _buttons;
   		var _opt = $.extend(defaults, opt);
		t.flexigrid(_opt);
	};
	//表格对象
	/*$.fn.fgtable = function(options){
		return this.each(function(){
			alert("ok");
			create($(this),options);
		});
	}*/
	$.fn.extend({
			fgtable : function(options){
				return this.each(function(){
					create($(this),options);
				});
			}
		});
	//插件全局判断
	$.fgtable = {
		plugInForm : false,
		form:function(){
			return; 
		}
	};
})(jQuery);
</script>