package com.example.bha.bookinghospitalappointment;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mohammed_Aqrabawi on 12/13/2017.
 */

public class CheckUsed {

	public String nsplit;

	public String checkdate(final int abcd, final String un){
		FirebaseDatabase Firedatabase;
		DatabaseReference myRef;
		Firedatabase = FirebaseDatabase.getInstance();
		myRef=Firedatabase.getReference();
		myRef.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				if (dataSnapshot.exists()) {
					nsplit=dataSnapshot.child("Patient").child(un)
							.child("Booking")
							.child(String.valueOf(abcd)).child("date").getValue().toString();
					Log.i("split1",nsplit+"");
				}
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

		Log.i("split2",nsplit+"");
		return nsplit;
	}
}
