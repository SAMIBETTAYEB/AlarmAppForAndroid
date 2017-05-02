package com.example.mobi_app;

import android.text.Editable;

public class Car {
	int id;
int year;
String mark;
String model;
String color;
String motor_type;
	int yearInsr;
	int monthInsr;
	int dayInsr;
	int hourInsr;
	int minuteInsr;

	public Car( int year, String mark, String model, String color,
				String motor_type,int yearInsr,int monthInsr,int dayInsr,int hourInsr,int minuteInsr) {
		super();

		this.year = year;
		this.mark = mark;
		this.model = model;
		this.color = color;
		this.motor_type = motor_type;
		this.yearInsr=yearInsr;
		this.monthInsr=monthInsr;
		this.dayInsr=dayInsr;
		this.hourInsr=hourInsr;
		this.minuteInsr=minuteInsr;

	}

	public Car(int id, int year, String mark, String model, String color,
				String motor_type,int yearInsr,int monthInsr,int dayInsr,int hourInsr,int minuteInsr) {
		super();

		this.id=id;
		this.year = year;
		this.mark = mark;
		this.model = model;
		this.color = color;
		this.motor_type = motor_type;
		this.yearInsr=yearInsr;
		this.monthInsr=monthInsr;
		this.dayInsr=dayInsr;
		this.hourInsr=hourInsr;
		this.minuteInsr=minuteInsr;

	}

	public int getYearInsr() {
		return yearInsr;
	}

	public int getMonthInsr() {
		return monthInsr;
	}

	public int getDayInsr() {
		return dayInsr;
	}

	public int getHourInsr() {
		return hourInsr;
	}

	public int getMinuteInsr() {
		return minuteInsr;
	}

	public int getId() {
		return id;
	}

	public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public String getMark() {
	return mark;
}
public void setMark(String mark) {
	this.mark = mark;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getMotor_type() {
	return motor_type;
}
public void setMotor_type(String motor_type) {
	this.motor_type = motor_type;
}

}