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
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class WDActivity extends AppCompatActivity{

    private String TAG = "WD_ACTIVITY";

    private SharedPreferences mPref;

    private RelativeLayout titleBarBackground;
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
        setContentView(R.layout.activity_wd);

        mPref = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());

        titleBarBackground = findViewById
                (R.id.wd_asset_title_bar);
        palettLayout = findViewById
                (R.id.wd_asset_palett_layout);
        palettButton = findViewById
                (R.id.wd_asset_palett_button);

        textWatcher = findViewById
                (R.id.wd_asset_textwatcher);
        dateView = findViewById
                (R.id.wd_asset_date_view);

        inputTitle = findViewById
                (R.id.wd_asset_input_title);
        inputDiary = findViewById
                (R.id.wd_asset_input_diary);

        palettButtonImageView = findViewById
                (R.id.wd_asset_palett_button_image);

        completeButton = findViewById
                (R.id.wd_asset_complete_button);

        setPaletts();

        addPictureButton = findViewById
                (R.id.wd_asset_add_picture_button);

        if(!mPref.getString("titleCache", "").equals("")){
            loadCache();
        }

        widgetFunctioning();

    }

    private void loadCache(){

        inputTitle.setText(mPref.getString("titleCache", "무제"));

        inputDiary.setText(mPref.getString("diaryCache", ""));

        if(!mPref.getString("pictureCache", "none").equals("none")){
            picture = mPref.getString("pictureCache", "");
            addPictureButton.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.drawable.wd_asset_add_picture_attached));
        }

        BACKGROUND = mPref.getInt("backgroundCache", 1);

        switch (BACKGROUND){
            case 1:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_1));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_1));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_1));
                dateView.setTextColor(getResources().getColor(R.color.palett_1));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_1));
                break;

            case 2:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_2));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_2));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_2));
                dateView.setTextColor(getResources().getColor(R.color.palett_2));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_2));
                break;

            case 3:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_3));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_3));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_3));
                dateView.setTextColor(getResources().getColor(R.color.palett_3));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_3));
                break;

            case 4:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_4));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_4));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_4));
                dateView.setTextColor(getResources().getColor(R.color.palett_4));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_4));
                break;

            case 5:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_5));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_5));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_5));
                dateView.setTextColor(getResources().getColor(R.color.palett_5));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_5));
                break;

            case 6:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_6));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_6));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_6));
                dateView.setTextColor(getResources().getColor(R.color.palett_6));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_6));
                break;

            case 7:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_7));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_7));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_7));
                dateView.setTextColor(getResources().getColor(R.color.palett_7));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_7));
                break;

            case 8:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_8));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_8));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_8));
                dateView.setTextColor(getResources().getColor(R.color.palett_8));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_8));
                break;

            case 9:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_9));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_9));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_9));
                dateView.setTextColor(getResources().getColor(R.color.palett_9));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_9));
                break;

            case 10:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_10));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_10));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_10));
                dateView.setTextColor(getResources().getColor(R.color.palett_10));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_10));
                break;

            case 11:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_11));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_11));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_11));
                dateView.setTextColor(getResources().getColor(R.color.palett_11));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_11));
                break;

            case 12:
                titleBarBackground.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.color.palett_12));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_12));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_12));
                dateView.setTextColor(getResources().getColor(R.color.palett_12));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_12));
                break;

        }

        textWatcher.setText(String.valueOf(inputDiary.length()) + " / 480");
        completeButton.setVisibility(View.VISIBLE);

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
                            (WDActivity.this, R.drawable.wd_asset_down_palett));
                    palettLayout.setVisibility(View.VISIBLE);

                }else{

                    PALETT_STATE = 0;
                    palettButtonImageView.setBackground(ContextCompat.getDrawable
                            (WDActivity.this, R.drawable.wd_asset_up_palett));
                    palettLayout.setVisibility(View.GONE);

                }

            }
        });

        dateView.setText(CurrentDate.getCurrentDate());

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
                //***
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                final String currentFullTime = sdf.format(date);
                //***
                diaryItem.setDate(currentFullTime);

                if(!picture.equals("none")){
                    diaryItem.setPicture(picture);
                }else{
                    diaryItem.setPicture("none");
                }

                diaryItem.setBackground(BACKGROUND);

                String filename = currentFullTime+".json";
                FileOutputStream outputStream;

                try {

                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(JsonUtil.toJSon(diaryItem).getBytes());
                    outputStream.close();

                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("titleCache", title);
                    editor.putString("diaryCache", diary);
                    editor.putString("pictureCache", picture);
                    editor.putInt("backgroundCache", BACKGROUND);
                    editor.commit();

                    MainActivity activity = (MainActivity) MainActivity.activity;
                    activity.finish();

                    startActivity(new Intent(WDActivity.this, MainActivity.class));
                    finish();

                } catch (Exception e) {

                    e.printStackTrace();

                }
                //********COMPLETE FILE**********//

            }
        });

        findViewById(R.id.wd_asset_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setPaletts(){

        findViewById(R.id.wd_asset_palett_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_1));
                }

                BACKGROUND = 1;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_1));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_1));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_1));
                dateView.setTextColor(getResources().getColor(R.color.palett_1));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_1));

            }
        });
        findViewById(R.id.wd_asset_palett_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_2));
                }

                BACKGROUND = 2;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_2));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_2));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_2));
                dateView.setTextColor(getResources().getColor(R.color.palett_2));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_2));

            }
        });
        findViewById(R.id.wd_asset_palett_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_3));
                }

                BACKGROUND = 3;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_3));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_3));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_3));
                dateView.setTextColor(getResources().getColor(R.color.palett_3));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_3));

            }
        });
        findViewById(R.id.wd_asset_palett_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_4));
                }

                BACKGROUND = 4;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_4));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_4));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_4));
                dateView.setTextColor(getResources().getColor(R.color.palett_4));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_4));

            }
        });
        findViewById(R.id.wd_asset_palett_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_5));
                }

                BACKGROUND = 5;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_5));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_5));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_5));
                dateView.setTextColor(getResources().getColor(R.color.palett_5));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_5));

            }
        });
        findViewById(R.id.wd_asset_palett_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_6));
                }

                BACKGROUND = 6;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_6));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_6));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_6));
                dateView.setTextColor(getResources().getColor(R.color.palett_6));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_6));

            }
        });
        findViewById(R.id.wd_asset_palett_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_7));
                }

                BACKGROUND = 7;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_7));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_7));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_7));
                dateView.setTextColor(getResources().getColor(R.color.palett_7));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_7));

            }
        });
        findViewById(R.id.wd_asset_palett_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_8));
                }

                BACKGROUND = 8;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_8));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_8));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_8));
                dateView.setTextColor(getResources().getColor(R.color.palett_8));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_8));

            }
        });
        findViewById(R.id.wd_asset_palett_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_9));
                }

                BACKGROUND = 9;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_9));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_9));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_9));
                dateView.setTextColor(getResources().getColor(R.color.palett_9));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_9));

            }
        });
        findViewById(R.id.wd_asset_palett_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_10));
                }

                BACKGROUND = 10;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_10));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_10));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_10));
                dateView.setTextColor(getResources().getColor(R.color.palett_10));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_10));

            }
        });
        findViewById(R.id.wd_asset_palett_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_11));
                }

                BACKGROUND = 11;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_11));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_11));
                textWatcher.setTextColor(getResources().getColor(R.color.palett_11));
                dateView.setTextColor(getResources().getColor(R.color.palett_11));
                inputTitle.setTextColor(getResources().getColor(R.color.palett_11));

            }
        });
        findViewById(R.id.wd_asset_palett_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.palett_12));
                }

                BACKGROUND = 12;
                titleBarBackground.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_12));
                inputDiary.setBackground(ContextCompat.getDrawable
                        (WDActivity.this, R.color.palett_12));
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
                        (WDActivity.this, R.drawable.wd_asset_add_picture_attached));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(WDActivity.this, "ERROR", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(WDActivity.this, "첨부가 취소되었습니다.",Toast.LENGTH_LONG).show();
            picture = "none";
            addPictureButton.setBackground(ContextCompat.getDrawable
                    (WDActivity.this, R.drawable.wd_asset_add_picture));

        }
    }

}
