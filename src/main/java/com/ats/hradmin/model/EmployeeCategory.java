package com.ats.hradmin.model;

public class EmployeeCategory {
	
	private int empCatId ;
	
	private int companyId ;
	
	private String empCatName;
	
	private String empCatShortName;
	
	private String empCatRemarks;
	
	private int delStatus;
	
	private int isActive;
	
	private int makerUserId ;
	
	private String makerEnterDatetime;
	
	private int exInt1;
	
	private int exInt2;
	
	private int exInt3;
	
	private String exVar1; 
	
	private String exVar2; 
	
	private String exVar3;

	

	public int getEmpCatId() {
		return empCatId;
	}



	public void setEmpCatId(int empCatId) {
		this.empCatId = empCatId;
	}



	public int getCompanyId() {
		return companyId;
	}



	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	public String getEmpCatName() {
		return empCatName;
	}



	public void setEmpCatName(String empCatName) {
		this.empCatName = empCatName;
	}



	public String getEmpCatShortName() {
		return empCatShortName;
	}



	public void setEmpCatShortName(String empCatShortName) {
		this.empCatShortName = empCatShortName;
	}



	public String getEmpCatRemarks() {
		return empCatRemarks;
	}



	public void setEmpCatRemarks(String empCatRemarks) {
		this.empCatRemarks = empCatRemarks;
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



	public int getExInt3() {
		return exInt3;
	}



	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
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



	public String getExVar3() {
		return exVar3;
	}



	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}



	@Override
	public String toString() {
		return "EmployeeCategory [empCatId=" + empCatId + ", companyId=" + companyId + ", empCatName=" + empCatName
				+ ", empCatShortName=" + empCatShortName + ", empCatRemarks=" + empCatRemarks + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}
	
	
}
