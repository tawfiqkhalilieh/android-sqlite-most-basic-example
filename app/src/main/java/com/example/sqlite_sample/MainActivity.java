package com.example.sqlite_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button fetchAllButton;
    Button insertUserButton;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchAllButton = findViewById(R.id.fechAll);
        insertUserButton = findViewById(R.id.insertUser);

        dbHandler = new DBHandler(MainActivity.this);

        fetchAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<HashMap<String, String>> users = dbHandler.getAllUsers();
                    // show simple toast to make sure it works
                    Toast.makeText(MainActivity.this, users.get(0).get("username"), Toast.LENGTH_SHORT).show();
                } catch ( Exception e ) {
                    Toast.makeText(MainActivity.this, "An error occurred while fetching users from the database", Toast.LENGTH_SHORT).show();
                }

            }
        });

        insertUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "insert user activated", Toast.LENGTH_SHORT).show();

                try {
                    dbHandler.insertUser(
                            "1", "taw", 17, "super5ateerbatata"
                    );
                    Toast.makeText(MainActivity.this, "User Inserted Successfully", Toast.LENGTH_SHORT).show();
                } catch ( Exception e ) {
                    Toast.makeText(MainActivity.this, "An error occurred while inserting the user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}