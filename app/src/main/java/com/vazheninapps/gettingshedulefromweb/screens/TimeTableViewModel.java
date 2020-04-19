package com.vazheninapps.gettingshedulefromweb.screens;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.vazheninapps.gettingshedulefromweb.R;
import com.vazheninapps.gettingshedulefromweb.data.TimeTableDatabase;
import com.vazheninapps.gettingshedulefromweb.data.TimeTableItem;
import com.vazheninapps.gettingshedulefromweb.modules.FromJSONGetter;
import com.vazheninapps.gettingshedulefromweb.modules.FromWebDownloader;
import org.json.JSONArray;
import java.util.List;

class TimeTableViewModel extends AndroidViewModel implements LoaderManager.LoaderCallbacks<JSONArray> {

    private static TimeTableDatabase database;
    private LiveData<List<TimeTableItem>> timeTable;

    private static final int LOADER_ID = 131;
    private LoaderManager loaderManager;

    <T extends LifecycleOwner & ViewModelStoreOwner> TimeTableViewModel(@NonNull Application application, T owner) {
        super(application);
        database = TimeTableDatabase.getInstance(application);
        timeTable = database.timeTableDao().getTimeTable();
        loaderManager = LoaderManager.getInstance(owner);
    }

    LiveData<List<TimeTableItem>> getTimeTable() {
        return timeTable;
    }




    //loader



    void loadData() {
        Bundle bundle = new Bundle();
        if (FromWebDownloader.isInternetConnection(getApplication())) {
            loaderManager.restartLoader(LOADER_ID, bundle, this);
        } else {
            Toast.makeText(getApplication(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        }
    }


    @NonNull
    @Override
    public Loader<JSONArray> onCreateLoader(int id, @Nullable Bundle args) {
        return new FromWebDownloader.JSONLoader(getApplication());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<JSONArray> loader, JSONArray data) {
        List<TimeTableItem> timeTable = FromJSONGetter.getTimeTable(data);
        deleteTimeTable();
        insertTimeTable(timeTable);
        loaderManager.destroyLoader(LOADER_ID);
    }


    @Override
    public void onLoaderReset(@NonNull Loader loader) {
    }


    //database operations

    @SuppressWarnings("unchecked")
    private void insertTimeTable(List<TimeTableItem> timeTableItems) {
        new InsertTimeTableTask().execute(timeTableItems);
    }

    private void deleteTimeTable() {
        new DeleteTimeTableTask().execute();
    }

    private static class InsertTimeTableTask extends AsyncTask<List<TimeTableItem>, Void, Void> {

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<TimeTableItem>... lists) {
            if (lists != null && lists.length > 0) {
                database.timeTableDao().insertTimeTable(lists[0]);
            }
            return null;
        }
    }

    private static class DeleteTimeTableTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            database.timeTableDao().deleteTimeTable();
            return null;
        }
    }

}
