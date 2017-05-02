package com.example.mobi_app;

/**
 * Created by Bettayeb on 4/30/2017.
 */

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteQueryBuilder;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

/**
 * Created by hussienalrubaye on 9/8/16.
 */

public class DBManager {
    private SQLiteDatabase sqlDB;
    static final String DBName="cars";
    static final String TableName="cars";


    static final String ColYear="year";
    static final String ColMark="mark";
    static final String ColModel="model";
    static final String ColType="type";
    static final String ColYearInsr="year_insr";
    static final String ColMonthInsr="month_insr";
    static final String ColDayInsr="day_insr";
    static final String ColHourInsr="hour_insr";
    static final String ColMinuteInsr="minute_insr";
    static final String ColColor="color";
    static final String ColID="ID";
    static final  int DBVersion=4;
    // create table Logins(ID integer primary key autoincrment, UserName text, Password text)
    static final  String CreateTable=" CREATE TABLE IF NOT EXISTS " +TableName+
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ ColMark+
            " TEXT,"+ ColModel + " TEXT," +
            ColType+ " TEXT," +
            ColColor+ " TEXT," +
            ColYear+ " INT," +
            ColYearInsr+ " INT," +
            ColMonthInsr+ " INT," +
            ColDayInsr+ " INT," +
            ColHourInsr+ " INT," +
            ColMinuteInsr+ " INT" +
            ");";

    private static class  DatabaseHelperUser extends SQLiteOpenHelper{
        Context context;
        DatabaseHelperUser(Context context){
            super(context,DBName,null,DBVersion);
            this.context=context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            Toast.makeText(context,"Table is created",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF  EXISTS "+ TableName);
            onCreate(db);
        }
    }



    public DBManager(Context context){

        DatabaseHelperUser db=new DatabaseHelperUser(context) ;
        sqlDB=db.getWritableDatabase();

    }

    public  long Insert(ContentValues values){
        long ID=   sqlDB.insert(TableName,"",values);
        //could insert id is user id, or fail id is or equal 0
        return ID;
    }
    //select username,Password from Logins where ID=1
    public Cursor query(String[] Projection,String Selection,String[] SelectionArgs,String SortOrder){

        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();
        qb.setTables(TableName);

        Cursor cursor=qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }

    public int Delete(String Selection,String[] SelectionArgs){
        int count=sqlDB.delete(TableName,Selection,SelectionArgs);
        return count;
    }

    public  int Update(ContentValues values,String Selection,String[] SelectionArgs)
    {
        int count=sqlDB.update(TableName,values,Selection,SelectionArgs);
        return count;
    }

}