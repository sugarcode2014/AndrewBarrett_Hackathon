package com.titanicapps.bottlerecycler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private static final int MIN_DENOM = 0;
	private static final int MAX_DENOM = 99;
	private static final int MAX_COUNT = 1000;
	private static final int MIN_COUNT = 0;
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1111;
	
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
		String tmpText = formatDenom(mDenom);
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
		
		String tmpText = formatDenom(mDenom);
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
		String tmpText = formatCount(mCount);
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
		String tmpText = formatCount(mCount);
		TextView countText = (TextView) findViewById(R.id.txtCount);		
		if(countText != null)
		{
			countText.setText(tmpText);
		}		
	}
	
	public void onVoiceInput(View view)
	{
		ConnectivityManager connMgr = (ConnectivityManager) 
	               getSystemService(Context.CONNECTIVITY_SERVICE);
	     NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	     if (networkInfo != null && networkInfo.isConnected())
	     {		 
			  Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			 
			  // Specify the calling package to identify your application
			  intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
			    .getPackage().getName());
			 
			  // Display an hint to the user about what he should say.
			 intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a number or count. Say stop when done.");
			 
	
			  intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
			    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			 
			  // Specify how many results you want to receive. The results will be
			  // sorted where the first result is the one with higher confidence.
			  intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
			  //Start the Voice recognizer activity for the result.
			  startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
		}
	    else
	    {
	    	showToastMessage("Network connection required for this feature.");
	    }
	}
	
	public void onAddTotal(View view)
	{

	}
	
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE) {
			// If Voice recognition is successful then it returns RESULT_OK
			boolean bStop = false;
			if (resultCode == RESULT_OK) {

				ArrayList<String> textMatchList = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				if (!textMatchList.isEmpty()) {

					String numText = "";

					for (int i = 0; i < textMatchList.size(); i++) {

						if (textMatchList.get(i).contains("stop")) {
							bStop = true;
						} else {
							if (textMatchList.get(i).matches(
									"\\b(^[0-9]{1,4}$)\\b")) {
								numText = textMatchList.get(i);
								Integer num = Integer.valueOf(numText);

								if (num != null) {
									mCount = num.intValue();
									String tmpText = formatCount(mCount);
									TextView countText = (TextView) findViewById(R.id.txtCount);
									if (countText != null) {
										countText.setText(tmpText);
									}
								}

								break;
							}
						}
					}

				}
				// Result code for various error.
			} else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
				showToastMessage("Audio Error");
			} else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR) {
				bStop = true;
				showToastMessage("Client Error");
			} else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
				bStop = true;
				showToastMessage("Network Error");
			} else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
				showToastMessage("No Match");
			} else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
				bStop = true;
				showToastMessage("Server Error");
			} else if (resultCode == 0) {
				bStop = true;
			}

			super.onActivityResult(requestCode, resultCode, data);

			if (bStop == false) {
				Button btnSpeak = (Button) findViewById(R.id.btn_voiceInput);
				onVoiceInput(btnSpeak);
			}
		}
	}
	
	@SuppressLint("DefaultLocale")
	private String formatCount(int count)
	{
		String formatedString = String.format(Locale.getDefault(),"%04d", count);
		return formatedString;
	}
	
	@SuppressLint("DefaultLocale")
	private String formatDenom(int denom)
	{
		String formatedString = String.format(Locale.getDefault(),getString(R.string._0_02d), denom);
		return formatedString;		
	}

	 public void checkVoiceRecognition() {
		 
		 Button btnSpeak = (Button)findViewById(R.id.btn_voiceInput);
		 if(btnSpeak != null)
		 {
			  // Check if voice recognition is present
			  PackageManager pm = getPackageManager();
			  List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
			    RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
			  if (activities.size() == 0) {
				   btnSpeak.setEnabled(false);
			   Toast.makeText(this, "Voice input unavailable",
			     Toast.LENGTH_SHORT).show();
			  }
		 }
	 }

	 
	 private void showToastMessage(String message)
	 {
		 Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	 }

}
