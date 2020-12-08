package com.laptrinhjavaweb.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
	private static final long serialVersionUID = -3629040282325497638L;
	
	@Column(name="fullname")
	private String fullName;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "assignmentcustomer",joinColumns = {@JoinColumn(name="customerid")},
				inverseJoinColumns = {@JoinColumn(name="staffid")}
			)
	private Set<UserEntity> staffs = new HashSet<UserEntity>();
	
	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
	private Set<TransactionEntity> transactions = new HashSet<TransactionEntity>();
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserEntity> getStaff() {
		return staffs;
	}

	public void setStaff(Set<UserEntity> staff) {
		this.staffs = staff;
	}

	public Set<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<TransactionEntity> transactions) {
		this.transactions = transactions;
	}
	
	
	
}
