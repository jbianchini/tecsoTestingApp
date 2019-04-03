package com.tecso.accounting.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tecso.accounting.model.entity.Account;
import com.tecso.accounting.model.entity.Move;
import com.tecso.accounting.persistence.dao.MoveRepository;

@Service
public class MoveService {

	@Autowired
	MoveRepository dao;

	@Autowired
	AccountService accountService;

	public Move save(Move newMove) throws Exception {
		/*
		 * Obtiene la cuenta al que referencia el numero de cuenta del movimiento nuevo.
		 * Valida el movimiento y luego actualiza el balance de la cuenta. Luego
		 * persiste.
		 */
		Account account = accountService.getByNumber(newMove.get_accountNumber());
		newMove.set_account(account);

		if (!validType(newMove.get_type())) {
			throw new ServiceException("ERROR: El movimiento es de un tipo inválido. (DEBIT | CREDIT)");
		}

		else if (exceedLimit(newMove)) {
			throw new ServiceException("ERROR: El movimiento genera un descubierto mayor al límite permitido.");
		} else {
			Double updatedBalance = accountService.getUpdatedBalance(newMove);
			newMove.get_account().set_balance(updatedBalance);

			return dao.saveAndFlush(newMove);
		}
	}

	public Move getById(long id) {
		return dao.getOne(id);
	}

	public List<Move> listByAccountNumber(int accountNumber) throws Exception {
		accountService.getByNumber(accountNumber);
		
		List<Move> movesList = dao.findBy_accountNumber(accountNumber, Sort.by(Direction.DESC, "_date"));
		return movesList;
	}

	private boolean exceedLimit(Move newMove) {
		// Evalua si el movimiento genera un descubierto que excede el límite
		Double updatedBalance = accountService.getUpdatedBalance(newMove);
		String accountCurrency = newMove.get_account().get_currency();

		if (accountCurrency.equals("ARS") && updatedBalance < -1000
				|| accountCurrency.equals("USD") && updatedBalance < -300
				|| accountCurrency.equals("EUR") && updatedBalance < -150) {
			return true;

		}

		return false;
	}

	private boolean validType(String type) {
		if (type.equals("DEBIT") || type.equals("CREDIT")) {
			return true;
		}
		return false;
	}

}
