package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.dto.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.input.BuildingAssignmentInput;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> getBuildings(BuildingSearchBuilder buildingSearchBuilder);
}
