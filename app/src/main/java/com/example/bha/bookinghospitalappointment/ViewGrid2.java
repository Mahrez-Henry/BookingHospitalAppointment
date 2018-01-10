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
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Mohammed_Aqrabawi on 12/17/2017.
 */

public class ViewGrid2 extends AppCompatActivity implements AdapterView.OnItemClickListener{
	ArrayList<String> dis;
	GridView gridView;
	TextView tv;
	Button feedback,gps;
	ImageButton logout,bback;
	EditText feedme;
	LinearLayout l1,l2,l3,l4,l5,l6;



	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_grid);


		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment",MODE_PRIVATE);
		tv= (TextView) findViewById(R.id.tv);
		tv.setText("* Your Booking appointment *");
		l1= (LinearLayout) findViewById(R.id.grid);
		l2= (LinearLayout) findViewById(R.id.feed);
		l3= (LinearLayout) findViewById(R.id.booking);
		l4= (LinearLayout) findViewById(R.id.nextbooking);
		l5= (LinearLayout) findViewById(R.id.viewlayout);
		l6= (LinearLayout) findViewById(R.id.viewlayout2);
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		l6.setVisibility(View.VISIBLE);
		l5.setVisibility(View.GONE);

		String[] items=new String[FullView.dis.size()];
		FullView.dis.toArray(items);
		gridView= (GridView) findViewById(R.id.view_grid2);
		gridView.setAdapter(new View_Custom_Grid_Adapter(items,this));
		gridView.setOnItemClickListener(this);
		//------------------------

		feedback= (Button) findViewById(R.id.feedback);
		gps= (Button) findViewById(R.id.gps);
		logout= (ImageButton) findViewById(R.id.logout);
		bback=(ImageButton)findViewById(R.id.bback);

		//--------------------------
		feedme= (EditText) findViewById(R.id.feedme);


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
		l5.setVisibility(View.GONE);
		l6.setVisibility(View.GONE);
		tv.setText(" * Give us Your FeedBack *");

	}
	public void backview(View v){
		setView(v);
	}

	FirebaseDatabase Firedatabase;
	DatabaseReference myRef;

	public void setDone(View v){

		EditText ET= (EditText) findViewById(R.id.feedme);
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		l5.setVisibility(View.GONE);
		l6.setVisibility(View.GONE);
		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.child("feedback").child(sp.getString("un","Null"))
				.child(sp.getInt("Inc",1)+"")
				.setValue(ET.getText().toString());
		sp.edit().putInt("Inc",sp.getInt("Inc",1)+1).apply();
		ET.setText(" ");
		tv.setText("* Choose Your Hospital *");
	}
	public void letsLogout(View v){
		Intent intent=new Intent(this,Login.class);
		startActivity(intent);
		finish();
	}

	public void setDel(View v){

		EditText ET= (EditText) findViewById(R.id.feedme);
		ET.setText(" ");
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		l5.setVisibility(View.GONE);
		l6.setVisibility(View.GONE);
		tv.setText("* Choose Your Hospital *");

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(this,HospitalGrid.class);
		startActivity(intent);
		finish();
	}


	public void setView(View v){

		Intent i1=new Intent(this,ViewGrid.class);
		startActivity(i1);
		finish();

	}
	public void letsGoBack(View view){
		Intent intent=new Intent(this,ViewGrid.class);
		startActivity(intent);
		finish();
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}
}
