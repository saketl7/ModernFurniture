package com.example.modernfurniture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserReportAdapter extends RecyclerView.Adapter<UserReportAdapter.UserViewHolder> {
    public ArrayList<getUserData> ulist;
    public ArrayList<getUserData> filteredList;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView uname,uemail,uphone;
        public UserViewHolder(View itemView) {
            super(itemView);
            uname = itemView.findViewById(R.id.Uname);
            uemail = itemView.findViewById(R.id.Uemail);
            uphone = itemView.findViewById(R.id.UPno);
        }
    }

    public UserReportAdapter(ArrayList<getUserData> list){
        ulist = list;
        this.filteredList = ulist;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_items, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserReportAdapter.UserViewHolder holder, int position) {
        getUserData current = filteredList.get(position);
        holder.uname.setText(String.valueOf(current.getfName()));
        holder.uemail.setText(String.valueOf(current.getEmail()));
        holder.uphone.setText(String.valueOf(current.getPhone()));

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    //search data
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if(key.isEmpty()){
                    filteredList = ulist;
                }
                else {
                    ArrayList<getUserData> isfiltered = new ArrayList<>();
                    for(getUserData row: ulist){
                        if(row.getfName().toLowerCase().contains(key.toLowerCase())){
                            isfiltered.add(row);
                        }
                    }
                    filteredList = isfiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                filteredList = (ArrayList<getUserData>)filterResults.values;
                notifyDataSetChanged();

            }
        };
    }
}
