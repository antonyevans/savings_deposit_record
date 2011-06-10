package com.android.RadicalFinanceMobiAppl;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class YesSearchClient extends ListActivity{
	
	//private DBAdapter mDbHelper;

	protected EditText searchText;
	protected SQLiteDatabase db;
	protected Cursor cursor;
	protected ListAdapter adapter;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.search_interface);
	      // mDbHelper = new DBAdapter(this);
	        //mDbHelper.open();
	        searchText = (EditText) findViewById(R.id.searchText);
	      db = (new DBAdapter(this)).getWritableDatabase();
	       // searchClient();
	       
}

	 //code to search from clients database make changes morning
	    public void search(View view) {
	        	// || is the concatenation operation in SQLite
	    		cursor = db.rawQuery("SELECT _id, name, surname, mobile FROM clientsTable WHERE name || ' ' || surname LIKE ?", 
	    						new String[]{"%" + searchText.getText().toString() + "%"});
	    		adapter = new SimpleCursorAdapter(
	    				this, 
	    				R.layout.search_clientlist, 
	    				cursor, 
	    				new String[] {"name", "surname", "mobile"}, 
	    				new int[] {R.id.name, R.id.surname, R.id.mobile});
	    		setListAdapter(adapter);
	        }
	    public void onListItemClick(ListView parent, View view, int position, long id) {
	    	//Intent intent = new Intent(this, ShowHistoryActivity.class);
	    	//Cursor cursor = (Cursor) adapter.getItem(position);
	    	//intent.putExtra("CLIENT_ID", cursor.getInt(cursor.getColumnIndex("_id")));
	    	//startActivity(intent);
	    }

}

