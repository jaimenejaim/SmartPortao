package com.android.smartportao.splashscreen;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


import com.android.smartportao.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_splash_screen);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
            }, 2000);

        }
}
