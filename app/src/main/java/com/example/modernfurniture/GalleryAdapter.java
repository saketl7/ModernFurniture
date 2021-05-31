package com.example.modernfurniture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>{
    public ArrayList<Products> ulist;
    public ArrayList<Products> filteredList;
    private Context context;

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView gname,Gprice,gtype;
        public GalleryViewHolder(View itemView){
            super(itemView);
            mimage = itemView.findViewById(R.id.Gimage);
            gname = itemView.findViewById(R.id.Gnamefill);
            Gprice = itemView.findViewById(R.id.Gpricefill);
            gtype = itemView.findViewById(R.id.Gtype);

        }
    }

    public GalleryAdapter(ArrayList<Products> list){
        ulist = list;
        this.filteredList = ulist;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_items, parent, false);
        return new GalleryAdapter.GalleryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        Products current = filteredList.get(position);
        holder.gname.setText(String.valueOf(current.getName()));
        holder.Gprice.setText(String.valueOf(current.getPrice()));
        holder.gtype.setText(String.valueOf(current.getType()));
        Picasso.get().load(current.getImageUrl()).into(holder.mimage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsPage.class);
                intent.putExtra("details",filteredList.get(position));
                v.getContext().startActivity(intent);
            }
        });

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
                    ArrayList<Products> isfiltered = new ArrayList<>();
                    for(Products row: ulist){
                        if(row.getName().toLowerCase().contains(key.toLowerCase())){
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
                filteredList = (ArrayList<Products>)filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

}
