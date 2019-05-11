package com.ats.hradmin.model;

import java.util.List;

public class EmployeeFreeBsyList {
	
	List<EmployeeInfo> freeList; 
	List<ProjectAllotment> bsyList;
	public List<EmployeeInfo> getFreeList() {
		return freeList;
	}
	public void setFreeList(List<EmployeeInfo> freeList) {
		this.freeList = freeList;
	}
	public List<ProjectAllotment> getBsyList() {
		return bsyList;
	}
	public void setBsyList(List<ProjectAllotment> bsyList) {
		this.bsyList = bsyList;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeFreeBsyList [freeList=").append(freeList).append(", bsyList=").append(bsyList)
				.append("]");
		return builder.toString();
	}
	 
	
}
