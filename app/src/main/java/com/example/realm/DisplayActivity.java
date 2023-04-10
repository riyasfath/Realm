package com.example.realm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class DisplayActivity extends AppCompatActivity {

    List<DataModel> dataModelList;
    Realm realm;
    RecyclerView recyclerView;
    RecyclerviewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.recyclerView_course);

        dataModelList = new ArrayList<>();



        dataModelList = realm.where(DataModel.class).findAll();

        recyclerViewAdapter = new RecyclerviewAdapter(dataModelList,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);
    }
}