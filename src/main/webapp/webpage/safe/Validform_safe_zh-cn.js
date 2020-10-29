/*
    Validform datatype extension
	By sean during December 8, 2012 - February 20, 2013
	For more information, please visit http://validform.rjboy.cn
	
	扩展以下类型：
test:测试验证方法
*/

(function(){

	if($.Datatype){
		$.extend($.Tipmsg.w,{
			"linkcheck":"关联校验不相符",
			"sumle":"{0}之和不能大于{1}"
			
		});
		
		$.extend($.Datatype,{
			
			"sumle":function(gets,obj,curform,datatype){
				var withele = $.trim(obj.attr("with"));
				var linkwith = withele.split(",");
				var sumval=0;
				var l2="";
				for ( var int = 0; int < linkwith.length; int++) {
					var obj2=curform.find(linkwith[int]+",[name='"+linkwith[int]+"']");
					val=obj2.val();
					lt= $.Vutil.getLabel(obj2,curform);
					if(val!=""){
						sumval+=parseInt(val);
					}
					if(int!=linkwith.length-1){
						l2+=lt+"、";
					}else{
						l2+=lt;
					}
					
					
				}
				if(parseInt($.trim(gets))<sumval){
				   l=	$.Vutil.getLabel(obj,curform);
					var nullmsg=$.Tipmsg.w["sumle"].replace(/\{0\}/,l2).replace(/\{1\}/,l);
					return nullmsg;
				}
				
				return true;
				
			},
			"linkcheck":function(gets,obj,curform,datatype){
				

				var withele = $.trim(obj.attr("with"));
				var linkwith = withele.split(",");
				for ( var int = 0; int < linkwith.length; int++) {
					if(!checklinkvalue(linkwith[int])){
						return false;
					}
					
				}
				
				return true;
				
		      function checklinkvalue(withele){
		  		var withvalue;
				if(withele.indexOf("#")>-1){
					withvalue=withele.split("#");
					if(withvalue.length==3){
						var obj2=curform.find(withvalue[0]+",[name='"+withvalue[0]+"']");
						var field=obj2.val();
						var values=$.trim(withvalue[2]).split(",");
						var exptype=withvalue[1];
						//alert("0="+field+"   1=="+exptype+"  2==  "+values);
						
						var reg=/[\u4E00-\u9FA5\uf900-\ufa2da-zA-Z\s]+/g;
						var label=curform[0].settings.label || ".Validform_label";
						label=obj.siblings(label).eq(0).text() || obj.siblings().find(label).eq(0).text() || obj.parent().prev().find(label).eq(0).text()|| obj.parent().siblings(label).eq(0).text() || obj.parent().siblings().find(label).eq(0).text();
						label=label.replace(/\s(?![a-zA-Z])/g,"").match(reg);
						label=label? label.join("") : [""];
					//	alert("label==  "+label);
						
						
						var label2=curform[0].settings.label || ".Validform_label";
						label2=obj2.siblings(label2).eq(0).text() || obj2.siblings().find(label2).eq(0).text() || obj2.parent().prev().find(label2).eq(0).text()|| obj2.parent().siblings(label2).eq(0).text() || obj2.parent().siblings().find(label2).eq(0).text();
						label2=label2.replace(/\s(?![a-zA-Z])/g,"").match(reg);
						label2=label2? label2.join("") : [""];
						
						if ("eq"==exptype) {
							if($.inArray(field ,[ value])<0){
								return false;
							}
						}
						if ("ne"==exptype) {
							if($.inArray(field ,[value ])>=0){
								return false;
							}
						}
						if ("empty"==exptype && values=="true") {
							if(field !=""){
								return false;
							}
						}
						if ("empty"==exptype && values=="false") {
							if( field ==""){
							
								$.Tipmsg.w["linkcheck"]=label+"若不为空，则"+label2+"不能为空";
								obj2.focus();
								return false;
							}
						}
						
					//	$.Tipmsg.w["test"]="testtes3333333ttesttest位中文";
					}
					
				}
				return true;
		      }
			}
		});
	}else{
		setTimeout(arguments.callee,10);
	}
})();