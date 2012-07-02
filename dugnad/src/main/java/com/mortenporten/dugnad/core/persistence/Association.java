package com.mortenporten.dugnad.core.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="Association")
public class Association implements Serializable {

	
	private Integer associationId;
	private String associationName;
	private Collection<Person> associationPersons;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getAssociationId() {
		return associationId;
	}
	public void setAssociationId(Integer associationId) {
		this.associationId = associationId;
	}
	
	@NotBlank
	@Column(name = "Name", length = 50)
	public String getAssociationName() {
		return associationName;
	}
	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}
	
	@OneToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	public Collection<Person> getAssociationPersons() {
		return associationPersons;
	}
	
	public void setAssociationPersons(Collection<Person> associationPersons) {
		this.associationPersons = associationPersons;
	}
	
	
	
}
