package com.example.modernfurniture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>{
    public ArrayList<getCartData> mlist;

    public static class SummaryViewHolder extends RecyclerView.ViewHolder{
        public ImageView mimage;
        public TextView mname,mprice,mquantity;
        public SummaryViewHolder(View itemView){
            super(itemView);
            mname = itemView.findViewById(R.id.SCname);
            mprice = itemView.findViewById(R.id.SCprice);
            mquantity = itemView.findViewById(R.id.SCquantity);
            mimage = itemView.findViewById(R.id.SCimage);

        }
    }

    public SummaryAdapter(ArrayList<getCartData> list){
        mlist = list;

    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_items, parent, false);
        return new SummaryAdapter.SummaryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        getCartData current = mlist.get(position);
        holder.mname.setText(current.getName());
        holder.mprice.setText(String.valueOf(current.getPrice()));
        holder.mquantity.setText(String.valueOf(current.getQuantity()));
        Picasso.get().load(current.getImage()).into(holder.mimage);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



}
