<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Spring 3 MVC Series - Contact Manager | viralpatel.net</title>
</head>
<body>
 
<h2>Contact Manager</h2>
 
<form:form method="post" action="add.html" commandName="festival">
 
    <table>
    <tr>
        <td><form:label path="festivalName">navn</form:label></td>
        <td><form:input path="festivalName" /></td>
        <td><form:errors path="festivalName" /></td>
    </tr>
   
  
    <tr>
        <td colspan="2">
            <input type="submit" value="add"/>
        </td>
    </tr>
</table>
</form:form>
 
<h3>Contacts</h3>
<c:if  test="${!empty festivals}">
<table class="data">
<tr>
    <th>Name</th>
</tr>
<c:forEach items="${festivals}" var="f">
    <tr>
        <td><a href="choose/${f.festivalName}">${f.festivalName}</a> </td>
        <td><a href="delete/${f.festivalId}">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
 
</body>
</html>