package com.mortenporten.dugnad.core.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Duty")
public class Duty implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875725278414997844L;
	private Integer dutyId;
	private Calendar start;
	private Calendar end;
	private Integer hours;
	private Festival festival;
	private String place;
	private Person responsible;
	private String name;
	private String description;
	private Integer required;
	private Collection<Person> persons;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getDutyId() {
		return dutyId;
	}
	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Start")
	@DateTimeFormat(pattern = "dd-MM-yy HH:mm")
	public Calendar getStart() {
		return start;
	}
	
	public void setStart(Calendar start) {
		this.start = start;
	}
	
	@DateTimeFormat(pattern = "dd-MM-yy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "End")
	public Calendar getEnd() {
		return end;
	}
	
	public void setEnd(Calendar end) {
		this.end = end;
	}
	
	@Column(name = "Hours")
	public Integer getHours() {
		return hours;
	}
	
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	
	@ManyToOne()
	public Festival getFestival() {
		return festival;
	}
	
	public void setFestival(Festival festival) {
		this.festival = festival;
	}
	
	@Column(name="Place")
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	@OneToOne
	public Person getResponsible() {
		return responsible;
	}
	
	public void setResponsible(Person responsible) {
		this.responsible = responsible;
	}
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="Description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "Required")
	public Integer getRequired() {
		return required;
	}
	
	public void setRequired(Integer required) {
		this.required = required;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Collection<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}
	
	
}
