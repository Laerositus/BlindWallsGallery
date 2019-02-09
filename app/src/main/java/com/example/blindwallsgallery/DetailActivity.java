package com.example.blindwallsgallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blindwallsgallery.data.Mural;
import com.example.blindwallsgallery.utilities.BlindWallsJsonUtils;
import com.example.blindwallsgallery.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    private ImageView mDetailImgMural;
    private TextView mDetailTitle;
    private TextView mDetailMuralInfo;
    private TextView mDetailPhotographer;
    private TextView mDetailMuralDescription;
    private String mMuralString;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDetailImgMural=findViewById(R.id.img_detail_author_image);
        mDetailTitle=findViewById(R.id.tv_title_detail);
        mDetailMuralInfo=findViewById(R.id.tv_mural_info);
        mDetailPhotographer=findViewById(R.id.tv_photographer);
        mDetailMuralDescription=findViewById(R.id.tv_mural_description);

        Intent parentIntent=getIntent();

        if(parentIntent!=null){
            if(parentIntent.hasExtra("mural")){
                mMuralString=parentIntent.getStringExtra("mural");
                try {
                    Mural m=BlindWallsJsonUtils.makeMuralFromJson(mMuralString);
                    insertDetails(m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void insertDetails(Mural m){
        Uri firstImage=Uri.parse(m.getImageUrls().get(0));
        Picasso.get().load(firstImage).into(mDetailImgMural);

        mDetailTitle.setText(m.getTitleEN());
        mDetailMuralDescription.setText(m.getDescEN());
        mDetailPhotographer.setText(m.getPhotographer());
        mDetailMuralInfo.setText("Material: "+m.getMaterialEN()+"\n\n"+"Address: "+m.getAddress()+" "+m.getNumberOnMap());
    }

}
