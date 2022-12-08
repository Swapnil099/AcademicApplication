package com.ubi.academicapplication.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Payment;
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
	public ResponseEntity<Response<Payment>> insertPayment(@RequestBody Payment payment) { // NOSONAR

		Response<Payment> response = this.paymentService.makePayment(payment);

		return ResponseEntity.ok().body(response);

	}

	@Operation(summary = "Get Payment By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{id}")
	public ResponseEntity<Response<Payment>> getSinglePayment(@PathVariable int id) {
		Response<Payment> response = paymentService.getSingle(id);
		if (response.getStatusCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@Operation(summary = "Get All Payment", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping()
	public ResponseEntity<Response<List<Payment>>> getPayments() {
		Response<List<Payment>> response = paymentService.getAllPayment();
		if (response.getStatusCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}

	@Operation(summary = "Delete Payment By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Payment>> deletePaymentById(@PathVariable("id") int id) {

		Response<Payment> response=this.paymentService.deletePayment(id);
		
		return ResponseEntity.ok().body(response);

	}

	@Operation(summary = "Update Payment with Id", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping
	public ResponseEntity<Response<Payment>> updatePayment(@RequestBody Payment pay) { // NOSONAR

		Response<Payment> updatepayment = this.paymentService.updatePay(pay);

		return ResponseEntity.ok().body(updatepayment);

	}

}
