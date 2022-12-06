package com.ubi.academicapplication.dto.responsedto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

	protected int statusCode;
	protected String message;

}