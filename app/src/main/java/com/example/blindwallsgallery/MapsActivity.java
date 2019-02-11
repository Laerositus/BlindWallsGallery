package com.example.blindwallsgallery;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Class for the Activity to show the Maps screen
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String author;
    private double latitude;
    private double longitude;

    /**
     * Main method to cerate the instance
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Intent parentIntent=getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra("author")){
                author=parentIntent.getStringExtra("mural");
            }
            if(parentIntent.hasExtra("latitude")) {
                latitude = parentIntent.getDoubleExtra("latitude", 1.0);
            }
            if(parentIntent.hasExtra("longitude")) {
                longitude = parentIntent.getDoubleExtra("longitude", 1.0);
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker on coords and move the camera
        LatLng coords = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(coords).title(author));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coords, 19.0f), 3000, null);
    }
}
