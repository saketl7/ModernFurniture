package com.example.modernfurniture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    public ArrayList<getCartData> mlist;
    private int totalAmount = 0;
    Context context;
    private  OnItemClickListner mListener;

    public interface OnItemClickListner{
        void onDeleteClick(int position);
    }

    public void  setOnItemClickListener(OnItemClickListner listner){
        mListener = listner;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{

        public ImageView mimage, mdelete;
        public TextView mname,mprice,mquantity;

        public CartViewHolder(View itemView, OnItemClickListner listner) {
            super(itemView);
            mname = itemView.findViewById(R.id.Cname);
            mprice = itemView.findViewById(R.id.Cprice);
            mquantity = itemView.findViewById(R.id.Cquantity);
            mimage = itemView.findViewById(R.id.Cimage);
            mdelete = itemView.findViewById(R.id.Cdelete);


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

    public CartAdapter(ArrayList<getCartData> list){
        mlist = list;

    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false);
        return new CartViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        getCartData current = mlist.get(position);
        holder.mname.setText(current.getName());
        holder.mprice.setText(String.valueOf(current.getPrice()));
        holder.mquantity.setText(String.valueOf(current.getQuantity()));
        Picasso.get().load(current.getImage()).into(holder.mimage);

        int oneProductPrice = (int) ((current.getPrice()) * (current.getQuantity()));
        totalAmount = totalAmount + oneProductPrice;
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("TotalAmount", totalAmount);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}

