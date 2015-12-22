/**
 * 命名空间
 * coral
 */

/**
 * 判断是否为空字符串
 */
coral.isNull = function(str){
	if(str == null || str == '' 
		|| str == 'null'
		|| str == 'undefined')
		return true;
	return false;
}
/**
 * 获得Ext对象
 * top->parent->self
 */
coral.getExt = function(){
var ExtObj;
	try{ExtObj = top.Ext;}catch(e){
	  try{ExtObj = parent.Ext;}catch(e){
	   	try{ExtObj = Ext;}catch(e){alert(coral.SYS_EXCEPTION + '(coral.getExt)');}
	  }
	}	
return ExtObj;
}

/**
 * 获得Iframe document对象(跨浏览器)
 */
coral.getIframeDocument = function(el) {   
    var oIframe = Ext.get(el).dom;   
    var oDoc = oIframe.contentWindow || oIframe.contentDocument;   
    if(oDoc.document) {   
        oDocoDoc = oDoc.document;   
    }   
    return oDoc;   
}   
/**
 * 支持IE与fireFox获得iframe的方法
 */
coral.getFrameById = function(frmId){
	return coral.getIframeDocument(frmId);
}

//得到server path
coral.getCtx = function(){
	var url = location.pathname;
	url = url.substring(1,url.length);
	url = url.substring(0,url.indexOf("/"));
	return "/"+url;
}

/**
 * 提示信息
 */
coral.showMsg = function(msgObj){
	var showMsg = {};
	if(typeof(msgObj) == 'string'){
		showMsg.msg = msgObj;
	}else{
		showMsg = msgObj;
	}
	
	var title = coral.MSG_INFO_TITLE ;
	if(showMsg.title) title = showMsg.title;
	
	var message = "";
	if(showMsg.msg) message = showMsg.msg;
	
	var callback = new function(){};
	if(showMsg.fn) callback = showMsg.fn;
	
	var icon = 'ext-mb-info';
	if(showMsg.icon) icon=showMsg.icon;
	
    var ExtObj = coral.getExt();
    if(!ExtObj) alert(message);

	var buttons = ExtObj.MessageBox.OK;
	if(showMsg.buttons) buttons = showMsg.buttons;
	    
    ExtObj.onReady(function(){ExtObj.MessageBox.show(
    	{title:title, msg:message,buttons: buttons,icon : icon,fn:callback}
    )});
}

/**
 * 一般提示信息
 */
coral.showInfoMsg = function(msgObj){
	var showMsg = {};
	if(typeof(msgObj) == 'string'){
		showMsg.msg = msgObj;
	}else{
		showMsg = msgObj;
	}
	
	if(!showMsg.title) showMsg.title = coral.MSG_INFO_TITLE;
	if(!showMsg.icon) showMsg.icon = 'ext-mb-info';

	coral.showMsg(showMsg);	
}
/**
 * 警告提示信息
 */
coral.showWarningMsg = function(msgObj){
	var showMsg = {};
	if(typeof(msgObj) == 'string'){
		showMsg.msg = msgObj;
	}else{
		showMsg = msgObj;
	}

	if(!showMsg.title) showMsg.title = coral.MSG_WARNING_TITLE;
	if(!showMsg.icon) showMsg.icon = 'ext-mb-warning';

	coral.showMsg(showMsg);	
}
/**
 * 错误提示信息
 */
coral.showErrorMsg = function(msgObj){
	var showMsg = {};
	if(typeof(msgObj) == 'string'){
		showMsg.msg = msgObj;
	}else{
		showMsg = msgObj;
	}
	
	if(!showMsg.title) showMsg.title = coral.MSG_ERROR_TITLE;
	if(!showMsg.icon) showMsg.icon = 'ext-mb-error';

	coral.showMsg(showMsg);	
}

/**
 * 自动隐藏的消息提示框
 */
