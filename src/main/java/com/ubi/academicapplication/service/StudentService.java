package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Student;



public interface StudentService{
	Student saveStudent(Student student);// throws BusinessException;;

	Response<List<Student>>  getStudents();
	
//	List<Student> getStudents();
	
	Response<Student> getStudentById(int id);

    void  deleteStudent(int id);
//
	Student updateStudent(Student student);
}
