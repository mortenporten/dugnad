package com.mortenporten.dugnad.core.views;

import java.awt.Color;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;


public class DayOverviewPdfView extends AbstractPdfView {

	private static Font dutyFont = new Font(Font.TIMES_ROMAN, 20,
			Font.BOLD);
	private static Font catFont = new Font(Font.TIMES_ROMAN, 16,
			Font.BOLD);
	
	private static Font smallBold = new Font(Font.TIMES_ROMAN, 14,
			Font.BOLD);
	
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Locale currentLocale = new Locale("nb", "no");
		ResourceBundle messages =
			    ResourceBundle.getBundle(
			        "properties.messages", currentLocale);
		
		Map<String, List<Duty>> duties = (Map<String, List<Duty>>) model.get("dayOverview");
		 
		List<Duty> dutiesForDate = duties.get("duties");
		
		DateTime date = new DateTime(dutiesForDate.get(0).getStart().getTimeInMillis());
		Locale locale = new Locale("no", "NO");
		String onlyDate = date.toString("EEEE dd-MM-yy",locale);

		Paragraph header = new Paragraph(messages.getString("header.day.overview.for") +
				onlyDate + ":", dutyFont);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);
		
		for(Duty d:dutiesForDate){
			Paragraph headerDuty = new Paragraph(d.getName(),dutyFont);
			document.add(headerDuty);
			document.add(new Paragraph("  "));
			
			PdfPTable dutyTable = new PdfPTable(4);
			
			Phrase phrase = new Phrase(messages.getString("label.place"), smallBold);
			dutyTable.addCell(phrase);
			
			phrase = new Phrase(messages.getString("label.clock"), smallBold);
			dutyTable.addCell(phrase);
			
			phrase = new Phrase(messages.getString("label.hours"), smallBold);
			dutyTable.addCell(phrase);
			
			phrase = new Phrase(messages.getString("label.responsible"), smallBold);
			dutyTable.addCell(phrase);
			
			phrase = new Phrase(messages.getString("label.description") + ":", smallBold);
			Paragraph description = new Paragraph(phrase);
			
			dutyTable.addCell(d.getPlace());
			dutyTable.addCell(getTimeBetween(d));
			dutyTable.addCell(Double.toString(d.getHours()));
	
			if(d.getResponsible() != null){
				dutyTable.addCell(d.getResponsible().getFirstName()
						+ " " + d.getResponsible().getLastName() + " " + 
						messages.getString("short.telephone") +
						d.getResponsible().getTelephone());	
			} else {
				dutyTable.addCell("");
			}
	
			Paragraph descriptionText = new Paragraph(d.getDescription());
			
			document.add(dutyTable);
			document.add(description);
			document.add(descriptionText);
			
			Paragraph headerPerson = new Paragraph(messages.getString("header.persons.assigned.to") +
					" " + d.getName() + ":", catFont);
			document.add(headerPerson);
			document.add(new Paragraph("  "));
			
			PdfPTable personTable = new PdfPTable(4);
			
			phrase = new Phrase(messages.getString("label.firstName"), smallBold);
			personTable.addCell(phrase);
			
			phrase = new Phrase(messages.getString("label.lastName"), smallBold);
			personTable.addCell(phrase);
			
			phrase = new Phrase(messages.getString("label.email"), smallBold);
			personTable.addCell(phrase);
			
			phrase = new Phrase(messages.getString("label.telephone"), smallBold);
			personTable.addCell(phrase);
			
			for(Person p : d.getPersons()){
				personTable.addCell(p.getFirstName());
				personTable.addCell(p.getLastName());
				personTable.addCell(p.getEmail());
				personTable.addCell(p.getTelephone());
			
			}
			
			document.add(personTable);
		}
		
 
		
		
		
	}
	
	private String getTimeBetween(Duty duty){
		
		DateTime start = new DateTime(duty.getStart().getTimeInMillis());
		DateTime end = new DateTime(duty.getEnd().getTimeInMillis());
		
		Locale locale = new Locale("no", "NO");
		String time = start.toString("HH:mm",locale);
		time += "-" + end.toString("HH:mm",locale);
		
		return time;
	}

}
