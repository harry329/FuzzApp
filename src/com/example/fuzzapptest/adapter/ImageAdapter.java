package com.example.fuzzapptest.adapter;

import java.util.List;

import com.example.fuzzapptest.R;
import com.example.fuzzapptest.adapter.DataAdapter.ViewHolder;
import com.example.fuzzapptest.data.DataClass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends ArrayAdapter {
	Context context;
	private List<DataClass> datalist;
	
	public ImageAdapter(Context context, int resource,List<DataClass> object) {
		super(context, resource);
		this.context= context;
		this.datalist= object;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row=convertView;
        ViewHolder holder= null;
        if(row==null) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.single_layout,parent,false);
        holder= new ViewHolder(row);
        row.setTag(holder);
       }else{
           holder= (ViewHolder) row.getTag();
       }
        DataClass mdata=datalist.get(position);
        holder.img.setImageBitmap(mdata.getImage());
        
        return row;
       }
	
	 class ViewHolder{
	        ImageView img;
	        
	        ViewHolder(View v){
	        	img= (ImageView)  v.findViewById(R.id.imageView2);
	        	
	        }
	}

}
