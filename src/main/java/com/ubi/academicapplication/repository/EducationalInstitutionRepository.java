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

	   @Query("SELECT e from EducationalInstitution e WHERE "
			   + " e.educationalInstitutionCode LIKE CONCAT('%', :query,'%')"
			   + " Or e.educationalInstitutionName LIKE CONCAT('%', :query,'%')"
			   + " Or e.educationalInstitutionType LIKE CONCAT('%', :query,'%')"
			   + " Or e.strength LIKE CONCAT('%', :query,'%')"
			   + " Or e.state LIKE CONCAT('%', :query,'%')"
			   + " Or e.exemptionFlag LIKE CONCAT('%', :query,'%')"
			   + " Or e.vvnAccount LIKE CONCAT('%', :query,'%')" )
	   EducationalInstitution getEducationalInstitutionByField(String query);

}
