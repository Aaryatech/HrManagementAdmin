package com.ats.hradmin.leave.model;

import java.util.Date;

public class GetHoliday {
	private int holidayId;

	private int calYrId;

	private int companyId;

	private int locId;

	private String holidayFromdt;

	private String holidayTodt;

	private String holidayRemark;

	private String companyName;
	private String locName;
	private String calYrFromDate;
	private String calYrToDate;
	
	private String exVar1;

	public int getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}

	public int getCalYrId() {
		return calYrId;
	}

	public void setCalYrId(int calYrId) {
		this.calYrId = calYrId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getHolidayFromdt() {
		return holidayFromdt;
	}

	public void setHolidayFromdt(String holidayFromdt) {
		this.holidayFromdt = holidayFromdt;
	}

	public String getHolidayTodt() {
		return holidayTodt;
	}

	public void setHolidayTodt(String holidayTodt) {
		this.holidayTodt = holidayTodt;
	}

	public String getHolidayRemark() {
		return holidayRemark;
	}

	public void setHolidayRemark(String holidayRemark) {
		this.holidayRemark = holidayRemark;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getCalYrFromDate() {
		return calYrFromDate;
	}

	public void setCalYrFromDate(String calYrFromDate) {
		this.calYrFromDate = calYrFromDate;
	}

	public String getCalYrToDate() {
		return calYrToDate;
	}

	public void setCalYrToDate(String calYrToDate) {
		this.calYrToDate = calYrToDate;
	}
	
	

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	@Override
	public String toString() {
		return "GetHoliday [holidayId=" + holidayId + ", calYrId=" + calYrId + ", companyId=" + companyId + ", locId="
				+ locId + ", holidayFromdt=" + holidayFromdt + ", holidayTodt=" + holidayTodt + ", holidayRemark="
				+ holidayRemark + ", companyName=" + companyName + ", locName=" + locName + ", calYrFromDate="
				+ calYrFromDate + ", calYrToDate=" + calYrToDate + ", exVar1=" + exVar1 + "]";
	}

}
