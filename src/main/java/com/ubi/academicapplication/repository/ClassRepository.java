package com.ubi.academicapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.ClassDetail;

@Repository
public interface ClassRepository extends JpaRepository<ClassDetail, Long> {

	//ClassDetail getClassByName(String name);
    
   // ClassDetail getClassByCode(String code);

    ClassDetail getClassByclassName(String className);
	
   	ClassDetail getClassByclassCode(String classCode);
   	
   	//Optional<ClassDetail> findByName(String className);

   //ClassDetail getClassByName(String schoolName);


    
}
