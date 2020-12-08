package com.laptrinhjavaweb.controller.admin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.input.BuildingInput;
import com.laptrinhjavaweb.dto.output.BuildingOutput;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IDistrictService;
import com.laptrinhjavaweb.service.IUserService;

import enums.DistrictEnum;

@Controller
public class BuildingController {
	
	@Autowired
	IDistrictService districtService;
	
	@Autowired
	IBuildingService buildingService;
	
	@Autowired
	IUserService userService;
	
	/*
	 spring boot khong chay duoc admin/building/home 
	 nó chỉ chạy đc 2 cấp
	 */
	@RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
	public ModelAndView buildingList() {
		BuildingOutput buildingOutput = new BuildingOutput();
		ModelAndView mav = new ModelAndView("admin/building/list");
		mav.addObject("buildingModelAttribute", buildingOutput);
		
		mav.addObject("districts",districtService.getDistricts());
		mav.addObject("buildingTypes",buildingService.getBuildingTypes());
		mav.addObject("staffs",userService.getStaffMap());
		return mav;
	}
	
	@RequestMapping(value = "/admin/building-list", method = RequestMethod.GET,params = {"name"})
	public ModelAndView buildingList(@ModelAttribute("buildingModelAttribute") BuildingOutput buildingOutput,@RequestParam  Map<String,String> requestParams,@RequestParam(name = "buildingTypes",required=false) String[] buildingTypes) {
		ModelAndView mav = new ModelAndView("admin/building/list");

		mav.addObject("districts",districtService.getDistricts());
		mav.addObject("buildingTypes",buildingService.getBuildingTypes());
		mav.addObject("staffs",userService.getStaffMap());
		
		BuildingSearchBuilder buildingSearchBuilder = convertMaptoBuilter(requestParams,buildingTypes);
		List<BuildingDTO> buildingDTOList =buildingService.getBuildings(buildingSearchBuilder);
		for(BuildingDTO buildingDTO:buildingDTOList) {
			String districtCode =buildingDTO.getDistrict();
			if(districtCode != null && !districtCode.equalsIgnoreCase("")) {
			buildingDTO.setDistrict(DistrictEnum.valueOf(districtCode).getValue());
			}else {
				buildingDTO.setDistrict("");
			}
		}
		mav.addObject("buildingDTOList", buildingDTOList);
		
		mav.addObject("buildingModelAttribute", buildingOutput);	
		return mav;
	}
	@RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
	public ModelAndView buildingEdit() {
		ModelAndView mav = new ModelAndView("admin/building/edit");
		//mav.addObject("name", "Lap trinh javaweb");
		return mav;
	}
	
