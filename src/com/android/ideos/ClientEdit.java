package com.android.ideos;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClientEdit extends Mscreen1 {
	private EditText NAMEText;
	private EditText SURNAMEText;
    private EditText MOBILEText;
    private Long rowid;
    private DBAdapter mDbHelper;
//    private EditText DEPOText;
  //  private Long rowidd;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mDbHelper = new DBAdapter(this);
            mDbHelper.open();
            
            setContentView(R.layout.client_edit);
        
        NAMEText = (EditText) findViewById(R.id.name);
        SURNAMEText = (EditText) findViewById(R.id.surname);
        MOBILEText = (EditText) findViewById(R.id.mobile);
        
       // DEPOText = (EditText) findViewById(R.id.depoAmount);
        Button doneButton = (Button) findViewById(R.id.done);
        

        rowid = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(DBAdapter.KEY_CLIENTID);
		if (   rowid == null) {
			Bundle extras = getIntent().getExtras();
			   rowid = extras != null ? extras.getLong(DBAdapter.KEY_CLIENTID): null;
		}
		populateFields();
		//populateFieldAMOUNT();

        doneButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
                
            }
        });
    }
    private void populateFields() {
        if (   rowid != null) {
            Cursor client = mDbHelper.getClient(rowid);
            startManagingCursor(client);
            NAMEText.setText(client.getString(
                    client.getColumnIndexOrThrow(DBAdapter.KEY_NAME)));
            SURNAMEText.setText(client.getString(
                    client.getColumnIndexOrThrow(DBAdapter.KEY_SURNAME)));
            MOBILEText.setText(client.getString(
                    client.getColumnIndexOrThrow(DBAdapter.KEY_MOBILE)));
        }           
}
    //    private void populateFieldAMOUNT() {
           // if (   rowidd != null) {
             //   Cursor clientdepo = mDbHelper.getClient(rowidd);
               // startManagingCursor(clientdepo);
                //DEPOText.setText(clientdepo.getString(
                  //      clientdepo.getColumnIndexOrThrow(DBAdapter.KEY_AMOUNT)));
        //}
        //}
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(DBAdapter.KEY_CLIENTID,  rowid);
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }
    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }
    private void saveState() {
        String name = NAMEText.getText().toString();
        String surname = SURNAMEText.getText().toString();
        String mobile = MOBILEText.getText().toString();
        

        if (rowid == null) {
            long _id = mDbHelper.insertClient(name, surname, mobile);
           if (_id > 0) {
           rowid = _id;
            }
        } 
    else {
            mDbHelper.updateClient(rowid, name, surname, mobile); 
        }
       
    }
    //closes the mDbHelper database
    //{
       // mDbHelper.close();
    //}
   /**
    private void saveDeposit() {
    	
    	String Amount = DEPOText.getText().toString();
    	
    if (rowidd == null){
    	long _id = mDbHelper.updateDepositTransactions(_id, Amount);
    	if (_id > 0) {
    		rowidd = _id;
    	}
    }
    else { 
    	mDbHelper.updateDepositTransactions(rowidd, Amount);
    }**/
    	
    }
