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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WallsAdapter extends RecyclerView.Adapter<WallsAdapter.AuthorViewHolder> {

    private static int viewHolderCount;
    private int mNumberOfItems;
    private ItemClickListener mOnClickListener;
    private ImageView mAuthorImage;
    private BlindWallsTask blindWallsTask;



    public WallsAdapter(int mNumberOfItems, ItemClickListener listener) {
        this.mOnClickListener=listener;
        this.mNumberOfItems = mNumberOfItems;
        viewHolderCount=0;
    }

    public interface ItemClickListener{
        void onItemClick(int index);
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context=viewGroup.getContext();
        int layoutIdForListItem=R.layout.list_murals;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParent=false;

        View view=inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParent);
        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder authorViewHolder, int i) {
        authorViewHolder.bind("Men");
    }

    @Override
    public int getItemCount() {return mNumberOfItems;}

    public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mListMurals;

        public AuthorViewHolder(View itemView) {
            super(itemView);

            mListMurals=(TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPos=getAdapterPosition();
            mOnClickListener.onItemClick(clickPos);

            Log.d("TAG", "onClick was called");
        }

        public void bind(String text){
            mListMurals.setText(text);
        }


    }


}
