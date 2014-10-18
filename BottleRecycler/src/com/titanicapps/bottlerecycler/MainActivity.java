package com.titanicapps.bottlerecycler;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	static int MIN_DENOM = 0;
	static int MAX_DENOM = 99;
	static int MAX_COUNT = 1000;
	static int MIN_COUNT = 0;
	
	private int mDenom = 0;
	private int mCount = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();		

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public void onDecreaseDenom(View view)
	{
		if(mDenom-- <= MIN_DENOM)
		{
			mDenom = MIN_DENOM;
		}
		String tmpText = String.valueOf(mDenom);
		TextView denomText = (TextView) findViewById(R.id.txtDenom);		
		if(denomText != null)
		{
			denomText.setText(tmpText);
		}
	}
	
	public void onIncreaseDenom(View view)
	{
		if(mDenom++ >= MAX_DENOM)
		{
			mDenom = MAX_DENOM;
		}
		
		String tmpText = String.valueOf(mDenom);
		TextView denomText = (TextView) findViewById(R.id.txtDenom);
		if(denomText != null)
		{
			denomText.setText(tmpText);
		}
	}
	
	public void onIncreaseCount(View view)
	{
		if(mCount++ >= MAX_COUNT)
		{
			mCount = MAX_COUNT;
		}
		String tmpText = String.valueOf(mCount);
		TextView countText = (TextView) findViewById(R.id.txtCount);		
		if(countText != null)
		{
			countText.setText(tmpText);
		}		
	}
	
	public void onDecreaseCount(View view)
	{
		if(mCount-- <= MIN_COUNT)
		{
			mCount = MIN_COUNT;
		}
		String tmpText = String.valueOf(mCount);
		TextView countText = (TextView) findViewById(R.id.txtCount);		
		if(countText != null)
		{
			countText.setText(tmpText);
		}		
	}
	
	private String formatCount(int count)
	{
	
	}

}
