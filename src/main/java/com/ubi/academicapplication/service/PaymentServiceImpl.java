package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import com.ubi.academicapplication.dto.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.ubi.academicapplication.dto.paymentdto.PaymentDto;
import com.ubi.academicapplication.entity.Payment;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.PaymentMapper;
import com.ubi.academicapplication.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentMapper paymentMapper;

	@Override
	public Response<PaymentDto> makePayment(PaymentDto paymentDto) {

		Result<PaymentDto> res = new Result<>();

		Response<PaymentDto> response = new Response<>();
		Optional<Payment> tempPayment = paymentRepository.findById(paymentDto.getId());
		if (tempPayment.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(), HttpStatusCode.NO_PAYMENT_FOUND,
					HttpStatusCode.NO_PAYMENT_FOUND.getMessage(), res);
		}
		Payment savePayment = paymentRepository.save(paymentMapper.dtoToEntity(paymentDto));
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<PaymentDto>(paymentMapper.entityToDto(savePayment)));
		return response;

	}

	@Override
	public Response<PaymentDto> getSingle(int id) {
		Response<PaymentDto> getPayment = new Response<PaymentDto>();
		Optional<Payment> pay = null;
//		res.setData(pay);
		pay = this.paymentRepository.findById(id);
		Result<PaymentDto> paymentResult = new Result<>();
		if (!pay.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID, HttpStatusCode.NO_PAYMENT_MATCH_WITH_ID.getMessage(),
					paymentResult);
		}
		paymentResult.setData(paymentMapper.entityToDto(pay.get()));
		getPayment.setStatusCode(HttpStatusCode.PAYMENT_RETRIVED_SUCCESSFULLY.getCode());
		getPayment.setMessage(HttpStatusCode.PAYMENT_RETRIVED_SUCCESSFULLY.getMessage());
		getPayment.setResult(paymentResult);
		return getPayment;
	}

	@Override
	public Response<List<PaymentDto>> getAllPayment(Integer PageNumber, Integer PageSize) {
		Result<List<PaymentDto>> allPaymentResult = new Result<>();
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<PaymentDto>> getListofPayment = new Response<List<PaymentDto>>();
		// List<Payment> list = (List<Payment>) this.paymentRepository.findAll();
		Page<Payment> list = this.paymentRepository.findAll(paging);
		List<PaymentDto> paymentDtos = paymentMapper.entitiesToDtos(list.toList());
		// res.setData(list.toList());
		// Result<List<Payment>> allPaymentResult = new Result<>();
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(), HttpStatusCode.NO_PAYMENT_FOUND,
					HttpStatusCode.NO_PAYMENT_FOUND.getMessage(), allPaymentResult);
		}
		allPaymentResult.setData(paymentDtos);
		getListofPayment.setStatusCode(HttpStatusCode.PAYMENT_RETRIVED_SUCCESSFULLY.getCode());
		getListofPayment.setMessage(HttpStatusCode.PAYMENT_RETRIVED_SUCCESSFULLY.getMessage());
		getListofPayment.setResult(allPaymentResult);
		return getListofPayment;
	}

	@Override
	public Response<PaymentDto> deletePayment(int id) {
		Result<PaymentDto> res = new Result<>();
		res.setData(null);
		Optional<Payment> payment = paymentRepository.findById(id);
		if (!payment.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		paymentRepository.deleteById(id);
		Response<PaymentDto> response = new Response<>();
		response.setMessage(HttpStatusCode.PAYMENT_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.PAYMENT_DELETED.getCode());
		response.setResult(new Result<PaymentDto>(paymentMapper.entityToDto(payment.get())));
		return response;

	}

	@Override
	public Response<PaymentDto> updatePay(PaymentDto pay) {
		Result<PaymentDto> res = new Result<>();

		res.setData(null);
		Optional<Payment> existingPaymentContainer = paymentRepository.findById(pay.getId());
		if (!existingPaymentContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(), HttpStatusCode.NO_PAYMENT_FOUND,
					HttpStatusCode.NO_PAYMENT_FOUND.getMessage(), res);
		}
		PaymentDto existingPayment = paymentMapper.entityToDto(existingPaymentContainer.get());
		existingPayment.setDate(pay.getDate());
		existingPayment.setTotalFees(pay.getTotalFees());
		existingPayment.setPaidFees(pay.getPaidFees());
		existingPayment.setBalanceFees(pay.getBalanceFees());
		existingPayment.setDescription(pay.getDescription());
		existingPayment.setRemark(pay.getRemark());
		existingPayment.setStatus(pay.isStatus());

		Payment updatePayment = paymentRepository.save(paymentMapper.dtoToEntity(existingPayment));
		Response<PaymentDto> response = new Response<>();
		response.setMessage(HttpStatusCode.PAYMENT_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.PAYMENT_UPDATED.getCode());
		response.setResult(new Result<>(paymentMapper.entityToDto(updatePayment)));
		return response;
	}

}
