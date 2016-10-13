package com.android.smartportao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.smartportao.util.navigationview.CustomTypefaceSpan;

public class SettingsActivity extends AppCompatActivity {


    ImageButton imageButtonLineDevice;
    ImageButton imageButtonLineNetwork;
    ImageButton imageButtonLineAccount;
    RelativeLayout relativeLayoutDevice;
    RelativeLayout relativeLayoutNetwork;
    RelativeLayout relativeLayoutAccount;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWindowFlag(SettingsActivity.this, WindowManager.LayoutParams.FLAG_FULLSCREEN, true);
        setContentView(R.layout.activity_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_main_title);
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
        imageButtonLineDevice = (ImageButton) findViewById(R.id.image_button_line_device);
        imageButtonLineNetwork = (ImageButton) findViewById(R.id.image_button_line_network);
        imageButtonLineAccount = (ImageButton) findViewById(R.id.image_button_line_account);
        relativeLayoutAccount = (RelativeLayout) findViewById(R.id.relative_layout_account);
        relativeLayoutDevice = (RelativeLayout) findViewById(R.id.relative_layout_device);
        relativeLayoutNetwork = (RelativeLayout) findViewById(R.id.relative_layout_network);

        imageButtonLineDevice.setOnClickListener(buttonsOnClickListener);
        imageButtonLineNetwork.setOnClickListener(buttonsOnClickListener);
        imageButtonLineAccount.setOnClickListener(buttonsOnClickListener);
        relativeLayoutDevice.setOnClickListener(buttonsOnClickListener);
        relativeLayoutNetwork.setOnClickListener(buttonsOnClickListener);
        relativeLayoutAccount.setOnClickListener(buttonsOnClickListener);

        overridePendingTransition(R.anim.animation_detail_2, R.anim.animation_detail_1);
    }

    View.OnClickListener buttonsOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.image_button_line_account:
                    startActivity(new Intent(SettingsActivity.this, SettingsUserActivity.class));
                    break;
                case R.id.relative_layout_account:
                    startActivity(new Intent(SettingsActivity.this, SettingsUserActivity.class));
                    break;



                case R.id.image_button_line_device:
                    startActivity(new Intent(SettingsActivity.this, SettingsDeviceActivity.class));
                    break;
                case R.id.relative_layout_device:
                    startActivity(new Intent(SettingsActivity.this, SettingsDeviceActivity.class));
                    break;



                case R.id.image_button_line_network:
                    Log.i("entrou","Network");
                    break;
                case R.id.relative_layout_network:
                    Log.i("entrou","Network");
                    break;
            }

        }
    };



    //Aplicando fonte customizada no NavgationView
    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
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
