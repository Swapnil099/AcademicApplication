package com.ubi.academicapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubi.academicapplication.entity.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {



}
