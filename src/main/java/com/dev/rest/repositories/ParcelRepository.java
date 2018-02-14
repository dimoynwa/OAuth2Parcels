package com.dev.rest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dev.rest.entities.Parcel;

public interface ParcelRepository extends PagingAndSortingRepository<Parcel, Integer> {
	
	void deleteById(Integer id);
}
