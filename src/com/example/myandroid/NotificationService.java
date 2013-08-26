package com.example.myandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.util.Log;

public class NotificationService extends Service {

	// Id of our Notification
	private static final int HELLO_ID = 1;
 
	@Override public int onStartCommand(Intent intent, int flags, int startId) {

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
			CharSequence	tickerText		= "Diet Day!";
			long			when			= System.currentTimeMillis();
			Notification	notification	= new Notification(icon, tickerText, when);

			// Set the "Latest Event Info" to create the content when we view it...
			// NB: We also assign an intent which is fired when we click the notification
			CharSequence	contentTitle		= "My notification";
			CharSequence	contentText			= "Hello World!";
			Intent			notificationIntent	= new Intent(this, SettingsActivity.class);
			PendingIntent	contentIntent		= PendingIntent.getActivity(this, 0, notificationIntent, 0);
			notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

			// Make it an "ongoing" event, this means it can't be "cleared"
			notification.flags = Notification.FLAG_ONGOING_EVENT;

			// Send the notification
			nm.notify(HELLO_ID, notification);

		// There are a few options as to what to return
		// I'm not 100% that this is the correct one (for lack of understanding)
		return Service.START_NOT_STICKY;
	}

	//
	// Not entirely sure why we need these parts
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