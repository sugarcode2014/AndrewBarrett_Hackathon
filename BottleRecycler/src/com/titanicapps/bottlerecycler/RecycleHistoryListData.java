package com.titanicapps.bottlerecycler;

import java.io.Serializable;
import java.util.ArrayList;

public class RecycleHistoryListData implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<RecycleHistoryData> recycleHistoryList = new ArrayList<RecycleHistoryData>();
	
	public void addRecycleHistoryData(RecycleHistoryData recycleHistoryData){
		recycleHistoryList.add(recycleHistoryData);
	}
	
	public ArrayList<RecycleHistoryData> getRecycleHistoryDataList(){
		return recycleHistoryList;
	}
}
