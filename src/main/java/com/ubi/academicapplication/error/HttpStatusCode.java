package com.ubi.academicapplication.error;

public enum HttpStatusCode {

	
	NO_ENTRY_FOUND(101,"Resource Not Found"),
	
	NO_STUDENT_NAME_FOUND(202,"No student name found"),
	
	NO_STUDENT_FOUND(108,"No Student Found"),
	
	NO_STUDENT_MATCH_WITH_ID(109,"No such student found with such id"),
	
	NO_CLASS_MATCH_WITH_ID(109,"No such class found with such id"),
	
    NO_PAYMENT_FOUND(108,"No Payment Found"),
	
	NO_PAYMENT_MATCH_WITH_ID(109,"No Payment found with given Id "),
	
	PAYMENT_RETRIVED_SUCCESSFULLY(200,"Payment Retrived"),
	
    NO_EDUCATIONAL_INSTITUTION_FOUND(108,"No Educational Institution Found"),
	
	NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID(109,"No Educational Institution found with given Id "),
	
	NO_EDUCATIONAL_INSTITUTION_NAME_FOUND(202,"No Educational Institution Name Found"),
	
	
	
	NO_TRANSFERCERTIFICATE_FOUND(108,"No Transfer Certificate Found"),
	
	NO_TRANSFERCERTIFICATE_MATCH_WITH_ID(109,"No Transfer Certificate  found with given Id "),


	NO_SCHOOL_MATCH_WITH_ID(109, "No School Found with Given ID"),
	
	NO_SCHOOL_FOUND(108, "No School Found"),
	
	NO_SCHOOL_MATCH_WITH_NAME(110, "No School Found With Given NAME"),
	
	
	//INVALID_COLUMN_ATTRIBUTE(103, "Invalid column attribute is provided"), 


	INVALID_JSON_INPUT(105, "Invalid JSON input or JSON format"),
	
	UNRECOGNIZED_FIELD(106, "Check sequence of fields or field name"),
	
	RESOURCE_NOT_FOUND(108, "Does not exist"),
	
	RESOURCE_ALREADY_EXISTS(110, "Already exists"),

	INVALID_COLUMN_NAME(111, "Invalid column name provided"),
	
	WRONG_DATA_TYPE(112, "Wrong datatype selected for non multivalued field"),
	
	IO_EXCEPTION(113, "I/O exception occurred"), 
	
	JSON_PARSE_EXCEPTION(114, "JSON parse error occurred"), 
	
	INVALID_FIELD_VALUE(116, "Value for field : {} is not expected as : {}"), 
	
	CONNECTION_REFUSED(120, "Connection is refused from the server"), 
	
	INVALID_CREDENTIALS(121, "Invalid credentials provided"), 
	
	BAD_REQUEST_EXCEPTION(400, "Bad Request Occuured"),
	
	NULL_POINTER_EXCEPTION(500, "Received Null response"),
	
	SERVER_UNAVAILABLE(503, "Unable to Connect To the Server"),
	
	OPERATION_NOT_ALLOWED(405, "Operation is Not Allowed"),
	
	UNAUTHORIZED_EXCEPTION(401, "Unauthorized To Perform Request"),
	
	FORBIDDEN_EXCEPTION(403, "Forbidden access attempted"),
	
	INTERNAL_SERVER_ERROR (500, "Internal Server Error Occured"),
	
	PROCESSING_NOT_COMPLETED (202, "Request cannot be Processed"),
	
	NOT_ACCEPTABLE_ERROR (406, "Request Not accpetable"),

	SUCCESSFUL(200, "Request Successfull"),

	STUDENT_DELETED(200,"student deleted successfully"),
	STUDENT_UPDATED(200,"student updated successfully"),
	
	PAYMENT_DELETED(200,"Payment deleted successfully"),
	PAYMENT_UPDATED(200,"Payment updated successfully"),
	
	EDUCATIONAL_INSTITUTION_DELETED(200,"Educational Institution deleted successfully"),
	EDUCATIONAL_INSTITUTION_UPDATED(200,"Educational Institution updated successfully"),
	
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