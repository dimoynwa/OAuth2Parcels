package com.dev.rest.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dev.rest.dto.GroupDTO;
import com.dev.rest.dto.ParcelDTO;
import com.dev.rest.dto.UserDTO;
import com.dev.rest.entities.UserRole;
import com.dev.rest.errors.NoSuchEntityException;

public interface UserService {
	
	List<UserDTO> getAllUsers();
	
	List<UserDTO> getAllByPage(Pageable pageble);
	
	UserDTO getById(Integer id) throws NoSuchEntityException;
	
	UserDTO createUser(UserDTO body, UserRole role);
	
	void deleteUser(Integer id) throws NoSuchEntityException;
	
	void deleteUser(String name) throws NoSuchEntityException;
	
	GroupDTO joinGroup(String userName, GroupDTO group) throws NoSuchEntityException;
	
	GroupDTO leaveGroup(String userName, GroupDTO group) throws NoSuchEntityException;
	
	ParcelDTO addParcel(String userName, ParcelDTO parcel) throws NoSuchEntityException;
	
	ParcelDTO deleteParcel(String userName, ParcelDTO parcel) throws NoSuchEntityException;
}
