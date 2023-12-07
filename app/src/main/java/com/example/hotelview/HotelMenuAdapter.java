package com.example.hotelview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelview.calendardetails.CalendarConstructor;
import com.example.hotelview.hotelmenudetails.HotelMenuConstructor;

import java.util.List;

public class HotelMenuAdapter extends RecyclerView.Adapter<HotelMenuStructure> {

    Context context;
    List<HotelMenuConstructor> items;

    public HotelMenuAdapter(Context context, List<HotelMenuConstructor> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public HotelMenuStructure onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotelMenuStructure(LayoutInflater.from(context).inflate(R.layout.activity_hotel_menu_structure, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull HotelMenuStructure holder, int position) {
        if (items != null && position < items.size()) {
            final HotelMenuConstructor item = items.get(position);

            holder.hotelName.setText(item.getHotelName());
            holder.hotelAddress.setText(item.getHotelAddress());
            holder.hotelPriceNight.setText("CAD $" + item.getHotelPriceNight()  );
            holder.hotelPicture.setImageResource(item.getHotelPicture());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
