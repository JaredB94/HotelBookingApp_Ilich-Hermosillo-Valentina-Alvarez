package com.example.hotelview.roomdetails;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelview.paymentdetails.PaymentMain;
import com.example.hotelview.Login;
import com.example.hotelview.calendardetails.CalendarMain;
import com.example.hotelview.hoteldetails.HotelDetailsMain;
import com.example.hotelview.R;
import com.example.hotelview.hotelmenudetails.HotelMenuMain;

public class RoomDetailsMain extends AppCompatActivity {
    RoomDetailsHelper dbHelper;
    Button selectR1, selectR2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        dbHelper = new RoomDetailsHelper(this);
        TextView txtRoomType = findViewById(R.id.txtRoomType);
        TextView txtNumPeople = findViewById(R.id.txtNumPeople);
        TextView txtRoomSize = findViewById(R.id.txtRoomSize2);

        TextView txtRoomType2 = findViewById(R.id.txtRoomType2);
        TextView txtNumPeople2 = findViewById(R.id.txtNumPeople2);
        TextView txtRoomSize2 = findViewById(R.id.txtRoomSize3);

        selectR1 = findViewById(R.id.selectR1);
        selectR2 = findViewById(R.id.selectR2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.logo_ic_launcher_foreground);

        ImageView btngoBack = findViewById(R.id.btngoBack);
        btngoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act2 = new Intent(RoomDetailsMain.this, HotelDetailsMain.class);
                startActivity(act2);
            }
        });

        // Create a HotelDetailsConstructor object
        RoomDetailsConstructor roomDetails = new RoomDetailsConstructor(
                "Studio Suite, 1 bedroom",
                2,
                355,
                "City view\nSleeps 2\n1 King Bed\n1 Bathroom"
        );

        RoomDetailsConstructor roomDetails2 = new RoomDetailsConstructor(
                "Twin Room, 2 Queen beds",
                4,
                555,
                "City view\nSleeps 4\n2 Queen Beds\n1 Bathroom"
        );

        String RoomType = roomDetails.getTxtRoomType();
        int NumPeople = roomDetails.getNumPeople();
        int RoomSize = roomDetails.getRoomSize();
        String RoomDetails = roomDetails.getRoomDetails();

        // Set the TextView elements with the hotel details
        txtRoomType.setText(roomDetails.getTxtRoomType());
        txtNumPeople.setText(roomDetails.getNumPeople() + " people");
        txtRoomSize.setText(roomDetails.getRoomSize() + " sq ft");


        String RoomType2 = roomDetails2.getTxtRoomType();
        int NumPeople2 = roomDetails2.getNumPeople();
        int RoomSize2 = roomDetails2.getRoomSize();
        String RoomDetails2 = roomDetails2.getRoomDetails();

        // Set the TextView elements with the hotel details
        txtRoomType2.setText(roomDetails2.getTxtRoomType());
        txtNumPeople2.setText(roomDetails2.getNumPeople() + " people");
        txtRoomSize2.setText(roomDetails2.getRoomSize() + " sq ft");


        selectR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = dbHelper.insertUserData(RoomType, NumPeople, RoomSize, RoomDetails);

                if (b) {
                    Intent intent = new Intent(RoomDetailsMain.this, PaymentMain.class);
                    intent.putExtra("RoomType", roomDetails.getTxtRoomType());
                    intent.putExtra("NumPeople", roomDetails.getNumPeople());
                    intent.putExtra("RoomSize", roomDetails.getRoomSize());
                    intent.putExtra("RoomDetails", roomDetails.getRoomDetails());
                    startActivity(intent);

                    Toast.makeText(RoomDetailsMain.this, "Room Data inserted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RoomDetailsMain.this, "Failed To insert Room Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        selectR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b2 = dbHelper.insertUserData(RoomType2, NumPeople2, RoomSize2, RoomDetails2);

                if (b2) {
                    Intent intent = new Intent(RoomDetailsMain.this, PaymentMain.class);
                    intent.putExtra("RoomType", roomDetails2.getTxtRoomType());
                    intent.putExtra("NumPeople", roomDetails2.getNumPeople());
                    intent.putExtra("RoomSize", roomDetails2.getRoomSize());
                    intent.putExtra("RoomDetails", roomDetails2.getRoomDetails());
                    startActivity(intent);

                    Toast.makeText(RoomDetailsMain.this, "Room Data inserted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RoomDetailsMain.this, "Failed To insert Room Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        // Menu options Display
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.my_menu,menu);
            return true;
        }

        // Display Options Available
        public boolean onOptionsItemSelected(MenuItem item){
            String title = item.getTitle().toString();

            switch (title){
                case "SelectDates":
                    showDates();
                    return true;
                case "SelectHotel":
                    showHotels();
                    return true;
                case "Payment":
                    showPayment();
                    return true;
                case "LogOut":
                    showLogout();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        // Alert Dialog building
        public static class Warning extends DialogFragment {
            public Dialog onCreateDialog(Bundle savedInstanceState){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Incomplete Reservation")
                        .setMessage("Proceed to Log out?")

                        // For 'Yes' selection
                        .setPositiveButton("Yes, Log out", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), Login.class);
                                getActivity().startActivity(intent);

                            }
                        })

                        // For 'No' selection
                        .setNegativeButton("No, continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Great!! Let's continue", Toast.LENGTH_SHORT).show();
                            }
                        });
                return builder.create();
            }
        }

        // Action Payement
        private void showPayment() {
            Intent intent = new Intent(RoomDetailsMain.this, PaymentMain.class);
            startActivity(intent);
        }

        // Action Hotels
        private void showHotels() {
            Intent intent = new Intent(RoomDetailsMain.this, HotelMenuMain.class);
            startActivity(intent);
        }

        // ActionShow Dates
        private void showDates() {
            Intent intent = new Intent(RoomDetailsMain.this, CalendarMain.class);
            startActivity(intent);
        }

        // Call and display Dialog Window
        private void showLogout() {
            CalendarMain.Warning warning = new CalendarMain.Warning();
            warning.show(getSupportFragmentManager(),"warning");
        }
    }

