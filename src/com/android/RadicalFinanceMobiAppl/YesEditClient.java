package com.android.RadicalFinanceMobiAppl;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class YesEditClient extends ListActivity{
	 private static final int ACTIVITY_EDIT=1;
	 
	 private DBAdapter mDbHelper;
	 
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      
	       // setContentView(R.layout.clients);
	        mDbHelper = new DBAdapter(this);
	        mDbHelper.open();
	       
	       
	        
	    }
	
	 @Override
     protected void onListItemClick(ListView l, View v, int position, long id) {
       super.onListItemClick(l, v, position, id);
       Intent i = new Intent(this, ClientEdit.class);
       i.putExtra(DBAdapter.KEY_CLIENTID, id);
       startActivityForResult(i, ACTIVITY_EDIT);
     }
	
      

     }

	
	 
