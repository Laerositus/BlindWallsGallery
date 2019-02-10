package com.example.blindwallsgallery;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blindwallsgallery.data.Mural;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosAdapterViewHolder> {

    private static final String TAG= PhotosAdapter.class.getSimpleName();
    private List<String> mPhotos;
    private static String activity;

    PhotosAdapter(ArrayList<String> mPhotos) {
        this.mPhotos = mPhotos;
    }

    class PhotosAdapterViewHolder extends RecyclerView.ViewHolder{

        private final ImageView mPhotoImage;

        PhotosAdapterViewHolder(View itemView) {
            super(itemView);
            mPhotoImage=itemView.findViewById(R.id.img_photo_image);
        }
    }

    @NonNull
    @Override
    public PhotosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder was called");
        Log.i(TAG, ""+i);

        Context context=viewGroup.getContext();
        int layoutId=R.layout.activity_photo_view_cell;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParent=false;
        View view=inflater.inflate(layoutId,viewGroup,shouldAttachToParent);
        return new PhotosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapterViewHolder photosAdapterViewHolder, int i) {
        Log.d(TAG,"onBindViewHolder was called");
        String photoToPlace=mPhotos.get(i);
        Uri firstImage=Uri.parse(photoToPlace);

        Log.i(TAG, "Uri: "+firstImage);
        Picasso.get().load(firstImage).into(photosAdapterViewHolder.mPhotoImage);
    }

    @Override
    public int getItemCount() {
        if(mPhotos==null) return 0;
        return mPhotos.size();
    }
}
