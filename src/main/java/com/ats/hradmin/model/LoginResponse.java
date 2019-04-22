package com.ats.hradmin.model;




public class LoginResponse {

  

    private int userId;
    
    private String  locId;
	
    private String userName;
    
    
    private String errMsg;
    
    
	GetEmployeeInfo getData;
    
    
    EmpType empType;
    

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public GetEmployeeInfo getGetData() {
		return getData;
	}

	public void setGetData(GetEmployeeInfo getData) {
		this.getData = getData;
	}

	public EmpType getEmpType() {
		return empType;
	}

	public void setEmpType(EmpType empType) {
		this.empType = empType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "LoginResponse [userId=" + userId + ", locId=" + locId + ", userName=" + userName + ", errMsg=" + errMsg
				+ ", getData=" + getData + ", empType=" + empType + ", getUserName()=" + getUserName() + ", getLocId()="
				+ getLocId() + ", getGetData()=" + getGetData() + ", getEmpType()=" + getEmpType() + ", getUserId()="
				+ getUserId() + ", getErrMsg()=" + getErrMsg() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	

	


}