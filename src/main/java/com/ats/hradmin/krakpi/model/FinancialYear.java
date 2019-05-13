package com.ats.hradmin.krakpi.model;

  
public class FinancialYear {
 	private int finYrId;
	
 	private String finYrFrom;
	
 	private String finYrTo ;
	
 	private int isCurrent;
	
 	private int delStatus;

 	private int isActive;
	
 	private int exInt1;

 	private int exInt2;

 	private String exVar1;

 	private String exVar2;
 
 	private boolean isError;
 
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsActive() {
		return isActive;
	}

	public int getFinYrId() {
		return finYrId;
	}

	public void setFinYrId(int finYrId) {
		this.finYrId = finYrId;
	}
	
	 
	public String getFinYrFrom() {
		return finYrFrom;
	}

	public void setFinYrFrom(String finYrFrom) {
		this.finYrFrom = finYrFrom;
	}

 	public String getFinYrTo() {
		return finYrTo;
	}

	public void setFinYrTo(String finYrTo) {
		this.finYrTo = finYrTo;
	}

	public int getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	@Override
	public String toString() {
		return "FinancialYear [finYrId=" + finYrId + ", finYrFrom=" + finYrFrom + ", finYrTo=" + finYrTo
				+ ", isCurrent=" + isCurrent + ", delStatus=" + delStatus + ", isActive=" + isActive + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", isError=" + isError
				+ ", getIsActive()=" + getIsActive() + ", getFinYrId()=" + getFinYrId() + ", getFinYrFrom()="
				+ getFinYrFrom() + ", getFinYrTo()=" + getFinYrTo() + ", getIsCurrent()=" + getIsCurrent()
				+ ", getDelStatus()=" + getDelStatus() + ", getExInt1()=" + getExInt1() + ", getExInt2()=" + getExInt2()
				+ ", getExVar1()=" + getExVar1() + ", getExVar2()=" + getExVar2() + ", isError()=" + isError()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	 
}
