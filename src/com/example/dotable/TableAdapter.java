package com.example.dotable;

import java.util.ArrayList;
import java.util.List;

import com.example.dotable.MainActivity.TitleModel;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TableAdapter extends BaseAdapter{
	private final static String TAG = TableAdapter.class.getSimpleName();
	private Context context;
	private List<RowBean> rowBeans;
	private List<TitleModel> titleModels;
	
	public TableAdapter(Context context) {
		this.context = context;
		rowBeans = new ArrayList<RowBean>();
		titleModels = new ArrayList<MainActivity.TitleModel>();
	}
	
	public void bind(List<RowBean> rowBeans,List<TitleModel> titleModels){
		this.rowBeans = rowBeans;
		this.titleModels = titleModels;
	}
	
	public void setTitleModels(List<TitleModel> titleModels){
		this.titleModels = titleModels;
	}
	
	public void addRowBean(RowBean rowBean){
		this.rowBeans.add(rowBean);
	}
	
	private TableRow getTableTitle(List<TitleModel> titleModels){
		ArrayList<TableCell> cells = new ArrayList<TableCell>();
		for (TitleModel titleModel : titleModels) {
			if (titleModel.isShow) {
				TableCell cell = new TableCell(titleModel.title, 150, 50, "#dddddd");
				cells.add(cell);
			}
		}
		return new TableRow(cells);
	}
	private TableRow getTableRow(RowBean rowBean,List<TitleModel> titleModels){
		ArrayList<TableCell> cells = new ArrayList<TableAdapter.TableCell>();
		for(TitleModel titleModel : titleModels){
			if(titleModel.isShow){
				cells.add(getTableCell(rowBean, titleModel));
			}
		}
		return new TableRow(cells);
	}
	
	private TableCell getTableCell(RowBean rowBean,TitleModel titleModel){
		String value = rowBean.getValue(titleModel.key);
		int width = 150;
		int height = 50;
		String background = "#ffffff";
		if(titleModel.key.equals("werks")){
			background = "#dddddd";
		}
		return new TableCell(value, width, height, background);
	}
	
	@Override
	public int getCount() {
		return rowBeans.size() + 1;
	}


	@Override
	public Object getItem(int position) {
		if(position > 0){
			return rowBeans.get(position);
		}else{
			return null;
		}
	} 


	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(position == 0){
			return new TableRowView(context, getTableTitle(titleModels));
		}else{
			RowBean rowBean = rowBeans.get(position - 1);
			TableRow tableRow = getTableRow(rowBean, titleModels);
			return  new TableRowView(context, tableRow);   
		}		
	}  
	
	
	
	 class TableRowView extends LinearLayout {  
	        public TableRowView(Context context, TableRow tableRow) {  
	            super(context);  
	            this.setOrientation(LinearLayout.HORIZONTAL);  
	            for (int i = 0; i < tableRow.getSize(); i++) {//逐个格单元添加到行  
	                TableCell tableCell = tableRow.getCellValue(i);  
	                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(  
	                        tableCell.width, tableCell.height);//按照格单元指定的大小设置空间  
	                layoutParams.setMargins(0, 0, 1, 1);//预留空隙制造边框  
                    TextView textCell = new TextView(context);  
                    textCell.setLines(1);  
                    textCell.setGravity(Gravity.CENTER);  
                    textCell.setBackgroundColor(Color.parseColor(tableCell.background));
                    textCell.setText(tableCell.value);  
                    this.addView(textCell, layoutParams); 
	            }  
	            this.setBackgroundColor(Color.BLACK);
	        }  
	    }  
	
	
	 static public class TableRow {  
	        private ArrayList<TableCell> cells;  
	        public TableRow(ArrayList<TableCell> cells) {  
	            this.cells = cells;  
	        }  
	        public int getSize() {  
	            return cells.size();  
	        }  
	        public TableCell getCellValue(int index) {  
	            if (index >= cells.size())  
	                return null;  
	            return cells.get(index);
	        }
			@Override
			public String toString() {
				return "TableRow [cell=" + cells + "]";
			}  
	    }  


	 static public class TableCell {   
	        public String value;  
	        public int width;  
	        public int height;    
	        public String background;
	        public TableCell(String value, int width, int height ,String background) {  
	            this.value = value;  
	            this.width = width;  
	            this.height = height;   
	            this.background = background;
	        }
	    }



}


