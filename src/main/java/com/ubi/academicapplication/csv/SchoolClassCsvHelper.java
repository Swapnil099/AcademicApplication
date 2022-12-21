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
import org.springframework.beans.factory.annotation.Autowired;

import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.entity.School;
import com.ubi.academicapplication.mapper.SchoolMapper;

public class SchoolClassCsvHelper {

	@Autowired
	static SchoolMapper schoolMapper;

	public static ByteArrayInputStream regionCSV(List<School> school) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		int count = 0;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);)
 {

			List<String> header = new ArrayList<>();
			header.add("School Id");
			header.add("School Code");
			header.add("School Name");
			header.add("School Email");
			header.add("School Contact");
			header.add("School Address");
			header.add("School Type");
			header.add("School Strength");
			header.add("School Shift");
			header.add("School Exemption Flag");
			header.add("School VvnAccount");
			header.add("School VvnFund");
			header.add("Class Id");
			header.add("Class Code");
			header.add("Class Name");
			
			csvPrinter.printRecord(header);
			
			
			for (School schools : school) {
				
	
				List<String> data = new ArrayList<>();

				data.add(String.valueOf(schools.getSchoolId()));
				data.add(String.valueOf(schools.getCode()));
				data.add(schools.getName());
				data.add(schools.getEmail());
				data.add(String.valueOf(schools.getContact()));
				data.add(schools.getAddress());
				data.add(schools.getType());
				data.add(String.valueOf(schools.getStrength()));
				data.add(String.valueOf(schools.isExemptionFlag()));
				data.add(String.valueOf(schools.getVvnAccount()));
				data.add(String.valueOf(schools.getVvnFund()));
				
				if(schools.getClassDetail().size() == 0) csvPrinter.printRecord(data);
				
				boolean flag = true;
				for(ClassDetail classDetail : schools.getClassDetail()) {
					if(flag) {
						data.add(String.valueOf(String.valueOf(classDetail.getClassId())));
						data.add(classDetail.getClassCode());
						data.add(classDetail.getClassName());
						flag = false;
					}
					else {
						data.clear();
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						data.add("");
						
						data.add(String.valueOf(classDetail.getClassId()));
						data.add(classDetail.getClassCode());
						data.add(classDetail.getClassName());
						
					}
					
					csvPrinter.printRecord(data);

				}
				count += 1;
			}
			csvPrinter.printRecord("Count: " + count);
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data: " + e.getMessage());
		}
	}
		
}
