package com.example.blindwallsgallery.utilities;

import android.content.Context;

import com.example.blindwallsgallery.Mural;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BlindWallsJsonUtils {

    public static String[] makeMuralFromApi(String response)throws JSONException {

//        final int OWM_ID="id";
//        final int OWM_
//        final int OWM_
//        final int OWM_

//        final double OWM_

//        final String OWM_AUTHOR_ID="authorID";
//        final String OWM_NUMBER_ON_MAP="numberOnMap";
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_
//        final String OWM_

        String[] murals;

        JSONObject jsonObject=new JSONObject(response);
        JSONArray results=jsonObject.getJSONArray("");
        murals=new String[results.length()];

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
            murals[n]= id + " - "+ date + " - " + authorID + " - " + address + " - " + numberOnMap + " - " + videoUrl + " - " + year + " - " + photographer + " - " + videoAuthor + " - " + author + " - " + rating + " - " + titleEN + " - " + titleNL + " - " + descEN + " - " + descEN + " - " + descNL + " - " + descNL + " - " + materialEN + " - " + materialNL + " - " + categoryEN + " - " + categoryNL + " - " + imageUrls;
        }
        return murals;
    }
}
