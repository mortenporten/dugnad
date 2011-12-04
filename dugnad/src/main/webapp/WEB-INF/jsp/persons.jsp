<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring 3 MVC Series - Contact Manager | viralpatel.net</title>
</head>
<body>
 
<h2>Contact Manager</h2>
 
<form:form method="post" action="add.html" commandName="person">
 
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
        <td><form:input path="telephone" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="add"/>
        </td>
    </tr>
</table>
</form:form>
 
<h3>Contacts</h3>
<c:if  test="${!empty persons}">
<table class="data">
<tr>
    <th>Name</th>
    <th>Email</th>
    <th>Telephone</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${persons}" var="p">
    <tr>
        <td>${p.name} </td>
        <td>${p.email}</td>
        <td>${p.telephone}</td>
        <td><a href="delete/${p.personId}">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
 
</body>
</html>