package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.response.Response;

public interface ContactInfoService {

	Response<ContactInfoDto> addContactInfo(ContactInfoDto contactInfoDto);
	
	Response<List<ContactInfoDto>> getContactInfo(Integer PageNumber, Integer PageSize);
	
	public Response<ContactInfoDto> getContactInfoById(Long classidL);
	
	public Response<ContactInfoDto> deleteContactById(Long classidL);

	Response<ContactInfoDto> updateContactDetails(ContactInfoDto contactInfoDto);
	
	Response<List<ContactInfoDto>> getContactInfowithSort(String field);

}
