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
	
	private static final String BOTTLE_COUNT_FILE_NAME= "bottle_count_data";
	private BottleCountData bottleCountData= new BottleCountData();
	
	public BottleCountData getBottleCountData(){
		return bottleCountData;
	}
	
	public void load(Activity context){
		try
    	{
    		
	    	FileInputStream fInputStream = context.openFileInput(BOTTLE_COUNT_FILE_NAME);
	    	ObjectInputStream oIsBottleCount = new ObjectInputStream(fInputStream);
	    	bottleCountData = (BottleCountData) oIsBottleCount.readObject();
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

		    	FileOutputStream fos;
		    	ObjectOutputStream Oos;

		    	fos = context.openFileOutput(BOTTLE_COUNT_FILE_NAME, mode);
		    	Oos = new ObjectOutputStream(fos);
	    		Oos.writeObject(bottleCountData);

		    	Oos.close();
		    }
		    catch(IOException e)
		    {}		
	}
	
}
