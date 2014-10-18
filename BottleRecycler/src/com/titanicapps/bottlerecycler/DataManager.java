package com.titanicapps.bottlerecycler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;



import java.io.ObjectOutputStream;


import android.app.Activity;
import android.content.Context;

public class DataManager {
	
	private static final String BOTTLE_COUNT_FILE_NAME = "bottle_count_data";
	private static final String RECYCLE_HISTORY_FILE_NAME = "recycle_history_data";
	
	private BottleCountData bottleCountData= new BottleCountData();
	private RecycleHistoryListData recycleHistoryListData = new RecycleHistoryListData();
	
	public BottleCountData getBottleCountData(){
		return bottleCountData;
	}
	
	public RecycleHistoryListData getRecycleHistoryListData(){
		return recycleHistoryListData;
	}
	
	public void load(Activity context){
		try
    	{
    		
	    	FileInputStream fIBottleCount = context.openFileInput(BOTTLE_COUNT_FILE_NAME);
	    	ObjectInputStream oIsBottleCount = new ObjectInputStream(fIBottleCount);
	    	bottleCountData = (BottleCountData) oIsBottleCount.readObject();
	    	fIBottleCount.close();   
	    	
	    	FileInputStream fInputStream = context.openFileInput(RECYCLE_HISTORY_FILE_NAME);
	    	ObjectInputStream oIsHistory = new ObjectInputStream(fInputStream);
	    	recycleHistoryListData = (RecycleHistoryListData) oIsHistory.readObject();
	    	fInputStream.close();  
	    	
	    	
    	}
    	catch(FileNotFoundException e)
    	{} 
    	catch (ClassNotFoundException e) 
    	{}
    	catch(IOException e)
    	{}		
	}
	
	public void save(Activity context){
		
		int mode = Context.MODE_PRIVATE;
		 try
		    {

		    	FileOutputStream fosBottle;
		    	ObjectOutputStream OosBottle;

		    	fosBottle = context.openFileOutput(BOTTLE_COUNT_FILE_NAME, mode);
		    	OosBottle = new ObjectOutputStream(fosBottle);
	    		OosBottle.writeObject(bottleCountData);

		    	OosBottle.close();
		    	
		    	FileOutputStream fosHistory;
		    	ObjectOutputStream OosHistory;

		    	fosHistory = context.openFileOutput(RECYCLE_HISTORY_FILE_NAME, mode);
		    	OosHistory = new ObjectOutputStream(fosHistory);
	    		OosHistory.writeObject(recycleHistoryListData);

		    	OosHistory.close();
		    }
		    catch(IOException e)
		    {}		
	}
	
}
