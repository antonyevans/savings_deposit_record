
package com.android.ideos;

/**
 * @author G Sam
 *
 */
import android.R.Phone number;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter {
	//defining constants
	public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_MOBILE= "mobile";    
    private static final String TAG = "DBAdapter";
 
    private static final String DATABASE_NAME = "radicalfinance";
    private static final String DATABASE_TABLE = "clientstable";
    private static final int DATABASE_VERSION = 1;
 
    private static final String DATABASE_CREATE =
        "create table clientstable (_id Phone number primary key autoincrement, "
        + "name text not null, surname text not null, " 
        + "mobile Phone number not null);";
 
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
            db.execSQL(DATABASE_CREATE);
        }
 
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS surname");
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
    public long insertTitle(String name, String surname, Phone number mobile) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_SURNAME, surname);
        initialValues.put(KEY_MOBILE, mobile);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
 
    //---deletes a particular title---
    public boolean deleteTitle(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
 
    //---retrieves all the title---
    public Cursor getAllTitles() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_ROWID, 
        		KEY_NAME,
        		KEY_SURNAME,
                KEY_MOBILE}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }
 
    //---retrieves a particular title---
    public Cursor getTitle(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_NAME, 
                		KEY_SURNAME,
                		KEY_MOBILE
                		}, 
                		KEY_ROWID + "=" + rowId, 
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
 
    //---updates a title---
    public boolean updateTitle(long rowId, String name, 
    String surname, String mobile) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_SURNAME, surname);
        args.put(KEY_MOBILE, mobile);
        return db.update(DATABASE_TABLE, args, 
                         KEY_ROWID + "=" + rowId, null) > 0;
    }
}