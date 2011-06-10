package com.android.RadicalFinanceMobiAppl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

public class WithdrawAgent extends Mscreen1{

	private DBAdapter mDbHelper;
	
		String AgentName ;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.setCustomTitle(getCurrentFocus());
   	 alert.setTitle("Agent Withdraw");
   	 alert.setMessage("Enter Amount to withdraw");
   	 
  // Set an EditText view to get user input 
	 final EditText input = new EditText(this);
	 alert.setView(input);

	 alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {
		 
	 public void onClick(final DialogInterface dialog, final int whichButton) {
	   final String value = input.getText().toString();
	   //parsed amount from long to string for compatibility issues with .getText
	   final Long Amount = Long.parseLong(value);
	   
		  mDbHelper.updateAgentDepositTransactions(AgentName, Amount);
		 
		  
		   mDbHelper.close();

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
		
		}
		

   	 