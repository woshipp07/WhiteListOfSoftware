package com.zltd.whitelist;

import java.sql.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SetTimeReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String tm=intent.getExtras().getString("time");
		long lstringtimeInMillis = Long.parseLong(tm);
    	Intent intentdatatime = new Intent("android.intent.action.SET_DATETIME");
		intentdatatime.putExtra("datetime", lstringtimeInMillis);
		context.sendBroadcast(intentdatatime);

	}
}
