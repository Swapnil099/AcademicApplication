
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

import com.ubi.academicapplication.entity.User;

public class UserContactInfoCsvHelper {


	public static ByteArrayInputStream userCSV(List<User> users) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		int count=0;
		List<String> data;
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);)
		{
			List<String> header = new ArrayList<>();
			header.add("User Id");
			header.add("User isEnabled");
			header.add("User Name");

			header.add("Role Id");
			header.add("Role Name");
			header.add("Role Type");

			header.add("ContactInfo Id");
			header.add("First Name");
			header.add("Middle Name");
			header.add("Last Name");
			header.add("Aadhar Card Number");
			header.add("Address");
			header.add("Age");
			header.add("Blood Group");
			header.add("Contact Number");
			header.add("Email");
			header.add("Gender");
			header.add("DOB");
			header.add("City");
			header.add("State");
			header.add("Nationality");

			csvPrinter.printRecord(header);
			for (User user : users) {
				int m=1;

				data = new ArrayList<>();
				//user data
				String Id = String.valueOf(user.getId());  
				data.add(Id);
				String isEnabled=user.getIsEnabled().toString();
				data.add(isEnabled);
				data.add(user.getUsername());

				//role data
				String roleId=String.valueOf(user.getRole().getId());
				data.add(roleId);
				data.add(user.getRole().getRoleName());
				data.add(user.getRole().getRoleType());

				//contact Info
				String contactId=String.valueOf(user.getContactInfo().getContactInfoId());
				data.add(contactId);
				data.add(user.getContactInfo().getFirstName());
				data.add(user.getContactInfo().getMiddleName());
				data.add(user.getContactInfo().getLastName());
				data.add(user.getContactInfo().getAadharCardNumber());
				data.add(user.getContactInfo().getAddress());
				data.add(user.getContactInfo().getAge());
				data.add(user.getContactInfo().getBloodGroup());
				data.add(user.getContactInfo().getContactNumber());
				data.add(user.getContactInfo().getEmail());
				data.add(user.getContactInfo().getGender());
				String dob=String.valueOf(user.getContactInfo().getDob());
				data.add(dob);
				data.add(user.getContactInfo().getCity());
				data.add(user.getContactInfo().getState());
				data.add(user.getContactInfo().getNationality());


				count +=m;
				csvPrinter.printRecord(data);

			}
			csvPrinter.printRecord("Total Users : "+count);
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data: " + e.getMessage());
		}
	}

}
