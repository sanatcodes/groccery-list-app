package com.example.groccery_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Grid extends AppCompatActivity {

    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_grid);


        dataList = findViewById(R.id.dataList);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth  Item");


        images.add(R.drawable.ic_baseline_android_24);
        images.add(R.drawable.ic_baseline_android_24);
        images.add(R.drawable.ic_baseline_android_24);
        images.add(R.drawable.ic_baseline_android_24);


        adapter = new Adapter(this, titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);

    }
}