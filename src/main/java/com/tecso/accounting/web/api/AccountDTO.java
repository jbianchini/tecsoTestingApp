package com.tecso.accounting.web.api;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountDTO {
	
	private long _accountId;
	
	@NotNull(message="El numero de cuenta es requerido")
	@Min(1)
	private int _accountNumber;
	
	@Pattern(regexp = "(ARS|USD|EUR)",message="Tipo de moneda inválido. (ARS|USD|EUR)")
	@NotNull(message="El tipo de moneda es requerido.")
	private String _currency;
	
	private List<MoveDTO> _moves;
	private double _balance;
	
	public AccountDTO (){
		
	}

	public AccountDTO(long _accountId, int _accountNumber, String _currency, double _balance) {
		this._accountId = _accountId;
		this._accountNumber = _accountNumber;
		this._currency = _currency;
		this._balance = _balance;
	}

	public long get_accountId() {
		return _accountId;
	}

	public void set_accountId(long _accountId) {
		this._accountId = _accountId;
	}

	public int get_accountNumber() {
		return _accountNumber;
	}

	public void set_accountNumber(int _accountNumber) {
		this._accountNumber = _accountNumber;
	}

	public String get_currency() {
		return _currency;
	}

	public void set_currency(String _currency) {
		this._currency = _currency;
	}

	public double get_balance() {
		return _balance;
	}

	public void set_balance(double _balance) {
		this._balance = _balance;
	}

	public List<MoveDTO> get_moves() {
		return _moves;
	}

	public void set_moves(List<MoveDTO> _moves) {
		this._moves = _moves;
	}

	@Override
	public String toString() {
		return "AccountDTO [_accountId=" + _accountId + ", _accountNumber=" + _accountNumber + ", _currency="
				+ _currency + ", _balance=" + _balance + "]";
	}
	
	

}
