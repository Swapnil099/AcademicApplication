package com.ubi.academicapplication.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.entity.TransferCertificate;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.repository.TransferCertificateRepository;


@Service
public class TransferCertificateServiceImpl implements TransferCertificateService {

	@Autowired
	private TransferCertificateRepository transferCertificateRepository;
	@Autowired
	Result res;

    Logger logger = LoggerFactory.getLogger(TransferCertificateServiceImpl.class);
	 
 
    @Override
	public Response<List<TransferCertificate>> getAllTransferCertificate() {
		
		Response<List<TransferCertificate>> getListofTransferCertificate = new Response<List<TransferCertificate>>();
		List<TransferCertificate> list = (List<TransferCertificate>) this.transferCertificateRepository.findAll();
		res.setData(list);
		Result<List<TransferCertificate>> allTransferCertificate = new Result<>();
		if (list.size() == 0) {
			logger.info("Custom Exception Occured"+ HttpStatusCode.RESOURCE_NOT_FOUND.getMessage());
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), 
					HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		allTransferCertificate.setData(list);
		getListofTransferCertificate.setStatusCode(HttpStatusCode.RETREIVED_SUCCESSFULLY.getCode());
		getListofTransferCertificate.setMessage(HttpStatusCode.RETREIVED_SUCCESSFULLY.getMessage());
		getListofTransferCertificate.setResult((Result<List<TransferCertificate>>) allTransferCertificate);
		return getListofTransferCertificate;
	}


	@Override
	public Response<TransferCertificate> addTransferCertificate(TransferCertificate transferCertificate) {

		Response<TransferCertificate> response = new Response<>();
		Optional<TransferCertificate> tempCertificate =transferCertificateRepository.findById(transferCertificate.getId());
		res.setData(tempCertificate);
		  if(tempCertificate.isPresent()){
	            throw new CustomException(HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode(),HttpStatusCode.RESOURCE_ALREADY_EXISTS, HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage(),res);
		  }
		  TransferCertificate saveTransferCertificate = transferCertificateRepository.save(transferCertificate);
		  response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		  response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		  response.setResult(new Result<TransferCertificate>(saveTransferCertificate));
			return response;
	}
	


	@Override
	public Response<TransferCertificate> updateTransferCertificate(TransferCertificate transferCertificate) throws ParseException {
		res.setData(null);
		Response<TransferCertificate> response = new Response<>();
		Optional<TransferCertificate> existingContainer = transferCertificateRepository.findById(transferCertificate.getId());
		if (!existingContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getCode(), HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND,
					HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getMessage(), res);
		}
     	TransferCertificate existingTransferCertificate = existingContainer.get();
		existingTransferCertificate.setDateOfIssue(new Date());
		TransferCertificate updateTransferCertificate = transferCertificateRepository.save(existingTransferCertificate);
		
		System.out.println(existingTransferCertificate);
		System.out.println(updateTransferCertificate);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		
//		
//		updateTransferCertificate.setDateOfIssue( sdf.parse(sdf.format(updateTransferCertificate.getDateOfIssue())));
//		
		response.setMessage(HttpStatusCode.TRANSFER_CERTIFICATE_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.TRANSFER_CERTIFICATE_UPDATED.getCode());
		response.setResult(new Result<>(updateTransferCertificate));
		return response;
	}
	
	
	
	@Override
	public Response<TransferCertificate> deleteTransferCertificate(int transferCertificateId){
		
		res.setData(null);
		Optional<TransferCertificate> transferCertificate = transferCertificateRepository.findById(transferCertificateId);
		if (!transferCertificate.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		transferCertificateRepository.deleteById(transferCertificateId);
		Response<TransferCertificate> response = new Response<>();
		response.setMessage(HttpStatusCode.TRANSFER_CERTIFICATE_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.TRANSFER_CERTIFICATE_DELETED.getCode());
		response.setResult(new Result<TransferCertificate>(transferCertificate.get()));
		return response;	

	}
   
   @Override
   public Response<TransferCertificate> getSingleTransferCertificate(int transferCertificateId) 
   {
	   Response<TransferCertificate> getTransferCertificate = new Response<TransferCertificate>();
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
		return getTransferCertificate;
   }
	
   
   public Page<TransferCertificate> findTCWithPagination(int offset,int pageSize){
       Page<TransferCertificate> transferCertificate = transferCertificateRepository.findAll(PageRequest.of(offset, pageSize));
       return  transferCertificate;
   }
   
   @Override
	public List<TransferCertificate> findTCWithSorting(String field) {
		
		return transferCertificateRepository.findAll(Sort.by(Sort.Direction.ASC,field));
	}
	
}