package com.ubi.academicapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ubi.academicapplication.entity.School;

public interface SchoolRepository extends JpaRepository<School, Integer>{

	@Query(value = "FROM School sd  WHERE sd.name = ?1")
	Optional<com.ubi.academicapplication.entity.School> findByName(String name);

	
}
