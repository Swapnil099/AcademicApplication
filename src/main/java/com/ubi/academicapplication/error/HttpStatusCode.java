package com.ubi.academicapplication.error;

public enum HttpStatusCode {

	
	NO_ENTRY_FOUND(101,"Resource Not Found"),
	
	NO_STUDENT_NAME_FOUND(202,"No student name found"),
	// With Custom Error Status Code
	//INVALID_TABLE_NAME(101, "Invalid table name"),
	
	//INVALID_SKU_NAME(102, "Invalid SKU name"),
	
	NO_STUDENT_FOUND(108,"No Student Found"),
	
	NO_STUDENT_MATCH_WITH_ID(109,"No such student found with such id"),
	
    NO_PAYMENT_FOUND(108,"No Payment Found"),
	
	NO_PAYMENT_MATCH_WITH_ID(109,"No Payment found with given Id "),
	
	
	//INVALID_COLUMN_ATTRIBUTE(103, "Invalid column attribute is provided"), 
	
	INVALID_JSON_INPUT(105, "Invalid JSON input or JSON format"),
	
	UNRECOGNIZED_FIELD(106, "Check sequence of fields or field name"),
	
	//UNDER_DELETION_PROCESS(107, "Under deletion process"),
	
	//TABLE_NOT_FOUND(108, "Does not exist"),
	
	//TABLE_NOT_UNDER_DELETION(109, "Not under deletion"),
	
	RESOURCE_ALREADY_EXISTS(110, "Already exists"),

	INVALID_COLUMN_NAME(111, "Invalid column name provided"),
	
	WRONG_DATA_TYPE(112, "Wrong datatype selected for non multivalued field"),
	
	IO_EXCEPTION(113, "I/O exception occurred"), 
	
	JSON_PARSE_EXCEPTION(114, "JSON parse error occurred"), 
	
	INVALID_FIELD_VALUE(116, "Value for field : {} is not expected as : {}"), 
	
	//SAAS_SERVER_ERROR(119, "This feature is currently down. Try again later"), 
	
	CONNECTION_REFUSED(120, "Connection is refused from the server"), 
	
	INVALID_CREDENTIALS(121, "Invalid credentials provided"), 
	
	// With Primitive Error Status Code
	BAD_REQUEST_EXCEPTION(400, "Bad Request Occuured"),
	
	NULL_POINTER_EXCEPTION(500, "Received Null response"),
	
	SERVER_UNAVAILABLE(503, "Unable to Connect To the Server"),
	
	OPERATION_NOT_ALLOWED(405, "Operation is Not Allowed"),
	
	UNAUTHORIZED_EXCEPTION(401, "Unauthorized To Perform Request"),
	
	FORBIDDEN_EXCEPTION(403, "Forbidden access attempted"),
	
	INTERNAL_SERVER_ERROR (500, "Internal Server Error Occured"),
	
	PROCESSING_NOT_COMPLETED (202, "Request cannot be Processed"),
	
	NOT_ACCEPTABLE_ERROR (406, "Request Not accpetable"),

	// SUCCESS STATUS CODES
	SUCCESSFUL(200, "Request Successfull"),
	RESOURCE_CREATED_SUCCESSFULLY (201, "Resource Created Successfully");

	private int code;
	private String message;
	
	HttpStatusCode(int code, String message) {
		this.code=code;
		this.message=message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	
	

}