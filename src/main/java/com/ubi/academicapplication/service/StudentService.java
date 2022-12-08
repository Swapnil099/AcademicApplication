package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Student;

public interface StudentService {
	Response<Student> saveStudent(Student student);

	Response<List<Student>> getStudents(Integer PageNumber, Integer PageSize);

	Response<Student> getStudentById(int id);

	public Response<Student> deleteById(int id);

	Response<Student> updateStudent(Student student);
}
