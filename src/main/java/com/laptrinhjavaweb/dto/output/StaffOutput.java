package com.laptrinhjavaweb.dto.output;

import com.laptrinhjavaweb.dto.BaseDTO;

public class StaffOutput extends BaseDTO {
	private static final long serialVersionUID = -9042741587539450698L;
	private String name;
	private Boolean checked = false;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
