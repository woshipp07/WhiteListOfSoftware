package com.zltd.whitelist;

import com.zltd.whitelist.StorageApkImportActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
	/*	if (Intent.ACTION_BOOT_COMPLETED.equals(arg1.getAction())) {*/
			Intent intent = new Intent(context, StorageApkImportActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}
	/*}*/
}
