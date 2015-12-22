<%@ page contentType="text/html; charset=UTF-8"%>
<link type="text/css" rel="Stylesheet" href="${ctx }/js/validity/jquery.validity.css" />
<script type="text/javascript" src="${ctx }/js/validity/jquery.validity.js"></script>
<script type="text/javascript" src="${ctx }/js/validity/jquery.validity.lang.zh-CN.js"></script>
<script type="text/javascript">
$(function() {
	$.validity.setup({ outputMode:"label" });
	$.validity.isValidating = function(){return true;};
	$.validity.report = { errors:0, valid:true };
	//alert("init:"+$.validity.report.errors);
});
</script>