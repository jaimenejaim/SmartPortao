package com.android.smartportao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.smartportao.util.Network.IASyncFetchListener;
import com.android.smartportao.util.Network.Network;
import com.android.smartportao.util.navigationview.CustomTypefaceSpan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Button buttonSocial;
    Button buttonPrincipal;
    ImageView  imageView_menu;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    NavigationView navView;

    RelativeLayout layoutMain;
    Button buttonOpenCloseMain;

    RelativeLayout layoutSocial;
    Button buttonOpenCloseSocial;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowFlag(MainActivity.this, WindowManager.LayoutParams.FLAG_FULLSCREEN, true);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_main_title);
        Typeface raleway=Typeface.createFromAsset(this.getAssets(),"fonts/Raleway-Regular.ttf");
        mTitle.setTypeface(raleway);
        mTitle.setTextSize(20);
        mDrawerLayout = (DrawerLayout)this.findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        Button buttonLogout =(Button)navView.findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        buttonSocial = (Button)findViewById(R.id.buttonSocial);
        buttonSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSocial.setBackgroundColor(Color.parseColor("#FF0F7174"));
                buttonPrincipal.setBackgroundColor(Color.parseColor("#17a1a5"));
                layoutSocial.setVisibility(View.VISIBLE);
                layoutMain.setVisibility(View.GONE);
                //Não muda o textView em tempo real por que o método abaixo é async e a tela é mostrada antes

            }
        });


        buttonPrincipal = (Button)findViewById(R.id.buttonPrincipal);
        buttonPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSocial.setBackgroundColor(Color.parseColor("#17a1a5"));
                buttonPrincipal.setBackgroundColor(Color.parseColor("#FF0F7174"));
                layoutMain.setVisibility(View.VISIBLE);
                layoutSocial.setVisibility(View.GONE);
                //Não muda o textView em tempo real por que o método abaixo é async e a tela é mostrada antes

            }
        });
        buttonPrincipal.setBackgroundColor(Color.parseColor("#FF0F7174"));

        imageView_menu = (ImageView)findViewById(R.id.button_menu);
        imageView_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        layoutMain = (RelativeLayout)findViewById(R.id.layout_main);
        buttonOpenCloseMain = (Button)layoutMain.findViewById(R.id.button_open_close_main);
        buttonOpenCloseMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //guardar o valor do ultimo request numa variavel e mandar o comando!
                    setOpenCloseDoor("off",false);

                    setOpenCloseDoor("on",false);
            }
        });

        layoutSocial = (RelativeLayout)findViewById(R.id.layout_social);
        layoutSocial.setVisibility(View.GONE);
        buttonOpenCloseSocial = (Button)layoutSocial.findViewById(R.id.button_open_close_social);
        buttonOpenCloseSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //guardar o valor do ultimo request numa variavel e mandar o comando!
                    setOpenCloseDoor("off",true);

                    setOpenCloseDoor("on",true);

            }
        });

        //Aplicando fonte personalizada no NavigationDrawer
        Menu m = navView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            //for applying a font to subMenu...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {

        }else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));


        }else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));

        }else if( id == R.id.nav_historic){
            startActivity(new Intent(MainActivity.this, HistoryActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

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


    public void setOpenCloseDoor(String value,final boolean type){
        Network network = new Network(this);
        network.setActionDoor(value,type);
        network.setListener(new IASyncFetchListener() {
            @Override
            public void onComplete(JSONObject jsonObject) {
                if(jsonObject.has("led")){
                    try {
                        if(jsonObject.getString("led").equals("true")){

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete(JSONArray jsonArray) {
                Log.i("entrou", " no Throwable error:" + jsonArray);
            }

            @Override
            public void onError(Throwable error) {
                Log.i("entrou", " no Throwable error:" + error);
            }

            @Override
            public void onError(JSONObject errorResponse) {
                Log.i("entrou", " no onError:" + errorResponse);
            }
        });
    }

}
