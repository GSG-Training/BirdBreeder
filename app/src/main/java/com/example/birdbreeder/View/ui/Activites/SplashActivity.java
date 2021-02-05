package com.example.birdbreeder.View.ui.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.birdbreeder.R;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    Button signIn ;
    TextView signUp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        signIn = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button :
                startActivity(new Intent(this , LogInActivity.class));
                break;
                
            case R.id.sign_up :
                startActivity(new Intent(this , SignUpActivity.class));
                break;
        }
    }
}