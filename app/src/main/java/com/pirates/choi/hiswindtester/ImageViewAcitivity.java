package com.pirates.choi.hiswindtester;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.pirates.choi.hiswindtester.utils.BitmapUtil;

public class ImageViewAcitivity extends AppCompatActivity{

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        imageView = findViewById
                (R.id.image_view_asset_view);

        Intent i = getIntent();
        imageView.setImageBitmap(BitmapUtil.StringToBitMap(i.getStringExtra("picture")));

        findViewById(R.id.image_view_asset_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
