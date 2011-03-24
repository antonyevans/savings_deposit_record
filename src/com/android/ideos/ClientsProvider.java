package com.android.ideos;

import static android.provider.BaseColumns._ID;

import static com.android.ideos.Constants.AUTHORITY;
import static com.android.ideos.Constants.CONTENT_URI;
import static com.android.ideos.Constants.TABLE_NAME;
import com.android.ideos.ClientsData;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class ClientsProvider extends ContentProvider
{
private static final int CLIENTS = 1;
private static final int CLIENTS_ID = 2;
/** The MIME type of a directory of clients */
private static final String CONTENT_TYPE
= "vnd.android.cursor.dir/vnd.android.client" ;
/** The MIME type of a single client */
private static final String CONTENT_ITEM_TYPE
= "vnd.android.cursor.item/vnd.android.client" ;
private ClientsData clients;
private UriMatcher uriMatcher;
// ...


@Override
public int delete(Uri uri, String selection, String[] selectionArgs) {
	// TODO Auto-generated method stub
	SQLiteDatabase db = clients.getWritableDatabase();
    int count;
    switch (uriMatcher.match(uri)) {
    case CLIENTS:
       count = db.delete(TABLE_NAME, selection, selectionArgs);
       break;
    case CLIENTS_ID:
       long id = Long.parseLong(uri.getPathSegments().get(1));
       count = db.delete(TABLE_NAME, appendRowId(selection, id),
             selectionArgs);
       break;
    default:
       throw new IllegalArgumentException("Unknown URI " + uri);
    }

    // Notify any watchers of the change
    getContext().getContentResolver().notifyChange(uri, null);
    return count;
 }
	
@Override
public String getType(Uri uri) {
	// TODO Auto-generated method stub
	switch (uriMatcher.match(uri)) {
    case CLIENTS:
       return CONTENT_TYPE;
    case CLIENTS_ID:
       return CONTENT_ITEM_TYPE;
    default:
       throw new IllegalArgumentException("Unknown URI " + uri);
    }
 }


@Override
public Uri insert(Uri uri, ContentValues values) {
	// TODO Auto-generated method stub
	SQLiteDatabase db = clients.getWritableDatabase();

    // Validate the requested uri
    if (uriMatcher.match(uri) != CLIENTS) {
       throw new IllegalArgumentException("Unknown URI " + uri);
    }

    // Insert into database
    long id = db.insertOrThrow(TABLE_NAME, null, values);

    // Notify any watchers of the change
    Uri newUri = ContentUris.withAppendedId(CONTENT_URI, id);
    getContext().getContentResolver().notifyChange(newUri, null);
    return newUri;
 }
@Override

public boolean onCreate() {
	// TODO Auto-generated method stub
	uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    uriMatcher.addURI(AUTHORITY, "clients", CLIENTS);
    uriMatcher.addURI(AUTHORITY, "clients/#", CLIENTS_ID);
    clients = new ClientsData(getContext());
	return false;
}
@Override
public Cursor query(Uri uri, String[] projection, String selection,
		String[] selectionArgs, String orderBy) {
	// TODO Auto-generated method stub
	if (uriMatcher.match(uri) == CLIENTS_ID) {
        long id = Long.parseLong(uri.getPathSegments().get(1));
        selection = appendRowId(selection, id);
     }

     // Get the database and run the query
     SQLiteDatabase db = clients.getReadableDatabase();
     Cursor cursor = db.query(TABLE_NAME, projection, selection,
           selectionArgs, null, null, orderBy);

     // Tell the cursor what uri to watch, so it knows when its
     // source data changes
     cursor.setNotificationUri(getContext().getContentResolver(),
           uri);
     return cursor;
  }
	

@Override
public int update(Uri uri, ContentValues values, String selection,
		String[] selectionArgs) {
	// TODO Auto-generated method stub
	SQLiteDatabase db = clients.getWritableDatabase();
    int count;
    switch (uriMatcher.match(uri)) {
    case CLIENTS:
       count = db.update(TABLE_NAME, values, selection,
             selectionArgs);
       break;
    case CLIENTS_ID:
       long id = Long.parseLong(uri.getPathSegments().get(1));
       count = db.update(TABLE_NAME, values, appendRowId(
             selection, id), selectionArgs);
       break;
    default:
       throw new IllegalArgumentException("Unknown URI " + uri);
    }
 // Notify any watchers of the change
    getContext().getContentResolver().notifyChange(uri, null);
    return count;
}
    /** Append an id test to a SQL selection expression */
    private String appendRowId(String selection, long id) {
       return _ID + "=" + id
             + (!TextUtils.isEmpty(selection)
                   ? " AND (" + selection + ')'
                   : "");
    
}}
