package com.titanicapps.bottlerecycler;


import java.io.Serializable;
import java.util.ArrayList;

public class BottleCountData implements Serializable{

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
	
	public Long getTotalCount(){
		Long totalCount = Long.valueOf(0);
		for(int i = 0; i < countRecords.size(); i++){
			totalCount += countRecords.get(i).getCount();
		}
		return totalCount;
	}
	
	public Long getTotalValueCents(){
		Long totalValue = Long.valueOf(0);
		for(int i = 0; i < countRecords.size(); i++){
			totalValue += countRecords.get(i).getCount() * countRecords.get(i).getDenomCents();
		}		
		return totalValue;
	}
	
	public void clearCountData()
	{
		countRecords.clear();
		currentDenomCents = 0;
		currentCount = 0;		
	}
	
}
