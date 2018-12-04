<%@ page language="java" contentType="text/html; charset=Shift-JIS" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<div class="">		   
	<div class="">
		<div class="form-horizontal">	
		 <c:choose>
			  <c:when test="${empty orders}">
				<h5><span style="margin-left:40%">No Orders Found!!!!</span></h5>
			  </c:when>		
			<c:otherwise>			  
			<table class="table table-striped">
			<thead>
				<tr>
					<th>
						#
					</th>
					<th>
						Order Id
					</th>
					<th>
						Kitchen
					</th>
					<th>
						Delivery Partner
					</th>
					<th>
						Order Time
					</th>
					<th>
						Total
					</th>
					<th>
						DP Paid
					</th>
				</tr>
			</thead>
			<div>					
				<tbody>																  
						<c:forEach items="${orders}" var="order"  varStatus="loop">						
							<tr>
								<td>
									${loop.count}.									
								</td>
								<td>
									<a href="#">${order.deliveryPartnerOrderId}</a>
								</td>
								<td>
									${order.kitchen.name}
								</td>
								<td>
									${order.deliveryPartner}
								</td>
								<td>
									${order.orderedTime}
								</td>
								<td>
									&#8377; ${order.totalCost}
								</td>
								<td>
									0
								</td>
							</tr>
						</c:forEach>					  																
				</tbody>
			</div>
			</table>
			</c:otherwise>	
			</c:choose>
		</div>
	</div>
</div>