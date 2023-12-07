package com.example.hotelview.hoteldetails;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.hotelview.R;

public class HotelDetailsAdapter extends RecyclerView.Adapter<HotelDetailsAdapter.HotelDetailsViewHolder> {
    private Context context;
    private Cursor cursor;

    public HotelDetailsAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public class HotelDetailsViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitleHotel, txtDescription, txtAmenitiesD, txtExactLocation;

        public HotelDetailsViewHolder(View itemView) {
            super(itemView);
            txtTitleHotel = itemView.findViewById(R.id.txtTitleHotel);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtAmenitiesD = itemView.findViewById(R.id.txtAmenitiesD);
            txtExactLocation = itemView.findViewById(R.id.txtExactLocation);
        }
    }

    @Override
    public HotelDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new HotelDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotelDetailsViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            int titleHotelIndex = cursor.getColumnIndex("txtTitleHotel");
            int descriptionIndex = cursor.getColumnIndex("txtDescription");
            int amenitiesDIndex = cursor.getColumnIndex("txtAmenitiesD");
            int exactLocationIndex = cursor.getColumnIndex("txtExactLocation");

            if (titleHotelIndex != -1) {
                holder.txtTitleHotel.setText(cursor.getString(titleHotelIndex));
            }

            if (descriptionIndex != -1) {
                holder.txtDescription.setText(cursor.getString(descriptionIndex));
            }

            if (amenitiesDIndex != -1) {
                holder.txtAmenitiesD.setText(cursor.getString(amenitiesDIndex));
            }

            if (exactLocationIndex != -1) {
                holder.txtExactLocation.setText(cursor.getString(exactLocationIndex));
            }
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
