/**
 * 5-2 > Settings
 *
 *		Simply allows the user to select the days of the week
 *		that they are dieting. This is the default activity that
 *		is called when running the app OR clicking a notification.
 *
 * @author		David "oodavid" King
 * @copyright	Copyright (c) 2013 +
 */

package com.example.myandroid;
 
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.CheckBox;
import android.widget.Button;
import android.util.Log;

public class SettingsActivity extends Activity {

	@Override public void onCreate(Bundle savedInstanceState) {

		// Render a window with the main layout
		// NB: R.layout.main = /res/layout/main.xml
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		// Which days are we dieting?
		// NB: Defaults to monday and thursday
		SharedPreferences settings	= getSharedPreferences("MyPrefs", 0);
		CheckBox checkbox_monday	= (CheckBox) findViewById(R.id.checkbox_monday);	checkbox_monday.setChecked		(settings.getBoolean("monday",		true));
		CheckBox checkbox_tuesday	= (CheckBox) findViewById(R.id.checkbox_tuesday);	checkbox_tuesday.setChecked		(settings.getBoolean("tuesday",		false));
		CheckBox checkbox_wednesday	= (CheckBox) findViewById(R.id.checkbox_wednesday);	checkbox_wednesday.setChecked	(settings.getBoolean("wednesday",	false));
		CheckBox checkbox_thursday	= (CheckBox) findViewById(R.id.checkbox_thursday);	checkbox_thursday.setChecked	(settings.getBoolean("thursday",	true));
		CheckBox checkbox_friday	= (CheckBox) findViewById(R.id.checkbox_friday);	checkbox_friday.setChecked		(settings.getBoolean("friday",		false));
		CheckBox checkbox_saturday	= (CheckBox) findViewById(R.id.checkbox_saturday);	checkbox_saturday.setChecked	(settings.getBoolean("saturday",	false));
		CheckBox checkbox_sunday	= (CheckBox) findViewById(R.id.checkbox_sunday);	checkbox_sunday.setChecked		(settings.getBoolean("sunday",		false));

	}

	// Saves the settings
	public void onBtnClicked(View v){
		
		// I'm not sure how to keep the settings and checkboxes in scope so I'm re-declaring them....
		SharedPreferences			settings		= getSharedPreferences("MyPrefs", 0);
		SharedPreferences.Editor	settingsEditor	= settings.edit();

		// And save...
		CheckBox checkbox_monday	= (CheckBox) findViewById(R.id.checkbox_monday);	settingsEditor.putBoolean("monday",		checkbox_monday.isChecked()).commit();
		CheckBox checkbox_tuesday	= (CheckBox) findViewById(R.id.checkbox_tuesday);	settingsEditor.putBoolean("tuesday",	checkbox_tuesday.isChecked()).commit();
		CheckBox checkbox_wednesday	= (CheckBox) findViewById(R.id.checkbox_wednesday);	settingsEditor.putBoolean("wednesday",	checkbox_wednesday.isChecked()).commit();
		CheckBox checkbox_thursday	= (CheckBox) findViewById(R.id.checkbox_thursday);	settingsEditor.putBoolean("thursday",	checkbox_thursday.isChecked()).commit();
		CheckBox checkbox_friday	= (CheckBox) findViewById(R.id.checkbox_friday);	settingsEditor.putBoolean("friday",		checkbox_friday.isChecked()).commit();
		CheckBox checkbox_saturday	= (CheckBox) findViewById(R.id.checkbox_saturday);	settingsEditor.putBoolean("saturday",	checkbox_saturday.isChecked()).commit();
		CheckBox checkbox_sunday	= (CheckBox) findViewById(R.id.checkbox_sunday);	settingsEditor.putBoolean("sunday",		checkbox_sunday.isChecked()).commit();

		// Call the Notification Activity (this will "refresh" the notification)
		// Start and trigger the notificaation service
		Intent i = new Intent(getBaseContext(), NotificationService.class);
		startService(i);
		finish();

	}

}