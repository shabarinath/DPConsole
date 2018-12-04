<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<c:set var="targetDiv" value="listingDiv"/>
<div class="listHeader">
	<form name="searchOrders" id="searchOrders" method="post" action="/orders" onsubmit="return false;" >
		<div class="listFilters col-sm-6" style="padding-left:0;">
			<div class="listStatusSelection form-inline">
				<div class="input-append" style="float: right; margin-left: 5px;">
					<input name="keyword" id="keyword" type="text"
						class="form-control ${( empty param.keyword) ? 'greyText':''} fontWeightBold fontsize11px"
						value="${(not empty param.keyword) ? param.keyword : 'Search here'}"
						onfocus="javascript:hideSearchText(this.id);"
						onblur="javascript:showSearchText(this.id);" size="28" />
					<button class="btn searchButton" type="submit"
						onclick="javascript:postSearchForm('searchOrders','keyword','${targetDiv}');">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
		</div>
<div style="clear:both; padding-top:7px;">
			<c:choose>
				<c:when test="${ordersPage != null && ordersPage.list != null && not empty ordersPage.list}">
					<span> ${ordersPage.totalResults} Items Found, Displaying
							${((ordersPage.pageNo-1) * ordersPage.pageSize) + 1} To
							${((ordersPage.pageNo-1) * ordersPage.pageSize) + fn:length(ordersPage.list)}.
					</span>
				</c:when>
			</c:choose>
			
			<span style="float:right;">
			<c:if test="${not empty ordersPage.list}">
				<c:set var="pageParam"><dp:displayTagTableParam tableId="order" paramName="page" /></c:set>

					<span class="form-inline">
						Page
						<select	id="pageNo" name="pageNo" onchange="javascript:loadTablePage('${pageParam}',this.value,'${targetDiv}');" class="form-control input-sm">
							<c:forEach begin="1" end="${ordersPage.totalPages}" step="1" var="pNo" varStatus="status">
								<option value="${pNo}" ${pNo == ordersPage.pageNo? 'selected=""':''}>${pNo}</option>
							</c:forEach>
						</select> 
						of
						${ordersPage.totalPages}
					</span>

			</c:if>
			</span>
</div>
	</form>
</div>
								
<form name="orderList" id="orderList" action="/orders" method="post">
	<ajax:displayTag id="${targetDiv}-ajax">
		<display:table name="${ordersPage.list}" id="order" class="displayTable" requestURI="/orders" defaultsort="6" pagesize="${ordersPage.pageSize}" partialList="true" size="${ordersPage.totalResults}" sort="external" export="false">
			<display:setProperty name="paging.banner.onepage">
				<input type="hidden" name="pageURL" id="pageURL" value="{1}" />
				<div class="pageLinkBlk form-inline">
					<span style="float:right;">
						Page 
						<select id="pageNo" name="pageNo" onchange="javascript:loadTablePage(''${pageParam}'',this.value,''${targetDiv}'');" class="form-control selectbox">
							<c:forEach begin="1" end="${ordersPage.totalPages}" step="1" var="pNo" varStatus="status">
								<option value="${pNo}" ${pNo == ordersPage.pageNo ? 'selected=""':''}>${pNo}</option>
							</c:forEach>
						</select>
						of
						${ordersPage.totalPages}
					</span>
				</div>
			</display:setProperty>
			<display:setProperty name="paging.banner.first">
				<input type="hidden" name="pageURL" id="pageURL" value="{1}" class="form-control"/>
				<div class="pageLinkBlk form-inline">
					<span style="float: right;">
						Page 
						<select id="pageNo" name="pageNo" onchange="javascript:loadTablePage(''${pageParam}'',this.value,''${targetDiv}'');" class="form-control selectbox">
							<c:forEach begin="1" end="${ordersPage.totalPages}" step="1" var="pNo" varStatus="status">
								<option value="${pNo}" ${pNo == ordersPage.pageNo ? 'selected=""':''}>${pNo}</option>
							</c:forEach>
						</select>
						of
						${ordersPage.totalPages}
					</span>
				</div>
			</display:setProperty>
			
			<display:setProperty name="paging.banner.last">
				<input type="hidden" name="pageURL" id="pageURL" value="{1}" class="form-control"/>
				<div class="pageLinkBlk form-inline">
					<span style="float: right;">
						Page 
						<select	id="pageNo" name="pageNo" onchange="javascript:loadTablePage(''${pageParam}'',this.value,''${targetDiv}'');" class="form-control selectbox">
							<c:forEach begin="1" end="${ordersPage.totalPages}" step="1" var="pNo" varStatus="status">
								<option value="${pNo}" ${pNo == ordersPage.pageNo ? 'selected=""':''}>${pNo}</option>
							</c:forEach>
						</select>
						of
						${ordersPage.totalPages}
					</span>
				</div>
			</display:setProperty>
			
			<display:setProperty name="paging.banner.full">
				<input type="hidden" name="pageURL" id="pageURL" value="{1}" class="form-control"/>
				<div class="pageLinkBlk form-inline">
					<span style="float: right;">
						Page
						<select	id="pageNo" name="pageNo" onchange="javascript:loadTablePage(''${pageParam}'',this.value,''${targetDiv}'');" class="form-control selectbox">
							<c:forEach begin="1" end="${ordersPage.totalPages}" step="1" var="pNo" varStatus="status">
								<option value="${pNo}" ${pNo == ordersPage.pageNo? 'selected=""':''}>${pNo}</option>
							</c:forEach>
						</select>
						of
						${ordersPage.totalPages}
					</span>
				</div>
			</display:setProperty>
			<display:column title="Order Id" sortable="true" property="deliveryPartnerOrderId" escapeXml="true" sortName="o.deliveryPartnerOrderId"  />
			<display:column title="Kitchen" sortable="true" property="kitchen.name" escapeXml="true" sortName="o.kitchen.name" />
			<display:column title="Deliver Partner" sortable="true" property="deliveryPartner" escapeXml="true" sortName="o.deliveryPartner"/>
			<display:column title="Order Time" sortable="true" escapeXml="true" property="orderedTime" sortName="o.orderedTime" />
			<display:column title="Total" sortable="true" escapeXml="true" property="totalCost" sortName="o.totalCost" />
			<display:column title="DP Paid" sortable="false" escapeXml="true">0</display:column>
		</display:table>
	</ajax:displayTag>	
</form>

<script>
</script>