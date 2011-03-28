package com.android.ideos;
// A class to store the data layout in the data base.
 import static android.provider.BaseColumns._ID;

 import static com.android.ideos.Constants.TABLE_NAME;
 
 import static com.android.ideos.Constants.TITLE;
 import android.content.Context;
 import android.database.sqlite.SQLiteDatabase;
 import android.database.sqlite.SQLiteOpenHelper;

 public class ClientsData extends SQLiteOpenHelper {
 private static final String DATABASE_NAME = "clients.db" ;
 private static final int DATABASE_VERSION = 1;
/** Create a helper object for the Clients database */
 
 public ClientsData(Context ctx) {
 super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
 }

 @Override
 public void onCreate(SQLiteDatabase db) {
 db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID
 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT NOT NULL);" );
 }

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion,
 int newVersion) {
 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
 onCreate(db);
 }
 }