coral.showGhostMsg = function(msgObj){

	try{
		// 隐藏保存进程	
		coral.hideMessageBox();
	}catch(e){};
	
	var showMsg = {};
	if(typeof(msgObj) == 'string'){
		showMsg.msg = msgObj;
		showMsg.title = coral.OPEATE_MSG;
	}else{
		showMsg = msgObj;
	}
	
    var ExtObj = coral.getExt();
    if(!ExtObj) {
    	coral.showInfoMsg(showMsg);
    }
    	
	if(ExtObj.example){
       ExtObj.example.msg(showMsg.title, showMsg.msg);
    }
}

	/**
	 * 显示保存进程
	 */
	coral.showSaveProgress = function(){
        this.getExt().MessageBox.show({
                  msg: coral.SAVE_PROGRESS_MSG,
                  progressText: coral.SAVEING_PROGRESS_MSG,
                  width:300,
                  wait:true,
                  waitConfig: {interval:200},
                  icon:'ext-mb-save',
                  nimEl: 'btnSave'
                });	
	}
	/**
	 * 隐藏消息提示
	 */
	coral.hideMessageBox = function(){
		this.getExt().MessageBox.hide();
	}
/**
 * 获得Ext对象
 * self -> parent -> top
 */
coral.getExtSelf = function(){
var ExtObj;
	try{ExtObj = Ext;}catch(e){
	  try{ExtObj = parent.Ext;}catch(e){
	   	try{ExtObj = top.Ext;}catch(e){alert(coral.SYS_EXCEPTION + '(coral.getExt)');}
	  }
	}	
return ExtObj;
}

/**
 * 调试信息方法命名空间
 */
coral.logger = new Object();
/**
 * 调试信息显示开关
 */
coral.logger.isMsg = true;
/**
 * 调试信息显示开关
 */
coral.logger.isDebug = false;
/**
 * 错误信息显示开关
 */
coral.logger.isError = true;
coral.logger.msg = function(msg){
	if(coral.logger.isMsg) {
   	 	/*var iDiv = document.getElementById("coral_logger_list");
	   	 if(iDiv == null){
	    	iDiv = document.createElement("div");
	    	iDiv.id="coral_logger_list";
	        iDiv.style.position = "absolute";
	        iDiv.style.top="0px";
	        iDiv.style.left="0px";
	        iDiv.style.background="url(icon_select.gif) no-repeat right 4px";
	        iDiv.style.border="1px solid #3366ff";
	        iDiv.style.fontSize="12px";
	        iDiv.style.lineHeight="100px";
	        iDiv.style.textIndent="100px";
	     }
	    iDiv.innerText = msg;
	    document.body.appendChild(iDiv);  */
	    alert(msg);   
	}
}
//debug msg
coral.logger.debug = function(msg){
	if(coral.logger.isDebug) {
		alert(msg); 
	}
}
//error msg
coral.logger.error = function(msg){
	if(coral.logger.isError) {
		alert(msg); 
	}
}

/**
 * 回调基类,用于窗口关闭后的操作	 
 */
coral.BaseCallBack = function(){
	
	/**
	 * 输入参数
	 */
	this.param = new Object();
	this.setParam = function(param){
		this.param = param;
	};
	
	/**
	 * 设置的返回数据对象
	 */
	this.result = new Object();	 
	this.setResult=function(result){
		this.result = result;
	};

	/**
	 * 回调之前执行的方法,需要继承基类后重写该方法
	 */
	this.before = function(){};
	/**
	 * 回调执行的方法,需要继承基类后重写该方法
	 */
	this.execute = function(){};	
	/**
	 * 回调之后执行方法,需要继承基类后重写该方法
	 */
	this.after = function(){};	
};

/**
 * 选择方法命名空间
 */
