package com.ubi.academicapplication.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Payment_Details")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "paymentDate")
	private LocalDate date;

	@Column(name = "totalFees")
	private Double totalFees;

	@Column(name = "paidFees")
	private Double paidFees;

	@Column(name = "balanceFees")
	private Double balanceFees;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "dueDate")
	private LocalDate dueDate;

	@Column(name = "paymentDescription")
	private String description;

	@Column(name = "paymentRemark")
	private String remark;

	@Column(name = "paymentStatus")
	private boolean status;
	
	
}
