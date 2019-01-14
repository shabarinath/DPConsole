<%@ page language="java" contentType="text/html; charset=Shift-JIS" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<section id="intro">
    <div class="container">
		<div style="float:right;margin-right:10%;">
			<!-- <a href="/kitchenItem/0" class="btn btn-danger btn-large btn-rounded">Create Item</a> -->
		</div>
		<h2>
			<span class="highlight primary text-info btn-rounded">Menu</span>
		</h2>			
		<div class="control-group">		
			<div class="controls" style="position: relative">
				<span style="margin-left:10px;"></span>	
				<select id="kitchens">					
					<!-- <option value="0">All</option>-->
					<c:forEach items="${kitchens}" var="kitchen"  varStatus="loop">
						<option value="${kitchen.id}">${kitchen.name}</option>
					</c:forEach>							 
				</select>
				<select id="categories">	
					<!-- <option value="">All</option> -->
					<c:forEach items="${categories}" var="category"  varStatus="loop">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>							 
				</select>
				<select id="subCategories">	
					<!-- <option value="">All</option> -->
					<c:forEach items="${subCategories}" var="subCategory"  varStatus="loop">
						<option value="${subCategory.id}">${subCategory.name}</option>
					</c:forEach>							 
				</select> 												
			</div>	
			<div style="float:right;margin-right:10%">
				<button type="submit" id="submit" class="btn">Search</button> 
			</div>
		</div>			
        <div style="margin-top:5%;" id="itemsDiv">		   
            
        </div>
    </div>
</section>
<script>
$("#submit").click(function() {		
	var kitchenId = $("#kitchens option:selected").val();
	var categoryId = $("#categories option:selected").val();		
	var subCategoryId = $("#subCategories option:selected").val();	
	var action = "/loadKitchenItems?kitchenId="+kitchenId+"&categoryId="+categoryId+"&subCategoryId="+subCategoryId+"&ajax=true";
	invokeAjaxCall(action, "html");							
});

function invokeAjaxCall(action, dataType) {	
	$.ajax({
		type: "GET",
		url: action,	
		contentType: "application/json",
		dataType: dataType,
		success: function(response) {			
			$("#itemsDiv").html(response);						
		}
	});	
}
</script>