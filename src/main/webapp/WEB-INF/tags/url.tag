<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="url"%>
<a href="<c:url value="${url}"/>">
	<jsp:doBody/>
</a>
