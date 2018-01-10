package com.example.bha.bookinghospitalappointment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Mohammed_Aqrabawi on 11/28/2017.
 */

public class ProfilePage extends AppCompatActivity {
	Button btn;
	ListView List,Listname;
	int z=1;
	ArrayList<String> matches,names;
	FirebaseDatabase Firedatabase;
	DatabaseReference myRef;
	SharedPreferences sp;
	String un,type;
	ImageButton bback;
	String[] all={"fullname","gender","age","email","phone","password"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilepag);
bback= (ImageButton) findViewById(R.id.bback2);
		List= (ListView) findViewById(R.id.list);
		Listname= (ListView) findViewById(R.id.listnames);
		sp=this.getSharedPreferences("com.example.bha.bookinghospitalappointment", Context.MODE_PRIVATE);
		un=sp.getString("un","");
		type=sp.getString("type","Patient");

			List.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
					FullView.matches));
			Listname.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				Fromlocal()));
		List.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				showChangeLangDialog(all[position]);
			}
		});

	}

	private ArrayList<String> Fromlocal() {
		names= new ArrayList<>();
		String[] all={"fullname","gender","age","email","phone","password"};
		for (int i=0;i<all.length;i++){
			names.add(all[i]);
			Log.i("names", all[i]);
		}
		return names;
	}

	public void letsGoBack(View view){
		Intent intent=new Intent(this,HospitalGrid.class);
		startActivity(intent);
		finish();
	}



FullView fv=new FullView();
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent=new Intent(this,HospitalGrid.class);
		startActivity(intent);
		finish();
	}
	public void showChangeLangDialog(final String change) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
		dialogBuilder.setView(dialogView);

		final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

		dialogBuilder.setTitle("update");
		dialogBuilder.setMessage("Enter the update below");
		dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				if(edt.getText().toString().length()>=2){
				Firedatabase = FirebaseDatabase.getInstance();
				myRef = Firedatabase.getReference();
				myRef.child(type).child(un).child(change).setValue(edt.getText().toString());
				fv.profileFromFirebase(type,un);
				Intent intent=new Intent(ProfilePage.this,ProfilePage.class);
				startActivity(intent);
				finish();}
				else edt.setError("Enter a valid update");
			}
		});
		dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				//pass
			}
		});
		AlertDialog b = dialogBuilder.create();
		b.show();
	}
}
