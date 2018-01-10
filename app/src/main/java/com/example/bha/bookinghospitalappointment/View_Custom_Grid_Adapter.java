package com.example.bha.bookinghospitalappointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class View_Custom_Grid_Adapter extends BaseAdapter {

	Context context;

	static String items_text[];

	public View_Custom_Grid_Adapter(String[] items, Context c) {
		context = c;
		this.items_text = items;
	}

	@Override
	public int getCount() {
		return items_text.length ;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View g;
		LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView==null){
			g=new View(context);
			g= layoutInflater.inflate(R.layout.view_items,null);
			TextView textView= (TextView) g.findViewById(R.id.text_grid);
			textView.setText(items_text[position]);


		} else {
			g= convertView;
		}
		return g;
	}

}
