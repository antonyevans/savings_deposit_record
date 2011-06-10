package com.android.RadicalFinanceMobiAppl;

import com.android.RadicalFinanceMobiAppl.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	    {"Samuel","Kibaki","Simon","Adera","John","Thuo","Antony","Muganda"
	                                            		
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

//calls the options menu layout
@Override
public boolean onCreateOptionsMenu(Menu menu) {
 MenuInflater myMenuInflater = getMenuInflater();
 myMenuInflater.inflate(R.menu.menu, menu);
    return true;
}
//the layout of the options menu as seen in the menu.xml file
@Override
public boolean onOptionsItemSelected(MenuItem item) {
 // TODO Auto-generated method stub
 switch(item.getItemId())
 {
 // the menu button New Client and the functionality code will be implemented here.
  case(R.id.menu_new_client):
   Toast.makeText(this, "New client", Toast.LENGTH_LONG).show();
  
  
   break;
   
   // the menu button: Edit, and the functionality code will be implemented here.
  case(R.id.menu_edit):
   Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show();
   break; 
   
   // the menu button: DElete, and the functionality code will be implemented here.
  case(R.id.menu_delete):
   Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show();
  
  
  	break;
   
   // the menu button: Search, and the functionality code will be implemented here.
  case(R.id.menu_search):
      Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
      break;
 } 
 return true;
}
}
	
	

