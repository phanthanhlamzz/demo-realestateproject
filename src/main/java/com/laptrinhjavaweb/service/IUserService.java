package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.output.StaffOutput;

public interface IUserService {
    UserDTO findOneByUserNameAndStatus(String name, int status);
    Map<Long,String> getStaffMap();
    Map<Long,String> getStaffMapChecked(Long buildingId);
    List<StaffOutput> getStaffsWithBuildingId(Long buildingId);
}
