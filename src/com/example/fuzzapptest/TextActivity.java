package com.example.fuzzapptest;

import java.util.ArrayList;

import java.util.List;

import com.example.fuzzapptest.arraytrans.ArrayTransfer;
import com.example.fuzzapptest.data.DataClass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/*
 * Activity for diplaying text only
 */
public class TextActivity extends ActionBarActivity implements OnItemClickListener  {

	private List<DataClass>  datalist;
	private List<String>  textlist;
	private ListView listView;
	 
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.text_layout);
	        listView= (ListView) findViewById(R.id.listView1);
	        textlist= new ArrayList<String>();
	    }

	
	protected void onStart() {
		super.onStart();
		datalist=ArrayTransfer.getList();
		if(datalist==null){
		Toast.makeText(getApplicationContext(), "Please select all first", Toast.LENGTH_LONG).show();
			return;
		}
		textList();
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                textlist );
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(this);
		
	}
	   public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	 public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        switch(id){
	        case R.id.all:
	        	Intent intent1= new Intent(this,MainActivity.class);
	        	startActivity(intent1);
	        	  break;
	        case R.id.text:
	        	Intent intent = new Intent(this,TextActivity.class);
	        	startActivity(intent);
	        case R.id.images:
	        	Intent intentImage = new Intent(this,ImageActivity.class);
	        	startActivity(intentImage);
	        }
	        return super.onOptionsItemSelected(item);
	    }
	private void textList() {
		for (int i =0; i<datalist.size();i++){
			DataClass dataClass= datalist.get(i);
			if(dataClass.isValidURL()){
				continue;
			}
			else if(dataClass.getData().equals("")){
				continue;
			}
			else{
				textlist.add(dataClass.getData());
			}
		}
		
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://fuzzproductions.com/"));
	    String title = getResources().getString(R.string.chooser_title);
	    Intent chooser = Intent.createChooser(intent, title);
	        if (intent.resolveActivity(getPackageManager()) != null) {
	            startActivity(chooser);
	        }
		
	}
	 
}
