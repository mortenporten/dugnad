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
 <script type="text/javascript" src="<c:url value="/resources/jquery-ui/jquery-ui-timepicker-addon.js" />"></script>
 <script type="text/javascript">
	$(function() {
		$( '#start' ).datetimepicker({
			dateFormat: 'dd-mm-y'
		});
		$( '#end' ).datetimepicker({
			dateFormat: 'dd-mm-y'
		});
		
		$('#addDuty').submit(function() {	  
			$("#end").val( $.trim($("#end").val()) );
			$("#start").val( $.trim($("#start").val()) );  
			return true;
		});
	});
</script>

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
		/* $( "#toggle" ).click(function() {
			$( "#combobox" ).toggle();
		}); */
	});
</script>


  
  <style type="text/css"> 
			
			/* css for timepicker */
			.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
			.ui-timepicker-div dl { text-align: left; }
			.ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
			.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
			.ui-timepicker-div td { font-size: 90%; }
			.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
			
		</style>
  
    <title><spring:message code="header.editDuty"/></title>
</head>
<body>
<div class="container"> 
 
 		<%@ include file="/WEB-INF/jsp/header-all.jsp" %>
 
<div  class="span-14 append-bottom">
	<button id="add" hidden="true"><spring:message code="button.addDuty" /></button>
</div>

<div id="form" class="span-21 append-bottom">
 
<h3><spring:message code="header.addDuty"/></h3>
 
<form:form method="post" id="addDuty" action="edited.html" commandName="duty">
 
    <table>
    <tr>
        <td><form:label path="name"><spring:message code="label.name"/></form:label></td>
        <td><form:input path="name" /></td><td><form:errors cssClass="errors" path="name" /></td>
    
        <td><form:label path="place"><spring:message code="label.place"/></form:label></td>
        <td><form:input path="place" /></td><td><form:errors cssClass="errors" path="place" /></td>
    </tr>
    <tr>
        <td><form:label path="start"><spring:message code="label.start"/></form:label></td>
        <td><form:input id="start" path="start" /></td><td><form:errors cssClass="errors" path="start" /></td>
   
        <td><form:label path="end"><spring:message code="label.end"/></form:label></td>
        <td><form:input id="end" path="end" /></td><td><form:errors cssClass="errors" path="end" /></td>
    </tr>
    <tr>
        <td><form:label  path="hours"><spring:message code="label.hours"/></form:label></td>
        <td><form:input  path="hours" /></td><td><form:errors cssClass="errors" path="hours" /></td>
    
    	<td><form:label  path="definedHours"><spring:message code="label.is.defined.hours"/></form:label></td>
        <td><form:checkbox value="false" path="definedHours" /></td><td><form:errors cssClass="errors" path="definedHours" /></td>
    </tr>
    <tr>
    	<td><form:label  path="required"><spring:message code="label.required" /></form:label></td>
        <td><form:input  path="required" /></td><td><form:errors cssClass="errors" path="required" /></td>
        
        <td><form:label path="description"><spring:message code="label.description"/></form:label></td>
        <td><form:textarea cssClass="textbox" path="description" /></td><td><form:errors cssClass="errors" path="description" /></td>
    </tr> 
</table>

 <tr>
     <td><form:label path="responsible" > <spring:message code="label.responsible"/></form:label></td> 
      <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th> 
     <form:select  id="combobox"  path="responsible.personId" >
					<form:option value="" label="" />
					<form:options items="${persons}"/>
	</form:select>
   
 </tr>
   
    <br>
     <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.edit"/>"/>
        </td>
    </tr>
</form:form>
</div>





</div>
</body>


</html>