package com.example.dotable;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dotable.MainActivity.TitleModel;

public class PopupAdapter extends BaseAdapter{
	private Context context;
	private List<TitleModel> titleModels;
	
	public PopupAdapter(Context context,List<TitleModel> titleModels) {
		this.context = context;
		this.titleModels = titleModels;
	}
	

	@Override
	public int getCount() {
		return titleModels.size();
	}

	@Override
	public Object getItem(int position) {
		return titleModels.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final TitleModel model = titleModels.get(position);
		final TextView tv;
		if(convertView == null){
			tv = new TextView(context);
		}else{
			tv = (TextView) convertView;
		}
		tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(model.isShow){
					model.isShow = false;
					tv.setBackgroundColor(Color.GRAY);
				}else{
					model.isShow = true;
					tv.setBackgroundColor(Color.WHITE);
				}
			}
		});
		tv.setText(model.title);
		return tv;
	}

}