coral.select = new Object();
//选择人员方法
coral.select.Personnel = function(){

	//回调事件定义，继承coral.BaseCallback
	this.selectCallBack = new coral.BaseCallBack();
	
	/**
	 * 用户参数数据
	 */
	this.param = new Object();
	this.setParam = function(param){			
		this.param = param;
		initParam(this.param);
		this.selectCallBack.setParam(param);
	};
	
	//初始化参数对象
	var initParam = function(param){
	
		if(!this.param) return;
		
		var ctx = param.ctx;
		if(coral.isNull(ctx))
			param.ctx = coral.getCtx();
			
		//默认单选
		var style = param.style;
		if(coral.isNull(style))
			param.style = '1';

		//窗口的高X宽
		if(coral.isNull(param.width))
			param.width = '500';		
		if(coral.isNull(param.height))
			param.height = '400';
	}
	
	/**
	 * 重写父类after方法,在回调方法之后调用
	 * 将数据回填到页面
	 */
	this.selectCallBack.after = function (){
		var style = "1";
		var formName = null;
		var propertys = new Array();
		if(this.param){
			var _param = this.param;
			if(_param.formName)
				formName = _param.formName;
			if(_param.style)
				style = _param.style;
			if(_param.propertys)
				propertys = _param.propertys;	
		}		
		
		//回填到表单
//		var formObj = document.forms[0];
//		if(formName != ''){
//			formObj = document.forms[formName];
//		}
		
//		if(formObj){
			if(propertys){
				var _data = this.result;
				var size = propertys.size();
				for(i=0;i<size;i++){
					var name = propertys[i].name;
					var element = propertys[i].element; 
					//写入到页面表单中
					writeElement(_data,name,element);
				}
			}			
//		}
		
	};
	
	//写入到页面表单中
	var writeElement = function(data,propertyName,element){
		coral.logger.debug("writeElement propertyName:"+propertyName + " element:" + element);		
		//回填到表单
		var elementObjs = document.getElementsByName(element);
		if(elementObjs[0]){
			var elementValue = "";
			for(var userId in data){
				coral.logger.debug("writeElement userId:"+userId);		
				var userInfo = data[userId];
				var propertyValue = userInfo[propertyName];
				if(coral.isNull(propertyValue)) propertyValue = '';
				if(!coral.isNull(elementValue))	elementValue += ",";
				elementValue += propertyValue;
				
			}	
			coral.logger.debug("writeElement elementValue:" + elementValue);
			elementObjs[0].value = elementValue;
			
		}
	}
	
	/**
	 * 打开选择窗口
	 */
	this.open = function(){
		//确定方法事件
		var ok = function(){
			var selectElement = coral.getIframeDocument(CoralWin.IFRAME_NAME);
			selectElement.confirmSelected();
		}
		//重置方法事件
		var reset = function(){
			var selectElement = coral.getIframeDocument(CoralWin.IFRAME_NAME);
			selectElement.resetSelected();
		}
		//取消事件
		var cancel = function(){CoralWin.cancel();}
	
		//设置按钮
		var buttons = [
		{'name':coral.BUT_OK ,'handler':ok},
		{'name':coral.BUT_RESET,'handler':reset},
		{'name':coral.BUT_CANCLE,'handler':cancel}	
		];		
		
		//设置选择人员的url
		var url = this.param.ctx;
			url += "/select/personnel.do?deptid=";
		if(this.param){
			var _param = this.param;
			if(_param.deptid)
				url += _param.deptid;
			if(_param.deptCode)
				url += "&deptCode=" + _param.deptCode;				
			if(_param.selectedUserIds)
				url += "&selectedUserIds=" + _param.selectedUserIds;
			if(_param.style)
				url += "&style=" + _param.style;
			if(_param.other)
				url += "&other=" + _param.other;
		}

		//标题
		var title = coral.SELECT_USER_TITLE;
		if(this.param.title)
			title = this.param.title;
			
		//alert(title);
		var _param = {
		'id'		:	'coral.SELECT_USER',	//id		
		'sTitle'	:	title,		//窗口标题
		'sOpenSrc'	:	url,		//打开的链接
		'bModel'	:	true, 			//可选,默认为模式窗口,false为无模式窗口
		'sWidth'	:	this.param.width,			//可选
		'sHeight'	:	this.param.height,			//可选
		'oCallback'	:	this.selectCallBack,		//可选,设置回调方法名称
		'oButtons'	:	buttons			//可选,设置窗口按钮,默认没有按钮
		};
		
		CoralWin.open(_param);
	}
}


