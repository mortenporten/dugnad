<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


<title><spring:message code="menu.persons" /></title>
</head>
<body>
	<div class="container">

	<%@ include file="/WEB-INF/jsp/header.jsp" %>

		<div class="span-16" >
	
		<h2><spring:message code="menu.persons" /></h2>
		
		</div>
		 <div class="span-16">
			<c:if test="${!empty persons}">
			<form:form id="addPerson" method="post" action="add.html" commandName="list" name="addPerson">
		<table class="data">
					<tr>
						<th><spring:message code="label.firstName" /></th>
						<th><spring:message code="label.lastName" /></th>
						<th><spring:message code="label.email" /></th>
						<th><spring:message code="label.telephone" /></th>
						<th><spring:message code="label.association" /></th>
						<th>Send mail</th>
					</tr>
					<c:forEach items="${persons}" var="p">
						<tr>
							<td>${p.firstName}</td>
							<td>${p.lastName}</td>
							<td>${p.email}</td>
							<td>${p.telephone}</td>
							<td>${p.association.associationName}</td>
							<td><form:checkbox path="mailingList" value="${p.personId}" /></td>
						</tr>
					</c:forEach>
					 <tr>
						<td colspan="2"><input type="submit" value="<spring:message code="label.add" />" /></td>
		  		  </tr>
		</table>
		<input type="button" name="CheckAll" value="Check All"
		onClick="checkAll(document.addPerson.mailingList)">
		<input type="button" name="UnCheckAll" value="Uncheck All"
		onClick="uncheckAll(document.addPerson.mailingList)">
</form:form>
			</c:if>
			<c:if test="${empty persons}">
			<p><spring:message code="label.no.persons"/></p>
			</c:if>
			</div>
		</div> 

<script>

function checkAll(field)
{
for (i = 0; i < field.length; i++)
	field[i].checked = true ;
}

function uncheckAll(field)
{
for (i = 0; i < field.length; i++)
	field[i].checked = false ;
}
//  End -->
</script>


</body>
</html>