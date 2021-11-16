package com.pirates.choi.hiswindtester.diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pirates.choi.hiswindtester.R;
import com.pirates.choi.hiswindtester.RDActivity;

import java.util.List;

public class DiaryAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    private List<DiaryAdapterItem> diaryItems;

    private LinearLayout background;

    private TextView dayView;
    private TextView ds;

    public DiaryAdapter(Activity activity, List<DiaryAdapterItem> diaryItems) {
        this.activity = activity;
        this.diaryItems = diaryItems;
    }

    @Override
    public int getCount() {
        return diaryItems.size();
    }

    @Override
    public Object getItem(int location) {
        return diaryItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_diary_item, null);

        background = convertView.findViewById(R.id.diary_item_asset_background);
        dayView = convertView.findViewById(R.id.diary_item_asset_day_view);
        ds = convertView.findViewById(R.id.diary_item_asset_ds);

        final DiaryAdapterItem item = diaryItems.get(position);

        if(!item.getPicture().equals("none")){
            /*
           setBackgroundOval(item, convertView);
           */
            setBackground(item, convertView);
        }else{
            setBackground(item, convertView);
        }

        dayView.setText(item.getDate().substring(8,10));

        widgetFunctioning(item, convertView, position);

        return convertView;

    }

    private void widgetFunctioning(final DiaryAdapterItem item, final View convertView, final int position){

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(convertView.getContext(), RDActivity.class);
                i.putExtra("title", item.getTitle());
                i.putExtra("diary", item.getDiary());
                i.putExtra("date", item.getDate());
                i.putExtra("picture", item.getPicture());
                i.putExtra("background", item.getBackground());
                convertView.getContext().startActivity(i);

            }
        });

        if(position != 0 && !diaryItems.get(position-1).getDate().substring(0,7)
                .equals(item.getDate().substring(0,7))){
            ds.setVisibility(View.VISIBLE);
            ds.setText(item.getDate().substring(0,7));
            /*
            setBackground(item, convertView);
            */
        }else{
            if(position == 0){
                ds.setVisibility(View.VISIBLE);
                ds.setText(item.getDate().substring(0,7));
                /*
                setBackground(item, convertView);
                */
            }else{
                ds.setVisibility(View.GONE);
            }
        }

    }

    private void setBackgroundOval(DiaryAdapterItem item, View convertView){
        switch (item.getBackground()){
            case 1 :
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_1_oval));
                break;
            case 2:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_2_oval));
                break;
            case 3:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_3_oval));
                break;
            case 4:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_4_oval));
                break;
            case 5:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_5_oval));
                break;
            case 6:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_6_oval));
                break;
            case 7:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_7_oval));
                break;
            case 8:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_8_oval));
                break;

            case 9:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_9_oval));
                break;
            case 10:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_10_oval));
                break;
            case 11:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_11_oval));
                break;
            case 12:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_12_oval));
                break;
        }
    }

    private void setBackground(DiaryAdapterItem item, View convertView){
        switch (item.getBackground()){
            case 1 :
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_1));
                break;
            case 2:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_2));
                break;
            case 3:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_3));
                break;
            case 4:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_4));
                break;
            case 5:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_5));
                break;
            case 6:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_6));
                break;
            case 7:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_7));
                break;
            case 8:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_8));
                break;

            case 9:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_9));
                break;
            case 10:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_10));
                break;
            case 11:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_11));
                break;
            case 12:
                background.setBackground(ContextCompat.getDrawable
                        (convertView.getContext(), R.drawable.palett_12));
                break;
        }
    }

}