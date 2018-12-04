<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<c:set var="renderDiv" value="listingDiv"/>
<style>
.infoStatement {
    padding: 10px 0px !important;
    background: #fff !important;
}
.mainTableOuterDiv{
	border:0px;
}
.listing_search{
	text-indent: 5px;
    padding: 8px 14px;
    width: 100%;
    border: 1px solid #dcdfe3;
    border-radius: .25rem;
    margin: auto;
    transition: border-color ease-in-out .15s;
    color: #575757;
    line-height: 16px;
    box-shadow: none;
}
.header{
	background-color: #ebebeb;
    height: 41px;
    width: 100%;
}
.title-label{
	font-size: 120%;
    float: left;
    padding: 0px 5px;
    line-height: 41px;
}
.mainTableHead_right{
	float:right;
}
.mainTableHead_right a{
	padding: 8px 5px;
    display: inline-block;
}
.searchButton{
	padding: 8px 10px !important;
    background: none !important;
    font-size: 14px;
    cursor: pointer;
}
</style>

<div class="mainTableOuterDiv">
	<form name="orderList" id="orderList" action="/orders" method="post" style="clear:both;">
		<ajax:displayTag id="${renderDiv}-ajax">
			<c:set var="targetDiv" value="${renderDiv}" scope="request"/>
			<c:set var="pageParam" scope="request"><dp:displayTagTableParam tableId="order" paramName="page"/></c:set>
			<display:table name="${ordersPage.list}" id="order" class="display-table table table-hover table-bordered table-striped" requestURI="/orders" defaultsort="2" pagesize="${ordersPage.pageSize}" partialList="true" size="${ordersPage.totalResults}" sort="external" export="false">
				<display:setProperty name="basic.empty.showtable">true</display:setProperty>
				<display:setProperty name="paging.banner.placement">bottom</display:setProperty>
				<display:setProperty name="basic.msg.empty_list">
					<input type="hidden" name="pageURL" id="pageURL" value="/orders" />
					<%@ include file="/WEB-INF/jsp/includes/noItemsFound.jsp" %>
				</display:setProperty>

				<display:setProperty name="paging.banner.placement">bottom</display:setProperty>
				<display:setProperty name="paging.banner.onepage">
							<c:import url="/WEB-INF/jsp/includes/tablePagination.jsp?listPageNo=${ordersPage.pageNo}&listPageSize=${ordersPage.pageSize}&listTotalPages=${ordersPage.totalPages}&pageStatus=one" />
				</display:setProperty>
				<display:setProperty name="paging.banner.first">
							<c:import url="/WEB-INF/jsp/includes/tablePagination.jsp?listPageNo=${ordersPage.pageNo}&listPageSize=${ordersPage.pageSize}&listTotalPages=${ordersPage.totalPages}&pageStatus=first" />
				</display:setProperty>
				<display:setProperty name="paging.banner.last">
							<c:import url="/WEB-INF/jsp/includes/tablePagination.jsp?listPageNo=${ordersPage.pageNo}&listPageSize=${ordersPage.pageSize}&listTotalPages=${ordersPage.totalPages}&pageStatus=last" />
				</display:setProperty>
				<display:setProperty name="paging.banner.full">
							<c:import url="/WEB-INF/jsp/includes/tablePagination.jsp?listPageNo=${ordersPage.pageNo}&listPageSize=${ordersPage.pageSize}&listTotalPages=${ordersPage.totalPages}&pageStatus=full" />
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
	<c:if test="${ordersPage.totalResults > 0}">
		<span class="floatLeft">${ordersPage.totalResults} orders found, displaying ${((ordersPage.pageNo-1) * ordersPage.pageSize) + 1} To ${((ordersPage.pageNo-1) *ordersPage.pageSize) + fn:length(ordersPage.list)}.</span>
	</c:if>
</div>