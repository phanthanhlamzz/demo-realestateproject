package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.dto.input.CustomerSearchInput;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom  {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerEntity> getCustomers(CustomerSearchInput customerSearchInput) {
		StringBuilder sql = new StringBuilder("");
		sql = buildSQL(sql,customerSearchInput);
		Query query= entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
		return query.getResultList();
	}
	private StringBuilder buildSQL(StringBuilder sql,CustomerSearchInput customerSearchInput) {
		/*
		 SELECT * FROM springbootwebbatdongsan.customer cus WHERE 
			1=1 
			AND cus.email LIKE '%%' 
			AND cus.fullname LIKE '%%' 
			AND cus.phone LIKE '%%'
			AND EXISTS(SELECT * FROM assignmentcustomer ass WHERE cus.id = ass.customerid AND ass.staffId = 2 )
			;

		 * */
		sql.append("SELECT * FROM customer cus WHERE 1=1");
		Class<CustomerSearchInput> clazzCustomerSearchInput = CustomerSearchInput.class;
		try {
			
			for(Field field : clazzCustomerSearchInput.getDeclaredFields()) {
				field.setAccessible(true);
				if(!field.getName().equalsIgnoreCase("staffId") 
					&& field.get(customerSearchInput) != null
					&& !((String)field.get(customerSearchInput)).equalsIgnoreCase("")) {
					
					sql.append(" AND cus."+field.getName()+" LIKE '%"+(String) field.get(customerSearchInput)+"%'");
				}
			}
			
			// handling staffId
			if(customerSearchInput.getStaffId() != null) {
				sql.append(" AND EXISTS(SELECT * FROM assignmentcustomer ass WHERE cus.id = ass.customerid AND ass.staffId = "+customerSearchInput.getStaffId()+" )");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return sql;
	}
}
