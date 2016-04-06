(function($){
	
	var golbalProp = {
			listUrl : '',//列表URL
			paginationId : '#pp',//分页DIV的ID
			formDialogId : '#dlg',//表单dialogDIV的id
			formId : '#ff',//表单ID
			editParamId : 'id',//编辑参数的ID
	};
	$.fn.extend({
		initProp:function(options){
			$.extend(golbalProp,options);
		},
		loadData:function(options){
			var def = {
					isPagination:true,
					curPage:1,
					pageSize:10,
					url:''
			};
			
			var prop = $.extend({},def,options);
			
			$.extend(golbalProp,{listUrl:prop.url});
			
			return this.each(function(){
				var obj = $(this);
				var url = prop.url;//CONTEXT_PATH+"/resource/list.do?moduleId=${param.moduleId}"+param;
				console.log("url:",url);
				$.post(url,{
					'curPage':prop.curPage,
					'pageSize':prop.pageSize},function(result){
					console.log("result",result);
					var list = result.data.data;
					obj.datagrid("loadData",list);
					if(!prop.isPagination){
						return ;
					}
					var _total = result.data.total;
					var _pageSize = result.data.pageSize;
					$(golbalProp.paginationId).pagination({
					    total:_total,
					    pageSize:_pageSize,
					    pageNumber:prop.curPage,
					    onSelectPage:function(pageNumber, pageSize){
							$(this).pagination('loading');
							console.log('pageNumber:'+pageNumber+',pageSize:'+pageSize);
							obj.loadData({
								isPagination:false,
								curPage:pageNumber,
								pageSize:pageSize,
								url:prop.url
							});
							$(this).pagination('loaded');
						}
					});

				});
			});
		},
		removeit:function(options){
			var def = {
					url:''
			};
			var prop = $.extend({},def,options);
			
			
			return this.each(function(){
				var obj = $(this);
				
				var row = obj.datagrid('getSelected');
				 console.log("row:",row);
				 if(null == row){
					 $.messager.alert('提示','请选择一条记录','info');
					 return ;
				 }
				 $.messager.confirm('删除','你确定要删除吗？',function(r){
					 if(r){
						 var url = prop.url+"?"+golbalProp.editParamId+"="+row.oid;//CONTEXT_PATH+"/resource/delete.do?id="+row.oid;
						 $.get(url,function(result){
							 var code = result.code;
							 var errMsg = result.errMsg;
							 var msg = "删除成功 ";
							 if(code != '0'){
								 msg += "删除失败："+errMsg;
							 } 
							 $.messager.alert('提示',msg,'info',function(){
								 var pageSize = $(golbalProp.paginationId).pagination('options').pageSize;
								 var pageNumber = $(golbalProp.paginationId).pagination('options').pageNumber;
								 obj.loadData({
									 isPagination:true,
									 curPage:pageNumber,
									 pageSize:pageSize,
									 url:golbalProp.listUrl
								 });
								
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
					fnSetMultiParam:function(data){}
			};
			var prop = $.extend({},def,options);
			
			 return this.each(function(){
				 var obj = $(this);
				 
				 var row = obj.datagrid('getSelected');
				 console.log("row:",row);
				 if(null == row){
					 $.messager.alert('提示','请选择一条记录','info');
					 return ;
				 }
				 var url = prop.url+"?"+golbalProp.editParamId+"="+row.oid;//CONTEXT_PATH+"/resource/edit.do?id="+row.oid;
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
					url:''
			};
			var prop = $.extend({},def,options);
			
			return this.each(function(){
				var obj = $(this);
				$(golbalProp.formId).form('submit', {
				    url:prop.url,//CONTEXT_PATH+"/resource/save.do",
				    onSubmit: function(){
				        // do some check
				        // return false to prevent submit;
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
				    		 var id = $('#'+golbalProp.editParamId).val();
				    		 var pageSize = $(golbalProp.paginationId).pagination('options').pageSize;
				    		 var pageNumber = 1;
				    		 if(id){
				    			 pageNumber = $(golbalProp.paginationId).pagination('options').pageNumber;
				    			 console.log('edit save:'+pageNumber);
				    		 }
				    		 obj.loadData({isPagination:true,
									 curPage:pageNumber,
									 pageSize:pageSize,
									 url:golbalProp.listUrl});
				    	});
				    }
				});
			});
		}
		
	});
})(jQuery);
