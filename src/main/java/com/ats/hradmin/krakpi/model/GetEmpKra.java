package com.ats.hradmin.krakpi.model;

 

public class GetEmpKra {
	
 	private int kraId;
	
 	private String kraTitle ;

	private String remark; 

	private int kpiCount;
	
	private String exvar1; 

	public int getKraId() {
		return kraId;
	}

	public void setKraId(int kraId) {
		this.kraId = kraId;
	}

	public String getKraTitle() {
		return kraTitle;
	}

	public void setKraTitle(String kraTitle) {
		this.kraTitle = kraTitle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getKpiCount() {
		return kpiCount;
	}

	public void setKpiCount(int kpiCount) {
		this.kpiCount = kpiCount;
	}
	
	

	public String getExvar1() {
		return exvar1;
	}

	public void setExvar1(String exvar1) {
		this.exvar1 = exvar1;
	}

	@Override
	public String toString() {
		return "GetEmpKra [kraId=" + kraId + ", kraTitle=" + kraTitle + ", remark=" + remark + ", kpiCount=" + kpiCount
				+ ", exvar1=" + exvar1 + ", getKraId()=" + getKraId() + ", getKraTitle()=" + getKraTitle()
				+ ", getRemark()=" + getRemark() + ", getKpiCount()=" + getKpiCount() + ", getExvar1()=" + getExvar1()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	 
	
	
}
