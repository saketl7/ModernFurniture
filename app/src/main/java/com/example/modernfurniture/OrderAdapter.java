package com.example.modernfurniture;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    public ArrayList<getOrderData> mlist;

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        public OrderViewHolder(View itemView){
            super(itemView);

        }
    }

    public OrderAdapter(ArrayList<getOrderData> list){
        mlist = list;

    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
