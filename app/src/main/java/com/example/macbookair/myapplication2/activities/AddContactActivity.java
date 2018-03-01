package com.example.macbookair.myapplication2.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.macbookair.myapplication2.R;
import com.example.macbookair.myapplication2.database.PersonDatabase;
import com.example.macbookair.myapplication2.models.Person;

public class AddContactActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText txtFullName;
    private EditText txtCountry;
    private EditText txtSocial;
    private EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        saveButton = findViewById(R.id.saveButton_addContact);
        txtFullName = findViewById(R.id.full_name_add);
        txtCountry = findViewById(R.id.country_add);
        txtEmail = findViewById(R.id.email_add);
        txtSocial = findViewById(R.id.social_network_add);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PersonDatabase db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "production")
                        .allowMainThreadQueries()
                        .build();
                db.personDao().insertAll(new Person(txtFullName.getText().toString(),
                        0,
                        txtSocial.getText().toString(),
                        txtCountry.getText().toString(),
                        txtEmail.getText().toString()));
                startActivity(new Intent(AddContactActivity.this, MainActivity.class));
            }
        });
    }

}
