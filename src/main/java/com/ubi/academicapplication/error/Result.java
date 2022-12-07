package com.ubi.academicapplication.error;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Result<T> {
	private T data;
}
