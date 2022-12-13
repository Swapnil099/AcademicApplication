package com.ubi.academicapplication.entity;

import java.time.LocalDate;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ContactInfo ")
public class ContactInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long contactInfoId;
	private String firstName;
	private String middleName;
	
	//@Column(name="lastname")
	private String lastName;
	
	@Email(message = "Please Enter a valid Email Address")
	private String Email;
	
	@Pattern(regexp  ="^\\d{10}$",message = "Please Enter a Valid Contact Number")
	private String contactNumber;
	
	private String city;
	private String State;
	private String address;
	private String age;
	private String gender;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dob;
	private String bloodGroup;
	
	@Pattern(regexp  ="^\\d{12}$",message = "Please Enter a Valid Aadhar Card Number")
	private String aadharCardNumber;
	private String nationality;


}

