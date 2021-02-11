package com.example.birdbreeder.View.ui.Birds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;

import com.example.birdbreeder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewMatingActivity extends AppCompatActivity {
    private FloatingActionButton fab ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mating);
        fab = findViewById(R.id.new_egg_fb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AlertDialog.Builder builder = new  AlertDialog.Builder(getApplicationContext());
                builder.setView(R.layout.dialog_new_egg);
                builder.show();
            }
        });
    }
}