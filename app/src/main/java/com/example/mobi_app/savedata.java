package com.example.mobi_app;

/**
 * Created by Bettayeb on 5/1/2017.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import java.util.Calendar;

/**
 * Created by hussienalrubaye on 11/1/16.
 */

public class savedata {
    SharedPreferences ShredRef;
    Context context;
    public savedata(Context context){
        this.context=context;
        ShredRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE);
    }

    public  void SaveData(int id,int year,int month,int day,int hour,int minute){
        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putInt("year",year);
        editor.putInt("month",month);
        editor.putInt("day",day);
        editor.putInt("hour",hour);
        editor.putInt("minute",minute);
        editor.putInt("id",id);
        editor.commit();
    }

    public void LoadData(){
        int year=ShredRef.getInt("year",0);
        int month=ShredRef.getInt("month",0);
        int day=ShredRef.getInt("day",0);
        int Minute=ShredRef.getInt("minute",0);
        int Hour=ShredRef.getInt("hour",0);
        int id=ShredRef.getInt("id",0);
        //Alarmset(id,year,month,day,Hour,Minute,"load");

    }

    void Alarmset(int id,int year, int month, int day, int Hour,int Minute,String from){
        DBManager dbManager=new DBManager(context);
        Cursor cursor=dbManager.query(null,"ID=?",new String[]{String.valueOf(id)},null);
        String y="",ma="",mo="",co="",ty="",yi="",moi="",di="",hi="",mi="",id__="0";
        if(cursor.moveToFirst()){
            id__=cursor.getInt(cursor.getColumnIndex(DBManager.ColID))+"";
            y=cursor.getInt(cursor.getColumnIndex(DBManager.ColYear))+"";
            ma=cursor.getString(cursor.getColumnIndex(DBManager.ColMark));
            mo=cursor.getString(cursor.getColumnIndex(DBManager.ColModel));
            co=cursor.getString(cursor.getColumnIndex(DBManager.ColColor));
            ty=cursor.getString(cursor.getColumnIndex(DBManager.ColType));
            yi=cursor.getInt(cursor.getColumnIndex(DBManager.ColYearInsr))+"";
            moi=cursor.getInt(cursor.getColumnIndex(DBManager.ColMonthInsr))+"";
            di=cursor.getInt(cursor.getColumnIndex(DBManager.ColDayInsr))+"";
            hi=cursor.getInt(cursor.getColumnIndex(DBManager.ColHourInsr))+"";
            mi=cursor.getInt(cursor.getColumnIndex(DBManager.ColMinuteInsr))+"";

        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, Minute);
        calendar.set(Calendar.SECOND, 0);
        AlarmManager am = (AlarmManager)  context.getSystemService  (Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReceiver.class);
        intent.setAction("com.me.alam");

        intent.putExtra("MyMessage","Alarm Notification");
        intent.putExtra("y",y);
        intent.putExtra("ma",ma);
        intent.putExtra("mo",mo);
        intent.putExtra("co",co);
        intent.putExtra("ty",ty);
        intent.putExtra("yi",yi);
        intent.putExtra("moi",moi);
        intent.putExtra("di",di);
        intent.putExtra("hi",hi);
        intent.putExtra("mi",mi);
        intent.putExtra("id",id__);
        intent.putExtra("from",from);

        PendingIntent pi = PendingIntent.getBroadcast(context, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY , pi);
    }
}