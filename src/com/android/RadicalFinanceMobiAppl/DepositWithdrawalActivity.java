package com.android.RadicalFinanceMobiAppl;

import com.android.RadicalFinanceMobiAppl.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class DepositWithdrawalActivity extends Mscreen1{
	
	private Button kSet_button;
    private Button kCancel_button;
   // private  EditText input = new EditText(this);
    String Agentname;
   protected DBAdapter db;
  // private DBAdapter mDbHelper;
	@Override  
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depositwithdrawal); 
        
        kSet_button = (Button)findViewById(R.id.Set_button);
        
        
        kSet_button.setOnClickListener(new OnClickListener(){
        	
        	 @Override
        	public void onClick(View v){
        		 
        		//String value = input.getText().toString();
         	   //parsed amount from long to string for compatibility issues with .getText
         	 // Long Amount = Long.parseLong(value);
         	   
         	 // mDbHelper.updateDepositTransactions(Agentname, Amount);
         	  
         	 //Toast.makeText(getApplicationContext(), "You Deposited "+ input.getText().toString(), Toast.LENGTH_LONG).show();
         	 
         	  
         	   // Do something with value and set button
         	   
         	   }
         	 });
        		
        		
        
        
        
        kCancel_button = (Button)findViewById(R.id.Cancel_button);
        
        kCancel_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// Close the activity dialog
        		finish();
        	}
        	});
       
}
        }