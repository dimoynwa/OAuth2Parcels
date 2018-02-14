package com.dev.rest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dev.rest.dto.ParcelDTO;

@Entity(name="parcels")
@Table(name="parcels")
public class Parcel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", unique=true)
	private String name;
	
	@Column(name="latitude")
	private double latitude;
	
	@Column(name="longtitude")
	private double longtitude;
	
	@Column(name="culture")
	private String culture;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public Parcel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public ParcelDTO toDTO() {
		ParcelDTO dto = new ParcelDTO();
		dto.setCulture(culture);
		dto.setId(id);
		dto.setLatitude(latitude);
		dto.setLongtitude(longtitude);
		dto.setName(name);
		return dto;
	}
}
