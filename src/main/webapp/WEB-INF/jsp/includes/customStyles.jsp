<%@ page language="java" contentType="text/css; charset=Shift-JIS" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>

@homePageBox : ${homePage.customStyle.homePageBox};
@homePageBoxBorder : ${homePage.customStyle.homePageBoxBorder};
@titleColor : ${homePage.customStyle.titleColor};
@bodyLight : ${homePage.customStyle.bodyLight};
@bodyDark : ${homePage.customStyle.bodyDark};

.homePageBox { 
	border-color: @homePageBoxBorder;
}
   
.readMore, .homePageBox {
	 background: @homePageBox;
}
	
.title1 ,.title2{
	color: @titleColor;
    text-shadow: 1px 1px 3px #283A4D;
	}
	
body {
   background-image: linear-gradient(to top, @bodyLight, @bodyDark);
}

#navigation ul li a:hover,
#navigation ul li.active a { 
	background-image: linear-gradient(to top, @bodyDark, @bodyLight);
    background-repeat: repeat-x;
	color: #626262; /*Static color*/
}

#vertical-navigation ul li:hover,
#vertical-navigation ul li.active { 
	background-image: linear-gradient(to left, @bodyDark, @bodyLight);
    background-repeat: repeat-x;
	color: #626262; /*Static color*/
} 

#vertical-navigation ul li{
	background-image: linear-gradient(to right, @bodyDark, @bodyLight);
}

#navigation ul li a {
	background-image: linear-gradient(to bottom, @bodyDark, @bodyLight);
}