package com.example.fuzzapptest.adapter;

import java.util.List;

import com.example.fuzzapptest.R;
import com.example.fuzzapptest.data.DataClass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class DataAdapter extends ArrayAdapter<DataClass> {

	private Context context;
	private List<DataClass> datalist;
	
	
	public DataAdapter(Context context, int resource,List<DataClass> object) {
		super(context, resource,object);
		this.context= context;
		this.datalist= object;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View row=convertView;
        ViewHolder holder= null;
        if(row==null) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.item_layout,parent,false);
        holder= new ViewHolder(row);
        row.setTag(holder);
       }else{
           holder= (ViewHolder) row.getTag();
       }
        DataClass mdata=datalist.get(position);
        holder.tv1.setText("Id of item is- "+ mdata.getId());
        holder.tv2.setText("Type of item is- "+ mdata.getType());
        holder.tv3.setText("Date of item is- "+ mdata.getDate());
        holder.tv4.setText("Data is- "+ mdata.getData());
        return row;
       }
	
	 class ViewHolder{
	        TextView tv1;
	        TextView tv2;
	        TextView tv3;
	        TextView tv4;
	        ViewHolder(View v){
	        	tv1= (TextView)  v.findViewById(R.id.textView1);
	        	tv2= (TextView)  v.findViewById(R.id.textView2);
	        	tv3= (TextView)  v.findViewById(R.id.textView3);
	        	tv4= (TextView)  v.findViewById(R.id.textView4);
	        }
	    }
	
	}
