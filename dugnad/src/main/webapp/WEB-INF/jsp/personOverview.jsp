<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
 
 <link rel="stylesheet" media="all" href="<c:url value="/resources/jquery-ui/css/ui-lightness/jquery-ui-1.8.16.custom.css" />" type="text/css" />
 <script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.7/jquery-1.7.1.js" />"></script>
 <script type="text/javascript" src="<c:url value="/resources/jquery-ui/js/jquery-ui-1.8.16.custom.min.js" />"></script>
 
    <title><spring:message code="header.overview" /></title>
</head>
<body>
<div class="container">

<%@ include file="/WEB-INF/jsp/header-all.jsp" %>
 
<div class="span-16 append-bottom"> 
<h3><spring:message code="header.overview" /></h3>
 

	<style>
	.ui-button { margin-left: -1px; }
	.ui-button-icon-only .ui-button-text { padding: 0.35em; } 
	.ui-autocomplete-input { margin: 0; padding: 0.48em 0 0.47em 0.45em; }
	</style>
	<script>
	(function( $ ) {
		$.widget( "ui.combobox", {
			_create: function() {
				var self = this,
					select = this.element.hide(),
					selected = select.children( ":selected" ),
					value = selected.val() ? selected.text() : "";
				var input = this.input = $( "<input>" )
					.insertAfter( select )
					.val( value )
					.autocomplete({
						delay: 0,
						minLength: 0,
						source: function( request, response ) {
							var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
							response( select.children( "option" ).map(function() {
								var text = $( this ).text();
								if ( this.value && ( !request.term || matcher.test(text) ) )
									return {
										label: text.replace(
											new RegExp(
												"(?![^&;]+;)(?!<[^<>]*)(" +
												$.ui.autocomplete.escapeRegex(request.term) +
												")(?![^<>]*>)(?![^&;]+;)", "gi"
											), "<strong>$1</strong>" ),
										value: text,
										option: this
									};
							}) );
						},
						select: function( event, ui ) {
							ui.item.option.selected = true;
							self._trigger( "selected", event, {
								item: ui.item.option
							});
						},
						change: function( event, ui ) {
							if ( !ui.item ) {
								var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
									valid = false;
								select.children( "option" ).each(function() {
									if ( $( this ).text().match( matcher ) ) {
										this.selected = valid = true;
										return false;
									}
								});
								if ( !valid ) {
									// remove invalid value, as it didn't match anything
									$( this ).val( "" );
									select.val( "" );
									input.data( "autocomplete" ).term = "";
									return false;
								}
							}
						}
					})
					.addClass( "ui-widget ui-widget-content ui-corner-left" );

				input.data( "autocomplete" )._renderItem = function( ul, item ) {
					return $( "<li></li>" )
						.data( "item.autocomplete", item )
						.append( "<a>" + item.label + "</a>" )
						.appendTo( ul );
				};

				this.button = $( "<button type='button'>&nbsp;</button>" )
					.attr( "tabIndex", -1 )
					.attr( "title", "Show All Items" )
					.insertAfter( input )
					.button({
						icons: {
							primary: "ui-icon-triangle-1-s"
						},
						text: false
					})
					.removeClass( "ui-corner-all" )
					.addClass( "ui-corner-right ui-button-icon" )
					.click(function() {
						// close if already visible
						if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
							input.autocomplete( "close" );
							return;
						}

						// work around a bug (likely same cause as #5265)
						$( this ).blur();

						// pass empty string as value to search for, displaying all results
						input.autocomplete( "search", "" );
						input.focus();
					});
			},

			destroy: function() {
				this.input.remove();
				this.button.remove();
				this.element.show();
				$.Widget.prototype.destroy.call( this );
			}
		});
	})( jQuery );

	$(function() {
		$( "#combobox" ).combobox();
		$( "#toggle" ).click(function() {
			$( "#combobox" ).toggle();
		});
	});
	</script>

	<div class="demo">

		<c:if test="${!empty persons}">
			<div class="ui-widget">
				<label><spring:message code="label.choosePerson" /></label> 
			<form:form method="post" action="personpicked" commandName="person">
			<form:select id="combobox" path="personId">
					  <form:option value="NONE" label="" />
					  <form:options items="${persons}" />
		</form:select>
		  <td colspan="2">
            <input type="submit" value="<spring:message code="button.choose" />"/>
        </td>
        <td><form:errors cssClass="errors" path="personId" /></td>
		</form:form>
		</div>
		</c:if>
	</div>

