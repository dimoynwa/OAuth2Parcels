package com.dev.rest.dto;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.dev.rest.entities.Group;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GroupDTO {
	
	private Integer id;
	private String name;
	private List<UserDTO> users;
	public GroupDTO() {
		super();
		users = new LinkedList<>();
	}
	public GroupDTO(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Group newGroup() {
		Group newGroup = new Group();
		newGroup.setName(name);
		return newGroup;
	}
	
}
