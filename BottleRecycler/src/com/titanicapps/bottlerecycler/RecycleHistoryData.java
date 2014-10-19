package com.titanicapps.bottlerecycler;

import java.io.Serializable;
import java.util.ArrayList;

public class RecycleHistoryData implements Serializable{

	private static final long serialVersionUID = 1L;
	private long dateReturnedInMs = 0;
	private ArrayList<CountRecord> countRecords = new ArrayList<CountRecord>();
	public long getDateReturnedInMs() {
		return dateReturnedInMs;
	}
	public void setDateReturnedInMs(long dateReturnedInMs) {
		this.dateReturnedInMs = dateReturnedInMs;
	}
	
	public void setCountRecords(ArrayList<CountRecord> countRecords){
		this.countRecords = countRecords;
	}
	
	public ArrayList<CountRecord> getCountRecords(){
		return countRecords;
	}

}
