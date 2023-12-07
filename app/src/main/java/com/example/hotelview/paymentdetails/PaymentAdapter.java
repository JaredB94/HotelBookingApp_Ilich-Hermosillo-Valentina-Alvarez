package com.example.hotelview.paymentdetails;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelview.R;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentAdapterViewHolder> {
    private Context context;
    private Cursor cursor;

    public PaymentAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public class PaymentAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHolderName, displayHotelName, displayRoomType,
                displayCheckInDate, displayCheckOutDate, displayNumP,
                displayNumNight, txtExpirDate, txtCardNumber,
                txtCVC, displayFinalPrice;

        public PaymentAdapterViewHolder(View itemView) {
            super(itemView);
            txtHolderName = itemView.findViewById(R.id.txtHolderName);
            displayHotelName = itemView.findViewById(R.id.displayHotelName);
            displayRoomType = itemView.findViewById(R.id.displayRoomType);
            displayCheckInDate = itemView.findViewById(R.id.displayCheckInDate);
            displayCheckOutDate = itemView.findViewById(R.id.displayCheckOutDate);
            displayNumP = itemView.findViewById(R.id.displayNumP);
            displayNumNight = itemView.findViewById(R.id.displayNumNight);
            txtExpirDate = itemView.findViewById(R.id.displayExpiration);
            txtCardNumber = itemView.findViewById(R.id.txtCardNumber);
            txtCVC = itemView.findViewById(R.id.txtCvc);
            displayFinalPrice = itemView.findViewById(R.id.displayFinalPrice);
        }
    }

    @Override
    public PaymentAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_final_payment, parent, false);
        return new PaymentAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaymentAdapterViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            int HolderName = cursor.getColumnIndex("txtHolderName");
            int displayHotelName = cursor.getColumnIndex("displayHotelName");
            int displayRoomType = cursor.getColumnIndex("displayRoomType");
            int displayCheckInDate = cursor.getColumnIndex("displayCheckInDate");
            int displayCheckOutDate = cursor.getColumnIndex("displayCheckOutDate");
            int displayNumP = cursor.getColumnIndex("displayNumP");
            int displayNumNight = cursor.getColumnIndex("displayNumNight");
            int txtExpirDate = cursor.getColumnIndex("txtExpirDate");
            int txtCardNumber = cursor.getColumnIndex("txtCardNumber");
            int txtCVC = cursor.getColumnIndex("txtCVC");
            int displayFinalPrice = cursor.getColumnIndex("displayFinalPrice");

            holder.txtHolderName.setText(cursor.getString(HolderName));
            holder.displayHotelName.setText(cursor.getString(displayHotelName));
            holder.displayRoomType.setText(cursor.getString(displayRoomType));
            holder.displayCheckInDate.setText(cursor.getString(displayCheckInDate));
            holder.displayCheckOutDate.setText(cursor.getString(displayCheckOutDate));
            holder.displayNumP.setText(cursor.getString(displayNumP));
            holder.displayNumNight.setText(cursor.getString(displayNumNight));
            holder.txtExpirDate.setText(cursor.getString(txtExpirDate));
            holder.txtCardNumber.setText(cursor.getString(txtCardNumber));
            holder.txtCVC.setText(cursor.getString(txtCVC));
            holder.displayFinalPrice.setText(cursor.getString(displayFinalPrice));
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
