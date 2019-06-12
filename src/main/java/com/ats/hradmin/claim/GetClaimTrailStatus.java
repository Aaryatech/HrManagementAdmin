package com.ats.hradmin.claim;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GetClaimTrailStatus {
	private int claimTrailPkey;

	private int claimId;

	private int empId;

	private String empRemarks;

	private int claimStatus;

	private int claimTitle;

	private int claimAmount;

	private String userName;

	private Date makerEnterDatetime;

	private String empFname;

	private String empMname;

	private String empSname;

	public int getClaimTitle() {
		return claimTitle;
	}

	public void setClaimTitle(int claimTitle) {
		this.claimTitle = claimTitle;
	}

	public int getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(int claimAmount) {
		this.claimAmount = claimAmount;
	}

	public int getClaimTrailPkey() {
		return claimTrailPkey;
	}

	public void setClaimTrailPkey(int claimTrailPkey) {
		this.claimTrailPkey = claimTrailPkey;
	}

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

	public String getEmpRemarks() {
		return empRemarks;
	}

	public void setEmpRemarks(String empRemarks) {
		this.empRemarks = empRemarks;
	}

	public int getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(int claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonFormat(locale = "Locale.ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy hh:mm:ss a") //
	public Date getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(Date makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
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

	@Override
	public String toString() {
		return "GetClaimTrailStatus [claimTrailPkey=" + claimTrailPkey + ", claimId=" + claimId + ", empId=" + empId
				+ ", empRemarks=" + empRemarks + ", claimStatus=" + claimStatus + ", claimTitle=" + claimTitle
				+ ", claimAmount=" + claimAmount + ", userName=" + userName + ", makerEnterDatetime="
				+ makerEnterDatetime + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname
				+ ", getClaimTitle()=" + getClaimTitle() + ", getClaimAmount()=" + getClaimAmount()
				+ ", getClaimTrailPkey()=" + getClaimTrailPkey() + ", getClaimId()=" + getClaimId() + ", getEmpId()="
				+ getEmpId() + ", getEmpRemarks()=" + getEmpRemarks() + ", getClaimStatus()=" + getClaimStatus()
				+ ", getUserName()=" + getUserName() + ", getMakerEnterDatetime()=" + getMakerEnterDatetime()
				+ ", getEmpFname()=" + getEmpFname() + ", getEmpMname()=" + getEmpMname() + ", getEmpSname()="
				+ getEmpSname() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
