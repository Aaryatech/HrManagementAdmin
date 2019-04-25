package com.ats.hradmin.model;



 public class ProjectHeader {
 	private int projectId;
	
 	private int companyId;
	
 	private int locId;
	
 	private int projectTypeId;	
	
 	private int custId;
	
 	private String projectTitle ;	
	
 	private String projectDesc ;	
	
 	private String projectEstStartdt ;	
	
 	private String projectEstEnddt ;
	
 	private int projectEstManhrs ;
	
 	private int projectEstBudget  ;
	
 	private String projectActStartdt ;
	
 	private String projectActenddt ;
	
 	private String projectStatus ;
	
 	private int projectCompletion ;
	
 	private int delStatus;
	
 	private int isActive;
	
 	private int makerUserId ;
	
 	private String makerEnterDatetime;
	
 	private int exInt1;
	
 	private int exInt2;
	
 	private int exInt3;
	
 	private String exVar1; 
	
 	private String exVar2; 
	
 	private String exVar3;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public int getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(int projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectEstStartdt() {
		return projectEstStartdt;
	}

	public void setProjectEstStartdt(String projectEstStartdt) {
		this.projectEstStartdt = projectEstStartdt;
	}

	public String getProjectEstEnddt() {
		return projectEstEnddt;
	}

	public void setProjectEstEnddt(String projectEstEnddt) {
		this.projectEstEnddt = projectEstEnddt;
	}

	public int getProjectEstManhrs() {
		return projectEstManhrs;
	}

	public void setProjectEstManhrs(int projectEstManhrs) {
		this.projectEstManhrs = projectEstManhrs;
	}

	public int getProjectEstBudget() {
		return projectEstBudget;
	}

	public void setProjectEstBudget(int projectEstBudget) {
		this.projectEstBudget = projectEstBudget;
	}

	public String getProjectActStartdt() {
		return projectActStartdt;
	}

	public void setProjectActStartdt(String projectActStartdt) {
		this.projectActStartdt = projectActStartdt;
	}

	public String getProjectActenddt() {
		return projectActenddt;
	}

	public void setProjectActenddt(String projectActenddt) {
		this.projectActenddt = projectActenddt;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public int getProjectCompletion() {
		return projectCompletion;
	}

	public void setProjectCompletion(int projectCompletion) {
		this.projectCompletion = projectCompletion;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
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

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
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

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	@Override
	public String toString() {
		return "ProjectHeader [projectId=" + projectId + ", companyId=" + companyId + ", locId=" + locId
				+ ", projectTypeId=" + projectTypeId + ", custId=" + custId + ", projectTitle=" + projectTitle
				+ ", projectDesc=" + projectDesc + ", projectEstStartdt=" + projectEstStartdt + ", projectEstEnddt="
				+ projectEstEnddt + ", projectEstManhrs=" + projectEstManhrs + ", projectEstBudget=" + projectEstBudget
				+ ", projectActStartdt=" + projectActStartdt + ", projectActenddt=" + projectActenddt
				+ ", projectStatus=" + projectStatus + ", projectCompletion=" + projectCompletion + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}
	
	
}