//选择质检对象
coral.select.Inspect = function(){

	//回调事件定义，继承coral.BaseCallback
	this.selectCallBack = new coral.BaseCallBack();
	
	/**
	 * 用户参数数据
	 */
	this.param = new Object();
	this.setParam = function(param){			
		this.param = param;
		initParam(this.param);
		this.selectCallBack.setParam(param);
	};
	
	//初始化参数对象
	var initParam = function(param){
	
		if(!this.param) return;
		
		var ctx = param.ctx;
		if(coral.isNull(ctx))
			param.ctx = coral.getCtx();
			
		//默认单选
		var style = param.style;
		if(coral.isNull(style))
			param.style = '1';

		//窗口的高X宽
		if(coral.isNull(param.width))
			param.width = '500';		
		if(coral.isNull(param.height))
			param.height = '400';
	}
	
	/**
	 * 重写父类after方法,在回调方法之后调用
	 * 将数据回填到页面
	 */
	this.selectCallBack.after = function (){
		var style = "1";
		var formName = null;
		var propertys = new Array();
		if(this.param){
			var _param = this.param;
			if(_param.formName)
				formName = _param.formName;
			if(_param.style)
				style = _param.style;
			if(_param.propertys)
				propertys = _param.propertys;	
		}		
		
		//回填到表单
//		var formObj = document.forms[0];
//		if(formName != ''){
//			formObj = document.forms[formName];
//		}
		
//		if(formObj){
			if(propertys){
				var _data = this.result;
				var size = propertys.size();
				for(i=0;i<size;i++){
					var name = propertys[i].name;
					var element = propertys[i].element; 
					//写入到页面表单中
					writeElement(_data,name,element);
				}
			}			
//		}
		
	};
	
	//写入到页面表单中
	var writeElement = function(data,propertyName,element){
		coral.logger.debug("writeElement propertyName:"+propertyName + " element:" + element);		
		//回填到表单
		var elementObjs = document.getElementsByName(element);
		if(elementObjs[0]){
			var elementValue = "";
			for(var userId in data){
				coral.logger.debug("writeElement userId:"+userId);		
				var userInfo = data[userId];
				var propertyValue = userInfo[propertyName];
				if(coral.isNull(propertyValue)) propertyValue = '';
				if(!coral.isNull(elementValue))	elementValue += ",";
				elementValue += propertyValue;
				
			}	
			coral.logger.debug("writeElement elementValue:" + elementValue);
			elementObjs[0].value = elementValue;
			
		}
	}
	
	/**
	 * 打开选择窗口
	 */
	this.open = function(){
		//确定方法事件
		var ok = function(){
			var selectElement = coral.getIframeDocument(CoralWin.IFRAME_NAME);
			selectElement.confirmSelected();
		}
		//重置方法事件
		var reset = function(){
			var selectElement = coral.getIframeDocument(CoralWin.IFRAME_NAME);
			selectElement.resetSelected();
		}
		//取消事件
		var cancel = function(){CoralWin.cancel();}
	
		//设置按钮
		var buttons = [
		{'name':coral.BUT_OK ,'handler':ok},
		{'name':coral.BUT_RESET,'handler':reset},
		{'name':coral.BUT_CANCLE,'handler':cancel}	
		];		
		
		//设置选择人员的url
		var url = this.param.ctx;
			url += "/select/personnel!inspectChoose.do?deptid=";
		if(this.param){
			var _param = this.param;
			if(_param.deptid)
				url += _param.deptid;
			if(_param.deptCode)
				url += "&deptCode=" + _param.deptCode;				
			if(_param.selectedUserIds)
				url += "&selectedUserIds=" + _param.selectedUserIds;
			if(_param.style)
				url += "&style=" + _param.style;
			if(_param.other)
				url += "&other=" + _param.other;
		}

		//标题
		var title = coral.SELECT_USER_TITLE;
		if(this.param.title)
			title = this.param.title;
			
		//alert(title);
		var _param = {
		'id'		:	'coral.SELECT_USER',	//id		
		'sTitle'	:	title,		//窗口标题
		'sOpenSrc'	:	url,		//打开的链接
		'bModel'	:	true, 			//可选,默认为模式窗口,false为无模式窗口
		'sWidth'	:	this.param.width,			//可选
		'sHeight'	:	this.param.height,			//可选
		'oCallback'	:	this.selectCallBack,		//可选,设置回调方法名称
		'oButtons'	:	buttons			//可选,设置窗口按钮,默认没有按钮
		};
		
		CoralWin.open(_param);
	}
}




