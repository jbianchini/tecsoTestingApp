package com.tecso.accounting.web.api;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String _message;
	private String _messageType;
	private String _route;
	private LocalDateTime _timeStamp;

	public ResponseMessage() {

	}

	public ResponseMessage(String message, String route, String messageType, LocalDateTime date) {
		super();
		this._message = message;
		this._route = route;
		this._messageType = messageType;
		this._timeStamp = date;
	}

	public String get_message() {
		return _message;
	}

	public void set_message(String _message) {
		this._message = _message;
	}

	public String get_messageType() {
		return _messageType;
	}

	public void set_messageType(String _messageType) {
		this._messageType = _messageType;
	}

	public String get_route() {
		return _route;
	}

	public void set_route(String _route) {
		this._route = _route;
	}

	public LocalDateTime get_timeStamp() {
		return _timeStamp;
	}

	public void set_timeStamp(LocalDateTime _timeStamp) {
		this._timeStamp = _timeStamp;
	}

}
