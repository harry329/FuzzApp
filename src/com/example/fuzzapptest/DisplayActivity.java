package com.example.fuzzapptest;

import java.util.List;

import com.example.fuzzapptest.arraytrans.ArrayTransfer;
import com.example.fuzzapptest.data.DataClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/* 
  Activity for displaying image on full screen
*/

public class DisplayActivity extends Activity {
		List<DataClass> listData;
		ImageView imageView;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_layout);
        imageView= (ImageView) findViewById(R.id.imageView1);
        listData= ArrayTransfer.getList();
        Intent intent = getIntent();
        if(intent!= null){
        	String str= intent.getStringExtra("Name");
        	for(int i=0;i<listData.size();i++){
        		DataClass dataClass= listData.get(i);
        		if(str.equals(dataClass.getId())){
        			imageView.setImageBitmap(dataClass.getImage());
        		}
        	}
        }
	}
}
