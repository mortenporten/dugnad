<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<!-- Framework CSS -->
<link rel="stylesheet"
	href='<c:url value="/resources/style/blueprint/screen.css" />'
	type="text/css" media="screen, projection" />
<link rel="stylesheet"
	href='<c:url value="/resources/style/blueprint/print.css" />'
	type="text/css" media="print" />
<!--[if IE]><link rel="stylesheet" href="<c:url value="/resources/style/blueprint/ie.css" />" type="text/css" media="screen, projection" /><![endif]-->
<link rel="stylesheet"
	href='<c:url value="/resources/style/css/style.css" />' type="text/css"
	media="screen, projection" />
	
	 <link rel="stylesheet" media="all" href="<c:url value="/resources/jquery-ui/css/ui-lightness/jquery-ui-1.8.16.custom.css" />" type="text/css" />
 <script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.7/jquery-1.7.1.js" />"></script>
 <script type="text/javascript" src="<c:url value="/resources/jquery-ui/js/jquery-ui-1.8.16.custom.min.js" />"></script>
	
	


    <title>Spring 3 MVC Series - Contact Manager | viralpatel.net</title>
</head>
<body>
 
 <div class="container">

		
		<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="span-11">

<h3><spring:message code="header.addFestival" /></h3>
 <p><spring:message code="label.required.fields" /> <img src='<c:url value="/resources/style/css/img/required-field.png" />'/></p>
<form:form method="post" action="add.html" commandName="festival">
 
    <table>
    <tr>
        <td><form:label path="festivalName"><spring:message code="label.name" /></form:label></td>
        <td><form:input path="festivalName" /><img src='<c:url value="/resources/style/css/img/required-field.png" />'/></td>
        <td><form:errors cssClass="errors" path="festivalName" /></td>
    </tr>
   
  
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.add" />"/>
        </td>
    </tr>
</table>
</form:form>

</div>
<div class="span-14">

<c:if  test="${!empty festivals}">
<h3><spring:message code="header.festivals" /></h3>

<table class="data">
<tr>
    <th><spring:message code="label.name" /></th>
</tr>
<c:forEach items="${festivals}" var="f">
    <tr>
        <td><a href="choose/${f.festivalName}">${f.festivalName}</a> </td>
        <td><a href="delete/${f.festivalId}" id="delete" ><spring:message code="label.delete" /></a></td>
        <td><a href="edit/${f.festivalId}"><spring:message code="label.edit" /></a></td>
    </tr>
</c:forEach>
</table>
</c:if>
<c:if  test="${empty festivals}">
 <p><spring:message code="label.no.festivals"/></p>
 
 </c:if>
 </div>
 </div>

<div id="question"  hidden="true">
<spring:message code="delete.festival.prompt" />
</div>

<script type="text/javascript">
$(document).ready(
		
		$('#delete').click(function(){
			  var answer = confirm($("#question").text());
			  return answer;
			})
);
</script>
</body>
</html>