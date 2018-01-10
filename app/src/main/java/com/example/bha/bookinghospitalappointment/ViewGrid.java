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

public class ViewGrid extends AppCompatActivity implements AdapterView.OnItemClickListener{
	ArrayList<String> dis;
	GridView gridView,gridView2;
	TextView tv;
	Button feedback,gps;
	ImageButton logout,bback;
	EditText feedme;
	LinearLayout l1,l2,l3,l4,l5,l6;
	ListView List;


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
		l6.setVisibility(View.GONE);
		l5.setVisibility(View.VISIBLE);
		String[] items=new String[FullView.itmes.size()];
		FullView.itmes.toArray(items);
		gridView= (GridView) findViewById(R.id.view_grid);
		gridView.setAdapter(new View_Custom_Grid_Adapter(items,this));
		gridView.setOnItemClickListener(this);
		//------------------------

		bback=(ImageButton)findViewById(R.id.bback);

		feedback= (Button) findViewById(R.id.feedback);
		gps= (Button) findViewById(R.id.gps);
		logout= (ImageButton) findViewById(R.id.logout);

		//--------------------------
		feedme= (EditText) findViewById(R.id.feedme);

	}
	FullView fv=new FullView();
	public void letsGoBack(View view){
		Intent intent=new Intent(this,HospitalGrid.class);
		startActivity(intent);
		finish();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
			case (0):
				sp.edit().putString("vpb",(position+1)+"").apply();


					Log.i("tag",fv.isExist(sp.getString("un",""),(position+1)+"")+"");
					fv.viewDis(sp.getString("un",""),(position+1)+"");

					Intent i1=new Intent(this,ViewGrid2.class);
					startActivity(i1);


				break;
			case (1):
				sp.edit().putString("vpb",(position+1)+"").apply();

					Log.i("tag",fv.isExist(sp.getString("un",""),(position+1)+"")+"");
					fv.viewDis(sp.getString("un",""),(position+1)+"");

					Intent i2=new Intent(this,ViewGrid2.class);
					startActivity(i2);


				break;
			case (2):
				sp.edit().putString("vpb",(position+1)+"").apply();

					Log.i("tag",fv.isExist(sp.getString("un",""),(position+1)+"")+"");
					fv.viewDis(sp.getString("un",""),(position+1)+"");

					Intent i3=new Intent(this,ViewGrid2.class);
					startActivity(i3);


				break;
			case (3):
				sp.edit().putString("vpb",(position+1)+"").apply();

					Log.i("tag",fv.isExist(sp.getString("un",""),(position+1)+"")+"");
					fv.viewDis(sp.getString("un",""),(position+1)+"");

					Intent i4=new Intent(this,ViewGrid2.class);
					startActivity(i4);


				break;
		}
	}//close on click items for hospital
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
	public void letsLogout(View v){
		Intent intent=new Intent(this,Login.class);
		startActivity(intent);
		finish();
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

	public void setDel(View v){

		EditText ET= (EditText) findViewById(R.id.feedme);
		ET.setText(" ");
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		l5.setVisibility(View.VISIBLE);
		l6.setVisibility(View.GONE);
		tv.setText("* Your Booking appointment *");

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent = new Intent(this,HospitalGrid.class);
		startActivity(intent);
		finish();
	}


	public void setView(View v){
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		l6.setVisibility(View.INVISIBLE);
		l5.setVisibility(View.VISIBLE);
		Intent i1=new Intent(this,ViewGrid.class);
		startActivity(i1);

	}


}
