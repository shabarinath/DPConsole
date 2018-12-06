<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<c:set var="renderDiv" value="reviewOrdersDiv"/>
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
.pagination {
  display: -ms-flexbox;
  display: flex;
  padding-left: 0;
  list-style: none;
  border-radius: 0.25rem;
}

.page-link {
  position: relative;
  display: block;
  padding: 0.5rem 0.75rem;
  margin-left: -1px;
  line-height: 1.25;
  color: #007bff;
  background-color: #fff;
  border: 1px solid #dee2e6;
}

.page-link:hover {
  z-index: 2;
  color: #0056b3;
  text-decoration: none;
  background-color: #e9ecef;
  border-color: #dee2e6;
}

.page-link:focus {
  z-index: 2;
  outline: 0;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.page-link:not(:disabled):not(.disabled) {
  cursor: pointer;
}

.page-item:first-child .page-link {
  margin-left: 0;
  border-top-left-radius: 0.25rem;
  border-bottom-left-radius: 0.25rem;
}

.page-item:last-child .page-link {
  border-top-right-radius: 0.25rem;
  border-bottom-right-radius: 0.25rem;
}

.page-item.active .page-link {
  z-index: 1;
  color: #fff;
  background-color: #007bff;
  border-color: #007bff;
}

.page-item.disabled .page-link {
  color: #6c757d;
  pointer-events: none;
  cursor: auto;
  background-color: #fff;
  border-color: #dee2e6;
}

.pagination-lg .page-link {
  padding: 0.75rem 1.5rem;
  font-size: 1.25rem;
  line-height: 1.5;
}

.pagination-lg .page-item:first-child .page-link {
  border-top-left-radius: 0.3rem;
  border-bottom-left-radius: 0.3rem;
}

.pagination-lg .page-item:last-child .page-link {
  border-top-right-radius: 0.3rem;
  border-bottom-right-radius: 0.3rem;
}

.pagination-sm .page-link {
  padding: 0.25rem 0.5rem;
  font-size: 0.875rem;
  line-height: 1.5;
}

.pagination-sm .page-item:first-child .page-link {
  border-top-left-radius: 0.2rem;
  border-bottom-left-radius: 0.2rem;
}

.pagination-sm .page-item:last-child .page-link {
  border-top-right-radius: 0.2rem;
  border-bottom-right-radius: 0.2rem;
}

li{
	line-height:20px;
	margin-left:3px;
}
.modal.fade {

    top: 25% !important;
    -webkit-transition: opacity .3s linear,top .3s ease-out;
    -moz-transition: opacity .3s linear,top .3s ease-out;
    -o-transition: opacity .3s linear,top .3s ease-out;
    transition: opacity .3s linear,top .3s ease-out;

}
</style>
<div id="reviewOrdersDiv" >
	<section id="intro" class="container">
			<h2>
				<span class="highlight primary text-info btn-rounded">Manual Review Required</span>
			</h2>
			<div class="mainTableOuterDiv container">
			<form name="orderList" id="orderList" action="/reviewOrders?ajax" method="post" style="clear:both;">
				<ajax:displayTag id="${renderDiv}-ajax">
					<c:set var="targetDiv" value="${renderDiv}" scope="request"/>
					<c:set var="pageParam" scope="request"><dp:displayTagTableParam tableId="order" paramName="page"/></c:set>
					<display:table name="${ordersPage.list}" id="order" class="display-table table table-hover table-bordered table-striped" requestURI="/reviewOrders?ajax" defaultsort="2" pagesize="${ordersPage.pageSize}" partialList="true" size="${ordersPage.totalResults}" sort="external" export="false">
						<display:setProperty name="basic.empty.showtable">true</display:setProperty>
						<display:setProperty name="paging.banner.placement">bottom</display:setProperty>
						<display:setProperty name="basic.msg.empty_list">
							<input type="hidden" name="pageURL" id="pageURL" value="/reviewOrders?ajax" />
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
						<display:column title="Order Id" sortable="true" escapeXml="false" property="deliveryPartnerOrderId" sortName="o.deliveryPartnerOrderId">					
						</display:column>
						<display:column title="Kitchen" sortable="true" property="kitchen.name" escapeXml="true" sortName="o.kitchen.name" />
						<display:column title="Reason" sortable="true" property="manualReviewComments" escapeXml="true" sortName="o.manualReviewComments"/>
						<display:column title="Order Time" sortable="true" escapeXml="true" property="orderedTime" sortName="o.orderedTime" />
					</display:table>
				</ajax:displayTag>
			</form>
		</div>
	</section>
</div>