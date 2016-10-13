package com.android.smartportao;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.smartportao.util.navigationview.CustomTypefaceSpan;

public class SettingsUserActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowFlag(SettingsUserActivity.this, WindowManager.LayoutParams.FLAG_FULLSCREEN, true);
        setContentView(R.layout.activity_settings_user);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_user_settings_title);
        Typeface raleway=Typeface.createFromAsset(this.getAssets(),"fonts/Raleway-Regular.ttf");
        mTitle.setTypeface(raleway);
        mTitle.setTextSize(20);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.animation_main_1, R.anim.animation_main_2);
            }
        });
        overridePendingTransition(R.anim.animation_detail_2, R.anim.animation_detail_1);
    }






    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
