package com.zltd.whitelist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zltd.whitelist.R;
import com.zltd.industry.WhiteListManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InstallApkImportActivity extends Activity {

	private ArrayList<packageBean> mList;
	WhiteListManager wm;
	private Context mContext;
	private AppshowAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		//wm =new WhiteListManager();
		setContentView(R.layout.activity_storageapkimport);
        ListView mListView = (ListView) findViewById(R.id.listvew);
		mList =getMessageInstallApps();
		adapter = new AppshowAdapter(mContext,mList);
		mListView.setAdapter(adapter);
		
		Button okBtn = (Button) findViewById(R.id.ok_btn1);
		okBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(BootActivity.this, "导入成功", Toast.LENGTH_SHORT).show();
				//save();///////////////////////////
			}

		});
		
	}
	private void save(){
		//Toast.makeText(mContext, "导入成功", Toast.LENGTH_LONG);
		StringBuffer sb =new StringBuffer();
		for(int i=0;i<mList.size();i++){
			packageBean pB=mList.get(i);
			if(pB.isIscheck()){
				sb.append(pB.getPackagename()).append(",");
			}
		}
	try {
			wm.addPackageName(sb.toString());
			Toast.makeText(InstallApkImportActivity.this, "导入成功", Toast.LENGTH_SHORT).show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			Toast.makeText(InstallApkImportActivity.this, "导入失败", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	
	}
	
	

	
	private ArrayList<packageBean> getMessageInstallApps(){
		
		ArrayList<packageBean>mPackageList=new ArrayList<packageBean>();
		String packNamewhite=null;
/*		try {
			packNamewhite=wm.getPackageNameWhite();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*//////////////////////////////////////
		if (packNamewhite==null) {
			packNamewhite="";
		}
		  List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		    for (int i = 0; i < packs.size(); i++) {
		    	
		        PackageInfo p = packs.get(i);
		       if( mContext.getPackageManager().getLaunchIntentForPackage(p.packageName)==null){
		    	   continue;
		       }
		       if((p.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)<=0){
		    	   
		      
		    	   String appname = p.applicationInfo.loadLabel(mContext.getPackageManager())
		        		.toString();
		    	   String pname = p.packageName;
		    	   ComponentName mComponentName = new ComponentName(pname,pname+".receivers.BootReceiver");
		    	   //xx就是软件名字，然后后面就是一般用来接收开机完成广播的组件名称。
		    	   int a=mContext.getPackageManager().getComponentEnabledSetting(mComponentName);

		    	   Drawable icon = p.applicationInfo.loadIcon(mContext.getPackageManager());
		    	   packageBean newInfo = new packageBean(icon,appname,pname);
		    	   if (packNamewhite.contains(pname+","))
		    		   newInfo.setIscheck(true);
		    	   else
		    		   newInfo.setIscheck(false);
		    	   mPackageList.add(newInfo);
		       	}else{
		    	   continue;
		       }
		    }
		return mPackageList;
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		 super.onBackPressed();
	}
}
