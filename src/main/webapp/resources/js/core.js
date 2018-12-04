function $1(divName) { 
	return document.getElementById(divName); 
};

function submitForm(formName, param, divId) {
	if(param && param != undefined && param != ''){
		document.forms[formName].appendChild(createHiddenElement(param, param, param));
	}
	return post(formName, divId);
};

function postSearchForm(formName, searchId, divId, defVal) {
	var value = trim($("#" + searchId).val());
	if ((value == 'Search here') || (value == 'Search By Name')|| (value == defVal)) {
		$("#" + searchId).val('');
	}
	document.forms[formName].appendChild(createHiddenElement('fromSearch',
			'fromSearch', 'fromSearch'));
	return post(formName, divId);
};

function createHiddenElement(name, value, id) {
	var field = document.createElement("input");
	field.setAttribute("type", "hidden");
	field.setAttribute("value", value);
	field.setAttribute("name", name);
	field.setAttribute("id", id);
	return field;
};

function post(formId, divName, onCompleteFunction, functionParams, dontBlockDiv) {
	doPost({
		formId:formId,renderTo:divName,onCompleteFunction:onCompleteFunction,functionParams:functionParams,noModal:(dontBlockDiv == true)?true:false
	});
	return false;
};

function showWaiting(loadDivId) {
	try{
		if(loadDivId && $1(loadDivId)){
			$('#'+loadDivId).mask('Loading... please wait');
		}
	}catch(e){}
};

function hideWaiting(loadDivId) {
	try{
		if(loadDivId && $1(loadDivId)){
			$('#'+loadDivId).unmask()
		}
	}catch(e){}
};

function initializeScrollar(contentId, heightBlockId, alwaysVisible){
	if(!heightBlockId || !($1(heightBlockId))){
		heightBlockId = contentId;
	}
	if(contentId && $1(contentId)){
		$('#'+contentId).slimScroll({height: $('#'+heightBlockId).height()/*,alwaysVisible: true*/});	
	}
};

