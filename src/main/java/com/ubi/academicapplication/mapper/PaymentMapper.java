package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.paymentdto.PaymentDto;
import com.ubi.academicapplication.entity.Payment;

@Component
public class PaymentMapper {
	
	ModelMapper modelMapper= new ModelMapper();
	 
	//entity to DTO Mapping
// public PaymentDto entityToDto(Payment payment) {
//		PaymentDto paymentDto =  modelMapper.map(payment, PaymentDto.class);
//		paymentDto.setDate(payment.getDate().toString());
//		paymentDto.setDueDate(payment.getDueDate().toString());
//		return paymentDto;
//	}
	
	 public PaymentDto entityToDto(Payment payment) {
			return modelMapper.map(payment, PaymentDto.class);
		}
 
 public List<PaymentDto> entitiesToDtos(List<Payment> payment) {
        return payment.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
    }
 
 

	// DTO to entity Mapping
	public Payment dtoToEntity(PaymentDto paymentDto) {
		return modelMapper.map(paymentDto, Payment.class);
	}
	
    public List<Payment> dtosToEntities(List<PaymentDto> paymentDtos) {
        return paymentDtos.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
    }


}
