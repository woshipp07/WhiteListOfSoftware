package com.zltd.whitelist;

import android.graphics.drawable.Drawable;

public class packageBean {
	private Drawable image;
	private String name;
	private String packagename;
	private boolean ischeck;
	public packageBean(Drawable image, String name, String packagename) {
		this.image = image;
		this.name = name;
		this.packagename = packagename;
	}
	public packageBean(Drawable image, String name) {
		this.image = image;
		this.name = name;
	}
	public Drawable getImage() {
		return image;
	}

	public void setImage(Drawable image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	public boolean isIscheck() {
		return ischeck;
	}
	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}
}
