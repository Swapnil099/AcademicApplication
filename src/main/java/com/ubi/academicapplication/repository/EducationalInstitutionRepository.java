package com.ubi.academicapplication.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.EducationalInstitution;

@Repository
public interface EducationalInstitutionRepository extends JpaRepository<EducationalInstitution, Integer>{
	

	Optional<EducationalInstitution>findByeducationalInstitutionName(String educationalInstitutionName);
	
   EducationalInstitution getEducationalInstitutionByeducationalInstitutionName(String educationalInstitutionName);
	
	EducationalInstitution getEducationalInstitutionByeducationalInstitutionCode(String educationalInstitutionCode);

	  

}
