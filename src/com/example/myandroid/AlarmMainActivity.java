package com.example.myandroid;

import java.util.Calendar;
 
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
 
public class AlarmMainActivity extends Activity 
{
	// Id of our Notification
	private static final int HELLO_ID = 1;
 
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		// Set the content of the window
		// NB: R.layout.main = /res/layout/main.xml
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		//
		// NOTIFICATION
		//

			// We use the application Context
			// NB: I need to figure out *why* we this is required
			Context context = getApplicationContext();

			// Reference the Notification Manager
			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

			// Create a notification Object
			// NB: The tickertext appears in the notification BAR when first added
			int				icon			= R.drawable.icon;
			CharSequence	tickerText		= "Hello";
			long			when			= System.currentTimeMillis();
			Notification	notification	= new Notification(icon, tickerText, when);

			// Set the "Latest Event Info" to create the content when we view it...
			// NB: We also assign an intent which is fired when we click the notification
			CharSequence	contentTitle		= "My notification";
			CharSequence	contentText			= "Hello World!";
			Intent			notificationIntent	= new Intent(this, AlarmReceiverActivity.class);
			PendingIntent	contentIntent		= PendingIntent.getActivity(this, 0, notificationIntent, 0);
			notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

			// Make it an "ongoing" event, this means it can't be "cleared"
			notification.flags = Notification.FLAG_ONGOING_EVENT;

			// Send the notification
			nm.notify(HELLO_ID, notification);

		//
		// AN ALARM / TIMER
		//
			/*

			// Create an offset from the current time in which the alarm will go off.
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, 5);

			// Create a new PendingIntent and add it to the AlarmManager
			Intent			intent			= new Intent(this, AlarmReceiverActivity.class);
			PendingIntent	pendingIntent	= PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
			AlarmManager	am				= (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

			*/
	}
}