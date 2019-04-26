package com.ats.hradmin.model;

 

public class User {
 	private int userId;
	
 	private int empId ;
	
 	private int empTypeId;
	
 	private String locId;	
	
 	private String userName;
	
 	private String userPwd;	
	
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
 	
 	private boolean error;


	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}

	
	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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
		return "User [userId=" + userId + ", empId=" + empId + ", empTypeId=" + empTypeId + ", locId=" + locId
				+ ", userName=" + userName + ", userPwd=" + userPwd + ", delStatus=" + delStatus + ", isActive="
				+ isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + ", getUserId()=" + getUserId() + ", getEmpId()=" + getEmpId()
				+ ", getEmpTypeId()=" + getEmpTypeId() + ", getLocId()=" + getLocId() + ", getUserName()="
				+ getUserName() + ", getUserPwd()=" + getUserPwd() + ", getDelStatus()=" + getDelStatus()
				+ ", getIsActive()=" + getIsActive() + ", getMakerUserId()=" + getMakerUserId()
				+ ", getMakerEnterDatetime()=" + getMakerEnterDatetime() + ", getExInt1()=" + getExInt1()
				+ ", getExInt2()=" + getExInt2() + ", getExInt3()=" + getExInt3() + ", getExVar1()=" + getExVar1()
				+ ", getExVar2()=" + getExVar2() + ", getExVar3()=" + getExVar3() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
