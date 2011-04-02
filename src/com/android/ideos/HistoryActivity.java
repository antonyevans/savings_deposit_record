package com.android.ideos;

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
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      // set the activity layout
        setContentView(R.layout.history);
        
        btnClients = (Button)findViewById(R.id.Clients_button);
        btnHistory = (Button)findViewById(R.id.History_button);
        btnBank = (Button)findViewById(R.id.Bank_button);
        
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

}
}    
