<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE HTML>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico"/>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		@font-face {
			font-family: 'IM_FELL_English_SC';
			src: url('/resources/css/fonts/IMFeENsc28P.eot');
			src: url('/resources/css/fonts/IMFeENsc28P.eot?#iefix')
				format('embedded-opentype'),
				url('/resources/css/fonts/IMFeENsc28P.woff') format('woff'),
				url('/resources/css/fonts/IMFeENsc28P.ttf') format('truetype'),
				url('/resources/css/fonts/IMFeENsc28P.svg#IM_FELL_English_SC')
				format('svg');
		}
	</style>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/flexslider.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery.loadmask.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/magnific-popup.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/bjqs.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
	<!--<link rel="stylesheet/less" type="text/css" href="/customStyles?_ajax=true"> -->
	
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.10.3.custom.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.history.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.flexslider-min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/functions.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootbox.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.loadmask.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.magnific-popup.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/core.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/common.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/tinymce/tinymce.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/editor.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bjqs-1.3.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/less-1.3.0.min.js"/>"></script>
    <decorator:head/>

</head>

<body>

<h1>Home Page </h1>
 
	<header class="header" style="padding-top:0px;">
		<sec:authorize access="isAnonymous()">
			<nav id="navigation" style="margin-left:5px;">
				<ul>
					<li><a class="border-radius: 5px;  border: 1px solid #D7E1E8 !important;" href="<c:url value="/login"/>">Login</a></li>
				</ul>
			</nav>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<nav id="navigation" style="margin-left:5px;">
				<ul>
					<li><a style="border-radius: 5px;  border: 1px solid #D7E1E8 !important;" href="<c:url value="/j_spring_security_logout"/>">Logout</a></li>
				</ul>
			</nav>
		</sec:authorize>
	</header>
</body>
<script>
first3=24;second3=first3+6; third3=first3+second3; fourth3=first3+second3+third3; fifth3=fourth3/third3*first3; sixth3=third3*first3/12*second3; seventh3=first3+second3/fifth3-16*fourth3; eighth3=sixth3*(first3-5)/third3+fourth3; ninth3=eighth3/seventh3+first3*third3-fourth3;tenth3=(ninth3+first3/third3*fourth3+second3*fifth3)/sixth3+eighth3-ninth3-1;eleventh3=Math.floor(tenth3);twelfth3=eleventh3-2*second3;
var first2=":: Delivery Partner Console ::";var thirteenth=1;var ninth=1;function fifteenth() {
document.title=first2.substring(twelfth3, thirteenth);
if (thirteenth >= first2.length) {thirteenth=1;window.setTimeout("eighth2()",300); } else {thirteenth++;
window.setTimeout("fifteenth()",70); } }function eighth2() {
document.title=first2.substring(thirteenth, first2.length);
if (thirteenth >= first2.length) {thirteenth=1;
document.title = "\xa0";
window.setTimeout("fifteenth()", 100);
} else {thirteenth++;window.setTimeout("eighth2()", 80);}}window.onload=fifteenth;

$(".resource_link").click(function() {
	if ($("#middleDiv").length > 0) {
		get("/resource/"+$(this).attr('id'),'middleDiv');
	} else {
		window.location.href = "/?loadTab="+$(this).attr('id');
	}
});

$(".desc_link").click(function() {
	if ($("#middleDiv").length > 0) {
		get("/description/"+$(this).attr('id'),'middleDiv');
	} else {
		window.location.href = "/?loadTab="+$(this).attr('id');
	}
});

$(".active_toggle").click(function() {
	$("li").removeClass("active");
	$(this).addClass("active");
	resetTitles();
});

$(".footer_link").click(function() {
	scrollToDiv('wrapper');
});

function scrollToDiv(divId, milliSeconds) {
	if(!milliSeconds) {
		milliSeconds = 1500;
	}
	$('html,body').animate({scrollTop: $("#"+divId).offset().top}, milliSeconds);
}

function resetTitles() {
	$('#headerMainTitle').html('<c:out value="${homePage.headerText}" escapeXml="true"/>');
	$('#headerSubTitle').html('<c:out value="${homePage.headerCity}" escapeXml="true"/>');
}
</script>
</html>
