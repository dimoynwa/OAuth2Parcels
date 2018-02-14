package com.dev.rest.entities;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;

import com.dev.rest.dto.GroupDTO;

@NamedEntityGraphs(value={@NamedEntityGraph(name="group_full_info", attributeNodes={@NamedAttributeNode(value="users")})})

@Entity(name="groups")
@Table(name="groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", unique=true)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "groups")
	private Set<User> users;

	public Group() {
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public GroupDTO toDTO() {
		GroupDTO dto = new GroupDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setUsers(users != null ? users.stream().map(u -> u.toDTO()).collect(Collectors.toList()): new ArrayList<>());
		return dto;
	}
	public GroupDTO toGeneralInfoDTO() {
		GroupDTO dto = new GroupDTO();
		dto.setId(id);
		dto.setName(name);
		return dto;
	}
}
