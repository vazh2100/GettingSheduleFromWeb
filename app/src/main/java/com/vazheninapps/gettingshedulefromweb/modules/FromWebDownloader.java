package com.vazheninapps.gettingshedulefromweb.modules;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FromWebDownloader {

    private static final String BASE_URL = "https://sample.fitnesskit-admin.ru/schedule/get_group_lessons_v2/1/";


    public static class JSONLoader extends AsyncTaskLoader<JSONArray> {
        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }


        public JSONLoader(@NonNull Context context) {
            super(context);
        }


        @Nullable
        @Override
        public JSONArray loadInBackground() {
            JSONArray result = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(BASE_URL);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                result = new JSONArray(builder.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                } }
            return result;
        }

    }

    public static boolean isInternetConnection(Context context) {
        boolean result = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            result = networkInfo != null && networkInfo.isConnected();
        }
        return result;
    }


}
