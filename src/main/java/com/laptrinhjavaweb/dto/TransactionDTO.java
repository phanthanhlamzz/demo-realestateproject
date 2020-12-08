package com.laptrinhjavaweb.dto;


public class TransactionDTO extends BaseDTO{
	private static final long serialVersionUID = -8351519813422749279L;
	
	private String code;
	private String note;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
