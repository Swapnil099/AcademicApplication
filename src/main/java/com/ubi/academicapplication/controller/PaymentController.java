package com.ubi.academicapplication.controller;

import java.util.List;


import com.ubi.academicapplication.dto.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.paymentdto.PaymentDto;

import com.ubi.academicapplication.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	@Operation(summary = "Create New Payment", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping
	public ResponseEntity<Response<PaymentDto>> insertPayment(@RequestBody PaymentDto paymentDto) { // NOSONAR

		Response<PaymentDto> response = this.paymentService.makePayment(paymentDto);

		return ResponseEntity.ok().body(response);

	}

	@Operation(summary = "Get Payment By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{id}")
	public ResponseEntity<Response<PaymentDto>> getSinglePayment(@PathVariable int id) {
		Response<PaymentDto> response = paymentService.getSingle(id);
		if (response.getStatusCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@Operation(summary = "Get All Payment", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping()
	public ResponseEntity<Response<List<PaymentDto>>> getPayments(
			@RequestParam(value = "PageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "PageSize", defaultValue = "5", required = false) Integer pageSize) {
		Response<List<PaymentDto>> response = paymentService.getAllPayment(pageNumber, pageSize);
		if (response.getStatusCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	


	@Operation(summary = "Delete Payment By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<PaymentDto>> deletePaymentById(@PathVariable("id") int id) {

		Response<PaymentDto> response = this.paymentService.deletePayment(id);

		return ResponseEntity.ok().body(response);

	}
	
	


	@Operation(summary = "Update Payment with Id", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping
	public ResponseEntity<Response<PaymentDto>> updatePayment(@RequestBody PaymentDto pay) { // NOSONAR

		Response<PaymentDto> updatepayment = this.paymentService.updatePay(pay);

		return ResponseEntity.ok().body(updatepayment);

	}

}
