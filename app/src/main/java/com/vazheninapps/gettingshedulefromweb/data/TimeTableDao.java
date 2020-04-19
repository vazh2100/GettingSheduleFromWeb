package com.vazheninapps.gettingshedulefromweb.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TimeTableDao {

    @Query("SELECT * FROM time_table ORDER BY weekDay ASC")
    LiveData<List<TimeTableItem>> getTimeTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTimeTable(List<TimeTableItem> timeTableItems);

    @Query("DELETE FROM time_table")
    void deleteTimeTable();

}
