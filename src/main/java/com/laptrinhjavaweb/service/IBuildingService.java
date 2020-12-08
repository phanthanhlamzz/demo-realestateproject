package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchBuilder;

public interface IBuildingService {
	Map<String,String> getBuildingTypes();
	List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
	BuildingDTO saveBuilding(BuildingDTO buildingDTO);
	void deleteBuildings(List<Long> buildingIds);
	Boolean assignBuildingForStaff(Long[] staffIds,Long buildingId);
	BuildingDTO getBuildingById(Long buildingId);
	BuildingDTO updateBuilding(BuildingDTO buildingDTO);
}
