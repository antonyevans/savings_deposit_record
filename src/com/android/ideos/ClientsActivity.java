package com.android.ideos;
//this where i will use the content provider to help in the functionality of the different menus
 import static android.provider.BaseColumns._ID;

 import static com.android.ideos.Constants.TABLE_NAME;
 import static com.android.ideos.Constants.TIME;
 import static com.android.ideos.Constants.TITLE;
 import android.content.ContentValues;
 import android.database.sqlite.SQLiteDatabase;
 
 import android.database.Cursor;
 import android.os.Bundle;
 import android.app.ListActivity;
 import android.widget.SimpleCursorAdapter;
 
 public class ClientsActivity extends ListActivity {
 private ClientsData clients;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.clients);
 clients = new ClientsData(this);
 
 
 try 
 {
 addClient("Antony, Samuel!" );
 Cursor cursor = getClients();
 showClients(cursor);
 }
 
 
 finally
 {
 clients.close();
 }
 }
 	
	 	private static int[] TO = { R.id.rowid, R.id.time, R.id.title, };
		private void showClients(Cursor cursor) {
		 // Set up data binding
		 SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
		 R.layout.listviewitem, cursor, FROM, TO);
		 setListAdapter(adapter);
		 }

 	private static String[] FROM = { _ID, TIME, TITLE, };
	private static String ORDER_BY = TIME + " DESC" ;
	private Cursor getClients() {
	// Perform a managed query. The Activity will handle closing
	// and re-querying the cursor when needed.
	SQLiteDatabase db = clients.getReadableDatabase();
	Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null,
	null, ORDER_BY);
	startManagingCursor(cursor);
	return cursor;
	}



private void addClient(String string) {
		 //Insert a new record into the Clients data source.
		 // You would do something similar for delete and update.
		SQLiteDatabase db = clients.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TIME, System.currentTimeMillis());
		values.put(TITLE, string);
		db.insertOrThrow(TABLE_NAME, null, values);
		}
	
}
