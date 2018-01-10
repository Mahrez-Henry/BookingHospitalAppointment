package com.example.bha.bookinghospitalappointment;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mohammed_Aqrabawi on 11/2/2017.
 */

public class UserCheck {
	FirebaseDatabase Firedatabase;
	DatabaseReference myRef;
	boolean x;
	String name;
	public  boolean finduser(final String un){
		Firedatabase = FirebaseDatabase.getInstance();

		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
		@Override
		public void onDataChange(DataSnapshot dataSnapshot) {

			if(dataSnapshot.child("Doctor").child(un).exists()){
				Log.i("DoctorFoundUser", "Yes");
				x=true;
			}
			else if(dataSnapshot.child("Patient").child(un).exists()){
						Log.i("PatientFoundUser", "Yes");
						x=true;
					}else{
						x=false;}

		}


		@Override
		public void onCancelled(DatabaseError databaseError) {



		}
	});
return x;
}
}
