<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title>Spring 3 MVC Series - Contact Manager | viralpatel.net</title>
</head>
<body>
 
<h2>Contact Manager</h2>
 
<form:form method="post" action="edited.html" commandName="person">
 
    <table>
    <tr>
        <td><form:label path="name">navn</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
   
    <tr>
        <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
        <td><form:input path="email" /></td><td><form:errors path="email" /></td>
    </tr>
    <tr>
        <td><form:label path="telephone">telefon</form:label></td>
        <td><form:input path="telephone" /></td><td><form:errors path="telephone" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="edit"/>
        </td>
    </tr>
</table>
</form:form>
 

 
</body>
</html>