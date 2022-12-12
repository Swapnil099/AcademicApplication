package com.ubi.academicapplication.dto.paymentdto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	
	private int id;

	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate date;

	
	private Double totalFees;


	private Double paidFees;


	private Double balanceFees;

	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dueDate;

	
	private String description;

	
	private String remark;

	
	private boolean status;

}
