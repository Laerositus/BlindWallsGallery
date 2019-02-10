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

import com.example.blindwallsgallery.data.Mural;
import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;
import com.example.blindwallsgallery.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity{
    private static final String TAG= DetailActivity.class.getSimpleName();

    private ImageView mDetailImgMural;

    private TextView mDetailTitle;
    private TextView mDetailMaterial;
    private TextView mDetailAddress;
    private TextView mDetailPhotographer;
    private TextView mDetailMuralDescription;
    private String mMuralString;
    private List<String> imageUrls;


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
                    Mural m=BlindWallsJsonUtils.makeMuralFromJson(mMuralString);
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
        Uri firstImage=Uri.parse(m.getImageUrls().get(0));
        Log.d(TAG, firstImage.toString());
        Picasso.get().load(firstImage).into(mDetailImgMural);
        mDetailImgMural.setAdjustViewBounds(true);

        mDetailTitle.setText(m.getTitleEN());
        mDetailMuralDescription.setText(m.getDescEN());
        mDetailPhotographer.setText("Photographer: "+m.getPhotographer());
        mDetailMaterial.setText("Material: "+m.getMaterialEN());
        mDetailAddress.setText("Address: "+m.getAddress());
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

}
