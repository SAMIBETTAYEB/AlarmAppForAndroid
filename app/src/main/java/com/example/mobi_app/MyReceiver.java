package com.example.mobi_app;

/**
 * Created by Bettayeb on 5/1/2017.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase("com.me.alam")){
            Bundle b=intent.getExtras();
            if(false){//!b.getString("from").equalsIgnoreCase("load")){

            }else{
            Toast.makeText(context,
//y="",ma="",mo="",co="",ty="",yi="",moi="",di="",hi="",mi="";
                    b.getString("MyMessage")
                            +"\nYear: "+b.getString("y")
                            +"\nMark: "+b.getString("ma")
                            +"\nModel: "+b.getString("mo")
                            +"\nColor: "+b.getString("co")
                            +"\nType: "+b.getString("ty")
                            +"\nDate and time of Notification: "+b.getString("yi")
                            +"/"+b.getString("moi")
                            +"/"+b.getString("di")
                            +"   "+b.getString("hi")
                            +":"+b.getString("mi")

                    ,Toast.LENGTH_LONG).show();

            CarNotification.notify(context,"Car notification",b.getString("MyMessage")
                    +"\nYear: "+b.getString("y")
                    +"\nMark: "+b.getString("ma")
                    +"\nModel: "+b.getString("mo")
                    +"\nColor: "+b.getString("co")
                    +"\nType: "+b.getString("ty")
                    +"\nDate and time of Notification: "+b.getString("yi")
                    +"/"+(Integer.parseInt(b.getString("moi"))+1)
                    +"/"+b.getString("di")
                    +"   "+b.getString("hi")
                    +":"+b.getString("mi"),Integer.parseInt(b.getString("id")));

                }

        }
        else if (intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){
            // restart
            savedata savedata =new savedata(context);
            savedata.LoadData();


        }
    }
}