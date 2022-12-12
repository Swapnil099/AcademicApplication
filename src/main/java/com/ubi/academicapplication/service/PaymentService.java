package com.ubi.academicapplication.service;

import java.util.List;


import com.ubi.academicapplication.dto.paymentdto.PaymentDto;
import com.ubi.academicapplication.dto.responsedto.Response;

import com.ubi.academicapplication.entity.Payment;

public interface PaymentService {

	Response<PaymentDto> makePayment(PaymentDto paymentDto);

	Response<PaymentDto> getSingle(int id);

	Response<List<PaymentDto>> getAllPayment(Integer PageNumber, Integer PageSize);
	
	Response<PaymentDto> updatePay(PaymentDto pay);

	public Response<PaymentDto> deletePayment(int id);

	

}
