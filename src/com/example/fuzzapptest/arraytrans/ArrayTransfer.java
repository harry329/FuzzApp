package com.example.fuzzapptest.arraytrans;

import java.util.List;

import com.example.fuzzapptest.data.DataClass;

public class ArrayTransfer {
	private static List<DataClass> datalist=null;
	
	public ArrayTransfer(List<DataClass> datalist){
		this.datalist=datalist;
	}
	
	public static List<DataClass> getList(){
		return datalist;
	}

}
