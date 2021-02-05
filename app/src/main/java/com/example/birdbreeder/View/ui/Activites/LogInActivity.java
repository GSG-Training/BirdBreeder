package com.example.birdbreeder.View.ui.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.birdbreeder.R;
import com.example.birdbreeder.View.ui.Activites.MainActivity.MainActivity;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    Button signIn ;
    TextView signUp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        signIn = findViewById(R.id.sign_in_but);
        signUp = findViewById(R.id.sign_up_tv);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_but :
                startActivity(new Intent(this , MainActivity.class));
                break;
            case R.id.sign_up_tv :
                startActivity(new Intent(this , SignUpActivity.class));
                break;
        }

    }
}