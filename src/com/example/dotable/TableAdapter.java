package com.example.dotable;

import java.util.ArrayList;
import java.util.List;

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
	private List<TableRow> table;
	
	public TableAdapter(Context context) {
		this.context = context;
		table = new ArrayList<TableAdapter.TableRow>();
	}
	
	public void bindTable(List<TableRow> table){
		this.table = table;
	}
	
	@Override
	public int getCount() {
		return table.size();
	}


	@Override
	public Object getItem(int position) {
		return table.get(position);
	} 


	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TableRow tableRow = table.get(position);
		return  new TableRowView(context, tableRow);   
		
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
	        private ArrayList<TableCell> cell;  
	        public TableRow(ArrayList<TableCell> cell) {  
	            this.cell = cell;  
	        }  
	        public int getSize() {  
	            return cell.size();  
	        }  
	        public TableCell getCellValue(int index) {  
	            if (index >= cell.size())  
	                return null;  
	            return cell.get(index);
	        }
			@Override
			public String toString() {
				return "TableRow [cell=" + cell + "]";
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


