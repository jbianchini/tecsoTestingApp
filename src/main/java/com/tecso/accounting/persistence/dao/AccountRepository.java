package com.tecso.accounting.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecso.accounting.model.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findBy_accountNumber(int number);
	boolean existsBy_accountNumber(int number);
}
