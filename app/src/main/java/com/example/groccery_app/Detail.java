package com.example.groccery_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        id = i.getIntExtra("ID",0);
        ListsDatabaseManager db = new ListsDatabaseManager(this);
        ListItem list = db.getListIem(id);
        getSupportActionBar().setTitle(list.get_name());
//        TextView details = findViewById(R.id.itemName);
//        details.setText(list.get_name());
    }
}