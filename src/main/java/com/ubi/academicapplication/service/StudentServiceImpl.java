package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import com.ubi.academicapplication.entity.Payment;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.repository.StudentRepository;



@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	Result res;

	@Autowired
	private StudentRepository repository;

	public Student saveStudent(Student student) {
		if (student.getStudentName().isEmpty() || student.getStudentName().length() == 0) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_NAME_FOUND.getCode(),
					HttpStatusCode.NO_STUDENT_NAME_FOUND, HttpStatusCode.NO_STUDENT_NAME_FOUND.getMessage(), res);
		}Student savedStudent = repository.save(student);
		return savedStudent;
	}

//	public Response<List<Student>> getStudents(Integer PageNumber, Integer PageSize) {
//		Pageable paging = PageRequest.of(PageNumber, PageSize);
//		Page<Student> pagedResult = repository.findAll(paging);
//		return ;
//	}

	public Response<List<Student>> getStudents() {
		Response<List<Student>> getListofStudent = new Response<List<Student>>();
		List<Student> list = (List<Student>) this.repository.findAll();
		res.setData(list);
		Result<List<Student>> studentsResult = new Result<>();
		if (list.size() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		studentsResult.setData(list);
		getListofStudent.setStatusCode(200);
		getListofStudent.setResult(studentsResult);
		return getListofStudent;
	}

	public Response<Student> getStudentById(int id) {
		Response<Student> getStudent = new Response<Student>();
		Optional<Student> std = null;
		std = this.repository.findById(id);
		Result<Student> studentResult = new Result<>();
		if (!std.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_STUDENT_MATCH_WITH_ID, HttpStatusCode.NO_STUDENT_MATCH_WITH_ID.getMessage(), res);
		}
		studentResult.setData(std.get());
		getStudent.setStatusCode(200);
		getStudent.setResult(studentResult);
		return getStudent;
	}

	@Override
	public void deleteStudent(int id) {
		try {
			Response<Student> deleteStd=new Response<Student>();
			repository.deleteById(id);
			
		} catch (IllegalArgumentException e) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_FOUND.getCode(), HttpStatusCode.NO_STUDENT_FOUND,
					HttpStatusCode.NO_STUDENT_FOUND.getMessage(), res);
		} catch (Exception e) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_FOUND.getCode(), HttpStatusCode.NO_STUDENT_FOUND,
					HttpStatusCode.NO_STUDENT_FOUND.getMessage(), res);

		}
	}

	public Student updateStudent(Student student) {	
		res.setData(null);
		Optional<Student> existingStudentContainer = repository.findById(student.getStudentId());
		if (!existingStudentContainer.isPresent())
			throw new CustomException(HttpStatusCode.NO_STUDENT_FOUND.getCode(), HttpStatusCode.NO_STUDENT_FOUND,
					HttpStatusCode.NO_STUDENT_FOUND.getMessage(), res);
		else {
			Student existingStudent = existingStudentContainer.get();
			existingStudent.setStudentName(student.getStudentName());
			existingStudent.setStudentStatus(student.isStudentStatus());
			existingStudent.setCategory(student.getCategory());
			existingStudent.setFatherName(student.getFatherName());
			existingStudent.setFatherOccupation(student.getFatherOccupation());
			existingStudent.setMotherName(student.getMotherName());
			existingStudent.setMotherOccupation(student.getMotherOccupation());
			existingStudent.setGender(student.getGender());
			existingStudent.setJoiningDate(student.getJoiningDate());
			existingStudent.setStatus(student.getStatus());
			existingStudent.setVerifiedByTeacher(student.getVerifiedByTeacher());
			existingStudent.setVerifiedByPrincipal(student.getVerifiedByPrincipal());
			existingStudent.setVerifiedByRegion(student.getVerifiedByRegion());
			return repository.save(existingStudent);
		}

	}


}
