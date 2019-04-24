package com.ats.hradmin.leave.model;


public class LeaveHistory {
	
	
	
    private int lvTypeId;
	
	private String lvTitleShort;
	
	private int lvsAllotedLeaves;
	
	private int balLeave;
	
	private int aplliedLeaeve;
	
	private int sactionLeave;

	public int getLvTypeId() {
		return lvTypeId;
	}

	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}

	public String getLvTitleShort() {
		return lvTitleShort;
	}

	public void setLvTitleShort(String lvTitleShort) {
		this.lvTitleShort = lvTitleShort;
	}

	public int getLvsAllotedLeaves() {
		return lvsAllotedLeaves;
	}

	public void setLvsAllotedLeaves(int lvsAllotedLeaves) {
		this.lvsAllotedLeaves = lvsAllotedLeaves;
	}

	public int getBalLeave() {
		return balLeave;
	}

	public void setBalLeave(int balLeave) {
		this.balLeave = balLeave;
	}

	public int getAplliedLeaeve() {
		return aplliedLeaeve;
	}

	public void setAplliedLeaeve(int aplliedLeaeve) {
		this.aplliedLeaeve = aplliedLeaeve;
	}

	public int getSactionLeave() {
		return sactionLeave;
	}

	public void setSactionLeave(int sactionLeave) {
		this.sactionLeave = sactionLeave;
	}

	@Override
	public String toString() {
		return "LeaveHistory [lvTypeId=" + lvTypeId + ", lvTitleShort=" + lvTitleShort + ", lvsAllotedLeaves="
				+ lvsAllotedLeaves + ", balLeave=" + balLeave + ", aplliedLeaeve=" + aplliedLeaeve + ", sactionLeave="
				+ sactionLeave + ", getLvTypeId()=" + getLvTypeId() + ", getLvTitleShort()=" + getLvTitleShort()
				+ ", getLvsAllotedLeaves()=" + getLvsAllotedLeaves() + ", getBalLeave()=" + getBalLeave()
				+ ", getAplliedLeaeve()=" + getAplliedLeaeve() + ", getSactionLeave()=" + getSactionLeave()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	


}
