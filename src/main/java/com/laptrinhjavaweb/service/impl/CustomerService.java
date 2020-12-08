package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.input.CustomerSearchInput;
import com.laptrinhjavaweb.dto.output.StaffOutput;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(customerRepository.save(modelMapper.map(customerDTO,CustomerEntity.class)),CustomerDTO.class);	
	}

	@Override
	@Transactional
	public List<CustomerDTO> searchCustomer(CustomerSearchInput customerSearchInput) {
		//List<CustomerEntity> customerEntities = customerReposity.findByFullNameAndEmailAndPhoneAndStaffs_Id(customerSearchInput.getFullName(), customerSearchInput.getEmail(), customerSearchInput.getPhone(), customerSearchInput.getStaffId());
		//List<CustomerEntity> customerEntities = customerReposity.findByFullNameAndPhone(customerSearchInput.getFullName(), customerSearchInput.getPhone());
		List<CustomerEntity> customerEntities = customerRepository.getCustomers(customerSearchInput);
		ModelMapper modelMapper = new ModelMapper();
		List<CustomerDTO> customerDTOs= new ArrayList<CustomerDTO>();
		
		for(CustomerEntity customerEntity : customerEntities) {
			customerDTOs.add(modelMapper.map(customerEntity,CustomerDTO.class));
		}
		
		return customerDTOs;
	}

	@Override
	public List<StaffOutput> getStaffsWithCustomerId(Long customerId) {
		List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
		List<UserEntity> staffsWithCustomerIds= userRepository.findByCustomers_Id(customerId);
		List<StaffOutput> StaffOutputs = new ArrayList<StaffOutput>();
		for(UserEntity staff : staffs) {
			StaffOutput staffOutput = new StaffOutput();
			staffOutput.setId(staff.getId());
			staffOutput.setName(staff.getFullName());
			for(UserEntity staffsWithBuilding:staffsWithCustomerIds) {
				if(staff.getId() == staffsWithBuilding.getId()) {
					staffOutput.setChecked(true);
					break;
				}
			}
			StaffOutputs.add(staffOutput);
		}
		return StaffOutputs;
	}

	@Override
	@Transactional
	public Boolean assignCustomerForStaff(Long[] staffIds, Long customerId) {
		CustomerEntity customerEntity = customerRepository.findOne(customerId);
		Set<UserEntity> userEntities = customerEntity.getStaff();
		userEntities.removeAll(userEntities);
		for(Long staffId : staffIds) {
			UserEntity userEntity = userRepository.findOne(staffId);
			userEntities.add(userEntity);
		}
		customerRepository.save(customerEntity);
		return true;
	}

	@Override
	public CustomerDTO getCustomerById(Long customerId) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(customerRepository.findOne(customerId),CustomerDTO.class);
	}

	@Override
	@Transactional
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerEntity customerEntity = customerRepository.findOne(customerDTO.getId());
		customerEntity.setFullName(customerDTO.getFullName());
		customerEntity.setEmail(customerDTO.getEmail());
		customerEntity.setPhone(customerDTO.getPhone());
		
//		Set<TransactionEntity> newTransactionEntities = new HashSet<TransactionEntity>();
//		TransactionEntity sample = new TransactionEntity();
//		sample.setCustomer(customerEntity);
//		sample.setNote("tesst ok");
//		newTransactionEntities.add(sample);
		//transactionRepository.
		//customerEntity.setTransactions(transactionEntities);
		//CustomerEntity customerEntity = customerRepository.save(modelMapper.map(customerDTO,CustomerEntity.class));
		/*for(TransactionEntity item : customerEntity.getTransactions()) {
			item.setCustomer(null);
		}
		customerEntity.getTransactions().removeAll(customerEntity.getTransactions());
		
		customerEntity.getTransactions().add(sample);
		//customerEntity.setTransactions(newTransactionEntities);*/
		
		customerRepository.save(customerEntity);
		return modelMapper.map(customerEntity,CustomerDTO.class);
	}

	@Override
	public List<TransactionDTO> getTransactionByCodeAndIdCustomer(String code, Long customerId) {
		List<TransactionEntity> transactionEntities = transactionRepository.findByCodeAndCustomer_Id(code, customerId);
		List<TransactionDTO> TransactionDTOs = new ArrayList<TransactionDTO>();
		ModelMapper modelMapper = new ModelMapper();
		for(TransactionEntity transactionEntity : transactionEntities) {
			TransactionDTOs.add(modelMapper.map(transactionEntity,TransactionDTO.class));
		}
		return TransactionDTOs;
	}

	@Override
	public TransactionDTO saveTransaction(String code, Long customerId,String note) {
		ModelMapper modelMapper = new ModelMapper();
		TransactionEntity transactEntity = new TransactionEntity();
		transactEntity.setCode(code);
		transactEntity.setCustomer(customerRepository.findOne(customerId));
		transactEntity.setNote(note);
		return modelMapper.map(transactionRepository.save(transactEntity),TransactionDTO.class);
	}

	@Override
	@Transactional
	public Boolean deleteCustomer(Long customerId) {
		CustomerEntity customerEntity= customerRepository.findOne(customerId);
		customerEntity.getStaff().removeAll(customerEntity.getStaff());
		transactionRepository.delete(customerEntity.getTransactions());
		customerRepository.delete(customerEntity);
		return true;
	}

}
