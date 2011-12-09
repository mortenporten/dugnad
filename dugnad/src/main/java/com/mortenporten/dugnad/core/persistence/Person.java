package com.mortenporten.dugnad.core.persistence;

import java.util.Collection;

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
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import static javax.persistence.GenerationType.IDENTITY;




@Entity
@Table(name = "Person")
public class Person implements java.io.Serializable {

	
	private Integer personId;
	private String name;
	private String telephone;
	private String email;
	private Collection<Duty> duties;



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getPersonId() {
		return personId;
	}
	
	
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	
	@Column(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Duty_Person", joinColumns = { @JoinColumn(name = "personId") }, inverseJoinColumns = { @JoinColumn(name = "dutyId") })
	@LazyCollection(LazyCollectionOption.FALSE)
	public Collection<Duty> getDuties() {
		return duties;
	}


	public void setDuties(Collection<Duty> duties) {
		this.duties = duties;
	}
	
	
	
	
	
}
