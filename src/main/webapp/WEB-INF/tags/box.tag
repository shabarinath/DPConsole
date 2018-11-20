<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title"%>

<div class="wrap">
	<div class="title" ondblclick="toggleSidePane(function(){null})">
		<img id="sidepane-toggler" onclick="toggleSidePane(function(){null})"
			src="<c:url value="/resources/img/left.png"/>">${title}
	</div>
	<div class="body">		
		<jsp:doBody/>		
	</div>
</div>