	@RequestMapping(value = "/admin/building-addition", method = RequestMethod.GET)
	public ModelAndView buildingAdd() {
		ModelAndView mav = new ModelAndView("admin/building/building-addition");
		BuildingInput buildingInput = new BuildingInput();
		mav.addObject("districts",districtService.getDistricts());
		mav.addObject("buildingTypes",buildingService.getBuildingTypes());
		mav.addObject("staffs",userService.getStaffMap());
		
		mav.addObject("buildingModelAttribute", buildingInput);
		return mav;
	}
//	@RequestMapping(value = "/admin/building-addition", method = RequestMethod.POST)
//	public ModelAndView buildingAdd(@ModelAttribute("buildingModelAttribute") BuildingInput buildingInput) {
//		ModelAndView mav = new ModelAndView("admin/building/building-addition");
//		mav.addObject("districts",districtService.getDistricts());
//		mav.addObject("buildingTypes",buildingService.getBuildingTypes());
//		mav.addObject("staffs",userService.getStaffMap());
//		
//		mav.addObject("buildingModelAttribute", buildingInput);
//		
//		buildingService.saveBuilding(convertInputToDTO(buildingInput));
//		
//		return mav;
//	}
	@RequestMapping(value = "/admin/building-edit/{buildingId}", method = RequestMethod.GET)
	public ModelAndView buildingEdit(@PathVariable("buildingId") Long buildingId) {
		ModelAndView mav = new ModelAndView("admin/building/building-update");
		BuildingDTO buildingDTO = buildingService.getBuildingById(buildingId);
		BuildingOutput buildingOutput = convertDTOToOutput(buildingDTO);
		
		mav.addObject("districts",districtService.getDistricts());
		mav.addObject("buildingTypes",buildingService.getBuildingTypes());
		mav.addObject("staffs",userService.getStaffMap());
		
		
		mav.addObject("buildingModelAttribute", buildingOutput);
		return mav;
	}
//	@RequestMapping(value = "/admin/building-edit/{buildingId}", method = RequestMethod.POST)
//	public ModelAndView buildingEditPOST(@PathVariable("buildingId") Long buildingId, @ModelAttribute("buildingModelAttribute") BuildingInput buildingInput) {
//		ModelAndView mav = new ModelAndView("admin/building/building-update");
//		
//		mav.addObject("districts",districtService.getDistricts());
//		mav.addObject("buildingTypes",buildingService.getBuildingTypes());
//		mav.addObject("staffs",userService.getStaffMap());
//		BuildingDTO buildingDTO = convertInputToDTO(buildingInput);
//		buildingDTO.setId(buildingId);
//		buildingService.updateBuilding(buildingDTO);
//		
//		mav.addObject("buildingModelAttribute", buildingInput);
//		return mav;
//	}
	
	
	
	
	
	
	/*
	 UTIL FUNCTION
	 */
	private BuildingSearchBuilder convertMaptoBuilter(Map<String,String> requestParams, String[] requestTypes) {
		String name = requestParams.containsKey("name")? (StringUtils.isNotBlank(requestParams.get("name"))? requestParams.get("name") :null): null;
		Integer numberOfBasement = requestParams.containsKey("numberOfBasement") ? (StringUtils.isNotBlank(requestParams.get("numberOfBasement"))? Integer.parseInt(requestParams.get("numberOfBasement")): null) : null;
		Integer floorArea = requestParams.containsKey("floorArea") ? (StringUtils.isNotBlank(requestParams.get("floorArea"))? Integer.parseInt(requestParams.get("floorArea")): null) : null;
		String district = requestParams.containsKey("district")? (StringUtils.isNotBlank(requestParams.get("district"))? requestParams.get("district") :null): null;
		String ward =  requestParams.containsKey("ward")? (StringUtils.isNotBlank(requestParams.get("ward"))? requestParams.get("ward") :null): null;
		String street=  requestParams.containsKey("street")? (StringUtils.isNotBlank(requestParams.get("street"))? requestParams.get("street") :null): null;
		String direction=requestParams.containsKey("direction")? (StringUtils.isNotBlank(requestParams.get("direction"))? requestParams.get("direction") :null): null;
		String level = requestParams.containsKey("level")? (StringUtils.isNotBlank(requestParams.get("level"))? requestParams.get("level") :null): null;
		Integer rentAreaFrom = requestParams.containsKey("rentAreaFrom") ? (StringUtils.isNotBlank(requestParams.get("rentAreaFrom"))? Integer.parseInt(requestParams.get("rentAreaFrom")): null) : null;
		Integer rentAreaTo =  requestParams.containsKey("rentAreaTo") ? (StringUtils.isNotBlank(requestParams.get("rentAreaTo"))? Integer.parseInt(requestParams.get("rentAreaTo")): null) : null;
		Integer rentPriceFrom = requestParams.containsKey("rentPriceFrom") ? (StringUtils.isNotBlank(requestParams.get("rentPriceFrom"))? Integer.parseInt(requestParams.get("rentPriceFrom")): null) : null;
		Integer rentPriceTo =requestParams.containsKey("rentPriceTo") ? (StringUtils.isNotBlank(requestParams.get("rentPriceTo"))? Integer.parseInt(requestParams.get("rentPriceTo")): null) : null;
		String 	staffId=requestParams.containsKey("staffId")? (StringUtils.isNotBlank(requestParams.get("staffId"))? requestParams.get("staffId") :null): null;
		String managerName = requestParams.containsKey("managerName")? (StringUtils.isNotBlank(requestParams.get("managerName"))? requestParams.get("managerName") :null): null;
		String managerPhone = requestParams.containsKey("managerPhone")? (StringUtils.isNotBlank(requestParams.get("managerPhone"))? requestParams.get("managerPhone") :null): null;
		
		String[] buildingTypes = (requestTypes != null)? (checkEmtyString(requestTypes)? requestTypes : null ):null;
		
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(name)
				.setNumberOfBasement(numberOfBasement)
				.setFloorArea(floorArea)
				.setDistrict(district)
				.setWard(ward)
				.setStreet(street)
				.setDirection(direction)
				.setLevel(level)
				.setRentAreaFrom(rentAreaFrom)
				.setRentAreaTo(rentAreaTo)
				.setRentPriceFrom(rentPriceFrom)
				.setRentPriceTo(rentPriceTo)
				.setBuildingTypes(buildingTypes)
				.setStaffId(staffId)
				.setManagerName(managerName)
				.setManagerPhone(managerPhone)
				.build();
		return builder;
	}
	private Boolean checkEmtyString(String[] arrayString) {	
		for (String str: arrayString) {
		if(!str.equals("")){
			return true;
			}
		}
		return false;
	} 
	
	// convertInputToDTO
	private BuildingDTO convertInputToDTO(BuildingInput buildingInput) {
		Class<BuildingInput>  buildingInputClazz = (Class<BuildingInput>)buildingInput.getClass();
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
		for(String type : buildingInput.getBuildingTypes()) {
			if(buildingType.length() == 0) {
				buildingType.append(type);
			}
			else {
				buildingType.append(","+type);
			}
		}
		buildingDTO.setBuildingType(buildingType.toString());
		
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
	
	// convertDTOToInput
	private BuildingInput convertDTOToInput(BuildingDTO buildingDTO) {
		BuildingInput buildingInput= new BuildingInput();
		Class<BuildingDTO> clazzBuildingDTO = BuildingDTO.class;
		for(Field field : clazzBuildingDTO.getDeclaredFields()) {
			if(!field.getName().equalsIgnoreCase("buildingType") && !field.getName().equalsIgnoreCase("rentAreas")) {
				field.setAccessible(true);
				try{
					BeanUtils.setProperty(buildingInput, field.getName(), field.get(buildingDTO));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		// handling buildingType
		buildingInput.setBuildingTypes(buildingDTO.getBuildingType().split(","));
		// handling renAreas
		StringBuilder rentArea = new StringBuilder("");
		for(RentAreaEntity rentAreaEntity: buildingDTO.getRentAreas()) {
			if(rentArea.length() == 0) {
				rentArea.append(rentAreaEntity.getValue());
			}else {
				rentArea.append(","+rentAreaEntity.getValue());
			}
			
		}
		buildingInput.setRentArea(rentArea.toString());
		
		return buildingInput;
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
