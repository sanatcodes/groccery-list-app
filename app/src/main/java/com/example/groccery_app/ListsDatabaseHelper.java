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
    public static final String TABLE_LISTS = "Lists";
    public static final String KEY_ID = "ListId";
    public static final String KEY_TITLE = "title";

    //second Table
    public static final String TABLE_LIST_ITEMS = "list_items";
    public static final String KEY_PRODUCT_ID = "product_id";
    public static final String KEY_PRODUCT_NAME = "product_name";
    public static final String KEY_BUY_STATUS = "buy_status";
    public static final String KEY_PRODUCT_LID = "product_listId";


    public ListsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LISTS_TABLE = "CREATE TABLE " + TABLE_LISTS + "("
                + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PRODUCT_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_LISTS_TABLE);

        String CREATE_LIST_ITEMS_TABLE = "CREATE TABLE " + TABLE_LIST_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT, "
                + KEY_BUY_STATUS + " TEXT, " + KEY_PRODUCT_LID + " TEXT"
                + ")";
        db.execSQL(CREATE_LIST_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS + TABLE_LIST_ITEMS);

        // Create tables again
        onCreate(db);
    }
}
