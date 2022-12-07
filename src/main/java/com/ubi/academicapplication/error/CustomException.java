package com.ubi.academicapplication.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private final int exceptionCode;
	private final HttpStatusCode status;
	private final String exceptionMessage;	
	private final Result<?> result;

}
