package com.dev.rest.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.rest.dto.GroupDTO;
import com.dev.rest.dto.ParcelDTO;
import com.dev.rest.dto.UserDTO;
import com.dev.rest.entities.Group;
import com.dev.rest.entities.Parcel;
import com.dev.rest.entities.User;
import com.dev.rest.entities.UserRole;
import com.dev.rest.errors.NoSuchEntityException;
import com.dev.rest.repositories.GroupRepository;
import com.dev.rest.repositories.ParcelRepository;
import com.dev.rest.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private ParcelRepository parcelRepository;
	
	@Override
	public List<UserDTO> getAllUsers() {
		List<User> result = userRepository.findAll();
		return result == null ? null : result.stream().map(u -> u.toGeneralInfoDTO()).collect(Collectors.toList());
	}

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		return userRepository.findOneByName(arg0);
	}

	@Override
	public UserDTO createUser(UserDTO body, UserRole role) {
		User user = body.createNewUser();
		user.setRole(role);
		user = userRepository.save(user);
		return user.toGeneralInfoDTO();
	}

	@Override
	public void deleteUser(Integer id) throws NoSuchEntityException {
		User us = userRepository.findOne(id);
		if(us == null) {
			throw new NoSuchEntityException("No such user with id : " + id);
		}
	 	userRepository.deleteById(id);
	 }

	@Override
	public void deleteUser(String name) throws NoSuchEntityException {
		User us = userRepository.findOneByName(name);
		if(us == null) {
			throw new NoSuchEntityException("No such user with name : " + name);
		}
		userRepository.deleteByName(name);
	}

	@Override
	public GroupDTO joinGroup(String userName, GroupDTO groupDTO) throws NoSuchEntityException {
		User user = userRepository.findOneByName(userName);
		Group group = null;
		if(groupDTO.getId() != null) {
			group = groupRepository.findOneById(groupDTO.getId());
		} else if(groupDTO.getName() != null) {
			group = groupRepository.findOneByName(groupDTO.getName());
		}
		if(group == null) {
			throw new NoSuchEntityException("No such group.");
		}
		if(user.getGroups() == null) {
			user.setGroups(new HashSet<>());
		}
		user.getGroups().add(group);
		userRepository.save(user);
		return group.toGeneralInfoDTO();
	}

	@Override
	public UserDTO getById(Integer id) throws NoSuchEntityException {
		User user = userRepository.findOneById(id);
		if(user == null) {
			throw new NoSuchEntityException("No such user with id : " + id);
		}
		return user.toDTO();
	}

	@Override
	public GroupDTO leaveGroup(String userName, GroupDTO group) throws NoSuchEntityException {
		User user = userRepository.findOneByName(userName);
		if(user == null) {
			throw new NoSuchEntityException("No such user with name : " + userName);
		}
		Group grp = null;
		if(group.getId() != null) { 
			grp = groupRepository.findOne(group.getId());
		} else if(group.getName() != null) {
			grp = groupRepository.findOneByName(group.getName());
		} else {
			throw new NoSuchEntityException("No such group");
		}
		user.removeFromGroup(grp);
		userRepository.save(user);
		return grp.toGeneralInfoDTO();
	}

	@Override
	public ParcelDTO addParcel(String userName, ParcelDTO parcel) throws NoSuchEntityException {
		User user = userRepository.findOneByName(userName);
		if(user == null) {
			throw new NoSuchEntityException("No such user for name : " + userName);
		}
		Parcel newParcel = parcel.newParcel();
		newParcel.setUser(user);
		newParcel = parcelRepository.save(newParcel);
		return newParcel.toDTO();
	}

	@Override
	public ParcelDTO deleteParcel(String userName, ParcelDTO parcel) throws NoSuchEntityException {
		User user = userRepository.findOneByName(userName);
		if(user == null) {
			throw new NoSuchEntityException("No such user for name : " + userName);
		}
		Integer id = parcel.getId();
		Optional<Parcel> p = user.getParcels().stream().filter(pr -> id.equals(pr.getId())).findAny();
		if(!p.isPresent()) {
			throw new NoSuchEntityException("No such parcel for user with name : " + userName);
		}
		parcelRepository.deleteById(id);
		return parcel;
	}

	@Override
	public List<UserDTO> getAllByPage(Pageable pageble) {
		Page<User> list = userRepository.findAll(pageble);
		return list.getContent().stream().map(u -> u.toGeneralInfoDTO()).collect(Collectors.toList());
	}
	
}
