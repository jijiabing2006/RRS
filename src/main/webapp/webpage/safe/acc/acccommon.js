    $(function(){

   // 设置下拉选择框
	  $("#actiontype").select2({
		    minimumResultsForSearch: Infinity,
	        theme: "bootstrap"

		  }).attr("style","width:200px");
	  $("#currencycode").select2({
		  theme: "bootstrap"
			  
	  });
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
	  //
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
		if(type=="CA"){
			$("#importdate").val($("#businessdate").val());
			//非空校验
			var limittype=$("#limittype").val();
			if(limittype=="12"||limittype=="13"){
			  if($("#accountlimit").val()==""){
				  $.Showmsg("如果“限额类型”选择“12”或“13”，则账户限额必填。");
				  $("#accountlimit").focus();
				  return false;
			  }
			}
		}else{
			$("#importdate").val($("#dealdate").val());
			var credit=Number($("#credit").val());
			var debit=Number($("#debit").val());
			var balance=Number($("#balance").val());
			var lastbalance=Number($("#lastbalance").val());
			var safebalance=Number($("#safebalance").val());
			var result=new Number(lastbalance+credit-debit).toFixed(2); 
			if(lastbalance!=safebalance){
				$.Showmsg("“上一日余额”与“已上报余额”不相等");
				  $("#safebalance").focus();
				return false;
			}else if(result!=balance){
				$.Showmsg("“上一日余额”+“当日贷方发生额”-“当日借方发生额”不等于“账户余额”");
				  $("#credit").focus();
				return false;
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