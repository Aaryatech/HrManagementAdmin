package com.ats.hradmin.model;

public class EmpDocType {
	
	private int doctypeId;
	
	private int companyId;
	
	private String doctypKey;
	
	private String doctypeName;
	
	private String doctypeNote;
	
	private int isValue;
	
	private int  isImage ;
	
	private int imageSizeWidth;
	
	private int imageSizeLength;
	
	private int isRemarks;
	
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

	public int getDoctypeId() {
		return doctypeId;
	}

	public void setDoctypeId(int doctypeId) {
		this.doctypeId = doctypeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getDoctypKey() {
		return doctypKey;
	}

	public void setDoctypKey(String doctypKey) {
		this.doctypKey = doctypKey;
	}

	public String getDoctypeName() {
		return doctypeName;
	}

	public void setDoctypeName(String doctypeName) {
		this.doctypeName = doctypeName;
	}

	public String getDoctypeNote() {
		return doctypeNote;
	}

	public void setDoctypeNote(String doctypeNote) {
		this.doctypeNote = doctypeNote;
	}

	public int getIsValue() {
		return isValue;
	}

	public void setIsValue(int isValue) {
		this.isValue = isValue;
	}

	public int getIsImage() {
		return isImage;
	}

	public void setIsImage(int isImage) {
		this.isImage = isImage;
	}

	public int getImageSizeWidth() {
		return imageSizeWidth;
	}

	public void setImageSizeWidth(int imageSizeWidth) {
		this.imageSizeWidth = imageSizeWidth;
	}

	public int getImageSizeLength() {
		return imageSizeLength;
	}

	public void setImageSizeLength(int imageSizeLength) {
		this.imageSizeLength = imageSizeLength;
	}

	public int getIsRemarks() {
		return isRemarks;
	}

	public void setIsRemarks(int isRemarks) {
		this.isRemarks = isRemarks;
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
		return "EmpDocType [doctypeId=" + doctypeId + ", companyId=" + companyId + ", doctypKey=" + doctypKey
				+ ", doctypeName=" + doctypeName + ", doctypeNote=" + doctypeNote + ", isValue=" + isValue
				+ ", isImage=" + isImage + ", imageSizeWidth=" + imageSizeWidth + ", imageSizeLength=" + imageSizeLength
				+ ", isRemarks=" + isRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId="
				+ makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2="
				+ exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ "]";
	}

	
}
