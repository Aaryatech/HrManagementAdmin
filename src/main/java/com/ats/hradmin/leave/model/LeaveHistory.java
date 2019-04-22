package com.ats.hradmin.leave.model;


public class LeaveHistory {
	
	
	private int empId;
	
	
	private String shortName;
	
	private int earnedNo;
	
	private int sanctionNo;
	
	private int appliedNo;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEarnedNo() {
		return earnedNo;
	}

	public void setEarnedNo(int earnedNo) {
		this.earnedNo = earnedNo;
	}

	public int getSanctionNo() {
		return sanctionNo;
	}

	public void setSanctionNo(int sanctionNo) {
		this.sanctionNo = sanctionNo;
	}

	public int getAppliedNo() {
		return appliedNo;
	}

	public void setAppliedNo(int appliedNo) {
		this.appliedNo = appliedNo;
	}

	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "LeaveHistory [empId=" + empId + ", shortName=" + shortName + ", earnedNo=" + earnedNo + ", sanctionNo="
				+ sanctionNo + ", appliedNo=" + appliedNo + "]";
	}

	


}
