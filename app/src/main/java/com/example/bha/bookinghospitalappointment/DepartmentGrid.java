package com.example.bha.bookinghospitalappointment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DepartmentGrid extends AppCompatActivity implements AdapterView.OnItemClickListener{
		GridView gridView;
	Button feedback,profile,gps;
	ImageButton logout,bback;
	static String[] dep_name = {"قسم الاطفال","قسم انف اذن حنجرة","قسم التوليد","قسم القلب","قسم العظام"};
	static int dep_images[]= {R.drawable.children,R.drawable.hear,R.drawable.pragnent,R.drawable.heart,R.drawable.arm};
	static ArrayList<String> drname = new ArrayList<String>();
	SharedPreferences sp;
	EditText feedme;
	Spinner spin;
	String getusername;
	LinearLayout l1,l2,l3,l4;
	TextView tv;
	@Override
protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_grid);
		tv= (TextView) findViewById(R.id.tv);
		tv.setText("* Chose Department *");
		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment",MODE_PRIVATE);
		drname.clear();
		gridView= (GridView) findViewById(R.id.hos_grid);
		gridView.setAdapter(new Hospital_Custom_Grid_Adapter(dep_name,dep_images,this));
		gridView.setOnItemClickListener(this);

	l1= (LinearLayout) findViewById(R.id.grid);
	l2= (LinearLayout) findViewById(R.id.feed);
	l3= (LinearLayout) findViewById(R.id.booking);
		l4= (LinearLayout) findViewById(R.id.nextbooking);
		l4.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);

		//-----------------------
		spin= (Spinner) findViewById(R.id.spin);
		ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,drname);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);



		//------------------------



		//-------------------------------

		profile= (Button) findViewById(R.id.profile);
		bback=(ImageButton)findViewById(R.id.bback);

		feedback= (Button) findViewById(R.id.feedback);
		gps= (Button) findViewById(R.id.gps);
		logout= (ImageButton) findViewById(R.id.logout);

		//--------------------------
		feedme= (EditText) findViewById(R.id.feedme);


		}
	public void letsGoBack(View view){
		Intent intent=new Intent(this,HospitalGrid.class);
		startActivity(intent);
		finish();
	}
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case (0):
			sp.edit().putString("dep",dep_name[position]).apply();
			callData(sp.getString("hos",""),sp.getString("dep",""));

			fillspin();
			Intent intent=new Intent(this,BookingGrid.class);
			startActivity(intent);

			break;
		case (1):
			sp.edit().putString("dep",dep_name[position]).apply();
			callData(sp.getString("hos",""),sp.getString("dep",""));

			fillspin();

			break;
		case (2):
			sp.edit().putString("dep",dep_name[position]).apply();
			callData(sp.getString("hos",""),sp.getString("dep",""));

			fillspin();

			break;
		case (3):
			sp.edit().putString("dep",dep_name[position]).apply();
			callData(sp.getString("hos",""),sp.getString("dep",""));

			fillspin();

			break;
		case (4):
			sp.edit().putString("dep",dep_name[position]).apply();
			callData(sp.getString("hos",""),sp.getString("dep",""));

			fillspin();

			break;
		}
		}
	public void Profile(View v){
		Intent intent=new Intent(this,ProfilePage.class);
		startActivity(intent);
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
		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.child("feedback").child(sp.getString("un","Null"))
				.child(sp.getInt("Inc",1)+"")
				.setValue(ET.getText().toString());
		sp.edit().putInt("Inc",sp.getInt("Inc",1)+1).apply();
		ET.setText(" ");
		Intent intent=new Intent(this,HospitalGrid.class);
		startActivity(intent);
		finish();
				}
	public void setView(View v){
		Intent i1=new Intent(this,ViewGrid.class);
		startActivity(i1);
	}

	public void setDel(View v){
		EditText ET= (EditText) findViewById(R.id.feedme);
		ET.setText(" ");
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		tv.setText("* Chose Your Department *");
	}
	public void letsLogout(View v){
		Intent intent=new Intent(this,Login.class);
		startActivity(intent);
		finish();
	}

	public void callData(final String hos, final String dep){

		Firedatabase = FirebaseDatabase.getInstance();


		myRef = Firedatabase.getReference();


		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot postSnapshot: dataSnapshot.child("hospital").child(hos).child(dep).getChildren()) {
						drname.add((String) postSnapshot.getValue());
					}
				}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});

	}

	public void fillspin(){
		ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,drname);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
			                           int pos, long id) {
				// TODO Auto-generated method stub
				getusername = (String) parent.getItemAtPosition(pos);//saving the value selected
				Log.i("spinvalue1",getusername+"");

			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				Log.i("spinvalue1",getusername+"");
			}
		});
	}
}