package com.example.fuzzapptest.data;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.support.v4.os.ParcelableCompat;

public class DataClass implements Serializable  {
	
	private String id;
	private String type;
	private String date;
	private String data;
	private boolean validURL;
	private Bitmap image;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isValidURL() {
		return validURL;
	}
	public void setValidURL(boolean validURL) {
		this.validURL = validURL;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}

	
	

}
