<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<head>
	<script type="text/javascript">
		function updateCount(id, total) {		
			$("#total"+id).html("("+total+")");
		}
	</script>
</head>
<section>
	<c:choose>	
		 <c:when test = "${empty statsMap}">
			<div align="center">
				<h4><p class="text-error">No Data for Selected Criteria !!</p></h4>
			</div>
		 </c:when>
		 <c:otherwise>
			<c:forEach var="category"  varStatus="categoryCount" items="${statsMap}">	
				<hr/>
				<h3><p class="text-warning"><b>${category.key} <span title="attribute" id="total${categoryCount.count}"}></span></b></p></h3>
				<hr/>
				<div>                   
				  <div class="accordion" id="accordion${categoryCount.count}">
					<div class="accordion-group">
					  <div class="accordion-heading">
						<a class="accordion-toggle active" data-toggle="collapse" data-parent="#accordion${categoryCount.count}" href="#collapse${categoryCount.count}">
						<i class="icon-caret-down"></i> ${category.key} </a>
					  </div>
					  <div id="collapse${categoryCount.count}" class="accordion-body collapse in">
						<div class="accordion-inner">
						<table class="table">
							<thead>
							  <tr>
								<th>
								  #
								</th>
								<th>
								  Name
								</th>
								<th>
								  Quantity
								</th>								
							  </tr>
							</thead>
								<tbody>
									<c:set var="total" value="${0}"/>
									 <c:forEach var="subCategory"  varStatus="subCategoryCount" items="${category.value}">			
										  <tr class="success">
											<td>
											  ${subCategoryCount.count})
											</td>
											<td>
											  ${subCategory.key}
											</td>
											<td>
											 <c:set var="countsMapKey" value="${subCategory.key}_TOTAL" />
											 <c:out value="${subCategoryCountMap[countsMapKey]}"/>
											</td>											
										  </tr>
										  <c:set var="total" value="${total + subCategoryCountMap[countsMapKey]}" />
									</c:forEach>
									<tr class="error">
										<td>										  
										</td>
										<td>
										  <b>Total</b>
										</td>
										<td>
										  <b>${total}</b>
										  
										</td>
										 <script>updateCount(${categoryCount.count}, ${total});</script>
									</td>
								</tbody>	
							</table>						 
						</div>
					  </div>
					</div>
					<!-- Items Iteration --->
					<c:forEach var="subCategory"  varStatus="itemsSubCategoryCount" items="${category.value}">  
						<div class="accordion" id="accordion${categoryCount.count}${itemsSubCategoryCount.count}">
							<div class="accordion-group">
							  <div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion${categoryCount.count}${itemsSubCategoryCount.count}" href="#collapse${categoryCount.count}${itemsSubCategoryCount.count}">
								<i class="icon-caret-right"></i> ${subCategory.key} </a>
							  </div>
							  <div id="collapse${categoryCount.count}${itemsSubCategoryCount.count}" class="accordion-body collapse">
								<div class="accordion-inner">
									<table class="table">
										<thead>
										  <tr>
											<th>
											  #
											</th>
											<th>
											  Name
											</th>
											<th>
											  Quantity
											</th>								
										  </tr>
										</thead>
										<tbody>
											<c:set var="itemsTotal" value="${0}"/>
											 <c:forEach var="item"  varStatus="itemCount" items="${subCategory.value}">			
												  <tr class="success">
													<td>
													 ${itemCount.count})
													</td>
													<td>
													  ${item.key}
													</td>
													<td>
													  ${item.value}
													</td>											
												  </tr>
												 <c:set var="itemsTotal" value="${itemsTotal + item.value}" />
											</c:forEach>
											<tr class="error">
												<td>										  
												</td>
												<td>
												  <b>Total</b>
												</td>
												<td>
												  <b>${itemsTotal}</b>
												</td>											
											</td>
										</tbody>	
									</table>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>				
			</div>
			</div>
			</c:forEach>			
		 </c:otherwise>
	</c:choose>				
</section>