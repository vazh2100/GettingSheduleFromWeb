package com.vazheninapps.gettingshedulefromweb.data;

import android.annotation.NonNull;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "time_table")
public class TimeTableItem {
@PrimaryKey(autoGenerate = true)
    private int uniqueId;
    private String title;
    private String description;
    private int weekDay;
    private String timeStart;
    private String timeEnd;
    private String place;
    private String teacherName;
    private String teacherPosition;
    private String teacherImageUrl;


     TimeTableItem(int uniqueId, String title, String description, int weekDay, String timeStart, String timeEnd, String place, String teacherName, String teacherPosition, String teacherImageUrl) {
        this.uniqueId = uniqueId;
        this.title = title;
        this.description = description;
        this.weekDay = weekDay;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.place = place;
        this.teacherName = teacherName;
        this.teacherPosition = teacherPosition;
        this.teacherImageUrl = teacherImageUrl;
    }
 @Ignore
    public TimeTableItem(String title, String description, int weekDay, String timeStart, String timeEnd, String place, String teacherName, String teacherPosition, String teacherImageUrl) {
        this.title = title;
        this.description = description;
        this.weekDay = weekDay;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.place = place;
        this.teacherName = teacherName;
        this.teacherPosition = teacherPosition;
        this.teacherImageUrl = teacherImageUrl;
    }

     int getUniqueId() {
        return uniqueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPosition() {
        return teacherPosition;
    }

    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition;
    }

    public String getTeacherImageUrl() {
        return teacherImageUrl;
    }

    public void setTeacherImageUrl(String teacherImageUrl) {
        this.teacherImageUrl = teacherImageUrl;
    }



    @Override
    public String toString() {
        return "TimeTableItem{" +
                "uniqueId=" + uniqueId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", weekDay=" + weekDay +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", place='" + place + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherPosition='" + teacherPosition + '\'' +
                ", teacherImageUrl='" + teacherImageUrl + '\'' +
                '}';
    }
}
