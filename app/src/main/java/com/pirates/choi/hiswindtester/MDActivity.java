package com.pirates.choi.hiswindtester;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pirates.choi.hiswindtester.POJO.DiaryItem;
import com.pirates.choi.hiswindtester.utils.BitmapUtil;
import com.pirates.choi.hiswindtester.utils.CurrentDate;
import com.pirates.choi.hiswindtester.utils.JsonUtil;
import com.pirates.choi.hiswindtester.utils.RoundImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MDActivity extends AppCompatActivity{

    private String TAG = "MD_ACTIVITY";

    private SharedPreferences mPref;

    private RelativeLayout titleBar;
    private RelativeLayout palettLayout;
    private RelativeLayout palettButton;

    private TextView textWatcher;
    private TextView dateView;

    private EditText inputTitle;
    private EditText inputDiary;

    private ImageView palettButtonImageView;

    private Button completeButton;

    private RoundImageView addPictureButton;

    private int BACKGROUND = 1;
    private int PALETT_STATE = 0;

    private String title;
    private String diary;
    private String date;
    private String picture = "none";

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            textWatcher.setText(String.valueOf(s.length()) + " / 480");

            if(s.length() > 0){

                completeButton.setVisibility(View.VISIBLE);
            } else {
                completeButton.setVisibility(View.INVISIBLE);
            }
        }

        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);

        mPref = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());

        titleBar = findViewById
                (R.id.md_asset_title_bar);
        palettLayout = findViewById
                (R.id.md_asset_palett_layout);
        palettButton = findViewById
                (R.id.md_asset_palett_button);

        textWatcher = findViewById
                (R.id.md_asset_textwatcher);
        dateView = findViewById
                (R.id.md_asset_date_view);

        inputTitle = findViewById
                (R.id.md_asset_input_title);
        inputDiary = findViewById
                (R.id.md_asset_input_diary);

        palettButtonImageView = findViewById
                (R.id.md_asset_palett_button_image);

        completeButton = findViewById
                (R.id.md_asset_complete_button);

        setPaletts();

        addPictureButton = findViewById
                (R.id.md_asset_add_picture_button);

        loadDiary();

    }

    private void loadDiary(){

        Intent i = getIntent();
        title = i.getStringExtra("title");
        diary = i.getStringExtra("diary");
        date= i.getStringExtra("date");
        picture = i.getStringExtra("picture");
        BACKGROUND = i.getIntExtra("background", 1);

        inputTitle.setText(title);
        inputDiary.setText(diary);
        dateView.setText(date);
        if(!picture.equals("none")){
            addPictureButton.setBackground(ContextCompat.getDrawable
                    (MDActivity.this, R.drawable.wd_asset_add_picture_attached));
        }
        setBackground(BACKGROUND);

        textWatcher.setText(String.valueOf(inputDiary.length()) + " / 480");

        switch(BACKGROUND){

            case 1:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_1));
                dateView.setTextColor(getResources().getColor(R.color.palett_1));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_1));
                break;
            case 2:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_2));
                dateView.setTextColor(getResources().getColor(R.color.palett_2));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_2));
                break;
            case 3:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_3));
                dateView.setTextColor(getResources().getColor(R.color.palett_3));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_3));
                break;
            case 4:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_4));
                dateView.setTextColor(getResources().getColor(R.color.palett_4));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_4));
                break;
            case 5:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_5));
                dateView.setTextColor(getResources().getColor(R.color.palett_5));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_5));
                break;
            case 6:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_6));
                dateView.setTextColor(getResources().getColor(R.color.palett_6));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_6));
                break;
            case 7:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_7));
                dateView.setTextColor(getResources().getColor(R.color.palett_7));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_7));
                break;
            case 8:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_8));
                dateView.setTextColor(getResources().getColor(R.color.palett_8));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_8));
                break;
            case 9:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_9));
                dateView.setTextColor(getResources().getColor(R.color.palett_9));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_9));
                break;
            case 10:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_10));
                dateView.setTextColor(getResources().getColor(R.color.palett_10));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_10));
                break;
            case 11:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_11));
                dateView.setTextColor(getResources().getColor(R.color.palett_11));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_11));
                break;
            case 12:
                textWatcher.setTextColor(getResources().getColor(R.color.palett_12));
                dateView.setTextColor(getResources().getColor(R.color.palett_12));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_12));
                break;

        }

        widgetFunctioning();

    }

    private void setBackground(int background) {

        switch (background) {
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_1));
                }
                titleBar.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_1));
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
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
                inputDiary.setBackground(ContextCompat.getDrawable
                        (getApplicationContext(), R.color.palett_12));

                break;
        }
    }

    private void widgetFunctioning(){

        inputDiary.addTextChangedListener(mTextEditorWatcher);

        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);

            }
        });

        palettLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        palettButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(PALETT_STATE == 0){

                    PALETT_STATE = 1;
                    palettButtonImageView.setBackground(ContextCompat.getDrawable
                            (MDActivity.this, R.drawable.wd_asset_down_palett));
                    palettLayout.setVisibility(View.VISIBLE);

                }else{

                    PALETT_STATE = 0;
                    palettButtonImageView.setBackground(ContextCompat.getDrawable
                            (MDActivity.this, R.drawable.wd_asset_up_palett));
                    palettLayout.setVisibility(View.GONE);

                }

            }
        });

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = inputTitle.getText().toString();
                if(title.equals("")) {
                    title = "무제";
                }
                diary = inputDiary.getText().toString();

                //********MAKE FILE**********//
                DiaryItem diaryItem = new DiaryItem();
                diaryItem.setTitle(title);
                diaryItem.setDiary(diary);
                diaryItem.setDate(date);

                if(!picture.equals("none")){
                    diaryItem.setPicture(picture);
                }else{
                    diaryItem.setPicture("none");
                }

                diaryItem.setBackground(BACKGROUND);

                String filename = date+".json";
                FileOutputStream outputStream;

                try {

                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(JsonUtil.toJSon(diaryItem).getBytes());
                    outputStream.close();

                    if(date.equals(CurrentDate.getCurrentDate())){
                        SharedPreferences.Editor editor = mPref.edit();
                        editor.putString("titleCache", title);
                        editor.putString("diaryCache", diary);
                        editor.putString("pictureCache", picture);
                        editor.commit();
                    }

                    RDActivity activity = (RDActivity) RDActivity.activity;
                    activity.finish();

                    Intent i = new Intent(new Intent(MDActivity.this, RDActivity.class));
                    i.putExtra("title", title);
                    i.putExtra("diary", diary);
                    i.putExtra("date", date);
                    i.putExtra("picture", picture);
                    i.putExtra("background", BACKGROUND);

                    startActivity(i);
                    finish();

                } catch (Exception e) {

                    e.printStackTrace();

                }
                //********COMPLETE FILE**********//

            }
        });

        findViewById(R.id.md_asset_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setPaletts(){

        findViewById(R.id.md_asset_palett_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_1));
                }

                BACKGROUND = 1;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_1));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_1));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_1));
                dateView.setTextColor(getResources().getColor(R.color.palett_1));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_1));

            }
        });
        findViewById(R.id.md_asset_palett_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_2));
                }

                BACKGROUND = 2;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_2));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_2));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_2));
                dateView.setTextColor(getResources().getColor(R.color.palett_2));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_2));

            }
        });
        findViewById(R.id.md_asset_palett_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_3));
                }

                BACKGROUND = 3;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_3));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_3));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_3));
                dateView.setTextColor(getResources().getColor(R.color.palett_3));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_3));

            }
        });
        findViewById(R.id.md_asset_palett_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_4));
                }

                BACKGROUND = 4;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_4));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_4));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_4));
                dateView.setTextColor(getResources().getColor(R.color.palett_4));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_4));

            }
        });
        findViewById(R.id.md_asset_palett_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_5));
                }

                BACKGROUND = 5;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_5));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_5));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_5));
                dateView.setTextColor(getResources().getColor(R.color.palett_5));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_5));

            }
        });
        findViewById(R.id.md_asset_palett_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_6));
                }

                BACKGROUND = 6;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_6));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_6));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_6));
                dateView.setTextColor(getResources().getColor(R.color.palett_6));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_6));

            }
        });
        findViewById(R.id.md_asset_palett_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_7));
                }

                BACKGROUND = 7;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_7));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_7));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_7));
                dateView.setTextColor(getResources().getColor(R.color.palett_7));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_7));

            }
        });
        findViewById(R.id.md_asset_palett_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_8));
                }

                BACKGROUND = 8;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_8));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_8));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_8));
                dateView.setTextColor(getResources().getColor(R.color.palett_8));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_8));

            }
        });
        findViewById(R.id.md_asset_palett_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_9));
                }

                BACKGROUND = 9;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_9));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_9));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_9));
                dateView.setTextColor(getResources().getColor(R.color.palett_9));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_9));

            }
        });
        findViewById(R.id.md_asset_palett_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_10));
                }

                BACKGROUND = 10;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_10));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_10));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_10));
                dateView.setTextColor(getResources().getColor(R.color.palett_10));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_10));

            }
        });
        findViewById(R.id.md_asset_palett_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_11));
                }

                BACKGROUND = 11;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_11));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_11));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_11));
                dateView.setTextColor(getResources().getColor(R.color.palett_11));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_11));

            }
        });
        findViewById(R.id.md_asset_palett_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_12));
                }

                BACKGROUND = 12;
                titleBar.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_12));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.color.palett_12));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_12));
                dateView.setTextColor(getResources().getColor(R.color.palett_12));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_12));

            }
        });

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {

                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                picture = BitmapUtil.BitMapToString(selectedImage);

                addPictureButton.setBackground(ContextCompat.getDrawable
                        (MDActivity.this, R.drawable.wd_asset_add_picture_attached));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MDActivity.this, "ERROR", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(MDActivity.this, "첨부가 취소되었습니다.",Toast.LENGTH_LONG).show();
            picture = "none";
            addPictureButton.setBackground(ContextCompat.getDrawable
                    (MDActivity.this, R.drawable.wd_asset_add_picture));

        }
    }

}
