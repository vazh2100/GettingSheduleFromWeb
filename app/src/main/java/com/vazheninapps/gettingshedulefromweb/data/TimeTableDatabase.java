package com.vazheninapps.gettingshedulefromweb.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TimeTableItem.class}, version = 1, exportSchema = false)
public abstract class TimeTableDatabase extends RoomDatabase {
    private static final String DB_NAME = "time_table.db";
    private static TimeTableDatabase database;
    private static final Object LOCK = new Object();


    public static TimeTableDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, TimeTableDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
            return database;
        }
    }

    public abstract TimeTableDao timeTableDao();

}
