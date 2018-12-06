<%@ page language="java" contentType="text/html; charset=Shift-JIS" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<section id="intro">
    <div class="container">
		<h2>
			<span class="highlight primary text-info btn-rounded">${headerValue}</span>
		</h2>
		<div class="control-group">		
			<div class="controls" style="position: relative">
				<span style="margin-left:10px;"></span>	
				<select id="kitchens">					
					<option value="0">All</option>
					<c:forEach items="${kitchens}" var="kitchen"  varStatus="loop">
						<option value="${kitchen.id}">${kitchen.name}</option>
					</c:forEach>							 
				</select>
				<select id="deliveryPartners">	
					<option value="">All</option>
					<c:forEach items="${deliveryPartners}" var="dp"  varStatus="loop">
						<option value="${dp}">${dp.name}</option>
					</c:forEach>							 
				</select> 
				<input type="text" id="startTime" placeholder="Start Time"/> 
				<span style="margin-left:3px;"></span>				
				<input type="text" id="endTime" placeholder="End Time"/>								
			</div>	
			<div style="float:right;margin-right:10%">
				<button type="submit" id="submit" class="btn">Search</button> 
			</div>
		</div>			
        <div style="margin-top:5%;" id="listingDiv">		   
            
        </div>
    </div>
</section>
<script>
$(document).ready(function() {
	$('#startTime').datetimepicker({
		format: 'YYYY-MM-DD HH:mm'
	});
	$('#endTime').datetimepicker({
		format: 'YYYY-MM-DD HH:mm'
	});	
	var url = $(location).attr('href');
	if(url.contains('zomatoGmailAuto')) {
		$("#deliveryPartners").hide();			
		$('#kitchens option[value="1"]').attr("selected", "selected");
		$('#kitchens').children('option[value="0"]').css('display','none');		
	}
});
$("#submit").click(function() {
	var tab = $(location).attr('href');	
	var action="";
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var kitchenId = $("#kitchens option:selected" ).val();
	var deliveryPartner = $("#deliveryPartners option:selected" ).val();
	if(startTime =="" || endTime=="") {
		$("#listingDiv").html("<div style='margin-left:40%'><h5>Please select start and end date !!!!</h5></div>");
	} else {
		var action = "";
		if(tab.contains('zomatoGmailAuto')) {		
			$("#listingDiv").html("<span style='margin-left:40%' class=\"text-success lead\"><b>Processing Please Wait....</b></span>");
			action = "/processZomatoOrders?startTime="+startTime+"&endTime="+endTime+"&kitchenId="+kitchenId;
			invokeAjaxCall(action, "json", tab);				
		} else if(tab.contains('orderLayout')) {
			action = "/orders?startTime="+startTime+"&endTime="+endTime+"&kitchenId="+kitchenId+"&deliveryPartner="+deliveryPartner;
			invokeAjaxCall(action, "html", tab);			
		}		
	}	
});

function invokeAjaxCall(action, dataType, tabUrl) {	
	$.ajax({
		type: "GET",
		url: action,	
		contentType: "application/json",
		dataType: dataType,
		success: function(response) {
			if(tabUrl.contains('zomatoGmailAuto')) {
				if(response.error==true) {
					$("#listingDiv").html("<span style='margin-left:40%' class=\"text-error lead\"><b>"+response.status+"</span>");	
				} else if(response.error==false) {
					$("#listingDiv").html("<span style='margin-left:40%' class=\"text-success lead\"><b>"+response.status+"</span>");	
				}
			} else if(tabUrl.contains('orderLayout')) {
				$("#listingDiv").html(response);
			}			
		}
	});	
}
</script>