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

import com.ubi.academicapplication.entity.EducationalInstitution;
import com.ubi.academicapplication.entity.Region;
import com.ubi.academicapplication.mapper.EducationalInstitutionMapper;

public class EducationalInstitutionCsvHelper {
	
	@Autowired
	static EducationalInstitutionMapper educationalInstitutionMapper;
	
	public static ByteArrayInputStream educationCSV(List<EducationalInstitution> eduInst) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
	    int count=0;
	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (EducationalInstitution eduInsts : eduInst) {
	    	  int m=1;
	    	 
	        List<String> data = new ArrayList<>();
	        String eduId = String.valueOf(eduInsts.getId());
	        String eduStrength = String.valueOf(eduInsts.getStrength());
	        String eduAcc = String.valueOf(eduInsts.getVvnAccount());
	       
	        data.add(eduId);
	        data.add(eduInsts.getEducationalInstitutionCode());
	        data.add(eduInsts.getEducationalInstitutionName());
	        data.add(eduInsts.getEducationalInstitutionType());
	        data.add(eduInsts.getState());
	        data.add(eduInsts.getExemptionFlag());
	        data.add(eduStrength);
	        data.add(eduAcc);
	     
	        for(Region reg:eduInsts.getRegion()) {	
	        	String regId = String.valueOf(reg.getId());
	        	data.add(regId);
	        	data.add(reg.getCode());
	        	data.add(reg.getName());
	        	
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
