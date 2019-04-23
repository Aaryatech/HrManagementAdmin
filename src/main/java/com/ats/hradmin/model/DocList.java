package com.ats.hradmin.model;
 

public class DocList {
	 
	private int doctypeId; 
	private String doctypeKey; 
	private String doctypeName;	 
	private String imageName; 
	private int docId ;
	private int isRequired ;
	public int getDoctypeId() {
		return doctypeId;
	}
	public void setDoctypeId(int doctypeId) {
		this.doctypeId = doctypeId;
	}
	public String getDoctypeKey() {
		return doctypeKey;
	}
	public void setDoctypeKey(String doctypeKey) {
		this.doctypeKey = doctypeKey;
	}
	public String getDoctypeName() {
		return doctypeName;
	}
	public void setDoctypeName(String doctypeName) {
		this.doctypeName = doctypeName;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public int getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(int isRequired) {
		this.isRequired = isRequired;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	@Override
	public String toString() {
		return "DocList [doctypeId=" + doctypeId + ", doctypeKey=" + doctypeKey + ", doctypeName=" + doctypeName
				+ ", imageName=" + imageName + ", docId=" + docId + ", isRequired=" + isRequired + "]";
	}

	
}
