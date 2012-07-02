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
public class Person implements java.io.Serializable, Comparable<Person> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9075374281953270752L;
	private Integer personId;
	private String firstName;
	private String lastName;
	private String telephone;
	private String email;
	private Collection<Duty> duties;
	private Collection<Ticket> tickets;
	private Association association;
	private Collection<Paid> receipts;
	private Boolean mailSent;


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

	@ManyToOne()
	public Association getAssociation() {
		return association;
	}


	public void setAssociation(Association association) {
		this.association = association;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	public Collection<Paid> getReceipts() {
		return receipts;
	}


	public void setReceipts(Collection<Paid> receipts) {
		this.receipts = receipts;
	}

	@Column(name = "MailSent")
	public Boolean getMailSent() {
		return mailSent;
	}


	public void setMailSent(Boolean mailSent) {
		this.mailSent = mailSent;
	}
	
	@Override
	public int compareTo(Person person) {
		int result = this.lastName.compareTo(person.lastName);
		if(result == 0){
			result = this.firstName.compareTo(person.firstName);
			if(result == 0){
				result = this.telephone.compareTo(person.telephone);
			}
		}
		return result;
	}
	
	
}
