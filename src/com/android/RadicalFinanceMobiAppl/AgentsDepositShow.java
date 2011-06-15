package com.android.RadicalFinanceMobiAppl;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class AgentsDepositShow extends ListActivity{
	
	private DBAdapter mDbHelper;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      // set the activity layout
        setContentView(R.layout.history1);
        
        mDbHelper = new DBAdapter(this);
	      mDbHelper.open();
	      filldatafromDB();
	      fillmobilefromDB();
	}
	
	public String fillmobilefromDB(){
        Cursor caCursor = mDbHelper.getAgentTransactions();
        startManagingCursor(caCursor);
        
      
        String phonenumber ="";
        phonenumber= DBAdapter.KEY_MOBILE;
        return phonenumber;
        
	}
	
	
	private void filldatafromDB() {
        Cursor caCursor = mDbHelper.getAgentTransactions();
        startManagingCursor(caCursor);
        
        // Create an array to specify the fields we want to display in the list (surname, mobile, datetime, cash)
        String phonenumber = DBAdapter.KEY_MOBILE;
        String[] fromColumns = new String[]{DBAdapter.KEY_SURNAME, DBAdapter.KEY_MOBILE, 
        		DBAdapter.KEY_CASH, DBAdapter.KEY_DATETIME};

        //and an array of the fields we want to bind those fields to 
        
       
        int[] toLayoutIDs = new int[]{R.id.clientName, R.id.Type, R.id.Amount, R.id.DateTime};
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter agent = new SimpleCursorAdapter(this, R.layout.historylist, caCursor, fromColumns, toLayoutIDs);
         setListAdapter(agent);
         
        }
 @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        filldatafromDB();
       

    }

}

