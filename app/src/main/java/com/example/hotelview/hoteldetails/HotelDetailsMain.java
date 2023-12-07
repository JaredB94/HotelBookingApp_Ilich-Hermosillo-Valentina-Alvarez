package com.example.hotelview.hoteldetails;

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

import com.example.hotelview.calendardetails.CalendarConstructor;
import com.example.hotelview.paymentdetails.PaymentMain;
import com.example.hotelview.Login;
import com.example.hotelview.R;
import com.example.hotelview.calendardetails.CalendarMain;
import com.example.hotelview.hotelmenudetails.HotelMenuMain;
import com.example.hotelview.roomdetails.RoomDetailsMain;

public class HotelDetailsMain extends AppCompatActivity {

    HotelDetailsHelper dbHelper;
    Button btnAvailableRooms;
    ImageView btngoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        dbHelper = new HotelDetailsHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.logo_ic_launcher_foreground);

        TextView txtTitleHotel = findViewById(R.id.txtTitleHotel);
        TextView txtDescription = findViewById(R.id.txtDescription);
        TextView txtAmenitiesD = findViewById(R.id.txtAmenitiesD);
        TextView txtExactLocation = findViewById(R.id.txtExactLocation);
        //TextView txtnighPrice = findViewById(R.id.txtpriceNight);

        btnAvailableRooms = findViewById(R.id.btnAvailableRooms);

        // Receive the hotel ID passed from the previous activity
        int hotelId = getIntent().getIntExtra("hotelId", 0);

        // Initialize the hotelDetails object
        final HotelDetailsConstructor hotelDetails;



        if (hotelId == 0) {
            hotelDetails = new HotelDetailsConstructor(
                    "The Listel Hotel Vancouver",
                    "Located near the beach, The Listel Hotel Vancouver is in Central Vancouver, a walkable area in Vancouver with great shopping. Canada Place Cruise Ship Terminal and Vancouver Waterfront are worth checking out if an activity is on the agenda, while those wishing to experience the area's natural beauty can explore Stanley Park.",
                    "Breakfast available\nPet-friendly\nParking available\nFree WiFi",
                    "1300 Robson St, Vancouver, BC, V6E1C5", 0
            );

        } else if (hotelId == 1) {
            hotelDetails = new HotelDetailsConstructor(
                    "The Hotel Belmont Vancouver",
                    "Located near the beach, Hotel Belmont Vancouver MGallery is in Downtown Vancouver, a walkable area in Vancouver with good shopping. Vancouver Waterfront and Canada Place Cruise Ship Terminal are worth checking out if an activity is on the agenda, while those in the mood for shopping can visit Robson Street.",
                    "Breakfast available\nPet-friendly\nBar\nFree WiFi",
                    "654 Nelson St, Vancouver, BC, V6B6K4", 1
            );
        } else if (hotelId == 2) {
            hotelDetails = new HotelDetailsConstructor(
                    "The Burrard Vancouver",
                    "Located near the beach, The Burrard is in Downtown Vancouver, a walkable area in Vancouver with good shopping. Canada Place Cruise Ship Terminal and Vancouver Waterfront are worth checking out if an activity is on the agenda, while those wishing to experience the area's natural beauty can explore Stanley Park.",
                    "Breakfast available\nHousekeeping\nParking available\nFree WiFi",
                    "1100 Burrard Street, Vancouver, BC, V6Z 1Y7", 2
            );
        } else {
            hotelDetails = null;
        }

        // Check if hotelDetails is not null before setting the TextView elements
        if (hotelDetails != null) {
            txtTitleHotel.setText(hotelDetails.getTxtTitleHotel());
            txtDescription.setText(hotelDetails.getTxtDescription());
            txtAmenitiesD.setText(hotelDetails.getTxtAmenitiesD());
            txtExactLocation.setText(hotelDetails.getTxtExactLocation());
        } else {
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
        }

        // Extract text from TextView elements and convert them to String
        String txtTitleHotelString = txtTitleHotel.getText().toString();
        String txtDescriptionString = txtDescription.getText().toString();
        String txtAmenitiesDString = txtAmenitiesD.getText().toString();
        String txtExactLocationString = txtExactLocation.getText().toString();

        boolean b = dbHelper.insertUserData(txtTitleHotelString, txtDescriptionString, txtAmenitiesDString, txtExactLocationString);
        if (b) {
            Toast.makeText(HotelDetailsMain.this, "Data inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HotelDetailsMain.this, "Failed To insert Data", Toast.LENGTH_SHORT).show();
        }

        btnAvailableRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelDetailsMain.this, RoomDetailsMain.class);
                intent.putExtra("txtTitleHotel", txtTitleHotel.getText());
                startActivity(intent);
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
        Intent intent = new Intent(HotelDetailsMain.this, PaymentMain.class);
        startActivity(intent);
    }

    // Action Hotels
    private void showHotels() {
        Intent intent = new Intent(HotelDetailsMain.this, HotelMenuMain.class);
        startActivity(intent);
    }

    // ActionShow Dates
    private void showDates() {
        Intent intent = new Intent(HotelDetailsMain.this, CalendarMain.class);
        startActivity(intent);
    }

    // Call and display Dialog Window
    private void showLogout() {
        Warning warning = new Warning();
        warning.show(getSupportFragmentManager(),"warning");
    }
}
