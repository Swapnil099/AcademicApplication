package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoCreationDto;
import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.entity.ContactInfo;

public interface ContactInfoService {

	Response<ContactInfoDto> addContactInfo(ContactInfoDto contactInfoDto);

	Response<List<ContactInfoDto>> getContactInfo(Integer PageNumber, Integer PageSize);

	public Response<ContactInfoDto> getContactInfoById(Long contactInfoId);

	public Response<ContactInfoDto> deleteContactById(Long contactInfoId);

	Response<ContactInfoCreationDto> updateContactInfoById(String contactInfoId,ContactInfoDto contactInfoDto);

	Response<List<ContactInfoDto>> getContactInfowithSort(String field);

	List<ContactInfo> getContactInfoFromString(ContactInfo contactInfo);

}
