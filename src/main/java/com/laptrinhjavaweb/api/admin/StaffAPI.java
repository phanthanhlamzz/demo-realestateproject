package com.laptrinhjavaweb.api.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.output.StaffOutput;
import com.laptrinhjavaweb.service.IUserService;

@RestController
public class StaffAPI {
	
	@Autowired
	IUserService UserService;
	
	@GetMapping(value = {"/api/admin/staffs/{buildingId}"})
	public List<StaffOutput> getStaffMapChecked(@PathVariable("buildingId") Long buildingId) {
		return UserService.getStaffsWithBuildingId(buildingId);	
	}
	@GetMapping(value = {"/api/admin/staffs"})
	public Map<Long,String> gerStaffMap() {
		return UserService.getStaffMap();
	}
}