//选择部门方法
coral.select.Dept = function(){

	//回调事件定义，继承coral.BaseCallback
	this.selectCallBack = new coral.BaseCallBack();
	
	/**
	 * 部门参数数据
	 */
	this.param = new Object();
	this.setParam = function(param){			
		this.param = param;
		initParam(this.param);
		this.selectCallBack.setParam(param);
	};

	//初始化参数对象
	var initParam = function(param){
	
		var ctx = param.ctx;
		if(coral.isNull(ctx))
			param.ctx = coral.getCtx();
				
		//默认单选
		var style = param.style;
		if(coral.isNull(style))
			style = '1';
		param.style = style;
	}
	
	/**
	 * 重写父类after方法,在回调方法之后调用
	 * 将数据回填到页面
	 */
	this.selectCallBack.after = function (){
		var style = "1";
		var formName = null;
		var propertys = new Array();
		if(this.param){
			var _param = this.param;
			if(_param.formName)
				formName = _param.formName;
			if(_param.style)
				style = _param.style;
			if(_param.propertys)
				propertys = _param.propertys;	
		}		
		
		//回填到表单
//		var formObj = document.forms[0];
//		if(formName != ''){
//			formObj = document.forms[formName];
//		}
		
//		if(formObj){
			if(propertys){
				var _data = this.result;
				var size = propertys.size();
				for(i=0;i<size;i++){
					var name = propertys[i].name;
					var element = propertys[i].element; 
					//写入到页面表单中
					writeElement(_data,name,element);
				}
			}			
//		}
		
	};
	
	//写入到页面表单中
	var writeElement = function(data,propertyName,element){
		coral.logger.debug("writeElement propertyName:"+propertyName + " element:" + element);		
		//回填到表单
		var elementObjs = document.getElementsByName(element);
		if(elementObjs[0]){
			var elementValue = "";
			for(var userId in data){
				coral.logger.debug("writeElement userId:"+userId);		
				var userInfo = data[userId];
				var propertyValue = userInfo[propertyName];
				if(coral.isNull(propertyValue)) propertyValue = '';				
				if(!coral.isNull(elementValue))	elementValue += ",";
				elementValue += propertyValue;
			}	
			coral.logger.debug("writeElement elementValue:" + elementValue);
			elementObjs[0].value = elementValue;
		}
	}
	
	/**
	 * 打开选择窗口
	 */
	this.open = function(){
		//确定方法事件
		var ok = function(){
			var selectElement = coral.getIframeDocument(CoralWin.IFRAME_NAME);
			//var selectElement = Ext.get[CoralWin.IFRAME_NAME].dom;
			selectElement.confirmSelected();
		}
		//重置方法事件
		var reset = function(){
			var selectElement = coral.getIframeDocument(CoralWin.IFRAME_NAME);
			selectElement.resetSelected();
		}
		//取消事件
		var cancel = function(){CoralWin.cancel();}
	
		//设置按钮
		var buttons = [
		{'name':coral.BUT_OK ,'handler':ok},
		{'name':coral.BUT_RESET,'handler':reset},
		{'name':coral.BUT_CANCLE,'handler':cancel}	
		];
		
		//设置选择人员的url
		var url = this.param.ctx;
			url += "/select/deptchoose.do?deptid=";
		if(this.param){
			var _param = this.param;
			if(_param.deptid)
				url += _param.deptid;
			if(_param.style)
				url += "&style=" + _param.style;
			if(_param.other)
				url += "&other=" + _param.other;
		}

		//设置高度与宽度
		var width=440;
		var  height=400;
		if(this.param){
			var _param = this.param;
			if(_param.width)
				width = _param.width;
			if(_param.height)
				height = _param.height;
		}
				
		var param = {
		'id'		:	'coral.SELECT_DEPT',	//id		
		'sTitle'	:	coral.SELECT_DEPT_TITLE,	//窗口标题
		'sOpenSrc'	:	url,		//打开的链接
		'bModel'	:	true, 			//可选,默认为模式窗口,false为无模式窗口
		'sWidth'	:	width,			//可选
		'sHeight'	:	height,			//可选
		'oCallback'	:	this.selectCallBack,		//可选,设置回调方法名称
		'oButtons'	:	buttons			//可选,设置窗口按钮,默认没有按钮
		};
		
		CoralWin.open(param);
	}
 }
 
 
