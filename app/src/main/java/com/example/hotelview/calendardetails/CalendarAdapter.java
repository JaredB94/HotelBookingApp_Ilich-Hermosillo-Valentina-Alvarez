package com.example.hotelview.calendardetails;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.hotelview.R;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarAdapterViewHolder> {
    private Context context;
    private Cursor cursor;

    public CalendarAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public class CalendarAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCheckInDate, txtCheckOutDate, txtnumNights;
        public EditText txtnumPersons;

        public CalendarAdapterViewHolder(View itemView) {
            super(itemView);
            txtCheckInDate = itemView.findViewById(R.id.txtCheckInDate);
            txtCheckOutDate = itemView.findViewById(R.id.txtCheckOutDate);
            txtnumNights = itemView.findViewById(R.id.txtnumNights);
            txtnumPersons = itemView.findViewById(R.id.txtnumPersons);
        }
    }

    @Override
    public CalendarAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_calendar, parent, false);
        return new CalendarAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CalendarAdapterViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            int checkInDateIndex = cursor.getColumnIndex("txtCheckInDate");
            int checkOutDateIndex= cursor.getColumnIndex("txtCheckOutDate");
            int NumNights = cursor.getColumnIndex("txtnumNights");
            int NumPersons= cursor.getColumnIndex("txtnumPersons");

            if (checkOutDateIndex != -1) {
                holder.txtCheckOutDate.setText(cursor.getString(checkOutDateIndex));
            }

            if (checkInDateIndex != -1) {
                holder.txtCheckInDate.setText(cursor.getString(checkInDateIndex));
            }

            if (NumNights != -1) {
                holder.txtnumNights.setText(cursor.getString(NumNights));
            }

            if (NumPersons != -1) {
                holder.txtnumPersons.setText(cursor.getString(NumPersons));
            }
        }
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
