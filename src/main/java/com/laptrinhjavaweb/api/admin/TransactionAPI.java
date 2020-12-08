package com.laptrinhjavaweb.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.input.TransactionInput;
import com.laptrinhjavaweb.service.ICustomerService;

@RestController
public class TransactionAPI {
	
	@Autowired
	ICustomerService customerService;
	
	@GetMapping(value = {"/api/admin/transactions"})
	public List<TransactionDTO> getTransactionByCodeAndId(@RequestParam("code") String code,@RequestParam("customerId") Long customerId){
		return customerService.getTransactionByCodeAndIdCustomer(code, customerId);
	}
	
	@PostMapping(value= {"/api/admin/transaction-addition"})
	public TransactionDTO saveTransaction(@RequestBody TransactionInput transactionInput) {
		return customerService.saveTransaction(transactionInput.getCode(),transactionInput.getCustomerId(), transactionInput.getNote());
	}
}
