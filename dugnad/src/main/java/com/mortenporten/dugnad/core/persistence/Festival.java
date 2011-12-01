package com.mortenporten.dugnad.core.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "festival")
public class Festival implements Serializable {

	
	private Integer festivalId;
	private String festivalName;
	private Collection<Duty> duties;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getFestivalId() {
		return festivalId;
	}
	public void setFestivalId(Integer festivalId) {
		this.festivalId = festivalId;
	}
	
	@Column(name = "Name")
	public String getFestivalName() {
		return festivalName;
	}
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	
	@OneToMany(mappedBy="duty")
	public Collection<Duty> getDuties() {
		return duties;
	}
	public void setDuties(Collection<Duty> duties) {
		this.duties = duties;
	}
	
	
	
}
