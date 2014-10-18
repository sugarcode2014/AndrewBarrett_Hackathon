package com.titanicapps.bottlerecycler;


import java.io.Serializable;
import java.util.ArrayList;


public class BottleCountData implements Serializable{

	public class CountRecord{
		public int denomCents = 0;
		public int count = 0;
	}
	private static final long serialVersionUID = 1L;
	private int currentDenomCents = 0;
	private int currentCount = 0;
	private ArrayList<CountRecord> countRecords = new ArrayList<CountRecord>();
	
	public int getCurrentDenomCents() {
		return currentDenomCents;
	}
	public void setCurrentDenomCents(int currentDenomCents) {
		this.currentDenomCents = currentDenomCents;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	
	public void addCountRecord(CountRecord countRecord){
		countRecords.add(countRecord);
	}
	
	public ArrayList<CountRecord> getCountRecords(){
		return countRecords;
	}
	

}
