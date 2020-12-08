package enums;

public enum BuildingTypeEnum {
	NOI_THAT("Nội Thất"),NGUYEN_CAN("Nguyên Căn"),TANG_TRET("Tầng Trệt");
	
	private String value;
	BuildingTypeEnum(String value){
		this.value=value;
	}
	public String getValue() {
		return this.value;
	}
}
