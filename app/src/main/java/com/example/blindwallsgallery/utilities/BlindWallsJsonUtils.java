package com.example.blindwallsgallery.utilities;

import android.util.Log;

import com.example.blindwallsgallery.data.Mural;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for all the utilities needed with JSON
 */
public class BlindWallsJsonUtils {
    private static final String TAG= BlindWallsJsonUtils.class.getSimpleName();

    /**
     * Method to make a simple Mural from a JSON format string
     * @param json String given in JSON format
     * @return Mural
     * @throws JSONException Exception
     */
    public static Mural makeMuralFromJson(String json) throws JSONException{
        Mural mural;
        Log.d(TAG, "makeMuralFromJSON was called");

        //Log.w(TAG, json);
        JSONObject result=new JSONObject(json);

        int id=result.getInt("id");
        double latitude = result.getDouble("latitude");
        double longitude = result.getDouble("longitude");
        String address=result.getString("address");
        int numberOnMap=result.getInt("numberOnMap");
        String photographer=result.getString("photographer");

        JSONObject title=result.getJSONObject("title");
        String titleEN=title.getString("en");
        String titleNL=title.getString("nl");

        JSONObject description=result.getJSONObject("description");
        String descEN=description.getString("en");
        String descNL=description.getString("nl");

        JSONObject material=result.getJSONObject("material");
        String materialEN=material.getString("en");
        String materialNL=material.getString("nl");

        JSONArray images = result.getJSONArray("images");
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < images.length(); i++) {
            String tempUrl=images.getJSONObject(i).getString("file");
            String url = "https://api.blindwalls.gallery/" + tempUrl;
            imageUrls.add(url);
        }
        mural=new Mural(id,latitude,longitude,address,numberOnMap,photographer,titleEN,titleNL,descEN,descNL,materialEN,materialNL,imageUrls);
        return mural;
    }

    /**
     * Method to make a Mural from the API
     * @param response Response frm the HTTP request
     * @return List of all Murals given by API
     * @throws JSONException Exception
     */
    static List<Mural> makeMuralFromApi(String response)throws JSONException {
        Log.d(TAG,"makeMuralFromApi was called");

        List<Mural>murals=new ArrayList<>();

        JSONArray results=new JSONArray(response);

        for(int n=0;n<results.length();n++) {
            JSONObject mural = results.getJSONObject(n);
            int id = mural.getInt("id");
            double latitude = mural.getDouble("latitude");
            double longitude = mural.getDouble("longitude");
            int authorID = mural.getInt("authorID");
            int numberOnMap = mural.getInt("numberOnMap");
            int year = mural.getInt("year");
            String rating=mural.getString("rating");
            String videoUrl=mural.getString("url");
            String date = mural.getString("date");
            String address = mural.getString("address");
            String photographer = mural.getString("photographer");
            String videoAuthor = mural.getString("videoAuthor");
            String author = mural.getString("author");
            JSONObject title = mural.getJSONObject("title");
            String titleEN = title.getString("en");
            String titleNL = title.getString("nl");
            JSONObject material = mural.getJSONObject("material");
            String materialEN = material.getString("en");
            String materialNL = material.getString("nl");
            JSONObject category = mural.getJSONObject("category");
            String categoryEN = category.getString("en");
            String categoryNL = category.getString("nl");

            JSONArray images = mural.getJSONArray("images");
            List<String> imageUrls = new ArrayList<>();
            for (int i = 0; i < images.length(); i++) {
                String tempUrl=images.getJSONObject(i).getString("file");
                String url = "https://api.blindwalls.gallery/" + tempUrl;
                imageUrls.add(url);
            }

            JSONObject description = mural.getJSONObject("description");
            String descrEN = description.getString("en");
            String descrNL = description.getString("nl");


            Mural muralObject=new Mural(id,latitude,longitude,date,authorID,address,numberOnMap,videoUrl,year,photographer,videoAuthor,author,rating,titleEN,titleNL,descrEN,descrNL,materialEN,materialNL,categoryEN,categoryNL,imageUrls);
            //Log.d(TAG,mural.toString());
            murals.add(muralObject);

        }

        return murals;
    }

    /**
     * Constructs a String in JSON Format from the mural given.
     * @param mural Mural to be set into JSON
     * @return String of JSON formatted Mural
     */
    public static String makeJsonFromMural(Mural mural){
        Log.d(TAG, "makeJsonFromMural was called");
        int id=mural.getId();
        String idS="\"id\":"+id+",";

        String titleEN=mural.getTitleEN();
        String titleNL=mural.getTitleNL();

        String title="\"title\":{";
        String titleEnS="\"en\":\""+titleEN+"\",";
        String titleNlS="\"nl\":\""+titleNL+"\"},";

        String materialEN=mural.getMaterialEN();
        String materialNL=mural.getMaterialNL();
        String material="\"material\":{";
        String materialEnS="\"en\":\""+materialEN+"\",";
        String materialNlS="\"nl\":\""+materialNL+"\"},";

        double latitude=mural.getLatitude();
        String latitudeS="\"latitude\":\""+latitude+"\",";

        double longitude=mural.getLongitude();
        String longitudeS="\"longitude\":\""+longitude+"\",";


        String address=mural.getAddress();
        String addressS="\"address\":\""+address+"\",";

        int numberOnMap=mural.getNumberOnMap();
        String numberOnMapS="\"numberOnMap\":"+numberOnMap+",";

        String photographer=mural.getPhotographer();
        String photographerS="\"photographer\":\""+photographer+"\",";

        String descEN=mural.getDescEN().replaceAll("\"", "");
        String descNL=mural.getDescNL().replaceAll("\"", "");

        String description="\"description\":{";
        String descEnS="\"en\":\""+descEN+"\",";
        String descNlS="\"nl\":\""+descNL+"\"},";

        List<String> imageUrls=mural.getImageUrls();

        String images="\"images\":[";
        String muralJson="{"+idS+latitudeS+longitudeS+addressS+numberOnMapS+photographerS+title+titleEnS+titleNlS+description+descEnS+descNlS+material+materialEnS+materialNlS+images;

        for(int i=0;i<imageUrls.size();i++) {
            String url=imageUrls.get(i);
            String file = "{\"file\":\""+url.substring(31,url.length())+"\"},";
            muralJson=muralJson+file;
        }
        muralJson=muralJson.substring(0,muralJson.length()-1)+"]}";

        return muralJson;
    }
}
