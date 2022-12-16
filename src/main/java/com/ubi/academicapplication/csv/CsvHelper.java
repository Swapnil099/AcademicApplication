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

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.entity.EducationalInstitution;
import com.ubi.academicapplication.entity.Region;
import com.ubi.academicapplication.mapper.EducationalInstitutionMapper;


public class CsvHelper {
	
	@Autowired
	static EducationalInstitutionMapper educationalInstitutionMapper;
	
	public static ByteArrayInputStream regionCSV(List<Region> region) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
	    int count=0;
	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (Region regions : region) {
	    	  int m=1;
	    	 
	        List<String> data = new ArrayList<String>();
	        String regionId = String.valueOf(regions.getId());
	        System.out.println(regionId);
	        data.add(regionId);
	        data.add(regions.getCode());
	        data.add(regions.getName());
	     
	        for(EducationalInstitution eduInsti:regions.getEducationalInstitiute()) {	
	        	data.add(eduInsti.toString());
	        }
	        count +=m;
	        csvPrinter.printRecord(data);
	        
	      }
	     csvPrinter.printRecord(count);
	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data: " + e.getMessage());
	    }
	}
}
	
