package com.example.dotable;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.content.Context;

public class Utils {

	public static String getFromAssets(Context context,String fileName) {
		String result = null;
		try {
			InputStreamReader inputReader = new InputStreamReader(
					context.getResources().getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			result = "";
			while ((line = bufReader.readLine()) != null)
				result += line;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
