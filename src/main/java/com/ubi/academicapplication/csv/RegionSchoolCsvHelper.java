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

import com.ubi.academicapplication.entity.Region;
import com.ubi.academicapplication.entity.School;
import com.ubi.academicapplication.mapper.RegionMapper;

public class RegionSchoolCsvHelper {

	
	@Autowired
	static RegionMapper regionMapper;

	public static ByteArrayInputStream regionSchoolCSV(List<Region> region) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		int count = 0;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out)
, format);)
 {

			List<String> header = new ArrayList<>();
			header.add("Region Id");
			header.add("Region Code");
			header.add("Region Name");
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
			
			csvPrinter.printRecord(header);
			
			
			for (Region regions : region) {
				
	
				List<String> data = new ArrayList<>();

				data.add(String.valueOf(regions.getId()));
				data.add(regions.getCode());
				data.add(regions.getName());
				
				if(regions.getSchool().size() == 0) csvPrinter.printRecord(data);
				
				boolean flag = true;
				for (School school : regions.getSchool()) {
					if(flag) {
						data.add(String.valueOf(school.getSchoolId()));
						data.add(String.valueOf(school.getCode()));
						data.add(school.getName());
						data.add(school.getEmail());
						data.add(String.valueOf(school.getContact()));
						data.add(school.getAddress());
						data.add(school.getType());
						data.add(String.valueOf(school.getStrength()));
						data.add(school.getShift());
						data.add(String.valueOf(school.isExemptionFlag()));
						data.add(String.valueOf(school.getVvnAccount()));
						data.add(String.valueOf(school.getVvnFund()));
						flag = false;
					}
					else {
						data.clear();
						data.add("");
						data.add("");
						data.add("");
						
						data.add(String.valueOf(school.getSchoolId()));
						data.add(String.valueOf(school.getCode()));
						data.add(school.getName());
						data.add(school.getEmail());
						data.add(String.valueOf(school.getContact()));
						data.add(school.getAddress());
						data.add(school.getType());
						data.add(String.valueOf(school.getStrength()));
						data.add(school.getShift());
						data.add(String.valueOf(school.isExemptionFlag()));
						data.add(String.valueOf(school.getVvnAccount()));
						data.add(String.valueOf(school.getVvnFund()));
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


