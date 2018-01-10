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

public class Doctor_Patient extends AppCompatActivity {
	SharedPreferences sp;
	GridView gridView2;
	LinearLayout l1,l2;
	Button bback;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_grid);
		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment",MODE_PRIVATE);
		l1= (LinearLayout) findViewById(R.id.l1);
		l2= (LinearLayout) findViewById(R.id.l2);
		l2.setVisibility(View.VISIBLE);
		l1.setVisibility(View.GONE);
		String[] item=new String[FullView.info.size()];
		FullView.info.toArray(item);
		gridView2= (GridView) findViewById(R.id.gridinfo);
		gridView2.setAdapter(new View_Custom_Grid_Adapter(item,this));
		bback= (Button) findViewById(R.id.bback);


	}
	public void letsGoBack(View view){
		Intent intent= new Intent(this,Doctor_Times.class);
		startActivity(intent);
		finish();
	}
	public void back(View v){
		Intent intent=new Intent(this,Doctor_Times.class);
		startActivity(intent);
		finish();
	}



	@Override
	public void onBackPressed() {
		super.onBackPressed();
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
	}
}
