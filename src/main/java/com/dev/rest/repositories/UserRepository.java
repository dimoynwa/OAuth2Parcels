package com.dev.rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dev.rest.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	
	@EntityGraph(value="user_gen_info_graph",type=EntityGraphType.FETCH)
	List<User> findAll();
	
	@EntityGraph(value="user_info_graph",type=EntityGraphType.FETCH)
	User findOneByName(String name);
	
	@EntityGraph(value="user_info_graph",type=EntityGraphType.FETCH)
	User findOneById(Integer id);
	
	void deleteById(Integer id);
	
	void deleteByName(String name);
}
