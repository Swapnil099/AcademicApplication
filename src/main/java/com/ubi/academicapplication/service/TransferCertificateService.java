package com.ubi.academicapplication.service;

import java.text.ParseException;
import java.util.List;


import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.transfercertificate.TransferCertificateDto;


public interface TransferCertificateService {

	public Response<List<TransferCertificateDto>> getAllTransferCertificate(Integer PageNumber, Integer PageSize);

	public Response<TransferCertificateDto> addTransferCertificate(TransferCertificateDto transferCertificateDto);

	public Response<TransferCertificateDto> deleteTransferCertificate(int transferCertificateId);

	public Response<TransferCertificateDto> getSingleTransferCertificate(int transferCertificateId);

	public Response<TransferCertificateDto> updateTransferCertificate(TransferCertificateDto transferCertificateDto) throws ParseException;

	

	

}
