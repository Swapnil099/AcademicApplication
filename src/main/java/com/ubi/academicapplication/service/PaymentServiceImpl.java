package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Payment;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentRepository paymentRepository;
	
//	@Autowired
//	private ModelMapper modelMapper;
//	
    @Autowired
	private Result res;
    
    
	@Override
	public Response<Payment> makePayment(Payment payment) {
		
		Response<Payment> response = new Response<>();
		  System.out.println(payment);
//		  if(paymentRepository.findById(payment.getId()) != null){
//	            throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(),HttpStatusCode.NO_PAYMENT_FOUND, HttpStatusCode.NO_PAYMENT_FOUND.getMessage(),res);
//		  }
		  Payment savePayment = paymentRepository.save(payment);
		  response.setStatusCode(200);
		  response.setMessage("Payment saved SuccessFully..!!");
			return response;
	
	}
	
	
	@Override
	public Response<Payment> getSingle(int id) {
		Response<Payment> getPayment = new Response<Payment>();
		Optional<Payment> pay = null;
		pay = this.paymentRepository.findById(id);
		Result<Payment> paymentResult = new Result<>();
		if (!pay.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID, HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID.getMessage(), null);
		}
		paymentResult.setData(pay.get());
		getPayment.setStatusCode(200);
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
		getListofPayment.setStatusCode(200);
		getListofPayment.setResult(allPaymentResult);
		return getListofPayment;
	}
	
	
	@Override
	public void deletePayment(int id) {
		
		try {
			Response<Payment> deleteStd=new Response<Payment>();
			paymentRepository.deleteById(id);
			
		} catch (IllegalArgumentException e) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(), HttpStatusCode.NO_PAYMENT_FOUND,
					HttpStatusCode.NO_PAYMENT_FOUND.getMessage(), res);
		} catch (Exception e) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(), HttpStatusCode.NO_PAYMENT_FOUND,
					HttpStatusCode.NO_PAYMENT_FOUND.getMessage(), res);

		}	

	}

	
	@Override
	public Payment updatePay(Payment pay) {


		return this.paymentRepository.save(pay);
	}
	








	



}
