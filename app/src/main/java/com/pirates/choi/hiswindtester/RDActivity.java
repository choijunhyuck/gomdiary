package com.pirates.choi.hiswindtester;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pirates.choi.hiswindtester.utils.RoundImageView;

public class RDActivity extends AppCompatActivity{

    public static RDActivity activity;

    private String TAG = "RD_ACTIVITY";

    private SharedPreferences mPref;

    private RelativeLayout titleBar;

    private TextView dateView;
    private TextView titleView;
    private TextView diaryView;

    private RoundImageView pictureView;

    private String title;
    private String diary;
    private String date;
    private String picture;
    private int background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rd);

        activity = this;

        mPref = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());

        titleBar = findViewById
                (R.id.rd_asset_title_bar);
        dateView = findViewById
                (R.id.rd_asset_date_view);
        titleView = findViewById
                (R.id.rd_asset_title_view);
        diaryView = findViewById
                (R.id.rd_asset_diary_view);
        pictureView = findViewById
                (R.id.rd_asset_add_picture_view);

        widgetFunctioning();

    }

    private void widgetFunctioning(){

        Intent i = getIntent();
        title = i.getStringExtra("title");
        diary = i.getStringExtra("diary");
        date = i.getStringExtra("date");
        picture = i.getStringExtra("picture");
        background = i.getIntExtra("background", 1);

        titleView.setText(title);
        diaryView.setText(diary);
        dateView.setText(date);

        if(!picture.equals("none")){
            pictureView.setVisibility(View.VISIBLE);
            pictureView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(RDActivity.this, ImageViewAcitivity.class);
                    i.putExtra("picture", picture);
                    startActivity(i);

                }
            });
        }
        setBackground(background);

        findViewById(R.id.rd_asset_modify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RDActivity.this, MDActivity.class);
                i.putExtra("title", title);
                i.putExtra("diary", diary);
                i.putExtra("date", date);
                i.putExtra("picture", picture);
                i.putExtra("background", background);
                startActivity(i);

            }
        });

        findViewById(R.id.rd_asset_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void setBackground(int background){

        switch (background){
            case 1 :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_1));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_1));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_1));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_1));

                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_2));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_2));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_2));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_2));

                break;
            case 3:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_3));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_3));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_3));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_3));

                break;
            case 4:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_4));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_4));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_4));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_4));

                break;
            case 5:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_5));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_5));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_5));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_5));

                break;
            case 6:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_6));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_6));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_6));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_6));

                break;
            case 7:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_7));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_7));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_7));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_7));

                break;
            case 8:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_8));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_8));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_8));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_8));

                break;

            case 9:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.palett_9));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_9));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_9));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_9));

                break;
            case 10:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_10));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_10));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_10));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_10));

                break;
            case 11:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_11));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_11));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_11));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_11));

                break;
            case 12:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.palett_12));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_12));
                titleView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_12));
                diaryView.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_12));

                break;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
