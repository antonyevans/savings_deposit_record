package com.android.ideos;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;



/**will remove this in any case made them comments for refence of my old work
//this where i will use the content provider to help in the functionality of the different menus
import static android.provider.BaseColumns._ID;
import static com.android.ideos.Constants.CONTENT_URI;
import static com.android.ideos.Constants.FirstName;
import static com.android.ideos.Constants.SecondName;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


 public class ClientsActivity extends ListActivity {
	
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.clients);
 
//an instance of the ClientsData
 ClientsData db = new ClientsData(this); 
 //an example of a client added
 addClient ("Samuel Mwaura");
 
 Cursor cursor = getClients();
 showClients(cursor);
}	
 
	private static int[] TO = { R.id._ID, R.id.name, R.id.surname, };
	private void showClients(Cursor cursor) {
	 // Set up data binding
	 SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
	 R.layout.listviewitem, cursor, FROM, TO);
	 setListAdapter(adapter);
	 } 	
	
		
		
		//Running a query
		private Cursor getClients() {
			// Perform a managed query. The Activity will handle closing
			// and re-querying the cursor when needed.
			return managedQuery(CONTENT_URI, FROM, null, null, ORDER_BY);
			}
		private static String[] FROM = { _ID, FirstName, SecondName };
		private static String ORDER_BY = "ASC" ;
	
	private void addClient(String string) {
		 //Insert a new record into the Clients data source.
		 // You would do something similar for delete and update.
	ContentValues values = new ContentValues();
	values.put(_ID, 1);
	values.put(FirstName, string);
	values.put(SecondName, string);
	getContentResolver().insert(CONTENT_URI, values);
	
	}
	
	//calls the content menu layout
	 @Override
     public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater myMenuInflater = getMenuInflater();
      myMenuInflater.inflate(R.menu.menu, menu);
         return true;
     }
 
	 // the layout of the menu as seen in the menu.xml file
	 @Override
     public boolean onOptionsItemSelected(MenuItem item) {
      // TODO Auto-generated method stub
      switch(item.getItemId())
      {
      // the menu button New Client and the functionality code will be implemented here.
       case(R.id.menu_new_client):
        Toast.makeText(this, "New client", Toast.LENGTH_LONG).show();
       
       ContentValues values = new ContentValues();
       // can be left "blank" for adding a clients' name
       values.put(FirstName, "samuel");
       values.put(SecondName, "Gatiru");
       	        
       	 getContentResolver().insert(Constants.CONTENT_URI, values);
       	       
        break;
        
        // the menu button: Edit, and the functionality code will be implemented here.
       case(R.id.menu_edit):
        Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show();
        break; 
        
        // the menu button: DElete, and the functionality code will be implemented here.
       case(R.id.menu_delete):
        Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show();
       
       Uri DBAdapterUri=Uri.parse("content://DBAdapter");
       	getContentResolver().delete(DBAdapterUri,"ClientID=?",new String[]{"1"});
        
       	break;
        
        // the menu button: Search, and the functionality code will be implemented here.
       case(R.id.menu_search):
           Toast.makeText(this, "Search", Toast.LENGTH_LONG).show();
           break;
      } 
      return true;
      
    
     
 }
	
}
*
*/
public class ClientsActivity extends ListActivity {
	 
	long id;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 
	 setContentView(R.layout.clients);
	 //an instance of the DBAdapter class
	 DBAdapter db = new DBAdapter(this); 
	
	 //---add two clients---
     db.open();        
     
     id = db.insertClient(
     		"Samuel",
     		"David",
     		"07564324568");        
     id = db.insertClient(
     		"Kioko",
     		"Kilokumi",
     		"07677667734");
     db.close();

     //---get all clients---
     db.open();
     Cursor c = db.getAllClients();
     if (c.moveToFirst())
     {
         do { 
        	
        	 DisplayClient(c);
        	 
         } while (c.moveToNext());
     }
     db.close();
    

     }
	public void DisplayClient(Cursor c) {
     	Toast.makeText(this,
                 "id: " + c.getString(0) + "\n" +
                 "NAME: " + c.getString(1) + "\n" +
                 "SURNAME: " + c.getString(2) + "\n" +
                 "MOBILE:  " + c.getString(3),
                 Toast.LENGTH_LONG).show(); 
         } 




// the layout of the menu as seen in the menu.xml file
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

     
     	

