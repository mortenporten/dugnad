<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h1>
	<img src='<c:url value="/resources/style/css/img/roots.jpg" />'
		height="150px" width="830px" alt="" />
</h1>

<ul class="topnav">
    <li><a href="/dugnad/index"><spring:message code="menu.home" /></a></li>
    <li>
        <a href="/dugnad/festival/festivals"><spring:message
				code="menu.festivals" /></a>
    </li>
    <li>
        <a href="/dugnad/person/persons"><spring:message
				code="menu.persons" /></a>
				<ul class="subnav">
            <li><a href="#">Sub Nav Link</a></li>
            <li><a href="#">Sub Nav Link</a></li>
        </ul>
    </li>
    <li>
        <a href="/dugnad/association/associations"><spring:message
				code="menu.associations" /></a>
    </li>
    <li>
        <a href="/dugnad/${festivalName}/duty/duties"><spring:message
				code="menu.duties" /></a>
    </li>
    <li>
        <a href="/dugnad/${festivalName}/ticket/tickets"><spring:message
				code="menu.tickets" /></a>
    </li>
    <li>
       	<a href="/dugnad/${festivalName}/day/overview"><spring:message
				code="menu.day.overview" /></a>
		
		<ul class="subnav">
            
            <li><a href="/dugnad/${festivalName}/overview/pickperson"><spring:message
				code="menu.personOverview" /></a></li>
            
            <li><a href="/dugnad/${festivalName}/available/duties"><spring:message
				code="menu.day.available.duties" /></a></li>
        </ul>
	</li>
    
    <li><a href="<c:url value="/j_spring_security_logout" />"><spring:message code="menu.logOut" /></a></li>
    <li>
</ul>

<script type="text/javascript">
$(document).ready(function(){

	$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled (Adds empty span tag after ul.subnav*)

	$("ul.topnav li span").click(function() { //When trigger is clicked...

		//Following events are applied to the subnav itself (moving subnav up and down)
		$(this).parent().find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click

		$(this).parent().hover(function() {
		}, function(){
			$(this).parent().find("ul.subnav").slideUp('slow'); //When the mouse hovers out of the subnav, move it back up
		});

		//Following events are applied to the trigger (Hover events for the trigger)
		}).hover(function() {
			$(this).addClass("subhover"); //On hover over, add class "subhover"
		}, function(){	//On Hover Out
			$(this).removeClass("subhover"); //On hover out, remove class "subhover"
	});

});
</script>
