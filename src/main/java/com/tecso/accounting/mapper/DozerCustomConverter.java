package com.tecso.accounting.mapper;

import java.sql.Date;
import java.time.LocalDate;

import org.dozer.CustomConverter;

import com.tecso.accounting.model.entity.Move;
import com.tecso.accounting.model.service.AccountService;
import com.tecso.accounting.web.api.MoveDTO;

public class DozerCustomConverter implements CustomConverter {

	AccountService accountService = new AccountService();

	@Override
	public Object convert(Object dest, Object source, Class<?> destinationClass, Class<?> sourceClass) {
		if (source == null)
			return null;

		if (source instanceof MoveDTO) {
			MoveDTO moveDto = (MoveDTO) source;
			Date date = moveDto.get_date();

			if (date == null) {
				date = Date.valueOf(LocalDate.now());
			}

			Move move = new Move(0, Integer.valueOf(moveDto.get_accountNumber()), null, date, moveDto.get_type(),
					moveDto.get_description(), moveDto.get_amount());
			return move;

		} else if (source instanceof Move) {
			Move move = (Move) source;
			MoveDTO moveDto = new MoveDTO(move.get_moveId(), move.get_account().get_accountId(),
					String.valueOf(move.get_account().get_accountNumber()), move.get_date(), move.get_type(),
					move.get_description(), move.get_amount());

			return moveDto;
		}

		return null;
	}

}
