package com.example.geolocatorapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    List<LocationModel> locations;
    Context context;

    public LocationAdapter(List<LocationModel> locations, Context context) {
        this.locations = locations;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_card,
                viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        System.out.println("LOCATION " + i + " :" + locations.get(i).toString());
        String ad = locations.get(i).getAddress();
        double latitude = locations.get(i).getLatitude();
        double longitude = locations.get(i).getLongitude();

        holder.address.setText("Address: " + ad);
        holder.latitude.setText("Latitude: " + latitude);
        holder.longitude.setText("Longitude: " + longitude);
        holder.setBackgroundColor(i);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        TextView latitude;
        TextView longitude;

        public ViewHolder(View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address_card_header);
            latitude = itemView.findViewById(R.id.latitude_card_header);
            longitude = itemView.findViewById(R.id.longitude_card_header);
        }

        public void setBackgroundColor(int n) {
            if (n % 2 == 0) {
                itemView.setBackgroundColor(Color.parseColor("#b0ff57"));
            } else {
                itemView.setBackgroundColor(Color.parseColor("#76ff03"));
            }
        }
    }
}
