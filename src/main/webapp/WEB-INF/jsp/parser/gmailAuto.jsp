<%@ page language="java" contentType="text/html; charset=Shift-JIS" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<section id="intro">
	<div class="container">
		<div class="row">
			<div class="span6">
				<div class="form-horizontal">
					<h2>
					<span class="highlight primary text-info">Process Zomato Emails </span>
					</h2>					
					<div class="control-group">
						<label class="control-label" for="inputEmail">Start Time</label>
						<div class="controls" style="position: relative">
						<input type="text" id="startTime" placeholder="Start Time"/> </div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmail">End Time</label>
						<div class="controls" style="position: relative">
							<input type="text" id="endTime" placeholder="End Time"/> </div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button id="processOrders" type="submit" class="btn">Process Orders</button>
						</div>
					</div>
				</div>
			</div>
			<div class="span6">

          <div class="group section-wrap upper" id="upper">
            <div id="response">					
			</div>            
          </div>
		</div>
	</div>
</div>
</section>
<script>
//MM/DD/YY HH:mm:ss
	$(document).ready(function() {
		$('#startTime').datetimepicker({
			format: 'YYYY-MM-DD HH:mm'
		});
		$('#endTime').datetimepicker({
			format: 'YYYY-MM-DD HH:mm'
		});		
	});
	$( "#processOrders" ).click(function() {
	 $("#response").html("<span class=\"text-success lead\"><b>Processing....</b></span>");	
	  var startTime = $("#startTime").val();
	  var endTime = $("#endTime").val();	  
		 $.ajax({
			type: "GET",
			url: "/processZomatoOrders?startTime="+startTime+"&endTime="+endTime,	
			contentType: "application/json",
			dataType: "json",
			success: function(response) {	
				if(response.error==true) {
					$("#response").html("<span class=\"text-error lead\"><b>"+response.status+"</span>");	
				} else if(response.error==false) {
					$("#response").html("<span class=\"text-success lead\"><b>"+response.status+"</span>");	
				}
				
		    }
		});
	});	
</script>