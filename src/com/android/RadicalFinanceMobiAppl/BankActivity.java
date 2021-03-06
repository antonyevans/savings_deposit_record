package com.android.RadicalFinanceMobiAppl;

import com.android.RadicalFinanceMobiAppl.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BankActivity extends Mscreen1{
//declaring the views for the screen	
	private Button btnClients;
    private Button btnHistory;
    private Button btnBank;
    private Button btnDeposit;
    private Button btnWithdrawal;
    private Button btnhistory;
    String AgentName;
	//private DBAdapter mDbHelper;
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      // set the activity layout
        setContentView(R.layout.bank);
        
        btnClients = (Button)findViewById(R.id.Clients_button);
        btnHistory = (Button)findViewById(R.id.History_button);
        btnBank = (Button)findViewById(R.id.Bank_button);
        btnDeposit = (Button)findViewById(R.id.deposit_button);
        btnWithdrawal = (Button)findViewById(R.id.withdrawal_button);
        btnhistory = (Button)findViewById(R.id.history_button);
        
      //Set the events(action listeners)
        btnClients.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent l = new Intent(BankActivity.this,ClientsActivity.class);
    			startActivity(l); 
        	}
	});
        
        btnHistory.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent k = new Intent(BankActivity.this,HistoryActivity.class);
    			startActivity(k); 
        		
        	}
        });
        
        btnBank.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		//Calls itself thus remains in the same screen upon clicking it
        		Intent q = new Intent(BankActivity.this,BankActivity.class);
    			startActivity(q); 
        	}
        	});
        //should call an alert dialogue
        btnDeposit.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		//calls deposit agent amount
        		Intent i = new Intent(BankActivity.this,DepositAgent.class);
    			startActivity(i);

        		
        	}
        });
        
        
        
        //should call a content dialogue
        btnWithdrawal.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		 //insert code to do here
        		Intent i = new Intent(BankActivity.this,WithdrawAgent.class);
    			startActivity(i);
        	}
        });
        btnhistory.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent i = new Intent(BankActivity.this,HistoryActivity.class);
    			startActivity(i);	 
        	}
        });
	}
	}