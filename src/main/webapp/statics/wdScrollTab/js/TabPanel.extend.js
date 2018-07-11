/*!
 * 首页Tab页签通用方法
 * @author ThinkGem
 * @version 2017-3-26
 */
(function(b){
	var a={
			tabPageId:null,
			getTabPage:function(){
				return b("#"+this.tabPageId);
			},
			initTabPage:function(e,c){
				this.tabPageId=e;
				var d=b.extend(true,{autoResizable:true,height:function(){return b(window).height()},items:[]},c);
				return b("#"+this.tabPageId).tabPanel(d)
			},
			addTabPage:function(g,h,d,f,e){
				console.log("g--->",g);
				var c=g?g.data("tabId"):null;
				console.log("c1--->",c);
				if(c==undefined){c="tabpanel-"+Math.uuid();g?g.attr("data-tab-id",c):null}
				console.log("c2--->",c);
				console.log("this.tabPageId--->",this.tabPageId);
				console.log("b(+this.tabPageId)--->",b("#"+this.tabPageId));
				b("#"+this.tabPageId).tabPanel(
						"addTab",
						{
							id:c,
							title:h,
							html:'<script>js.loading();<\/script><iframe id="'+c+'-frame" src="'+d+'" width="100%" height="100%" frameborder="0" onload="js.closeLoading();"></iframe>',
							closable:(f==undefined?true:f),
							onPreClose:function(i){js.closeLoading(1000,true)},
							disabled:false,
							autoResizable:true,
							widthResizable:true})
			},
			getCurrentTabPage:function(d){
				var c=b("#"+this.tabPageId).tabPanel("getActiveTab");
				var g=b("#"+c.id+"-frame");
				if(g.length>0&&typeof d=="function"){
					try{
						d(g[0].contentWindow)
					}catch(f){
						js.error(f)}}
				return g},
			getPrevTabPage:function(c,f){
				var d=b("#"+this.tabPageId).tabPanel("getActiveTab");
				var h=b("#"+d.preTabId+"-frame");
				if(h.length>0&&typeof c=="function"){
					try{c(h[0].contentWindow)}catch(g){js.error(g)}}
				if(f==true){b("#"+this.tabPageId).tabPanel("kill",d.id)}
				return h
			},
			closeCurrentTabPage:function(c){getPrevTabPage(c,true)},
			setRenderWH:function(wh){
				b("#"+this.tabPageId).tabPanel("setRenderWH",wh);
			}
	};
	window.tabPage=a
})(window.jQuery);