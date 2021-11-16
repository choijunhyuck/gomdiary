package com.pirates.choi.hiswindtester;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.pirates.choi.hiswindtester.utils.CurrentDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity{

    private String TAG = "REGISTER_ACTIVITY";

    private SharedPreferences mPref;

    private RadioButton radio_1_1;
    private RadioButton radio_2_1;
    private RadioButton radio_3_1;
    private RadioButton radio_4_2;
    private RadioButton radio_5_2;
    private RadioButton radio_6_2;
    private RadioButton radio_7_3;
    private RadioButton radio_8_3;

    private EditText inputDate;
    private EditText inputName;

    private ImageView faultBar;

    private Button commitButton;

    private String name;
    private String startDate;

    private int classification = 0;

    private DatePickerDialog mDatePicker;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPref = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());

        if(!mPref.getString("name", "").equals("") &&
                !mPref.getString("startDate", "").equals("")){
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }

        radio_1_1 = findViewById
                (R.id.register_asset_radio_1_1);
        radio_2_1 = findViewById
                (R.id.register_asset_radio_2_1);
        radio_3_1 = findViewById
                (R.id.register_asset_radio_3_1);
        radio_4_2 = findViewById
                (R.id.register_asset_radio_4_2);
        radio_5_2 = findViewById
                (R.id.register_asset_radio_5_2);
        radio_6_2 = findViewById
                (R.id.register_asset_radio_6_2);
        radio_7_3 = findViewById
                (R.id.register_asset_radio_7_3);
        radio_8_3 = findViewById
                (R.id.register_asset_radio_8_3);

        inputDate = findViewById
                (R.id.register_asset_input_date);
        inputName = findViewById
                (R.id.register_asset_input_name);

        faultBar = findViewById
                (R.id.register_asset_fault_bar);

        commitButton = findViewById
                (R.id.register_asset_commit_button);

        widgetFunctioning();

    }

    private void widgetFunctioning(){
        radio_1_1.setChecked(true);
        radio_1_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    classification = 0;
                    radio_2_1.setChecked(false);
                    radio_3_1.setChecked(false);
                    radio_4_2.setChecked(false);
                    radio_5_2.setChecked(false);
                    radio_6_2.setChecked(false);
                    radio_7_3.setChecked(false);
                    radio_8_3.setChecked(false);

                }

            }
        });
        radio_2_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    classification = 1;
                    radio_1_1.setChecked(false);
                    radio_3_1.setChecked(false);
                    radio_4_2.setChecked(false);
                    radio_5_2.setChecked(false);
                    radio_6_2.setChecked(false);
                    radio_7_3.setChecked(false);
                    radio_8_3.setChecked(false);

                }

            }
        });
        radio_3_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    classification = 2;
                    radio_1_1.setChecked(false);
                    radio_2_1.setChecked(false);
                    radio_4_2.setChecked(false);
                    radio_5_2.setChecked(false);
                    radio_6_2.setChecked(false);
                    radio_7_3.setChecked(false);
                    radio_8_3.setChecked(false);
                }

            }
        });
        radio_4_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    classification = 3;
                    radio_1_1.setChecked(false);
                    radio_2_1.setChecked(false);
                    radio_3_1.setChecked(false);
                    radio_5_2.setChecked(false);
                    radio_6_2.setChecked(false);
                    radio_7_3.setChecked(false);
                    radio_8_3.setChecked(false);
                }

            }
        });
        radio_5_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    classification = 4;
                    radio_1_1.setChecked(false);
                    radio_2_1.setChecked(false);
                    radio_3_1.setChecked(false);
                    radio_4_2.setChecked(false);
                    radio_6_2.setChecked(false);
                    radio_7_3.setChecked(false);
                    radio_8_3.setChecked(false);
                }

            }
        });
        radio_6_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    classification = 5;
                    radio_1_1.setChecked(false);
                    radio_2_1.setChecked(false);
                    radio_3_1.setChecked(false);
                    radio_4_2.setChecked(false);
                    radio_5_2.setChecked(false);
                    radio_7_3.setChecked(false);
                    radio_8_3.setChecked(false);
                }

            }
        });
        radio_7_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    classification = 6;
                    radio_1_1.setChecked(false);
                    radio_2_1.setChecked(false);
                    radio_3_1.setChecked(false);
                    radio_4_2.setChecked(false);
                    radio_5_2.setChecked(false);
                    radio_6_2.setChecked(false);
                    radio_8_3.setChecked(false);
                }

            }
        });
        radio_8_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    classification = 7;
                    radio_1_1.setChecked(false);
                    radio_2_1.setChecked(false);
                    radio_3_1.setChecked(false);
                    radio_4_2.setChecked(false);
                    radio_5_2.setChecked(false);
                    radio_6_2.setChecked(false);
                    radio_7_3.setChecked(false);
                }

            }
        });

        startDate = CurrentDate.getCurrentDate();

        inputDate.setText(startDate);
        inputDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mDatePicker = new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                mDatePicker.show();
            }
        });

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inputName.getText().toString().length() > 0){
                    name = inputName.getText().toString();

                    verifyClass();

                }else{
                    faultBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            faultBar.setVisibility(View.INVISIBLE);
                        }
                    }, 1200);
                }

            }
        });
    }

    private void updateLabel() {
        String Format = "yyyy.MM.dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.KOREA);
        startDate = sdf.format(myCalendar.getTime());
        inputDate.setText(startDate);
    }

    private void verifyClass(){

            Log.d(TAG, "이름 : "+name);
            Log.d(TAG, "시일 : "+startDate);

            SharedPreferences.Editor editor = mPref.edit();
            editor.putString("name", name);
            editor.putString("company", String.valueOf(classification));
            editor.putString("startDate", startDate);
            editor.putString("currentDate", CurrentDate.getCurrentDate());
            editor.commit();

            startActivity(new Intent(RegisterActivity.this,
                    MainActivity.class));
            finish();

    }

}
