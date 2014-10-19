package com.titanicapps.bottlerecycler;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



import java.util.Calendar;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class HistoryActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);

		if (savedInstanceState == null) {

			
			RecycleHistoryListData history = (RecycleHistoryListData)getIntent().getExtras().getSerializable("history");
			showTable(history.getRecycleHistoryDataList());
		}
	}
	
	public void showTable(ArrayList<RecycleHistoryData> historyData)
	{
		int screenSize = getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;

		int fontSize = 18;
		switch (screenSize) {
		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			fontSize = 28;
			break;
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			fontSize = 32;
			break;
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			fontSize = 20;
			break;
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			fontSize = 18;
			break;
		default:
			fontSize = 18;
		}

		TableLayout tableLayout = (TableLayout) findViewById(R.id.historyTable);
		tableLayout.removeAllViews();

		//title columns
		TableRow titlesRow = new TableRow(this);
 		titlesRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		     LayoutParams.WRAP_CONTENT));
 		
 		
	    TextView tvDates = new TextView(this);
	    tvDates.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	      LayoutParams.WRAP_CONTENT));
	    tvDates.setBackgroundColor(0xFF065902);
	    tvDates.setText(R.string.date_title);
	    tvDates.setTextSize(fontSize);
	    tvDates.setTextColor(0xFFFFFFFF);
	    titlesRow.addView(tvDates);
	    
	    TextView tvCounts = new TextView(this);
	    tvCounts.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	      LayoutParams.WRAP_CONTENT));
	    tvCounts.setBackgroundColor(0xFF065902);
	    tvCounts.setText(R.string.total_title);
	    tvCounts.setTextSize(fontSize);
	    tvCounts.setTextColor(0xFFFFFFFF);
	    titlesRow.addView(tvCounts);
	    
	    TextView tvValues = new TextView(this);
	    tvValues.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	      LayoutParams.WRAP_CONTENT));
	    tvValues.setBackgroundColor(0xFF065902);
	    tvValues.setText(R.string.value_title);
	    tvValues.setTextSize(fontSize);
	    tvValues.setTextColor(0xFFFFFFFF);
	    titlesRow.addView(tvValues);
		  
	    tableLayout.addView(titlesRow);
		
		for (int i = 0; i < historyData.size(); i++) {
		

			TableRow row = new TableRow(this);
			   row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
			     LayoutParams.WRAP_CONTENT));
			   
	   
			   
			 //Date
		    TextView tv1 = new TextView(this);
		    tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		      LayoutParams.WRAP_CONTENT));
			 if(i%2 == 0)
			 {
				 tv1.setBackgroundColor(0xFFA7DBA4);
			 }
		   
		
		    //tv1.setPadding(5, 5, 5, 5);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(historyData.get(i).getDateReturnedInMs());
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy",
					Locale.CANADA);
			String date = dateFormat.format(calendar.getTime());
		    tv1.setText(date);
		    //tv1.setTextColor(0x00000000);
		    
		    tv1.setTextSize(fontSize);
		    row.addView(tv1);
		    
		    //total count
		    TextView tv2 = new TextView(this);
		    tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		      LayoutParams.WRAP_CONTENT));
		    //tv2.setBackgroundColor(cellColor);
		    Long totalCount = Long.valueOf(0);
		    Long totalValue = Long.valueOf(0);
		    for(int n=0; n < historyData.get(i).getCountRecords().size(); n++)
		    {
		    	long count = historyData.get(i).getCountRecords().get(n).getCount();
		    	totalValue += count * historyData.get(i).getCountRecords().get(n).getDenomCents();
		    	totalCount += count;
		    	
		    }
		    tv2.setText(totalCount.toString());
		    //tv2.setTextColor(0x00000000);
		    tv2.setTextSize(fontSize);
			 if(i%2 == 0)
			 {
				 tv2.setBackgroundColor(0xFFA7DBA4);
			 }
		    row.addView(tv2);
		    
		    //total value
		    TextView tv3 = new TextView(this);
		    tv3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		      LayoutParams.WRAP_CONTENT));
		    //tv2.setBackgroundColor(cellColor);
		    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());

		    tv3.setText(currencyFormat.format(totalValue.longValue()/100.0));
		    //tv3.setTextColor(0x00000000);
		    tv3.setTextSize(fontSize);
			 if(i%2 == 0)
			 {
				 tv3.setBackgroundColor(0xFFA7DBA4);
			 }
		    row.addView(tv3);
		    
		    tableLayout.addView(row);
		}
			 
			 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