var divHsNameMap = {};
var _bRefresh = false;
function load(url,divId){
	url = url.replace(/^.*#/, '');
	
	if(divId == null || $1(divId) ==  null)
		divId = "container";
	divHsNameMap[escape(url)] = divId;
	_bRefresh = false;
	$.history.load(url);
	window.scrollTo(0,0);
	return;
};

function confirmModal(options){
	bootbox.dialog('<p style="font-size:150%;">'+options.msg+'</p>', [{
		"label" : "Yes",
		"class" : "btn-primary",
		"callback": function() {
			if(options.confirmCallBackfn){
				try{options.confirmCallBackfn.call();}catch(e){}
			}
			return;
		}
	}, {
		"label" : "No",
		"class" : "btn",
		"callback": function() {
			if(options.cancelCallBackfn){
				try{options.cancelCallBackfn.call();}catch(e){}
			}
			return;
		}
	}]);
}

/* Ajax functions */
var ajaxRequests = [];

function get(url, divName, parameters, onCompleteFunction, functionParams, dontBlockDiv) {
	doGet(url,{
		renderTo:divName,
		noModal:(dontBlockDiv == true)?true:false,
		requestParameters:parameters,
		onCompleteFunction:onCompleteFunction,
		functionParams:functionParams,
		scrollTop:false
	});
};

function doGet(reqUrl,options){
	var renderTo = "container";
	var parameters = {};
	if((options != null) && (options.renderTo != null)/* && $1(options.renderTo)*/){
		renderTo = options.renderTo;
	}
	if(options == null || !options.noModal){
		showWaiting(renderTo);
	}
	if(options != null && options.requestParameters != null){
		parameters = options.requestParameters;
	}
	parameters._ajax= true;		
	try{
		if(ajaxRequests && ajaxRequests[renderTo]){
			ajaxRequests[renderTo].abort();
		}
	}catch(e){}
	var method='GET', dataType='html';
	if(options.method == 'POST'){
		method='POST';
	}
	if(options.method == 'DELETE'){
		method='DELETE';
	}
	if(options.dataType){
		dataType=options.dataType;
	}
	
	ajaxRequests[renderTo] = $.ajax({
	   type: method,
	   url: reqUrl,
	   data: parameters,
	   dataType:dataType,
	   success: function(data){
	   		if(options != null && options.scrollTop != null && options.scrollTop == true){
				if (window != null){
					window.scrollTo(0,0);
				}
			}
			handleAjaxResponse(data,renderTo,options);
			if(options != null && options.onCompleteFunction != null){
				options.onCompleteFunction.call(this, data, options.functionParams)
			}	
			ajaxRequests[renderTo] = null;
	   },
	   error:function (XMLHttpRequest, textStatus, errorThrown) {
			if(XMLHttpRequest.responseText && (XMLHttpRequest.responseText != 'undefined')){			
				$1(renderTo).innerHTML = XMLHttpRequest.responseText;
				if($1("j_username")){
					window.location.href = '/login';
				}
				if(options == null || !options.noModal){
					hideWaiting(renderTo);
				}
				$("#"+renderTo).show();
				ajaxRequests[renderTo] = null;
			}
	   }
	 });
};

function doPost(formOptions){
	var formObj = $1(formOptions.formId);
	var actionUrl = formObj.action;
	if(formOptions.url){
		actionUrl = formOptions.url;
	}
	if(actionUrl.indexOf('_ajax')<0){
		if(actionUrl.indexOf('?')<0){
			actionUrl += '?_ajax=true';
		}else{
			actionUrl += '&_ajax=true';
		}
	}
	var extraParams = {_ajax:true};
	if(formObj && formObj._cid){
		extraParams._cid = formObj._cid.value;
	}
	if(formOptions && formOptions.params){
		for(param in formOptions.params) {
			extraParams[param]=formOptions.params[param]; 
		}
	}
	
	var renderTo = "container";
	if(formOptions.renderTo != null && $1(formOptions.renderTo)){
		renderTo = formOptions.renderTo;
	}
	if(extraParams && extraParams._skip){
		$.post(actionUrl,extraParams,function(data){
			handleAjaxResponse(data,renderTo,formOptions);
		});
		return false;
	}
	var opts = {
		dataType:'html',
		url:actionUrl,
		data:extraParams,
		target: "#"+renderTo,
		success: function(data){
			handleAjaxResponse(data,renderTo,formOptions);
			if(formOptions != null && formOptions.onCompleteFunction != null){
				formOptions.onCompleteFunction.call(this, data, formOptions.functionParams)
			}
		}
	};
	$(formObj).find("input:file").each(function(i){
		if($(this) == null || $(this).val() == ''){
			$(this).attr("disabled","disabled");
		}
	});
	if(formOptions.confirmMsg){
		bootbox.dialog(formOptions.confirmMsg, [{"label" : "Yes","class" : "btn-primary","callback": function() {
				if(!formOptions.noModal) {	
					showWaiting(renderTo);
				}
				try{
					$(formObj).ajaxSubmit(opts);
				}catch(e){}
			}
		},{"label" : "No","class" : "btn-danger","callback": function() {return;}
		}]);
	}else{
		if(!formOptions.noModal) {	
			showWaiting(renderTo);
		}
		try{
			$(formObj).ajaxSubmit(opts);

		}catch(e){}
	}
	return false;
};

function handleAjaxResponse(data,renderTo,options){
	if(renderTo != null && $1(renderTo) && (options != null && !options.noResponse)) {
		$("#"+renderTo).html(data);
		$("#"+renderTo).show();
		if($1("j_username")){
			window.location.href = '/login';
		}
		$("#"+renderTo).show();
		if(renderTo == 'modalBody'){
			$("#sccModal").show();
			adjustModalDimensions($('#sccModal'));				
		}
		if(options && options.tinyScrollars){
			initializeScrollar(renderTo);
		}			
		if(options == null || !options.noModal){
			hideWaiting(renderTo);
		}						
	}
	if(options != null && options.noResponse){
		evalScripts(data);
	}
};

function evalScripts(htmlContent){
	if(htmlContent) {
		var scripts, scriptsFinder=/<script\b[^>]*>([\s\S]*?)<\/script>/gm;
        while(scripts=scriptsFinder.exec(htmlContent)) {
			if (window.execScript){
				window.execScript(scripts[1]);
			} else {
				eval.call(window, scripts[1]);
			}
        }		
	}	
};

function ajax(id,options)
{
    $("#"+id).html("<div class='loading'>Loading..</div>");

    var method = options.success;
    options.success = function(response){
        $("#"+id).html("");
        if(method!=undefined)
            method(response);
    };
    $.ajax(options);
}