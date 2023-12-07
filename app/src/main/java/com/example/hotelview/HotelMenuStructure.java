package com.example.hotelview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelview.hoteldetails.HotelDetailsMain;

public class HotelMenuStructure extends RecyclerView.ViewHolder {
    ImageView hotelPicture;
    TextView hotelName, hotelAddress, hotelPriceNight;
    ConstraintLayout hotelLayout;
    Button btnMoreDetails;

    public HotelMenuStructure(View itemView) {
        super(itemView);

        hotelPicture = itemView.findViewById(R.id.hotelPicture);
        hotelName = itemView.findViewById(R.id.hotelName);
        hotelAddress = itemView.findViewById(R.id.hotelAddress);
        hotelPriceNight = itemView.findViewById(R.id.hotelPriceNight);
        btnMoreDetails = itemView.findViewById(R.id.btnMoreDetails);
        hotelLayout = itemView.findViewById(R.id.hotelLayout);

        btnMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ensure you use the context of the parent activity
                Context context = view.getContext();

                // Get the hotel ID associated with this item
                int selectedHotelId = getAdapterPosition();

                // Create an Intent and pass the selected hotel ID
                Intent intent = new Intent(context, HotelDetailsMain.class);
                intent.putExtra("hotelId", selectedHotelId);

                // Add the FLAG_ACTIVITY_NEW_TASK flag
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }
}