</div>
<div class="span-18">
<h3><spring:message code="label.dutiesFor" /> ${chosenPerson.firstName} ${chosenPerson.lastName}</h3>
<c:if  test="${!empty dutiesOverview}">


<table class="data">
<tr>
	<th><spring:message code="label.name"/></th>
	<th><spring:message code="label.place"/></th>
    <th><spring:message code="label.date"/></th>
    <th><spring:message code="label.clock"/></th>
    <th><spring:message code="label.hours"/></th>
</tr>
<c:forEach items="${dutiesOverview}" var="d">
    <tr>
        <td>${d.name}</td>
        <td>${d.place}</td>
        <fmt:setLocale value="no" scope="session"/>
        <td><fmt:formatDate type="date" pattern="EEEE dd-MM-yy" value="${d.start.time}"  /></td>
        <td><fmt:formatDate type="date" pattern="HH:mm" value="${d.start.time}"  /> - 
        	<fmt:formatDate type="date" pattern="HH:mm" value="${d.end.time}"  /></td>
        <td>${d.hours}</td>
        <td><a href="delete/${d.dutyId}"><spring:message code="label.remove"/></a></td>
</c:forEach>
    <tr>
    <td class="loud"><spring:message code="label.hoursWorked"/></td><td>
    </td><td></td><td></td><td class="loud">${hours}</td>
   </tr>
</table>
</c:if>	
<c:if test="${empty dutiesOverview}">
<spring:message code="label.personGotNoDuties"/>
</c:if>
</div>
</div>
<c:if test="${!empty ticketsMap}">
<p>
<button id="add" ><spring:message code="button.deliver.ticket" /></button>
</p>
<div class="span-14 last append-bottom" hidden="true" id="form">
<h3><spring:message code="header.deliver.new.ticket"/></h3>
<form method="post" action="add.html">
		
		<table>
 			<tr><form:label path="ticket.ticketId">
 			<spring:message code="label.choose.ticket" /></form:label> 
				<td><form:select path="ticket.ticketId">
					  <form:options items="${ticketsMap}" />
				     </form:select>
               	</td>
			</tr>
		</table>
		<td><form:label path="chosenPerson.paid">
		<spring:message code="label.paid" />
		</form:label></td>
		<form:input path="chosenPerson.paid" name="paid"/>
	
		<tr>
			<td colspan="2"><input type="submit" value="<spring:message code="button.deliver" />" /></td>
		</tr>
	</form>


</div>
</c:if>
<c:if test="${empty ticketsMap}">
<spring:message code="label.festival.no.tickets"/>
</c:if>
<div class="span-14 last append-bottom">
	
<c:if  test="${!empty tickets}">
<h3><spring:message code="header.delivered.to"/> ${chosenPerson.firstName} ${chosenPerson.lastName}:</h3>
<p class="loud">${chosenPerson.firstName} ${chosenPerson.lastName} <spring:message code="para.paid.for.tickets"/>: ${chosenPerson.paid} ${nok}  </p>
<table class="data">
<tr>
    <th><spring:message code="label.ticket.type"/></th>
    <th><spring:message code="label.ticket.date"/></th>
</tr>
<c:forEach items="${tickets}" var="t">
    <tr>
        <td>${t.ticketType}</td>
	    <fmt:setLocale value="no" scope="session"/>
        <td><fmt:formatDate type="date" pattern="EEEE dd-MM-yy" value="${t.date.time}"  /></td>
        <td><a href="remove/${t.ticketId}"><spring:message code="label.remove"/></a></td>
    </tr>
</c:forEach>
</table>
</c:if>
<c:if  test="${empty tickets}">
<p>person have not gotten his tickets</p>
</c:if>
</div>
<script type="text/javascript">	
$("#add").click(function () {
	$("#form").show("slow");
	$("#add").hide("slow");
  });
$(document).ready(function() {
	if($("span").hasClass("errors"))
	$("#form").show("fast");
	if($("span").hasClass("errors"))
	$("#add").hide("fast");
	});
</script>
</body>
</html>