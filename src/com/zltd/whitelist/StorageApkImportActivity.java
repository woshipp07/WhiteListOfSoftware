package com.zltd.whitelist;

import java.io.File;
import java.util.ArrayList;

import com.zltd.whitelist.R;
import com.zltd.industry.WhiteListManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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

public class StorageApkImportActivity extends Activity {

	
	ArrayList<File> mListFile=new ArrayList<File>();
	private ArrayList<packageBean> mList;
	WhiteListManager wm;
	private Context mContext;
	private AppshowAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		wm =new WhiteListManager();///////////////////////////////////

        File path = Environment.getExternalStorageDirectory();  
        System.out.println(path.toString());
        mListFile.clear();
        filePath(path);  
        setContentView(R.layout.activity_storageapkimport);
        //setContentView(R.layout.activity_main);
        
        ListView mListView = (ListView) findViewById(R.id.listvew);
		mList = new ArrayList<packageBean>();
		//mList.addAll(AppUtils.getInstalledApps(this, true));
		//mList =getMessageStorageApps();
		mList =toRepeatgetMessageStorageApps();
		adapter = new AppshowAdapter(mContext,mList);
		mListView.setAdapter(adapter);
		
		Button okBtn = (Button) findViewById(R.id.ok_btn1);
		okBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(BootActivity.this, "导入成功", Toast.LENGTH_SHORT).show();
				save();
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
			Toast.makeText(StorageApkImportActivity.this, "导入成功", Toast.LENGTH_SHORT).show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			Toast.makeText(StorageApkImportActivity.this, "导入失败", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	
	}
	
	public void filePath(File file) {  
        if (file != null && file.exists() && file.isDirectory()) {  
            File[] files = file.listFiles();  
            for (File file2 : files) {  
                if (file2.listFiles() == null) { 
                	String name_s=file2.getName();
                	if(name_s.toLowerCase().endsWith(".apk"))
                	{
                		String str = "11111111" + file2.getName() + " 路径:"+ file2.getPath();  
                		//System.out.println(str);  
                		//getMessageFromApk(file2);
                		mListFile.add(file2);
                	}
                }else if (file2.isDirectory()){ 
                     filePath(file2);  
                }
            }  
        } else  
            System.out.println("文件不存�?......");  
    } 
	private ArrayList<packageBean> toRepeatgetMessageStorageApps(){
		ArrayList<packageBean>mRepeatList;
		int j;
		ArrayList<packageBean> mNotRepeatList = new ArrayList<packageBean>();
		ArrayList<String> stringList = new ArrayList<String>();
		mRepeatList=getMessageStorageApps();
/*		for(packageBean pb:mRepeatList){
			String packageName=pb.getPackagename();
			stringList.add(packageName);
		}*/
		for (int i=0;i<mRepeatList.size();i++){
			String st=mRepeatList.get(i).getPackagename();
			for (j=0;j<mNotRepeatList.size();j++){
				if(mNotRepeatList.get(j).getPackagename().equals(st)){
					break;
				}
			}
			if(j==(mNotRepeatList.size())){
				mNotRepeatList.add(mRepeatList.get(i));
			}//说明没有重复�?,break没有执行
			
			
		}
		return mNotRepeatList;
	}
	
	private ArrayList<packageBean> getMessageStorageApps(){
		
		ArrayList<packageBean>mPackageList=new ArrayList<packageBean>();
		String packNamewhite=null;
		try {
			packNamewhite=wm.getPackageNameWhite();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (packNamewhite==null) {
			packNamewhite="";
		}
		for(int i=0;i<mListFile.size();i++){
			String apk_path = mListFile.get(i).getAbsolutePath();// apk文件的绝对路�?
			PackageManager pm = getPackageManager();
            //PackageInfo packageInfo = pm.getPackageArchiveInfo(apk_path, PackageManager.GET_ACTIVITIES);
            PackageInfo packageInfo = pm.getPackageArchiveInfo(apk_path, PackageManager.GET_META_DATA);
            ApplicationInfo appInfo = packageInfo.applicationInfo;  
           // String name=appInfo.loadLabel(pm).toString();
            //Drawable image=appInfo.loadIcon(pm);
            appInfo.sourceDir=apk_path;
            appInfo.publicSourceDir=apk_path;
            Drawable image=pm.getApplicationIcon(appInfo);
            String name=pm.getApplicationLabel(appInfo).toString();
            String packagename=appInfo.packageName;
            packageBean newInfo=new packageBean(image,name,packagename);
            Log.v("1111", name+"22"+packagename+"33"+apk_path);
	        if (packNamewhite.contains(packagename+",")) 
	        	newInfo.setIscheck(true);
			else
				newInfo.setIscheck(false);
	        
	        mPackageList.add(newInfo);
		}
		return mPackageList;
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		 super.onBackPressed();
	}
}
