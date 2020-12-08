package com.laptrinhjavaweb.dto;

public class BuildingSearchBuilder {
	private Long id;
	private String name;
	private Integer numberOfBasement;
	private Integer floorArea;
	private String district;
	private String ward;
	private String street;
	private String direction;
	private String level;
	
	private String[] buildingTypes;
	
	private Integer rentAreaFrom;
	private Integer rentAreaTo;
	private Integer rentPriceFrom;
	private Integer rentPriceTo;
	
	private String managerName;
	private String managerPhone;
	private String staffId;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public String getDistrict() {
		return district;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	public Integer getRentAreaFrom() {
		return rentAreaFrom;
	}

	public Integer getRentAreaTo() {
		return rentAreaTo;
	}

	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}

	public Integer getRentPriceTo() {
		return rentPriceTo;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public String getStaffId() {
		return staffId;
	}

	private BuildingSearchBuilder(Builder builder) {
		this.id=builder.id;
		this.name=builder.name;
		this.numberOfBasement=builder.numberOfBasement;
		this.floorArea=builder.floorArea;
		this.district=builder.district;
		this.ward=builder.ward;
		this.street=builder.street;
		this.direction=builder.direction;
		this.level=builder.level;
		
		this.buildingTypes=builder.buildingTypes;
		
		this.rentAreaFrom=builder.rentAreaFrom;
		this.rentAreaTo=builder.rentAreaTo;
		this.rentPriceFrom=builder.rentPriceFrom;
		this.rentPriceTo=builder.rentPriceTo;
		
		this.managerName=builder.managerName;
		this.managerPhone=builder.managerPhone;
		this.staffId=builder.staffId;
		
	}
		
//		private Computer(ComputerBuilder builder) {
//			this.HDD=builder.HDD;
//			this.RAM=builder.RAM;
//			this.isGraphicsCardEnabled=builder.isGraphicsCardEnabled;
//			this.isBluetoothEnabled=builder.isBluetoothEnabled;
//		}
		
		//Builder Class
		public static class Builder{

			private Long id;
			private String name;
			private Integer numberOfBasement;
			private Integer floorArea;
			private String district;
			private String ward;
			private String street;
			private String direction;
			private String level;		
			private String[] buildingTypes;
			
			private Integer rentAreaFrom;
			private Integer rentAreaTo;
			private Integer rentPriceFrom;
			private Integer rentPriceTo;
			
			private String managerName;
			private String managerPhone;
			private String staffId;

//			public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
//				this.isGraphicsCardEnabled = isGraphicsCardEnabled;
//				return this;
//			}
			public Builder setId(Long id) {
				this.id = id;
				return this;
			}

			public Builder setName(String name) {
				this.name = name;
				return this;
			}

			public Builder setNumberOfBasement(Integer numberOfBasement) {
				this.numberOfBasement = numberOfBasement;
				return this;
			}
		
			public Builder setFloorArea(Integer floorArea) {
				this.floorArea = floorArea;
				return this;
			}

			public Builder setDistrict(String district) {
				this.district = district;
				return this;
			}

			public Builder setWard(String ward) {
				this.ward = ward;
				return this;
			}

			public Builder setStreet(String street) {
				this.street = street;
				return this;
			}

			public Builder setDirection(String direction) {
				this.direction = direction;
				return this;
			}

			public Builder setLevel(String level) {
				this.level = level;
				return this;
			}

			public Builder setRentAreaFrom(Integer rentAreaFrom) {
				this.rentAreaFrom = rentAreaFrom;
				return this;
			}

			public Builder setRentAreaTo(Integer rentAreaTo) {
				this.rentAreaTo = rentAreaTo;
				return this;
			}

			public Builder setRentPriceFrom(Integer rentPriceFrom) {
				this.rentPriceFrom = rentPriceFrom;
				return this;
			}

			public Builder setRentPriceTo(Integer rentPriceTo) {
				this.rentPriceTo = rentPriceTo;
				return this;
			}

			public Builder setManagerName(String managerName) {
				this.managerName = managerName;
				return this;
			}

			public Builder setManagerPhone(String managerPhone) {
				this.managerPhone = managerPhone;
				return this;
			}

			public Builder setStaffId(String staffId) {
				this.staffId = staffId;
				return this;
			}
			
			public Builder setBuildingTypes(String[] buildingTypes) {
				this.buildingTypes = buildingTypes;
				return this;
			}

			public BuildingSearchBuilder build(){
				return new BuildingSearchBuilder(this);
			}

		}
}
