package com.example.bha.bookinghospitalappointment;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Mohammed_Aqrabawi on 12/17/2017.
 */

public class FullView {
	boolean exist;
	int x=1;

	public static ArrayList<String> itmes;
	public static ArrayList<String> dis;
	public static ArrayList<String> times;
	public static ArrayList<String> info;
	public static ArrayList<String> matches;
	FirebaseDatabase Firedatabase;
	DatabaseReference myRef;
	public void viewItems(final String un){
		itmes=new ArrayList<>();
		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if(dataSnapshot.child("Patient").child(un).child("Booking").exists()) {
					if (dataSnapshot.child("Patient").child(un).child("Booking")
							.hasChildren()) {
						while (itmes.size() < ((int) (dataSnapshot.child("Patient").child(un)
								.child("Booking").getChildrenCount()))) {
							itmes.add((String) dataSnapshot.child("Patient").child(un)
									.child("Booking").child(x + "").getKey()+" :   "+(String) dataSnapshot.child("Patient").child(un)
									.child("Booking").child(x + "").child("hospital").getValue()+" / "+ (String) dataSnapshot.child("Patient").child(un)
									.child("Booking").child(x + "").child("department").getValue());
							Log.i("times" + x, (String) dataSnapshot.child("Patient").child(un)
									.child("Booking").child(x + "").child("date").getValue() + ((int) (dataSnapshot.child("Patient").child(un)
									.child("Booking").getChildrenCount())));
							x++;

						}

					}
				}
				else itmes.add( "No Record is Avaliable");

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}
	public ArrayList<String> viewDis(final String un,final String position){
		dis =new ArrayList<>();
		final String [] names={"Full Name : ","Date     : ","Day      : ","Time     : ","Dr.Name  : "};

		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					if (dataSnapshot.child("Patient").child(un).child("Booking").child(position).exists()) {
						String[] all = {"fullname", "date", "day", "time", "dr_Name"};

						for (int i = 0; i < all.length; i++) {
							String value = names[i] + "" + String.valueOf(dataSnapshot.child("Patient").child(un).child("Booking").child(position).child(all[i]).getValue());
							dis.add(value);
						}
					}
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
		return dis;
	}
	public boolean isExist(final String un , final String position){
		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					if (dataSnapshot.child("Patient").child(un).child("Booking").child(position).exists()) {
						exist=true;
						}
						else exist=false;
					}
				}


			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
		return exist;
	}
	public boolean isExist2(final String un ,final String dayname , final String date,final String time){
		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					if (dataSnapshot.child("Doctor").child(un).child("day").child(dayname).child(date).child(time).exists()) {
						exist=true;
						viewPatientDetail(un, dayname, date, time);
					}
					else exist=false;
				}
			}


			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});
		return exist;
	}
//----------------------------------------------------------------------------------------------------------
public void viewDoctorItems(final String un ,final String dayname , final String date){
	times=new ArrayList<>();
	Firedatabase = FirebaseDatabase.getInstance();
	myRef = Firedatabase.getReference();
	myRef.addValueEventListener(new ValueEventListener() {
		@Override
		public void onDataChange(DataSnapshot dataSnapshot) {
			if(dataSnapshot.child("Doctor").child(un).child("day").exists()) {
				if (dataSnapshot.child("Doctor").child(un).child("day").child(dayname).child(date).exists()){
					for (DataSnapshot postSnapshot: dataSnapshot.child("Doctor").child(un).child("day").child(dayname).child(date).getChildren()){
						Log.i("no1","1"+dayname+"***"+date);
						times.add((String) postSnapshot.getKey());
					}
					}
				else {times.add( "No Record is Avaliable");
				Log.i("no1","1"+dayname+"***"+date);}
			}
			else {times.add( "No Record is Avaliable");Log.i("no2","2");}
		}

		@Override
		public void onCancelled(DatabaseError databaseError) {
		}
	});
}
	public void viewPatientDetail(final String un,final String dayname , final String date , final String time){
		info =new ArrayList<>();
		final String [] names={"SSN       : ","Full Name   : ","Gender     : ","Age        : ","Description : ","User Name : "};

		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Log.i("tag123",dayname+"*"+date+"**"+time+"***");

						String[] all = {"ssn","fullname", "gender", "age", "description", "user_Name"};
						Log.i("tag123",dayname+"*"+date+"**"+time+"***");

						for (int i = 0; i < all.length; i++) {
							Log.i("tag123",dayname+"*"+date+"**"+time+"***");

							String value = names[i] + "" + dataSnapshot.child("Doctor").child(un).child("day").child(dayname).child(date).child(time).child(all[i]).getValue();
							Log.i("tag123",value+"");
							info.add(value);
						}

				}


			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});

	}
	public static int bookCount;
	public int bookingCount(final String un){
		Firedatabase = FirebaseDatabase.getInstance();
		myRef = Firedatabase.getReference();
		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.child("Patient").child(un).child("Booking").exists()) {
					bookCount= 1 + (int) dataSnapshot.child("Patient").child(un).child("Booking").getChildrenCount();
					Log.i("bookCountexist",bookCount+"");
				}

				else {bookCount=1;
					Log.i("bookNot Exist",bookCount+"");}
			}


			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
		Log.i("bookCount",bookCount+"");
		return bookCount;
	}


	public ArrayList<String> profileFromFirebase(final String t, final String u) {

		matches=new ArrayList<>();

		Firedatabase = FirebaseDatabase.getInstance();


		myRef = Firedatabase.getReference();


		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					String[] all={"fullname","gender","age","email","phone","password"};
					if ((matches.isEmpty())){
						for (int i=0;i<all.length;i++){
						String value = String.valueOf(dataSnapshot.child(t).child(u).child(all[i]).getValue());
						matches.add(value);
						Log.i("value", value+" "+t+" "+u);
					}

					}else {matches.clear();profileFromFirebase(t, u);}

				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}

		});


		return matches;
	}
}