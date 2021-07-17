package com.appdevelopersumankr.suman_kumar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.view.View;

public class MainActivity extends AppCompatActivity {
    AppCompatButton login, register;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is null
        if (firebaseUser != null){
            Intent intent = new Intent (MainActivity.this, Main_two.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );


        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener ( new android.view.View.OnClickListener () {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        } );

       register.setOnClickListener ( new android.view.View.OnClickListener () {
           @Override
           public void onClick(android.view.View v) {
               startActivity(new Intent(MainActivity.this, RegisterActivity.class));
           }
       } );


    }
}