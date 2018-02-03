package com.example.macbookair.myapplication2.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.macbookair.myapplication2.Models.Person;
import com.example.macbookair.myapplication2.Databse.PersonDatabase;
import com.example.macbookair.myapplication2.R;
import com.example.macbookair.myapplication2.Adapters.RecyclerViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ImageButton addButton;
    private static RecyclerViewAdapter adapter;
    private PersonDatabase db;
    private List<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        addButton = findViewById(R.id.addButton);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Contacts");
        toolbar.setTitleTextAppearance(this, R.style.toolbarStyle);
        toolbar.setTitleMarginStart(270);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        people = db.personDao().getAllData();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddContactActivity.class));
            }
        });


        adapter = new RecyclerViewAdapter(this, getPersonList());
        recyclerView.setAdapter(adapter);

    }

    public List<Person> getPersonList() {
        return people;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
