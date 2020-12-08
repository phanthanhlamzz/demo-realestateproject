package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom{
	List<CustomerEntity> findByFullNameAndEmailAndPhoneAndStaffs_Id(String fullName,String email,String phone,Long staffId);
	List<CustomerEntity> findByFullName(String fullName);
	List<CustomerEntity> findByFullNameAndPhone(String fullName ,String phone);

}
