/*
 * Ext JS Library 1.0
 * Copyright(c) 2006-2007, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://www.extjs.com/license
 */

// create the CoralWin application (single instance)
var _CoralWinDefine = function(){
    // everything in this space is private and only accessible in the HelloWorld block
    
    // define some private variables
    var win;
    var winGroup = new Ext.WindowGroup();  
    var width = "";
    var height = "";
    var openSrc = "";
    var title = "";
    var model = true;
    var callback = null;
    var frame = "";
    var contentHtmlOng = '<iframe id="${iframeId}" src="${openSrc}" frameborder="0" scrolling="auto" style="height:100%;width:100%;border:0px none;"></iframe>';
    
    //button name and handler
   var oButtons1 = new Array();
     // var oButtons1 =null;
    
    var toggleTheme = function(){
       // Ext.get(document.body, true).toggleClass('ytheme-gray');
    };
    
    // return a public interface
    return {
    	//init
        init : function(){},
        //window open
        open : function(obj){
			this.id = obj.id==null ? '':obj.id;        
	      	this.width = obj.sWidth==null ? '600':obj.sWidth;
        	this.height = obj.sHeight==null ? '500':obj.sHeight;
			this.model = obj.bModel==null ? true:obj.bModel;
			this.title =  obj.sTitle==null ? " ":obj.sTitle;
			this.minimizable = obj.bMinimizable ? true:false;
			this.maximizable = obj.bMaximizable ? true:false;
						
			this.callback = obj.oCallback==null ? "":obj.oCallback;
        	this.openSrc = obj.sOpenSrc;
 			this.buttons = obj.oButtons==null ? null:obj.oButtons;
        	this.showDialog();
        	return win;
        },
        //cancel not call back  
        cancel : function(result){win.close();},  
        //window close
        close : function(result){
	      	win.close();
	      	//执行回调
	      	//alert(typeof(this.callback));	      	
	      	if(typeof(this.callback)=='object' 
	      		|| typeof(this.callback)=='function'){
	      		try{
		      		if(result) this.callback.setResult(result);
		      		this.callback.before();
	    	  		this.callback.execute();
		      		this.callback.after();
	      		}catch(e){
	      			alert('Method setResult() or execute() not finded!');
	      		}
	      	}
	      		      	
        },   
		//private function
        showDialog : function(){
        
			//frame name
			this.IFRAME_NAME = "coral-win-iframe";
			
			win = winGroup.get(this.id);
			if(!win){

				var contentHtml = contentHtmlOng;
				contentHtml = contentHtml.replace("${iframeId}",this.IFRAME_NAME);
				contentHtml = contentHtml.replace("${openSrc}",this.openSrc);

				var intW = parseInt(this.width);
				var intH = parseInt(this.height);
				//alert('w:'+intW+"  h:"+intH);				
				
	            win = new Ext.Window({
	            	id : this.id,
	                //el:'coral-win',
	                layout:'fit',
	                width:intW,
	                height:intH,
	                modal:this.model,
	                plain: true,
	                listeners: {'close':{fn: this.makesureClose}},
	                items: new Ext.Panel({
	                    border:false,
	                    html:contentHtml
	                }),
	                minimizable: this.minimizable,
	                maximizable: this.maximizable  , 
                	animCollapse:false,
                	constrainHeader:true	                            
	             },{
	                manager: winGroup
	            });
	            if(this.buttons != null){
		            	var size = this.buttons.length;
		            	for(var i =0;i<size;i++){
		             		var btnName = this.buttons[i].name;
			            	var btnHandler = this.buttons[i].handler;
			            	win.addButton(btnName,btnHandler);
		            	}                	
		            }		         			
				 win.setTitle(this.title);  		
          
        	}
            
            win.show(this); 
         
        },
        makesureClose:function(){
        	//alert("makesure close!");
        }
	}
}
// using onDocumentReady instead of window.onload initializes the application
// when the DOM is ready, without waiting for images and other resources to load
var CoralWin =  new  _CoralWinDefine();
//var CoralWin = new _CoralWinDefine();
Ext.onReady(CoralWin.init, CoralWin, true);  
//Ext.EventManager.onDocumentReady(CoralWin.init, CoralWin, true);
