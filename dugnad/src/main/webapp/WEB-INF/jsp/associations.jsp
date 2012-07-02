<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
 <script type="text/javascript" src="<c:url value="/resources/jquery-ui/jquery-ui-timepicker-addon.js" />"></script>
 <script type="text/javascript">
	$(function() {
		$( '#date' ).datepicker({
			dateFormat: 'dd-mm-y'
		});
		
		
		
	});
</script>

  
  <style type="text/css"> 
			
			/* css for timepicker */
			.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
			.ui-timepicker-div dl { text-align: left; }
			.ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
			.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
			.ui-timepicker-div td { font-size: 90%; }
			.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
			
		</style>
  
    <title><spring:message code="header.association"/></title>
</head>
<body>
<div class="container"> 
 
 	<%@ include file="/WEB-INF/jsp/header.jsp" %>


<div  class="span-21 append-bottom">
	<h2><spring:message code="header.association"/></h2>
	<button id="add" ><spring:message code="button.add.association" /></button>
</div>

<div id="form" hidden="true" class="span-21 append-bottom">
 
<h3><spring:message code="header.add.association"/></h3>
 <p><spring:message code="label.required.fields" /> <img src='<c:url value="/resources/style/css/img/required-field.png" />'/></p>
<form:form method="post" id="addTicket" action="add.html" commandName="association">
 
    <table>
    <tr>
        <td><form:label path="associationName"><spring:message code="label.association.name"/></form:label></td>
        <td><form:input path="associationName" /><img src='<c:url value="/resources/style/css/img/required-field.png" />'/>
        </td><td><form:errors cssClass="errors" path="associationName" /></td>
    </tr>
   
</table>
    <br>
     <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.add"/>"/>
        </td>
    </tr>
</form:form>
</div>


<div class="span-21 last append-bottom">
	
<c:if  test="${!empty associations}">
<table class="data">
<tr>
    <th><spring:message code="label.association.name"/></th>
</tr>
<c:forEach items="${associations}" var="a">
    <tr>
        <td>${a.associationName}</td>
        <td><a href="delete/${a.associationId}"><spring:message code="label.delete"/></a></td>
        <td><a href="edit/${a.associationId}"><spring:message code="label.edit"/></a></td>
    </tr>
</c:forEach>
</table>
</c:if>
<c:if  test="${empty associations}">
<p><spring:message code="message.no.associations"/></p>
</c:if>
</div>

</div>


<script type="text/javascript">	
$("#add").click(function () {
	$("#form").show("slow");
	$("#add").hide("slow");
  });
$(document).ready(function() {
	if($("span").hasClass("errors"))
	$("#form").show("fast");
	if($("span").hasClass("errors"))
	$("#add").hide("fast");
	});
</script>

</body>


</html>