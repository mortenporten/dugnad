<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>

 <link rel="stylesheet" media="all" href="<c:url value="/resources/jquery-ui/css/ui-lightness/jquery-ui-1.8.16.custom.css" />" type="text/css" />
 <script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.7/jquery-1.7.1.js" />"></script>
 <script type="text/javascript" src="<c:url value="/resources/jquery-ui/js/jquery-ui-1.8.16.custom.min.js" />"></script>
 <script type="text/javascript" src="<c:url value="/resources/jquery-ui/jquery-ui-timepicker-addon.js" />"></script>
 <script type="text/javascript">
	$(function() {
		$( '#start' ).datetimepicker({
			dateFormat: 'dd-mm-y'
		});
		$( '#end' ).datetimepicker({
			dateFormat: 'dd-mm-y'
		});
		
		$('#addDuty').submit(function() {	  
			$("#end").val( $.trim($("#end").val()) );
			$("#start").val( $.trim($("#start").val()) );  
			return true;
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
  
    <title>Spring 3 MVC Series - Contact Manager | viralpatel.net</title>
</head>
<body>
 
<h2>Contact Manager</h2>
 
<form:form method="post" id="addDuty" action="add.html" commandName="duty">
 
    <table>
    <tr>
        <td><form:label path="start"><spring:message code="label.start"/></form:label></td>
        <td><form:input id="start" path="start" /></td><td><form:errors path="start" /></td>
    </tr>
   
    <tr>
        <td><form:label path="end"><spring:message code="label.end"/></form:label></td>
        <td><form:input id="end" path="end" /></td><td><form:errors path="end" /></td>
    </tr>
    <tr>
        <td><form:label path="hours"><spring:message code="label.hours"/></form:label></td>
        <td><form:input path="hours" /></td><td><form:errors path="hours" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="add"/>
        </td>
    </tr>
</table>
</form:form>
 
<h3>Contacts</h3>
<c:if  test="${!empty duties}">
<table class="data">
<tr>
    <th>Start</th>
    <th>End</th>
    <th>hours</th>
</tr>
<c:forEach items="${duties}" var="d">
    <tr>
        <td><fmt:formatDate type="date" pattern="dd-MM-yy HH:mm" value="${d.start.time}"  /></td>
        <td><fmt:formatDate type="date" pattern="dd-MM-yy HH:mm" value="${d.end.time}"  /></td>
        <td>${d.hours}</td>
        <td><a href="delete/${d.dutyId}">delete</a></td>
        <td><a href="edit/${d.dutyId}">edit</a></td>
    </tr>
</c:forEach>
</table>
</c:if>


</body>


</html>