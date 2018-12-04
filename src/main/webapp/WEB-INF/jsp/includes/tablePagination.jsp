<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<c:set var="listPageSize" value="${param.listPageSize > 0 ? param.listPageSize : 20}" scope="request"/>
<c:choose>
	<c:when test="${!empty pageURLHiddenElId}">
		<input type="hidden" name="${pageURLHiddenElId}" id="${pageURLHiddenElId}" value="{1}" disabled="true"/>
	</c:when>
	<c:otherwise>
		<input type="hidden" name="pageURL" id="pageURL" value="{1}" disabled="true"/>
	</c:otherwise>
</c:choose>
<c:if test="${param.listTotalPages > 0}">
	<div class="tableActions" style="text-align:right;">
		<ul class="pagination pagination-sm">
			<c:choose>
				<c:when test="${param.pageStatus == 'one'}">
					<li class="disabled"><a href="javascript://nope/"><i class="fas fa-step-backward"></i></a></li>
	    			<li class="disabled"><a href="javascript://nope/"><i class="fas fa-chevron-left"></i></a></li>
					<li class="active"><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:1,pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')">1</a></li>
					<li class="disabled"><a href="javascript://nope/"><i class="fas fa-chevron-right"></i></a></li>
	    			<li class="disabled"><a href="javascript://nope/"><i class="fas fa-step-forward"></i></a></li>
				</c:when>
				<c:when test="${param.pageStatus=='first'}">
					<li class="disabled"><a href="javascript://nope/"><i class="fas fa-step-backward"></i></a></li>
	    			<li class="disabled"><a href="javascript://nope/"><i class="fas fa-chevron-left"></i></a></li>
					<li class="active"><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:1,pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')">1</a></li>
					<c:forEach begin="2" end="${(param.listTotalPages > 5) ? 5 : param.listTotalPages}" step="1" var="pageNo">
						<li onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${pageNo},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><a href="javascript://nope/">${pageNo}</a></li>
					</c:forEach>
					<li><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${param.listPageNo+1},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><i class="fas fa-chevron-right"></i></a></li>
	    			<li><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${param.listTotalPages},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><i class="fas fa-step-forward"></i></a></li>
				</c:when>
				<c:when test="${param.pageStatus=='last'}">
					<li><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:1,pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><i class="fas fa-step-backward"></i></a></li>
	    			<li><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${param.listPageNo-1},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><i class="fas fa-chevron-left"></i></a></li>
					<c:forEach begin="${((param.listTotalPages-5)>1)?(param.listTotalPages-5):1}" end="${param.listTotalPages}" step="1" var="pageNo">
						<li class="${(pageNo == param.listPageNo) ? 'active':''}" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${pageNo},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><a href="javascript://nope/">${pageNo}</a></li>
					</c:forEach>
					<li class="disabled"><a href="javascript://nope/"><i class="fas fa-chevron-right"></i></a></li>
	    			<li class="disabled"><a href="javascript://nope/"><i class="fas fa-step-forward"></i></a></li>
				</c:when>
				<c:when test="${param.pageStatus=='full'}">
					<li><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:1,pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><i class="fas fa-step-backward"></i></a></li>
	    			<li><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${param.listPageNo-1},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><i class="fas fa-chevron-left"></i></a></li>
					
					<c:forEach begin="${((param.listPageNo-2) > 1) ? (param.listPageNo-2) : 1}" end="${((param.listPageNo+2) > param.listTotalPages) ? param.listTotalPages : (param.listPageNo+2)}" step="1" var="pageNo">
						<li class="${(pageNo == param.listPageNo) ? 'active':''}" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${pageNo},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><a href="javascript://nope/">${pageNo}</a></li>
					</c:forEach>
					<li><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${param.listPageNo+1},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><i class="fas fa-chevron-right"></i></a></li>
	    			<li><a href="javascript://nope/" onclick="loadPaginatedList('{pageParam:''${pageParam}'',pNo:${param.listTotalPages},pSize:${listPageSize},pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')"><i class="fas fa-step-forward"></i></a></li>
				</c:when>
			</c:choose>
	    	<li>
	    		<span class="selectRecordsPerPage form-inline" style="padding:0;margin:0 10px;border:0;">
			  		<select name="pageSize" id="pageSize" onchange="loadPaginatedList('{pageParam:''${pageParam}'',pNo:1,pSize:this.value,pageUrlHiddenId:''${pageURLHiddenElId}'',renderTo:''${targetDiv}'',_isFilterPage:${_isFilterPage ? true : false}}')" class="form-control input-xs">
			        	<option value="10" ${(listPageSize == 10) ? 'selected="selected"':''}>10</option>
			        	<option value="20" ${(listPageSize == 20) ? 'selected="selected"':''}>20</option>
			        	<option value="40" ${(listPageSize == 40) ? 'selected="selected"':''}>40</option>
			        	<option value="50" ${(listPageSize == 50) ? 'selected="selected"':''}>50</option>
			        	<option value="100" ${(listPageSize == 100) ? 'selected="selected"':''}>100</option>
			      	</select><span class="recordsPerPage">/ Page:</span>
			    </span>
	    	</li>    		    		
	  	</ul>
	</div>
</c:if>