package com.server.manage.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(content = Include.NON_NULL)
public class Response {

	public LocalDateTime timeStamp ; 
	protected int statusCode ;
	protected HttpStatus status ;
	protected String raison ; 
	protected String message ;
	protected String developerMessage ; 
	protected HashMap<?, ?> data;
}
