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

<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-responsive.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/animate.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/docs.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/flexslider.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-ie7.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-ie7.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/headerfix.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/overwrite.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/prettyPhoto.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/refineslide-theme-dark.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/refineslide.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/refineslide-theme-dark.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/refineslide-theme-dark.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/color/default.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/js/google-code-prettify/prettify.css"/>">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600,700" rel="stylesheet">

<!--<link rel="stylesheet/less" type="text/css" href="/customStyles?_ajax=true"> -->

<script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/modernizr.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.easing.1.3.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/google-code-prettify/prettify.js"/>">
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.prettyPhoto.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/portfolio/jquery.quicksand.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/portfolio/setting.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/hover/jquery-hover-effect.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.flexslider.js"/>"></script>
<!-- <script type="text/javascript" src="<c:url value="/resources/js/classie.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/cbpAnimatedHeader.min.js"/>"></script>-->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.refineslide.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.ui.totop.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/custom.js"/>"></script>
<decorator:head />
<header>
    <!-- Navbar
    ================================================== -->
    <div class="cbp-af-header">
      <div class=" cbp-af-inner">
        <div class="container">
          <div class="row">

            <div class="span4">
              <!-- logo -->
              <div class="logo">
                <h1><a href="index.html">DP Console</a></h1>
                <!-- <img src="assets/img/logo.png" alt="" /> -->
              </div>
              <!-- end logo -->
            </div>

            <div class="span8">
              <!-- top menu -->
              <div class="navbar">
                <div class="navbar-inner">
				<sec:authorize access="isAuthenticated()">
                  <nav>
                    <ul class="nav topnav">
                      <li class="dropdown active">
                        <a href="index.html">Home</a>
                      </li>
                      <li class="dropdown">
                        <a href="#">Gmail Auto</a>
                        <ul class="dropdown-menu">
                          <li><a href="scaffolding.html">Zomato</a></li>
                          <!-- <li><a href="base-css.html">Base CSS</a></li>
                          <li><a href="components.html">Components</a></li>
                          <li><a href="icons.html">Icons</a></li>
                          <li><a href="list.html">Styled lists</a></li>
                          <li class="dropdown"><a href="#">3rd level</a>
                            <ul class="dropdown-menu sub-menu">
                              <li><a href="#">Example menu</a></li>
                              <li><a href="#">Example menu</a></li>
                              <li><a href="#">Example menu</a></li>
                            </ul>
                          </li> -->
                        </ul>
                      </li>
                      <li class="dropdown">
                        <a href="#">POS</a>
                        <ul class="dropdown-menu">
                          <li><a href="about.html">Uber Eats</a></li>
                          <!-- <li><a href="pricingtable.html">Pricing table</a></li>
                          <li><a href="fullwidth.html">Fullwidth</a></li>
                          <li><a href="404.html">404</a></li> -->
                        </ul>
                      </li>
                      <li>
                        <a href="#">Dashboard</a>
                        <!-- <ul class="dropdown-menu">
                          <li><a href="portfolio-2cols.html">Portfolio 2 columns</a></li>
                          <li><a href="portfolio-3cols.html">Portfolio 3 columns</a></li>
                          <li><a href="portfolio-4cols.html">Portfolio 4 columns</a></li>
                          <li><a href="portfolio-detail.html">Portfolio detail</a></li>
                        </ul> -->
                      </li>
                      <li>
                        <a href="#">Stats</a>
                        <!-- <ul class="dropdown-menu">
                          <li><a href="blog_left_sidebar.html">Blog left sidebar</a></li>
                          <li><a href="blog_right_sidebar.html">Blog right sidebar</a></li>
                          <li><a href="post_left_sidebar.html">Post left sidebar</a></li>
                          <li><a href="post_right_sidebar.html">Post right sidebar</a></li>
                        </ul> -->
                      </li>
					   <li>
                        <a href="#"/>Excel+</a>
                      </li>					  
                      <li>
                        <a href="<c:url value="/j_spring_security_logout"/>" >Logout</a>
                      </li>
                    </ul>
                  </nav>
				 </sec:authorize>
				 <sec:authorize access="isAnonymous()">
					 <nav>
						<ul class="nav topnav">
						  <li>							
							<a href="<c:url value="/login"/>">Login</a>
						  </li>
						</ul>
					 </nav>
				 </sec:authorize>
                </div>
              </div>
              <!-- end menu -->
            </div>

          </div>
        </div>
      </div>
    </div>
  </header>
</head>
<body>
	<decorator:body /> 								
	<!-- Footer
 ================================================== -->
  <footer class="footer">
    <div class="container">
      <div class="row">
        <div class="span3">
          <div class="widget">
            <!-- logo -->
            <div class="footerlogo">
              <h6><a href="index.html">Plato</a></h6>
              <!-- <img src="assets/img/logo.png" alt="" /> -->
            </div>
            <!-- end logo -->
            <address>
				<strong>Plato business company, Inc.</strong><br>
				 4455 Great building Ave, Suite A10<br>
				 San Francisco, CA 94107<br>
				<abbr title="Phone">P:</abbr> (123) 456-7890 </address>
          </div>
        </div>
        <div class="span3">
          <div class="widget">
            <h5>Browse pages</h5>
            <ul class="list list-ok">
              <li><a href="#">Lorem ipsum dolor sit amet</a></li>
              <li><a href="#">Tamquam ponderum at eum, nibh dicta offendit mei</a></li>
              <li><a href="#">Vix no vidisse dolores intellegam</a></li>
              <li><a href="#">Est virtute feugiat accommodare eu</a></li>
            </ul>
          </div>
        </div>      
        <div class="span3">
          <div class="widget">
            <h5>Keep updated</h5>
            <p>
              Enter your email to subcribe newsletter
            </p>
            <form>
              <div class="input-append">
                <input class="span2" id="appendedInputButton" type="text">
                <button class="btn btn-color" type="submit">Subscribe</button>
              </div>
            </form>
            <ul class="social-network">
              <li><a href="#"><i class="icon-bg-light icon-facebook icon-circled icon-1x"></i></a></li>
              <li><a href="#" title="Twitter"><i class="icon-bg-light icon-twitter icon-circled icon-1x"></i></a></li>
              <li><a href="#" title="Linkedin"><i class="icon-bg-light icon-linkedin icon-circled icon-1x"></i></a></li>
              <li><a href="#" title="Pinterest"><i class="icon-bg-light icon-pinterest icon-circled icon-1x"></i></a></li>
              <li><a href="#" title="Google plus"><i class="icon-bg-light icon-google-plus icon-circled icon-1x"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="subfooter">
      <div class="container">
        <div class="row">
          <div class="span6">
            <p>
              &copy; Biryani Vs Pulav - All right reserved
            </p>
          </div>        
        </div>
      </div>
    </div>
  </footer>
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
</script>
</html>