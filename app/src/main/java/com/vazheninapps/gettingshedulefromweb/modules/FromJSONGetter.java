package com.vazheninapps.gettingshedulefromweb.modules;

import android.util.Log;
import androidx.annotation.NonNull;
import com.vazheninapps.gettingshedulefromweb.data.TimeTableItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class FromJSONGetter {

    private static final String KEY_TITLE = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_WEEK_DAY = "weekDay";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_END_TIME = "endTime";
    private static final String KEY_PLACE = "place";

    private static final String KEY_TEACHER_V2 = "teacher_v2";
    private static final String KEY_TEACHER_NAME = "name";
    private static final String KEY_TEACHER_POSITION = "position";
    private static final String KEY_TEACHER_IMAGE_URL = "imageUrl";


    public static List<TimeTableItem> getTimeTable(@NonNull JSONArray jsonArray) {
        List<TimeTableItem> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString(KEY_TITLE);
                String description = jsonObject.getString(KEY_DESCRIPTION);
                int weekDay = jsonObject.getInt(KEY_WEEK_DAY);
                String startTime = jsonObject.getString(KEY_START_TIME);
                String endTime = jsonObject.getString(KEY_END_TIME);
                String place = jsonObject.getString(KEY_PLACE);
                JSONObject teacherJSON = jsonObject.getJSONObject(KEY_TEACHER_V2);
                String teacherName = teacherJSON.getString(KEY_TEACHER_NAME);
                String teacherPosition = teacherJSON.getString(KEY_TEACHER_POSITION);
                String teacherImageURL = teacherJSON.getString(KEY_TEACHER_IMAGE_URL);
                Log.i("1111", teacherImageURL);
                TimeTableItem timeTableItem = new TimeTableItem(title, description, weekDay, startTime, endTime, place, teacherName, teacherPosition, teacherImageURL);
                result.add(timeTableItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}


