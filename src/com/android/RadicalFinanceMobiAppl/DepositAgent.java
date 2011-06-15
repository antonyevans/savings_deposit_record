package com.android.RadicalFinanceMobiAppl;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;


public class DepositAgent extends Mscreen1 {
	
	String AgentName;
	private Long rowid;
	private DBAdapter mDbHelper;
	 public static String agentnumber;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincustom);
        mDbHelper = new DBAdapter(this);
        mDbHelper.open();
        
        rowid = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(DBAdapter.KEY_AGENTID);
		if (   rowid == null) {
			Bundle extras = getIntent().getExtras();
			   rowid = extras != null ? extras.getLong(DBAdapter.KEY_AGENTID): null;
			   
			  }
	

	
	
	
	AlertDialog.Builder alert = new AlertDialog.Builder(this);

	 alert.setTitle("Agent Deposit");
	 alert.setMessage("Enter Amount to Deposit");
	 
	 
	 
	 // Set an EditText view to get user input 
	 final EditText input = new EditText(this);
	 alert.setView(input);

	 alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		 
	 public void onClick(final DialogInterface dialog, final int whichButton) {
	   final String value = input.getText().toString();
	   //parsed amount from long to string for compatibility issues with .getText
	   
	   final Long Cash = Long.parseLong(value);
	   
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
       Date date = new Date();
       
       if (rowid == null) {
           long _id = mDbHelper.insertAgentDeposit("Guest", agentnumber(), dateFormat.format(date), Cash);
          if (_id > 0) {
          rowid = _id;
           }
       } 
       else {
               mDbHelper.updateAgentTransactions("Guest", agentnumber(), dateFormat.format(date), Cash); 
           }
	 
      
	  

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
	
	 public String agentnumber (){
		 String h="";
		AgentsDepositShow d=new AgentsDepositShow();
		h=d.fillmobilefromDB();
	 //h =  mDbHelper.getAgentTransactions();
      
		 
  	   return h;
     }
	  	 
	public void close(){
		mDbHelper.close();
	}
	
	}
	

                
              
  