package com.example.blindwallsgallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WallsAdapter extends RecyclerView.Adapter<WallsAdapter.AuthorViewHolder> {

    private static int viewHolderCount;
    private int mNumberOfItems;
    private final ItemClickListener mOnClickListener;

    public WallsAdapter(int mNumberOfItems, ItemClickListener listener) {
        this.mOnClickListener=listener;
        this.mNumberOfItems = mNumberOfItems;
        viewHolderCount=0;
    }

    public interface ItemClickListener{
        void onItemClick(int index);
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context=viewGroup.getContext();
//        int layoutIdForListItem=R.layout.
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttatchToParent=false;

//        View view=inflater.inflate(layoutIdForListItem,viewGroup,shouldAttatchToParent );
        return new AuthorViewHolder();
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder authorViewHolder, int i) { }

    @Override
    public int getItemCount() {return mNumberOfItems;}

    public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public AuthorViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPos=getAdapterPosition();
            mOnClickListener.onItemClick(clickPos);
        }
    }
}
