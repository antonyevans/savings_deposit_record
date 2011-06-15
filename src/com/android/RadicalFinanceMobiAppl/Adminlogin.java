package com.android.RadicalFinanceMobiAppl;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Adminlogin extends Mscreen1{
	private EditText NAMEText1;
	private EditText SURNAMEText1;
    private EditText MOBILEText;
    private EditText PASSKEYText;
    private Long rowid;
    
	private DBAdapter mDbHelper;
	
	
	 @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         mDbHelper = new DBAdapter(this);
         mDbHelper.open();
         
         setContentView(R.layout.adminlogin);
         
         NAMEText1 = (EditText) findViewById(R.id.name);
         SURNAMEText1 = (EditText) findViewById(R.id.surname);
         MOBILEText = (EditText) findViewById(R.id.mobile);
         PASSKEYText = (EditText) findViewById(R.id.passkey);
         Button registerButton = (Button) findViewById(R.id.register);
         
         rowid = (savedInstanceState == null) ? null :
             (Long) savedInstanceState.getSerializable(DBAdapter.KEY_AGENTID);
 		if (   rowid == null) {
 			Bundle extras = getIntent().getExtras();
 			   rowid = extras != null ? extras.getLong(DBAdapter.KEY_AGENTID): null;
 		}
 		populateAgentFields();
 		
         
         registerButton.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
                 setResult(RESULT_OK);
                 finish();
                 
             }
         });
         
   }
	 public void populateAgentFields() {
	        if (   rowid != null) {
	            Cursor admin = mDbHelper.getAgent(rowid);
	            startManagingCursor(admin);
	            NAMEText1.setText(admin.getString(
	            		admin.getColumnIndexOrThrow(DBAdapter.KEY_NAME)));
	            SURNAMEText1.setText(admin.getString(
	            		admin.getColumnIndexOrThrow(DBAdapter.KEY_SURNAME)));
	            MOBILEText.setText(admin.getString(
	            		admin.getColumnIndexOrThrow(DBAdapter.KEY_MOBILE)));
	            PASSKEYText.setText(admin.getString(
	            		admin.getColumnIndexOrThrow(DBAdapter.KEY_AGENTPASSKEY)));
	        }           
}
	  @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        saveState();
	        outState.putSerializable(DBAdapter.KEY_AGENTID,  rowid);
	    }
	    @Override
	    protected void onPause() {
	        super.onPause();
	        saveState();
	    }
	    @Override
	    protected void onResume() {
	        super.onResume();
	        populateAgentFields();
	    }
	    private void saveState() {
	        String name = NAMEText1.getText().toString();
	        String surname = SURNAMEText1.getText().toString();
	        String mobile = MOBILEText.getText().toString();
	        String passkey = PASSKEYText.getText().toString();
	        
	        

	        if (rowid == null) {
	            long _id = mDbHelper.insertAgent(name, surname, mobile, passkey);
	           if (_id > 0) {
	           rowid = _id;
	            }
	        } 
	    else {
	            mDbHelper.updateAgent(rowid, name, surname, mobile, passkey); 
	            
	            
	        }
	       
	    }
} 