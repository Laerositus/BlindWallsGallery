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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the PhotoViewing class
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosAdapterViewHolder> {

    private static final String TAG= PhotosAdapter.class.getSimpleName();
    private List<String> mPhotos;

    /**
     * Constructor to create a PhotosAdapter Object
     * @param mPhotos ArrayList
     */
    PhotosAdapter(ArrayList<String> mPhotos) {
        this.mPhotos = mPhotos;
    }

    /**
     * Class for the viewHolder for the Adapter
     */
    class PhotosAdapterViewHolder extends RecyclerView.ViewHolder{

        private final ImageView mPhotoImage;

        /**
         * Constructor for the viewHolder
         * @param itemView View
         */
        PhotosAdapterViewHolder(View itemView) {
            super(itemView);
            mPhotoImage=itemView.findViewById(R.id.img_photo_image);
        }
    }

    /**
     *
     * @param viewGroup viewGroup
     * @param i int of the index
     * @return PhotosAdapterViewHolder
     */
    @NonNull
    @Override
    public PhotosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: called");

        Context context=viewGroup.getContext();
        int layoutId=R.layout.activity_photo_view_cell;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParent=false;
        View view=inflater.inflate(layoutId,viewGroup,shouldAttachToParent);
        return new PhotosAdapterViewHolder(view);
    }

    /**
     * Binds the viewHolder with the images
     * @param photosAdapterViewHolder photosAdapterViewHolder
     * @param i index
     */
    @Override
    public void onBindViewHolder(@NonNull PhotosAdapterViewHolder photosAdapterViewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: calleds");

        String photoToPlace=mPhotos.get(i);
        Uri firstImage=Uri.parse(photoToPlace);

        Log.i(TAG, "Uri: "+firstImage);
        Picasso.get().load(firstImage).into(photosAdapterViewHolder.mPhotoImage);
    }

    /**
     * Returns the amount of items in the view
     * @return Integer
     */
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: called");
        if(mPhotos==null) return 0;
        return mPhotos.size();
    }
}
