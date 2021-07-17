package com.appdevelopersumankr.suman_kumar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash_screen );

        new Handler ().postDelayed( new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(Splash_screen.this, MainActivity.class);
                Splash_screen.this.startActivity(mainIntent);
                Splash_screen.this.finish();
            }
        }, 2000);
    }
}