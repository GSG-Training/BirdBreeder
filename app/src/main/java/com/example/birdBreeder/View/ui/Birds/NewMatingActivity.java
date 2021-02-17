package com.example.birdBreeder.View.ui.Birds;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.birdBreeder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewMatingActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton fab;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mating);
        fab = findViewById(R.id.new_egg_fb);
        fab.setOnClickListener(this);
        button = findViewById(R.id.mating_edit_details);
        button.setOnClickListener(this);
    }

    private void showEggDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_new_egg);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_edit_mating);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_egg_fb:
                showEggDialog();
                break;
            case R.id.mating_edit_details:
                showEditDialog();
                break;
        }
    }
}