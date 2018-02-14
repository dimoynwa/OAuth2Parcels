package com.dev.rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dev.rest.entities.Group;

public interface GroupRepository extends PagingAndSortingRepository<Group, Integer> {
	
	List<Group> findAll();
	
	@EntityGraph(value="group_full_info",type=EntityGraphType.FETCH)
	Group findOneById(Integer id);
	
	@EntityGraph(value="group_full_info",type=EntityGraphType.FETCH)
	Group findOneByName(String name);
	
	void deleteById(Integer id);
	
	void deleteByName(String name);
}
