package com.pirates.choi.hiswindtester;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.pirates.choi.hiswindtester.utils.CurrentDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterEndDateAcitivity extends AppCompatActivity{

    private SharedPreferences mPref;

    private TextView nameView;

    private EditText inputDate;

    private String name;

    private String startDate;
    private String endDate;

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
        setContentView(R.layout.activity_register_end_date);

        mPref = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());

        name = mPref.getString("name", "error");

        nameView = findViewById
                (R.id.register_end_date_asset_name_view);

        inputDate = findViewById
                (R.id.register_end_date_asset_input_date);

        startDate = CurrentDate.getCurrentDate();
        endDate = mPref.getString("endDate", CurrentDate.getCurrentDate());

        widgetFunctioning();

    }

    private void widgetFunctioning(){

        nameView.setText(name+"의 전역일은?");

        inputDate.setText(endDate);
        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mDatePicker = new DatePickerDialog(RegisterEndDateAcitivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDatePicker.show();
            }
        });

        findViewById(R.id.register_end_date_asset_complete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mPref.edit();
                editor.putString("endDate", endDate);
                editor.putString("leftDay", calDateBetweenAandB(startDate, endDate));
                editor.commit();

                MainActivity activity = (MainActivity) MainActivity.activity;
                activity.finish();

                startActivity(new Intent(RegisterEndDateAcitivity.this, MainActivity.class));
                finish();
            }
        });

        findViewById(R.id.register_end_date_naver_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%A0%84%EC%97%AD%EC%9D%BC+%EA%B3%84%EC%82%B0%EA%B8%B0&oquery=%EC%A0%84%EC%97%AD%EC%9D%BC%EA%B3%84%EC%82%B0%EA%B8%B0&tqi=UZr81spySENssvxRcFdssssssQs-305281");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.register_end_date_asset_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void updateLabel() {
        String Format = "yyyy.MM.dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.KOREA);
        endDate = sdf.format(myCalendar.getTime());
        inputDate.setText(endDate);
    }

    public String calDateBetweenAandB(String start, String end){

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            Date beginDate = formatter.parse(start);
            Date endDate = formatter.parse(end);

            // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
            long diff = endDate.getTime() - beginDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            return String.valueOf(diffDays);

        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }

    }

}
