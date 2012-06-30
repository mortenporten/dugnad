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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;




@Entity
@Table(name = "Paid")
public class Paid implements java.io.Serializable {

	
	/**
	 * 
	 */

	private Integer paidId;
	private Person person;
	private BigDecimal paid;
	private Festival festival;
	


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getPaidId() {
		return paidId;
	}
	
	
	public void setPaidId(Integer paidId) {
		this.paidId = paidId;
	}

	@Column(name=("Paid"), precision=10, scale=2)
	public BigDecimal getPaid() {
		return paid;
	}
	
	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	@ManyToOne()
	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}

	@OneToOne 
	public Festival getFestival() {
		return festival;
	}


	public void setFestival(Festival festival) {
		this.festival = festival;
	}
	
}
