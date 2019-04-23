package com.ats.hradmin.leave.model;



public class GetLeaveApplyAuthwise {
	
	


	private int leaveId;
	
	private int calYrId;
	
	private String leaveTitle;
	
	private int empId;
	
	private String empFname;
	
	private String empMname;
	
	private String empSname;
	
	private String empCode;
	
 	private int lvTypeId;
 	
 	private String leaveTypeName;
	
 	private String leaveDuration;
	
 	private String leaveFromdt;
	
 	private String leaveTodt ;
	
 	private float leaveNumDays;
	
 	private String leaveEmpReason;
 	
 	private String circulatedTo;

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getCalYrId() {
		return calYrId;
	}

	public void setCalYrId(int calYrId) {
		this.calYrId = calYrId;
	}

	public String getLeaveTitle() {
		return leaveTitle;
	}

	public void setLeaveTitle(String leaveTitle) {
		this.leaveTitle = leaveTitle;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public int getLvTypeId() {
		return lvTypeId;
	}

	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public String getLeaveDuration() {
		return leaveDuration;
	}

	public void setLeaveDuration(String leaveDuration) {
		this.leaveDuration = leaveDuration;
	}

	public String getLeaveFromdt() {
		return leaveFromdt;
	}

	public void setLeaveFromdt(String leaveFromdt) {
		this.leaveFromdt = leaveFromdt;
	}

	public String getLeaveTodt() {
		return leaveTodt;
	}

	public void setLeaveTodt(String leaveTodt) {
		this.leaveTodt = leaveTodt;
	}

	public float getLeaveNumDays() {
		return leaveNumDays;
	}

	public void setLeaveNumDays(float leaveNumDays) {
		this.leaveNumDays = leaveNumDays;
	}

	public String getLeaveEmpReason() {
		return leaveEmpReason;
	}

	public void setLeaveEmpReason(String leaveEmpReason) {
		this.leaveEmpReason = leaveEmpReason;
	}

	public String getCirculatedTo() {
		return circulatedTo;
	}

	public void setCirculatedTo(String circulatedTo) {
		this.circulatedTo = circulatedTo;
	}

	@Override
	public String toString() {
		return "GetLeaveApplyAuthwise [leaveId=" + leaveId + ", calYrId=" + calYrId + ", leaveTitle=" + leaveTitle
				+ ", empId=" + empId + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname
				+ ", empCode=" + empCode + ", lvTypeId=" + lvTypeId + ", leaveTypeName=" + leaveTypeName
				+ ", leaveDuration=" + leaveDuration + ", leaveFromdt=" + leaveFromdt + ", leaveTodt=" + leaveTodt
				+ ", leaveNumDays=" + leaveNumDays + ", leaveEmpReason=" + leaveEmpReason + ", circulatedTo="
				+ circulatedTo + "]";
	}
		
	
	

}
