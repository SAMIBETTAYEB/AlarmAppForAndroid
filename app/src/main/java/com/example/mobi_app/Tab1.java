package com.example.mobi_app;
import android.content.ContentValues;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;


import android.support.v4.view.ViewPager;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.*;
import java.util.Calendar;


 public class Tab1 extends Fragment{
	 
	 EditText mark,model,year,color,motor_type,date_inser;
	 Button butn;
	 Car car;
	// DB_Controler controler;
	 Context context;
	 int nbr = 0;
	 private MyDBAdapter db = null;
	 
	public  Tab1(Context c){
		 
		 this.context = c;
		 db = new MyDBAdapter(context);
	 }
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View rootView=inflater.inflate(R.layout.tab1,container,false);
		return rootView;
}

	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	
		final EditText mark = (EditText) getActivity().findViewById(
	            R.id.editText1);
	    final EditText  model= (EditText) getActivity().findViewById(
	            R.id.editText2);
	    final EditText year = (EditText) getActivity().findViewById(
	            R.id.editText3);
	    final EditText color = (EditText) getActivity().findViewById(
	            R.id.editText5);
	    final EditText motor_type = (EditText) getActivity().findViewById(
	            R.id.editText6);
//		final EditText date_inser = (EditText) getActivity().findViewById(
//				R.id.editText7);
		final DatePicker date_inser = (DatePicker) getActivity().findViewById(
				R.id.datePicker);
		final TimePicker time_inser = (TimePicker) getActivity().findViewById(
				R.id.timePicker);



		Button butn = (Button) getActivity().findViewById(R.id.button1);
	    butn.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
//	        	startAlert();
//
//
//	        	db.open();
//	        	//db.insertCar("car"+nbr);
//	        	car = new Car(Integer.parseInt(year.getText().toString()),mark.getText().toString(),model.getText().toString(),color.getText().toString(),motor_type.getText().toString(),date_inser.getText().toString());
//	        	db.insetDATA(car);
//	        	/*db.insetDATA(new Car(1, Integer.parseInt(year.getText()), mark.getText(), model.getText(), color.getText(),
//	        			motor_type.getText(), date_inser.getText())); */
//	        	Cursor  c = db.getAllCar();
//
//	        	for (c.moveToFirst();!c.isAfterLast(); c.moveToNext()) {
//	        		Toast.makeText(context , c.getString(1)  , Toast.LENGTH_LONG).show();
//				}
//	        	nbr ++;
//	        	db.close();
				DBManager dbManager=new DBManager(getActivity());
				ContentValues values= new ContentValues();
				values.put(DBManager.ColColor,color.getText().toString());
				values.put(DBManager.ColMark,mark.getText().toString());
				values.put(DBManager.ColModel,model.getText().toString());
				values.put(DBManager.ColType,motor_type.getText().toString());
				values.put(DBManager.ColYear,Integer.parseInt(year.getText().toString()));


				values.put(DBManager.ColYearInsr,date_inser.getYear());
				values.put(DBManager.ColMonthInsr,date_inser.getMonth());
				values.put(DBManager.ColDayInsr,date_inser.getDayOfMonth());
				values.put(DBManager.ColHourInsr,time_inser.getCurrentHour());
				values.put(DBManager.ColMinuteInsr,time_inser.getCurrentMinute());

				long id= dbManager.Insert(values);
				if (id>0){
					Toast.makeText(getActivity().getApplicationContext(),"Data is added and user id:"+id,Toast.LENGTH_LONG).show();
					setTime((int) id,date_inser.getYear(),date_inser.getMonth(),date_inser.getDayOfMonth(),time_inser.getCurrentHour(),time_inser.getCurrentMinute(),"add");

				} else
					Toast.makeText(getActivity().getApplicationContext(),"cannot insert",Toast.LENGTH_LONG).show();


				Tab2.cars=Tab2.loadElement(getActivity().getApplicationContext());
				Tab2.adapter.notifyDataSetChanged();
	           
	      
	        		
	        		
	        	} 
	     

			
	    });

	}

	//------------------------------------------------------------------//
//	public void startAlert(int year,int month,int day,int hour,int minute) {
//	       // for Alarm 30/04/2017 at 12.00
//	Calendar myAlarmDate = Calendar.getInstance();
//	myAlarmDate.setTimeInMillis(System.currentTimeMillis());
//	myAlarmDate.set(year, month, day, hour, minute, 0);
//	//other way
//	  Calendar calendar = Calendar.getInstance();
//	        calendar.set(Calendar.HOUR_OF_DAY, 01);
//	        calendar.set(Calendar.MINUTE, 37);
//	        calendar.set(Calendar.SECOND, 00);
//
//	 //define Repeating Alarm Start After Each 2 Minutes
//
//
//	  AlarmManager am = (AlarmManager)context.getSystemService  (Context.ALARM_SERVICE);
//	        Intent intent = new Intent(context, MyService.class);
//	        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
//	                PendingIntent.FLAG_UPDATE_CURRENT);
//	        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//	                AlarmManager.INTERVAL_DAY , pi);
//	// Create one time   Alarm Start After Each 2 Minutes
//	am.setRepeating(AlarmManager.RTC_WAKEUP, myAlarmDate.getTimeInMillis(),24*60*60*1000,pi);
//
//
//	}




	 void setTime(int id,int year, int month, int day,int Hour ,int Minute,String from ){


		 // /save dat
		 savedata savedata =new savedata(getActivity());
		 savedata.Alarmset(id,year,month,day,Hour,Minute,from);
		 savedata.SaveData(id,year,month,day,Hour,Minute);

	 }




 }



	
