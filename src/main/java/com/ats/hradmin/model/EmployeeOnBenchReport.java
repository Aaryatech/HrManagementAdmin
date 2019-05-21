package com.ats.hradmin.model;
 

public class EmployeeOnBenchReport {
	 
	private int empId ; 
	private String empFname ; 
	private String empMname; 
	private String empSname; 
	private float empRatePerhr; 
	private float ctc; 
	private String category; 
	private String type;
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
	public float getEmpRatePerhr() {
		return empRatePerhr;
	}
	public void setEmpRatePerhr(float empRatePerhr) {
		this.empRatePerhr = empRatePerhr;
	}
	public float getCtc() {
		return ctc;
	}
	public void setCtc(float ctc) {
		this.ctc = ctc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "EmployeeOnBenchReport [empId=" + empId + ", empFname=" + empFname + ", empMname=" + empMname
				+ ", empSname=" + empSname + ", empRatePerhr=" + empRatePerhr + ", ctc=" + ctc + ", category="
				+ category + ", type=" + type + "]";
	}
	
	

}
