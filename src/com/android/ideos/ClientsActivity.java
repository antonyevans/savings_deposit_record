package com.android.ideos;
//this where i will use the content provider to help in the functionality of the different menus
 import static android.provider.BaseColumns._ID;

import static com.android.ideos.Constants.CONTENT_URI;
import static com.android.ideos.Constants.TIME;
import static com.android.ideos.Constants.TITLE;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
 
 public class ClientsActivity extends ListActivity {
 
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.clients);
 addClient("" );
 Cursor cursor = getClients();
 showClients(cursor);
 }
 
 	
	 	private static int[] TO = { R.id.rowid, R.id.time, R.id.title, };
		private void showClients(Cursor cursor) {
		 // Set up data binding
		 SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
		 R.layout.listviewitem, cursor, FROM, TO);
		 setListAdapter(adapter);
		 }
		private Cursor getClients() {
			// Perform a managed query. The Activity will handle closing
			// and re-querying the cursor when needed.
			return managedQuery(CONTENT_URI, FROM, null, null, ORDER_BY);
			}
 	private static String[] FROM = { _ID, TIME, TITLE, };
	private static String ORDER_BY = TIME + " DESC" ;
	
	private void addClient(String string) {
		 //Insert a new record into the Clients data source.
		 // You would do something similar for delete and update.
	ContentValues values = new ContentValues();
	values.put(TIME, System.currentTimeMillis());
	values.put(TITLE, string);
	getContentResolver().insert(CONTENT_URI, values);
	}
 
 
	 @Override
     public boolean onOptionsItemSelected(MenuItem item) {
      // TODO Auto-generated method stub
      switch(item.getItemId())
      {
       case(R.id.menu_new_client):
        Toast.makeText(this, "New client", Toast.LENGTH_LONG).show();
        break;
       case(R.id.menu_edit):
        Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show();
        break; 
       case(R.id.menu_delete):
        Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show();
        break;
       case(R.id.menu_search):
           Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
           break;
      } 
      return true;
      
    
     
 }
	
}
