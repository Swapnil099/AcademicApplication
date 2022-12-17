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
		int count = 0;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

			List<String> header = new ArrayList<>();
			header.add("Educational Institute Id");
			header.add("Educational Institution Code");
			header.add("Educational Institution Name");
			header.add("Educational Institution Type");
			header.add("Educational Institution State");
			header.add("Educational Institution ExemptionFlag");
			header.add("Educational Institution Strength");
			header.add("Educational Institution VvnAccount");
			header.add("Region Id");
			header.add("Region Code");
			header.add("Region Name");
			csvPrinter.printRecord(header);
			for (EducationalInstitution eduInsts : eduInst) {
				int m = 1;

				List<String> data = new ArrayList<>();

				data.add(String.valueOf(eduInsts.getId()));
				data.add(eduInsts.getEducationalInstitutionCode());
				data.add(eduInsts.getEducationalInstitutionName());
				data.add(eduInsts.getEducationalInstitutionType());
				data.add(eduInsts.getState());
				data.add(eduInsts.getExemptionFlag());
				data.add(String.valueOf(eduInsts.getStrength()));
				data.add(String.valueOf(eduInsts.getVvnAccount()));

				for (Region reg : eduInsts.getRegion()) {

					data.add(String.valueOf(reg.getId()));
					data.add(reg.getCode());
					data.add(reg.getName());

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
