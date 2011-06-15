package com.android.RadicalFinanceMobiAppl;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class AdminAgentslistview extends ListActivity{
private DBAdapter mDbHelper;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 setContentView(R.layout.adminlistvof);
        
        mDbHelper = new DBAdapter(this);
	      mDbHelper.open();
	      fillData();

}
private void fillData() {
    Cursor ACursor = mDbHelper.getAllAgents();
    startManagingCursor(ACursor);
 // Create an array to specify the fields we want to display in the list (name and Surname and mobile num, passkey)
    String[] fromColumns = new String[]{DBAdapter.KEY_NAME, DBAdapter.KEY_SURNAME, DBAdapter.KEY_MOBILE, DBAdapter.KEY_AGENTPASSKEY};
 // and an array of the fields we want to bind those fields to (in this case just text1,2,3.4)
    int[] toLayoutIDs = new int[]{R.id.client1, R.id.client2, R.id.client3,  R.id.client4};
 // Now create a simple cursor adapter and set it to display
    SimpleCursorAdapter agentstable = new SimpleCursorAdapter(this, R.layout.clients_row, ACursor, fromColumns, toLayoutIDs);
     setListAdapter(agentstable);
     
  }
}