package com.example.blindwallsgallery;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class BlindWallsTask extends AsyncTask<Void, Void, String> {

    final private String TAG = BlindWallsTask.class.getSimpleName();
    final private String mBlindWallsApi = "https://api.blindwalls.gallery/apiv2/murels";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG, "doinbackground was called");

        String response = null;

        try {
            URL mUrl = new URL(mBlindWallsApi);
            URLConnection urlConnection = mUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {

                InputStream in = httpURLConnection.getInputStream();

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    response = scanner.next();
                }
                Log.d(TAG, response);

            }else {
                Log.e(TAG, "Er was een fout: code = " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG,"onPostExecute() was called.");
        Log.d(TAG,"Response: "+s);

    }


}
