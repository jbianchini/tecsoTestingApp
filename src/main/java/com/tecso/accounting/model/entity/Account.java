package com.tecso.accounting.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="accountSequence" ,strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name = "accountSequence", allocationSize = 1)
	private long _accountId;
	
	private int _accountNumber;
	private String _currency;
	
	@OneToMany(mappedBy = "_account", cascade=CascadeType.ALL)
	private List<Move> _moves;
	
	private double _balance;
	
	public Account() {
		
	}

	public Account(long _accountId, int _accountNumber, String _currency, double _balance) {
		this._accountId = _accountId;
		this._accountNumber = _accountNumber;
		this._currency = _currency;
		this._balance = formatAmount(_balance);
	}
	
	private double formatAmount(double amount) {
		BigDecimal bigDecimal = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
		return bigDecimal.doubleValue();
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
		this._balance = formatAmount(_balance);
	}

	public List<Move> getMoves() {
		return _moves;
	}

	public void setMoves(List<Move> moves) {
		this._moves = moves;
	}

	@Override
	public String toString() {
		return "Account [_accountId=" + _accountId + ", _accountNumber=" + _accountNumber + ", _currency=" + _currency
				+ ", _moves=" + _moves + ", _balance=" + _balance + "]";
	}

	
	
	
	

}
