    $(function(){

   //设置下拉选择框
	  $("#actiontype").select2({
		    minimumResultsForSearch: Infinity,
		    theme: "bootstrap"

		  });
	  $("#custype").select2({
		    minimumResultsForSearch: Infinity,
		    theme: "bootstrap",
	        placeholder: {
	        	 id: "-1",
	        	   placeholder: "Select an option",
	        	   allowClear: true
	        }

		  }).on("select2:select", function (e) {//客户证件号码联动设置
                  var cust=$("#custype").val();
                  if(cust=="C"){
  	  				$("#idcode").attr("disabled","disabled").attr("style","background:#EEEEEE").val("").attr("ignore","ignore");
  	  				$("#custcod").attr("disabled",false).attr("style","background:white").removeAttr("ignore").focus();
  	  			}else{
  	  				$("#custcod").attr("disabled","disabled").attr("style","background:#EEEEEE").val("").attr("ignore","ignore");
  	  				$("#idcode").attr("disabled",false).attr("style","background:none").removeAttr("ignore");
  	  			}
		  });
	
	  $("#txccy").select2({
		  
		  theme: "bootstrap"

		  });
	  $("#method").select2({
		   minimumResultsForSearch: Infinity,
		   theme: "bootstrap"
			  
	  });
	  $("#isref").select2({
		  minimumResultsForSearch: Infinity,
		  theme: "bootstrap"  
	  });
	  
	  $(".pagination-page-list").select2({
		    minimumResultsForSearch: Infinity,
		    theme: "bootstrap"

		  }).attr("style","width:auto;");
	  
  	//设置客户证件号码的默认值
      var cust=$("#custype").val();
      if(cust=="C"){
				$("#idcode").attr("disabled","disabled").attr("style","background:#EEEEEE").val("").attr("ignore","ignore");
				$("#custcod").attr("disabled",false).attr("style","background:white");
      }else{
				$("#custcod").attr("disabled","disabled").attr("style","background:#EEEEEE").val("").attr("ignore","ignore");
				$("#idcode").attr("disabled",false).attr("style","background:none");
      }
      //如果没有进入SAFE的记录，Actiontype不可以修改
		if($("#isinsafe").val()!="1"){
			$("#actiontype").prop("disabled",true);
			$("#actiondesc").attr("disabled","disabled").attr("style","background:#EEEEEE").val("").attr("ignore","ignore");
		}else {
				$("#actiontype").val(null).trigger("change");
				$("#actiontype").find("option[value='A']").attr("disabled","disabled");
				
    		//	$("#actiontype").find("option[value='C']").attr("selected","selected");
    				// alert($("#s2id_actiontype").find("span.select2-chosen").text());
    		//	$("#s2id_actiontype").find("span.select2-chosen").text("修改");
		
    				// alert($("#s2id_actiontype").find("span.select2-chosen").text());
	
		}
		
	  //申报号码不可以修改
		  $("#rptno").attr("style","background:#EEEEEE").attr("readonly","readonly");
		  
		  var rptdate=$("#rptdate");
		  if(rptdate!= undefined&&rptdate.val()==""){
			  $("#rptdate").val($("#importdate").val()).val();
		  }
		  
		  
  });
	
    function check(curform,type) {
    	if($("#isvalidation").val()=="1" && $("#isinsafe").val()!="1"){
			$.Showmsg("已审核过记录不可直接编辑");
			return false;
		}
		if($("#actiontype").val()!="A"&&$("#actiondesc").val()==""){
			$.Showmsg("操作类型为修改/删除时，修改删除原因不能为空");
			$("#actiondesc").focus();
			return false;
		}
		if($("#lcyamt").val()==""&&$("#fcyamt").val()==""&&$("#othamt").val()==""){
			$.Showmsg("购汇金额, 现汇金额, 其它金额至少输入一项");
			$("#fcyamt").focus();
			return false;
		}
		
		if(type=="C"||type=="F"){
			var method=$("#method").val();
			if((method=="L"||method=="G")&&$("#lcbgno").val()==""){
				$.Showmsg("当结算方式为信用证/保函时，编号必须输入。");
				$("#lcbgno").focus();
				return false;	
			}else if($("#lcbgno").val()!=""&&$("#issdate").val()!=""&&$("#tenor").val()&&method!="L"&&method!="G"){
				$.Showmsg("当信用证/保函编号不为空时，与结算方式不相符");
				$("#lcbgno").focus();
				return false;
			}
		}
		//资本项目项下交易（涉外收支交易编码以“5”、“6”、“7”、“8”和部分“9”开头，具体见7.3）的“外汇局批件号/备案表号/业务编号”为必输项。
		if (  type == "G"||type == "H" || type == "K"||type == "Q"||type == "R" || type == "S") {
			
			var cap = $("#cap").val();
			if (cap == "true") {
				
				var arr = [ "923010", "923020", "923090", "924010", "924020", "924030",
							"924090", "929020" ];
					var txcode = $("#txcode").val();
					var fc = txcode.substring(0, 1);
					var regno = $("#regno").val();
					if(type=="G"){//只有涉外收入申报 与其它类型的‘ 批准件’字段名称不一样
						regno= $("#billno").val()
					}
				
				if (fc == "5" || fc == "6" || fc == "7" || fc == "8"
						|| $.inArray(txcode, arr) != -1) {
					if (regno == "" || regno == "N/A") {
						$.Showmsg("资本项目项下交易（涉外收支交易编码以“5”、“6”、“7”、“8”和部分“9”开头）"
								+ "的“外汇局批件号/备案表号/业务编号”为必输项。");
						if(type=="G"){$("#billno").val("").focus();}else{$("#regno").val("").focus();}
						
						return false;

					}
				}
			}
		}
		
		
		//提交时修改Actiontype的disable属性，避免acitontype：disable时值丢失的情况
		$("#actiontype").prop("disabled",false);
		$("#isedit").val('1');
		$("#isvalidation").val('0');
	  	if($("#isexport").val()==""||$("#isexport").val()==null){
	  		$("#isexport").val('0');
	  	}
	  	if($("#isinsafe").val()==""||$("#isinsafe").val()==null){
	  		$("#isinsafe").val('0');
	  	}
		$("#remark").val("");
	}