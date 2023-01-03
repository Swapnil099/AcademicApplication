package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoCreationDto;
import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.pagination.PaginationResponse;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;
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

	@Autowired
	Result result;

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

	public Response<PaginationResponse<List<ContactInfoDto>>> getContactInfo(Integer PageNumber, Integer PageSize) {
		Result<PaginationResponse<List<ContactInfoDto>>> res = new Result<>();
		res.setData(null);
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<PaginationResponse<List<ContactInfoDto>>> getListofContacts = new Response<>();
		Page<ContactInfo> list = this.contactInfoRepository.findAll(paging);
		List<ContactInfoDto> contactInfoDtos = contactInfoMapper.entitiesToDtos(list.toList());
		
		//res.setData(contactInfoDtos);
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		PaginationResponse paginationResponse=new PaginationResponse<List<ContactInfoDto>>(contactInfoDtos,list.getTotalPages(),list.getTotalElements());
		res.setData(paginationResponse);
		
		getListofContacts.setStatusCode(200);
		getListofContacts.setResult(res);
		return getListofContacts;
	}

	public Response<ContactInfoDto> getContactInfoById(Long contactInfoId) {

		Result<ContactInfoDto> res = new Result<>();
		res.setData(null);
		Response<ContactInfoDto> getClass = new Response<ContactInfoDto>();
		Optional<ContactInfo> cls = null;
		Result<ContactInfoDto> contactResult = new Result<>();
		cls = this.contactInfoRepository.findById(contactInfoId);
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

	@Override
	public Response<ContactInfoCreationDto> updateContactInfoById(String contactInfoId,ContactInfoDto contactInfoDto) {

		ContactInfo contactInfo = contactInfoRepository.getReferenceById(Long.parseLong(contactInfoId));
		if(contactInfo == null){
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
					HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
					result);
		}

		contactInfo.setAadharCardNumber(contactInfoDto.getAadharCardNumber());
		contactInfo.setAddress(contactInfoDto.getAddress());
		contactInfo.setAge(contactInfoDto.getAge());
		contactInfo.setBloodGroup(contactInfoDto.getBloodGroup());
		contactInfo.setCity(contactInfoDto.getCity());
		contactInfo.setContactNumber(contactInfoDto.getContactNumber());
		contactInfo.setDob(contactInfoDto.getDob());
		contactInfo.setEmail(contactInfoDto.getEmail());
		contactInfo.setFirstName(contactInfoDto.getFirstName());
		contactInfo.setGender(contactInfoDto.getGender());
		contactInfo.setLastName(contactInfoDto.getLastName());
		contactInfo.setMiddleName(contactInfoDto.getMiddleName());
		contactInfo.setNationality(contactInfoDto.getNationality());
		contactInfo.setState(contactInfoDto.getState());

		contactInfoRepository.save(contactInfo);

		Response response = new Response<>();
		response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
		response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
		response.setResult(new Result<>(contactInfoMapper.entityToDto(contactInfo)));
		return response;
	}

	@Override
	public Response<List<ContactInfoDto>> getContactInfowithSort(String field) {

		Result<List<ContactInfoDto>> allContactInfoResult = new Result<>();

		Response<List<ContactInfoDto>> getListofContactInfo = new Response<>();

		List<ContactInfo> list = this.contactInfoRepository.findAll(Sort.by(Sort.Direction.ASC,field));
		List<ContactInfoDto> contactInfoDtos = contactInfoMapper
				.entitiesToDtos(list);

		if (list.size() == 0) {
			throw new CustomException(HttpStatusCode.NO_CONTACTINFO_FOUND.getCode(), HttpStatusCode.NO_CONTACTINFO_FOUND,
					HttpStatusCode.NO_CONTACTINFO_FOUND.getMessage(), allContactInfoResult);
		}
		allContactInfoResult.setData(contactInfoDtos);
		getListofContactInfo.setStatusCode(HttpStatusCode.CONTACTINFO_RETRIVED_SUCCESSFULLY.getCode());
		getListofContactInfo.setMessage(HttpStatusCode.CONTACTINFO_RETRIVED_SUCCESSFULLY.getMessage());
		getListofContactInfo.setResult(allContactInfoResult);
		return getListofContactInfo;
	}

	@Override
	public List<ContactInfo> getContactInfoFromString(ContactInfo contactInfo) {
		List<ContactInfo> contactInfos = contactInfoRepository.findAll();
		return contactInfos;
	}
}
