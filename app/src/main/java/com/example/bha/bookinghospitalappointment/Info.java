package com.example.bha.bookinghospitalappointment;

public class Info {

	public String fullname, gender, age, email, phone, password;

	public Info() {
	}

	public Info(String full_name, String Gender, String Age, String Email, String Phone, String Password) {
		this.fullname = full_name;
		this.gender = Gender;
		this.age = Age;
		this.email = Email;
		this.phone = Phone;
		this.password = Password;

	}

	public String getFullname() {
		return fullname;
	}

	public String getGender() {
		return gender;
	}

	public String getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	}