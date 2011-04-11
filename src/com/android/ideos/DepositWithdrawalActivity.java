package com.android.ideos;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class DepositWithdrawalActivity extends Mscreen1{
	
	private Button kSet_button;
    private Button kCancel_button;
	@Override  
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depositwithdrawal); 
        
        kSet_button = (Button)findViewById(R.id.Set_button);
        
        kSet_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		
        	
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