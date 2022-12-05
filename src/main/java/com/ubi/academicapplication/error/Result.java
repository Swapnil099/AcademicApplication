package com.ubi.academicapplication.error;

import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class Result<T> {

	private T data;
}