//显示附件信息
coral.showAttachment = function(param){
	var billid="";
	var moduleCode="";
	var classname="";
	var showDivId="";
	var ctx="";

	if(param){
		var _param = param;
		if(_param.billid)
			billid = _param.billid;
		if(_param.moduleCode)
			moduleCode = _param.moduleCode;
		if(_param.classname)
			classname = _param.classname;
		if(_param.showDivId)
			showDivId = _param.showDivId;
		if(_param.ctx)
			ctx = _param.ctx;			
	}

	if(coral.isNull(billid)) return false;
	if(coral.isNull(showDivId)) return false;	
	
	var _showAttachment = function(attachments){
		var allAtts = "";
		var openAction = ctx + "/attachmentOpen.do?billid=" + billid + "&attid=";
		var downAction = ctx + "/attachmentDownload.do?billid=" + billid + "&attid=";
		for(var i = 0;i<attachments.length;i++){
			var att = attachments[i];
			var attid = att[0];
			var fileName = att[1];			
			var oAction = openAction + attid;
			var dAction = downAction + attid;
			allAtts += "<a href='" + oAction + "' target='_blank'>" + fileName + "</a>";
			allAtts += ",&nbsp;";
		}
		allAtts = allAtts.substring(0,allAtts.length-1);
		$(showDivId).innerHTML = allAtts;
	};

	attachmentInfoManagerAjaxHelper.findAttachmentList(billid,moduleCode,classname,_showAttachment);
}
 
 //附件上传的公共选择
coral.select.Attachment = function(){

	//回调事件定义，继承coral.BaseCallback
	this.selectCallBack = new coral.BaseCallBack();
	
	/**
	 * 附件上传参数数据
	 */
	this.param = new Object();
	this.setParam = function(param){			
		this.param = param;
		initParam(this.param);
		this.selectCallBack.setParam(param);
	};
	
	//初始化参数对象
	var initParam = function(param){
		if(!param) return false;
		
		var ctx = param.ctx;
		if(coral.isNull(ctx))
			param.ctx = coral.getCtx();
					
	};
	
	/**
	 * 重写父类after方法,在回调方法之后调用
	 * 将数据回填到页面
	 */
	this.selectCallBack.after = function (){coral.showAttachment(param)};
	
	/**
	 * 打开选择窗口
	 */
	this.open = function(){
	
		if(this.param['billid']==''){
			alert('请先保存业务单据再添加附件！');
			return;
		}
		//确定方法事件
		var ok = function(){
			var selectElement = coral.getIframeDocument(CoralWin.IFRAME_NAME);
			selectElement.confirmSelected();
		};
		//重置方法事件
		var reset = function(){
			var selectElement = coral.getIframeDocument(CoralWin.IFRAME_NAME);
			selectElement.resetSelected();
		};
		//取消事件
		var close = function(){CoralWin.close();};
	
		//设置按钮
		var buttons = [{'name':coral.BUT_CLOSE,'handler':close}];
		
		//设置附件上传的url
		var url = this.param.ctx;
			url += "/attachment.do?billid=";
		if(this.param){
			var _param = this.param;
			if(_param.billid)
				url += _param.billid;
			if(_param.moduleCode)
				url += "&moduleCode=" + _param.moduleCode;
			if(_param.classname)
				url += "&classname=" + _param.classname;
			if(_param.other)
				url += "&other=" + _param.other;
			if(_param.flag)
				url += "&flag=" + _param.flag;
		}

		//标题
		var title = coral.SELECT_ATTACHMENT_TITLE;
		if(this.param.title)
			title = this.param.title;

		//窗口的高X宽
		if(coral.isNull(this.param.width))
			this.param.width = '600';		
		if(coral.isNull(this.param.height))
			this.param.height = '380';
						
		//alert(title);
		var _param = {
		'id'		:	'coral.select.Attachment',
		'sTitle'	:	title,		//窗口标题
		'sOpenSrc'	:	url,		//打开的链接
		'bModel'	:	true, 			//可选,默认为模式窗口,false为无模式窗口
		'sWidth'	:	this.param.width,			//可选
		'sHeight'	:	this.param.height,			//可选
		'oCallback'	:	this.selectCallBack,		//可选,设置回调方法名称
		'oButtons'	:	buttons			//可选,设置窗口按钮,默认没有按钮
		};
		
		CoralWin.open(_param);
	}
}
