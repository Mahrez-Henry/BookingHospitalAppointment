package com.example.bha.bookinghospitalappointment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Mohammed_Aqrabawi on 12/17/2017.
 */

public class Doctor_Times extends AppCompatActivity implements AdapterView.OnItemClickListener {

	SharedPreferences sp;
	GridView gridView;
	String day,date,un;
	String[] items;
	LinearLayout l1,l2;
	Button bback;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_grid);
		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment",MODE_PRIVATE);
		l1= (LinearLayout) findViewById(R.id.l1);
		l2= (LinearLayout) findViewById(R.id.l2);
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		 items=new String[FullView.times.size()];
		FullView.times.toArray(items);
		gridView= (GridView) findViewById(R.id.gridtimes);
		gridView.setAdapter(new View_Custom_Grid_Adapter(items,this));
		gridView.setOnItemClickListener(this);
		date=sp.getString("ddd","");
		day=sp.getString("nnn","");
		un=sp.getString("un","");
		bback= (Button) findViewById(R.id.bback);

	}
	FullView fv=new FullView();
	public void letsGoBack(View view){
		Intent intent= new Intent(this,Doctor_Choose.class);
		startActivity(intent);
		finish();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
	        case 0:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {
			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
	        	break;
	        case 1:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {
			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
		        break;
	        case 2:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {
			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
		        break;
	        case 3:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {
			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
		        break;
	        case 4:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {
			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
		        break;
	        case 5:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {
			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
		        break;
	        case 6:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {
			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
		        break;
	        case 7:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {

			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
		        break;
	        case 8:
		        if(fv.isExist2(sp.getString("un",""),day+"",date+"",items[position]+"")) {

			        Intent i1 = new Intent(this, Doctor_Patient.class);
			        startActivity(i1);
			        finish();
		        }
		        break;
        }
	}

}
