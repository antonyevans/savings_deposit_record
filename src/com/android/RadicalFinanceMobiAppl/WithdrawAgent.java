package com.android.RadicalFinanceMobiAppl;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

public class WithdrawAgent extends Mscreen1{

	private DBAdapter mDbHelper;
	private Long rowid;
	String AgentName ;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new DBAdapter(this);
        mDbHelper.open();
        
        rowid = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(DBAdapter.KEY_AGENTID);
		if (   rowid == null) {
			Bundle extras = getIntent().getExtras();
			   rowid = extras != null ? extras.getLong(DBAdapter.KEY_AGENTID): null;
			   
			  }
        
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
       
	   	 alert.setTitle("Agent Withdraw");
	   	 alert.setMessage("Enter Amount to withdraw");
	   	 
	  // Set an EditText view to get user input 
		 final EditText input = new EditText(this);
		 alert.setView(input);
	
		 alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			 
		public void onClick(final DialogInterface dialog, final int whichButton) {
		  final String value = input.getText().toString();
		  
		   //parsed amount from long to string for compatibility issues with .getText
		   final Long AmountWithdrawn = Long.parseLong(value);
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	       Date date = new Date();
	       
	   
		  mDbHelper.updateAgentTransactions("", "mobile", dateFormat.format(date), AmountWithdrawn);
		 
		  
		  

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
		
	public void close(){
		mDbHelper.close();
	}
		}
		

   	 