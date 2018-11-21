<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/resources/img/favicon.ico"/>">
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
<!--<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"> -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/flexslider.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery.loadmask.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/css/magnific-popup.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/bjqs.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/layout.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<!--<link rel="stylesheet/less" type="text/css" href="/customStyles?_ajax=true"> -->

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-1.6.js"/>"></script>
<!--<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>-->
<script type="text/javascript"
	src="<c:url value="/resources/js/cufon-yui.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/cufon-replace.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/Forum_400.font.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.easing.1.3.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/tms-0.3.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/tms_presets.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/script.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/atooltip.jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/html5.js"/>"></script>
<!--<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.10.3.custom.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.history.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.flexslider-min.js"/>"></script>-->
<script type="text/javascript"
	src="<c:url value="/resources/js/functions.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.form.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootbox.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.loadmask.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.magnific-popup.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/core.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/tinymce/tinymce.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/editor.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bjqs-1.3.js"/>"></script>
<decorator:head />

</head>
<body id="page1">
<div class="body6">
  <div class="body1">
    <div class="body5">
      <div class="main">
        <!-- header -->
        <header>
          <h1><a href="index.html" id="logo">Deliccio Classic European Cuisine</a></h1>
          <nav>
            <ul id="top_nav">
              <li><a href="index.html"><img src="/resources/img/icon_1.gif" alt=""></a></li>
              <li><a href="#"><img src="/resources/img/icon_2.gif" alt=""></a></li>
              <li class="end"><a href="contacts.html"><img src="/resources/img/icon_3.gif" alt=""></a></li>
            </ul>
          </nav>
          <nav>
            <ul id="menu">
              <li class="active"><a href="index.html">Restaurant</a></li>
              <li><a href="cuisine.html">Cuisine</a></li>
              <li><a href="wine.html">Wine List</a></li>
              <li><a href="cook-book.html">CookBook</a></li>
              <sec:authorize access="isAnonymous()">
					<li><a
						class="border-radius: 5px;  border: 1px solid #D7E1E8 !important;"
						href="<c:url value="/login"/>">Login</a>
					</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li>
						<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
					</li>
				</sec:authorize>
            </ul>
          </nav>
        </header>
		

        <!-- / header -->       
		<div class="body2">
		  <div class="main">
			<article id="content2">
			  <div class="wrapper">
			  <decorator:body /> 								
			  </div>
			</article>
			<!-- / content -->
		  </div>
		</div>
		<div class="body3">
		  <div class="body4">
			<div class="main">
			  <!-- footer -->
			  <footer>
				<div class="wrapper">
				  <section class="col1 pad_left1">
					<h3>Toll Free: <span>1-800 123 45 67</span></h3>
					Copyright &copy; <a href="#">Domain Name</a> All Rights Reserved<br>
					Design by <a target="_blank" href="http://www.templatemonster.com/">TemplateMonster.com</a></section>
				  <section class="col2 pad_left1">
					<h3>Follow Us </h3>
					<ul id="icons">
					  <li><a href="#" class="normaltip"><img src="/resources/img/icon1.gif" alt=""></a></li>
					  <li><a href="#" class="normaltip"><img src="/resources/img/icon2.gif" alt=""></a></li>
					  <li><a href="#" class="normaltip"><img src="/resources/img/icon3.gif" alt=""></a></li>
					  <li><a href="#" class="normaltip"><img src="/resources/img/icon4.gif" alt=""></a></li>
					  <li><a href="#" class="normaltip"><img src="/resources/img/icon5.gif" alt=""></a></li>
					</ul>
				  </section>
				</div>
				<!-- {%FOOTER_LINK} -->
			  </footer>
			  <!-- / footer -->
			</div>
		  </div>
		</div>		
</body>
<script>
	first3 = 24;
	second3 = first3 + 6;
	third3 = first3 + second3;
	fourth3 = first3 + second3 + third3;
	fifth3 = fourth3 / third3 * first3;
	sixth3 = third3 * first3 / 12 * second3;
	seventh3 = first3 + second3 / fifth3 - 16 * fourth3;
	eighth3 = sixth3 * (first3 - 5) / third3 + fourth3;
	ninth3 = eighth3 / seventh3 + first3 * third3 - fourth3;
	tenth3 = (ninth3 + first3 / third3 * fourth3 + second3 * fifth3) / sixth3
			+ eighth3 - ninth3 - 1;
	eleventh3 = Math.floor(tenth3);
	twelfth3 = eleventh3 - 2 * second3;
	var first2 = ":: Delivery Partner Console ::";
	var thirteenth = 1;
	var ninth = 1;
	function fifteenth() {
		document.title = first2.substring(twelfth3, thirteenth);
		if (thirteenth >= first2.length) {
			thirteenth = 1;
			window.setTimeout("eighth2()", 300);
		} else {
			thirteenth++;
			window.setTimeout("fifteenth()", 70);
		}
	}
	function eighth2() {
		document.title = first2.substring(thirteenth, first2.length);
		if (thirteenth >= first2.length) {
			thirteenth = 1;
			document.title = "\xa0";
			window.setTimeout("fifteenth()", 100);
		} else {
			thirteenth++;
			window.setTimeout("eighth2()", 80);
		}
	}
	window.onload = fifteenth;

	$(".resource_link").click(function() {
		if ($("#middleDiv").length > 0) {
			get("/resource/" + $(this).attr('id'), 'middleDiv');
		} else {
			window.location.href = "/?loadTab=" + $(this).attr('id');
		}
	});

	$(".desc_link").click(function() {
		if ($("#middleDiv").length > 0) {
			get("/description/" + $(this).attr('id'), 'middleDiv');
		} else {
			window.location.href = "/?loadTab=" + $(this).attr('id');
		}
	});	

	$(".footer_link").click(function() {
		scrollToDiv('wrapper');
	});

	function scrollToDiv(divId, milliSeconds) {
		if (!milliSeconds) {
			milliSeconds = 1500;
		}
		$('html,body').animate({
			scrollTop : $("#" + divId).offset().top
		}, milliSeconds);
	}
</script>
</html>