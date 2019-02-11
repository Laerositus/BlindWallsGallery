package com.example.blindwallsgallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blindwallsgallery.data.Mural;
import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;
import com.example.blindwallsgallery.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity{
    private static final String TAG= DetailActivity.class.getSimpleName();
    private String language = "en";


    private ImageView mDetailImgMural;

    private TextView mDetailTitle;
    private TextView mDetailMaterial;
    private TextView mDetailAddress;
    private TextView mDetailPhotographer;
    private TextView mDetailMuralDescription;
    private String mMuralString;
    private List<String> imageUrls;
    private Mural m;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDetailImgMural=findViewById(R.id.img_detail_author_image);
        mDetailTitle=findViewById(R.id.tv_title_detail);
        mDetailMaterial=findViewById(R.id.tv_material);
        mDetailAddress=findViewById(R.id.tv_address);
        mDetailPhotographer=findViewById(R.id.tv_photographer);
        mDetailMuralDescription=findViewById(R.id.tv_mural_description);

        Intent parentIntent=getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra("mural")){
                mMuralString=parentIntent.getStringExtra("mural");
                try {
                    m=BlindWallsJsonUtils.makeMuralFromJson(mMuralString);
                    insertDetails(m);
                    imageUrls=m.getImageUrls();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        addListener();
    }

    public void insertDetails(Mural m){
        Log.d(TAG, "insertDetails was called");
        Uri firstImage=Uri.parse(m.getImageUrls().get(0));
        Log.i(TAG, firstImage.toString());
        Picasso.get().load(firstImage).into(mDetailImgMural);
        mDetailImgMural.setAdjustViewBounds(true);
        Log.d(TAG, "Language= " +language);

        if (language.equals("nl")) {
            mDetailTitle.setText(m.getTitleNL());
            mDetailMuralDescription.setText(m.getDescNL());
            mDetailMaterial.setText("Materiaal: "+m.getMaterialNL());
            mDetailPhotographer.setText("Fotograaf: "+m.getPhotographer());
            mDetailAddress.setText("Adres: "+m.getAddress());
        }

        else {
            mDetailTitle.setText(m.getTitleEN());
            mDetailMuralDescription.setText(m.getDescEN());
            mDetailMaterial.setText("Material: "+m.getMaterialEN());
            mDetailPhotographer.setText("Photographer: "+m.getPhotographer());
            mDetailAddress.setText("Address: "+m.getAddress());
        }

    }

    public void addListener(){
        View.OnClickListener listener=new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Context context=v.getContext();
                Class destination= PhotoActivity.class;
                Intent photoIntent=new Intent(context,destination);
                ArrayList<String> images=(ArrayList<String>) imageUrls;
                photoIntent.putStringArrayListExtra("imageUrls", images);
                startActivity(photoIntent);
            }
        };
        mDetailImgMural.setOnClickListener(listener);
    }

    public void onClickOpenAddressButton(View v) {

        String addressString = m.getAddress() + ", Breda";
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .appendQueryParameter("q", addressString);
        Uri addressUri = builder.build();
        showMap(addressUri);
    }

    private void showMap(Uri geoLocation) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(geoLocation);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
        Log.i(TAG, "showMap: coords(LatLng): " + m.getLatitude() + ", " + m.getLongitude());
        Context context=this;
        Class destination=MapsActivity.class;
        Intent detailIntent=new Intent(context,destination);
        detailIntent.putExtra("latitude", m.getLatitude());
        detailIntent.putExtra("longitude",m.getLongitude());
        detailIntent.putExtra("author",m.getAuthor());
        startActivity(detailIntent);
    }
}
