package com.dev.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;

import com.dev.rest.dto.GroupDTO;
import com.dev.rest.entities.Group;
import com.dev.rest.errors.NoSuchEntityException;
import com.dev.rest.repositories.GroupRepository;
import com.dev.rest.services.GroupService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupTest {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GroupService groupService;
	
	@Test
	public void testFindByIdAndFindByName() {
		Group group = new Group();
		group.setName("testGroup");
		
		group = groupRepository.save(group);
		
		assertThat(group, notNullValue());
		
		Group foundById = groupRepository.findOneById(group.getId());
		
		assertThat(foundById, notNullValue());
		assertEquals(foundById.getId(), group.getId());
		
		Group foundByName = groupRepository.findOneByName("testGroup");
		
		assertThat(foundByName, notNullValue());
		assertEquals(foundByName.getId(), group.getId());
		assertEquals(foundByName.getName(), "testGroup");
		
		groupRepository.delete(group.getId());
	}
	
	@Test
	public void testCreateAndDeleteGroup() throws NoSuchEntityException {
		GroupDTO group = new GroupDTO();
		group.setName("testCreate");
		
		GroupDTO created = groupService.createGroup(group);
		
		assertThat(created, notNullValue());
		assertEquals(created.getName(), "testCreate");
		
		groupService.deleteGroup(created.getId());
	}
}
