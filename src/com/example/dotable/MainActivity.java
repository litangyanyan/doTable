package com.example.dotable;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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
	private ListView vShow;
	private TextView vBack, vSelect;
	private PopupWindow vPopup;

	private List<TitleModel> titleModels;

	private String tongjiJson;
	private TongjiBean tongjiBean;

	private TableAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;
		activity = this;

		vShow = (ListView) findViewById(R.id.vShow);
		vBack = (TextView) findViewById(R.id.vBack);
		vSelect = (TextView) findViewById(R.id.vSelect);

		adapter = new TableAdapter(context);
		vShow.setAdapter(adapter);
		vBack.setOnClickListener(this);
		vSelect.setOnClickListener(this);
		
		titleModels = getTitleModels();
		
		initPopup(titleModels);
		
		tongjiJson = Utils.getFromAssets(context, "tongji.json");
		tongjiBean = new TongjiBean();
		tongjiBean.decoding(tongjiJson);

		ArrayList<TableRow> table = getTable(titleModels);
		adapter.bindTable(table);
		adapter.notifyDataSetChanged();

	}

	private List<TitleModel> getTitleModels() {
		List<TitleModel> list = new ArrayList<MainActivity.TitleModel>();
		String[] titles = { "编码", "电厂", "电厂名称", "风场", "风场描述", "班组", "班组描述",
				"发现条数1", "发现条数2", "发现条数3", "发现条数4", "被发现条数", "存在条数", "完成条数",
				"上月存在条数", "消缺率", "消缺及时率", "缺陷发生变化率", "漏点个数", "超期1", "超期2",
				"超期3", "超期4", "延期1", "延期2", "延期3", "延期4", };
		String[] keys = { "_id", "werks", "name1", "sort", "sorttxt", "arbpl",
				"arbpltxt", "find1", "find2", "find3", "find4", "isfind",
				"existnum", "completenum", "lastexistnum", "notipercentages",
				"notiontimepercent", "notifindpercent", "missnum", "overdue1",
				"overdue2", "overdue3", "overdue4", "delay1", "delay2",
				"delay3", "delay4" };
		for (int i = 0; i < titles.length; i++) {
			TitleModel model = new TitleModel(i, keys[i], titles[i], true);
			list.add(model);
		}
		return list;
	}

	private ArrayList<TableRow> getTable(List<TitleModel> titleModels) {
		ArrayList<TableRow> table = new ArrayList<TableRow>();

		ArrayList<TableCell> titleCells = new ArrayList<TableCell>();
		for (TitleModel titleModel : titleModels) {
			if (titleModel.isShow) {
				TableCell cell = new TableCell(titleModel.title, 150, 50, "#dddddd");
				titleCells.add(cell);
			}
		}
		TableRow titleRow = new TableRow(titleCells);
		table.add(titleRow);

		ArrayList<RowBean> rowBeans = (ArrayList<RowBean>) tongjiBean
				.getResult();
		Log.i(TAG, "-------getTable()--rowBeans" + rowBeans);
		for (RowBean bean : rowBeans) {
			ArrayList<TableCell> cells = new ArrayList<TableAdapter.TableCell>();
			for (TitleModel titleModel : titleModels) {
				if (titleModel.isShow) {
					String value = bean.getValue(titleModel.key);
					TableCell cell = new TableCell(value, 150, 50, "#ffffff");
					cells.add(cell);
				}
			}
			TableRow row = new TableRow(cells);
			table.add(row);
		}
		return table;
	}



	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.vSelect:
			Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
			vPopup.showAsDropDown(vSelect);
			
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
				ArrayList<TableRow> table = getTable(titleModels);
				adapter.bindTable(table);
				adapter.notifyDataSetChanged();
			}
		});
		PopupAdapter adapter = new PopupAdapter(context, titleModels);
		vPopupMain.setAdapter(adapter);
		
		vPopup = new PopupWindow(popupView, LayoutParams.MATCH_PARENT	, LayoutParams.WRAP_CONTENT);
		vPopup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); 
	}
	
	
	
	

	class TitleModel {
		int order;
		String key;
		String title;
		boolean isShow;

		public TitleModel(int order, String key, String title, boolean isShow) {
			super();
			this.order = order;
			this.key = key;
			this.title = title;
			this.isShow = isShow;
		}
	}

}
