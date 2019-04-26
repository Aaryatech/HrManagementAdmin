package com.ats.hradmin.model;

import java.util.List;

import com.ats.hradmin.leave.model.GetHoliday;
import com.ats.hradmin.leave.model.Holiday;
 
 

public class HolidayAndWeeklyOff {
	
	List<WeeklyOff> weeklyList;
	List<Holiday> holidayList;
	public List<WeeklyOff> getWeeklyList() {
		return weeklyList;
	}
	public void setWeeklyList(List<WeeklyOff> weeklyList) {
		this.weeklyList = weeklyList;
	}
	public List<Holiday> getHolidayList() {
		return holidayList;
	}
	public void setHolidayList(List<Holiday> holidayList) {
		this.holidayList = holidayList;
	}
	@Override
	public String toString() {
		return "HolidayAndWeeklyOff [weeklyList=" + weeklyList + ", holidayList=" + holidayList + "]";
	}
	
	

}
