package com.example.fuzzapptest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.fuzzapptest.adapter.DataAdapter;
import com.example.fuzzapptest.adapter.ImageAdapter;
import com.example.fuzzapptest.arraytrans.ArrayTransfer;
import com.example.fuzzapptest.data.DataClass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/* 
Activity for downloading images
*/

public class ImageActivity extends ActionBarActivity implements OnItemClickListener  {
	GridView grid;
	List<DataClass>  datalist;
	List<DataClass>  textlist;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        grid= (GridView) findViewById(R.id.gridView);
        textlist= new ArrayList<DataClass>();
}
	
	@SuppressWarnings("unchecked")
	protected void onStart() {
		super.onStart();
		datalist=ArrayTransfer.getList();
		if(datalist==null){
		Toast.makeText(getApplicationContext(), "Please select all first", Toast.LENGTH_LONG).show();
			return;
		}
		textList();
		Toast.makeText(getApplicationContext(), "Please wait!Images are downloading", Toast.LENGTH_LONG).show();
		ImageLoader imageLoader= new ImageLoader();
		imageLoader.execute(textlist);
		ImageAdapter adapter= new ImageAdapter(this,R.layout.item_layout,textlist);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
		
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this,DisplayActivity.class);
		intent.putExtra("Name", textlist.get(position).getId());
		startActivity(intent);
	    
		
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
        	break;
        case R.id.images:
        	Intent intentImage = new Intent(this,ImageActivity.class);
        	startActivity(intentImage);
        	break;
        }
        return super.onOptionsItemSelected(item);
    }
	private void textList() {
		for (int i =0; i<datalist.size();i++){
			DataClass dataClass= datalist.get(i);
			if(dataClass.isValidURL()){
				textlist.add(dataClass);
			}
			else{
				continue;
			}
		}
	 }
	
	/*
	 * BackgroundThread for downloading image
	 * 
	 */
	class ImageLoader extends AsyncTask<List<DataClass>,Void,Void>{

		@Override
		protected Void doInBackground(List<DataClass>... params) {
			List<DataClass> listData=params[0];
			for(int i =0; i< listData.size();i++){
				DataClass dataClass= listData.get(i);
     			String url= dataClass.getData();
				InputStream is;
				try {
					is = (InputStream) new URL(url).getContent();
					Bitmap bitmap = BitmapFactory.decodeStream(is);
					Bitmap bitmap1=Bitmap.createScaledBitmap(bitmap, 10, 10, true);
					dataClass.setImage(bitmap1);
					is.close();
				} 
			  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
			}
			return null;
			
		}
		
	}
}