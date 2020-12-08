package com.laptrinhjavaweb.dto.input;

public class BuildingAssignmentInput {
	private Long buildingId;
	private Long[] staffId;
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Long[] getStaffId() {
		return staffId;
	}
	public void setStaffId(Long[] staffId) {
		this.staffId = staffId;
	}
	
}
