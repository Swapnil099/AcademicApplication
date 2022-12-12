package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.entity.School;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.repository.SchoolRepository;


@Service
public class SchoolServiceImpl implements SchoolService{

	Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);

	@Autowired
	private SchoolRepository schoolRepository;
	
	//@Autowired
	//private ModelMapper modelMapper;
	
    @Autowired
	private Result res;
	
	
	
	@Override
	public Response<School> saveSchool(School school) {
		Response<School> response = new Response<>();
		  System.out.println(school);
		  //if(schoolRepository.findById(school.getSchoolId()) != null){
//	            throw new CustomException(HttpStatusCode.NO_PAYMENT_FOUND.getCode(),HttpStatusCode.NO_PAYMENT_FOUND, HttpStatusCode.NO_PAYMENT_FOUND.getMessage(),res);
//		  }
		  School saveSchool = schoolRepository.save(school);
		  response.setStatusCode(200);
		  response.setMessage("School saved SuccessFully..!!");
		  response.setResult(new Result<>(saveSchool));
			return response;	
	}

	@Override
	public Response<School> getsingleSchool(int id) {
			
			Response<School> getSchool = new Response<School>();
			Optional<School> sch = null;
			sch = this.schoolRepository.findById(id);
			Result<School> schoolResult=new Result<>();
			res.setData(sch);
			if (!sch.isPresent()) {
				throw new CustomException(HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID.getCode(),
						HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID, HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID.getMessage(), null);
			}
			getSchool.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
			getSchool.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
			getSchool.setResult(new Result<>(sch.get()));
			return getSchool;

	}

	@Override
	public Response<List<School>> getAllSchools() {
		
			
			Response<List<School>> getListofSchools = new Response<List<School>>();
			List<School> list = (List<School>) this.schoolRepository.findAll();
			
			res.setData(list);	
			Result<List<School>> schoolResult=new Result<>();		
			if (list.size() == 0) {
				throw new CustomException(HttpStatusCode.NO_SCHOOL_FOUND.getCode(), HttpStatusCode.NO_SCHOOL_FOUND,
						HttpStatusCode.NO_SCHOOL_FOUND.getMessage(), res);
			}
			getListofSchools.setStatusCode(200);
			getListofSchools.setResult(schoolResult);
			return getListofSchools;			
		}


	@Override
	public School updateSchool(School school) {
		return this.schoolRepository.save(school);
	}

	@Override
	public void deleteSchool(int id) {
			
			try {
				Response<School> deleteSchool=new Response<School>();
				schoolRepository.deleteById(id);
				
			} catch (IllegalArgumentException e) {
				throw new CustomException(HttpStatusCode.NO_SCHOOL_FOUND.getCode(), HttpStatusCode.NO_SCHOOL_FOUND,
						HttpStatusCode.NO_SCHOOL_FOUND.getMessage(), res);
			} catch (Exception e) {
				throw new CustomException(HttpStatusCode.NO_SCHOOL_FOUND.getCode(), HttpStatusCode.NO_SCHOOL_FOUND,
						HttpStatusCode.NO_SCHOOL_FOUND.getMessage(), res);

			}	
		
	}

	@Override
	public Response<School> getschoolByName(String name) {
		Response<School> getSchoolName = new Response<>();
		Optional<School> sch1 = null;
		sch1 = this.schoolRepository.findByName(name);
		Result<School> schoolResult=new Result<>();
		if (!sch1.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_SCHOOL_MATCH_WITH_NAME.getCode(),
					HttpStatusCode.NO_SCHOOL_MATCH_WITH_NAME, HttpStatusCode.NO_SCHOOL_MATCH_WITH_NAME.getMessage(), null);
		}
		getSchoolName.setStatusCode(200);
		getSchoolName.setResult(schoolResult);
		return getSchoolName;

}
}


