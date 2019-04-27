package com.ats.hradmin.claim;

import java.util.List;

 
public class ClaimDetail {
 	private int claimId;

	private int empId;

	private int projectId;

	private int claimTypeId;

	private String claimDate;

	private float claimAmount;
	
	private String claimRemarks;

	private int claimFinalStatus;

	private String circulatedTo;

	private int delStatus;

	private int isActive;

	private int makerUserId;

	private String makerEnterDatetime;

	private int exInt1;

	private int exInt2;

	private int exInt3;

	private String exVar1;
	
	private String exVar2;

	private String exVar3;
	
	private String empFname;
	
	private String empMname;
	
	private String empSname;
	
	private String empCode;
	
	private String empPhoto;
	
	private String empDeptName;
	
	private String projectTypeTitle;
	
	private String claimTypeTitle;
	
 	List<GetClaimTrailStatus>  getClaimTrailStatus;

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getClaimTypeId() {
		return claimTypeId;
	}

	public void setClaimTypeId(int claimTypeId) {
		this.claimTypeId = claimTypeId;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getClaimRemarks() {
		return claimRemarks;
	}

	public void setClaimRemarks(String claimRemarks) {
		this.claimRemarks = claimRemarks;
	}

	public int getClaimFinalStatus() {
		return claimFinalStatus;
	}

	public void setClaimFinalStatus(int claimFinalStatus) {
		this.claimFinalStatus = claimFinalStatus;
	}

	public String getCirculatedTo() {
		return circulatedTo;
	}

	public void setCirculatedTo(String circulatedTo) {
		this.circulatedTo = circulatedTo;
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

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpMname() {
		return empMname;
	}

	public void setEmpMname(String empMname) {
		this.empMname = empMname;
	}

	public String getEmpSname() {
		return empSname;
	}

	public void setEmpSname(String empSname) {
		this.empSname = empSname;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}

	public String getEmpDeptName() {
		return empDeptName;
	}

	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}

	public String getProjectTypeTitle() {
		return projectTypeTitle;
	}

	public void setProjectTypeTitle(String projectTypeTitle) {
		this.projectTypeTitle = projectTypeTitle;
	}

	public String getClaimTypeTitle() {
		return claimTypeTitle;
	}

	public void setClaimTypeTitle(String claimTypeTitle) {
		this.claimTypeTitle = claimTypeTitle;
	}

	public List<GetClaimTrailStatus> getGetClaimTrailStatus() {
		return getClaimTrailStatus;
	}

	public void setGetClaimTrailStatus(List<GetClaimTrailStatus> getClaimTrailStatus) {
		this.getClaimTrailStatus = getClaimTrailStatus;
	}

	@Override
	public String toString() {
		return "ClaimDetail [claimId=" + claimId + ", empId=" + empId + ", projectId=" + projectId + ", claimTypeId="
				+ claimTypeId + ", claimDate=" + claimDate + ", claimAmount=" + claimAmount + ", claimRemarks="
				+ claimRemarks + ", claimFinalStatus=" + claimFinalStatus + ", circulatedTo=" + circulatedTo
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", empCode="
				+ empCode + ", empPhoto=" + empPhoto + ", empDeptName=" + empDeptName + ", projectTypeTitle="
				+ projectTypeTitle + ", claimTypeTitle=" + claimTypeTitle + ", getClaimTrailStatus="
				+ getClaimTrailStatus + "]";
	}
	
	

}
