package com.example.doorsteptailors;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash__screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash__screen);
        Thread mythread =new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    Intent intent =new Intent(Splash_Screen.this,SignIn.class);
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();

    }
}
