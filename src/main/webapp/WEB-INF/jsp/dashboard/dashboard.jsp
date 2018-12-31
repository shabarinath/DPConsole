<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<section>
	<div class="container">      
		<table class="table table-striped">
			<thead>
               <tr>
				  <th>
                     Kitchen
                  </th>
                  <th>
                     Zomato
                  </th>
                  <th>
                     Swiggy
                  </th>
                  <th>
                     FoodPanda
                  </th>
                  <th>
                     UberEats
                  </th>
               </tr>
            </thead>
            <tbody>
			<c:forEach var="entry" items="${stats}">			
			  <tr>
                  <td>
                     <b><c:out value="${entry.key}"/></b>
                  </td>
                  <td>
					 <c:choose>
						<c:when test="${empty entry.value['Zomato']}">
							--
						</c:when>
						<c:otherwise>
							Amount: <b>&#8377; <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${entry.value['Zomato'].totalAmount}" /></b>
							 <br/>
							 No Of Items: 					 
							 <b><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${entry.value['Zomato'].noOfItems}" /></b>
							 <br/>
							 No Of Orders: 
							 <b><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${entry.value['Zomato'].noOfOrders}" /></b>
						</c:otherwise>
					</c:choose>	                     					
                  </td>
                  <td>
					<c:choose>
						<c:when test="${empty entry.value['Swiggy']}">
							--
						</c:when>
						<c:otherwise>
							Amount: &#8377; <b><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${entry.value['Swiggy'].totalAmount}" /></b>
							<br/>
							No Of Items: <b><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${entry.value['Swiggy'].noOfItems}" /></b>
							<br/>
							No Of Orders: <b><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${entry.value['Swiggy'].noOfOrders}" /></b>					 
						</c:otherwise>
					</c:choose>	                      
                  </td>
                  <td>
					<c:choose>
						<c:when test="${empty entry.value['FoodPanda']}">
							--
						</c:when>
						<c:otherwise>
							Amount: <b>&#8377; <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${entry.value['FoodPanda'].totalAmount}" /></b>
						<br/>
							No Of Items: <b><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${entry.value['FoodPanda'].noOfItems}" /></b>
						<br/>
							No Of Orders: <b><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${entry.value['FoodPanda'].noOfOrders}" /></b>
						</c:otherwise>
					</c:choose>	 									
                  </td>
				  <td>
					 <c:choose>
						<c:when test="${empty entry.value['Uber Eats']}">
							--
						</c:when>
						<c:otherwise>
							 Amount: <b>&#8377; <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${entry.value['Uber Eats'].totalAmount}" />
							 <br/></b>
							 No Of Items: <b><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${entry.value['Uber Eats'].noOfItems}" />
							 <br/></b>
							 No Of Orders: <b><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0" value="${entry.value['Uber Eats'].noOfOrders}" /></b>
						</c:otherwise>
					</c:choose>	                        
                  </td>
               </tr>
			</c:forEach>			
            </tbody>
         </table>     
   </div>
</section>