package com.example.fuzzapptest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuzzapptest.adapter.DataAdapter;
import com.example.fuzzapptest.arraytrans.ArrayTransfer;
import com.example.fuzzapptest.data.DataClass;
import com.example.fuzzapptest.httpconnect.HttpManager;
import com.example.fuzzapptest.jsonparser.DataJsonParser;
/*
 * 
 * Main Activity for diplaying all the data that is downloaded
 */

public class MainActivity extends ActionBarActivity {
	public static final String BASE_URL="http://quizzes.fuzzstaging.com/quizzes/mobile/1/data.json";
	List<DataClass>  datalist;
	ListView listView;
	TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView= (ListView) findViewById(R.id.listView1);
        textView=(TextView) findViewById(R.id.textView1);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
        case R.id.all:
        	 
        	  if (isOnline()) {
                  requestData("http://quizzes.fuzzstaging.com/quizzes/mobile/1/data.json");
              } else {
                  Toast.makeText(this, "no network", Toast.LENGTH_SHORT).show();
              }
        	  break;
        case R.id.text:
        	Intent intentText = new Intent(this,TextActivity.class);
        	startActivity(intentText);
        	break;
        
        case R.id.images:
        	Intent intentImage = new Intent(this,ImageActivity.class);
        	startActivity(intentImage);
        	break;
        
        }
        return super.onOptionsItemSelected(item);
    }
    protected void update(){
        DataAdapter adapter= new DataAdapter(this,R.layout.item_layout,datalist);
        listView.setAdapter(adapter);
        textView.setText("Below is the data you requested");
        
        
    }


	private void requestData(String uri) {
    	  MyTask mytask = new MyTask();
          mytask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
          
    }
   
    // Check network connectivity 
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
        }
    /*
     * 
     * BackgroundThread for downloading data
     */
     class MyTask extends AsyncTask<String, Void, List<DataClass>>{
    	
		protected List<DataClass> doInBackground(String... params)  {
             String response =HttpManager.getData(params[0]);
             datalist = DataJsonParser.parseFeed(response);
             ArrayTransfer arrayTransfer= new ArrayTransfer(datalist);
             return  datalist;
         }
		protected void onPostExecute(List<DataClass> result) {
			
            if(result != null){
            	update();
                 }
        }
    }
    
}
