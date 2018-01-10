package com.example.bha.bookinghospitalappointment;

import android.widget.Toast;

/**
 * Created by Mohammed_Aqrabawi on 12/7/2017.
 */

public class PatianToDoc {
	public String fullname, gender, age,ssn,description,user_Name ;

	public PatianToDoc() {
	}

	public PatianToDoc(String full_name, String Gender, String Age, String Ssn, String Description,String User_Name) {
		this.fullname = full_name;
		this.gender = Gender;
		this.age = Age;
		this.ssn = Ssn;
		this.description = Description;
		this.user_Name=User_Name;

	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

}
