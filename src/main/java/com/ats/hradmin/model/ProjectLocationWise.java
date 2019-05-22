package com.ats.hradmin.model;
 

public class ProjectLocationWise {
	 
	private int pallotId ; 
	private int projectId ; 
	private int locId; 
	private float resourceCost ; 
	private String projectTitle; 
	private String locName; 
	private float revenue;
	
	public int getPallotId() {
		return pallotId;
	}
	public void setPallotId(int pallotId) {
		this.pallotId = pallotId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public float getResourceCost() {
		return resourceCost;
	}
	public void setResourceCost(float resourceCost) {
		this.resourceCost = resourceCost;
	} 
	
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public float getRevenue() {
		return revenue;
	}
	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	@Override
	public String toString() {
		return "ProjectLocationWise [pallotId=" + pallotId + ", projectId=" + projectId + ", locId=" + locId
				+ ", resourceCost=" + resourceCost + ", projectTitle=" + projectTitle + ", locName=" + locName
				+ ", revenue=" + revenue + "]";
	}
	
	
	

}
