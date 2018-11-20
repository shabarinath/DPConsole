/*var Ajax;
if (Ajax && (Ajax != null)) {
	Ajax.Responders.register({
	  onCreate: function() {
        if($('spinner') && Ajax.activeRequestCount>0)
          Effect.Appear('spinner',{duration:0.5,queue:'end'});
	  },
	  onComplete: function() {
        if($('spinner') && Ajax.activeRequestCount==0)
          Effect.Fade('spinner',{duration:0.5,queue:'end'});
	  }
	});
}*/
$(document).ready(function() {

       setTimeout(function(){
			$(".notification").fadeOut('slow');
		}, 5000);
});


function toggleSidePane(callback)
{
    if($(".info")==null || $(".info").css('display') == "none")
    {
        //$(".wrap").css({'margin-left':'265px'});
        $('.wrap').animate({
            marginLeft:"265px"
        }, 300, function() {
            $(".info").show();
            $("#sidepane-toggler").attr({"src":$("#sidepane-toggler").attr("src").replace("right","left")});
            if(callback!=undefined)
                callback();
        });
    }
    else
    {
        $(".info").hide();
        $('.wrap').animate({
            marginLeft:"5px"
        }, 300, function() {
            $("#sidepane-toggler").attr({"src":$("#sidepane-toggler").attr("src").replace("left","right")});
            if(callback!=undefined)
                callback();
        });
    }
}



function showAjaxPopUp(settings)
{
    settings  = jQuery.extend({
                    url:"",
                    onComplete:function(){},
                    onPostComplete:function(){},
                    parameters:{},
                    postParameters:{}
                }, settings);

    jQuery.openPopupLayer({
        name: settings.name,
        title:settings.title,
        url: settings.url,
        showWindow:true,
        width:settings.width,
        height:settings.height,
        afterClose:settings.afterClose,
        beforeClose:settings.beforeClose,
        closeOnEscape:settings.closeOnEscape,
        parameters:settings.parameters,
        success:function()
        {
            settings.onComplete();
            checkFormAndPost(settings);
        }
    });
}

function checkFormAndPost(settings)
{
    if(settings.formId != undefined && $(settings.formId) != null)
    {
        Event.observe(settings.formId, 'submit', function(event) {
            Event.stop(event);
            postAjaxPopUp({
                name:settings.name,
                formId:settings.formId,
                parameters:settings.postParameters,
                onComplete:function(){
                    checkFormAndPost(settings);
                    settings.onPostComplete();
                }
            });
        });
    }
}


function postAjaxPopUp(settings)
{
    $(settings.formId).request({
        method:"post",
        parameters:{"subview":true},
        onComplete: function(response)
        {
            $('popup-body-'+settings.name).update(response.responseText);
            settings.onComplete();
        }
    });
    return false;
}


function setUrlHash(params)
{
    location.hash = jQuery.param(params);
}

function getUrlHash()
{
    return unparam(location.hash.substr(1))
}

function unparam(p){
    var ret = {},
        seg = p.replace(/^.*\?/,'').split('&'),
        len = seg.length, i = 0, s;
    for (;i<len;i++) {
        if (!seg[i]) { continue; }
        s = seg[i].split('=');
        ret[s[0]] = s[1];
    }
    return ret
}

function goToUrl(url){
    window.location = url;
}


