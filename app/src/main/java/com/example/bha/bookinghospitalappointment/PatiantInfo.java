package com.example.bha.bookinghospitalappointment;

import android.widget.Toast;

/**
 * Created by Mohammed_Aqrabawi on 12/7/2017.
 */

public class PatiantInfo {
	public String fullname, gender, age,ssn,description,dr_Name ,hospital,department,day,date,time;

	public PatiantInfo() {
	}

	public PatiantInfo(String full_name, String Gender, String Age, String Ssn, String Description,String Dr_Name,String Hospital,String Department ,String Date,String Day,String Time) {
		this.fullname = full_name;
		this.gender = Gender;
		this.age = Age;
		this.ssn = Ssn;
		this.description = Description;
		this.dr_Name=Dr_Name;
		this.hospital=Hospital;
		this.department=Department;
		this.day=Day;
		this.date=Date;
		this.time=Time;
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

	public String getDr_Name() {
		return dr_Name;
	}

	public void setDr_Name(String dr_Name) {
		this.dr_Name = dr_Name;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
