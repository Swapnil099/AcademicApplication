package com.ubi.academicapplication.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.TransferCertificate;

public interface TransferCertificateService {

	public Response<List<TransferCertificate>> getAllTransferCertificate();

	public Response<TransferCertificate> addTransferCertificate(TransferCertificate transferCertificate);

	public Response<TransferCertificate> deleteTransferCertificate(int transferCertificateId);

	public Response<TransferCertificate> getSingleTransferCertificate(int transferCertificateId);

	public Response<TransferCertificate> updateTransferCertificate(TransferCertificate transferCertificate) throws ParseException;

	

	public Page<TransferCertificate> findTCWithPagination(int offset, int pageSize);

	public List<TransferCertificate> findTCWithSorting(String field);

}
