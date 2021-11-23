
package com.example.groccery_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.groccery_app.ListsDatabaseHelper.KEY_TITLE;
import static com.example.groccery_app.ListsDatabaseHelper.TABLE_LIST_ITEMS;
import static com.example.groccery_app.ListsDatabaseHelper.KEY_PRODUCT_ID;
import static com.example.groccery_app.ListsDatabaseHelper.KEY_PRODUCT_NAME;
import static com.example.groccery_app.ListsDatabaseHelper.KEY_BUY_STATUS;
import static com.example.groccery_app.ListsDatabaseHelper.KEY_PRODUCT_LID;



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

    public ListsDatabaseManager open() throws SQLException {
        ListdbHelper = new ListsDatabaseHelper(context);
        database = ListdbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ListdbHelper.close();
    }

    //todo:add CURD operations for groceryListDb table

    // add the new item in task
    void addTask(ListItem listItem) {

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, listItem.getItemId()); // Task Name
        values.put(KEY_PRODUCT_NAME, listItem.getName()); // Task Phone
        values.put(KEY_BUY_STATUS, listItem.getBought()); // Task Name
        values.put(KEY_PRODUCT_LID, listItem.getProductListId()); // Task Name

        // Inserting Row
        database.insert(TABLE_LIST_ITEMS, null, values);
        //2nd argument is String containing nullColumnHack

    }


    ListItem getListIem(int id) {

        Cursor cursor = database.query(TABLE_LIST_ITEMS, new String[] { KEY_PRODUCT_ID,
                        KEY_PRODUCT_NAME, KEY_BUY_STATUS, KEY_PRODUCT_LID }, KEY_PRODUCT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ListItem listItem = new ListItem(
                cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
        // return task
        return listItem;
    }

    // code to get all tasks in a list view
    public Cursor getAllItems() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LIST_ITEMS;

        Cursor itemlist = database.rawQuery(selectQuery, null);

        return itemlist;
    }

    // code to update the single task
    public int updateItem(ListItem listItem) {

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, listItem.getName());
        values.put(KEY_BUY_STATUS, listItem.getBought());
        values.put(KEY_PRODUCT_LID, listItem.getProductListId());

        // updating row
        return database.update(TABLE_LIST_ITEMS, values, KEY_PRODUCT_ID + " = ?",
                new String[] { String.valueOf(listItem.getItemId()) });
    }

    // Deleting single item
    public void deleteTask(ListItem listItem) {

        database.delete(TABLE_LIST_ITEMS, KEY_PRODUCT_ID + " = ?",
                new String[] { String.valueOf(listItem.getItemId()) });
        database.close();
    }

    // Getting item Count
    public int getItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LIST_ITEMS;
        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
