package com.pirates.choi.hiswindtester;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MNAcitivity extends AppCompatActivity{

    private SharedPreferences mPref;

    private TextView nameView;

    private EditText inputName;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mn);

        mPref = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());

        name = mPref.getString("name", "error");

        nameView = findViewById
                (R.id.mn_asset_name_view);

        inputName = findViewById
                (R.id.mn_asset_input_name);

        widgetFunctioning();

    }

    private void widgetFunctioning(){

        nameView.setText("새로운 이름은?");
        inputName.setText(name);

        findViewById(R.id.mn_asset_complete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inputName.getText().length() > 0){
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("name", inputName.getText().toString());
                    editor.commit();

                    MainActivity activity = (MainActivity) MainActivity.activity;
                    activity.finish();

                    startActivity(new Intent(MNAcitivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(MNAcitivity.this, "새로운 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.mn_asset_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
