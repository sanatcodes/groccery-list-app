package com.example.groccery_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;



public class ListsDatabaseHelper extends SQLiteOpenHelper{


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "groceryListDb";
    public static final String TABLE_LIST_ITEMS = "list_items";
    public static final String KEY_ID = "_id";
    public static final String KEY_PRODUCT_NAME = "product_name";
    public static final String KEY_BUY_STATUS = "buy_status";


    public ListsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LIST_ITEMS_TABLE = "CREATE TABLE " + TABLE_LIST_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PRODUCT_NAME + " TEXT, "
                + KEY_BUY_STATUS + " TEXT"
                + ")";
        db.execSQL(CREATE_LIST_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS "  + TABLE_LIST_ITEMS);

        // Create tables again
        onCreate(db);
    }
}
