package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
	private static final long serialVersionUID = -8625792675268444448L;

	@Column(name="name")
	private String name;
	
	@Column(name="numberofbasement")
	private Integer numberOfBasement;
	
	@Column(name="district")
	private String district;
	
	@Column(name="ward")
	private String ward;
	
	@Column(name="street")
	private String street;
	
	@Column(name="direction")
	private String direction;
	
	@Column(name="level")
	private String level;
	
	@Column(name="floorarea")
	private Integer floorArea;
	
	@Column(name = "buildingtype")
	private String buildingType;
	
	@Column(name="rentprice")
	private Integer rentPrice;
	
	@Column(name="rentpricedescription")
	private String rentPriceDescription;
	
	@Column(name="servicefee")
	private String serviceFee;
	
	@Column(name="managername")
	private String managerName;
	
	@Column(name="managerphone")
	private String managerPhone;
	
	
	@OneToMany(mappedBy = "building",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<RentAreaEntity> rentAreas =new ArrayList<RentAreaEntity>(); 
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "assignmentbuilding",
		joinColumns = @JoinColumn(name="buildingid"),
		inverseJoinColumns = @JoinColumn(name="staffid")
			)
	private Set<UserEntity> staffs = new HashSet<UserEntity>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public Integer getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getRentPriceDescription() {
		return rentPriceDescription;
	}

	public void setRentPriceDescription(String rentPriceDescription) {
		this.rentPriceDescription = rentPriceDescription;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public List<RentAreaEntity> getRentAreas() {
		return rentAreas;
	}

	public void setRentAreas(List<RentAreaEntity> rentAreas) {
		this.rentAreas = rentAreas;
	}

	public Set<UserEntity> getStaffs() {
		return staffs;
	}

	public void setStaffs(Set<UserEntity> staffs) {
		this.staffs = staffs;
	}
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	//private Set<UserEntity> staffs;
	public void removeAllStaffs() {
		this.staffs.removeAll(this.staffs);
	}
	//private Set<RentAreaEntity> rentAreas; 
//	public void removeAllRentAreas() {
//		this.rentAreas.removeAll(this.rentAreas);
//	}
	
}
