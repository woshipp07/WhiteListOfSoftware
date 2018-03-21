package com.zltd.whitelist;


import com.zltd.whitelist.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn_1).setOnClickListener(
				new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent=new Intent(MainActivity.this,StorageApkImportActivity.class);
						startActivity(intent);
					}

				});
		findViewById(R.id.btn_2).setOnClickListener(
				new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent=new Intent(MainActivity.this,InstallApkImportActivity.class);
						startActivity(intent);
					}

				});
		
	}
}
