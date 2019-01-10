<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp"%>
<section id="intro">
    <div class="container">
		<h2>	
			<span class="highlight primary text-info btn-rounded">ZOMATO File Upload</span>
		</h2>	
		<div class="control-group" style="margin-left:30%;">							
			<form:form method="post" action="/processFile/${type}" enctype="multipart/form-data">  		
			<p><input name="file" id="fileToUpload" type="file" /></p>  
			<div><input type="submit" value="Upload"></div>  
			</form:form>  
			<h4 style="color:green;">${fileSuccess}</h4>  
			<h4 style="color:red;">${fileProcessingFailed}</h4>  
		</div>
	</div>
</section>