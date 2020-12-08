package com.laptrinhjavaweb.service.impl;

import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.service.ITransactionService;

import enums.TransactionEnum;

@Service
public class TransactionService implements ITransactionService {

	@Override
	public TransactionEnum[] getAllTransactionTypes() {	
		return TransactionEnum.values();
	}

}
