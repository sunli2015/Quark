<%@ page contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.8.3.min.js"></script>
<script src="${ctx}/dwr/engine.js" type="text/javascript"></script>
<script src="${ctx}/dwr/util.js" type="text/javascript"></script>
<script src="${ctx}/common/myjs/coral_constans.js" type="text/javascript"></script>
<script src="${ctx}/common/myjs/coral.js" type="text/javascript"></script>
<script type="text/javascript">
	dwr.engine.setErrorHandler(errorHandler);
	dwr.util.useLoadingMessage('请等待...');
	function errorHandler(errorString, exception)
	{
	    alert(errorString);
	}
</script>