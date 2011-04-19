package com.android.ideos;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class List extends ListActivity
{
	private static final String TAG = "List";
	public static final String[] Clients= new String[]
	    {"Samuel","Mwaura","Simon","Adera","John","Thuo","Antony","Muganda"
	                                            		
	                                            	 };
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.listitem, Clients));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {Toast.makeText(getApplicationContext(), ((TextView)view).getText(), Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	 //enabling loging of errors in logcat
	String List = null;
	{
	Log.e(TAG, "this class" + List);
}
}