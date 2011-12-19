<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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


<title><spring:message code="title.day.overview" /></title>
</head>
<body>
	<div class="container">

	<%@ include file="/WEB-INF/jsp/header-all.jsp" %>

		<h2><spring:message code="title.day.overview"/></h2>
		<h3><spring:message code="header.choose.day"/></h3>
		
		<div class="span-20">
		
		
				<ul>		
					<c:forEach items="${daysDate}" var="dates">
					<td><a href="overview/${dates}" class="large">${dates}  </a></td>
					</c:forEach>	
				</ul>
		</div>
	
<div class="span-21 last append-bottom ">
<h3><spring:message code="header.day.overview.for"/>${date}:</h3>
	
<c:if  test="${!empty duties}">
<c:forEach items="${duties}" var="d">
<div class="span-21 box border">
<h2>${d.name}</h2>
<table class="data">
<tr>
    <th><spring:message code="label.place"/></th>
    <th><spring:message code="label.date"/></th>
    <th><spring:message code="label.clock"/></th>
    <th><spring:message code="label.hours"/></th>
    <th><spring:message code="label.required"/></th>
    <th><spring:message code="label.responsible"/></th>
    <th><spring:message code="label.description"/></th>
</tr>

    <tr>
        <td>${d.place}</td>
         <td>${date}</td>
        <td><fmt:formatDate type="date" pattern="HH:mm" value="${d.start.time}"  /> -
        <fmt:formatDate type="date" pattern="HH:mm" value="${d.end.time}"  /></td>
        <td>${d.hours}</td>
        <td>${d.required}</td>
        <td>${d.responsible.firstName} ${d.responsible.lastName} <spring:message code="short.telephone"/> ${d.responsible.telephone}</td>
        <td>${d.description}</td>
    </tr>
</table>
<c:if  test="${!empty d.persons}">
	<h3><spring:message code="header.persons.assigned.to" /> ${d.name}: </h3>
				<table class="data">
					<tr>
						<th><spring:message code="label.firstName" /></th>
						<th><spring:message code="label.lastName" /></th>
						<th><spring:message code="label.email" /></th>
						<th><spring:message code="label.telephone" /></th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${d.persons}" var="p">
						<tr>
							<td>${p.firstName}</td>
							<td>${p.lastName}</td>
							<td>${p.email}</td>
							<td>${p.telephone}</td>
						</tr>
					</c:forEach>
				</table>
</c:if>
<c:if  test="${empty d.persons}">
<spring:message code="label.no.persons.assigned"/>
</c:if>
<c:if  test="${empty duties}">
<p><spring:message code="label.festival.no.duties"/></p>
</c:if>
</div>
</c:forEach>
</c:if>

</div>
	
	
	
	
	
	
	
	</div>
</body>
</html>