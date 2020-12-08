package com.laptrinhjavaweb.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.input.CustomerSearchInput;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;

@Controller
public class CustomerController {
	@Autowired
	IUserService userService;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	ITransactionService transactionService;
	
	// Customer List Page
	@GetMapping(value = {"/admin/customer-list"})
	public ModelAndView customerListPage() {
		ModelAndView modelAndView = new ModelAndView("admin/customer/list");
		modelAndView.addObject("staffs", userService.getStaffMap());
		modelAndView.addObject("customerModelAttribute", new CustomerSearchInput());
		return modelAndView;
	}
	
	@GetMapping(value = {"/admin/customer-list"},params = {"fullName"})
	public ModelAndView customerListPage(@ModelAttribute("customerModelAttribute") CustomerSearchInput customerSearchInput) {
		ModelAndView modelAndView = new ModelAndView("admin/customer/list");
		modelAndView.addObject("staffs", userService.getStaffMap());
		modelAndView.addObject("customerModelAttribute",customerSearchInput);
		
		List<CustomerDTO> customerDTOs = customerService.searchCustomer(customerSearchInput);
		modelAndView.addObject("customerDTOs", customerDTOs);
		
		return modelAndView;
	}
	
	// Customer Addition Page
	@GetMapping(value = {"/admin/customer-addition"})
	public ModelAndView customerAdditionPage() {
		ModelAndView modelAndView = new ModelAndView("admin/customer/addition");
		CustomerDTO customerDTO = new CustomerDTO();
		modelAndView.addObject("customerModelAttribute",  customerDTO);
		return modelAndView;
	}
	
	// Customer Update Page
	@GetMapping(value = {"/admin/customer-edit/{customerId}"})
	public ModelAndView customerEditPage(@PathVariable("customerId") Long customerId) {
		ModelAndView modelAndView = new ModelAndView("admin/customer/update");
		modelAndView.addObject("customerModelAttribute",  customerService.getCustomerById(customerId));
		modelAndView.addObject("TransactionTypes",transactionService.getAllTransactionTypes());
		return modelAndView;
	}
}
