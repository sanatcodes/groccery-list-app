package com.example.groccery_app;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Grid extends AppCompatActivity {

    RecyclerView dataList;
    Adapter adapter;
    Toolbar toolbar;
    TextView noItemsText;
    ListsDatabaseManager db;
    List<ListItem> allitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid);
        //code for toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialise db
        db = new ListsDatabaseManager(this);

        //create List of items
        allitems = db.getAllItems();

        //Declare Recycle View
        dataList = findViewById(R.id.dataList);

        //text for when list is empty
        noItemsText = findViewById(R.id.noItemsText);
        if(allitems.isEmpty()){
            noItemsText.setVisibility(View.VISIBLE);
        }else {
            noItemsText.setVisibility(View.GONE);
            displayList(allitems);
        }

    }

    private void displayList(List<ListItem> allitems) {

        adapter = new Adapter(this, allitems );
        dataList.setAdapter(adapter);
        dataList.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(dataList);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            Intent i = new Intent (this,addItem.class);
            startActivityForResult(i,1);

            Toast.makeText(this, "add btn is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<ListItem> getAllLists = db.getAllItems();
                    displayList(getAllLists);
    }


    @Override
    protected void onResume() {
        super.onResume();
        List<ListItem> getAllLists = db.getAllItems();
        if(getAllLists.isEmpty()){
            noItemsText.setVisibility(View.VISIBLE);
        }else {
            noItemsText.setVisibility(View.GONE);
            displayList(getAllLists);
        }


    }
}