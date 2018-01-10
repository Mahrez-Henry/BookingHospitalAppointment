package com.example.bha.bookinghospitalappointment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HospitalGrid extends AppCompatActivity implements AdapterView.OnItemClickListener{

	GridView gridView;
	TextView tv;
	Button feedback,profile,gps,done;
	ImageButton logout,bback;
	EditText feedme;
	LinearLayout l1,l2,l3,l4,l5,l6;
	static String[] hos_name = {"مستشفى الجامعة الاردنية","المستشفى التخصصي","مستشفى الاردن","مستشفى الاسراء","مستشفى الرويال"};
	static int hos_images[]= {R.drawable.uj,R.drawable.special,R.drawable.jordan,R.drawable.israa,R.drawable.royal};

SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_grid);

		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment",MODE_PRIVATE);
		tv= (TextView) findViewById(R.id.tv);
		tv.setText("* Chose Hospital *");
		sp.edit().putInt("inc",1).apply();
		gridView= (GridView) findViewById(R.id.hos_grid);
		gridView.setAdapter(new Hospital_Custom_Grid_Adapter(hos_name,hos_images,this));
		gridView.setOnItemClickListener(this);
		//------------------------
		l1= (LinearLayout) findViewById(R.id.grid);
		l2= (LinearLayout) findViewById(R.id.feed);
		l3= (LinearLayout) findViewById(R.id.booking);
		l4= (LinearLayout) findViewById(R.id.nextbooking);
		l5= (LinearLayout) findViewById(R.id.viewlayout);
		l6= (LinearLayout) findViewById(R.id.viewlayout2);
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		l5.setVisibility(View.GONE);
		l6.setVisibility(View.GONE);
		//profile= (Button) findViewById(R.id.profile);
//		profile.setOnClickListener((View.OnClickListener) this);

		feedback= (Button) findViewById(R.id.feedback);
		gps= (Button) findViewById(R.id.gps);
		logout= (ImageButton) findViewById(R.id.logout);
		bback=(ImageButton)findViewById(R.id.bback);
		//--------------------------
		feedme= (EditText) findViewById(R.id.feedme);

		fv.viewItems(sp.getString("un",""));
		fv.profileFromFirebase(sp.getString("type","Patient"),sp.getString("un",""));
		sp.edit().putInt("i",fv.bookCount).apply();
	}
	FullView fv = new FullView();
	public void letsGoBack(View view){
		letsLogout(view);
		finish();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
			case (0):
				sp.edit().putString("hos",hos_name[position]).apply();
				Intent intent = new Intent (HospitalGrid.this,DepartmentGrid.class);
				startActivity(intent);
				break;
			case (1):
				sp.edit().putString("hos",hos_name[position]).apply();
				Intent intent1 = new Intent (HospitalGrid.this,DepartmentGrid.class);
				startActivity(intent1);
				break;
			case (2):
				sp.edit().putString("hos",hos_name[position]).apply();
				Intent intent2 = new Intent (HospitalGrid.this,DepartmentGrid.class);
				startActivity(intent2);
				break;
			case (3):
				sp.edit().putString("hos",hos_name[position]).apply();
				Intent intent3 = new Intent (HospitalGrid.this,DepartmentGrid.class);
				startActivity(intent3);break;
			case (4):
				sp.edit().putString("hos",hos_name[position]).apply();
				Intent intent4 = new Intent (HospitalGrid.this,DepartmentGrid.class);
				startActivity(intent4);break;
			case (5):
				sp.edit().putString("hos",hos_name[position]).apply();
				Intent intent5 = new Intent (HospitalGrid.this,DepartmentGrid.class);
				startActivity(intent5);break;
		}
	}//close on click items for hospital
	public void Profile(View v){
		Intent intent=new Intent(this,ProfilePage.class);
		startActivity(intent);
	}
	public void letsLogout(View v){
		Intent intent=new Intent(this,Login.class);
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
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.child("feedback").child(sp.getString("un","Null"))
				.child(sp.getInt("Inc",1)+"")
				.setValue(ET.getText().toString());
		sp.edit().putInt("Inc",sp.getInt("Inc",1)+1).apply();
		ET.setText(" ");
		tv.setText("* Chose Your Hospital *");
	}

	public void setDel(View v){

		EditText ET= (EditText) findViewById(R.id.feedme);
		ET.setText(" ");
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		l4.setVisibility(View.GONE);
		tv.setText("* Chose Your Hospital *");

	}
	public void setView(View v){
		Intent i1=new Intent(this,ViewGrid.class);
		startActivity(i1);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}


}//close class

