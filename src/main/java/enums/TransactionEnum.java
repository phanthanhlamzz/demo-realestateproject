package enums;

public enum TransactionEnum {
	QUA_TRINH_CSKH("Quá Trình CSKH"),DAN_DI_XEM("Dẫn Đi xem");
	private String value;
	TransactionEnum(String value){
		this.value=value;
	}
	public String getValue() {
		return this.value;
	} 
}
