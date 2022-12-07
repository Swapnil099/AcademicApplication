package com.ubi.academicapplication.service;

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
	 
    /* Method for Get All Tc */
	/*@Override
	public List<TransferCertificate> getTC() {
	return transferCertificateRepository.findAll();
	}*/
    @Override
	public Response<List<TransferCertificate>> getTC() {
		
		Response<List<TransferCertificate>> getListofTc = new Response<List<TransferCertificate>>();
		List<TransferCertificate> list = (List<TransferCertificate>) this.transferCertificateRepository.findAll();
		res.setData(list);
		Result<List<TransferCertificate>> allTcResult = new Result<>();
		if (list.size() == 0) {
			throw new CustomException(HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getCode(), 
					HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND,
					HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getMessage(), res);
		}
		allTcResult.setData(list);
		getListofTc.setStatusCode(200);
		getListofTc.setResult((Result<List<TransferCertificate>>) allTcResult);
		return getListofTc;
	}

	/* Method for Add new Tc */
	//@Override
	//public TransferCertificate addTC(TransferCertificate transferCertificate) {
		
		//return transferCertificateRepository.save(transferCertificate);
	//}
	@Override
	public Response<TransferCertificate> addTC(TransferCertificate transferCertificate) {
		Response<TransferCertificate> response = new Response<>();
		  System.out.println(transferCertificate);
		  TransferCertificate saveTransferCertificate = transferCertificateRepository.save(transferCertificate);
		  response.setStatusCode(200);
		  response.setMessage("TC saved SuccessFully..!!");
			return response;
	
	}
	

	/* Method for Update Tc */
	@Override
	public TransferCertificate updateTC(TransferCertificate transferCertificate) {
		return transferCertificateRepository.save(transferCertificate);
	}
	
	

	/* Method for Delete Tc */
  // @Override
	//public void deleteTC(int tcId) {
	  // transferCertificateRepository.deleteById(tcId);
	//}
	
	@Override
	public void deleteTC(int tcId){
		
		try {
			Response<TransferCertificate> deleteTc=new Response<TransferCertificate>();
			transferCertificateRepository.deleteById(tcId);
			
		} catch (IllegalArgumentException e) {
			throw new CustomException(HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getCode(), 
					HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND,
					HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getMessage(), res);
		} catch (Exception e) {
			throw new CustomException(HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getCode(),
					HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND,
					HttpStatusCode.NO_TRANSFERCERTIFICATE_FOUND.getMessage(), res);

		}	

	}
   
   /* Method for Get single Tc */
   @Override
   public TransferCertificate getSingleTC(int id) 
   {
	Optional<TransferCertificate> transferCertificate=  transferCertificateRepository.findById(id);
	if(transferCertificate.isPresent())
	{
	return transferCertificate.get();
	}else
	  throw new RuntimeException("TC is not found" +id) ;
   }
	/*
	
	
	@Override
	public Response<TransferCertificate> getSingleTC(int id) {
		Response<TransferCertificate> getTransferCertificate = new Response<TransferCertificate>();
		Optional<TransferCertificate> Tc = null;
		Tc = this.transferCertificateRepository.findById(id);
		Result<TransferCertificate> tcResult = new Result<>();
		if (!Tc.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_TRANSFERCERTIFICATE_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_TRANSFERCERTIFICATE_MATCH_WITH_ID, HttpStatusCode.NO_TRANSFERCERTIFICATE_MATCH_WITH_ID.getMessage(), null);
		}
		tcResult.setData(Tc.get());
		getTransferCertificate.setStatusCode(200);
		//getTransferCertificate.setResult(tcResult);
		return getTransferCertificate;
	}

	*/
   /* Method for Pagination  */
   public Page<TransferCertificate> findTCWithPagination(int offset,int pageSize){
       Page<TransferCertificate> transferCertificate = transferCertificateRepository.findAll(PageRequest.of(offset, pageSize));
       return  transferCertificate;
   }
   
   /* Method for Sort TC Data */
   @Override
	public List<TransferCertificate> findTCWithSorting(String field) {
		
		return transferCertificateRepository.findAll(Sort.by(Sort.Direction.ASC,field));
	}
	
}