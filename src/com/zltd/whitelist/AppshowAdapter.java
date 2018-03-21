package com.zltd.whitelist;

import java.util.ArrayList;
import java.util.List;

import com.zltd.whitelist.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

class AppshowAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<packageBean>mDates;
	private Context mContext;

	public AppshowAdapter(Context mContext,ArrayList<packageBean>mDates) {
		this.mContext=mContext;
		this.mDates=mDates;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mDates == null ? 0 : mDates.size();
	}

	@Override
	public Object getItem(int position) {
		return mDates.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		packageBean bean = mDates.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_showapp, null);
			holder.image = (ImageView) convertView.findViewById(R.id.img);
			holder.text = (TextView) convertView.findViewById(R.id.name);
			holder.check = (CheckBox) convertView.findViewById(R.id.check);
			final ViewHolder finalViewHolder = holder;
			holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					packageBean info = (packageBean) finalViewHolder.check.getTag();
					info.setIscheck(compoundButton.isChecked());
				}
			});
			convertView.setTag(holder);
			holder.check.setTag(bean);
		} else {
			holder = (ViewHolder) convertView.getTag();
			holder.check.setTag(bean);
		}

		// img.setBackgroundResource(item.mDrawableResId);
		// img.setVisibility(View.INVISIBLE);
		holder.image.setImageDrawable(bean.getImage());
		holder.text.setText(bean.getName());
		holder.check.setChecked(bean.isIscheck());
		return convertView;
	}

	class ViewHolder {
		ImageView image;
		TextView text;
		CheckBox check;

	}
}



