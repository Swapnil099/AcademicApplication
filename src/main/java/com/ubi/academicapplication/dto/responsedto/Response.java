package com.ubi.academicapplication.dto.responsedto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ubi.academicapplication.error.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> extends BaseResponse {
	private Result<T> result;
}