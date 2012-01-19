<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h1>
	<img src='<c:url value="/resources/style/css/img/roots.jpg" />'
		height="150px" width="830px" alt="" />
</h1>

<div class="topMenu">

	<div class="menu">
		<a href="/dugnad/index"><spring:message code="menu.home" /></a>
	</div>
	<div class="menu">
		<a href="/dugnad/festival/festivals"><spring:message
				code="menu.festivals" /></a>
	</div>
	<div class="menu">
		<a href="/dugnad/person/persons"><spring:message
				code="menu.persons" /></a>
	</div>
	<div class="menu">
		<a href="/dugnad/association/associations"><spring:message
				code="menu.associations" /></a>
	</div>
	<div class="menu">
		<a href="/dugnad/${festivalName}/duty/duties"><spring:message
				code="menu.duties" /></a>
	</div>
	<div class="menu">
		<a href="/dugnad/${festivalName}/ticket/tickets"><spring:message
				code="menu.tickets" /></a>
	</div>
	<div class="menu">
		<a href="/dugnad/${festivalName}/overview/pickperson"><spring:message
				code="menu.personOverview" /></a>
	</div>
	<div class="menu">
		<a href="/dugnad/${festivalName}/day/overview"><spring:message
				code="menu.day.overview" /></a>
	</div>
	<div class="menu">
		<a href="/dugnad/${festivalName}/available/duties"><spring:message
				code="menu.day.available.duties" /></a>
	</div>
	<div class="menu">
		<a href="<c:url value="/j_spring_security_logout" />"><spring:message code="menu.logOut" /></a>
	</div>
	<div class="festivalchoosen">
		${festivalName}
	</div>
</div>