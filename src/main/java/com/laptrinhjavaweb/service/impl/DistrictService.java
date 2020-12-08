package com.laptrinhjavaweb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.service.IDistrictService;

import enums.DistrictEnum;

@Service
public class DistrictService implements IDistrictService{

	@Override
	public Map<String, String> getDistricts() {
		Map<String,String> districtMap = new HashMap<String, String>();
		for(DistrictEnum districtEnum:DistrictEnum.values()) {
			districtMap.put(districtEnum.name(), districtEnum.getValue());
		}
		return districtMap;
	}

}
