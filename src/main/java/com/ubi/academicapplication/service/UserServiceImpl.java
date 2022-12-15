package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.user.UserContactInfoDto;
import com.ubi.academicapplication.dto.user.UserContactInfoMappingDto;
import com.ubi.academicapplication.dto.user.UserCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.entity.ContactInfo;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.User;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.UserMapper;
import com.ubi.academicapplication.repository.ContactInfoRepository;
import com.ubi.academicapplication.repository.UserRepository;

import lombok.Data;

@Service
@Data
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ContactInfoRepository contactInfoRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserMapper userMapper;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	Result result;

	@Override
	public Response<List<UserDto>> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDto> allUsers = users.stream().map(userMapper::toDto).collect(Collectors.toList());
		Response<List<UserDto>> response = new Response<>();
		response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
		response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
		response.setResult(new Result<>(allUsers));
		return response;
	}

	@Override
	public Response<User> createNewUser(UserCreationDto userCreationDTO) {
		Response<User> response = new Response<>();
		if(this.getUserByUsername(userCreationDTO.getUsername()) != null){
			throw new CustomException(
					HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode(),
					HttpStatusCode.RESOURCE_ALREADY_EXISTS,
					HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage(),
					result);
		}

		User user = userMapper.toUser(userCreationDTO);
		User userWithPreEncodePassword = new User(user.getUsername(),user.getPassword(),user.getIsEnabled(),user.getRole());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		userWithPreEncodePassword.setId(user.getId());
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<>(userWithPreEncodePassword));
		return response;
	}

	@Override
	public Response<UserDto> getUserById(String userId) {
		Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
		Response<UserDto> response = new Response<>();
		if(!currUser.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
					HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
					result);
		}
		User user = currUser.get();
		UserDto userDto = userMapper.toDto(user);
		response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
		response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
		response.setResult(new Result<>(userDto));
		return response;
	}

	public UserDto getUserByUsername(String username){
		User user = userRepository.findByUsername(username);
		if(user == null) return null;
		return userMapper.toDto(user);
	}

	@Override
	public Response<UserDto> deleteUserById(String userId) {
		Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
		if(!currUser.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
					HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
					result);
		}
		userRepository.deleteById(Long.parseLong(userId));
		Response<UserDto> response = new Response<>();
		response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
		response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
		response.setResult(new Result<>(userMapper.toDto(currUser.get())));
		return response;
	}

	@Override
	public boolean isUsernamePasswordValid(String username,String password){
		User user = userRepository.findByUsername(username);
		return (user != null && passwordEncoder.matches(password,user.getPassword()));
	}

	@Override
	public String getRoleByUsername(String username) {
		UserDto user = this.getUserByUsername(username);
		return user.getRoleType();
	}

	@Override
	public Response<UserDto> changeActiveStatusToTrue(String userId) {
		if(this.getUserById(userId).getResult().getData() == null){
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
					HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
					result);
		}
		User user = userRepository.getReferenceById(Long.parseLong(userId));
		user.setIsEnabled(true);
		User updatedUser = userRepository.save(user);
		return new Response<>(new Result<>(userMapper.toDto(updatedUser)));
	}

	@Override
	public Response<UserDto> changeActiveStatusToFalse(String userId) {
		if(this.getUserById(userId).getResult().getData() == null){
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
					HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
					result);
		}
		User user = userRepository.getReferenceById(Long.parseLong(userId));
		user.setIsEnabled(false);
		User updatedUser = userRepository.save(user);
		return new Response<>(new Result<>(userMapper.toDto(updatedUser)));
	}

	@Override
	public Response<String> changeSelfPassword(String userId,String newPassword) {
		Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!currUser.isPresent()){
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
					HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
					result);
		}

		if(!user.getId().equals(currUser.get().getId())){
			throw new CustomException(HttpStatusCode.UNAUTHORIZED_EXCEPTION.getCode(),
					HttpStatusCode.UNAUTHORIZED_EXCEPTION,
					HttpStatusCode.UNAUTHORIZED_EXCEPTION.getMessage(),
					result);
		}
		newPassword = passwordEncoder.encode(newPassword);
		user.setPassword(newPassword);
		userRepository.save(user);

		Response response = new Response<>();
		response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
		response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
		response.setResult(new Result<>("PASSWORD CHANGED SUCCESSFULLY"));
		return response;
	}

	@Override
	public Response<UserDto> updateUserById(String userId, UserCreationDto userCreationDto) {
		Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
		if(!currUser.isPresent()){
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
					HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
					result);
		}
		User user = currUser.get();
		User userByNewUsername = userRepository.findByUsername(userCreationDto.getUsername());
		if(userByNewUsername != null && !user.getUsername().equals(userCreationDto.getUsername())){
			throw new CustomException(HttpStatusCode.USERNAME_NOT_AVAILAIBLE.getCode(),
					HttpStatusCode.USERNAME_NOT_AVAILAIBLE,
					HttpStatusCode.USERNAME_NOT_AVAILAIBLE.getMessage(),
					result);
		}
		user.setUsername(userCreationDto.getUsername());
		user.setIsEnabled(userCreationDto.getIsActivate());
		Role role = roleService.getRoleByRoleType(userCreationDto.getRoleType());
		if(role == null){
			throw new CustomException(HttpStatusCode.ROLE_NOT_EXISTS.getCode(),
					HttpStatusCode.ROLE_NOT_EXISTS,
					HttpStatusCode.ROLE_NOT_EXISTS.getMessage(),
					result);
		}
		user.setRole(role);
		userRepository.save(user);

		Response response = new Response<>();
		response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
		response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
		response.setResult(new Result<>(userMapper.toDto(user)));
		return response;
	}

	@Override
	public Response<UserContactInfoDto> addContactInfo(UserContactInfoMappingDto userContactInfoMappingDto) {
		System.out.println(userContactInfoMappingDto.toString());
		Long userId = userContactInfoMappingDto.getUserId();
		Long contactId = userContactInfoMappingDto.getContactId();

		Response<UserContactInfoDto> response = new Response<>();
		Result<UserContactInfoDto> res = new Result<>();
		User user=userRepository.getReferenceById(userId);
		ContactInfo contact=contactInfoRepository.getReferenceById(contactId);
		user.setContactInfo(contact);
		ContactInfo setOfContact = user.getContactInfo();

		/*if(setOfContact.getContactInfoId() == contact.getContactInfoId()) {
   				throw new CustomException(HttpStatusCode.MAPPING_ALREADY_EXIST.getCode(), 
   						HttpStatusCode.MAPPING_ALREADY_EXIST, 
   						HttpStatusCode.MAPPING_ALREADY_EXIST.getMessage(), 
   						res);
   			}*/


		userRepository.save(user);
		System.out.println(user.getUsername()+">>>"+user.getContactInfo().getContactInfoId());

		UserContactInfoDto userContactInfoDto = userMapper.toUserContactInfoDto(user);
		response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
		response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
		response.setResult(new Result<UserContactInfoDto>(userContactInfoDto));
		return response;
	}
	
	 @Override
	  	public Response<UserContactInfoDto> getContactInfowithUser(Long id) {

	  		Response<UserContactInfoDto> response = new Response<>();
	  		Result<UserContactInfoDto> res = new Result<>();

	  		Optional<User> user = this.userRepository.findById(id);

	  		if (!user.isPresent()) {
	  			throw new CustomException(HttpStatusCode.NO_USER_MATCH_WITH_ID.getCode(),
	  					HttpStatusCode.NO_USER_MATCH_WITH_ID,
	  					HttpStatusCode.NO_USER_MATCH_WITH_ID.getMessage(), res);
	  		}
	  		UserContactInfoDto userContactInfoDto = new UserContactInfoDto();
	  		userContactInfoDto.setUser(user.get());
	  		userContactInfoDto.setContactInfo(user.get().getContactInfo());
	  		

	  		res.setData(userContactInfoDto);

	  		response.setStatusCode(HttpStatusCode.USER_RETRIVED_SUCCESSFULLY.getCode());
	  		response.setMessage(HttpStatusCode.USER_RETRIVED_SUCCESSFULLY.getMessage());
	  		response.setResult(res);
	  		return response;

	  	}
}
