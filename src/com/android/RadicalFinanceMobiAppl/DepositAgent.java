package com.android.RadicalFinanceMobiAppl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

public class DepositAgent extends Mscreen1 {
	
	String AgentName;
	//private Long rowid;
	private DBAdapter mDbHelper;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      // set the activity layout
        //setContentView(R.layout.history);
	
	//}
	
	//private void displ() {
		// TODO Auto-generated method stub

	
	AlertDialog.Builder alert = new AlertDialog.Builder(this);

	 alert.setTitle("Agent Deposit");
	 alert.setMessage("Enter Amount to Deposit");
	 
	 
	 
	 // Set an EditText view to get user input 
	 final EditText input = new EditText(this);
	 alert.setView(input);

	 alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {
		 
	 public void onClick(final DialogInterface dialog, final int whichButton) {
	   final String value = input.getText().toString();
	   //parsed amount from long to string for compatibility issues with .getText
	   
	   final Long Amount = Long.parseLong(value);
	   
	  mDbHelper.updateAgentDepositTransactions(null, Amount);
	 // Insert into tableX  (mytime) values(‘NOW()’); 

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
	

                
              
  