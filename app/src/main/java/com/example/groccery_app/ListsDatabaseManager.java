
package com.example.groccery_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.groccery_app.ListsDatabaseHelper.TABLE_LIST_ITEMS;
import static com.example.groccery_app.ListsDatabaseHelper.KEY_ID;
import static com.example.groccery_app.ListsDatabaseHelper.KEY_PRODUCT_NAME;
import static com.example.groccery_app.ListsDatabaseHelper.KEY_BUY_STATUS;

import java.util.ArrayList;
import java.util.List;


/*****************************************************************************************
 *
 *     This is a custom class to store CRUD operations for tasks database
 *     Note we've  added an open() method to instantiate the helper class, and make a call to
 *     use the database (getWriteableDatabase).
 *
 *******************************************************************************************/



public class ListsDatabaseManager
{
    Context context;
    private ListsDatabaseHelper ListdbHelper;
    private SQLiteDatabase database;


    public ListsDatabaseManager(Context context)
    {
        this.context = context;

    }

    public ListsDatabaseManager openw() throws SQLException {
        ListdbHelper = new ListsDatabaseHelper(context);
        database = ListdbHelper.getWritableDatabase();
        return this;
    }

    public ListsDatabaseManager openr() throws SQLException {
        ListdbHelper = new ListsDatabaseHelper(context);
        database = ListdbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        ListdbHelper.close();
    }

    // add the new item in task
    long addListItem(ListItem listItem) {
        openw();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, listItem.get_name()); // item Name
        values.put(KEY_BUY_STATUS, listItem.get_bought()); // Item buy status
        // Inserting Row
        long ID = database.insert(TABLE_LIST_ITEMS, null, values);
        return ID;
        //2nd argument is String containing nullColumnHack

    }


    ListItem getListIem(int id) {
        openr();


        Cursor cursor = database.query(TABLE_LIST_ITEMS, new String[] { KEY_ID,
                        KEY_PRODUCT_NAME, KEY_BUY_STATUS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return new ListItem(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2));

    }

    // code to get all items in a list view
    public List<ListItem> getAllItems() {
        openr();
        //make a new array list
        List<ListItem> allItems = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LIST_ITEMS;

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ListItem item = new ListItem();
                item.setID(Integer.parseInt(cursor.getString(0)));
                item.set_name(cursor.getString(1));
                item.set_bought(cursor.getString(2));
                allItems.add(item);
            }while (cursor.moveToNext());
        }


        return allItems;
    }

    // code to update the single task
    public int updateItem(ListItem listItem) {

        openw();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, listItem.get_name());
        values.put(KEY_BUY_STATUS, listItem.get_bought());

        // updating row
        return database.update(TABLE_LIST_ITEMS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(listItem.getID()) });
    }

    // Deleting single item
    public void deleteTask(ListItem listItem) {

        openw();

        database.delete(TABLE_LIST_ITEMS, KEY_ID + " = ?",
                new String[] { String.valueOf(listItem.getID()) });
        database.close();
    }

    // Getting item Count
    public int getItemCount() {
        openr();
        String countQuery = "SELECT  * FROM " + TABLE_LIST_ITEMS;
        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
