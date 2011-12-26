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
  
    <title><spring:message code="header.edit.ticket"/></title>
</head>
<body>
<div class="container"> 
 
 	<%@ include file="/WEB-INF/jsp/header-all.jsp" %>


<div  class="span-21 append-bottom">

	
</div>

<div id="form"  class="span-21 append-bottom">
 
<h3><spring:message code="header.edit.ticket"/></h3>
 <p><spring:message code="label.required.fields" /> <img src='<c:url value="/resources/style/css/img/required-field.png" />'/></p>
<form:form method="post" id="addTicket" action="edited.html" commandName="ticket">
 
    <table>
    <tr>
        <td><form:label path="ticketType"><spring:message code="label.ticket.type"/></form:label></td>
        <td><form:input path="ticketType" /><img src='<c:url value="/resources/style/css/img/required-field.png" />'/>
        </td><td><form:errors cssClass="errors" path="ticketType" /></td>
    </tr>
    <tr>
        <td><form:label path="date"><spring:message code="label.ticket.date"/></form:label></td>
        <td><form:input id="date" path="date" /></td>
        <td><form:errors cssClass="errors" path="date" /></td>
    </tr>
   
</table>
    <br>
     <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.edit"/>"/>
        </td>
    </tr>
</form:form>
</div>


</div>




</body>


</html>