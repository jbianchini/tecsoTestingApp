package com.tecso.accounting.web.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tecso.accounting.model.entity.Account;
import com.tecso.accounting.model.entity.Move;
import com.tecso.accounting.model.service.AccountService;
import com.tecso.accounting.model.service.MoveService;

@RestController
public class ApplicationApi {

	@Autowired
	AccountService accountService;

	@Autowired
	MoveService moveService;

	@Autowired
	Mapper mapper;
	
	@GetMapping("/account")
	public AccountDTO getAccountByNumber(@RequestBody int number) throws Exception {
		// Metodo para consular una cuenta por su id
		return mapper.map(accountService.getByNumber(number), AccountDTO.class);
	}

	@GetMapping("/accounts")
	public List<AccountDTO> listAllAccounts() {
		// Obtener todas las cuentas de la base

		List<Account> accounts = accountService.listAll();
		return this.toDTOList(accounts, AccountDTO.class);

	}

	@PostMapping("/account")
	public AccountDTO saveAccount(@RequestBody @Valid AccountDTO accountDto) throws Exception {
		// Crear una cuenta nueva

		Account account = mapper.map(accountDto, Account.class);
		return mapper.map(accountService.save(account), AccountDTO.class);

	}

	@DeleteMapping("/account")
	public ResponseEntity<ResponseMessage> deleteAccount(@RequestBody int accountNumber) throws Exception {
		// Borrar una cuenta por su numero de cuenta.

		accountService.delete(accountNumber);

		return acceptResponse("La cuenta " + accountNumber + " ha sido eliminada.");

	}

	@PostMapping("/move")
	public MoveDTO saveMove(@RequestBody @Valid MoveDTO moveDto) throws Exception {
		// Crear movimiento
		Move move = mapper.map(moveDto, Move.class);
		return mapper.map(moveService.save(move), MoveDTO.class);
	}

	@GetMapping("/moves")
	public List<MoveDTO> getMoveByAccountNumber(@RequestBody int accountNumber) throws Exception {
		// Obtener todos los movimientos de una cuenta
		List<Move> list = moveService.listByAccountNumber(accountNumber);
		return toDTOList(list, MoveDTO.class);
	}

	private ResponseEntity<ResponseMessage> acceptResponse(String message) {
		ResponseMessage rsp = new ResponseMessage(message, null, null, null);
		return new ResponseEntity<ResponseMessage>(rsp, HttpStatus.ACCEPTED);
	}
	
	private <T, O> List<O> toDTOList(List<T> list, Class<O> class2) {
		// Transforma una lista de value objects en una lista de DTOs
		ArrayList<O> retList = new ArrayList<>();
		for (T t : list) {
			retList.add(mapper.map(t, class2));
		}

		return retList;
	}

}
