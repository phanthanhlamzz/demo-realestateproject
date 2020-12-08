package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.input.BuildingAssignmentInput;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;

import enums.BuildingTypeEnum;

@Service
public class BuildingService implements IBuildingService {
	
	@Autowired
	BuildingRepository buildingRepository;
	
	@Autowired
	RentAreaRepository rentAreaRepository;
	
	@Autowired
	UserRepository userRepository;
	
	// Get Building Types
	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String,String> buildingTypeMap = new HashMap<String, String>();
		for(BuildingTypeEnum buildingTypeEnum:BuildingTypeEnum.values()) {
			buildingTypeMap.put( buildingTypeEnum.name(), buildingTypeEnum.getValue());
		}
		return buildingTypeMap;
	}
	
	// Get Buildings
	@Override
	public List<BuildingDTO> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		List<BuildingEntity> buildingEntityList = buildingRepository.getBuildings(buildingSearchBuilder);
		ModelMapper modelMapper = new ModelMapper();
		//modelMapper.map()
		List<BuildingDTO> result = buildingEntityList.stream()
		.map( buildingEntity -> (modelMapper.map( buildingEntity,BuildingDTO.class)))
		.collect(Collectors.toList());
		return result;
	}

	// Save buildings
	@Override
	@Transactional
	public BuildingDTO saveBuilding(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity buildingEntity= modelMapper.map(buildingDTO, BuildingEntity.class);
		if(buildingEntity.getRentAreas() != null) {
			for(RentAreaEntity rentAreaEntity:buildingEntity.getRentAreas()) {
				rentAreaEntity.setBuilding(buildingEntity);
			}
		}
		BuildingDTO result = modelMapper.map(buildingRepository.save(buildingEntity),BuildingDTO.class);
		return result;
	}

	
	// Delete buildings
	@Override
	@Transactional
	public void deleteBuildings(List<Long> buildingIds) {
		BuildingEntity buildingEntity=null;
		for(Long buildingId:buildingIds) {
			buildingEntity = buildingRepository.findOne(buildingId);
			//buildingEnity.removeAllRentAreas();
			buildingEntity.removeAllStaffs();
			rentAreaRepository.delete(buildingEntity.getRentAreas());
			buildingRepository.delete(buildingEntity);
			
		}
		
	}
	
	
	// Get building By Id
	@Override
	public BuildingDTO getBuildingById(Long buildingId) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity buildingEntity = buildingRepository.findOne(buildingId);
		BuildingDTO buildingDTO = modelMapper.map(buildingEntity,BuildingDTO.class);
		return buildingDTO;
	}

	
	// Update building
	@Override
	@Transactional
	public BuildingDTO updateBuilding(BuildingDTO buildingDTO) {
	/*	ModelMapper modelMapper = new ModelMapper();
		BuildingEntity newBuildingEntity = modelMapper.map(buildingDTO,BuildingEntity.class);
		//BuildingEntity buildingEntity=buildingRepository.findOne(newBuildingEntity.getId());
		//buildingEntity = mergeBuildingEntity(buildingEntity,newBuildingEntity);
		List<RentAreaEntity> rentAreaEntities = new ArrayList<RentAreaEntity>();
		for(RentAreaEntity item:newBuildingEntity.getRentAreas()) {
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setValue(item.getValue());
			rentAreaEntity.setBuilding(newBuildingEntity);
			rentAreaEntities.add(rentAreaEntity);
		}
		newBuildingEntity.setRentAreas(rentAreaEntities);
		newBuildingEntity = buildingRepository.save(newBuildingEntity);
		return modelMapper.map(newBuildingEntity,BuildingDTO.class);*/

		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity newBuildingEntity = modelMapper.map(buildingDTO,BuildingEntity.class);
		BuildingEntity buildingEntity=buildingRepository.findOne(newBuildingEntity.getId());
		Set<RentAreaEntity> buildingEntitiesForDelete = new HashSet<RentAreaEntity>();
		buildingEntity = mergeBuildingEntity(buildingEntity,newBuildingEntity,buildingEntitiesForDelete);
		rentAreaRepository.delete(buildingEntitiesForDelete);
		return modelMapper.map(buildingRepository.save(buildingEntity),BuildingDTO.class);
	}
//	private BuildingEntity mergeBuildingEntity(BuildingEntity buildingEntity,BuildingEntity newBuildingEntity) {
//		Class<BuildingEntity> clazzBuildingEntity = BuildingEntity.class;
//		for(Field field: clazzBuildingEntity.getDeclaredFields()) {
//			if(!field.getName().equalsIgnoreCase("rentAreas") &&
//				!field.getName().equalsIgnoreCase("staffs")	
//				) {
//				field.setAccessible(true);
//				try {
//				BeanUtils.setProperty(buildingEntity, field.getName(), field.get(newBuildingEntity));
//				}catch(Exception e) {e.printStackTrace();}
//			}
//		}
//		// handling rentAreas
//			buildingEntity.getRentAreas().removeAll(buildingEntity.getRentAreas());
//		 for(RentAreaEntity rentAreaEntity : newBuildingEntity.getRentAreas()) {
//			 rentAreaEntity.setBuilding(buildingEntity);
//			 //buildingEntity.getRentAreas().add(rentAreaEntity);
//		 }
//		 buildingEntity.setRentAreas(newBuildingEntity.getRentAreas());
//		 	 
//		return buildingEntity;
//	}
	
	private BuildingEntity mergeBuildingEntity(BuildingEntity buildingEntity,BuildingEntity newBuildingEntity,Set<RentAreaEntity> buildingEntitiesForDelete) {
		Class<BuildingEntity> clazzBuildingEntity = BuildingEntity.class;
		for(Field field: clazzBuildingEntity.getDeclaredFields()) {
			if(!field.getName().equalsIgnoreCase("rentAreas")) {
				field.setAccessible(true);
				try {
				BeanUtils.setProperty(buildingEntity, field.getName(), field.get(newBuildingEntity));
				}catch(Exception e) {e.printStackTrace();}
			}
		}
		
		// handling rentAreas
		for(RentAreaEntity rentAreaEntity:buildingEntity.getRentAreas()) {
			Integer value = rentAreaEntity.getValue();
			Boolean flag = true;
			for(RentAreaEntity newRentAreaEntity:newBuildingEntity.getRentAreas()) {
				Integer newValue=newRentAreaEntity.getValue();
				if(value.intValue() == newValue.intValue()) {
					newBuildingEntity.getRentAreas().remove(newRentAreaEntity);
					flag = false;
					break;
				}			
			}
			
			if(flag) {
				buildingEntitiesForDelete.add(rentAreaEntity);
				}
		}
		buildingEntity.getRentAreas().removeAll(buildingEntitiesForDelete);
		for(RentAreaEntity newRentAreaEntity:newBuildingEntity.getRentAreas()) {
			newRentAreaEntity.setBuilding(buildingEntity);
			buildingEntity.getRentAreas().add(newRentAreaEntity);
		}
		
		
		return buildingEntity;
	}


	@Override
	@Transactional
	public Boolean assignBuildingForStaff(Long[] staffIds, Long buildingId) {
		BuildingEntity buildingEnity= buildingRepository.getOne(buildingId);
		Set<UserEntity> staffs = buildingEnity.getStaffs();
		staffs.removeAll(buildingEnity.getStaffs());
		UserEntity userEnity =null;
		for(Long staffId : staffIds) {
			userEnity = userRepository.getOne(staffId);
			staffs.add(userEnity);
		}
		
		buildingRepository.save(buildingEnity);
		
		return true;
	}

}
