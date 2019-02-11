package com.example.blindwallsgallery;

import com.example.blindwallsgallery.data.Mural;
import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the detailed view of a selected mural
 */
public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();

    private ImageView mDetailImgMural;
    private TextView mDetailTitle;
    private TextView mDetailMaterial;
    private TextView mDetailAddress;
    private TextView mDetailPhotographer;
    private TextView mDetailMuralDescription;
    private String mMuralString;
    private List<String> imageUrls;
    private Mural m;
    private String language;

    /**
     * Main onCreate method to create the screen
     * @param savedInstanceState Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
        setContentView(R.layout.activity_detail);

        mDetailImgMural = findViewById(R.id.img_detail_author_image);
        mDetailTitle = findViewById(R.id.tv_title_detail);
        mDetailMaterial = findViewById(R.id.tv_material);
        mDetailAddress = findViewById(R.id.tv_address);
        mDetailPhotographer = findViewById(R.id.tv_photographer);
        mDetailMuralDescription = findViewById(R.id.tv_mural_description);

        Intent parentIntent = getIntent();
        getIntentExtra(parentIntent);

        addListener();
    }

    /**
     * Gets the content from MainActivity that was put in the Intent
     * @param parentIntent Intent
     */
    public void getIntentExtra(Intent parentIntent){
        Log.d(TAG, "getIntentExtra: called");
        if (parentIntent != null) {
            //Checks if there is an extra called "language"
            if (parentIntent.hasExtra("language")) {
                language = parentIntent.getStringExtra("language");
            }
            //Checks if there is an extra called "mural"
            if (parentIntent.hasExtra("mural")) {
                mMuralString = parentIntent.getStringExtra("mural");
                try {
                    //Remakes the mural from Json
                    m=BlindWallsJsonUtils.makeMuralFromJson(mMuralString);
                    insertDetails(m);
                    imageUrls = m.getImageUrls();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Inserts the details into the views for the detailed view.
     * @param m Mural
     */
    public void insertDetails(Mural m){
        Log.d(TAG, "insertDetails: called");
        Uri firstImage=Uri.parse(m.getImageUrls().get(0));

        Picasso.get().load(firstImage).into(mDetailImgMural);
        mDetailImgMural.setAdjustViewBounds(true);

        //Checks what language should be used
        if (language.equals("nl")) {

            mDetailTitle.setText(m.getTitleNL());
            mDetailMuralDescription.setText(m.getDescNL());
            mDetailMaterial.setText("Materiaal: " + m.getMaterialNL());
            mDetailPhotographer.setText("Fotograaf: " + m.getPhotographer());
            mDetailAddress.setText("Adres: " + m.getAddress());

        } else if (language.equals("en")){
            mDetailTitle.setText(m.getTitleEN());
            mDetailMuralDescription.setText(m.getDescEN());
            mDetailMaterial.setText("Material: " + m.getMaterialEN());
            mDetailPhotographer.setText("Photographer: " + m.getPhotographer());
            mDetailAddress.setText("Address: " + m.getAddress());
        }
    }

    /**
     * Listener for the view of the image to go to the PhotoActivity with an arrayList of the URLs to display
     */
    public void addListener() {
        Log.d(TAG, "addListener: called");
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Class destination = PhotoActivity.class;
                Intent photoIntent = new Intent(context, destination);
                ArrayList<String> images = (ArrayList<String>) imageUrls;
                photoIntent.putStringArrayListExtra("imageUrls", images);
                startActivity(photoIntent);
            }
        };
        mDetailImgMural.setOnClickListener(listener);
    }

    /**
     * Listener for the button to open a Maps screen with the location of the mural
     * @param v View
     */
    public void onClickOpenMapButton(View v) {
        Log.d(TAG, "onClickOpenMapButton: called");
        Log.i(TAG, "onClickOpenMapButton: coords(LatLng): " + m.getLatitude() + ", " + m.getLongitude());
        Context context=this;
        Class destination=MapsActivity.class;
        Intent detailIntent=new Intent(context,destination);
        detailIntent.putExtra("latitude", m.getLatitude());
        detailIntent.putExtra("longitude",m.getLongitude());
        detailIntent.putExtra("author",m.getAuthor());
        startActivity(detailIntent);
    }
}


