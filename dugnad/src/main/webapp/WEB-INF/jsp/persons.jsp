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

		
		<div class="span-16">
		
		<div  class="span-14 append-bottom">
		
		<button id="add" ><spring:message code="button.addPerson" /></button>
		</div>		
			<div id="form" hidden="true" class="span-14">
			
			<h3><spring:message code="button.addPerson" /></h3>
			<p><spring:message code="label.required.fields" /> <img src='<c:url value="/resources/style/css/img/required-field.png" />'/></p>
			<form:form id="addPerson" method="post" action="add.html" commandName="person">

				<table>
					<tr>
						<td><form:label path="firstName">
								<spring:message code="label.firstName" />
							</form:label></td>
						<td><form:input path="firstName" /><img src='<c:url value="/resources/style/css/img/required-field.png" />'/></td>
						<td><form:errors cssClass="errors" path="firstName" /></td>
					</tr>
					<tr>
						<td><form:label path="lastName">
								<spring:message code="label.lastName" />
							</form:label></td>
						<td><form:input path="lastName" /><img src='<c:url value="/resources/style/css/img/required-field.png" />'/></td>
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
						<td><form:input path="telephone" /><img src='<c:url value="/resources/style/css/img/required-field.png" />'/></td>
						<td><form:errors cssClass="errors" path="telephone" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="<spring:message code="label.add" />" /></td>
					</tr>
				</table>
			</form:form>
		
		</div>
		<div class="span-16">
			<c:if test="${!empty persons}">
			<h3><spring:message code="menu.persons" /></h3>
				<table class="data">
					<tr>
						<th><spring:message code="label.firstName" /></th>
						<th><spring:message code="label.lastName" /></th>
						<th><spring:message code="label.email" /></th>
						<th><spring:message code="label.telephone" /></th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${persons}" var="p">
						<tr>
							<td>${p.firstName}</td>
							<td>${p.lastName}</td>
							<td>${p.email}</td>
							<td>${p.telephone}</td>
							<td><a href="delete/${p.personId}"><spring:message code="label.delete" /></a></td>
							<td><a href="edit/${p.personId}"><spring:message code="label.edit" /></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty persons}">
			<p><spring:message code="label.no.persons"/></p>
			</c:if>
			</div>
		</div>
	</div>
<script type="text/javascript">	
$("#add").click(function () {
	$("#form").show("slow");
	$("#add").hide("slow");
  });
$(document).ready(function() {
	if(!$("span").text() == "")
	$("#form").hide("fast");
	if(!$("span").text() == "")
	$("#add").hide("fast");
	});
</script>
</body>
</html>