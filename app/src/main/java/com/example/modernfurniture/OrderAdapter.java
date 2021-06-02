package com.example.modernfurniture;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    public ArrayList<getOrderData> mlist;

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        public TextView oid,oaddress,odate,otime,omethod,oname;
        public OrderViewHolder(View itemView){
            super(itemView);
            oaddress = itemView.findViewById(R.id.oaddress);
            odate = itemView.findViewById(R.id.odate);
            otime = itemView.findViewById(R.id.otime);
            oid = itemView.findViewById(R.id.oid);
            omethod = itemView.findViewById(R.id.omethod);
            oname = itemView.findViewById(R.id.oname);

        }
    }

    public OrderAdapter(ArrayList<getOrderData> list){
        mlist = list;

    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items, parent, false);
        return new OrderAdapter.OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        getOrderData current = mlist.get(position);
        holder.omethod.setText(String.valueOf(current.getMethod()));
        holder.oid.setText(String.valueOf(current.getOrderId()));
        holder.otime.setText(String.valueOf(current.getTime()));
        holder.odate.setText(String.valueOf(current.getDate()));
        holder.oaddress.setText(String.valueOf(current.getAddress()));
        holder.oname.setText(String.valueOf(current.getUserName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Summary.class);
                intent.putExtra("SSdetails",current.getOrderId());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

}
