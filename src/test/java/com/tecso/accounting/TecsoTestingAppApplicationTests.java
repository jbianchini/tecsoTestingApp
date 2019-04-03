package com.tecso.accounting;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tecso.accounting.model.entity.Account;
import com.tecso.accounting.model.entity.Move;
import com.tecso.accounting.model.service.AccountService;
import com.tecso.accounting.model.service.MoveService;
import com.tecso.accounting.model.service.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("/test-mysql.sql")
public class TecsoTestingAppApplicationTests {

	@Autowired
	MoveService moveService;

	@Autowired
	AccountService accountService;

	@Test
	public void createAccountTest()  {
		// Se puede crear una cuenta nueva
		Account newAccount = new Account(1, 3293, "EUR", 203940.22);
		Account saved = null;
		try {
			saved = accountService.save(newAccount);
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
		assert(saved.get_accountId() == newAccount.get_accountId());
		assert(saved.get_accountNumber() == newAccount.get_accountNumber());
		assert(saved.get_currency() == newAccount.get_currency());
		assert(saved.get_balance() == newAccount.get_balance());
	}

	@Test(expected=ServiceException.class)
	public void cannotCreateDuplicatedAccountTest() throws ServiceException {
		// No se puede crear una cuenta con numero duplicado
		Account newAccount1 = new Account(1, 3293, "EUR", 12.22);
		Account newAccount2 = new Account(2, 3293, "EUR", 2355.99);
		accountService.save(newAccount1);
		accountService.save(newAccount2);
	}

	@Transactional
	@Test(expected=ServiceException.class)
	public void cannotDeleteAnAccountTest() throws Exception {
		// No se puede borrar una cuenta con movimientos
		accountService.delete(10010);
		
		// No se puede borrar una cuenta que no existe
		accountService.delete(201230);
	}
	
	@Transactional
	@Test
	public void canDeleteAnAccountTest() {
		// Se puede borrar una cuenta sin movimientos
		try {
			accountService.delete(10040); 
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	public void thereAreFourAccountsTest() {
		// Se pueden listar cuentas
		assert (accountService.listAll().size() == 4);
		assert (accountService.listAll().size() != 2);
	}

	@Test
	@Transactional
	public void addingAMoveUpdatesBalanceTest() {
		// Se puede agregar un movimiento a una cuenta, cuando ocurre se actualiza el
		// saldo de la cuenta
		
		try {
			Account anAccount = accountService.getByNumber(10040);
			Move newMove = new Move(1, anAccount.get_accountNumber(), anAccount, Date.valueOf(LocalDate.now()), "CREDIT", "TEST", 500);

			double actualBalance = anAccount.get_balance();
			double updatedBalance = accountService.getUpdatedBalance(newMove);
			
			assert(actualBalance < updatedBalance);
			
			moveService.save(newMove);
			actualBalance = anAccount.get_balance();
			
			assert(actualBalance == updatedBalance);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Transactional
	@Test(expected = ServiceException.class)
	public void rejectMovesWithGreaterDebtTest() throws Exception {
		// Se rechazan los movimientos que generen un descubierto mayor al límite

		Account anAccount1 = accountService.getByNumber(10010);
		Account anAccount2 = accountService.getByNumber(10020);
		Account anAccount3 = accountService.getByNumber(10040);
		Move newMove1 = new Move(1, anAccount1.get_accountNumber(), anAccount1, Date.valueOf(LocalDate.now()), "DEBIT",
				"TEST", 1300);
		Move newMove2 = new Move(1, anAccount2.get_accountNumber(), anAccount2, Date.valueOf(LocalDate.now()), "DEBIT",
				"TEST", 1000);
		Move newMove3 = new Move(1, anAccount3.get_accountNumber(), anAccount3, Date.valueOf(LocalDate.now()), "DEBIT",
				"TEST", 900);

		moveService.save(newMove1);
		moveService.save(newMove2);
		moveService.save(newMove3);

	}
	
	private void print(Object data) {
		System.out.println("\n" + data.toString() + "\n");
	}

}
