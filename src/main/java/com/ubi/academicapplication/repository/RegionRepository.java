package com.ubi.academicapplication.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {
     Region getRegionByName(String name);
     
     Region getRegionByCode(String code);

	Set<Region> getReferenceByIdIn(Set<Integer> regionId);

	
	
     
     
}
