<%@ page language="java" contentType="text/html; charset=Shift-JIS" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<section id="intro">
    <div class="container">
		<h2>
			<span class="highlight primary text-info btn-rounded">Create/Edit Item</span>
		</h2>
		<div class="control-group" style="margin-left:20%;">
			<c:choose>
				<c:when test="${id > 0}">
					<c:set var = "disabled" value = "true"/>
				</c:when>
				<c:otherwise>
					<c:set var = "disabled" value = "false"/>
				</c:otherwise>
			</c:choose>			
			<form:form  commandName="kitchenItem" id="kitchenItemForm" name="kitchenItemForm" method="post" action="/saveKitchenItem/${id}">						
			  <div class="controls" style="position: relative">							
					<form:select disabled="${disabled}" path="item.subCategory.category">	
						<!-- <option value="">All</option> -->
						<c:forEach items="${categories}" var="category"  varStatus="loop">
							<form:option value="${category.name}">${category.name}</form:option>
						</c:forEach>							 
					</form:select>
					<form:select disabled="${disabled}" path="item.subCategory">	
						<!-- <option value="">All</option> -->
						<c:forEach items="${subCategories}" var="subCategory"  varStatus="loop">
							<form:option value="${subCategory.name}">${subCategory.name}</form:option>
						</c:forEach>							 
					</form:select>
					<c:choose>
						<c:when test="${id > 0}">
							<form:select disabled="${disabled}" path="kitchen.id">					
								<!-- <option value="0">All</option>-->
								<c:forEach items="${kitchens}" var="kitchen"  varStatus="loop">
									<form:option value="${kitchen.id}">${kitchen.name}</form:option>
								</c:forEach>							 
							</form:select>
						</c:when>
						<c:otherwise>
							<form:select path="item.type">	
								<!-- <option value="">All</option> -->
								<c:forEach items="${types}" var="type"  varStatus="loop">
									<form:option value="${type}">${type.name}</form:option>
								</c:forEach>							 
							</form:select>
						</c:otherwise>
					</c:choose>						
					<c:if test="${id <= 0}">
						<label><b>Create Items for all below Kitchens</b></label>
						<div id="kitchensDiv">
							<c:forEach items="${kitchens}" var="kitchen"  varStatus="loop">
								<form:checkbox path="active"/> ${kitchen.name}
							</c:forEach>										
						</div>
					</c:if>
					<label></label>
					<c:choose>
						<c:when test="${id <= 0}">
							<spring:bind path="item.name">		
								<form:input path="item.name" cssClass="${status.error ? 'errorInput' : ''}" placeholder="Item Name"/>
							</spring:bind>
						</c:when>
						<c:otherwise>
							<spring:bind path="item.name">		
								<form:input path="item.name" cssClass="${status.error ? 'errorInput' : ''}" placeholder="Item Name" readonly="true"/>
							</spring:bind>
						</c:otherwise>
					</c:choose>				
					<c:if test="${id > 0}">
						<spring:bind path="active">
							<form:checkbox path="active"/> Is Active
						</spring:bind>						
					</c:if>
					<label>Manufacturing Price</label>
					<spring:bind path="item.manufacturingPrice">		
						<form:input path="item.manufacturingPrice" cssClass="${status.error ? 'errorInput' : ''}"/>
					</spring:bind>
					<label>Packing Cost</label>
					<spring:bind path="item.packingPrice">		
						<form:input path="item.packingPrice" cssClass="${status.error ? 'errorInput' : ''}"/>
					</spring:bind>
					<label>Market Price</label>
					<spring:bind path="marketPrice">		
						<form:input path="marketPrice" cssClass="${status.error ? 'errorInput' : ''}"/>
					</spring:bind>										
				  </div>	
				  <button class="btn btn-success" type="submit" >Submit</button>				  
			</form:form>
		</div>			    
    </div>
</section>
<script>
	$(document).ready(function () {
		var someObj = {};
		someObj.chkArray = [];
		var $checks = $('#kitchensDiv input[type="checkbox"]').load(function () {
			var ids = $checks.filter(':checked').map(function () {
				return this.id
			}).get();
			alert("Handler for .click() called.");
			alert("GRANTED: " + ids);
			someObj.chkArray = ids;
		});		
	})
</script>