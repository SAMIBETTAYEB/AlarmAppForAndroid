package com.example.mobi_app;
import java.util.ArrayList;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Tab2 extends Fragment{
	ListView lv;
	static ArrayList<Car> cars=new ArrayList<Car>();
	public static MyCustomAdapter adapter;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.tab2,container,false);
		ListView lv=(ListView )rootView.findViewById(R.id.listView1);
//		cars.add(new Car(199,"Mark","Model7","Color8","Motor type15","Date16"));
//		cars.add(new Car(1999,"Mark1","Model6","Color9","Motor type14","Date17"));
//		cars.add(new Car(19915,"Mark2","Model5","Color10","Motor type13","Date18"));
//		cars.add(new Car(19923,"Mark3","Model4","Color11","Motor type12","Date19"));
		cars=loadElement(getActivity().getApplicationContext());
		adapter=new MyCustomAdapter(cars);
		lv.setAdapter(adapter);
		return rootView;
	

		
}



	public static ArrayList<Car> loadElement(Context context)
	{
		//String[] SelectionsArgs={ "%"+etUserName.getText().toString()+"%"};
		//add data and view it
		//cars=new ArrayList<Car>();
		cars.clear();
		DBManager dbManager=new DBManager(context);
		Cursor cursor=dbManager.query(null,null,null,null);

		if (cursor.moveToFirst()){
			String tableData="";
			do {
                /*tableData+=cursor.getString(cursor.getColumnIndex(DBManager.ColUserName))+ ","+
                        cursor.getString( cursor.getColumnIndex(DBManager.ColPassWord)) +"::";
               */


				Car car=new Car(cursor.getInt(cursor.getColumnIndex(DBManager.ColID)),cursor.getInt(cursor.getColumnIndex(DBManager.ColYear)),cursor.getString(cursor.getColumnIndex(DBManager.ColMark)),cursor.getString(cursor.getColumnIndex(DBManager.ColModel)),cursor.getString(cursor.getColumnIndex(DBManager.ColColor)),cursor.getString(cursor.getColumnIndex(DBManager.ColType)),cursor.getInt(cursor.getColumnIndex(DBManager.ColYearInsr)),cursor.getInt(cursor.getColumnIndex(DBManager.ColMonthInsr)),cursor.getInt(cursor.getColumnIndex(DBManager.ColDayInsr)),cursor.getInt(cursor.getColumnIndex(DBManager.ColHourInsr)),cursor.getInt(cursor.getColumnIndex(DBManager.ColMinuteInsr)));
				cars.add(car);

				//savedata savedata=new savedata(context);
				//savedata.Alarmset(cursor.getInt(cursor.getColumnIndex(DBManager.ColID)),cursor.getInt(cursor.getColumnIndex(DBManager.ColYearInsr)),cursor.getInt(cursor.getColumnIndex(DBManager.ColMonthInsr)),cursor.getInt(cursor.getColumnIndex(DBManager.ColDayInsr)),cursor.getInt(cursor.getColumnIndex(DBManager.ColHourInsr)),cursor.getInt(cursor.getColumnIndex(DBManager.ColMinuteInsr)),"load");

			}while (cursor.moveToNext());

			Toast.makeText(context,tableData,Toast.LENGTH_LONG).show();
		}

		return cars;

//		myadapter=new MyCustomAdapter(listnewsData);
//
//		ListView lsNews=(ListView)findViewById(R.id.LVNews);
//		lsNews.setAdapter(myadapter);//intisal with data
	}

	

	class MyCustomAdapter extends BaseAdapter{
		private ArrayList<Car> cars;
		private DBManager dbManager=new DBManager(getActivity());

		public MyCustomAdapter(ArrayList<Car> cars){
			this.cars=cars;
		}

		@Override
		public int getCount() {
			return cars.size();
		}

		@Override
		public Car getItem(int position) {
			return cars.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater=getActivity().getLayoutInflater();
			View myView=inflater.inflate(R.layout.item,null);
//			int year,
//			String mark,
//			String model,
//			String color,
//			String motor_type,
//			String date_insr
			((TextView)myView.findViewById(R.id.textViewyear)).setText(String.valueOf(cars.get(position).getYear()));
			((TextView)myView.findViewById(R.id.textViewmark)).setText(cars.get(position).getMark());
			((TextView)myView.findViewById(R.id.textViewmodel)).setText(cars.get(position).getModel());
			((TextView)myView.findViewById(R.id.textViewcolor)).setText(cars.get(position).getColor());
			((TextView)myView.findViewById(R.id.textViewmotortype)).setText(cars.get(position).getMotor_type());
			((TextView)myView.findViewById(R.id.textViewdate)).setText(cars.get(position).getYearInsr()+"/"+cars.get(position).getMonthInsr()+"/"+cars.get(position).getDayInsr()+"   "+cars.get(position).getHourInsr()+":"+cars.get(position).getMinuteInsr());


			Button buDelet=(Button)myView.findViewById(R.id.btn_delete);
			buDelet.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String[] SelectionArgs={String.valueOf( cars.get(position).getId())};
					int count= dbManager.Delete("ID=?",SelectionArgs);
					if (count>0)
					{


						AlarmManager am = (AlarmManager)  getActivity().getSystemService  (Context.ALARM_SERVICE);
						Intent intent = new Intent(getActivity(), MyReceiver.class);
						intent.setAction("com.me.alam");
						PendingIntent pi = PendingIntent.getBroadcast(getActivity(), cars.get(position).getId(), intent,
								PendingIntent.FLAG_UPDATE_CURRENT);
						am.cancel(pi);

						Tab2.loadElement(getActivity());
						Tab2.adapter.notifyDataSetChanged();

					}
				}
			});

//			Button buUpdate=(Button)myView.findViewById(R.id.buUpdate);
//			buUpdate.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					etUserName.setText(s.UserName);
//					etPassword.setText(s.Password);
//					RecordID=s.ID;
//				}
//			});


			return myView;
		}
	}


}
