package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.input.CustomerSearchInput;
import com.laptrinhjavaweb.dto.output.StaffOutput;

public interface ICustomerService {
	CustomerDTO saveCustomer(CustomerDTO customerDTO);
	List<CustomerDTO> searchCustomer(CustomerSearchInput customerSearchInput);
	List<StaffOutput> getStaffsWithCustomerId(Long customerId);
	Boolean assignCustomerForStaff(Long[] staffIds,Long customerId);
	CustomerDTO getCustomerById(Long customerId);
	CustomerDTO updateCustomer(CustomerDTO customerDTO);
	List<TransactionDTO> getTransactionByCodeAndIdCustomer(String code,Long customerId);
	TransactionDTO saveTransaction(String code,Long customerId,String note);
	Boolean deleteCustomer(Long customerId);
}
