package com.example.dotable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dotable.TableAdapter.TableCell;
import com.example.dotable.TableAdapter.TableRow;

public class MainActivity extends Activity implements OnClickListener {
	private final static String TAG = MainActivity.class.getSimpleName();
	private Context context;
	private Activity activity;
	private List<TitleModel> titleModels;
	private Handler uiHandler;
	
	private ListView vShow;
	private TextView vBack, vSelect;
	private PopupWindow vPopup;

	private String tongjiJson;
	private TongjiBean tongjiBean;

	private TableAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;
		activity = this;
		titleModels = getTitleModels();
		uiHandler = new Handler();
		
		vShow = (ListView) findViewById(R.id.vShow);
		vBack = (TextView) findViewById(R.id.vBack);
		vSelect = (TextView) findViewById(R.id.vSelect);
		initPopup(titleModels);
		vBack.setOnClickListener(this);
		vSelect.setOnClickListener(this);
		
		adapter = new TableAdapter(context);
		vShow.setAdapter(adapter);
		
		tongjiJson = Utils.getFromAssets(context, "tongji.json");
		tongjiBean = new TongjiBean();
		tongjiBean.decoding(tongjiJson);
		adapter.bind(tongjiBean.getResult(), titleModels);
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				uiHandler.post(new Runnable() {
					@Override
					public void run() {
						vPopup.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
//						vPopup.showAsDropDown(vSelect);		
					}
				});				
			}
		}, 1 * 1000);
		
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		titleModels = null;
		tongjiBean = null;
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.vSelect:
			vPopup.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
//			vPopup.showAsDropDown(vSelect);
			break;
		}
	}
	
	
	public void initPopup(final List<TitleModel> titleModels){
		View popupView = LayoutInflater.from(context).inflate(R.layout.popup, null);
		GridView vPopupMain = (GridView) popupView.findViewById(R.id.popup_main);
		Button vPopupSubmit = (Button) popupView.findViewById(R.id.popup_submit);
		vPopupSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vPopup.dismiss();
				adapter.setTitleModels(titleModels);
				adapter.notifyDataSetChanged();
			}
		});
		PopupAdapter adapter = new PopupAdapter(context, titleModels);
		vPopupMain.setAdapter(adapter);
		
		vPopup = new PopupWindow(popupView, LayoutParams.MATCH_PARENT	, LayoutParams.MATCH_PARENT);
		vPopup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
	}

	class TitleModel {
		int order;
		String key;
		String title;
		boolean isShow;

		public TitleModel( String key, String title, boolean isShow) {
			super();
			this.key = key;
			this.title = title;
			this.isShow = isShow;
		}
	}

	private List<TitleModel> getTitleModels() {
		List<TitleModel> list = new ArrayList<MainActivity.TitleModel>();
//		list.add(new TitleModel("_id ","编码", true));
		list.add(new TitleModel("werks","电厂", true));
		list.add(new TitleModel("name1","电厂名称", true));
		list.add(new TitleModel("sort","风场", true));
		list.add(new TitleModel("sorttxt","风场描述", true));
		list.add(new TitleModel("arbpl","班组", true));
		list.add(new TitleModel("arbpltxt","班组描述", true));
		list.add(new TitleModel("find1","发现条数1", true));
		list.add(new TitleModel("find2","发现条数2", true));
		list.add(new TitleModel("find3","发现条数3", true));
		list.add(new TitleModel("find4","发现条数4", true));
		list.add(new TitleModel("isfind","被发现条数", true));
		list.add(new TitleModel("existnum","存在条数", true));
		list.add(new TitleModel("completenum","完成条数", true));
		list.add(new TitleModel("lastexistnum","上月存在条数", true));
		list.add(new TitleModel("notipercentages","消缺率", true));
		list.add(new TitleModel("notiontimepercent","消缺及时率", true));
		list.add(new TitleModel("notifindpercent","缺陷发生变化率", true));
		list.add(new TitleModel("missnum","漏点个数", true));
		list.add(new TitleModel("overdue1","超期1", true));
		list.add(new TitleModel("overdue2","超期2", true));
		list.add(new TitleModel("overdue3","超期3", true));
		list.add(new TitleModel("overdue4","超期4", true));
		list.add(new TitleModel("delay1","延期1", true));
		list.add(new TitleModel("delay2","延期2", true));
		list.add(new TitleModel("delay3","延期3", true));
		list.add(new TitleModel("delay4","延期4", true));
		return list;
	}
}
