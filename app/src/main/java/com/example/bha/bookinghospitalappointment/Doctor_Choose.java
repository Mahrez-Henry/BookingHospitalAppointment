package com.example.bha.bookinghospitalappointment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Doctor_Choose extends AppCompatActivity implements View.OnClickListener {
	ImageButton doc,pat;
	TextView welcome;

	String date;
	SharedPreferences sp;
	String dayname;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_chose);
		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment",MODE_PRIVATE);

		welcome= (TextView) findViewById(R.id.welcome_doctor);
		welcome.setText("Welcome Back Doctor "+sp.getString("name",""));
		doc= (ImageButton) findViewById(R.id.Doctor);
		doc.setOnClickListener(this);
		pat= (ImageButton) findViewById(R.id.Patiant);
		pat.setOnClickListener(this);

		Calendar calendar=Calendar.getInstance();
		int day=calendar.get(Calendar.DAY_OF_MONTH),month=calendar.get(Calendar.MONTH)+1;
		// daynamee = calendar.get(Calendar.DAY_OF_WEEK);
		 dayname =new SimpleDateFormat("EEEE").format(calendar.getTime());
		 date=day+"-"+month;
		 sp.edit().putString("ddd",date).apply();
		 sp.edit().putString("nnn",dayname+"").apply();
	}

FullView fv=new FullView();
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.Doctor:


				fv.viewDoctorItems(sp.getString("un",""),dayname+"",date+"");
				Intent i1=new Intent(this,Doctor_Times.class);
				startActivity(i1);
				finish();
				break;
			case R.id.Patiant:
				Intent intent = new Intent(Doctor_Choose.this,HospitalGrid.class);
				startActivity(intent);
				finish();
				break;
		}
	}
}
