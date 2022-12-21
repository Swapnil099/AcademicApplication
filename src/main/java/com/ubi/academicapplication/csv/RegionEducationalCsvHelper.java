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

import com.ubi.academicapplication.entity.EducationalInstitution;
import com.ubi.academicapplication.entity.Region;

public class RegionEducationalCsvHelper {

	public static ByteArrayInputStream regionCSV(List<Region> region) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		int count = 0;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			List<String> header = new ArrayList<>();
			header.add("Region Id");
			header.add("Region Code");
			header.add("Region Name");
			header.add("Educational Institute Id");
			header.add("Educational Institution Code");
			header.add("Educational Institution Name");
			header.add("Educational Institution Type");
			header.add("Educational Institution Strength");
			header.add("Educational Institution State");
			header.add("Educational Institution ExemptionFlag");
			header.add("Educational Institution VvnAccount");
			csvPrinter.printRecord(header);
			for (Region regions : region) {
				int m = 1;
				List<String> data = new ArrayList<String>();
				String regionId = String.valueOf(regions.getId());
				data.add(regionId);
				data.add(regions.getCode());
				data.add(regions.getName());

				for (EducationalInstitution eduInsti : regions.getEducationalInstitiute()) {

					data.add(String.valueOf(eduInsti.getId()));
					data.add(eduInsti.getEducationalInstitutionCode());
					data.add(eduInsti.getEducationalInstitutionName());
					data.add(eduInsti.getEducationalInstitutionType());
					data.add(String.valueOf(eduInsti.getStrength()));
					data.add(eduInsti.getState());
					data.add(eduInsti.getExemptionFlag());
					data.add(String.valueOf(eduInsti.getVvnAccount()));
				}
				count += m;
				csvPrinter.printRecord(data);
			}
			csvPrinter.printRecord("Count: " + count);
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data: " + e.getMessage());
		}
	}
}
