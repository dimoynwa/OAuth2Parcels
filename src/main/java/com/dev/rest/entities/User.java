package com.dev.rest.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dev.rest.dto.ParcelDTO;
import com.dev.rest.dto.UserDTO;

@NamedEntityGraphs(value={@NamedEntityGraph(name="user_info_graph", attributeNodes={
		@NamedAttributeNode(value="groups"), @NamedAttributeNode(value="parcels")
}), @NamedEntityGraph(name="user_gen_info_graph", attributeNodes={@NamedAttributeNode(value="groups")})})

@Entity(name="users")
@Table(name="users")
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="age", nullable=false)
	private Integer age;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="role", nullable=false)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "group_user", joinColumns = {
			@JoinColumn(name = "USER_ID", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "GROUP_ID",
					nullable = false, updatable = false) })
	private Set<Group> groups;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade=CascadeType.ALL)
	private Set<Parcel> parcels;
	
	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Parcel> getParcels() {
		return parcels;
	}

	public void setParcels(Set<Parcel> parcels) {
		this.parcels = parcels;
	}
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		return authorities;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void removeFromGroup(Group group) {
		this.getGroups().remove(group);
	    groups.remove(this);
	}
	
	public UserDTO toDTO() {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setAge(age);
		dto.setEmail(email);
		dto.setName(name);
		if(groups != null) {
			List<String> grs = groups.stream().map(g -> g.getName()).collect(Collectors.toList());
			dto.setGroups(grs);
		}
		if(parcels != null) {
			List<ParcelDTO> parcs = parcels.stream().map(p -> p.toDTO()).collect(Collectors.toList());
			dto.setParcels(parcs);
		}
		return dto;
	}
	
	public UserDTO toGeneralInfoDTO() {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setAge(age);
		dto.setEmail(email);
		dto.setName(name);
		if(groups != null) {
			List<String> grs = groups.stream().map(g -> g.getName()).collect(Collectors.toList());
			dto.setGroups(grs);
		}
		return dto;
	}
	
	
}
