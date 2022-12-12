//package com.ubi.academicapplication.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Rule;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.rules.ExpectedException;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import com.ubi.academicapplication.dto.responsedto.Response;
//import com.ubi.academicapplication.entity.Student;
//import com.ubi.academicapplication.repository.StudentRepository;
//
//
//
//class StudentServiceImplTest {
//
//	@Mock
//	private StudentRepository studentRepository;
//
//	@InjectMocks
//	private StudentServiceImpl studentService;
//	
//	Student tempStudent = new Student();
//	
//	@Rule
//	public ExpectedException expectedEx = ExpectedException.none();
//
////	@BeforeAll
////	void setUp() {
////		Student student = new Student();
////		user.setStudentName("prashik");
////	}
//	
//
////	@Test
////	public void shouldThrowRuntimeExceptionWhenEmployeeIDisNull() throws Exception {
////		
////	
////  when(studentRepository.findNextSerialNo((String) any(), (String) any())).thenThrow(new IllegalStateException());
////when(configurationService.getSubscriberId()).thenReturn(Optional.of("42"));
////assertThrows(IllegalStateException.class, () -> cRBFileService.generateXmlFileName("File Type"));
////verify(cRBFileRepository).findNextSerialNo((String) any(), (String) any());
////verify(configurationService).getSubscriberId();
////	}
//
//	@Test
//	void testSaveStudent() {
//		tempStudent.setStudentName("prashik");
//		when(studentRepository.save(tempStudent)).thenReturn(tempStudent);
//		Response<Student> tempUser = studentService.saveStudent(tempStudent);
//		assertEquals(tempUser.getResult(), tempStudent.getStudentName());
//
//	}
//
////	@Test
////	void testGetStudents() {
////		int pageNumber = 0;
////		int pageSize = 1;
////		Pageable pageable = PageRequest.of(pageNumber, pageSize);
////		Student student = new Student(1, "omkar", false, null, null, null, null, null, null, null, null, null, null,
////				null);
////		Page<Student> students = new PageImpl<>(Collections.singletonList(student));
////		when(studentRepository.findAll(pageable)).thenReturn(students);
////		List<Student> studentsFromService = (List<Student>) studentService.getStudents();
////		List<Student> list = new ArrayList<Student>();
////		list.add(new Student(1, "omkar", false, null, null, null, null, null, null, null, null, null, null, null));
////		assertEquals(studentsFromService, list);
////
////	}
//
//	@Test
//	void testGetStudentById() {
//		tempStudent.setStudentId(1);
//		Optional<Student> student = Optional.of(tempStudent);
//		when(studentRepository.findById(1)).thenReturn(student);
//		Response<Student> studentFromService = studentService.getStudentById(1);
//		assertEquals(studentFromService, student);
//	}
//
////	@Test
////	void testDeleteStudent() {
////		tempStudent.setStudentId(1);
////		when(studentRepository.findById(tempStudent.getStudentId())).thenReturn(Optional.of(tempStudent));
////		studentService.deleteStudent(tempStudent.getStudentId());
////		doNothing().when(studentRepository).deleteById(Mockito.anyInt());
////		 assertEquals(1, tempStudent.getStudentId());  
////
////	}
//
////	@Test
////	void testUpdateStudent() {
////		tempStudent.setStudentId(1);
////		tempStudent.setStudentName("prashik");
////		Optional<Student> tempstudent = Optional.of(tempStudent);
////		when(studentRepository.findById(1)).thenReturn(tempstudent);
////		when(studentRepository.save(tempStudent)).thenReturn(tempStudent);
////		Student studentFromService = studentService.updateStudent(tempStudent);
////		assertEquals(studentFromService, tempStudent);
////	}
//}
