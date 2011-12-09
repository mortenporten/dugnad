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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Duty")
public class Duty implements Serializable{

	private Integer dutyId;
	private Calendar start;
	private Calendar end;
	private Integer hours;
	private Festival festival; 
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
	@Column(name = "start")
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
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "Duty_Person", joinColumns = { @JoinColumn(name = "dutyId") }, inverseJoinColumns = { @JoinColumn(name = "personId") })
	public Collection<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}
	
}
