package com.ubi.academicapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.TransferCertificate;
public interface TransferCertificateService {

	
	public Response<List<TransferCertificate>> getTC(); 
	
	public Response<TransferCertificate> addTC(TransferCertificate transferCertificate);

	public TransferCertificate updateTC(TransferCertificate transferCertificate);

	public void deleteTC(int tcId);

	public TransferCertificate getSingleTC(int id);
	
	public Page<TransferCertificate> findTCWithPagination(int offset,int pageSize);
	
	public List<TransferCertificate> findTCWithSorting(String field);

}
