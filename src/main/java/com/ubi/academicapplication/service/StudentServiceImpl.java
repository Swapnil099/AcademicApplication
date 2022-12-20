package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;
import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.StudentMapper;
import com.ubi.academicapplication.repository.ClassRepository;
import com.ubi.academicapplication.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	Result res;

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	ClassRepository classRepository;

	@Autowired
	private StudentRepository repository;

	public Response<StudentDto> saveStudent(StudentDto studentDto) {

		res.setData(null);
		Response<StudentDto> response = new Response<>();

		if (studentDto.getStudentName().isEmpty() || studentDto.getStudentName().length() == 0) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_NAME_FOUND.getCode(),
					HttpStatusCode.NO_STUDENT_NAME_FOUND, HttpStatusCode.NO_STUDENT_NAME_FOUND.getMessage(), res);
		}
		
//		studentDto.getClassId();
		ClassDetail classDetail = classRepository.getReferenceById(studentDto.getClassId());
		Student student=studentMapper.dtoToEntity(studentDto);
		student.setClassDetail(classDetail);
		
		Student savedStudent = repository.save(student);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<StudentDto>(studentMapper.entityToDto(savedStudent)));
		return response;
	}

	public Response<List<StudentDto>> getStudents(Integer PageNumber, Integer PageSize) {
		res.setData(null);
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<StudentDto>> getListofStudent = new Response<>();
		Page<Student> list = this.repository.findAll(paging);

		List<StudentDto> studentDtos = studentMapper.entitiesToDtos(list.toList());

		res.setData(studentDtos);
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		getListofStudent.setStatusCode(200);
		getListofStudent.setResult(res);
		return getListofStudent;
	}

	public Response<StudentDto> getStudentById(int id) {
		res.setData(null);
		Response<StudentDto> getStudent = new Response<StudentDto>();
		Optional<Student> std = this.repository.findById(id);
		Result<StudentDto> studentResult = new Result<>();
		if (!std.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_STUDENT_MATCH_WITH_ID, HttpStatusCode.NO_STUDENT_MATCH_WITH_ID.getMessage(), res);
		}
		
//		ClassDetail classDetail = classRepository.getReferenceById(studentDto.getClassId());
		StudentDto student=studentMapper.entityToDto(std.get());
		student.setClassId(id);
//		existingStudent.setClassId(studentDto.getClassId());
//		student.setClassDetail(classDetail);
		
		
		studentResult.setData(student);
		getStudent.setStatusCode(200);
		getStudent.setResult(studentResult);
		return getStudent;
	}

	@Override
	public Response<StudentDto> deleteById(int id) {
		res.setData(null);
		Optional<Student> student = repository.findById(id);
		
		
		
		
		if (!student.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		
			
		repository.deleteById(id);
		Response<StudentDto> response = new Response<>();
		response.setMessage(HttpStatusCode.STUDENT_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.STUDENT_DELETED.getCode());
		response.setResult(new Result<StudentDto>(studentMapper.entityToDto(student.get())));
		return response;
	}

	public Response<StudentDto> updateStudent(StudentDto studentDto) {
		res.setData(null);
		Optional<Student> existingStudentContainer = repository.findById(studentDto.getStudentId());
		if (!existingStudentContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_STUDENT_FOUND.getCode(), HttpStatusCode.NO_STUDENT_FOUND,
					HttpStatusCode.NO_STUDENT_FOUND.getMessage(), res);
		}
		StudentDto existingStudent = studentMapper.entityToDto(existingStudentContainer.get());
		existingStudent.setStudentName(studentDto.getStudentName());
		existingStudent.setStudentStatus(studentDto.isStudentStatus());
		existingStudent.setCategory(studentDto.getCategory());
		existingStudent.setFatherName(studentDto.getFatherName());
		existingStudent.setFatherOccupation(studentDto.getFatherOccupation());
		existingStudent.setMotherName(studentDto.getMotherName());
		existingStudent.setMotherOccupation(studentDto.getMotherOccupation());
		existingStudent.setGender(studentDto.getGender());
		existingStudent.setJoiningDate(studentDto.getJoiningDate());
		existingStudent.setStatus(studentDto.getStatus());
		existingStudent.setVerifiedByTeacher(studentDto.getVerifiedByTeacher());
		existingStudent.setVerifiedByPrincipal(studentDto.getVerifiedByPrincipal());
		existingStudent.setVerifiedByRegion(studentDto.getVerifiedByRegion());
		ClassDetail classDetail = classRepository.getReferenceById(studentDto.getClassId());
		Student student=studentMapper.dtoToEntity(existingStudent);		
		existingStudent.setClassId(studentDto.getClassId());
		student.setClassDetail(classDetail);
		Student updateStudent = repository.save(student);
		Response<StudentDto> response = new Response<>();
		response.setMessage(HttpStatusCode.STUDENT_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.STUDENT_UPDATED.getCode());
		response.setResult(new Result<>(studentMapper.entityToDto(updateStudent)));
		return response;
	}
	
	@Override
	public Response<StudentDto> changeActiveStatusToTrue(int id) {
		
		
		res.setData(null);
		Response<StudentDto> response = new Response<>();
		
		
		if (this.getStudentById(id).getResult().getData() == null) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		
		Student student = repository.getById(id);
		student.setIsActivate(true);
		Student updateStudent = repository.save(student);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<StudentDto>(studentMapper.entityToDto(updateStudent)));
		return response;
		
	}

	@Override
	public Response<StudentDto> changeActiveStatusToFalse(int id) {
		res.setData(null);
		Response<StudentDto> response = new Response<>();
		
		if (this.getStudentById(id).getResult().getData() == null) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		
		Student student = repository.getById(id);
		student.setIsActivate(false);
		Student updateStudent = repository.save(student);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<StudentDto>(studentMapper.entityToDto(updateStudent)));
		return response;

	}

	@Override
	public Response<StudentDto> changeCurrentStatusToPromoted(int id) {

		res.setData(null);
		Response<StudentDto> response = new Response<>();

		if (this.getStudentById(id).getResult().getData() == null) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}

		Student student = repository.getById(id);
		student.setCurrentStatus("Promoted");
		Student updateStudent = repository.save(student);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<StudentDto>(studentMapper.entityToDto(student)));

		return response;
	}

	@Override
	public Response<StudentDto> changeCurrentStatusToDemoted(int id) {
		res.setData(null);
		Response<StudentDto> response = new Response<>();

		if (this.getStudentById(id).getResult().getData() == null) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}

		Student student = repository.getReferenceById(id);
		student.setCurrentStatus("Demoted");
		Student updateStudent = repository.save(student);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<StudentDto>(studentMapper.entityToDto(student)));

		return response;
	}

	@Override
	public Response<List<StudentDto>> findByGenderAndCategoryAndMinority(String gender, String category,
			String minority) {
		res.setData(null);
		List<Student> student = repository.findByGenderAndCategoryAndMinority(gender, category, minority);
		if (student.size() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		Response<List<StudentDto>> getListofStudent = new Response<>();
		getListofStudent.setStatusCode(200);
		getListofStudent.setResult(new Result<>(studentMapper.entitiesToDtos(student)));
		return getListofStudent;
	}
	
	

}
