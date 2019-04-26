package com.ats.hradmin.claim;

 

 
 public class GetClaimApplyAuthwise {
	
 	private int claimId;
	
	private int empId;
	
	private String empCode;
	
	private String empName;
	
	private int projectId;
	
	private String projectTitle;
	
	private int claimTypeId;
	
	private String claimTypeName;

	private float claimAmount;

	private String claimRemarks;

	private String claimDate;
	
	private String circulatedTo ;
	
	private int exInt1;
	
	private int caIniAuthEmpId;

	private int caFinAuthEmpId;

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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public int getClaimTypeId() {
		return claimTypeId;
	}

	public void setClaimTypeId(int claimTypeId) {
		this.claimTypeId = claimTypeId;
	}

	public String getClaimTypeName() {
		return claimTypeName;
	}

	public void setClaimTypeName(String claimTypeName) {
		this.claimTypeName = claimTypeName;
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

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	
	
	public String getCirculatedTo() {
		return circulatedTo;
	}

	public void setCirculatedTo(String circulatedTo) {
		this.circulatedTo = circulatedTo;
	}

	public int getCaIniAuthEmpId() {
		return caIniAuthEmpId;
	}

	public void setCaIniAuthEmpId(int caIniAuthEmpId) {
		this.caIniAuthEmpId = caIniAuthEmpId;
	}

	public int getCaFinAuthEmpId() {
		return caFinAuthEmpId;
	}

	public void setCaFinAuthEmpId(int caFinAuthEmpId) {
		this.caFinAuthEmpId = caFinAuthEmpId;
	}

	@Override
	public String toString() {
		return "GetClaimApplyAuthwise [claimId=" + claimId + ", empId=" + empId + ", empCode=" + empCode + ", empName="
				+ empName + ", projectId=" + projectId + ", projectTitle=" + projectTitle + ", claimTypeId="
				+ claimTypeId + ", claimTypeName=" + claimTypeName + ", claimAmount=" + claimAmount + ", claimRemarks="
				+ claimRemarks + ", claimDate=" + claimDate + ", circulatedTo=" + circulatedTo + ", exInt1=" + exInt1
				+ ", caIniAuthEmpId=" + caIniAuthEmpId + ", caFinAuthEmpId=" + caFinAuthEmpId + ", getClaimId()="
				+ getClaimId() + ", getEmpId()=" + getEmpId() + ", getEmpCode()=" + getEmpCode() + ", getEmpName()="
				+ getEmpName() + ", getProjectId()=" + getProjectId() + ", getProjectTitle()=" + getProjectTitle()
				+ ", getClaimTypeId()=" + getClaimTypeId() + ", getClaimTypeName()=" + getClaimTypeName()
				+ ", getClaimAmount()=" + getClaimAmount() + ", getClaimRemarks()=" + getClaimRemarks()
				+ ", getClaimDate()=" + getClaimDate() + ", getExInt1()=" + getExInt1() + ", getCirculatedTo()="
				+ getCirculatedTo() + ", getCaIniAuthEmpId()=" + getCaIniAuthEmpId() + ", getCaFinAuthEmpId()="
				+ getCaFinAuthEmpId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
	
	

}
