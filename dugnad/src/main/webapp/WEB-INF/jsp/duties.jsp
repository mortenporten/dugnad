<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<script type="text/javascript"
	src="<c:url value="/resources/jquery/jquery-1.7.1.js" />"></script>
    <title>Spring 3 MVC Series - Contact Manager | viralpatel.net</title>
</head>
<body>
 
<h2>Contact Manager</h2>
 
<form:form method="post" action="add.html" commandName="duty">
 
    <table>
    <tr>
        <td><form:label path="start">start</form:label></td>
        <td><form:input path="start" /></td>
    </tr>
   
    <tr>
        <td><form:label path="end">end</form:label></td>
        <td><form:input path="end" /></td>
    </tr>
    <tr>
        <td><form:label path="hours">hours</form:label></td>
        <td><form:input path="hours" /></td>
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
    </tr>
</c:forEach>
</table>
</c:if>

</body>


</html>