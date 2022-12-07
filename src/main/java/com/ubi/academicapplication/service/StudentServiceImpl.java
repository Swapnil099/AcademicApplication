package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

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

	public Response<Student> saveStudent(Student student) {

		Response<Student> response = new Response<>();

		if (student.getStudentName().isEmpty() || student.getStudentName().length() == 0) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_NAME_FOUND.getCode(),
					HttpStatusCode.NO_STUDENT_NAME_FOUND, HttpStatusCode.NO_STUDENT_NAME_FOUND.getMessage(), res);
		}
		Student savedStudent = repository.save(student);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<Student>(savedStudent));
		return response;
	}

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
	public Response<Student> deleteById(int id) {
		res.setData(null);
		Optional<Student> student = repository.findById(id);
		if (!student.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		repository.deleteById(id);
		Response<Student> response = new Response<>();
		response.setMessage(HttpStatusCode.STUDENT_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.STUDENT_DELETED.getCode());
		response.setResult(new Result<Student>(student.get()));
		return response;
	}

	public Response<Student> updateStudent(Student student) {
		res.setData(null);
		Optional<Student> existingStudentContainer = repository.findById(student.getStudentId());
		if (!existingStudentContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_FOUND.getCode(), HttpStatusCode.NO_STUDENT_FOUND,
					HttpStatusCode.NO_STUDENT_FOUND.getMessage(), res);
		}
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

		Student updateStudent = repository.save(existingStudent);
		Response<Student> response = new Response<>();
		response.setMessage(HttpStatusCode.STUDENT_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.STUDENT_UPDATED.getCode());
		response.setResult(new Result<>(updateStudent));
		return response;
	}

}
