package com.example.dotable;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TongjiBean {
	private List<RowBean> result;
	private int code;
	private String msg;
	
	public void decoding(String json){
		try {
			JSONObject obj = new JSONObject(json);
			decoding(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void decoding(JSONObject obj){
		try {
			code = obj.getInt("code");
			msg = obj.getString("msg");
			JSONArray ja = obj.getJSONArray("result");
			result = new ArrayList<RowBean>();
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				RowBean bean = new RowBean();
				bean.decoding(jo);
				result.add(bean);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public List<RowBean> getResult() {
		return result;
	}

	public void setResult(List<RowBean> result) {
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "TongjiBean [result=" + result + ", code=" + code + ", msg=" + msg
				+ "]";
	}
	
	
	
}
