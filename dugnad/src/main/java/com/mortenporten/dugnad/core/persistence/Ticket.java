package com.mortenporten.dugnad.core.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Ticket")
public class Ticket implements Serializable{

	private Integer ticketId;
	private Festival festival;
	private String ticketType;
	private Calendar date;
	private Collection<Person> persons;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	
	@OneToOne
	public Festival getFestival() {
		return festival;
	}
	
	public void setFestival(Festival festival) {
		this.festival = festival;
	}
	
	@Column(name="type",length=40)
	@NotBlank
	public String getTicketType() {
		return ticketType;
	}
	
	public void setTicketType(String name) {
		this.ticketType = name;
	}
	@DateTimeFormat(pattern = "dd-MM-yy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	public Calendar getDate() {
		return date;
	}
	
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	@ManyToMany(mappedBy="tickets")
	@LazyCollection(LazyCollectionOption.FALSE)
	public Collection<Person> getPersons() {
		return persons;
	}

	public void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}
	
	
	
}
