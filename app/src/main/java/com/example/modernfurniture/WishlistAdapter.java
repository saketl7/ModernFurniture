package com.example.modernfurniture;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>{
    public ArrayList<getWishlistData> ulist;
    private WishlistAdapter.OnItemClickListner mListener;

    public interface OnItemClickListner{
        void onDeleteClick(int position);
    }

    public void  setOnItemClickListener(WishlistAdapter.OnItemClickListner listner){
        mListener = listner;
    }

    public static class WishlistViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage, mdelete;
        public WishlistViewHolder(View itemView, WishlistAdapter.OnItemClickListner listner){
            super(itemView);
            mimage = itemView.findViewById(R.id.Wimage);
            mdelete = itemView.findViewById(R.id.Wdelete);


            mdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public WishlistAdapter(ArrayList<getWishlistData> list){
        ulist = list;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_items, parent, false);
        return new WishlistAdapter.WishlistViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        getWishlistData current = ulist.get(position);
        Picasso.get().load(current.getImage()).into(holder.mimage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WishlistDetails.class);
                intent.putExtra("Wdetails",ulist.get(position));
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ulist.size();
    }


}
