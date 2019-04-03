package com.tecso.accounting.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Move implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="moveSequence" ,strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name = "moveSequence", allocationSize = 1)
	private long _moveId;
	
	@ManyToOne
	@JoinColumn(name="_account_id", referencedColumnName = "_accountId")
	@NotNull
	private Account _account;
	
	private int _accountNumber;
	private Date _date;
	private String _type;
	private String _description;
	private double _amount;
	
	public Move() {
		
	}

	public Move(long moveId, int accountNumber, Account account, Date date, String type, String description, double amount) {
		
		this._moveId = moveId;
		this._accountNumber = accountNumber;
		this._account = account;
		this._date = date;
		this._type = type;
		this._description = description;
		this._amount = formatAmount(amount);
	}

	private double formatAmount(double amount) {
		BigDecimal bigDecimal = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
		return bigDecimal.doubleValue();
	}

	public long get_moveId() {
		return _moveId;
	}

	public void set_moveId(long _moveId) {
		this._moveId = _moveId;
	}
	
	public int get_accountNumber() {
		return _accountNumber;
	}

	public void set_accountNumber(int _accountNumber) {
		this._accountNumber = _accountNumber;
	}

	public Account get_account() {
		return _account;
	}

	public void set_account(Account _accountId) {
		this._account = _accountId;
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
		this._amount = formatAmount(_amount);
	}

	@Override
	public String toString() {
		return "Move [_moveId=" + _moveId + ", _account=" + _account.get_accountId() + ", _accountNumber=" + _accountNumber + ", _date="
				+ _date + ", _type=" + _type + ", _description=" + _description + ", _amount=" + _amount + "]";
	}

	
	
	

}
