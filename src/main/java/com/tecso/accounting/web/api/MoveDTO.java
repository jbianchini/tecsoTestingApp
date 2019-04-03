package com.tecso.accounting.web.api;

import java.sql.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MoveDTO {
	
	private long _moveId;
	private long _accountId;
	
	@Pattern(regexp = "^[0-9]*[1-9][0-9]*$", message="El numero de cuenta debe ser un numero natural. Ejemplo: 10100")
	@NotNull(message="El numero de cuenta es requerido")
	private String _accountNumber;
	
	private Date _date;
	
	@Pattern(regexp = "(DEBIT|CREDIT)",message="Tipo de movimiento inválido. (DEBIT | CREDIT)")
	@NotNull(message="El tipo de movimiento es requerido.")
	private String _type;
	
	@Size(max = 200)
	private String _description;
	
	@DecimalMin(value = "0.00")
	private double _amount;
	
	public MoveDTO() {
		
	}

	public MoveDTO(long _moveId, long _accountId, String _accountNumber, Date _date, String _type, String _description, double _amount) {
		super();
		this._moveId = _moveId;
		this._accountId = _accountId;
		this._accountNumber = _accountNumber;
		this._date = _date;
		this._type = _type;
		this._description = _description;
		this._amount = _amount;
	}

	public String get_accountNumber() {
		return _accountNumber;
	}

	public void set_accountNumber(String _accountNumber) {
		this._accountNumber = _accountNumber;
	}

	public long get_moveId() {
		return _moveId;
	}

	public void set_moveId(long _moveId) {
		this._moveId = _moveId;
	}

	public long get_accountId() {
		return _accountId;
	}

	public void set_accountId(long _accountId) {
		this._accountId = _accountId;
	}

	public Date get_date() {
		return _date;
	}

	public void set_date(Date _date) {
		this._date = _date;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public double get_amount() {
		return _amount;
	}

	public void set_amount(double _amount) {
		this._amount = _amount;
	}

	@Override
	public String toString() {
		return "MoveDTO [_moveId=" + _moveId + ", _accountId=" + _accountId + ", _date=" + _date + ", _type=" + _type
				+ ", _description=" + _description + ", _amount=" + _amount + "]";
	}
	
	

}
