package com.tecso.accounting.persistence.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tecso.accounting.model.entity.Move;

public interface MoveRepository extends JpaRepository<Move, Long>{
	public List<Move> findBy_accountNumber(int accountNumber, Sort sort);
	
	
}
