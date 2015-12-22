<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" href="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/themes/base/jquery.ui.all.css"/>
	<%--<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/external/jquery.bgiframe-2.1.2.js"></script> --%>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.core.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.mouse.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.button.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.draggable.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.position.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script src="${ctx }/js/jquery-ui-1.9.2.custom/development-bundle/ui/jquery.ui.effect.js"></script>
<style type="text/css">
body { font-size: 64.5%; }
#dialog-form input.text { margin-bottom:12px; width:95%; padding: .4em; }
#dialog-form fieldset { padding:0; border:0; margin-top:10px; }
#dialog-form select { margin-bottom:12px; width:95%; padding: .2em; }
#dialog-form select.selectfont { font-family: Verdana,Arial,sans-serif/*{ffDefault}*/; font-size: 1.1em/*{fsDefault}*/; }
<%--
body { font-size: 62.5%; }
#dialog-form h1 { font-size: 1.2em; margin: .6em 0; }
div#users-contain { width: 350px; margin: 20px 0; }
div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
.ui-dialog .ui-state-error { padding: .3em; }
.validateTips { border: 1px solid transparent; padding: 0.3em; }
--%>
</style>
<script type="text/javascript">
(function($){
	var FORMID = "editform";
	var _default = {
			autoOpen: true,
			height: 400,
			width: 400,
			modal: false,
			position:['center',10],
			buttons: null,
			rtnUrl:'',
			reset:null,
			type:'threeBtn'//type:twoBtn(save,reset),threeBtn(save,reset,return),
		};
	$.openDialog = function(o,opt){
		$this = $(o);
		$.extend(_default, opt);
		if(_default.type && _default.type == 'twoBtn'){
			$.extend(_default, {
					buttons: {
					"保存": function() {
						$("#"+FORMID).submit();
					},
					"重置": function() {
						$("#"+FORMID+" :visible").val("");
						if(_default.reset && $.isFunction(_default.reset)){
							_default.reset();
						}
					}
					}
				});
		}
		if(_default.type && _default.type == 'threeBtn'){
			$.extend(_default, {
					buttons: {
					"保存": function() {
						$("#"+FORMID).submit();
					},
					"重置": function() {
						$("#"+FORMID+" :visible").val("");
						if(_default.reset && $.isFunction(_default.reset)){
							_default.reset();
						}
					},
					"返回":function(){
						window.location.href = _default.rtnUrl;
					}
				}
			});
		}
		$this.dialog(_default);
		$('a.ui-dialog-titlebar-close').hide();
	};
	$.fn.extend({
				openWin : function(opt){
				return this.each(function(){
					$.openDialog(this,opt);
				});
			}
		});
})(jQuery);
</script>