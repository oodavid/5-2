package com.example.myandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import java.util.Calendar;

public class NotificationService extends Service {

	// Id of our Notification
	private static final int NOTIFICATION_ID = 1;
 
	@Override public int onStartCommand(Intent intent, int flags, int startId) {

		// Setup
		NotificationManager	nm			= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		SharedPreferences	settings	= getSharedPreferences("MyPrefs", 0);
		Boolean				diet_day	= false;
		Calendar			calendar	= Calendar.getInstance();
		int					day			= calendar.get(Calendar.DAY_OF_WEEK);
		// Read the settings for this day...
		switch(day){
			case Calendar.MONDAY:		diet_day = settings.getBoolean("monday",	true);	break;
			case Calendar.TUESDAY:		diet_day = settings.getBoolean("tuesday",	false);	break;
			case Calendar.WEDNESDAY:	diet_day = settings.getBoolean("wednesday",	false);	break;
			case Calendar.THURSDAY:		diet_day = settings.getBoolean("thursday",	false);	break;
			case Calendar.FRIDAY:		diet_day = settings.getBoolean("friday",	true);	break;
			case Calendar.SATURDAY:		diet_day = settings.getBoolean("saturday",	false);	break;
			case Calendar.SUNDAY:		diet_day = settings.getBoolean("sunday",	false);	break;
		}
		// Either hide or show the notification
		if(diet_day){

			// Create a notification Object
			// NB: The tickertext appears in the notification BAR when first added
			int				icon			= R.drawable.icon;
			CharSequence	tickerText		= "Diet Day!";
			long			when			= System.currentTimeMillis();
			Notification	notification	= new Notification(icon, tickerText, when);

			// Set the "Latest Event Info" to create the content when we view it...
			// NB: We also assign an intent which is fired when we click the notification
			CharSequence	contentTitle		= "My notification";
			CharSequence	contentText			= "Hello World!";
			Intent			notificationIntent	= new Intent(this, SettingsActivity.class);
			PendingIntent	contentIntent		= PendingIntent.getActivity(this, 0, notificationIntent, 0);
			notification.setLatestEventInfo(getApplicationContext(), contentTitle, contentText, contentIntent);

			// Make it an "ongoing" event, this means it can't be "cleared"
			notification.flags = Notification.FLAG_ONGOING_EVENT;

			// Send the notification
			nm.notify(NOTIFICATION_ID, notification);

		} else {

			// Cancel the notification
			nm.cancel(NOTIFICATION_ID);
		}

		// There are a few options as to what to return
		// I'm not 100% that this is the correct one (for lack of understanding)
		return Service.START_NOT_STICKY;
	}

	//
	// Not entirely sure why we need these parts however the Service (not Activity) requires it to work
	// I've stripped it down to the smallest amount of codez that work
	//
    public class LocalBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }
	private final IBinder mBinder = new LocalBinder();
	@Override public IBinder onBind(Intent intent) {
		return mBinder;
	}

} 