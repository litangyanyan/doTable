package com.example.dotable;

import org.json.JSONException;
import org.json.JSONObject;

public class RowBean {

	private String _id;
	private String werks;
	private String name1;
	private String sort;
	private String sorttxt;
	private String arbpl;
	private String arbpltxt;
	private String find1;
	private String find2;
	private String find3;
	private String find4;
	private String isfind;
	private String existnum;
	private String completenum;
	private String lastexistnum;
	private String notipercentages;
	private String notiontimepercent;
	private String notifindpercent;
	private String missnum;
	private String overdue1;
	private String overdue2;
	private String overdue3;
	private String overdue4;
	private String delay1;
	private String delay2;
	private String delay3;
	private String delay4;

	public String getValue(String key) {
		if (key.equals("_id")) {
			return _id;
		} else if (key.equals("werks")) {
			return werks;
		} else if (key.equals("name1")) {
			return name1;
		} else if (key.equals("sort")) {
			return sort;
		} else if (key.equals("sorttxt")) {
			return sorttxt;
		} else if (key.equals("arbpl")) {
			return arbpl;
		} else if (key.equals("arbpltxt")) {
			return arbpltxt;
		} else if (key.equals("find1")) {
			return find1;
		} else if (key.equals("find2")) {
			return find2;
		} else if (key.equals("find3")) {
			return find3;
		} else if (key.equals("find4")) {
			return find4;
		} else if (key.equals("isfind")) {
			return isfind;
		} else if (key.equals("existnum")) {
			return existnum;
		} else if (key.equals("completenum")) {
			return completenum;
		} else if (key.equals("lastexistnum")) {
			return lastexistnum;
		} else if (key.equals("notipercentages")) {
			return notipercentages;
		} else if (key.equals("notiontimepercent")) {
			return notiontimepercent;
		} else if (key.equals("notifindpercent")) {
			return notifindpercent;
		} else if (key.equals("missnum")) {
			return missnum;
		} else if (key.equals("overdue1")) {
			return overdue1;
		} else if (key.equals("overdue2")) {
			return overdue2;
		} else if (key.equals("overdue3")) {
			return overdue3;
		} else if (key.equals("overdue4")) {
			return overdue4;
		} else if (key.equals("delay1")) {
			return delay1;
		} else if (key.equals("delay2")) {
			return delay2;
		} else if (key.equals("delay3")) {
			return delay3;
		} else if (key.equals("delay4")) {
			return delay4;
		}
		return "";
	}

	public void decoding(JSONObject obj) {
		try {
			_id = obj.getString("_id");
			werks = obj.getString("werks");
			name1 = obj.getString("name1");
			sort = obj.getString("sort");
			sorttxt = obj.getString("sorttxt");
			arbpl = obj.getString("arbpl");
			arbpltxt = obj.getString("arbpltxt");
			find1 = obj.getString("find1");
			find2 = obj.getString("find2");
			find3 = obj.getString("find3");
			find4 = obj.getString("find4");
			isfind = obj.getString("isfind");
			existnum = obj.getString("existnum");
			completenum = obj.getString("completenum");
			lastexistnum = obj.getString("lastexistnum");
			notipercentages = obj.getString("notipercentages");
			notiontimepercent = obj.getString("notiontimepercent");
			notifindpercent = obj.getString("notifindpercent");
			missnum = obj.getString("missnum");
			overdue1 = obj.getString("overdue1");
			overdue2 = obj.getString("overdue2");
			overdue3 = obj.getString("overdue3");
			overdue4 = obj.getString("overdue4");
			delay1 = obj.getString("delay1");
			delay2 = obj.getString("delay2");
			delay3 = obj.getString("delay3");
			delay4 = obj.getString("delay4");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String toString() {
		return "TongjiBean [_id=" + _id + ", werks=" + werks + ", name1="
				+ name1 + ", sort=" + sort + ", sorttxt=" + sorttxt
				+ ", arbpl=" + arbpl + ", arbpltxt=" + arbpltxt + ", find1="
				+ find1 + ", find2=" + find2 + ", find3=" + find3 + ", find4="
				+ find4 + ", isfind=" + isfind + ", existnum=" + existnum
				+ ", completenum=" + completenum + ", lastexistnum="
				+ lastexistnum + ", notipercentages=" + notipercentages
				+ ", notiontimepercent=" + notiontimepercent
				+ ", notifindpercent=" + notifindpercent + ", missnum="
				+ missnum + ", overdue1=" + overdue1 + ", overdue2=" + overdue2
				+ ", overdue3=" + overdue3 + ", overdue4=" + overdue4
				+ ", delay1=" + delay1 + ", delay2=" + delay2 + ", delay3="
				+ delay3 + ", delay4=" + delay4 + "]";
	}

}
