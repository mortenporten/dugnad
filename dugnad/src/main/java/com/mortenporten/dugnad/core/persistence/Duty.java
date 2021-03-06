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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity(name = "Duty")
public class Duty implements Serializable, Comparable<Duty>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875725278414997844L;
	private Integer dutyId;
	private Calendar start;
	private Calendar end;
	private Double hours;
	private Boolean definedHours;
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
	
	@Column(name = "Hours", precision=10, scale=2)
	@Min(value=0)
	public Double getHours() {
		return hours;
	}
	
	public void setHours(Double hours) {
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
	@NotBlank
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
	
	@Min(0)
	@Max(25)
	@Digits(fraction = 0, integer = 8)
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
	
	@Column(name = "Definedhours")
	public Boolean getDefinedHours() {
		return definedHours;
	}
	public void setDefinedHours(Boolean definedHours) {
		this.definedHours = definedHours;
	}
	@Override
	public int compareTo(Duty duty) {
		int compare = this.start.compareTo(duty.start);
		if(compare == 0){
			compare = this.end.compareTo(duty.end);
			if(compare == 0){
				return this.name.compareTo(duty.getName());
			}
			return compare;
		}
		return compare;
	}
	
	
}
