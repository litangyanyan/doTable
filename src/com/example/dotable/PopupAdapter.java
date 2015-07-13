package com.example.dotable;

import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dotable.MainActivity.TitleModel;

public class PopupAdapter extends BaseAdapter{
	private Context context;
	private List<TitleModel> titleModels;
	private LayoutInflater inflater;
	
	public PopupAdapter(Context context,List<TitleModel> titleModels) {
		this.context = context;
		this.titleModels = titleModels;
		inflater = LayoutInflater.from(context);
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
		final View view;
		if(convertView == null){
			view = inflater.inflate(R.layout.popup_item, null);
		}else{
			view = (View) convertView;
		}
		TextView vText = (TextView) view.findViewById(R.id.pop_item_text);
		vText.setText(model.title);
		setSelect(view, model);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(model.isShow){
					model.isShow = false;
				}else{
					model.isShow = true;
				}
				setSelect(view, model);
			}
		});
		return view;
	}
	
	private void setSelect(View v,TitleModel titleModel){
		TextView text = (TextView) v.findViewById(R.id.pop_item_text);
		ImageView icon = (ImageView) v.findViewById(R.id.pop_item_icon);
		if(titleModel.isShow){
			text.setTextColor(Color.WHITE);
			v.setBackgroundColor(Color.parseColor("#1B607D"));
			icon.setBackgroundResource(R.drawable.pop_title_select);
		}else{
			text.setTextColor(Color.BLACK);
			v.setBackgroundColor(Color.parseColor("#FFFFFF"));
			icon.setBackgroundResource(R.drawable.pop_title_noselect);
		}		
	}
	
}
