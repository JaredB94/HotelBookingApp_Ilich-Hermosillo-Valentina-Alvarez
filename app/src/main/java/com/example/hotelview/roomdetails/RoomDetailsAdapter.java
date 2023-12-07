package com.example.hotelview.roomdetails;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelview.R;

public class RoomDetailsAdapter extends RecyclerView.Adapter<RoomDetailsAdapter.RoomDetailsViewHolder>{

    private Context context;
    private Cursor cursor;

    public RoomDetailsAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public class RoomDetailsViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRoomType, txtNumPeople, txtRoomSize, txtRoomDetails;

        public RoomDetailsViewHolder(View itemView) {
            super(itemView);
            txtRoomType = itemView.findViewById(R.id.txtRoomType);
            txtNumPeople = itemView.findViewById(R.id.txtNumPeople);
            txtRoomSize = itemView.findViewById(R.id.txtRoomSize2);
            //txtRoomDetails = itemView.findViewById(R.id.txtRoomDetails);
        }
    }

    @Override
    public RoomDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_room_details, parent, false);
        return new RoomDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RoomDetailsViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            int RoomTypeIndex = cursor.getColumnIndex("txtRoomType");
            int NumPeopleIndex = cursor.getColumnIndex("txtNumPeople");
            int RoomSizeIndex = cursor.getColumnIndex("txtRoomSize");
            int RoomDetailsIndex = cursor.getColumnIndex("txtRoomDetails");

            if (RoomTypeIndex != -1) {
                holder.txtRoomType.setText(cursor.getString(RoomTypeIndex));
            }

            if (NumPeopleIndex != -1) {
                holder.txtNumPeople.setText(cursor.getString(NumPeopleIndex));
            }

            if (RoomSizeIndex != -1) {
                holder.txtRoomSize.setText(cursor.getString(RoomSizeIndex));
            }

            if (RoomDetailsIndex  != -1) {
                holder.txtRoomDetails.setText(cursor.getString(RoomDetailsIndex ));
            }
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
