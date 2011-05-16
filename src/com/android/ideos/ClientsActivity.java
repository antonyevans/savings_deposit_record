package com.android.ideos;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ClientsActivity extends ListActivity {
	private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;
    private static final int DEPOSIT_ID = Menu.FIRST + 2;
    private static final int WITHDRAW_ID = Menu.FIRST + 3;
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
   String clientname;
    
protected DBAdapter db;
	
	//protected ListAdapter adapter;
    private DBAdapter mDbHelper;
	
	
	//private static final String TAGG = "ClientsActivity";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients);
        mDbHelper = new DBAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
    }
    private void fillData() {
        Cursor clientsCursor = mDbHelper.getAllClients();
        startManagingCursor(clientsCursor);
    
 // Create an array to specify the fields we want to display in the list (name and Surname)
  
    String[] from = new String[]{DBAdapter.KEY_NAME};
   
    
 // and an array of the fields we want to bind those fields to (in this case just text1)
    int[] to = new int[]{R.id.client1};
   

 // Now create a simple cursor adapter and set it to display
    SimpleCursorAdapter clientstable = 
        new SimpleCursorAdapter(this, R.layout.clients_row, clientsCursor, from, to);
     setListAdapter(clientstable);
     
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_insert);
        return true;
       
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case INSERT_ID:
            
            	
                createClient();
                return true;
            
        }
        return super.onMenuItemSelected(featureId, item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
        menu.add(1, DEPOSIT_ID, 1, R.string.menu_deposit);
        menu.add(2, WITHDRAW_ID, 2, R.string.menu_withdraw);
        
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case DELETE_ID:
                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
                mDbHelper.deleteClient(info.id);
                fillData();
                return true;
                
      
       
         case DEPOSIT_ID:
        	 
        	
        	
        	 
        	 AlertDialog.Builder alert = new AlertDialog.Builder(this);

        	 alert.setTitle("Deposit");
        	 alert.setMessage("Enter Amount to Deposit");
        	 
        	 
        	 
        	 // Set an EditText view to get user input 
        	 final EditText input = new EditText(this);
        	 alert.setView(input);

        	 alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {
        	 public void onClick(DialogInterface dialog, int whichButton) {
        	   String value = input.getText().toString();
        	   //parsed amount from long to string for compatibility issues with .getText
        	   Long Amount = Long.parseLong(value);
        	  mDbHelper.updateDepositTransactions(clientname, Amount);
        	  Toast.makeText(getApplicationContext(), "Deposited "+input.getText().toString(), Toast.LENGTH_LONG).show();
        	 
        	  
        	   // Do something with value and ok button
        	   
        	   }
        	 });

        	 alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        	   public void onClick(DialogInterface dialog, int whichButton) {
        	     // Canceled.
        		   
                       finish();
        	   }
        	 });

        	 alert.show();
        }       
                   return super.onContextItemSelected(item);         
    }       
    private void createClient() {
        Intent i = new Intent(this, ClientEdit.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long _id) {
        super.onListItemClick(l, v, position, _id);
        TextView name = (TextView)v; 
        
        Toast.makeText(getApplicationContext(), "item selected "+name.getText().toString(), Toast.LENGTH_LONG).show();
    
        Intent i = new Intent(this, ClientEdit.class);
        i.putExtra(DBAdapter.KEY_CLIENTID, _id);
        startActivityForResult(i, ACTIVITY_EDIT);
        
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
       

    }
       
       
       
        
       
      
   
   


  
    //calls the options menu layout(non-Javadoc)
   /* @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
   */
  /* @Override
   public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater myMenuInflater = getMenuInflater();
    myMenuInflater.inflate(R.menu.menu, menu);
       return true;
   }

   // the layout of the options menu as seen in the menu.xml file
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
*/
   
}

