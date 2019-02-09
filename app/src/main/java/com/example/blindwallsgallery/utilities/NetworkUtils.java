package com.example.blindwallsgallery.utilities;

import android.net.Uri;
import android.util.JsonReader;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = BlindWallsTask.class.getSimpleName();

    private static final String mBlindWallsApi = "https://api.blindwalls.gallery/apiv2/murals";

    private static final String format="json";

    private static final String FORMAT_PARAM="mode";

    public static URL buildUrl() {
        Log.e("DEBUG", "buildUrl was called");
        Uri builtUri = Uri.parse(mBlindWallsApi).buildUpon()
                .appendQueryParameter(FORMAT_PARAM, format)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        Log.e("DEBUG","getResponseFromHttpUrl was called");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Content-Type","application/json");
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();

//            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter("\\A");
//
//            boolean hasInput = scanner.hasNext();
//            if (hasInput) {
//                String json = scanner.next();
//                Log.d(TAG, json);
//                //json = json.replaceAll("\"\"", "\"");
//                return json;
//            } else {
//                return null;
//            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
