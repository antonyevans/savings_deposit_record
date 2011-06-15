package com.android.RadicalFinanceMobiAppl;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ClientsActivity extends ListActivity {
	private static final int INSERT_ID = Menu.FIRST;
	private static final int EDIT_ID = Menu.FIRST + 1;
    private static final int DELETE_ID = Menu.FIRST + 2;
    private static final int DEPOSIT_ID = Menu.FIRST + 3;
    private static final int WITHDRAW_ID = Menu.FIRST + 4;
    private static final int HISTORY_ID = Menu.FIRST + 5;
    private static final int SEARCH_ID = Menu.FIRST + 6;
	
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    public static String nm = "null";
    private Long rowid;
    String Name;
  
	
    
    
protected DBAdapter db;
	
	//protected ListAdapter adapter;
    private DBAdapter mDbHelper;
    private Button btnHistory;
    private Button btnBank;
	
	
	//private static final String TAGG = "ClientsActivity";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients);
        btnHistory = (Button)findViewById(R.id.History_button);
        btnBank = (Button)findViewById(R.id.Bank_button);
        
        btnHistory.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		//Calls the next HistoryActivity 
        		Intent k = new Intent(ClientsActivity.this,HistoryActivity.class);
    			startActivity(k);  
               
        		}
        	});
        btnBank.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		//Calls the next BankActivity 
        		Intent n = new Intent(ClientsActivity.this,BankActivity.class);
    			startActivity(n);  
        	  }
        });
        
        mDbHelper = new DBAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
        
        rowid = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(DBAdapter.KEY_TRANSID);
		if (   rowid == null) {
			Bundle extras = getIntent().getExtras();
			   rowid = extras != null ? extras.getLong(DBAdapter.KEY_TRANSID): null;
			   
			  }
		//populatefield ();
    }
    
    private void fillData() {
        Cursor clientsCursor = mDbHelper.getAllClients();
        startManagingCursor(clientsCursor);
        
    
 // Create an array to specify the fields we want to display in the list (name, Surname, mobile)
  
    String[] fromColumns = new String[]{DBAdapter.KEY_NAME, DBAdapter.KEY_SURNAME, DBAdapter.KEY_MOBILE};
   
    
 // and an array of the fields we want to bind those fields to (in this case just text1,2,3)
    int[] toLayoutIDs = new int[]{R.id.client1, R.id.client2, R.id.client3};
  

 // Now create a simple cursor adapter and set it to display
    SimpleCursorAdapter clientstable = new SimpleCursorAdapter(this, R.layout.clients_row, clientsCursor, fromColumns, toLayoutIDs);
     setListAdapter(clientstable);
     
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_insert);
        menu.add(1, EDIT_ID, 1, R.string.menu_editclient);
        menu.add(2, DELETE_ID, 2, R.string.menu_delete);
        menu.add(3, SEARCH_ID, 3, R.string.menu_search);
        

        return true;
       
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
        
            case INSERT_ID:
            	 createClient();
                 return true;
            case EDIT_ID:
            	
            	
            	 return true;
            case DELETE_ID:
            	mDbHelper.deleteClient(featureId);
            	 return true;
                 
            	
            case SEARCH_ID:
            	/*
            	Intent k = new Intent(ClientsActivity.this,YesSearchClient.class);
    			startActivity(k); 
    			*/
            	return true;
               
            
        }
        return super.onMenuItemSelected(featureId, item);
    }
   
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        
        menu.add(0, EDIT_ID, 0, R.string.menu_editclient);
        menu.add(1, DELETE_ID, 1, R.string.menu_delete);
        menu.add(2, DEPOSIT_ID, 2, R.string.menu_deposit);
        menu.add(3, WITHDRAW_ID, 3, R.string.menu_withdraw);
        menu.add(4, HISTORY_ID, 4, R.string.menu_history);
        
       
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        
        	case EDIT_ID:
        		
        		
        		
        	return true;
        
            case DELETE_ID:
                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
                mDbHelper.deleteClient(info.id);
                fillData();
                return true;
                
      
       
            case DEPOSIT_ID:
            	
	        	 AlertDialog.Builder alertDeposit = new AlertDialog.Builder(this);
	        	
	        	alertDeposit.setTitle("Deposit");
	        	alertDeposit.setMessage("Enter Amount to Deposit");
	        	
	        	 // Set an EditText view to get user input 
	       	 	final EditText input = new EditText(this);
	       	 	alertDeposit.setView(input);
       	 

		       	 alertDeposit.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		       	 public void onClick(DialogInterface dialog, int whichButton) {
		       	 String value = input.getText().toString();
		       	 
		       	   //parsed amount from long to string for compatibility issues with .getText
		       	 Long Amount = Long.parseLong(value);
		       	   // inserting data into database
		       	
		       	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		         Date date = new Date();
		         
       	   
       	  if (rowid == null) {
              long _id = mDbHelper.insertClientTransaction("Samuel", "Deposit", dateFormat.format(date), Amount);
             if (_id > 0) {
             rowid = _id;
              }
          } 
      else {
              mDbHelper.updateDepositandWithdrawTransactions("Samuel", "Deposit", dateFormat.format(date), Amount); 
          }
         
       	
       	  
       	   // mDbHelper.close();
       	  
       	  Toast.makeText(getApplicationContext(), "You Deposited "+input.getText().toString(), Toast.LENGTH_LONG).show();
       // Do something with value and ok button
   	   
  	   }
  	 });

  	 alertDeposit.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
  	   public void onClick(DialogInterface dialog, int whichButton) {
  	     // Canceled.
  		   
                 finish();
  	   }
  	 });

  	 alertDeposit.show();
  	 	return true;
  	 		
            case WITHDRAW_ID:
            	
            	AlertDialog.Builder alertWithdraw = new AlertDialog.Builder(this);
             	 
         		 alertWithdraw.setTitle("Withdraw");
         		 alertWithdraw.setMessage("Enter Amount to Withdraw");
         		 
         		 // Set an EditText view to get user input 
             	 final EditText output = new EditText(this);
             	 alertWithdraw.setView(output);
             	 
             	 alertWithdraw.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 	 public void onClick(DialogInterface dialog, int whichButton) {
                 		 
                 		String value = output.getText().toString();
                 		
       		       	 
     		       	   //parsed amount from long to string for compatibility issues with .getText
     		       	 		Long Amount = Long.parseLong(value);
                 		 
     		       	 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
     		       	 		
     		       	 		Date date = new Date();
     		       	 		
     		       	 		
     		       	 	Toast.makeText(getApplicationContext(), "You have withdrawn "+output.getText().toString(), Toast.LENGTH_LONG).show();
                 	 }
             	 });
             	 alertWithdraw.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               	   public void onClick(DialogInterface dialog, int whichButton) {
               	     // Canceled.
               		   
                              finish();
               	   }
               	 });
             	 alertWithdraw.show();
             	 	return true;
             	 
             	 
            	case HISTORY_ID:
            		/*
            		Intent showhisto = new Intent(ClientsActivity.this,ShowHistoryActivity.class);
        			startActivity(showhisto);
        			*/
            		return true;

        	 
        }
        return super.onContextItemSelected(item);         
               
        } 
      
        
    
    private void createClient() {
        Intent i = new Intent(this, ClientEdit.class);
        startActivityForResult(i, ACTIVITY_CREATE);
       
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long _id) {
        super.onListItemClick(l, v, position, _id);
        
       TextView name = (TextView)v;
        
        
     //nm = name.getText().toString();
      
       
      
       
       
      Toast.makeText(getApplicationContext(), "item selected "+name.getText().toString(), Toast.LENGTH_LONG).show();
        
        Intent i = new Intent(this, ClientEdit.class);
        i.putExtra(DBAdapter.KEY_CLIENTID, _id);
        startActivityForResult(i, ACTIVITY_EDIT);
        
    }
  //  @Override
   // public string onListItemClick(ListView l, View v, int position, long _id) {
    //	super.onListItemClick(l, v, position, _id);
        
      // TextView name = (TextView)v;
              
       //return nm = name.getText().toString();
      
    //}

    
    
   
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
       

    }


  
    
   
}


