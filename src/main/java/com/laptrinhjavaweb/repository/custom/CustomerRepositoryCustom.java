package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.dto.input.CustomerSearchInput;
import com.laptrinhjavaweb.entity.CustomerEntity;

public interface CustomerRepositoryCustom {
	List<CustomerEntity> getCustomers(CustomerSearchInput customerSearchInput);
}
