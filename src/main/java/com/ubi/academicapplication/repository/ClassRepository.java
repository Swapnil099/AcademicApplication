package com.ubi.academicapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubi.academicapplication.entity.ClassDetail;


public interface ClassRepository extends JpaRepository<ClassDetail, Long> {

	//ClassDetail getClassByName(String name);
    
   // ClassDetail getClassByCode(String code);

    ClassDetail getClassByclassName(String className);
	
   	ClassDetail getClassByclassCode(String classCode);

   //ClassDetail getClassByName(String schoolName);


    
}
