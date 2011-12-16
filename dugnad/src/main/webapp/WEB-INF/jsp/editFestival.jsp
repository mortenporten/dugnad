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

    <title>Spring 3 MVC Series - Contact Manager | viralpatel.net</title>
</head>
<body>
 
 <div class="container">

		<h1>
			<img src='<c:url value="/resources/style/css/img/roots.jpg" />'
				height="150px" width="830px" alt="" />
		</h1>

		<div class="topMenu">
			<div class="menu">
				<a href="/dugnad/index"><spring:message code="menu.home" /></a>
			</div>
			<div class="menu">
				<a href="/dugnad/person/persons"><spring:message code="menu.persons" /></a>
			</div>
			<div class="menu">
				<a href="logOut"><spring:message code="menu.logOut" /></a>
			</div>
		</div>


<div class="span-11">

<h3><spring:message code="header.editFestival" /></h3>
 
<form:form method="post" action="edited.html" commandName="festival">
 
    <table>
    <tr>
        <td><form:label path="festivalName"><spring:message code="label.name" /></form:label></td>
        <td><form:input path="festivalName" /></td>
        <td><form:errors cssClass="errors" path="festivalName" /></td>
    </tr>
   
  
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.edit" />"/>
        </td>
    </tr>
</table>
</form:form>

</div>

 </div>
</body>
</html>