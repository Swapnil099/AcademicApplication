package com.ubi.academicapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.entity.StudentPromoteDemote;




@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	List<Student> findByGenderAndCategoryAndMinority(String gender,String category, String minority);

	StudentPromoteDemote save(StudentPromoteDemote studentPromoteDemoteCreation);

	
	
}
