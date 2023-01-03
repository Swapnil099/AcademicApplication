package com.ubi.academicapplication.csv;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.entity.School;
import com.ubi.academicapplication.entity.Student;

import io.jsonwebtoken.lang.Arrays;

@Service
public class StudentCSVHelper {

	public static ByteArrayInputStream StudentToCSV(List<Student> students) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);	    
	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	    	List<String> header = new ArrayList<>();
			header.add("student Id");
			header.add("student Name");
			header.add("dateOfBirth");
			header.add("student Status");
			header.add("category");
			header.add("minority");
			header.add("fatherName");
			header.add("father Occupation");			
			header.add("mother Name");
			header.add("mother Occupation");
			header.add("Gender");
			header.add("joining Date");
			header.add("status");			
			header.add("verified By Teacher");
			header.add("verified By Principal");
		
			header.add("isActivate");
			header.add("currentStatus");
			header.add("classId");
			csvPrinter.printRecord(header);
	    	
	      for (Student student : students) {
	        List<String> data =new ArrayList<>();
	        data.add(String.valueOf(student.getStudentId()));
	             data.add(String.valueOf( student.getStudentName()));
	             data.add(String.valueOf(student.getDateOfBirth()));
	             data.add(String.valueOf(student.getStatus()));
	             data.add(String.valueOf(student.getCategory()));
	             data.add(String.valueOf(student.getMinority()));
	             data.add(String.valueOf(student.getFatherName()));
	             data.add(String.valueOf(student.getFatherOccupation()));
	             data.add(String.valueOf(student.getMotherName()));
	             data.add(String.valueOf(student.getMotherOccupation()));
	             data.add(String.valueOf(student.getGender()));
	             data.add(String.valueOf(student.getJoiningDate()));
	             data.add(String.valueOf(student.getStatus()));    
	             data.add(String.valueOf(student.getVerifiedByTeacher()));
	             data.add(String.valueOf(student.getVerifiedByPrincipal()));
	           
	             data.add(String.valueOf(student.getIsActivate()));
	             data.add(String.valueOf(student.getCurrentStatus()));

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	  }
}

//	public static ByteArrayInputStream regionCSV(List<Student> student) {
//		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//		int count = 0;
//		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out)
//, format);)
// {
//
//			List<String> header = new ArrayList<>();
//			header.add("student Id");
//			header.add("student Name");
//			header.add("dateOfBirth");
//			header.add("student Status");
//			header.add("category");
//			header.add("minority");
//			header.add("fatherName");
//			header.add("father Occupation");			
//			header.add("mother Name");
//			header.add("mother Occupation");
//			header.add("Gender");
//			header.add("joining Date");
//			header.add("status");			
//			header.add("verified By Teacher");
//			header.add("verified By Principal");
//			header.add("verified By Region");
//			header.add("isActivate");
//			header.add("currentStatus");
//			header.add("classId");
//			csvPrinter.printRecord(header);

//			
//			
//			for (Student students : student) {
//				
//	
//				List<String> data = new ArrayList<>();
//
//				data.add(String.valueOf(students.getStudentId()));
//				data.add(students.getName());
//				
//				if(students) csvPrinter.printRecord(data);
//				
//				boolean flag = true;
//				for (School school : students.getSchool()) {
//					if(flag) {
//						data.add(String.valueOf(school.getSchoolId()));
//						data.add(String.valueOf(school.getCode()));
//						data.add(school.getName());
//						data.add(school.getEmail());
//						data.add(String.valueOf(school.getContact()));
//						data.add(school.getAddress());
//						data.add(school.getType());
//						data.add(String.valueOf(school.getStrength()));
//						data.add(school.getShift());
//						data.add(String.valueOf(school.isExemptionFlag()));
//						data.add(String.valueOf(school.getVvnAccount()));
//						data.add(String.valueOf(school.getVvnFund()));
//						flag = false;
//					}
//					else {
//						data.clear();
//						data.add("");
//						data.add("");
//						data.add("");
//						
//						data.add(String.valueOf(school.getSchoolId()));
//						data.add(String.valueOf(school.getCode()));
//						data.add(school.getName());
//						data.add(school.getEmail());
//						data.add(String.valueOf(school.getContact()));
//						data.add(school.getAddress());
//						data.add(school.getType());
//						data.add(String.valueOf(school.getStrength()));
//						data.add(school.getShift());
//						data.add(String.valueOf(school.isExemptionFlag()));
//						data.add(String.valueOf(school.getVvnAccount()));
//						data.add(String.valueOf(school.getVvnFund()));
//					}
//					
//					csvPrinter.printRecord(data);
//
//				}
//				count += 1;
//
//			}
//			csvPrinter.printRecord("Count: " + count);
//			csvPrinter.flush();
//			return new ByteArrayInputStream(out.toByteArray());
//		} catch (IOException e) {
//			throw new RuntimeException("fail to import data: " + e.getMessage());
//		}
//	}
//
//}	
