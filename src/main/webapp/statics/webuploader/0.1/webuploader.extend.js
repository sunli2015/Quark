(function(b){var a=function(k,p){var s=b.extend({},b.fn.webuploader.defaults,k),w=s.id,l="",v=s.uploadType=="image",x=b("#"+w+"Uploader"),o=x.find("#"+w+"fileLists");if(v){o.appendTo(x.find(".queueList"))}var F=x.find(".statusBar"),e=F.find(".info"),d=x.find(".uploadBtn"),n=x.find(".placeholder"),u=F.find(".progress").hide(),G=0,t=0,q=window.devicePixelRatio||1,r=110*q,c=110*q,m="pedding",H={},I=(function(){var J;try{J=navigator.plugins["Shockwave Flash"];J=J.description}catch(K){try{J=new ActiveXObject("ShockwaveFlash.ShockwaveFlash").GetVariable("$version")}catch(L){J="0.0"}}J=J.match(/\d+/g);return parseFloat(J[0]+"."+J[1],10)})(),D=(function(){var J=document.createElement("p").style,K="transition" in J||"WebkitTransition" in J||"MozTransition" in J||"msTransition" in J||"OTransition" in J;J=null;return K})(),E=[],j=[],B=[],i=[],A={bizType:s.bizType,bizKey:s.bizKey,uploadType:s.uploadType,imageMaxWidth:s.imageMaxWidth,imageMaxHeight:s.imageMaxHeight,};if(WebUploader.browser.ie&&!WebUploader.Uploader.support("flash")){if(I){(function(J){window.expressinstallcallback=function(M){switch(M){case"Download.Cancelled":alert("您取消了更新！");break;case"Download.Failed":alert("安装失败！");break;default:alert("安装已成功，请刷新！");break}delete window.expressinstallcallback};var L=ctxStatic+"/webuploader/0.1/expressInstall.swf";var K='<object type="application/x-shockwave-flash" data="'+L+'" ';if(WebUploader.browser.ie){K+='classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" '}K+='width="100%" height="100%" style="outline:0"><param name="movie" value="'+L+'" /><param name="wmode" value="transparent" /><param name="allowscriptaccess" value="always" /></object>';J.html(K)})(x)}else{x.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>')}return}else{if(!WebUploader.Uploader.support()){js.showMessage("文件上传组件不支持您的浏览器，请使用高版本浏览器！");return}}var k={};if(!s.readonly){k=b.extend({},{pick:{id:"#"+w+"filePicker",label:"点击选择文件"},dnd:"#"+w+"Uploader .queueList",paste:"#"+w+"Uploader .queueList"});if(s.uploadType=="image"){k.pick.label="点击选择图片";k.accept={title:"Images",extensions:s.imageAllowSuffixes.replace(/\./g,""),mimeTypes:"image/*"}}else{if(s.uploadType=="media"){k.pick.label="点击选择视频";k.accept={title:"Medias",extensions:s.mediaAllowSuffixes.replace(/\./g,""),mimeTypes:"*/*"}}else{if(s.uploadType=="file"){k.accept={title:"Files",extensions:s.fileAllowSuffixes.replace(/\./g,""),mimeTypes:"*/*"}}else{k.accept={title:"All",extensions:(s.imageAllowSuffixes+s.mediaAllowSuffixes+s.fileAllowSuffixes).replace(/\./g,""),mimeTypes:"*/*"}}}}}k=b.extend({},{disableGlobalDnd:true,swf:ctxStatic+"/webuploader/0.1/Uploader.swf",server:ctxAdmin+"/file/upload",formData:A,threads:1,fileNumLimit:s.maxUploadNum,fileSingleSizeLimit:s.maxFileSize,compress:false},k);var z=WebUploader.create(k);if(!window.webuploader){window.webuploader=[]}window.webuploader.push(z);if(!window.webuploaderRefresh){window.webuploaderRefresh=function(){setTimeout(function(){for(var J in window.webuploader){window.webuploader[J].refresh()}},200)}}z.on("dndAccept",function(L){var K=false,J=L.length,M=0,N="text/plain;application/javascript ";for(;M<J;M++){if(~N.indexOf(L[M].type)){K=true;break}}return !K});z.addButton({id:"#"+w+"filePicker2",label:"继续添加"});if(!s.isLazy){d.hide()}function C(L,J,M){if(s.returnPath){B.push(J);i.push(M);b("#"+s.filePathInputId).val(B.join("|")).change();b("#"+s.fileNameInputId).val(i.join("|")).change();try{b("#"+s.filePathInputId).valid()}catch(K){}try{b("#"+s.fileNameInputId).valid()}catch(K){}}else{E.push(L);b("#"+w).val(E.join(",")).change();try{b("#"+w).valid()}catch(K){}}}function h(L){var M=L.attr("fileUploadId");if(M&&M!=null){if(s.returnPath){var J=L.attr("fileUrl");var N=L.attr("fileName");B.splice(b.inArray(J,B),1);i.splice(b.inArray(N,i),1);b("#"+s.filePathInputId).val(B.join("|"));b("#"+s.fileNameInputId).val(i.join("|"));try{b("#"+s.filePathInputId).valid()}catch(K){}try{b("#"+s.fileNameInputId).valid()}catch(K){}}else{E.splice(b.inArray(M,E),1);j.push(M);b("#"+w).val(E.join(","));b("#"+w+"__del").val(j.join(","));try{b("#"+w).valid()}catch(K){}try{b("#"+w+"__del").valid()}catch(K){}}}}function y(L){var K,J;if(L===m){return}d.removeClass("state-"+m);d.addClass("state-"+L);m=L;switch(m){case"pedding":n.removeClass("element-invisible");o.hide();F.addClass("element-invisible");break;case"ready":n.addClass("element-invisible");b("#"+w+"filePicker2").removeClass("element-invisible");o.show();F.removeClass("element-invisible");break;case"uploading":b("#"+w+"filePicker2").addClass("element-invisible");u.show();d.text("暂停上传");break;case"paused":u.show();d.text("继续上传");break;case"confirm":u.hide();b("#"+w+"filePicker2").removeClass("element-invisible");d.text("开始上传");J=z.getStats();if(J.successNum&&!J.uploadFailNum){y("finish");return}break;case"finish":J=z.getStats();if(J.successNum){}else{m="done"}break}g()}function f(){var J=0,M=0,K=u.children(),L;b.each(H,function(O,N){M+=N[0];J+=N[0]*N[1]});L=M?J/M:0;K.eq(0).text(Math.round(L*100)+"%");K.eq(1).css("width",Math.round(L*100)+"%");g()}function g(){var K="",J=z.getStats();if(m==="confirm"&&J.uploadFailNum){K=J.uploadFailNum+(v?"张图片":"个文件")+'上传失败，<a class="retry" href="#">重新上传</a>失败或<a class="ignore" href="#">忽略</a>'}else{if(m==="confirm"||m==="ready"){K="共"+G+(v?"张图片":"个文件")+(t<=0?"":"（"+WebUploader.formatSize(t)+"）")}else{K="已成功上传"+G+(v?"张图片":"个文件")+(t<=0?"":"（"+WebUploader.formatSize(t)+"）");if(J.uploadFailNum){K+="，失败"+J.uploadFailNum+"个"}}}e.html(K);if(G<s.maxUploadNum){b("#"+w+"filePicker2").show()}else{b("#"+w+"filePicker2").hide()}window.webuploaderRefresh()}z.onUploadProgress=function(K,J){var L;if(v){L=b("#"+w+K.id),$percent=L.find(".progress span");$percent.css("width",J*100+"%")}else{L=b("#"+w+K.id).find(".prog_bar"),$percent=L.find(".progress-bar");$percent.css("width",Math.round(J*100)+"%");$percent.text(Math.round(J*100)+"%")}H[K.id][1]=J;f()};z.onFileQueued=function(J){if(G>=s.maxUploadNum){js.showMessage("您只能上传"+s.maxUploadNum+"个文件");return}js.loading("正在验证文件，请稍等。");d.addClass("disabled");z.md5File(J,0,10*1024*1024).then(function(Q){G++;t+=J.size;if(G===1&&!s.readonly){F.show()}if(v){var R=b('<li id="'+w+J.id+'"><input id="'+w+J.id+'_md5" type="hidden" value="'+Q+'"/><p id="'+w+J.id+'_name" class="title">'+J.name+'</p><p class="imgWrap"></p><p class="progress"><span></span></p></li>'),N=b('<div class="file-panel"><span class="cancel">删除</span></div>').appendTo(R),M=R.find("p.progress-bar"),L=R.find("p.imgWrap"),P=b('<p class="error"></p>'),K=function(S){var T="";switch(S){case"exceed_size":T="文件大小超出";break;case"interrupt":T="文件传输中断";break;case"http":T="HTTP请求错误";break;case"not_allow_type":T="文件格式不允许";break;default:T="上传失败，请重试";break}if(T!=null){P.text(T).appendTo(R)}};if(J.getStatus()==="invalid"){K(J.statusText)}else{L.text("预览中");z.makeThumb(J,function(T,U){if(T){L.text("不能预览");return}var S=b('<img src="'+U+'">');L.empty().append(S)},r,c);H[J.id]=[J.size,0];J.rotation=0}J.on("statuschange",function(T,S){if(S==="progress"){M.hide().width(0)}if(T==="error"||T==="invalid"){K(J.statusText);H[J.id][1]=1}else{if(T==="interrupt"){K("interrupt")}else{if(T==="queued"){H[J.id][1]=0}else{if(T==="progress"){P.remove();M.css("display","block")}}}}R.removeClass("state-"+S).addClass("state-"+T)});R.on("mouseenter",function(){N.stop().animate({height:30})});R.on("mouseleave",function(){N.stop().animate({height:0})});N.on("click","span",function(){var U=b(this);switch(U.index()){case 0:var S=b(this);js.confirm("确定删除该图片吗？",function(V){h(S);z.removeFile(J)});return;case 1:J.rotation+=90;break;case 2:J.rotation-=90;break}if(D){var T="rotate("+J.rotation+"deg)";L.css({"-webkit-transform":T,"-mos-transform":T,"-o-transform":T,transform:T})}else{L.css("filter","progid:DXImageTransform.Microsoft.BasicImage(rotation="+(~~((J.rotation/90)%4+4)%4)+")")}})}else{var R=b('<tr id="'+w+J.id+'" class="template-upload"><input id="'+w+J.id+'_md5" type="hidden" value="'+Q+'"/><td id="'+w+J.id+'_name" class="name">'+J.name+'</td><td class="size">'+WebUploader.formatSize(J.size)+'</td><td class="prog_bar"><p class="progress"><span class="progress-bar">0%</span></p></td></tr>'),M=R.find(".progress-bar"),P=b('<td class="msg"></td>'),K=function(S){var T="";switch(S){case"exceed_size":T="<span class='label label-sm label-danger'>文件大小超出</span>";break;case"interrupt":T="<span class='label label-sm label-danger'>文件传输中断</span>";break;case"http":T="<span class='label label-sm label-danger'>HTTP请求错误</span>";break;case"not_allow_type":T="<span class='label label-sm label-danger'>文件格式不允许</span>";break;default:T="<span class='label label-sm label-danger'>上传失败，请重试</span>";break}if(T!=null){P.html(T)}};if(J.getStatus()==="invalid"){K(J.statusText);P.appendTo(R)}else{H[J.id]=[J.size,0];J.rotation=0;P.text("等待上传").appendTo(R)}J.on("statuschange",function(T,S){if(S==="progress"){}if(T==="error"||T==="invalid"){K(J.statusText);H[J.id][1]=1;M.text("0%").css("width","0%")}else{if(T==="interrupt"){K("interrupt");M.text("0%").css("width","0%")}else{if(T==="progress"){P.text("正在上传");M.css("display","block")}else{if(T==="complete"){}}}}R.removeClass("state-"+S).addClass("state-"+T)});N=b('<td class="btncancel"><a class="btn btn-default btn-xs yellow"><i class="fa fa-ban"></i> 取消 </a></td>').appendTo(R);N.on("click","a",function(){var T=b(this);switch(T.index()){case 0:z.removeFile(J);return;case 1:var S=b(this);js.confirm("确定删除该文件吗？",function(U){h(S);z.removeFile(J)});return}})}R.appendTo(o);y("ready");f();var O=null;b.ajax({type:"POST",url:ctxAdmin+"/file/upload",data:{fileMd5:Q,fileName:J.name},cache:false,async:false,timeout:10000,dataType:"json",success:function(S){if(S.result=="true"){O=S.fileUpload;if(v){O.message='<p class="error" title="'+S.message+'">'+S.message+"</p>"}else{O.progress='<p class="progress"><span class="progress-bar" style="display:block;width:100%;">100%</span></p>';O.message='<span class="label label-sm label-success" title="'+S.message+'">'+S.message+"</span>"}}}});if(O){z.removeFile(J);p.refreshFileList([O],false)}d.removeClass("disabled");if(!s.isLazy){d.click()}js.closeLoading()})};z.on("uploadAccept",function(P,J,N){var M=(J._raw||J);var K=JSON.parse(M);if(K.result=="false"){N(K.code)}var Q=b("#"+w+P.file.id);try{var M=(J._raw||J),K=JSON.parse(M);if(v){b('<p class="error" title="'+K.message+'">'+K.message+"</p>").appendTo(Q)}else{var L=(K.result=="true")?"success":"danger";Q.find(".msg").html('<span class="label label-sm label-'+L+'" title="'+K.message+'">'+K.message+"</span>")}}catch(O){if(v){b('<p class="error">服务器返回出错</p>').appendTo(Q)}else{Q.find(".msg").html('<span class="label label-sm label-danger">服务器返回出错</span>')}}});z.on("uploadBeforeSend",function(J,K,L){L.X_Requested_With="XMLHttpRequest";K.fileMd5=b("#"+w+K.id+"_md5").val();K.fileName=b("#"+w+K.id+"_name").text()});z.on("uploadSuccess",function(K,P){var J=b("#"+w+K.id).find(".btncancel");var N=b("#"+w+K.id).find(".file-panel");var R=b("#"+w+K.id).find(".progress-bar");try{var Q=(P._raw||P),M=JSON.parse(Q);if(M.result=="true"){var S=M.fileUpload,T=(js.startWith(S.fileEntity.fileUrl,ctxPath)?"":ctxPath)+S.fileEntity.fileUrl,L=(S.id==""?T:ctxAdmin+"/file/download/"+S.id);if(v){N.find(".cancel").attr("fileUploadId",S.id).attr("fileUrl",T).attr("fileName",S.fileName).attr("fileSize",S.fileEntity.fileSize)}else{J.find("a").hide();b('<a class="btn btn-danger btn-xs" fileUploadId="'+S.id+'" fileUrl="'+T+'" fileName="'+S.fileEntity.fileName+'" fileSize="'+S.fileEntity.fileSize+'"><i class="fa fa-trash-o"></i> 删除</a> &nbsp;<a class="btn btn-info btn-xs" target="_blank" href="'+ctxAdmin+"/file/download/"+S.id+'" ><i class="fa fa-download"></i> 下载</a>').appendTo(J)}C(S.id,T,S.fileName)}else{R.css("width","0%").text("0%")}}catch(O){R.css("width","0%").text("0%");error(O)}g()});z.onFileDequeued=function(J){G--;if(!G){y("pedding")}t-=J.size;delete H[J.id];f();if(v){b("#"+w+J.id).off().find(".file-panel").off().end().remove()}else{b("#"+w+J.id).remove()}};z.on("all",function(K,L){var J;switch(K){case"uploadFinished":y("confirm");break;case"startUpload":y("uploading");break;case"stopUpload":y("paused");break}});z.onError=function(J){var K="";switch(J){case"Q_TYPE_DENIED":K="文件类型不对";break;case"F_EXCEED_SIZE":K="文件大小超出";break;case"F_DUPLICATE":K="不要选择重复文件";break;case"Q_EXCEED_NUM_LIMIT":K="您只能上传"+s.maxUploadNum+"个文件";break;case"Q_EXCEED_SIZE_LIMIT":K="文件总大小超出";break;default:K="上传错误，请重试";break}js.showMessage(K)};d.on("click",function(){if(b(this).hasClass("disabled")){return false}if(m==="ready"){z.upload()}else{if(m==="paused"){z.upload()}else{if(m==="uploading"){z.stop()}}}});e.on("click",".retry",function(){z.retry();return false});e.on("click",".ignore",function(){var K,J,L=z.getFiles("error");for(J=0;K=L[J++];){z.removeFile(K)}y("finish");f();return false});d.addClass("state-"+m);f();p.refreshFileList=function(N,K){if(K){o.empty();G=0;t=0;E=[];B=[];i=[]}if(N&&N.length>0){for(var M=0;M<N.length;M++){var L=N[M],J=(js.startWith(L.fileEntity.fileUrl,ctxPath)?"":ctxPath)+L.fileEntity.fileUrl,O=(L.id==""?J:ctxAdmin+"/file/download/"+L.id);if(v){$li=b('<li id="'+L.id+'"><p class="title"><a target="_blank" href="'+O+'">'+L.fileName+'</a></p><p class="imgWrap"><img src="'+J+'"/></p><p class="progress"><span></span></p><div class="file-panel"><span class="cancel '+(!s.readonly?"":"hide")+'" fileUploadId="'+L.id+'" fileUrl="'+J+'" fileName="'+L.fileName+'" fileSize="'+L.fileEntity.fileSize+'">删除</span></div>'+(L.message?L.message:"")+"</li>"),$li.on("click",".imgWrap img",function(){var Q=b(this),S=Q.attr("src"),T="#outerdiv",R="#innerdiv",P="#bigimg";if(b(T).length==0){b('<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:99999;width:100%;height:100%;display:none;"><div id="innerdiv" style="position:fixed;"><img id="bigimg" style="border:2px solid #fff;" src="" /></div></div>').appendTo(document.body)}b(P).attr("src",S);b("<img/>").attr("src",S).load(function(){var U=b(window).width();var Y=b(window).height();var ab=this.width;var X=this.height;var Z,aa,V=0.8;if(X>Y*V){aa=Y*V;Z=aa/X*ab;if(Z>U*V){Z=U*V}}else{if(ab>U*V){Z=U*V;aa=Z/ab*X}else{Z=ab;aa=X}}b(P).css("width",Z);var ac=(U-Z)/2;var W=(Y-aa)/2;b(R).css({top:W,left:ac});b(T).fadeIn("fast")});b(T).click(function(){b(this).fadeOut("fast")})});$li.on("mouseenter",function(){var P=b(this).index();o.find(".file-panel").eq(P).stop().animate({height:30})});$li.on("mouseleave",function(){var P=b(this).index();o.find(".file-panel").eq(P).stop().animate({height:0})});$li.on("click","span",function(){var T=b(this),U=b(this).closest("li"),Q=b(this).parent().data("fileRotation");if(!Q){Q=0}switch(T.index()){case 0:if(!s.readonly){var T=b(this);js.confirm("确定删除该图片吗？",function(W){h(T);if(s.returnPath){z.onFileDequeued({id:0,size:0})}else{var V=T.attr("fileSize");z.onFileDequeued({id:0,size:V})}U.remove()})}return;case 1:Q+=90;break;case 2:Q-=90;break}var P=b(this).parent().parent().index();var S=o.find(".imgWrap :eq("+P+")");if(D){var R="rotate("+Q+"deg)";S.css({"-webkit-transform":R,"-mos-transform":R,"-o-transform":R,transform:R})}else{S.css("filter","progid:DXImageTransform.Microsoft.BasicImage(rotation="+(~~((Q/90)%4+4)%4)+")")}b(this).parent().data("fileRotation",Q)})}else{$li=b('<tr id="'+L.id+'"  class="template-upload"><td class="name">'+L.fileName+'</td><td class="size">'+(s.returnPath?"":WebUploader.formatSize(L.fileEntity.fileSize))+'</td><td class="prog_bar">'+(L.progress?L.progress:"")+'</td><td class="msg">'+(L.message?L.message:"")+'</td><td class="btncancel">'+(s.preview!=""?'<a class="btn btn-default btn-xs preview" herf="javascript:void(0);" previewUrl="'+J+"?fileName="+L.fileName+"&preview="+s.preview+'"><i class="fa fa-eye"></i> 预览</a> &nbsp;':"")+'<a class="btn btn-danger btn-xs delete'+(!s.readonly?"":"hide")+'" fileUploadId="'+L.id+'" fileUrl="'+J+'" fileName="'+L.fileName+'" fileSize="'+L.fileEntity.fileSize+'"><i class="fa fa-trash-o"></i> 删除 </a> &nbsp;<a class="btn btn-info btn-xs blue" target="_blank" href="'+O+'"><i class="fa fa-download"></i> 下载 </a></td></tr>');$li.on("click","a.preview",function(){js.addTabPage(b(this),"文件查看",b(this).attr("previewUrl"),true,false)});if(!s.readonly){$li.on("click","a.delete",function(){var Q=b(this),P=b(this).closest("tr");js.confirm("确定删除该文件吗？",function(S){h(Q);if(s.returnPath){z.onFileDequeued({id:0,size:0})}else{var R=Q.attr("fileSize");z.onFileDequeued({id:0,size:0})}P.remove()});return})}}G++;t+=L.fileEntity.fileSize;o.append($li);C(L.id,J,L.fileName)}}if(E.length>0||B.length>0){if(!s.readonly){F.show()}y("ready")}f()};p.refreshFileListByPath=function(){var L=[],N=[],M,J=b("#"+s.filePathInputId).val(),K=b("#"+s.fileNameInputId).val();if(J!=undefined&&J!=""){N=J.split("|")}if(K!=undefined&&K!=""){M=K.split("|")}if(M==undefined||M.length!=N.length){M=N}b.each(N,function(O,R){var Q=M[O].split("/");var P=Q[Q.length-1];L.push({id:P.split(".")[0],fileName:P,fileEntity:{fileUrl:R,fileSize:0},})});p.refreshFileList(L,true)};p.refreshFileListByBizData=function(){b.ajax({url:ctxAdmin+"/file/fileList?___t="+new Date().getTime(),data:{bizKey:s.bizKey,bizType:s.bizType},dataType:"json",success:function(J){if(!(J.result=="false")){p.refreshFileList(J,true)}}})};if(s.returnPath){p.refreshFileListByPath()}else{p.refreshFileListByBizData()}return p};b.fn.webuploader=function(d,f){var e;var c=this.each(function(){var i=b(this);var h=i.data("webuploader");var g=typeof d==="object"&&d;if(!h){i.data("webuploader",(h=new a(g,i)))}if(typeof d==="string"&&typeof h[d]==="function"){if(f instanceof Array){e=h[d].apply(h,f)}else{e=h[d](f)}}});return(e===undefined)?c:e};b.fn.webuploader.defaults={id:"",bizKey:"",bizType:"",readonly:false,returnPath:false,filePathInputId:"",fileNameInputId:"",uploadType:"",imageAllowSuffixes:".gif,.bmp,.jpeg,.jpg,.ico,.png,.tif,.tiff,",mediaAllowSuffixes:".flv,.swf,.mkv,webm,.mid,.mov,.mp3,.mp4,.m4v,.mpc,.mpeg,.mpg,.swf,.wav,.wma,.wmv,.avi,.rm,.rmi,.rmvb,.aiff,.asf,.ogg,.ogv,",fileAllowSuffixes:".doc,.docx,.rtf,.xls,.xlsx,.csv,.ppt,.pptx,.pdf,.vsd,.txt,.md,.xml,.rar,.zip,7z,.tar,.tgz,.jar,.gz,.gzip,.bz2,.cab,.iso,",maxFileSize:100*1024*1024,maxUploadNum:300,imageMaxWidth:1024,imageMaxHeight:768,isLazy:false,preview:""}})(jQuery);