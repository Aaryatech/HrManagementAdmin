package com.ats.hradmin.krakpi.model;

  
public class GetEmpKraKpiCount {
 	private int empId;
	
 	private String empCode;
	 
	private int empCatId;

	private String empCategory;
	
	private int empTypeId;
	
	private String empType;
	
	private int empDeptId;
	
	private String empDept;
	
	private String empDeptShortName;
	
	private String empTypeShortName;
	
	private String empCatShortName;
	
	private String empFname;
	
	private String empMname;
	
	private String empSname;
	
	private int kraCount;
	
	private int kpiCount;

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

	public int getEmpCatId() {
		return empCatId;
	}

	public void setEmpCatId(int empCatId) {
		this.empCatId = empCatId;
	}

	public String getEmpCategory() {
		return empCategory;
	}

	public void setEmpCategory(String empCategory) {
		this.empCategory = empCategory;
	}

	public int getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public int getEmpDeptId() {
		return empDeptId;
	}

	public void setEmpDeptId(int empDeptId) {
		this.empDeptId = empDeptId;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getEmpDeptShortName() {
		return empDeptShortName;
	}

	public void setEmpDeptShortName(String empDeptShortName) {
		this.empDeptShortName = empDeptShortName;
	}

	public String getEmpTypeShortName() {
		return empTypeShortName;
	}

	public void setEmpTypeShortName(String empTypeShortName) {
		this.empTypeShortName = empTypeShortName;
	}

	public String getEmpCatShortName() {
		return empCatShortName;
	}

	public void setEmpCatShortName(String empCatShortName) {
		this.empCatShortName = empCatShortName;
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

	public int getKraCount() {
		return kraCount;
	}

	public void setKraCount(int kraCount) {
		this.kraCount = kraCount;
	}

	public int getKpiCount() {
		return kpiCount;
	}

	public void setKpiCount(int kpiCount) {
		this.kpiCount = kpiCount;
	}

	@Override
	public String toString() {
		return "GetEmpKraKpiCount [empId=" + empId + ", empCode=" + empCode + ", empCatId=" + empCatId
				+ ", empCategory=" + empCategory + ", empTypeId=" + empTypeId + ", empType=" + empType + ", empDeptId="
				+ empDeptId + ", empDept=" + empDept + ", empDeptShortName=" + empDeptShortName + ", empTypeShortName="
				+ empTypeShortName + ", empCatShortName=" + empCatShortName + ", empFname=" + empFname + ", empMname="
				+ empMname + ", empSname=" + empSname + ", kraCount=" + kraCount + ", kpiCount=" + kpiCount + "]";
	}
	
	
	
	

}
