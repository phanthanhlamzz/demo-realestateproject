package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.output.StaffOutput;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO findOneByUserNameAndStatus(String name, int status) {
        return userConverter.convertToDto(userRepository.findOneByUserNameAndStatus(name, status));
    }

	@Override
	public Map<Long, String> getStaffMap() {
		Map<Long,String> staffMap = new HashMap<Long, String>();
		List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
		for(UserEntity staff : staffs) {
			staffMap.put(staff.getId(), staff.getFullName());
		}
		return staffMap;
	}

	@Override
	public Map<Long, String> getStaffMapChecked(Long buildingId) {
		Map<Long,String> staffMap = new HashMap<Long, String>();
		List<UserEntity> staffs = userRepository.findByBuildings_Id(buildingId);
		for(UserEntity staff : staffs) {
			staffMap.put(staff.getId(), staff.getFullName());
		}
		return staffMap;
	}

	@Override
	@Transactional
	public List<StaffOutput> getStaffsWithBuildingId(Long buildingId) {
		List<UserEntity> staffsWithBuildingIds = userRepository.findByBuildings_Id(buildingId);
		List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
		List<StaffOutput> StaffOutputs = new ArrayList<StaffOutput>();
		for(UserEntity staff : staffs) {
			StaffOutput staffOutput = new StaffOutput();
			staffOutput.setId(staff.getId());
			staffOutput.setName(staff.getFullName());
			for(UserEntity staffsWithBuilding:staffsWithBuildingIds) {
				if(staff.getId() == staffsWithBuilding.getId()) {
					staffOutput.setChecked(true);
					break;
				}
			}
			StaffOutputs.add(staffOutput);
		}
		return StaffOutputs;
	}
}
