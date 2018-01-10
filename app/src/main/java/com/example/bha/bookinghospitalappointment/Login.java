package com.example.bha.bookinghospitalappointment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener{

	Button login,reg;
	EditText username,password;
	FirebaseDatabase Firedatabase;
	DatabaseReference myRef;
	int access;
	SharedPreferences pref ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		login= (Button) findViewById(R.id.Login);
		login.setOnClickListener(this);

		pref=this.getSharedPreferences("com.example.bha.bookinghospitalappointment", Context.MODE_PRIVATE);

		reg= (Button) findViewById(R.id.reg);
		reg.setOnClickListener(this);

		username = (EditText) findViewById(R.id.username);
		password= (EditText) findViewById(R.id.password);

	}
	String un,pass;
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.Login:
				 un=username.getText().toString();
				pass=password.getText().toString();
				userChecking ();
				/*if (userChecking ()==1){

				}
				else if(userChecking ()==2){

				}*/


				break;

			case R.id.reg:
				Intent intent=new Intent(Login.this,Register.class);
				startActivity(intent);
				break;

		}

	}
	FullView fv=new FullView();
	public void userChecking (){

		Firedatabase = FirebaseDatabase.getInstance();

		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				if( (!(un.isEmpty())) && dataSnapshot.child("Doctor").child(un).exists()){
					Log.i("FoundUser", "Yes");
					if(dataSnapshot.child("Doctor")
							.child(username.getText().toString())
							.child("password").getValue().toString()
							.equals(pass)){
						access=1;
						Log.i("passwordDoc", "Yes");
						String name=dataSnapshot.child("Doctor").child(username.getText().toString()).child("fullname").getValue().toString();
						pref.edit().putString("name",name).apply();
						pref.edit().putString("un",un).apply();
						pref.edit().putString("type","Doctor").apply();
						fv.bookingCount(un);
					Intent intent=new Intent(Login.this,Doctor_Choose.class);
					startActivity(intent);
					finish();}
					else {Toast.makeText(Login.this,"The UserName Or Password is Wrong.",Toast.LENGTH_LONG).show();
						return;}

				}else{
					//************ no record for user on Firebase we
					//             will create new one using local info
					//                                                      ************//

					if((!(un.isEmpty())) && dataSnapshot.child("Patient").child(un).exists()){
						Log.i("FoundUserPatient", "Yes");
						if(dataSnapshot.child("Patient")
								.child(username.getText().toString())
								.child("password").getValue().toString()
								.equals(pass)
						){
							access=2;

							String fname= String.valueOf(dataSnapshot.child("Patient")
									.child(username.getText().toString()).child("fullname").getValue());

							pref.edit().putString("name",fname).apply();
							pref.edit().putString("un",un).apply();
							pref.edit().putString("type","Patient").apply();
							fv.bookingCount(un);


							Intent intent1=new Intent(Login.this,HospitalGrid.class);
							startActivity(intent1);
							finish();
							Toast.makeText(Login.this,
									"Welcome "+fname+" We Wish You have a good health",
									Toast.LENGTH_LONG).show();
									return;
						}

						else{
							Toast.makeText(Login.this,"The UserName Or Password is Wrong.",Toast.LENGTH_LONG).show();}
					}else {
						access=0;
					Log.i("NotFoundUser", "no");
					Toast.makeText(Login.this,"Not Member yet !! Register now By Clicking on 'Register'.",Toast.LENGTH_LONG).show();}
					/*info = new Info(DB.queryalph(personID),DB.query(personID));
					Firedatabase = FirebaseDatabase.getInstance();
					myRef = Firedatabase.getReference();
					myRef.child("Users").child(Home.personID).setValue(info);*/
				}

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}

	@Override
	public void onBackPressed() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
