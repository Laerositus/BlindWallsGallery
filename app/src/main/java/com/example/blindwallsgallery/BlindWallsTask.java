package com.example.blindwallsgallery;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
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

    public List<Mural> makeMuralFromApi(String response){
        List<Mural> murals=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray results=jsonObject.getJSONArray("");

            for(int n=0;n<results.length();n++) {

                JSONObject mural = results.getJSONObject(0);

                int id = mural.getInt("id");
                int authorID = mural.getInt("authorID");
                int numberOnMap = mural.getInt("numberOnMap");
                int year = mural.getInt("year");

                double rating=mural.getDouble("rating");

                String videoUrl=mural.getString("url");

                String date = mural.getString("date");
                String address = mural.getString("address");
                String photographer = mural.getString("photographer");
                String videoAuthor = mural.getString("videoAuthor");
                String author = mural.getString("author");

                JSONObject title = mural.getJSONObject("title");
                String titleEN = title.getString("en");
                String titleNL = title.getString("nl");

                JSONObject description = mural.getJSONObject("description");
                String descEN = description.getString("en");
                String descNL = description.getString("nl");

                JSONObject material = mural.getJSONObject("material");
                String materialEN = material.getString("en");
                String materialNL = material.getString("nl");

                JSONObject category = mural.getJSONObject("category");
                String categoryEN = category.getString("en");
                String categoryNL = category.getString("nl");

                JSONArray images = mural.getJSONArray("images");
                List<String> imageUrls = new ArrayList<>();

                for (int i = 0; i < images.length(); i++) {
                    String url = "https://api.blindwalls.gallery/" + images.getJSONObject(i).getString("url");
                    imageUrls.add(url);
                }
                murals.add(new Mural(id,date,authorID,address,numberOnMap,videoUrl,year,photographer,videoAuthor,author,rating,titleEN,titleNL,descEN,descEN,descNL,descNL,materialEN,materialNL,categoryEN,categoryNL,imageUrls));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return murals;
    }
}
