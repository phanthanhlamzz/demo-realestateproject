package com.laptrinhjavaweb.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.input.BuildingAssignmentInput;
import com.laptrinhjavaweb.dto.input.CustomerAssignmentInput;
import com.laptrinhjavaweb.dto.output.StaffOutput;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.IUserService;

@RestController
public class CustomerAPI {
	
	@Autowired
	ICustomerService customerService;
	
	
	@PostMapping(value = {"/api/admin/customer/save"})
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomer(customerDTO);
	}
	
	@GetMapping(value = {"/api/admin/customers/{customerId}"})
	public List<StaffOutput> getStaffMapChecked(@PathVariable("customerId") Long customerId) {
		return customerService.getStaffsWithCustomerId(customerId);
	}
	
	// Chức năng giao customer
	@PutMapping("/api/admin/customer/assignment-staff")
	public Boolean assignCustomerForStaff(@RequestBody CustomerAssignmentInput customerAssignmentInput) {
		return customerService.assignCustomerForStaff(customerAssignmentInput.getStaffId(), customerAssignmentInput.getCustomerId());
	}
	
	// chức năng cập nhật customer 
	@PutMapping("/api/admin/customer/update")
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.updateCustomer(customerDTO);
	}
	
	// chức năng xóa customer
	@DeleteMapping(value = {"/api/admin/customer-delete"})
	public Boolean deleteCustomers(@RequestBody Long[] customerIds) {
		for(Long customerId : customerIds) {
			customerService.deleteCustomer(customerId);
		}
		return true;
	}
}
