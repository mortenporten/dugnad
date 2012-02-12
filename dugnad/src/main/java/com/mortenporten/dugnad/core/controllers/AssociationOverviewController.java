package com.mortenporten.dugnad.core.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.bo.AssociationBo;
import com.mortenporten.dugnad.core.persistence.Association;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.Ticket;


@Controller
@RequestMapping("/{festivalName}/association/*")
@SessionAttributes()
public class AssociationOverviewController {

	@Autowired
	AssociationBo associationBo;
	
	@RequestMapping("/pickassociation")
	public String pickPerson(ModelMap map) {
		
		
		map.put("associationsMap", associationBo.getAllAssociationsMap());
		map.put("association", new Association());
		
		return "associationOverview";
	}
	
	@RequestMapping("/associationpicked")
	public String associationPicked(@ModelAttribute("association") Association association,
			BindingResult result, @PathVariable("festivalName")
    		String festivalName, ModelMap map) {

		if(result.hasErrors()){
			return "associationOverview";
		}
		
		map.put("associationsMap", associationBo.getAllAssociationsMap());
		map.put("association", new Association());
		
		if(association.getAssociationId() != null){
		
			String associationId = Integer.toString(association.getAssociationId());
			association = associationBo.findAssociationById(associationId);
			
			map.put("associationDuties", associationBo.getAllDutiesForAssociationByFestivalName(
					associationId, festivalName));
			map.put("chosenAssociation", association);
		
		}
		
		return "associationOverview";
	}
}
