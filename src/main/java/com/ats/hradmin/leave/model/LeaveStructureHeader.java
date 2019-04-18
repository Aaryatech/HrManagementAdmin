package com.ats.hradmin.leave.model;

public class LeaveStructureHeader {

	private int lvsId;

	private int companyId;

	private String lvsName;

	private int delStatus;

	private int isActive;

	private int makerUserId;

	private String makerEnterDatetime;

	private int exInt1;

	private int exInt2;

	private String exVar1;

	private String exVar2;

	public int getLvsId() {
		return lvsId;
	}

	public void setLvsId(int lvsId) {
		this.lvsId = lvsId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getLvsName() {
		return lvsName;
	}

	public void setLvsName(String lvsName) {
		this.lvsName = lvsName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	@Override
	public String toString() {
		return "LeaveStructureHeader [lvsId=" + lvsId + ", companyId=" + companyId + ", lvsName=" + lvsName
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}

}
