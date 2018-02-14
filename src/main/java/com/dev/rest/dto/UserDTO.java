package com.dev.rest.dto;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.dev.rest.entities.User;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserDTO {
	
	private Integer id;
	private String name;
	private String email;
	private Integer age;
	private String password;
	private List<String> groups;
	private List<ParcelDTO> parcels;
	
	public UserDTO() {
		super();
		groups = new LinkedList<>();
		parcels = new LinkedList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<ParcelDTO> getParcels() {
		return parcels;
	}
	public void setParcels(List<ParcelDTO> parcels) {
		this.parcels = parcels;
	}
	public void addGroup(String group) {
		groups.add(group);
	}
	public User createNewUser() {
		User newUser = new User();
		newUser.setAge(age);
		newUser.setEmail(email);
		newUser.setName(name);
		newUser.setPassword(password);
		newUser.setEnabled(true);
		return newUser;
	}
}
