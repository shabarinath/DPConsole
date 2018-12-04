var deviceDiscTimerIds = new Object();
var _bRefresh = false;
function bRefresh() {
	_bRefresh = true;
}
function $1(divName) { 
	return document.getElementById(divName); 
};

function initializeScrollar(contentId, heightBlockId, alwaysVisible){
	if(!heightBlockId || !($1(heightBlockId))){
		heightBlockId = contentId;
	}
	if(contentId && $1(contentId)){
		//$('#'+contentId).slimScroll({height: $('#'+heightBlockId).height()/*,alwaysVisible: true*/});	
	}
};
/* Ajax functions */
var ajaxRequests = [];
function doGet(reqUrl,options){
	var renderTo = "contentDiv";
	var parameters = {};
	if((options != null) && (options.renderTo != null)/* && $1(options.renderTo)*/){
		renderTo = options.renderTo;
	}else if((options != null) && (options.childElemRenderTo != null)/* && $1(options.renderTo)*/){
		renderTo = $('#'+options.childElemRenderTo).parent().attr("id");
	}if(options == null || !options.noModal){
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
	if(parameters.mspId && parameters.mspId <= 0){
		if($1('bcMspId') && $1('bcMspId').value > 0) {
			parameters.mspId = $1('bcMspId').value;
		}
	}
	if(parameters.clientId && parameters.clientId <= 0){
		if($1('bcClientId') && $1('bcClientId').value > 0) {
			parameters.clientId = $1('bcClientId').value;
		}
	}
	ajaxRequests[renderTo] = $.ajax({
	   type: method,
	   url: reqUrl,
	   data: parameters,
	   dataType:dataType,
	   success: function(data, status, xhr){
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
				if(options == null || !options.noModal){
					hideWaiting(renderTo);
				}
				$("#"+renderTo).show();
				ajaxRequests[renderTo] = null;
			}
	   },
	   complete : function(xhr,status){
		   if((status == 'error') && ((xhr.status == 991) || (xhr.status == 901))) {
			   window.location.href = '/login.do';
		   }
		   if((status == 'error') && (xhr.status == 0)) {
			   try{
				   //location.reload(true);
			   }catch(e){}
		   }
	   }
	 });
};

function ajaxGet(url, options){
	doGet(url,{
		renderTo:(options?options.contentDiv:''),
		noModal:(options.showWaiting == true)?false:true,
		requestParameters:(options?options.requestParameters:''),
		onCompleteFunction:(options?options.onCompleteFunction:''),
		functionParams:(options?options.functionParams:''),
		scrollTop:(options.scrollTop?options.scrollTop:false)
	});
};

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

function load_get(url, divName, parameters, onCompleteFunction, functionParams, dontBlockDiv) {
	if($1('inventoryDetailsBlk')) {
		load(url, divName, parameters, onCompleteFunction, functionParams, dontBlockDiv);
	} else {
		url = url.replace('device.do', 'newDevice.do');
		url = url.replace('publicCloud.do?action=viewResourceDetails', 'newDevice.do?action=details');
		get(url, divName, parameters, onCompleteFunction, functionParams, dontBlockDiv);
	}
};

function getRequest(url, params, onCompleteFunction, functionParams, options) {
	var parameters = {};
	if(params){
		parameters = params;
	}
	parameters._ajax=true;
	if(options && options.method == 'POST'){
		return $.post(url,parameters).done(function(responseData){
			evalScripts(responseData);
			if(onCompleteFunction){
				onCompleteFunction.call(this, responseData, functionParams);
			}
		}).fail(function(responseData){
			
		});
	}else{
		$.get(url, parameters, function(data){
			if(!options || !options.skipEvalScripts) {
				evalScripts(data);
			}
			if(onCompleteFunction != null) {
				onCompleteFunction.call(this, data, functionParams);
			}
		});
	}
};

function getJSON(jsonParams) {
	var parameters = {};
	if(jsonParams.params){
		parameters = jsonParams.params;
	}
	parameters._ajax=true;
	if(jsonParams.method == 'POST'){
		return $.post(jsonParams.url,parameters).done(function(jsonData){
			if(jsonParams.onSuccessFunction){
				jsonParams.onSuccessFunction.call(this, jsonData, jsonParams.functionParams);
			}
		}).fail(function(jsonData){
			if(jsonParams.onFailureFunction){
				jsonParams.onFailureFunction.call(this, jsonData, jsonParams.functionParams);
			}
		});
	}else{
		return $.getJSON(jsonParams.url,parameters).done(function(jsonData){
			if(jsonParams.onSuccessFunction){
				jsonParams.onSuccessFunction.call(this, jsonData, jsonParams.functionParams);
			}
		}).fail(function(jsonData){
			if(jsonParams.onFailureFunction){
				jsonParams.onFailureFunction.call(this, jsonData, jsonParams.functionParams);
			}
		});
	}
};


