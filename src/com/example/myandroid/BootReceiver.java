/**
 * 5-2 > BootReceiver
 *
 * @author		David "oodavid" King
 * @copyright	Copyright (c) 2013 +
 */

package com.example.myandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver{
	@Override public void onReceive(Context context, Intent intent){
		// Start the Notification Service
		Intent service = new Intent(context, NotificationService.class);
		context.startService(service);
	}
}