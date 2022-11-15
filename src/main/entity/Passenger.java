package main.entity;

import java.io.Serializable;

public class Passenger implements Serializable{
	private String name;
	private String gender;
	private long mobileNo;
	
	public Passenger(String name, String gender, long mobileNo) {
		this.name = name;
		this.gender = gender;
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Passenger [name=" + name + ", gender=" + gender + ", mobileNo=" + mobileNo + "]";
	}
}
