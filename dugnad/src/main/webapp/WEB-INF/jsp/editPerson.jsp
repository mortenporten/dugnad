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


<title><spring:message code="header.editPerson" /></title>
</head>
<body>
	<div class="container">

		<%@ include file="/WEB-INF/jsp/header.jsp" %>

		
		<div class="span-16">
		
			
			
			
			<h3><spring:message code="header.editPerson" /></h3>
			<form:form id="addPerson" method="post" action="edited.html" commandName="person">

				<table>
					<tr>
						<td><form:label path="firstName">
								<spring:message code="label.firstName" />
							</form:label></td>
						<td><form:input path="firstName" /></td>
						<td><form:errors cssClass="errors" path="firstName" /></td>
					</tr>
					<tr>
						<td><form:label path="lastName">
								<spring:message code="label.lastName" />
							</form:label></td>
						<td><form:input path="lastName" /></td>
						<td><form:errors cssClass="errors" path="lastName" /></td>
					</tr>
					<tr>
						<td><form:label path="email">
								<spring:message code="label.email" />
							</form:label></td>
						<td><form:input path="email" /></td>
						<td><form:errors cssClass="errors" path="email" /></td>
					</tr>
					<tr>
						<td><form:label path="telephone">
								<spring:message code="label.telephone" />
							</form:label></td>
						<td><form:input path="telephone" /></td>
						<td><form:errors cssClass="errors" path="telephone" /></td>
					</tr>
					<tr><td><form:label path="association">
 						<spring:message code="label.association" /></form:label> </td>
						<td><form:select path="association.associationId">
						<form:option value="-1" label="" />
					  	<form:options items="${associationsMap}" />
				     	</form:select>
				     	<td><form:errors cssClass="errors" path="association.associationId" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="<spring:message code="label.edit" />" /></td>
					</tr>
				</table>
			</form:form>
		
		
		
		</div>
	</div>

</body>
</html>