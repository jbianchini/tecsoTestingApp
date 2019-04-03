package com.tecso.accounting.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecso.accounting.model.entity.Account;
import com.tecso.accounting.model.entity.Move;
import com.tecso.accounting.persistence.dao.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository dao;

	public Account save(Account account) throws ServiceException {
		if (dao.existsBy_accountNumber(account.get_accountNumber())) {
			throw new ServiceException(
					"ERROR: Ya existe una cuenta con número de cuenta: " + account.get_accountNumber());
		}
		return dao.saveAndFlush(account);
	}

	public void delete(int accountNumber) throws Exception {
		Account account = getByNumber(accountNumber);

		if (!account.getMoves().isEmpty()) {
			throw new ServiceException("ERROR: No puede eliminarse una cuenta con movimientos asociados.");
		} else {
			dao.delete(account);
		}
	}

	public Account getByNumber(int accountNumber) throws Exception {
		if (!dao.existsBy_accountNumber(accountNumber)) {
			throw new ServiceException("ERROR: No existen cuentas con el numero " + accountNumber);
		}
		return dao.findBy_accountNumber(accountNumber);

	}

	public List<Account> listAll() {
		return dao.findAll();
	}

	public double getUpdatedBalance(Move move) {
		Account account = move.get_account();

		if (move.get_type().equals("DEBIT")) {
			return account.get_balance() - move.get_amount();
		} else if (move.get_type().equals("CREDIT")) {
			return account.get_balance() + move.get_amount();
		}
		return 0;
	}
}
