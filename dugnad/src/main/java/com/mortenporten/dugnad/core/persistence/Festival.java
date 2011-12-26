package com.mortenporten.dugnad.core.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Festival", uniqueConstraints = @UniqueConstraint(columnNames="Name"))
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
	
	@NotBlank
	@Column(name = "Name", unique = true, nullable = false)
	public String getFestivalName() {
		return festivalName;
	}
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	public Collection<Duty> getDuties() {
		return duties;
	}
	public void setDuties(Collection<Duty> duties) {
		this.duties = duties;
	}
		
}
