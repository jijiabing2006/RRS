/*
    Validform datatype extension
	By sean during December 8, 2012 - February 20, 2013
	For more information, please visit http://validform.rjboy.cn
	
	扩展以下类型：
	date：匹配日期
	zh：匹配中文字符
	dword：匹配双字节字符
	money：匹配货币类型
	ipv4：匹配ipv4地址
	ipv6：匹配ipv6地址
	num：匹配数值型
	qq：匹配qq号码
	unequal：当前值不能等于被检测的值，如可以用来检测新密码不能与旧密码一样
	notvalued：当前值不能包含指定值，如密码不能包含用户名等的检测
	min：多选框最少选择多少项
	max：多选框最多不能超过多少项
	byterange:判断字符长度，中文算两个字符
	numrange：判断数值范围，如小于100大于10之间的数
	daterange：判断日期范围
	idcard：对身份证号码进行严格验证
*/

(function(){
	if($.Datatype){
		$.extend($.Tipmsg.w,{
			"date":"请填写日期！",
			"yyyyMMdd":"请填写yyyyMMdd格式日期！",
			"notblank":"不能包含空格！",
			"zh":"请填写中文！",
			"dword":"请填写双字节字符！",
			"money":"请填写货币值！",
			"ipv4":"请填写ip地址！",
			"ipv6":"请填写IPv6地址！",
			"num":"请填写数值！",
			"positivenum":"请填写正数数值！",
			"integer":"请填写正整数！",
			"qq":"请填写QQ号码！",
			"dns":"DNS格式不符合规则！",
			"patrn":"只可为数字或大写拉丁字母！",
			"lastpatrn":"前8位必须是数字，最后一位只可为数字或大写拉丁字母:X！",
			"unequal":"值不能相等！",
			"notvalued":"不能含有特定值！",
			"idcard":"身份证号码不对！",
			"startJNJW":"必须以大写英文字符 (JW)或(JN)开头！",
			"orgcode":"组织机构代码不对！",
			"linkcheck":"关联校验不相符",
			"bondcheck":"{0}{1}要同时有值或者同时为空",
			"duplicate":"{0}{1}的值不可以相同",
			"subeq":"{0}之差应等于{1}",
			"sumeq":"{0}之和应等于{1}",
			"sumge":"{0}之和不能大于{1}",
			"unbothnull":"{0}与{1}不能同时为空"
		});
		
		$.extend($.Datatype,{
			/*
				reference http://blog.csdn.net/lxcnn/article/details/4362500;
				
				日期格式可以是：20120102 / 2012.01.02 / 2012/01/02 / 2012-01-02
				时间格式可以是：10:01:10 / 02:10
				如 2012-01-02 02:10
				   2012-01-02
			*/
			"date":/^(?:(?:1[6-9]|[2-9][0-9])[0-9]{2}([-/.]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:(?:1[6-9]|[2-9][0-9])(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)([-/.]?)0?2\2(?:29))(\s+([01][0-9]:|2[0-3]:)?[0-5][0-9]:[0-5][0-9])?$/,
			
					
					//不能包含空格;
					"notblank":/^\S*$/,
			//匹配中文字符;
			"zh":/^[\u4e00-\u9fa5]+$/,
			
			//匹配双字节字符;
			"dword":/^[^\x00-\xff]+$/,
			
			//货币类型;
			"money":/^([\u0024\u00A2\u00A3\u00A4\u20AC\u00A5\u20B1\20B9\uFFE5]\s*)(\d+,?)+\.?\d*\s*$/,
			
			//匹配ipv4地址;
			"ipv4":/^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/,
			
			/*
				匹配ipv6地址;
				reference http://forums.intermapper.com/viewtopic.php?t=452;
			*/
			"ipv6":/^\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:)))(%.+)?\s*$/,
			
			
			//数值型;
			"num":/^(-|[0-9])+\.?[0-9]*$/,
			//正数
			"positivenum":/^[0-9]+\.?[0-9]*$/,
			//整数型;
			"integer":/^[1-9]{1}[0-9]*$/,
			//QQ号码;
			"qq":/^[1-9][0-9]{4,}$/,
			
			"dns":/^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/,
			//只能是数字和大写字母
			 "patrn": /^[0-9A-Z]+$/,
			//第9位只能是数字或者大写拉丁字母X 
			 "lastpatrn":/^[0-9]{8}[0-9X]{1}$/,
			 //必须以大写英文字符 (JW)或(JN)开头
			 "startJNJW":/^\(J[WN]\)([^x00-xff]|[a-zA-Z]|[0-9]|[\,\.\'\_]){2,}/,
			/*
			  参数gets是获取到的表单元素值，
			  obj为当前表单元素，
			  curform为当前验证的表单，
			  datatype为内置的一些正则表达式的引用。
			*/
			
			"unequal":function(gets,obj,curform,datatype){
				/*
					当前值不能与指定表单元素的值一样，如新密码不能与旧密码一样，密码不能设置为用户名等
					注意需要通过绑定with属性来指定要比较的表单元素，可以是clas，id或者是name属性值

					eg.  <input type="text" name="name" id="name" class="name" />
					eg1. <input type="text" name="test" datatype="unequal" with="name" />
					eg2. <input type="text" name="test" datatype="unequal" with=".name" />
					eg3. <input type="text" name="test" datatype="unequal" with="#name" />
					
					也可以用来验证不能与with指定的值相等
					当上面根据class，id和name都查找不到对象时，会直接跟with的值比较
					eg4. <input type="text" name="test" datatype="num unequal" with="100" />
					该文本框的值不能等于100
				*/
				var withele=$.trim(obj.attr("with"));
				var val=curform.find(withele+",[name='"+withele+"']").val() || withele;

				if(gets==$.trim(val)){
					return false;
				}
			},
			
			
			"notvalued":function(gets,obj,curform,datatype){
				/*
					当前文本框的值不能含有指定文本框的值，如注册时设置的密码里不能包含用户名
					注意需要给表单元素绑定with属性来指定要比较的表单元素，可以是clas，id或者是name属性值
					<input type="text" name="username" id="name" class="name" />
					eg. <input type="password" name="test" datatype="notvalued" with=".name" />
					
					也可以用来验证不能包含with指定的值
					当上面根据class，id和name都查找不到对象时，会直接跟with的值比较
					eg2. <input type="password" name="test" datatype="notvalued" with="validform" />
					要求不能含有"validform"字符
				*/
				var withele=$.trim(obj.attr("with"));
				var val=curform.find(withele+",[name='"+withele+"']").val() || withele;

				if(gets.indexOf($.trim(val))!=-1){
					return false;
				}
			},
			
			
			"min":function(gets,obj,curform,datatype){
				/*
					checkbox最少选择n项
					注意需要给表单元素绑定min属性来指定是至少需要选择几项，没有绑定的话使用默认值
					eg. <input type="checkbox" name="test" datatype="min" min="3" />
				*/
				
				var minim=~~obj.attr("min") || 2,
					numselected=curform.find("input[name='"+obj.attr("name")+"']:checked").length;
				return  numselected >= minim ? true : "请至少选择"+minim+"项！";
			},
			
			
			"max":function(gets,obj,curform,datatype){
				/*
					checkbox最多选择n项
					注意需要给表单元素绑定max属性来指定是最多需要选择几项，没有绑定的话使用默认值
					eg. <input type="checkbox" name="test" datatype="max" max="3" />
				*/
				
				var atmax=~~obj.attr("max") || 2,
					numselected=curform.find("input[name='"+obj.attr("name")+"']:checked").length;
					
				if(numselected==0){
					return false;
				}else if(numselected>atmax){
					return "最多只能选择"+atmax+"项！";
				}
				return  true;
			},
			
			
			"byterange":function(gets,obj,curform,datatype){
				/*
					判断字符长度，中文算两个字符
					注意需要给表单元素绑定max,min属性来指定最大或最小允许的字符长度，没有绑定的话使用默认值
				*/
				var dregx=/[^\x00-\xff]/g;
				var maxim=~~obj.attr("max") || 100000000,
					minim=~~obj.attr("min") || 0;
					
				getslen=gets.replace(dregx,"00").length;
				
				if(getslen>maxim){
					return "输入字符不能多于"+maxim+"个，中文算两个字符！";
				}
				
				if(getslen<minim){
					return "输入字符不能少于"+minim+"个，中文算两个字符！";
				}
				
				return true;
			},
			
			
			"numrange":function(gets,obj,curform,datatype){
				/*
					判断数值范围
					注意需要给表单元素绑定max,min属性来指定最大或最小可输入的值，没有绑定的话使用默认值
				*/
				
				var maxim=~~obj.attr("max") || 100000000,
					minim=~~obj.attr("min") || 0;
				
				gets=gets.replace(/\s*/g,"").replace(/,/g,"");
				if(!/^\d+\.?\d*$/.test(gets)){
					return "只能输入数字！";
				}
				
				if(gets<minim){
					return "值不能小于"+minim+"！";
				}else if(gets>maxim){
					return "值不能大于"+maxim+"！";
				}
				return  true;
			},
		
			
			"daterange":function(gets,obj,curform,datatype){
				/*
					判断日期范围
					注意需要给表单元素绑定max或min属性，或两个同时绑定来指定最大或最小可输入的日期
					日期格式：2012/12/29 或 2012-12-29 或 2012.12.29 或 2012,12,29
				*/
				var maxim=new Date(obj.attr("max").replace(/[-\.,]/g,"/")),
					minim=new Date(obj.attr("min").replace(/[-\.,]/g,"/")),
					gets=new Date(gets.replace(/[-\.,]/g,"/"));

				if(!gets.getDate()){
					return "日期格式不对！";
				}
				
				if(gets>maxim){
					return "日期不能大于"+obj.attr("max");	
				}
				
				if(gets<minim){
					return "日期不能小于"+obj.attr("min");
				}
				
				return true;
			},
			
			
			"idcard":function(gets,obj,curform,datatype){
				/*
					该方法由网友提供;
					对身份证进行严格验证;
				*/
			
				var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
				var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
			
				if (gets.length == 15) {   
					return isValidityBrithBy15IdCard(gets);   
				}else if (gets.length == 18){   
					var a_idCard = gets.split("");// 得到身份证数组   
					if (isValidityBrithBy18IdCard(gets)&&isTrueValidateCodeBy18IdCard(a_idCard)) {   
						return true;   
					}   
					return false;
				}
				return false;
				
				function isTrueValidateCodeBy18IdCard(a_idCard) {   
					var sum = 0; // 声明加权求和变量   
					if (a_idCard[17].toLowerCase() == 'x') {   
						a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
					}   
					for ( var i = 0; i < 17; i++) {   
						sum += Wi[i] * a_idCard[i];// 加权求和   
					}   
					valCodePosition = sum % 11;// 得到验证码所位置   
					if (a_idCard[17] == ValideCode[valCodePosition]) {   
						return true;   
					}
					return false;   
				}
				
				function isValidityBrithBy18IdCard(idCard18){   
					var year = idCard18.substring(6,10);   
					var month = idCard18.substring(10,12);   
					var day = idCard18.substring(12,14);   
					var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
					// 这里用getFullYear()获取年份，避免千年虫问题   
					if(temp_date.getFullYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){   
						return false;   
					}
					return true;   
				}
				
				function isValidityBrithBy15IdCard(idCard15){   
					var year =  idCard15.substring(6,8);   
					var month = idCard15.substring(8,10);   
					var day = idCard15.substring(10,12);
					var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
					// 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
					if(temp_date.getYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){   
						return false;   
					}
					return true;
				}
				
			},
			 "orgcode":function(gets,obj,curform,datatype){
				 
				 var withele = $.trim(obj.attr("with"));
						if(withele.indexOf("#")>-1){
							withvalue=withele.split("#");
							if(withvalue.length==3){
								var obj2=curform.find(withvalue[0]+",[name='"+withvalue[0]+"']");
								var field=obj2.val();
								var values=$.trim(withvalue[2]) ;
								var exptype=withvalue[1];
								if ("eq"==exptype) {
									if(field!=values){
										return true;
									}
								}
							}
						}
				 
						if(gets.length!=9){
							$.Tipmsg.w["orgcode"]="对公客户填写9位组织机构代码" ;
							return false;
						}
						
						
				 	var weightedfactors = [ 3, 7, 9, 10, 5, 8, 4, 2 ];
				 	var orgcodemapping = [ '0:0', '1:1', '2:2', '3:3', '4:4', '5:5', '6:6', '7:7',
				 	              		'8:8', '9:9', 'A:10', 'B:11', 'C:12', 'D:13', 'E:14', 'F:15', 'G:16',
				 	              		'H:17', 'I:18', 'J:19', 'K:20', 'L:21', 'M:22', 'N:23', 'O:24', 'P:25',
				 	              		'Q:26', 'R:27', 'S:28', 'T:29', 'U:30', 'V:31', 'W:32', 'X:33', 'Y:34',
				 	              		'Z:35' ];
				 	var ancode;
					var ancodevalue;	
					var total = 0;
					var checkcode = gets.substring(8, 9);
					for ( var i = 0; i < gets.length - 1; i++) {
						ancode = gets.substring(i, i + 1);
						ancodevalue = findvalueinorgcodemapping(ancode);
						total = total + ancodevalue * weightedfactors[i];
					}
					var logiccheckcode = 11 - total % 11;
					if (logiccheckcode == 10) {
						logiccheckcode = 'X';
					}
					if (logiccheckcode == 11) {
						logiccheckcode = '0';
					}
					if (checkcode != logiccheckcode) {
						$.Tipmsg.w["orgcode"]="最后一位校验码应为:" + logiccheckcode;
						return false;
					} 
						return true;
						
				  function findvalueinorgcodemapping(ancode){
							var mapping;
							var result=-1;
							for(var i=0;i<orgcodemapping.length;i++){
								mapping=orgcodemapping[i];
								if(mapping.substring(0,1)==ancode){
									result= mapping.substring(2,mapping.length);
									break;
								}
							}
							return result;
						}
					
			 },
			 "subeq":function(gets,obj,curform,datatype){
				 var withele = $.trim(obj.attr("with"));
				 var linkwith = withele.split(",");
				 var sub=0;
				 var first=0;
				 var l2="";
				 for ( var int = 0; int < linkwith.length; int++) {
						var linkindex=linkwith[int];
						if(linkindex.indexOf(":")>-1){
							if(linkindex.indexOf("subeq")>-1){
								linkindex=$.trim(linkindex.substring(linkindex.indexOf(":")+1));
							}else{
								continue;
							}
						}
					 var obj2=curform.find(linkindex+",[name='"+linkindex+"']");
					 val=obj2.val()==""?0:obj2.val();
					 lt= $.Vutil.getLabel(obj2,curform);
					 
					 if(int==0){
						 first=parseInt(val);
					 }else {
						 sub+=parseInt(val);
					 }
					 if(int!=linkwith.length-1){
						 l2+=lt+"、";
					 }else{
						 l2+=lt;
					 }
				 }
				 var subresult=(first-sub);
				 if(parseInt($.trim(gets))!=subresult){
					 l=	$.Vutil.getLabel(obj,curform);
					 var nullmsg=$.Tipmsg.w["subeq"].replace(/\{0\}/,l2).replace(/\{1\}/,l);
					 return nullmsg;
				 }
				 
				 return true;
				 
			 },
			 "sumge":function(gets,obj,curform,datatype){
				 var withele = $.trim(obj.attr("with"));
				 var linkwith = withele.split(",");
				 var sumval=0;
				 var l2="";
				 for ( var int = 0; int < linkwith.length; int++) {
						var linkindex=linkwith[int];
						if(linkindex.indexOf(":")>-1){
							if(linkindex.indexOf("sumge")>-1){
								linkindex=$.trim(linkindex.substring(linkindex.indexOf(":")+1));
							}else{
								continue;
							}
						}
					 var obj2=curform.find(linkindex+",[name='"+linkindex+"']");
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
					 var nullmsg=$.Tipmsg.w["sumge"].replace(/\{0\}/,l2).replace(/\{1\}/,l);
					 return nullmsg;
				 }
				 
				 return true;
				 
			 },
				"sumeq":function(gets,obj,curform,datatype){
					var withele = $.trim(obj.attr("with"));
					var linkwith = withele.split(",");
					var sumval=0;
					var l2="";
					for ( var int = 0; int < linkwith.length; int++) {
						var linkindex=linkwith[int];
						if(linkindex.indexOf(":")>-1){
							if(linkindex.indexOf("sumeq")>-1){
								linkindex=$.trim(linkindex.substring(linkindex.indexOf(":")+1));
							}else{
								continue;
							}
						}
						 var obj2=curform.find(linkindex+",[name='"+linkindex+"']");
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
					if(parseInt($.trim(gets))!=sumval){
					   l=	$.Vutil.getLabel(obj,curform);
						var nullmsg=$.Tipmsg.w["sumeq"].replace(/\{0\}/,l2).replace(/\{1\}/,l);
						return nullmsg;
					}
					
					return true;
					
				},
				"unbothnull":function(gets,obj,curform,datatype){
					var withele = $.trim(obj.attr("with"));
					var obj2=curform.find(withele+",[name='"+withele+"']");
				
					if($.trim(gets)!="" ){return true;}
					if( $.trim(obj2.val())!=""){return true;}
					
					
				    	lt= $.Vutil.getLabel(obj2,curform);
						l=	$.Vutil.getLabel(obj,curform);
						var nullmsg=$.Tipmsg.w["unbothnull"].replace(/\{0\}/,l).replace(/\{1\}/,lt);
							return nullmsg;	
						
					
				},
				"duplicate":function(gets,obj,curform,datatype){
					var withele = $.trim(obj.attr("with"));
					var linkwith = withele.split(",");
					var array=new Array();
					var l2="";
					var count=0;
					for ( var int = 0; int < linkwith.length; int++) {
						var linkindex=linkwith[int];
						if(linkindex.indexOf(":")>-1){
							if(linkindex.indexOf("duplicate")>-1){
								linkindex=$.trim(linkindex.substring(linkindex.indexOf(":")+1));
							}else{
								continue;
							}
						}
						var obj2=curform.find(linkindex+",[name='"+linkindex+"']");
						val=obj2.val();
						if(gets==$.trim(val)){
							lt= $.Vutil.getLabel(obj2,curform);
							l2+=lt+"、";
							count++;   //值重复时，计数
						}
					}
					
					if(count>0){
						l=	$.Vutil.getLabel(obj,curform);
						var nullmsg=$.Tipmsg.w["duplicate"].replace(/\{0\}/,l2).replace(/\{1\}/,l);
						return nullmsg;
					}
					
					return true;
					
				},
				"bondcheck":function(gets,obj,curform,datatype){
					var withele = $.trim(obj.attr("with"));
					var linkwith = withele.split(",");
					var sumval=0;
					var l2="";
					for ( var int = 0; int < linkwith.length; int++) {
						var linkindex=linkwith[int];
						if(linkindex.indexOf(":")>-1){
							if(linkindex.indexOf("bondcheck")>-1){
								linkindex=$.trim(linkindex.substring(linkindex.indexOf(":")+1));
							}else{
								continue;
							}
						}
						 var obj2=curform.find(linkindex+",[name='"+linkindex+"']");
						val=obj2.val();
						lt= $.Vutil.getLabel(obj2,curform);
						if(val!=""){
							sumval++;
						}
						l2+=lt+"、";
					}
					
					if(!($.trim(gets)!=""&&sumval==linkwith.length)||$.trim(gets)==""&&sumval>0){
						l=	$.Vutil.getLabel(obj,curform);
						var nullmsg=$.Tipmsg.w["bondcheck"].replace(/\{0\}/,l2).replace(/\{1\}/,l);
						return nullmsg;
					}
				//	alert(!($.trim(gets)!=""&&sumval==linkwith.length)||$.trim(gets)==""&&sumval>0);
					
					return true;
					
				},
				"linkcheck":function(gets,obj,curform,datatype){
					var withele = $.trim(obj.attr("with"));
					var linkwith = withele.split(",");
					for ( var int = 0; int < linkwith.length; int++) {
						var linkindex=linkwith[int];
						if(linkindex.indexOf(":")>-1){
							if(linkindex.indexOf("linkcheck")>-1){
								linkindex=$.trim(linkindex.substring(linkindex.indexOf(":")+1));
							}else{
								continue;
							}
						}
						if(!checklinkvalue(linkindex)){
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
							
							label=$.Vutil.getLabel(obj,curform);
								
							label2=$.Vutil.getLabel(obj2,curform);
							
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
								if( field ==""||field===undefined){
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