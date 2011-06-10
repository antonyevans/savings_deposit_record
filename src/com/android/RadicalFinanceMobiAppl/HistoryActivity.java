package com.android.RadicalFinanceMobiAppl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class HistoryActivity extends Mscreen1{
	//declaring the views for the screen	
	private Button btnClients;
    private Button btnHistory;
    private Button btnBank;
    private Button btnAllTransactions;
    private Button btnBank1;
    private Button btnToday;
    private Button btnByClient;
   
    
    
    //String clientname;
    //String value;
    private DBAdapter mDbHelper;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      // set the activity layout
        setContentView(R.layout.history);
        
        mDbHelper = new DBAdapter(this);
        mDbHelper.open();
       
        
       // showHistory();
        
       
        
       /**
        Cursor c = mDbHelper.getClientNameANDAmount();
       if (c.moveToFirst())        
            DisplayHistory(c);
        else
            Toast.makeText(this, "No history yet", 
            	Toast.LENGTH_LONG).show();
        	//setContentView(R.layout.historylist);
        	
        mDbHelper.close();
       **/
        
        btnClients = (Button)findViewById(R.id.Clients_button);
        btnHistory = (Button)findViewById(R.id.History_button);
        btnBank = (Button)findViewById(R.id.Bank_button);
        btnAllTransactions = (Button)findViewById(R.id.AllTransactions_button);
        btnBank1 = (Button)findViewById(R.id.ByBank_button);
        btnToday = (Button)findViewById(R.id.Today_button);
        btnByClient = (Button)findViewById(R.id.ByClient_button);
        
        
      //Set the events(action listeners)
        btnClients.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent l = new Intent(HistoryActivity.this,ClientsActivity.class);
    			startActivity(l); 
        	}
	});
           
        
        
            btnHistory.setOnClickListener(new OnClickListener()
            {
        	@Override
        	public void onClick(View v){
        		//calls itself
        		Intent k = new Intent(HistoryActivity.this,HistoryActivity.class);
    			startActivity(k); 
        		
    			 
        		
        	}
        });
        
        btnBank.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		//Calls the BankActivity
        		Intent q = new Intent(HistoryActivity.this,BankActivity.class);
    			startActivity(q); 
        	}
        	});
        btnAllTransactions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        btnBank1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        btnToday.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        btnByClient.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent q = new Intent(HistoryActivity.this,ShowHistoryActivity.class);
    			startActivity(q); 
				
			}
        /*	
        class ShowHistoryOfClients extends ListActivity{
        	
			//public void onClick(View arg0) {
				
				//Intent showhisto = new Intent(HistoryActivity.this,YesSearchClient.class);
    			//startActivity(showhisto);
				public void  filldata() {
			        Cursor historyCursor = mDbHelper.getClientTransactions();
			        startManagingCursor(historyCursor);
			        
			    
			 // Create an array to specify the fields we want to display in the list (name and Surname)
			  
			    String[] fromColumns = new String[]{DBAdapter.KEY_NAME, DBAdapter.KEY_TYPE, DBAdapter.KEY_DATETIME, DBAdapter.KEY_AMOUNT};
			   
			    
			 // and an array of the fields we want to bind those fields to (in this case just text1)
			    int[] toLayoutIDs = new int[]{R.id.client1, R.id.client2, R.id.client3, R.id.client4};
			  

			 // Now create a simple cursor adapter and set it to display
			    SimpleCursorAdapter TransactionsTable = new SimpleCursorAdapter(this, R.layout.clients_row, historyCursor, fromColumns, toLayoutIDs);
			     setListAdapter(TransactionsTable);
    			
			
		
    
  
        };
			
  */
       });

    }
    }    	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
	
        
    






   

