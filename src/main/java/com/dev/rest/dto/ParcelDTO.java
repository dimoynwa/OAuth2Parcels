package com.dev.rest.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.dev.rest.entities.Parcel;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ParcelDTO {
	
	private Integer id;
	private String name;
	private double latitude;
	private double longtitude;
	private String culture;
	public ParcelDTO(Integer id, String name, double latitude, double longtitude, String culture) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.culture = culture;
	}
	public ParcelDTO() {
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
	
	public Parcel newParcel() {
		Parcel newParcel = new Parcel();
		newParcel.setCulture(culture);
		newParcel.setLatitude(latitude);
		newParcel.setLongtitude(longtitude);
		newParcel.setName(name);
		return newParcel;
	}
}