function doPost(formOptions){
	var formObj = $1(formOptions.formId);
	var actionUrl;
	if(formOptions.url){
		actionUrl = formOptions.url;
	}else{
		actionUrl = formObj.action;
	}
	if(actionUrl.indexOf('_ajax')<0){
		if(actionUrl.indexOf('?')<0){
			actionUrl += '?_ajax=true';
		}else{
			actionUrl += '&_ajax=true';
		}
	}
	var extraParams = {_ajax:true};
	if(formOptions && formOptions.params){
		for(param in formOptions.params) {
			extraParams[param]=formOptions.params[param]; 
		}
	}
	var renderTo = "contentDiv";
	if(formOptions.renderTo != null && $1(formOptions.renderTo)){
		renderTo = formOptions.renderTo;
	}
	if(extraParams && extraParams._skip){
		if(formObj && formObj._cid){
			extraParams._cid = formObj._cid.value;
		}
		if(formObj && formObj._csrf){
			extraParams._csrf = formObj._csrf.value;
		}
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
		success: function(data, status, xhr){
			handleAjaxResponse(data,renderTo,formOptions);
			if(formOptions != null && formOptions.onCompleteFunction != null){
				formOptions.onCompleteFunction.call(this, data, formOptions.functionParams)
			}
		},
	   error:function (xhr, textStatus, errorThrown) {
			if((xhr.status == 403) && (formOptions.formId == 'j_loginForm')) {
				location.reload(true);
			}
	   },
	   complete : function(xhr,status){
		   if((status == 'error') && ((xhr.status == 991) || (xhr.status == 901))) {
			   window.location.href = '/login.do';
		   }
	   }
	};
	
	if (typeof csrfHeader != 'undefined') {
		$.extend(opts, { headers: csrfHeader });
	}
	
	$(formObj).find("input:file").each(function(i){
		if($(this) == null || $(this).val() == ''){
			$(this).attr("disabled","disabled");
		}
	});
	if(formOptions.confirmMsg){
		bootbox.dialog(formOptions.confirmMsg, [{"label" : "Yes","class" : "btn-success","callback": function() {
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

function doFileUpload(formOptions){
	var extraParams = {_ajax:true};
	try{
		if(formOptions && formOptions.params){
			for(param in formOptions.params) {
				extraParams[param]=formOptions.params[param]; 
			}
		}
		var renderTo = "contentDiv";
		if(formOptions.renderTo != null && $1(formOptions.renderTo)){
			renderTo = formOptions.renderTo;
		}
		if(!formOptions.noModal) {	
			showWaiting(renderTo);
		}
		$($1(formOptions.formId)).find("input:file").each(function(i){
			if($(this) == null || $(this).val() == ''){
				$(this).attr("disabled","disabled");
			}
		});
		var xhr = $('#'+formOptions.formId).ajaxSubmit({data:extraParams}).data('jqxhr');
		xhr.done(function(data, status, xhr) {
			handleAjaxResponse(data,renderTo,formOptions);
			if(formOptions != null && formOptions.onCompleteFunction != null){
				formOptions.onCompleteFunction.call(this, data, formOptions.functionParams)
			}
		});
		xhr.fail(function(xhr, status, err) {
			handleAjaxResponse(err,renderTo,formOptions);
		});
		xhr.always(function(xhr, status) {
			/*$('#status').html('complete (' + status + ')');*/
		});
	}catch(e){}
	return false;
};

function handleAjaxResponse(data,renderTo,options){
	if(renderTo != null && $1(renderTo)) {
		$("#"+renderTo).html(data);
		$("#"+renderTo).show();
		/*if($1("j_loginForm")){
			window.location.href = '/login.do';
		}*/
		if(renderTo == 'modalBody'){
			$("#sccModal").show();
			//alert(getDOMHeight('sccModal'));
			adjustModalDimensions(options);				
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

function post(formId, divName, onCompleteFunction, functionParams, dontBlockDiv) {
	doPost({
		formId:formId,renderTo:divName,onCompleteFunction:onCompleteFunction,functionParams:functionParams,noModal:(dontBlockDiv == true)?true:false
	});
	return false;
};
function postForm(formObj, divName, onCompleteFunction, functionParams, dontBlockDiv) {
	doPost({
		formId:$(formObj).attr('id'),renderTo:divName,onCompleteFunction:onCompleteFunction,functionParams:functionParams,noModal:(dontBlockDiv == true)?true:false
	});
	return false;
};
function showWaiting(loadDivId,customMsg) {
	try{
		if(loadDivId && $1(loadDivId)){
			if(customMsg!=undefined){
				$('#'+loadDivId).mask(customMsg);
			}
			else{
				$('#'+loadDivId).mask('Loading... please wait');
			}
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
function findPos(obj) {
	var curleft = curtop = 0;
	if (obj.offsetParent) {
		do {
			curleft += obj.offsetLeft;
			curtop += obj.offsetTop;
		} while (obj = obj.offsetParent);
	}
	return [curleft,curtop];
};

var Class = {
	create: function()
	{
		return function() {
			this.initialize.apply(this, arguments);
		}
	}
};
var Ajax = new Object();
Ajax.Updater = Class.create();
Ajax.Updater.prototype = {
	initialize: function(container, url, options) {
		try{
			var contentBlk = container;
			if(container.indexOf('-') > 0){
					contentBlk = container.substring(0,container.lastIndexOf('-'));
			}
			var options = {
				renderTo:contentBlk,
				noModal:false,
				scrollTop:false
			}
			doGet(url,options);
		}catch(e){}
	}
};

function getForm(formName) {
	var obj;
	if(document.forms[formName] && document.forms[formName].length > 1) {
		obj = document.forms[formName];
	}
	else obj = document.forms[0];
	return obj;
};

var initLoad = true;
var divHsNameMap = {};
function pageload(hash) {
	if(!hash) {
		if(initLoad) {
			return;
		} else {
			window.location.href='';
			return;
		}
	}
	/*To resolve the bug 7479(Unable to refresh the page while url has # symbol in it)*/
	if (($.support.msie && parseInt($.support.version) <= 6)) {
		var currentUrl = window.location.href;
		if ( currentUrl.indexOf("#") > -1 ){
			var hash = trim(currentUrl.substr(currentUrl.lastIndexOf("#")),"#");
		}
	}
	var divLoad = divHsNameMap[escape(hash)];
	if(_bRefresh) {
		_bRefresh = false;
		return;
	}
	var currentUrl = window.location.href;
	if(divLoad == null || divLoad ==  'undefined') {
		divLoad = "contentDiv";
		if((currentUrl.indexOf('#') > -1) && (currentUrl.indexOf('#') != currentUrl.lastIndexOf('#'))) {
			if($1('setupBody')) // To Check whether the user is in setup tab
				divLoad = "setupBody";
			else if($1('reportsDiv')) // To Check whether the user is in reports tab
				divLoad = "reportsDiv";
			else if($1('patchDiv')) // To Check whether the user is in patches tab
				divLoad = "patchDiv";
			else if($1('ticketAreaDiv')) // To Check whether the user is in patches tab
				divLoad = "ticketAreaDiv";
			else if($1('jobsBody')) // To Check whether the user is in jobs tab
				divLoad = "jobsBody";
		}
	}
	get(hash, divLoad);
	initLoad = false;
};

function load(url,divId){
	url = url.replace(/^.*#/, '');
	
	if(divId == null || $1(divId) ==  null)
		divId = "contentDiv";
	divHsNameMap[escape(url)] = divId;
	/*$.cookie('divHsNameRefreshed', divId);*/
	_bRefresh = false;
	$.history.load(url);
	window.scrollTo(0,0);
	return;
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
function suggestionField(fieldId){
	$('#'+fieldId).bind("focus",function(){
		if($.trim(this.value) == $(this).attr('alt')){
			this.value = '';
			$(this).removeClass('suggestion-field');
		}
	});
	$('#'+fieldId).bind("focusout",function(){
		if($.trim(this.value) == ''){
			this.value = $(this).attr('alt');
			$(this).addClass('suggestion-field');
		}
	});
};

function getDOMHeight(elId){
	var h=0;
	$('#'+elId).children().each(function(i){
		h+=$(this).height();	
	});
	return h;
};
function getURLAndParametersMap(url,parameters){
	var res = {};
	res.url = url;
	if(url.indexOf('?') != -1){
		res.url = url.substring(0,url.indexOf('?'));
		if(url.indexOf('+') >= 0){
			url = url.replace(/\+/g, ' ');
		}
		var regex = /[?&]([^=#]+)=([^&#]*)/g;
		var paramMap = {};
		while(match = regex.exec(url)) {
			paramMap[match[1]] = match[2];
		}
		$.extend(paramMap,parameters);
		res.params = paramMap;
	}else{
		res.url = url;
		if(parameters){
			res.params = parameters;
		}else{
			res.params = {};
		}
	}
	return res;
};

if (!String.prototype.startsWith) { //IE11
	String.prototype.startsWith = function (searchString, position) {
	position = position || 0;
	return this.indexOf(searchString, position) === position;
	};
}

function selectDropdown(options) {
	return $('#'+options.elId).selectize();
};

function setSelectDropdownValue(options) {
	if($('#'+options.elId).hasClass('selectized')){
		selectize = $('#'+options.elId)[0].selectize;
		selectize.setValue(options.value, true);
	}
};

function confirmAndDoPost(url,confirmMsg, divName, parameters, onCompleteFunction, functionParams, dontBlockDiv) {
	confirmModal({
		msg:confirmMsg,
		confirmCallBackfn:function(){
			doGet(url,{
				renderTo:divName,
				method:'POST',
				noModal:(dontBlockDiv == true)?true:false,
				requestParameters:parameters,
				onCompleteFunction:onCompleteFunction,
				functionParams:functionParams,
				scrollTop:false
			});
		}
	});
};

if (!String.prototype.contains) {
	String.prototype.contains = function(str, ignoreCase) {
		return (ignoreCase ? this.toUpperCase() : this).indexOf(ignoreCase ? str.toUpperCase() : str) >= 0;
	};
}
