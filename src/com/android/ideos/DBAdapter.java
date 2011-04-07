package com.android.ideos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author G Sam

 *
 */



public class DBAdapter {
	//defining columns(fields) in all 3 tables
	public static final String KEY_CLIENTID = "_id";
	public static final String KEY_TRANSID = "transId";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_MOBILE= "mobile"; 
    public static final String KEY_TYPE = "Type";
    public static final String KEY_DATETIME = "DateTime";
    public static final String KEY_AMOUNT = "Amount";
    public static final String KEY_BALANCE = "Balance";
    
    private static final String TAG = "DBAdapter";
 
    private static final String DATABASE_NAME = "radicalfinance";
    private static final String DATABASE_CLIENTSTable = "clientstable";
    private static final String DATABASE_TRANSACTIONS = "TransactionsTable";
    private static final String DATABASE_CLIENTSBALANCE = "ClientsBalanceTable";
    private static final int DATABASE_VERSION = 1;
 //Creating the database radicalfinance
    //CREATING CLIENTSTable
    private static final String DATABASE_CREATE_CLIENTSTABLE =
        "create table clientstable (_id integer primary key autoincrement, "
        + "name text not null, surname text not null, " 
        + "mobile integer not null);";
 //CREATING TransactionsTable    
    private static final String DATABASE_CREATE_TRANSACTIONSTABLE =
        "create table TransactionsTable (_id integer primary key autoincrement, "
    	+ "transId integer primary key autoincrement,"
        + "Type boolean not null, DateTime text not null, " 
        + "Amount long not null);";
 //CREATING ClientsBalanceTable
    private static final String DATABASE_CREATE_CLIENTSBALANCETABLE =
        "create table ClientsBalanceTable (_id integer primary key autoincrement, "
        + "Balance long not null); "; 
        
 
    private final Context context;  
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
 
    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
 
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
 
        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	db.execSQL(DATABASE_CREATE_CLIENTSTABLE);
            db.execSQL(DATABASE_CREATE_TRANSACTIONSTABLE);
            db.execSQL(DATABASE_CREATE_CLIENTSBALANCETABLE);
        }
 
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS clientstable");
            onCreate(db);
        }
    }    

	//methods for opening and closing the database, as well as the methods for adding/editing/deleting rows in the table.


 
   //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
 
    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
 
    //---insert a title into the database---

    public long insertTitle(String name, String surname, String mobile) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_SURNAME, surname);
        initialValues.put(KEY_MOBILE, mobile);
        return db.insert(DATABASE_CLIENTSTable, null, initialValues);
    }
    public long insertTitle(String transId, String Type, String DateTime, String Amount) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TRANSID, transId);
        initialValues.put(KEY_TYPE, Type);
        initialValues.put(KEY_DATETIME, DateTime);
        initialValues.put(KEY_AMOUNT, Amount);
        return db.insert(DATABASE_TRANSACTIONS, null, initialValues);
    }
    public long insertTitle(String Balance) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_BALANCE, Balance);
        return db.insert(DATABASE_CLIENTSBALANCE, null, initialValues);
    }
 
    //---deletes a particular title---
    public boolean deleteTitle(long clientId) 
    {
        return db.delete(DATABASE_CLIENTSTable,KEY_CLIENTID + "=" + clientId, null) > 0;
    }
 
    //---retrieves all the title---
    public Cursor getAllTitles() 
    {
        return db.query(DATABASE_CLIENTSTable, new String[] {
        		KEY_CLIENTID, 
        		KEY_NAME,
        		KEY_SURNAME,
        		KEY_MOBILE}, 
                null, 
                null, 
                null, 
                null, 
                null);
    
    }
    //querying TransactionsTable
    public Cursor getAllTransactionsRecords() {
    
        return db.query(DATABASE_TRANSACTIONS, new String[] {
        		KEY_TRANSID, 
        		KEY_TYPE,
        		KEY_DATETIME,
                KEY_AMOUNT}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }
        //querying clientsbalancetable
    //public Cursor getAllBalanceRecords() {
        
        //return db.query(DATABASE_CLIENTSBALANCE, new String[] {
        		//KEY_BALANCE}, 
        		//null, 
                //null);
    //}
 
    //---retrieves a particular title---
    public Cursor getTitle(long clientId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_CLIENTSTable, new String[] {
                		KEY_CLIENTID,
                		KEY_NAME, 
                		KEY_SURNAME,
                		KEY_MOBILE
                		}, 
                		KEY_CLIENTID + "=" + clientId, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
 
    //---updates a record---
    public boolean updateTitle(long clientId, String name, 
    String surname, String mobile) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_SURNAME, surname);
        args.put(KEY_MOBILE, mobile);
        return db.update(DATABASE_CLIENTSTable, args, 
                        KEY_CLIENTID + "=" + clientId, null) > 0;
    }
    public boolean updateTransactions(long clientId, long transId, String Type, 
    	    String DateTime, long Amount) 
    	    {
    	        ContentValues args = new ContentValues();
    	        args.put(KEY_TRANSID, transId);
    	        args.put(KEY_TYPE, Type);
    	        args.put(KEY_DATETIME, DateTime);
    	        args.put (KEY_AMOUNT, Amount);
    	        return db.update(DATABASE_TRANSACTIONS, args, 
    	                        KEY_CLIENTID + "=" + clientId, null) > 0;
    	    }
}
