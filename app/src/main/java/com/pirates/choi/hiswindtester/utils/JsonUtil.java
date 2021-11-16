package com.pirates.choi.hiswindtester.utils;

import com.pirates.choi.hiswindtester.POJO.DiaryItem;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

    public static String toJSon(DiaryItem diaryClass) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("title", diaryClass.getTitle());// Set the title...etc
            jsonObj.put("diary", diaryClass.getDiary());
            jsonObj.put("date", diaryClass.getDate());
            jsonObj.put("picture", diaryClass.getPicture());
            jsonObj.put("background", diaryClass.getBackground());

            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }
}