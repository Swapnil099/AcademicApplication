package com.ubi.academicapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

}
