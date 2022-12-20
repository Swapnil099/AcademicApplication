package com.ubi.academicapplication.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class_data ")
@ToString
public class ClassDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ClassId")
	private int classId;

	@Column(name = "ClassCode")
	private String classCode;

	@Column(name = "ClassName")
	private String className;

	@OneToMany(mappedBy = "classDetail")	
	List<Student> students = new ArrayList<>();

}




