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

import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.entity.Student;


public class ClassCsvHelper {

	public static ByteArrayInputStream classCSV(List<ClassDetail> classDetail) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		int count=0;
		List<String> data;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);)
		{
			List<String> header = new ArrayList<>();
			header.add("Class Id");
			header.add("Class Code");
			header.add("Class Name");

			header.add("Student Id");
			header.add("Student Name");
			header.add("Category");
			header.add("Current status");
			header.add("Father Name");
			header.add("Father Occupation");
			header.add("Gender");
			header.add("Minority");
			header.add("Mother Name");
			header.add("Mother Occupation");
			header.add("Status");
			header.add("Date Of Birth");
			header.add("Is Activate");
			header.add("Joining Date");
			header.add("Verified By Principal");
			
			header.add("Verified By Teacher");


			csvPrinter.printRecord(header);
			for (ClassDetail classData : classDetail) {
				int m=1;

				data = new ArrayList<>();

				String Id = String.valueOf(classData.getClassId());  
				data.add(Id);
				data.add(classData.getClassCode());
				data.add(classData.getClassName());


				for (Student student : classData.getStudents()) {

					data.add(String.valueOf(student.getStudentId()));
					data.add(student.getStudentName());
					data.add(student.getCategory());
					data.add(student.getCurrentStatus());
					data.add(student.getFatherName());
					data.add(student.getFatherOccupation());
					data.add(student.getGender());
					data.add(student.getMinority());
					data.add(student.getMotherName());
					data.add(student.getMotherOccupation());
					data.add(student.getStatus());
					
				
					data.add(String.valueOf(student.getDateOfBirth()));
					data.add(String.valueOf(student.getIsActivate()));
					data.add(String.valueOf(student.getJoiningDate()));
					data.add(String.valueOf(student.getVerifiedByPrincipal()));
					data.add(String.valueOf(student.isVerifiedByTeacher()));

				}

				count +=m;
				csvPrinter.printRecord(data);

			}
			csvPrinter.printRecord("Total Class : "+count);
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data: " + e.getMessage());
		}
	}

}

