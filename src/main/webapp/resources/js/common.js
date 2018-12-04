function downloadURL(url){
	window.location.href=url;
};

function loadTablePage(pParam,pNo,divId,pageUrlHiddenId,reqParameters){
	try{
		var paramOptions = {};
		if(reqParameters) {
			paramOptions = reqParameters;
		}
		if(divId == null || $1(divId) == null) divId = "contentDiv";
		var url;
		if($1(pageUrlHiddenId)){
			url = $1(pageUrlHiddenId).value;
		} else {
			$("input[id='pageURL']").each(function(i){
				url = this.value;
			});		
		}
		var urlParamMap = getURLAndParametersMap(unescape(url));
		if(pParam && pNo){
			urlParamMap.params[pParam] = pNo;
		}
		var options = {
			contentDiv:divId,
			requestParameters:urlParamMap.params,
			showWaiting:true,
			scrollTop:true
		}
		ajaxGet(urlParamMap.url,options);
	}catch(e){}
};

function loadPaginatedList(options){
	try{
		var paramOptions = {};
		if(!(options.renderTo || $1(options.renderTo))) options.renderTo = "contentDiv";
		var url;
		if(options.pageUrlHiddenId && $1(options.pageUrlHiddenId)){
			url = $1(options.pageUrlHiddenId).value;
		} else {
			$("input[id='pageURL']").each(function(i){
				url = this.value;
			});		
		}
		var urlParamMap = getURLAndParametersMap(unescape(url));
		if(options.pSize){
			urlParamMap.params['pageSize'] = options.pSize;
		}
		if(options.pageParam && options.pNo){
			urlParamMap.params[options.pageParam] = options.pNo;
		}
		if(options.filter){
			$(".filtered input").each(function(i){
				urlParamMap.params[this.id] = 's$'+this.value;
			});
			$(".filtered select").each(function(i){
				if($(this).attr("fPrefix")) {
					urlParamMap.params[this.id] = $(this).attr("fPrefix")+'$'+this.value;
				}else{
					urlParamMap.params[this.id] = 'n$'+this.value;
				}
			});
		}
		if(options.xName){
			if($.trim(options.xValue) != '') {
				urlParamMap.params[options.xName] = options.xValue;
			}
		}
		if(options.credential){
			if($1('taskDiv')){
				 formName = 'saveCustomScheduleJob';
				 document.forms[formName].action = '/task.do?action=taskApprovalDevices';
				 if (options.checkedDevIds && options.checkedDevIds.length > 0) {
					 document.forms[formName].action+='&checkedDevIds='+options.checkedDevIds;
				 }
				 doPost({formId:formName,renderTo:'devicesDiv',params:urlParamMap.params});
				 return;
		   }else{
		        formName = 'searchPatch';
		        document.forms[formName].action = '/missingPatches.do?action=patchApprovalDevices';
				doPost({formId:formName,renderTo:'devicesDiv',params:urlParamMap.params});
		   }
		}
		
		if (options._isFilterPage) {
			if ($1('updateAgentForm')) {
				doPost({formId:'updateAgentForm',renderTo:options.renderTo,params:urlParamMap.params});
			}
		}else{
		       ajaxGet(urlParamMap.url,{
					contentDiv:options.renderTo,
					requestParameters:urlParamMap.params,
					showWaiting:true,
					scrollTop:false
				});
		}
	}catch(e){}
};