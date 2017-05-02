package com.example.mobi_app;

import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {

	/*----------------------------------------------------------
	 * variable database
	*/
	private static int  DATABASE_VERSION = 1;
	
	public static String V_ID    = "id";
	public static String V_MARK  = "mark";
	public static String V_YEAR  = "year";
	public static String V_MODEL  = "model";
	public static String V_MOTOR_TYPE  = "motor_type";
	public static String V_DATE_INSR  = "date_insr";
	
	
	public static String DATABASE_NAME = "db_vehicul";
	
	public static String TABLE_car = "tcar";
	
	
	private static String CREATE_TABLE_CAR = 
			"create table "+TABLE_car+ "  (id integer primary key autoincrement ," +
			"    year int," +
					"mark varchar(50)," +
					"color varchar(25)," +
					"motor_type varchar(25)," +
					"model varchar(25)" +
					"date_insr date ) ;" ;

	private static String DROP_TABLES =
			"DROP table if EXISTS "+ TABLE_car;
	
	/*-------------------------------------------------------------*/
	
	 public  Context context;
	
	 DataBaseHelper DBhelper;
	 SQLiteDatabase db;
	 
	 /*----------------consructor-----------------------------*/
	 
	 public MyDBAdapter(Context c){
		 this.context =c;
		 DBhelper = new DataBaseHelper(context);
	 }
	 
	 /*-------------open database------------------------------*/	 
	 public void open(){
		 db = DBhelper.getWritableDatabase();
	 }
	 
	 /*---------------close database------------------------*/	 
	 public void close(){
		 DBhelper.close();
	 }
	 
	//-------- insert Car object into the database-------------
	 
	 public void insetDATA(Car car){
		
		 ContentValues val = new ContentValues();
		 val.put(V_MARK, car.getMark());
		 val.put(V_YEAR, car.getYear());
		 val.put(V_MODEL, car.getModel());
		 val.put(V_MOTOR_TYPE, car.getMotor_type());
		 //val.put(V_DATE_INSR, car.getDate_insr());
		  db.insert(TABLE_car, null, val);
		 
	 }
	 
	//-------- insert contact into the database-------------
	 
	 public long insertCar(String mark ){
		 ContentValues val = new ContentValues();
		 val.put(V_MARK, mark);
		 
		 return db.insert(TABLE_car, null, val);
	 }
	 
	 //--------- get all contacts --------------	 
	 public Cursor getAllCar(){
		 Cursor mCursor  = db.query(TABLE_car, new String[]{V_ID , V_MARK,V_YEAR,V_MODEL,V_MOTOR_TYPE,V_DATE_INSR},
				 null, null, null, null, V_MARK);
		 if(mCursor != null){
			 mCursor.moveToFirst();
			 return mCursor;
		 }
		 return mCursor;
	 }
	 
	 public Cursor sherchCar(String mark){
		 
		 Cursor mCursor = db.query(true, TABLE_car, new String[]{V_ID , V_MARK,},
				 V_MARK+"="+mark, 
				 null, null, null, null, null);
		 if(mCursor != null){
			 mCursor.moveToFirst();
			 return mCursor;
		 }
		 return mCursor;
	 }
	 //----------- get Contact -------
	 public Cursor getCar(long id){
		 Cursor mCursor = db.query(true, TABLE_car, new String[]{V_ID , V_MARK,},
				 V_ID+"="+id, 
				 null, null, null, null, null);
		 if(mCursor != null){
			 mCursor.moveToFirst();
			 return mCursor;
		 }
		 return mCursor;
	 }
	
	 //---- delete car from table 
		public boolean deleteCar(long id){
			
			return db.delete(TABLE_car, V_ID+"="+id, null) >0;
		}
	 //---------- update car --------
		
		
	 public boolean updateCar(long id ,String mark ){
		 ContentValues val = new ContentValues();
		 val.put(V_MARK,mark);
		
		 return db.update(TABLE_car, val, V_ID +"="+ id, null) >0;
	 }
	 
	 //---------------is database vide  -------
	 public boolean isEmptyDB(){
		 
		 Cursor mCursor = db.query(TABLE_car, new String[]{V_ID , V_MARK},
				 null, null, null, null, null);
		 
		 if(mCursor.getCount() >0){
			return false;
		 }
		 return true;
	 }
	 
	/*-------------------------------------------------------------------------
	 * 
	*/
	public class DataBaseHelper extends SQLiteOpenHelper{

		public DataBaseHelper(Context context) {
			
			super(context, DATABASE_NAME, null, DATABASE_VERSION);			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(CREATE_TABLE_CAR);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			
			try {
				db.execSQL(DROP_TABLES);
				onCreate(db);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
