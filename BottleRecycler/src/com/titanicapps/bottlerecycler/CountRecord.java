package com.titanicapps.bottlerecycler;

import java.io.Serializable;

public class CountRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	private int denomCents = 0;
	private int count = 0;
	
	public int getDenomCents(){
		return denomCents;
	}
	public int getCount(){
		return count;
	}
	
	void setDenomCents(int denomCents){
		this.denomCents = denomCents;
	}
	
	void setCount(int count){
		this.count = count;
	}	
}
