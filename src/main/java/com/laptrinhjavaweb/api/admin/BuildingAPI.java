package com.laptrinhjavaweb.api.admin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingAssignmentInput;
import com.laptrinhjavaweb.dto.input.BuildingInput;
import com.laptrinhjavaweb.dto.output.BuildingOutput;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.service.IBuildingService;

@RestController
public class BuildingAPI {
	
	@Autowired
	IBuildingService buildingService;
	
	// Chức năng thêm tòa nhà 
	@PostMapping(value= {"/api/admin/building/save"})
	public BuildingOutput saveBuildingAPI(@RequestBody BuildingInput buildingInput) {
		BuildingDTO buildingDTO = convertInputToDTO(buildingInput);
		return convertDTOToOutput(buildingService.saveBuilding(buildingDTO));
	}
	
	// Chức năng xóa tòa nhà
	@DeleteMapping(value = {"/api/admin/building-delete"})
	public Boolean deleteBuildings(@RequestBody List<Long> buildingIds) {
		buildingService.deleteBuildings(buildingIds);
		return true;
	}
	@GetMapping(value = {"/api/test"})
	public String doTest() {
		return "hello";
	}
	
	// Chức năng giao tòa nhà
	@PutMapping("/api/admin/building/assignment-staff")
	public Boolean assignBuildingForStaff(@RequestBody BuildingAssignmentInput buildingAssignmentInput) {
		return buildingService.assignBuildingForStaff(buildingAssignmentInput.getStaffId(),buildingAssignmentInput.getBuildingId());	
	}
	
	// Chức năng sửa tòa nhà 
	@PutMapping("/api/admin/building/update")
	public BuildingOutput updateBuildingById(@RequestBody BuildingInput buildingInput) {
		BuildingDTO buildingDTO = convertInputToDTO(buildingInput);
		buildingDTO = buildingService.updateBuilding(buildingDTO);
		return convertDTOToOutput(buildingDTO);
	}
	/*######################
	  # UTIL FUNCTION
	  ######################
	 */
	
	// convertInputToDTO
		private BuildingDTO convertInputToDTO(BuildingInput buildingInput) {
			Class<BuildingInput>  buildingInputClazz = BuildingInput.class;
			BuildingDTO buildingDTO = new BuildingDTO();
			Field[] fields = buildingInputClazz.getDeclaredFields();
			for(Field field : fields) {
				if(!field.getName().equalsIgnoreCase("buildingTypes") && !field.getName().equalsIgnoreCase("rentArea")) {
					field.setAccessible(true);
					try {
						BeanUtils.setProperty(buildingDTO, field.getName(), field.get(buildingInput));
						
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
			// handle buildingTypes
			StringBuilder buildingType = new StringBuilder("");
			if(buildingInput.getBuildingTypes() != null) {
				for(String type : buildingInput.getBuildingTypes()) {
					if(buildingType.length() == 0) {
						buildingType.append(type);
					}
					else {
						buildingType.append(","+type);
					}
				}
				buildingDTO.setBuildingType(buildingType.toString());
			}
			
			
			// handle rentArea
			List<RentAreaEntity> rentAreas = new ArrayList<RentAreaEntity>();
			if(buildingInput.getRentArea() != null && buildingInput.getRentArea() !="" ) {
				for(String value :  buildingInput.getRentArea().split(",")) {
					RentAreaEntity rentAreaEntity = new RentAreaEntity();
					rentAreaEntity.setValue(Integer.parseInt(value));
					rentAreas.add(rentAreaEntity);
				}
			}
			
			buildingDTO.setRentAreas(rentAreas);
			
			return buildingDTO;
		}
	
	// convertDTOToOutput
		private BuildingOutput convertDTOToOutput(BuildingDTO buildingDTO) {
			BuildingOutput buildingOutput= new BuildingOutput();
			Class<BuildingDTO> clazzBuildingDTO = BuildingDTO.class;
			for(Field field : clazzBuildingDTO.getDeclaredFields()) {
				if(!field.getName().equalsIgnoreCase("buildingType") && !field.getName().equalsIgnoreCase("rentAreas")) {
					field.setAccessible(true);
					try{
						BeanUtils.setProperty( buildingOutput, field.getName(), field.get(buildingDTO));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			// handling buildingType
			if(buildingDTO.getBuildingType() !=null && buildingDTO.getBuildingType()!="") {
				buildingOutput.setBuildingTypes(buildingDTO.getBuildingType().split(","));
			}	 
			// handling renAreas
			StringBuilder rentArea = new StringBuilder("");
			if(buildingDTO.getRentAreas().size() != 0) {
			for(RentAreaEntity rentAreaEntity: buildingDTO.getRentAreas()) {
				if(rentArea.length() == 0) {
					rentArea.append(rentAreaEntity.getValue());
				}else {
					rentArea.append(","+rentAreaEntity.getValue());
				}
				
			}
			 buildingOutput.setRentArea(rentArea.toString());
			 }
			// handling id
			buildingOutput.setId(buildingDTO.getId());
			
			return  buildingOutput;
		}
		
	
}
