package com.ubi.academicapplication.service;

import java.text.ParseException;

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


import com.ubi.academicapplication.dto.transfercertificate.TransferCertificateDto;

import com.ubi.academicapplication.entity.TransferCertificate;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.TransferCertificateMapper;
import com.ubi.academicapplication.repository.TransferCertificateRepository;


@Service

public class TransferCertificateServiceImpl implements TransferCertificateService {

	@Autowired
	private TransferCertificateRepository transferCertificateRepository;
	//@Autowired
	//Result res;

    Logger logger = LoggerFactory.getLogger(TransferCertificateServiceImpl.class);
    
    @Autowired
	private TransferCertificateMapper transferCertificateMapper;
 
 
    @Override
	public Response<TransferCertificateDto> addTransferCertificate(TransferCertificateDto transferCertificateDto) {

    	Result<TransferCertificateDto> res=new Result<>();
		Response<TransferCertificateDto> response = new Response<>();
		Optional<TransferCertificateDto> tempCertificate =Optional.empty();
		
	//	res.setData(null);
		  if(tempCertificate.isPresent()){
	            throw new CustomException(HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode(),HttpStatusCode.RESOURCE_ALREADY_EXISTS, HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage(),res);
		  }
		  TransferCertificate savedTransferCertificate = transferCertificateRepository.save(transferCertificateMapper.dtoToEntity(transferCertificateDto));
			response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
			response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
			response.setResult(new Result<TransferCertificateDto>(transferCertificateMapper.entityToDto(savedTransferCertificate)));
			return response;
	}
	

    
    
    
   /* @Override
	public Response<List<TransferCertificate>> getAllTransferCertificate(Integer PageNumber, Integer PageSize) {
		
    	res.setData(null);
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<TransferCertificate>> getListofTransferCertificate = new Response<>();
		Page<TransferCertificate> list = this.transferCertificateRepository.findAll(paging);
		res.setData(list.toList());
		Result<List<TransferCertificate>> result = new Result<>();
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}

		getListofTransferCertificate.setStatusCode(200);
		getListofTransferCertificate.setResult(res);
		return getListofTransferCertificate;
	} */
    @Override
    public Response<List<TransferCertificateDto>> getAllTransferCertificate(Integer PageNumber, Integer PageSize) {
    	Result<List<TransferCertificateDto>> res=new Result<>();
    	res.setData(null);
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<TransferCertificateDto>> getListofTransferCertificate = new Response<>();
		Page<TransferCertificate> list = this.transferCertificateRepository.findAll(paging);

		List<TransferCertificateDto> transferCertificateDtos =transferCertificateMapper.entitiesToDtos(list.toList());

		res.setData(transferCertificateDtos);
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		getListofTransferCertificate.setStatusCode(200);
		getListofTransferCertificate.setResult(res);
		return getListofTransferCertificate;
	}

    @Override
    public Response<TransferCertificateDto> getSingleTransferCertificate(int transferCertificateId) 
    {
    	Result<TransferCertificateDto> res=new Result<>();
    	res.setData(null);
    		Response<TransferCertificateDto> getTransferCertificate = new Response<TransferCertificateDto>();
    		Optional<TransferCertificate> tc = null;
    		tc = this.transferCertificateRepository.findById(transferCertificateId);
    		Result<TransferCertificateDto> transferCertificateResult = new Result<>();
    		if (!tc.isPresent()) {
    			throw new CustomException(HttpStatusCode.NO_STUDENT_MATCH_WITH_ID.getCode(),
    					HttpStatusCode.NO_STUDENT_MATCH_WITH_ID, HttpStatusCode.NO_STUDENT_MATCH_WITH_ID.getMessage(), res);
    		}
    		transferCertificateResult.setData(transferCertificateMapper.entityToDto(tc.get()));
    		getTransferCertificate.setStatusCode(200);
    		getTransferCertificate.setResult(transferCertificateResult);
    		return getTransferCertificate;
    	}
    	
    	
 	  /* Response<TransferCertificate> getTransferCertificate = new Response<TransferCertificate>();
 		Optional<TransferCertificate> transferCertificate = null;
 		transferCertificate = this.transferCertificateRepository.findById(transferCertificateId);
 		Result<TransferCertificate> transferCertificateResult = new Result<>();
 		if (!transferCertificate.isPresent()) {
 			throw new CustomException(HttpStatusCode.NO_TRANSFERCERTIFICATE_MATCH_WITH_ID.getCode(),
 					HttpStatusCode.NO_TRANSFERCERTIFICATE_MATCH_WITH_ID, HttpStatusCode.NO_TRANSFERCERTIFICATE_MATCH_WITH_ID.getMessage(), res);
 		}
 		transferCertificateResult.setData(transferCertificate.get());
 		getTransferCertificate.setStatusCode(HttpStatusCode.RETREIVED_SUCCESSFULLY.getCode());
 		getTransferCertificate.setMessage(HttpStatusCode.RETREIVED_SUCCESSFULLY.getMessage());
 		getTransferCertificate.setResult(transferCertificateResult);
 		return getTransferCertificate;*/
    

	

	@Override
	public Response<TransferCertificateDto> updateTransferCertificate(TransferCertificateDto transferCertificateDto) throws ParseException {
		Result<TransferCertificateDto> res=new Result<>();
		res.setData(null);
		//Response<TransferCertificate> response = new Response<>();
		Optional<TransferCertificate> existingTransferCertificateContainer = transferCertificateRepository.findById(transferCertificateDto.getId());
		if (!existingTransferCertificateContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getCode(), HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND,
					HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getMessage(), res);
		}
     	TransferCertificateDto existingTransferCertificate =transferCertificateMapper.entityToDto(existingTransferCertificateContainer.get());

		existingTransferCertificate.setDateOfIssue(transferCertificateDto.getDateOfIssue());
		TransferCertificate updateTransferCertificate = transferCertificateRepository.save(transferCertificateMapper.dtoToEntity(existingTransferCertificate));
		Response<TransferCertificateDto> response = new Response<>();
	//	System.out.println(existingTransferCertificate);
		//System.out.println(updateTransferCertificate);

		response.setMessage(HttpStatusCode.TRANSFER_CERTIFICATE_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.TRANSFER_CERTIFICATE_UPDATED.getCode());
		response.setResult(new Result<>(transferCertificateMapper.entityToDto(updateTransferCertificate)));
		return response;
	}
	
	
	
	@Override
	public Response<TransferCertificateDto> deleteTransferCertificate(int transferCertificateId){
		Result<TransferCertificateDto> res=new Result<>();
		res.setData(null);
		Optional<TransferCertificate> transferCertificate = transferCertificateRepository.findById(transferCertificateId);
		if (!transferCertificate.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		transferCertificateRepository.deleteById(transferCertificateId);
		Response<TransferCertificateDto> response = new Response<>();
		response.setMessage(HttpStatusCode.TRANSFER_CERTIFICATE_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.TRANSFER_CERTIFICATE_DELETED.getCode());
		response.setResult(new Result<TransferCertificateDto>(transferCertificateMapper.entityToDto(transferCertificate.get())));
		return response;	

	}
   
  


   
   
	
}