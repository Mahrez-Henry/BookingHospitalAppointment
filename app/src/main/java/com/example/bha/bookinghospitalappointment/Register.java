package com.example.bha.bookinghospitalappointment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Register extends AppCompatActivity implements View.OnClickListener {
	Button submit;
	ImageButton hint;
	RadioButton RB;
	String fullname_val,gender_val,age_val,e_mail_val,phone_val,username_val,password_val;
	EditText fullname,age,e_mail,phone,username,password;
	FirebaseDatabase Firedatabase;
	DatabaseReference myRef;

	SharedPreferences sp;
	FullView fv=new FullView();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment", Context.MODE_PRIVATE);

		fullname = (EditText) findViewById(R.id.fullname);
		age = (EditText) findViewById(R.id.age);
		e_mail = (EditText) findViewById(R.id.email);
		phone = (EditText) findViewById(R.id.phone);

		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		hint = (ImageButton) findViewById(R.id.eye);
		hint.setOnClickListener(this);

		submit = (Button) findViewById(R.id.register);
		submit.setOnClickListener(this);

	}
	int vv=2;
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.eye:
				if (vv%2==0){
				password.setInputType(InputType.TYPE_CLASS_TEXT);
				vv++;}
				else {password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);vv=2;}
				break;
			case R.id.register:

				fullname_val=fullname.getText().toString();
				age_val=age.getText().toString();
				e_mail_val=e_mail.getText().toString();
				phone_val=phone.getText().toString();
				username_val=username.getText().toString();
				password_val=password.getText().toString();



				if(notNull()) {

					if (finduser()) {
						Log.i("NotFoundUser", "no");
						Info info;
						info = new Info(fullname_val, gender_val, age_val, e_mail_val, phone_val, password_val);
						Firedatabase = FirebaseDatabase.getInstance();
						myRef = Firedatabase.getReference();
						myRef.child("Patient").child(username_val).setValue(info);

						sp.edit().putString("fname",fullname_val).apply();
						sp.edit().putString("un",username_val).apply();

						fv.bookingCount(username_val);
						Intent intent1=new Intent(Register.this,HospitalGrid.class);
						startActivity(intent1);
						finish();


					} else {Toast.makeText(this,"UserName is Already Exist",Toast.LENGTH_LONG).show();}


				}else{
				Toast.makeText(this, bool + ".", Toast.LENGTH_LONG).show();}
		}
	}
	public void onRadioButtonClicked(View v){
		RB= (RadioButton) v;
		boolean checked=RB.isChecked();
		if(checked){
			gender_val=RB.getText().toString();
		}
	}
	String bool;
	public boolean notNull(){
		if (fullname_val!=null&& fullname_val.length()>10)
			if(gender_val!=null)
				if (!(age_val.isEmpty()))
					if(Integer.parseInt(age_val)>10&&age_val.length()>1&&age_val.length()<3)
						if((!(e_mail_val.isEmpty()))&& e_mail_val.contains("@"))
							if ((!(phone_val.isEmpty()))&&phone_val.length()>=9)
								if (!(username_val.isEmpty()))
									if((!(password_val.isEmpty()))&&password_val.length()>=6)
										return true;
									else {bool=" * password * must contain a minimum of 6+ characters! ";password.setError( "password must contain at minimum of 6+ characters!" ); }
								else {bool=" * username * Requierd ";password.setError( "* username * Requierd" ); }
						    else {bool=" * phone * must contain a minimum of 9+ digits!";phone.setError(" phone must contain a minimum of 9+ digits!");}
						else {bool=" * email * is missing";e_mail.setError(" * email * is missing");}
					else {bool=" * age * must have older than 15 years ";age.setError(" * age * must have older than 15 years ");}
				else {bool=" * age * is Required and must have older than 10 years ";age.setError(" * age * is Required and must have older than 15 years ");}
			else bool=" * gender * is Requierd ";
		else {bool=" * full name * is Requierd";fullname.setError(" The * full name * is Requierd");}
	return false;
	}
boolean x;
	public  boolean finduser(){
		Firedatabase = FirebaseDatabase.getInstance();

		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if(dataSnapshot.child("Doctor").child(username_val).exists()){
					Log.i("DoctorFoundUser", "Yes");

					x=false;

				}
				else if(dataSnapshot.child("Patient").child(username_val).exists()){
					Log.i("PatientFoundUser", "Yes");
					x=false;

				}else{Log.i("PatientNotFoundUser", "Yes");
					x   = true;}

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

				Log.i("DatabaseError", "Yes"+x);

			}
		});

		Log.i("PatientNotFoundUser", "Yes"+x);
		return x;

	}
}
