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

		
		<h3><spring:message code="header.choose.day"/></h3>
		
		<div class="span-16">
		<c:if  test="${!empty daysDate}">
				<table>
					<tr>
					<c:forEach items="${daysDate}" var="dates">
					<td><a href="overview/${dates}" class="large">${dates}</a></td>
					</c:forEach>	
					</tr>
				</table>
		</c:if>
		<c:if  test="${empty daysDate}">
		<p><spring:message code="label.no.duties.for.festival"/></p>
		</c:if>
		</div>
	</div>
</body>
</html>