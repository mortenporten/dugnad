package com.mortenporten.dugnad.core.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;




@Entity
@Table(name = "Person")
public class Person implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9075374281953270752L;
	private Integer personId;
	private String firstName;
	private String lastName;
	private String telephone;
	private String email;
	private BigDecimal paid;
	private Collection<Duty> duties;
	private Collection<Ticket> tickets;
	private Association association;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getPersonId() {
		return personId;
	}
	
	
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@NotBlank
	@Column(name = "Firstname")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@NotBlank
	@Column(name = "Lastname")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	@Column(name = "Telephone")
	@Digits(integer = 8, fraction = 0)
	@Length(min = 8, max = 8)
	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Email
	@Column(name = "Email")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name=("Paid"), precision=10, scale=2)
	public BigDecimal getPaid() {
		return paid;
	}
	
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}


	@ManyToMany(mappedBy="persons")
	@LazyCollection(LazyCollectionOption.FALSE)
	public Collection<Duty> getDuties() {
		return duties;
	}


	public void setDuties(Collection<Duty> duties) {
		this.duties = duties;
	}
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	public Collection<Ticket> getTickets() {
		return tickets;
	}


	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}

	@ManyToOne(cascade=CascadeType.REMOVE)
	public Association getAssociation() {
		return association;
	}


	public void setAssociation(Association association) {
		this.association = association;
	}
	
	
	
}
