package com.android.ideos;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class DepositWithdrawalActivity extends Mscreen1{
	
    private Button kCancel_button;
	@Override  
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depositwithdrawal); 
        
       
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