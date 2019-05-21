package com.ats.hradmin.model;
 

public class ActivityRevenueReport {
	 
	private int projectTypeId ; 
	private float revenue ; 
	private float resourceCost; 
	private String projectTypeTitle; 
	private String projectTypeTitleShort;
	public int getProjectTypeId() {
		return projectTypeId;
	}
	public void setProjectTypeId(int projectTypeId) {
		this.projectTypeId = projectTypeId;
	}
	public float getRevenue() {
		return revenue;
	}
	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	public float getResourceCost() {
		return resourceCost;
	}
	public void setResourceCost(float resourceCost) {
		this.resourceCost = resourceCost;
	}
	public String getProjectTypeTitle() {
		return projectTypeTitle;
	}
	public void setProjectTypeTitle(String projectTypeTitle) {
		this.projectTypeTitle = projectTypeTitle;
	}
	public String getProjectTypeTitleShort() {
		return projectTypeTitleShort;
	}
	public void setProjectTypeTitleShort(String projectTypeTitleShort) {
		this.projectTypeTitleShort = projectTypeTitleShort;
	}
	@Override
	public String toString() {
		return "ActivityRevenueReport [projectTypeId=" + projectTypeId + ", revenue=" + revenue + ", resourceCost="
				+ resourceCost + ", projectTypeTitle=" + projectTypeTitle + ", projectTypeTitleShort="
				+ projectTypeTitleShort + "]";
	}
	
	

}
