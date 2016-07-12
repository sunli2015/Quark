(function($){
	
	var golbalProp = {
			listUrl : '',//列表URL
			paginationId : '#pp',//分页DIV的ID
			formDialogId : '#dlg',//表单dialogDIV的id
			formId : '#ff',//表单ID
			editParamId : 'id',//编辑参数的ID
			datagrid:true//表格数据
	};
	/**
	 * load data
	 */
	function _loadData(obj,options){
		var def = {
				curPage:1,
				pageSize:10,
				url:'',
				fnCallback:function(){},
				qparam:{}
		};
		
		var prop = $.extend({},def,options);
		$.extend(golbalProp,{listUrl:prop.url});
		
		var url = prop.url;//CONTEXT_PATH+"/resource/list.do?moduleId=${param.moduleId}"+param;
		//query param ajax post to server
		var _qparam = $.extend({},{
			'curPage':prop.curPage,
			'pageSize':prop.pageSize},prop.qparam);
		
		
		$.post(url,_qparam,function(result){
			//console.log("result",result);
			var list = result.data.data;
			obj.datagrid("loadData",list);
			var _total = result.data.total;
			var _pageSize = result.data.pageSize;
			var pageObj = obj.datagrid("getPager");
			pageObj.pagination({
			    total:_total,
			    pageSize:_pageSize,
			    pageNumber:prop.curPage,
			    onSelectPage:function(pageNumber, pageSize){
					$(this).pagination('loading');
					console.log('pageNumber:'+pageNumber+',pageSize:'+pageSize);
					obj.loadData({
						curPage:pageNumber,
						pageSize:pageSize,
						url:prop.url,
						qparam:prop.qparam
					});
					$(this).pagination('loaded');
					prop.fnCallback();
				}
			});
			prop.fnCallback();
		});
	};
	$.fn.extend({
		initProp:function(options){
			$.extend(golbalProp,options);
		},
		loadData:function(options){
			return this.each(function(){
				var obj = $(this);
				_loadData(obj,options);
			});
		},
		removeit:function(options){
			var def = {
					url:'',
					datagrid:true,
					fnCallback:function(obj){
						var pageObj = obj.datagrid("getPager");
						
						var pageSize = pageObj.pagination('options').pageSize;
						 var pageNumber = pageObj.pagination('options').pageNumber;
						 obj.loadData({
							 curPage:pageNumber,
							 pageSize:pageSize,
							 url:golbalProp.listUrl
						 });
					}
			};
			var prop = $.extend({},def,options);
			console.log(prop);
			
			return this.each(function(){
				var obj = $(this);
				var url = prop.url;
				if(prop.datagrid){
					var row = obj.datagrid('getSelected');
					 console.log("row:",row);
					 if(null == row){
						 $.messager.alert('提示','请选择一条记录','info');
						 return ;
					 }
					 url = prop.url+"?"+golbalProp.editParamId+"="+row.oid;//CONTEXT_PATH+"/resource/delete.do?id="+row.oid;
				}
				console.log(prop);
				 $.messager.confirm('删除','你确定要删除吗？',function(r){
					 if(r){
						 $.get(url,function(result){
							 var code = result.code;
							 var errMsg = result.errMsg;
							 var msg = "删除成功 ";
							 if(code != '0'){
								 msg += "删除失败："+errMsg;
							 } 
							 $.messager.alert('提示',msg,'info',function(){
								 prop.fnCallback(obj);
							 });
						 });
					 }
				 });
			});
		},
		appendit:function(fnSetMultiParam){
			return this.each(function(){
				$(golbalProp.formDialogId).dialog('open').dialog('center').dialog('setTitle','新增');
		        $(golbalProp.formId).form('clear');
		        if(fnSetMultiParam) fnSetMultiParam();
			});
		},
		editit:function(options){
			var def = {
					url:'',
					datagrid:true,
					fnSetMultiParam:function(data){}
			};
			var prop = $.extend({},def,options);
			
			 return this.each(function(){
				 var obj = $(this);
				 var url = "";
				 if(!prop.datagrid){
					 url = prop.url;
				 } else {
					 var row = obj.datagrid('getSelected');
					 console.log("row:",row);
					 if(null == row){
						 $.messager.alert('提示','请选择一条记录','info');
						 return ;
					 }
					 url = prop.url+"?"+golbalProp.editParamId+"="+row.oid;//CONTEXT_PATH+"/resource/edit.do?id="+row.oid;
				 }
				 
				 $.get(url,function(result){
					 var data = result.data.data;
					 console.log("edit row:",data);
					 $(golbalProp.formDialogId).dialog('open').dialog('center').dialog('setTitle','修改');
					 $(golbalProp.formId).form('load',data);
					 if(prop.fnSetMultiParam) prop.fnSetMultiParam(data);
				 });
			 });
		},
		saveit:function(options){
			var def = {
					url:'',
					fnCallback:function(obj){//默认是表格回调处理
						var id = $('#'+golbalProp.editParamId).val();
						var pageObj = obj.datagrid("getPager");
						
			    		 var pageSize = pageObj.pagination('options').pageSize;
			    		 var pageNumber = 1;
			    		 if(id){
			    			 pageNumber = pageObj.pagination('options').pageNumber;
			    			 console.log('edit save:'+pageNumber);
			    		 }
			    		 obj.loadData({
								 curPage:pageNumber,
								 pageSize:pageSize,
								 url:golbalProp.listUrl});
					},
					fnOnSubmit:function(){return true;}
			};
			var prop = $.extend({},def,options);
			
			return this.each(function(){
				var obj = $(this);
				$(golbalProp.formId).form('submit', {
				    url:prop.url,//CONTEXT_PATH+"/resource/save.do",
				    onSubmit: function(){
				    	return prop.fnOnSubmit();
				    },
				    success:function(data){
				    	console.log("save===》"+data);
				    	var data = eval('(' + data + ')');  // change the JSON string to javascript object
				    	var msg = "保存成功";
				    	if(data.code != '0'){
				    		msg = '保存失败：'+data.errMsg;
				    	}
				    	$.messager.alert('提示',msg,'info',function(){
				    		 $(golbalProp.formDialogId).dialog('close');
				    		 prop.fnCallback(obj);
				    	});
				    }
				});
			});
		},
		reloadData:function(options){
			return this.each(function(){
				var obj = $(this);
				var pageObj = obj.datagrid("getPager");
	    		var pageSize = pageObj.pagination('options').pageSize;
	    		var pageNumber = pageObj.pagination('options').pageNumber;
	    		_loadData(obj,{
					 curPage:pageNumber,
					 pageSize:pageSize,
					 url:golbalProp.listUrl,
					 qparam:options.qparam});
			});
		}
		
	});
})(jQuery);
