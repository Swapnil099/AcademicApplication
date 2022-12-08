package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Payment;
import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentRepository paymentRepository;
		
    @Autowired
	private Result res;
    
    
	@Override
	public Response<Payment> makePayment(Payment payment) {
		
		Response<Payment> response = new Response<>();
		Optional<Payment> tempPayment= paymentRepository.findById(payment.getId());
		res.setData(tempPayment);
		  if(tempPayment.isPresent()){
	            throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(),HttpStatusCode.NO_PAYMENT_FOUND, HttpStatusCode.NO_PAYMENT_FOUND.getMessage(),res);
		  }
		  Payment savePayment = paymentRepository.save(payment);
		  response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		  response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		  response.setResult(new Result<Payment>(savePayment));
			return response;
	
	}
	
	
	@Override
	public Response<Payment> getSingle(int id) {
		Response<Payment> getPayment = new Response<Payment>();
		Optional<Payment> pay = null;
//		res.setData(pay);
		pay = this.paymentRepository.findById(id);
		Result<Payment> paymentResult = new Result<>();
		if (!pay.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID, HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID.getMessage(), paymentResult);
		}
		paymentResult.setData(pay.get());
		getPayment.setStatusCode(HttpStatusCode.PAYMENT_RETRIVED_SUCCESSFULLY.getCode());
		getPayment.setMessage(HttpStatusCode.PAYMENT_RETRIVED_SUCCESSFULLY.getMessage());
		getPayment.setResult(paymentResult);
		return getPayment;
	}

	@Override
	public Response<List<Payment>> getAllPayment() {
		
		Response<List<Payment>> getListofPayment = new Response<List<Payment>>();
		List<Payment> list = (List<Payment>) this.paymentRepository.findAll();
		res.setData(list);
		Result<List<Payment>> allPaymentResult = new Result<>();
		if (list.size() == 0) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(), HttpStatusCode.NO_PAYMENT_FOUND,
					HttpStatusCode.NO_PAYMENT_FOUND.getMessage(), res);
		}
		allPaymentResult.setData(list);
		getListofPayment.setStatusCode(HttpStatusCode.PAYMENT_RETRIVED_SUCCESSFULLY.getCode());
		getListofPayment.setMessage(HttpStatusCode.PAYMENT_RETRIVED_SUCCESSFULLY.getMessage());
		getListofPayment.setResult(allPaymentResult);
		return getListofPayment;
	}
	
	
	@Override
	public Response<Payment> deletePayment(int id) {
		
		res.setData(null);
		Optional<Payment> payment = paymentRepository.findById(id);
		if (!payment.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		paymentRepository.deleteById(id);
		Response<Payment> response = new Response<>();
		response.setMessage(HttpStatusCode.PAYMENT_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.PAYMENT_DELETED.getCode());
		response.setResult(new Result<Payment>(payment.get()));
		return response;

	}

	
	@Override
	public Response<Payment> updatePay(Payment pay) {


		res.setData(null);
		Optional<Payment> existingPaymentContainer = paymentRepository.findById(pay.getId());
		if (!existingPaymentContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(), HttpStatusCode.NO_PAYMENT_FOUND,
					HttpStatusCode.NO_PAYMENT_FOUND.getMessage(), res);
		}
		Payment existingPayment = existingPaymentContainer.get();
		existingPayment.setDate(pay.getDate());
		existingPayment.setTotalFees(pay.getTotalFees());
		existingPayment.setPaidFees(pay.getPaidFees());
		existingPayment.setBalanceFees(pay.getBalanceFees());
		existingPayment.setDescription(pay.getDescription());
		existingPayment.setRemark(pay.getRemark());
		existingPayment.setStatus(pay.isStatus());
		

		Payment updatePayment = paymentRepository.save(existingPayment);
		Response<Payment> response = new Response<>();
		response.setMessage(HttpStatusCode.PAYMENT_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.PAYMENT_UPDATED.getCode());
		response.setResult(new Result<>(updatePayment));
		return response;
	}
	
	
}
	
