package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.entity.ContactInfo;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.ContactInfoMapper;
import com.ubi.academicapplication.repository.ContactInfoRepository;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactInfoService.class);

	@Autowired
	private ContactInfoRepository contactInfoRepository;

	@Autowired
	private ContactInfoMapper contactInfoMapper;

	public Response<ContactInfoDto> addContactInfo(ContactInfoDto contactInfoDto) {

		Response<ContactInfoDto> response = new Response<>();
		Result<ContactInfoDto> res = new Result<>();

		if (contactInfoDto.getFirstName().isEmpty() || contactInfoDto.getFirstName().length() == 0) {
			throw new CustomException(HttpStatusCode.NO_CONTACTINFO_FOUND.getCode(),
					HttpStatusCode.NO_CONTACTINFO_FOUND, HttpStatusCode.NO_CONTACTINFO_FOUND.getMessage(), res);
		}
		ContactInfo savedContact = contactInfoRepository.save(contactInfoMapper.dtoToEntity(contactInfoDto));
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<ContactInfoDto>(contactInfoMapper.entityToDto(savedContact)));
		return response;
	}
	
	    public Response<List<ContactInfoDto>> getContactInfo(Integer PageNumber, Integer PageSize) {
	    Result<List<ContactInfoDto>> res = new Result<>();
		res.setData(null);
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<ContactInfoDto>> getListofContacts = new Response<>();
		Page<ContactInfo> list = this.contactInfoRepository.findAll(paging);
		List<ContactInfoDto> contactInfoDtos = contactInfoMapper.entitiesToDtos(list.toList());
		res.setData(contactInfoDtos);
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		getListofContacts.setStatusCode(200);
		getListofContacts.setResult(res);
		return getListofContacts;
	}
	 
	   public Response<ContactInfoDto> getContactInfoById(Long classidL) {
		
		Result<ContactInfoDto> res = new Result<>();
		res.setData(null);
		Response<ContactInfoDto> getClass = new Response<ContactInfoDto>();
		Optional<ContactInfo> cls = null;
		Result<ContactInfoDto> contactResult = new Result<>();
		cls = this.contactInfoRepository.findById(classidL);
		if (!cls.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_CONTACTINFO_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_CONTACTINFO_MATCH_WITH_ID, HttpStatusCode.NO_CONTACTINFO_MATCH_WITH_ID.getMessage(), res);
		}
		contactResult.setData(contactInfoMapper.entityToDto(cls.get()));
		getClass.setStatusCode(200);
		getClass.setResult(contactResult);
		return getClass;
	}
	  
	   public Response<ContactInfoDto> deleteContactById(Long id) {
		Result<ContactInfoDto> res = new Result<>();
		
		res.setData(null);
		Optional<ContactInfo> contactDetail = contactInfoRepository.findById(id);
		if (!contactDetail.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		contactInfoRepository.deleteById(id);
		Response<ContactInfoDto> response = new Response<>();
		response.setMessage(HttpStatusCode.CONTACTINFO_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.CONTACTINFO_DELETED.getCode());
		response.setResult(new Result<ContactInfoDto>(contactInfoMapper.entityToDto(contactDetail.get())));
		return response;
	}

	   public Response<ContactInfoDto> updateContactDetails(ContactInfoDto contactInfoDto) {
			Result<ContactInfoDto> res = new Result<>();
			
			res.setData(null);
			Optional<ContactInfo> existingContactContainer = contactInfoRepository.findById(contactInfoDto.getContactInfoId());
			if (!existingContactContainer.isPresent()) {
				throw new CustomException(HttpStatusCode.NO_CONTACTINFO_FOUND.getCode(), HttpStatusCode.NO_CONTACTINFO_FOUND,
						HttpStatusCode.NO_CONTACTINFO_FOUND.getMessage(), res);
			}
			ContactInfoDto existingContacts = contactInfoMapper.entityToDto(existingContactContainer.get());
			existingContacts.setFirstName(contactInfoDto.getFirstName());
			existingContacts.setMiddleName(contactInfoDto.getMiddleName());
			existingContacts.setLastName(contactInfoDto.getLastName());
			existingContacts.setAddress(contactInfoDto.getAddress());
			existingContacts.setAge(contactInfoDto.getAge());
			existingContacts.setBloodGroup(contactInfoDto.getBloodGroup());
			existingContacts.setState(contactInfoDto.getState());
			existingContacts.setCity(contactInfoDto.getCity());
			existingContacts.setNationality(contactInfoDto.getNationality());
			existingContacts.setAadharCardNumber(contactInfoDto.getAadharCardNumber());
			existingContacts.setEmail(contactInfoDto.getEmail());
			existingContacts.setDob(contactInfoDto.getDob());
			existingContacts.setGender(contactInfoDto.getGender());
			existingContacts.setContactNumber(contactInfoDto.getContactNumber());
			
			ContactInfo updateContact = contactInfoRepository.save(contactInfoMapper.dtoToEntity(existingContacts));
			Response<ContactInfoDto> response = new Response<>();
			response.setMessage(HttpStatusCode.CONTACTINFO_UPDATED.getMessage());
			response.setStatusCode(HttpStatusCode.CONTACTINFO_UPDATED.getCode());
			response.setResult(new Result<>(contactInfoMapper.entityToDto(updateContact)));
			return response;
		}

}
