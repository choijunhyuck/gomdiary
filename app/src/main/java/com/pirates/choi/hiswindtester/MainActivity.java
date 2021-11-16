package com.pirates.choi.hiswindtester;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pirates.choi.hiswindtester.diary.DiaryAdapter;
import com.pirates.choi.hiswindtester.diary.DiaryAdapterItem;
import com.pirates.choi.hiswindtester.utils.BitmapUtil;
import com.pirates.choi.hiswindtester.utils.CurrentDate;
import com.pirates.choi.hiswindtester.utils.RoundImageView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    public static MainActivity activity;

    private String TAG = "MAIN_ACTIVITY";

    private SharedPreferences mPref;

    private RelativeLayout popup;

    private TextView classView;
    private TextView nameView;
    private TextView leftDayView;

    private Button fButton;
    private Button writeDiaryButton;

    private RoundImageView ppView;

    //LIST
    private GridView diaryList;
    private DiaryAdapter listAdapter;
    private List<DiaryAdapterItem> diaryItem;

    private int POPUP_ACTIVATE = 0;

    private String name;
    private String leftDay;
    private String ppString;
    private String company;

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_iMAGE = 2;

    //read notes
    private String title;
    private String diary;
    private String date;
    private String picture;
    private int background;
    //

    private Uri mImageCaptureUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());

        if(mPref.getString("currentDate", "").equals("")){
            SharedPreferences.Editor editor = mPref.edit();
            editor.putString("currentDate", CurrentDate.getCurrentDate());
            editor.commit();
        }

        activity = this;

        popup = findViewById
                (R.id.main_asset_popup);

        classView = findViewById
                (R.id.main_asset_class_view);
        nameView = findViewById
                (R.id.main_asset_name_view);
        leftDayView = findViewById
                (R.id.main_asset_left_day_view);

        fButton = findViewById
                (R.id.main_asset_fbutton);
        writeDiaryButton = findViewById
                (R.id.main_asset_write_diary_button);

        ppView = findViewById
                (R.id.main_asset_pp_view);

        //LIST SETTING
        diaryList = findViewById
                (R.id.main_asset_diary_list);
        diaryItem = new ArrayList<DiaryAdapterItem>();
        listAdapter = new DiaryAdapter(this, diaryItem);
        diaryList.setAdapter(listAdapter);

        widgetFunctioning();

    }

    private void widgetFunctioning(){

        name = mPref.getString("name", "error");
        leftDay = mPref.getString("leftDay", "SET");
        ppString = mPref.getString("ppString", "error");
        company = mPref.getString("company", "0");

        //setPP
        if(!ppString.equals("error")){
            ppView.setImageBitmap(BitmapUtil.StringToBitMap(ppString));
        }

        //setText
        setTexts(Integer.valueOf(company), name, leftDay);

        //setPPclickListener
        ppView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set new ppString

                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        doTakePhotoAction();

                    }
                };

                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        doTakeAlbumAction();

                    }
                };


                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                };

                new AlertDialog.Builder(MainActivity.this)

                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("사진촬영", cameraListener)
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();

            }
        });

        //setPOPUPclickListener
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(POPUP_ACTIVATE == 0){

                    POPUP_ACTIVATE = 1;
                    fButton.setBackground(ContextCompat.getDrawable
                            (MainActivity.this, R.drawable.main_asset_fbutton_activate));
                    popup.setVisibility(View.VISIBLE);

                }else{

                    POPUP_ACTIVATE = 0;
                    fButton.setBackground(ContextCompat.getDrawable
                            (MainActivity.this, R.drawable.main_asset_fbutton_deactivate));
                    popup.setVisibility(GONE);

                }

            }
        });

        //setWDBclickListener
        writeDiaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                POPUP_ACTIVATE = 0;
                fButton.setBackground(ContextCompat.getDrawable
                        (MainActivity.this, R.drawable.main_asset_fbutton_deactivate));
                popup.setVisibility(GONE);

                startActivity(new Intent(MainActivity.this, WDActivity.class));

            }
        });

        getDiaries();

    }

    private void setTexts(int classification, String name, String leftDay){

        switch (classification){
            case 0 :
                classView.setText("육군");
                break;
            case 1:
                classView.setText("의무경찰");
                break;
            case 2:
                classView.setText("해병");
                break;
            case 3:
                classView.setText("해군");
                break;
            case 4:
                classView.setText("해양의무경찰");
                break;
            case 5:
                classView.setText("공군");
                break;
            case 6:
                classView.setText("사회복무");
                break;
            case 7:
                classView.setText("의무소방");
                break;
        }

        nameView.setText(name);
        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MNAcitivity.class));
            }
        });

        leftDayView.setText(leftDay);
        leftDayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, RegisterEndDateAcitivity.class));

            }
        });

    }

    private void getDiaries(){

        File file = new File(getApplication().getFilesDir().getPath());
        Log.d(TAG, getApplication().getFilesDir().getPath());
        File[] list = file.listFiles();

        for (File f: list){

            String fileName = f.getName();
            File dataFile = new File(getApplication().getFilesDir(), fileName);
            try {

                //READ NOTE
                String data = getStringFromFile(dataFile.getPath());

                JSONObject jObj = new JSONObject(data);

                //
                String downloadedTitle = jObj.getString("title");
                //
                String downloadedDiary;
                if(!jObj.has("diary")){
                    downloadedDiary = jObj.getString("note");
                }else{
                    downloadedDiary = jObj.getString("diary");
                }
                //
                String downloadedDate = jObj.getString("date");
                //
                String downloadedPicture;
                if(!jObj.has("picture")){
                    downloadedPicture = "none";
                }else{
                    downloadedPicture = jObj.getString("picture");
                }
                //
                int downloadedBackground;
                if(!jObj.has("background")){
                    downloadedBackground = 1;
                }else{
                    downloadedBackground = jObj.getInt("background");
                }
                //

                title = downloadedTitle;
                diary = downloadedDiary;
                date = downloadedDate;
                picture = downloadedPicture;
                background = downloadedBackground;

                //To Watch OLD VERSION COMPATIBILITY
                Log.d(TAG, title);
                Log.d(TAG, diary);
                Log.d(TAG, date);
                Log.d(TAG, picture);
                Log.d(TAG, String.valueOf(background));

                DiaryAdapterItem item = new DiaryAdapterItem(title, diary, date, picture, background);
                diaryItem.add(listAdapter.getCount(), item);

            }catch (Exception e){
                e.printStackTrace();
                Log.d(TAG, "error!");
            }

            /*
            if (name.endsWith(".jpg") || name.endsWith(".mp3") || name.endsWith(".some media extention"))
                count++;
            System.out.println("170 " + count);
            */
        }
        listAdapter.notifyDataSetChanged();
        alarmClock();
    }

    private void alarmClock(){

        //INSPECTION OF DAY CHANGING
        if(!mPref.getString("currentDate", "")
                .equals(CurrentDate.getCurrentDate()) && !leftDay.equals("SET")){

            leftDay = String.valueOf(Integer.valueOf(leftDay) - 1);
            SharedPreferences.Editor editor = mPref.edit();
            editor.putString("leftDay", leftDay);
            editor.putString("currentDate", CurrentDate.getCurrentDate());
            editor.remove("titleCache");
            editor.remove("diaryCache");
            editor.remove("pictureCache");
            editor.remove("backgroundCache");
            editor.commit();

            leftDayView.setText(leftDay);

        }

    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    /**
     * 카메라에서 사진 촬영
     */

    public void doTakePhotoAction(){

        //CAMERA
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //TEMP PATH
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);

    }

    /**
     * 앨범에서 이미지 가져오기
     */

    public void doTakeAlbumAction() // 앨범에서 이미지 가져오기
    {

        // ALBUM
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode != RESULT_OK)

            return;

        switch(requestCode)

        {

            case PICK_FROM_ALBUM:

            {

                mImageCaptureUri = data.getData();

                Log.d(TAG,mImageCaptureUri.getPath().toString());

            }


            case PICK_FROM_CAMERA:

            {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");

                intent.setDataAndType(mImageCaptureUri, "image/*");

                // CROP할 이미지를 200*200 크기로 저장

                intent.putExtra("outputX", 200); // CROP한 이미지의 x축 크기

                intent.putExtra("outputY", 200); // CROP한 이미지의 y축 크기

                intent.putExtra("aspectX", 1); // CROP 박스의 X축 비율

                intent.putExtra("aspectY", 1); // CROP 박스의 Y축 비율

                intent.putExtra("scale", true);

                intent.putExtra("return-data", true);

                startActivityForResult(intent, CROP_FROM_iMAGE); // CROP_FROM_CAMERA case문 이동

                break;

            }

            case CROP_FROM_iMAGE:

            {

                if(resultCode != RESULT_OK) {
                    return;
                }

                final Bundle extras = data.getExtras();

                if(extras != null) {

                    Bitmap photo = extras.getParcelable("data"); // CROP된 BITMAP

                    String bitmap = BitmapUtil.BitMapToString(photo);
                    SharedPreferences.Editor editor = mPref.edit();

                    editor.putString("ppString", bitmap);
                    editor.commit();
                    ppView.setImageBitmap(photo);

                    break;

                }

                // 임시 파일 삭제

                File f = new File(mImageCaptureUri.getPath());

                if(f.exists())

                {
                    f.delete();
                }

            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        POPUP_ACTIVATE = 0;
        fButton.setBackground(ContextCompat.getDrawable
                (MainActivity.this, R.drawable.main_asset_fbutton_deactivate));
        popup.setVisibility(GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        diaryItem.clear();
        getDiaries();
    }
}
