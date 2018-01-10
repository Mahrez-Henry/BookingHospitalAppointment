package com.example.bha.bookinghospitalappointment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mohammed_Aqrabawi on 12/9/2017.
 */

public class BookingGrid extends AppCompatActivity {
	Button feedback,profile,gps,next,back,back2,submit;
	ImageButton logout,bback;
	SharedPreferences sp;
	EditText feedme;
	Spinner spin,time;
	String getusername;
	LinearLayout l1,l2,l3,l4;
	DatePicker DP;
	TextView tv;
	String fullname_val,gender_val,age_val,SSN_val,Description_val;
	EditText fullname,age,SSN,Description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_grid);
		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment",MODE_PRIVATE);
		tv= (TextView) findViewById(R.id.tv);
		tv.setText("* Fill Your Information *");
		l1= (LinearLayout) findViewById(R.id.grid);
		l2= (LinearLayout) findViewById(R.id.feed);
		l3= (LinearLayout) findViewById(R.id.booking);
		l4= (LinearLayout) findViewById(R.id.nextbooking);
		l4.setVisibility(View.GONE);
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.VISIBLE);

		fullname = (EditText) findViewById(R.id.fullname);
		age = (EditText) findViewById(R.id.age);
		SSN = (EditText) findViewById(R.id.SSN);
		Description = (EditText) findViewById(R.id.Description);
		bback=(ImageButton)findViewById(R.id.bback);


		//-----------------------
		spin= (Spinner) findViewById(R.id.spin);
		ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,DepartmentGrid.drname);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);

		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
			                           int pos, long id) {
				// TODO Auto-generated method stub
				getusername = (String) parent.getItemAtPosition(pos);//saving the value selected
				sp.edit().putString("und",getusername+"").apply();
				Log.i("spinvalue",getusername+"");

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				Log.i("spinvalue",getusername+"");

			}

		});


		//------------------------
		time = (Spinner) findViewById(R.id.time);
		ArrayAdapter<CharSequence> adapter2 =ArrayAdapter.createFromResource(this,
				R.array.Time,android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		time.setAdapter(adapter2);

		time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
			                           int pos, long id) {
				// TODO Auto-generated method stub

				String tt = (String) parent.getItemAtPosition(pos);
				sp.edit().putString("Time",tt).apply();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});

		//-----------------------------
		profile= (Button) findViewById(R.id.profile);

		feedback= (Button) findViewById(R.id.feedback);
		gps= (Button) findViewById(R.id.gps);
		logout= (ImageButton) findViewById(R.id.logout);
		next= (Button) findViewById(R.id.next);
		back=(Button) findViewById(R.id.back);
		back2=(Button) findViewById(R.id.back2);
		submit=(Button) findViewById(R.id.submit);
		//--------------------------
		feedme= (EditText) findViewById(R.id.feedme);
		DP = (DatePicker) findViewById(R.id.datepicker);
		DP.setMinDate(System.currentTimeMillis());

	}// Close On Create
	public void letsGoBack(View view){
		Intent intent=new Intent(this,DepartmentGrid.class);
		startActivity(intent);
		finish();
	}
	public void setNext(View view) {
		if (getDatee()) {
			if(getTimee()){
				l1.setVisibility(View.GONE);
				l2.setVisibility(View.GONE);
				l3.setVisibility(View.GONE);
				l4.setVisibility(View.VISIBLE);
			}else
				Toast.makeText(getApplicationContext(), "This time is already taken choose another time ,, Thanks :) ", Toast.LENGTH_LONG).show();
			if(sp.getInt("i",1)>4){
				eleminateDate();
			}
			// do linear 4
	}else Toast.makeText(this,"Chose correct date *Friday and Saturday* is break date :)",Toast.LENGTH_LONG).show();

	}
	public void getDate(View view){

		String date=DP.getDayOfMonth()+"-"+(DP.getMonth()+1);
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date());
		int dateofmonth = DP.getDayOfMonth();
		int month = DP.getMonth();
		int year = DP.getYear();
		cal.set(year, month,dateofmonth );

		String day =new SimpleDateFormat("EEEE").format(cal.getTime());
		Log.i("date",date+"//"+day);
		if (!(day.equals("Friday")||day.equals("Saturday"))){
			sp.edit().putString("Date", date).apply();
			sp.edit().putString("Day", day).apply();}


	}
	public void letsLogout(View v){
		Intent intent=new Intent(this,Login.class);
		startActivity(intent);
		finish();
	}

	public boolean getDatee(){
		String date=DP.getDayOfMonth()+"-"+(DP.getMonth()+1);
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date());
		int dateofmonth = DP.getDayOfMonth();
		int month = DP.getMonth();
		int year = DP.getYear();
		cal.set(year, month,dateofmonth );

		String day =new SimpleDateFormat("EEEE").format(cal.getTime());
		Log.i("date",date+"//"+day);
		if (day.equals("Friday")||day.equals("Saturday"))
			return false;
		else {
			sp.edit().putString("Date", date).apply();
			sp.edit().putString("Day", day).apply();
			return true;
		}



	}

	boolean b1 ;
	String [] times=new String[6];
	int x=0;
	public void eleminateDate(){

		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				if (dataSnapshot.child("Patient").child(sp.getString("un", "")).child("Booking")
						.hasChildren()) {
					while (x <= ((int)(dataSnapshot.child("Patient").child(sp.getString("un", ""))
							.child("Booking").getChildrenCount()))){
						times[x]= (String) dataSnapshot.child("Patient").child(sp.getString("un", ""))
								.child("Booking").child(x+"").child("date").getValue();
						Log.i("times"+x,(String) dataSnapshot.child("Patient").child(sp.getString("un", ""))
								.child("Booking").child(x+"").child("date").getValue()+((int)(dataSnapshot.child("Patient").child(sp.getString("un", ""))
								.child("Booking").getChildrenCount())));
						x++;
					}
				}

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}

	public boolean getTimee(){

		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				if (!(dataSnapshot.child("Doctor").child(sp.getString("und", "")).child("day").child(sp.getString("Day", ""))
						.child(sp.getString("Date", "")).child(sp.getString("Time", "")).exists())) {
					b1=true;

				}
				else {

					b1 = false;
					Log.i("Time", dataSnapshot.child("Doctor").child(sp.getString("und", "")).child("day").child(sp.getString("Day", ""))
							.child(sp.getString("Date", "")).child(sp.getString("Time", "")).exists() + "");

				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
		return b1;
	}


	public void Profile(View v){
		Intent intent=new Intent(this,ProfilePage.class);
		startActivity(intent);
		finish();
	}
	public void setFeedme(View v){
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.VISIBLE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		tv.setText(" * Give us Your FeedBack *");
	}
	FirebaseDatabase Firedatabase;
	DatabaseReference myRef;
	public void setDone(View v){
		EditText ET= (EditText) findViewById(R.id.feedme);
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.child("feedback").child(sp.getString("un","Null"))
				.setValue(ET.getText().toString());
		Intent i1=new Intent(this,HospitalGrid.class);
		startActivity(i1);
		finish();
	}
	public void setDel(View v){

		EditText ET= (EditText) findViewById(R.id.feedme);
		ET.setText(" ");
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.VISIBLE);
		l4.setVisibility(View.GONE);
		tv.setText("* Fill Your Information *");

	}



	public void setBack(View view){
		onBackPressed();
	}
	public void setBackTwo(View view){l4.setVisibility(View.GONE);
	l3.setVisibility(View.VISIBLE);}
	String bool;

	public boolean notNull(){
		if (fullname_val!=null&& fullname_val.length()>10)
			if(gender_val!=null)
				if (!(age_val.isEmpty()))
						if(!(SSN_val.isEmpty())&&SSN_val.length()==10)
							return true;
						else {bool=" * SSN * is missing";SSN.setError(" * Nationality ID Number * is missing");}
				else {bool=" * age * is Required and must have older than 10 years ";age.setError(" * age * is Required and must have older than 15 years ");}
			else bool=" * gender * is Requierd ";
		else {bool=" * full name * is Requierd";fullname.setError(" The * full name * is Requierd");}
		return false;
	}

	// ------------ setBooking (submit) ------------------------
	public void setBooking(View view){
		fullname_val=fullname.getText().toString();
		age_val=age.getText().toString();
		SSN_val=SSN.getText().toString();
		Description_val=Description.getText().toString();
		if(notNull()){
			if(sp.getInt("i",1)<=4) {
				PatiantInfo info = new PatiantInfo(fullname_val, gender_val, age_val, SSN_val, Description_val
						,sp.getString("und", "")
						,sp.getString("hos", "")
						,sp.getString("dep", "")
						,sp.getString("Date","")
						,sp.getString("Day","")
						,sp.getString("Time","")
						);
				PatianToDoc info2= new PatianToDoc(fullname_val, gender_val, age_val, SSN_val, Description_val
						,sp.getString("un", "")
				);

				Firedatabase = FirebaseDatabase.getInstance();
				myRef = Firedatabase.getReference();

				// patiant to doctor
				myRef.child("Doctor").child(sp.getString("und", "")).child("day")
						.child(sp.getString("Day", "")).child(sp.getString("Date","")).child(sp.getString("Time",""))
						.setValue(info2);

				// patiant info fill
				myRef.child("Patient").child(sp.getString("un", "")).child("Booking")
						.child((String.valueOf( String.valueOf(sp.getInt("i", 1)))))
						.setValue(info);

				sp.edit().putInt("i",sp.getInt("i",1)+1).apply();
				Toast.makeText(this,"Your Booking Hospital Appointment Done Successfully ",Toast.LENGTH_LONG).show();
				Intent intent=new Intent(BookingGrid.this,HospitalGrid.class);
				startActivity(intent);
				finish();
			}else {
				Calendar calendar=Calendar.getInstance();
				int day=calendar.get(Calendar.DAY_OF_MONTH),month=calendar.get(Calendar.MONTH)+1;

				int abcd =1;
				while (abcd<x) {


					Log.i("split",nsplit+"");

						res = times[abcd].split("\\-");
						resint[0] = Integer.parseInt(res[0]);
						resint[1] = Integer.parseInt(res[1]);
					Log.i("split",resint[0]+"/"+resint[1]+"**"+month+"***"+day);


					if (resint[1]>=month)
						if (resint[1]>month)
						{abcd++;continue;}
						else
								if (resint[0]<day){
									PatiantInfo info = new PatiantInfo(fullname_val, gender_val, age_val, SSN_val, Description_val
											,sp.getString("und", "")
											,sp.getString("hos", "")
											,sp.getString("dep", "")
											,sp.getString("Date","")
											,sp.getString("Day","")
											,sp.getString("Time","")
											);
									myRef.child("Patient").child(sp.getString("un", "")).child("Booking")
											.child(String.valueOf(abcd))
											.setValue(info);

									// patiant to doctor
									PatianToDoc info2= new PatianToDoc(fullname_val, gender_val, age_val, SSN_val, Description_val
											,sp.getString("un", "")
									);


									myRef.child("Doctor").child(sp.getString("und", "")).child("day")
											.child(sp.getString("Day", "")).child(sp.getString("Date","")).child(sp.getString("Time",""))
											.setValue(info2);
									Toast.makeText(this,"Your Booking Hospital Appointment Done Successfully ",Toast.LENGTH_LONG).show();
									Intent intent1=new Intent(this,HospitalGrid.class);
									startActivity(intent1);
									finish();
									break;
									}
								else {abcd++ ;sp.edit().putInt("abcd",abcd).apply(); continue;}

					else
					{
						PatiantInfo info = new PatiantInfo(fullname_val, gender_val, age_val, SSN_val, Description_val
								,sp.getString("und", "")
								,sp.getString("hos", "")
								,sp.getString("dep", "")
								,sp.getString("Date","")
								,sp.getString("Day","")
								,sp.getString("Time","")
						);
						myRef.child("Patient").child(sp.getString("un", "")).child("Booking")
								.child(String.valueOf(abcd))
								.setValue(info);
						// patiant to doctor
						PatianToDoc info2= new PatianToDoc(fullname_val, gender_val, age_val, SSN_val, Description_val
								,sp.getString("un", "")
						);

						myRef.child("Doctor").child(sp.getString("und", "")).child("day")
								.child(sp.getString("Day", "")).child(sp.getString("Date","")).child(sp.getString("Time",""))
								.setValue(info2);
						Toast.makeText(this,"Your Booking Hospital Appointment Done Successfully ",Toast.LENGTH_LONG).show();

						Intent intent=new Intent(this,HospitalGrid.class);
						startActivity(intent);
						finish();
						break;
					}

				}
				Toast.makeText(this,"You reached Bookink Hospital Appointment Limit , wait until finish your appointment",Toast.LENGTH_LONG).show();
			}
		}
	}
	String[] res=new String[2];

	int [] resint=new int[2];
	// ------------ Close setBooking (submit) ------------------------

	RadioButton RB;
	public void onRadioButtonClicked(View v){
		RB= (RadioButton) v;
		boolean checked=RB.isChecked();
		if(checked){
			gender_val=RB.getText().toString();
		}
	}

String nsplit;

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		Intent intent = new Intent(this,HospitalGrid.class);
		startActivity(intent);
		finish();
	}

	public void viewItems(){

		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				if (dataSnapshot.child("Patient").child(sp.getString("un", "")).child("Booking")
						.hasChildren()) {
					while (x <= ((int)(dataSnapshot.child("Patient").child(sp.getString("un", ""))
							.child("Booking").getChildrenCount()))){
						times[x]= (String) dataSnapshot.child("Patient").child(sp.getString("un", ""))
								.child("Booking").child(x+"").child("date").getValue();
						Log.i("times"+x,(String) dataSnapshot.child("Patient").child(sp.getString("un", ""))
								.child("Booking").child(x+"").child("date").getValue()+((int)(dataSnapshot.child("Patient").child(sp.getString("un", ""))
								.child("Booking").getChildrenCount())));
						x++;
					}
				}

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}

}
