package com.tecso.accounting.web.api;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tecso.accounting.model.service.ServiceException;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> commonExceptionHandler(Exception ex, WebRequest request){
		ex.printStackTrace();
		ResponseMessage rsp = new ResponseMessage(ex.toString(), request.getDescription(false), "Error de aplicación. Contacte al administrador.", LocalDateTime.now());
		return new ResponseEntity<>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ResponseMessage> serviceExceptionHandler(ServiceException ex, WebRequest request){
        ResponseMessage rsp = new ResponseMessage(ex.getMessage(), request.getDescription(false), "Error de solicitud. Compruebe los datos ingresados e intente nuevamente.", LocalDateTime.now());
        return new ResponseEntity<>(rsp, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessage> invalidArgumentExceptionHandler(MethodArgumentNotValidException ex, WebRequest request){
		ObjectError error = ex.getBindingResult().getAllErrors().get(0);
		FieldError field = ex.getBindingResult().getFieldError();
		ResponseMessage rsp = new ResponseMessage(field.getField() + " " + error.getDefaultMessage(), request.getDescription(false), "Error de entrada. Compruebe los datos ingresados e intente nuevamente.", LocalDateTime.now());
		return new ResponseEntity<>(rsp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseMessage> messageNotReadableExceptionHandler(HttpMessageNotReadableException ex, WebRequest request) {
		String error = ex.getCause().getMessage();
		ResponseMessage rsp = new ResponseMessage(error, request.getDescription(false), "Error de formato. Compruebe los datos ingresados.", LocalDateTime.now());
		return new ResponseEntity<>(rsp, HttpStatus.BAD_REQUEST);
	}
	
}
