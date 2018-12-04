<%@ page language="java" contentType="text/html; charset=Shift-JIS" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<section id="intro">
    <div class="container">
		<div class="control-group">		
			<div class="controls" style="position: relative">
				<span style="margin-left:10px;"/>		
				<input type="text" id="startTime" placeholder="Start Time"/> 
				<span style="margin-left:3px;"/>				
				<input type="text" id="endTime" placeholder="End Time"/>
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
});
$("#submit").click(function() {
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var kitchenId = $("#kitchens option:selected" ).val();
	var deliveryPartner = $("#deliveryPartners option:selected" ).val();
	if(startTime =="" || endTime=="") {
		$("#listingDiv").html("<div style='margin-left:40%'><h5>Please select start and end date !!!!</h5></div>");
	} else {
		$.ajax({
		type: "GET",
		url: "/getOrderDetails?startTime="+startTime+"&endTime="+endTime+"&kitchenId="+kitchenId+"&deliveryPartner="+deliveryPartner,	
		contentType: "application/json",
		dataType: "html",
		success: function(response) {				
			$("#listingDiv").html(response);
		}
		});	
	}	
});
</script>