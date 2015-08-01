package com.example.fuzzapptest.jsonparser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Patterns;
import android.webkit.URLUtil;

import com.example.fuzzapptest.data.DataClass;

public class DataJsonParser {
	public static List<DataClass> parseFeed(String content){
        try{
        JSONArray ar = new JSONArray(content);
        List<DataClass> list = new ArrayList<DataClass>();
        for(int i=0; i<ar.length(); i++){
            DataClass mdata = new DataClass();
            JSONObject obj = ar.getJSONObject(i);
            mdata.setId(obj.getString("id"));
            mdata.setType(obj.getString("type"));
            if(obj.has("date")){
            mdata.setDate(obj.getString("date"));}
            else
            {
            	mdata.setData("No date available");
            }
            if(obj.has("data")){
            	mdata.setData(obj.getString("data"));
            	if(checkURL(mdata.getData())){
            		mdata.setValidURL(true);
            	}
            	else
            	{
            		mdata.setValidURL(false);
            	}		
            }
            else
            {
            	mdata.setData("No data is available");
            	mdata.setValidURL(false);
            }
            
            list.add(mdata);
        }

        return list;
        }   catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
	public static boolean checkURL(CharSequence input) {
	    if (TextUtils.isEmpty(input)) {
	        return false;
	    }
	    Pattern URL_PATTERN = Patterns.WEB_URL;
	    boolean isURL = URL_PATTERN.matcher(input).matches();
	    return isURL;
	}
}
