package com.example.hotelview.hotelmenudetails;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hotelview.calendardetails.CalendarConstructor;
import com.example.hotelview.calendardetails.CalendarHelper;
import com.example.hotelview.paymentdetails.PaymentMain;
import com.example.hotelview.HotelMenuAdapter;
import com.example.hotelview.Login;
import com.example.hotelview.R;
import com.example.hotelview.calendardetails.CalendarMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class HotelMenuMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_menu_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<HotelMenuConstructor> items = new ArrayList<>();

        //CalendarConstructor numberNights = ;
        // Calendar Details
        CalendarHelper ch = new CalendarHelper(this);
        Cursor cursor2 = ch.getData();
        int numNights = 0;
        if (cursor2 != null && cursor2.moveToFirst()) {
            // Retrieve No. Nights
            @SuppressLint("Range") int nNights = cursor2.getInt(cursor2.getColumnIndex("txtnumNights"));
            numNights = nNights;
            //displayNumNight.setText("\t" + nNights);
        }


        int itemIdCounter = 0; // Start with ID 1
        items.add(new HotelMenuConstructor("The Listel Hotel Vancouver", "1300 Robson St, Vancouver, BC, V6E1C5", (numNights*55), R.drawable.h_listel, 0));
        items.add(new HotelMenuConstructor("The Hotel Belmont Vancouver", "654 Nelson St, Vancouver, BC, V6B6K4", (numNights*67), R.drawable.h_belmont, 1));
        items.add(new HotelMenuConstructor("The Burrard Vancouver", "1100 Burrard Street, Vancouver, BC, V6Z1Y7", (numNights*70), R.drawable.h_burrard, 2));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HotelMenuAdapter(getApplicationContext(), items));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.logo_ic_launcher_foreground);
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
        public static class Warning extends DialogFragment{
            public Dialog onCreateDialog(Bundle savedInstanceState){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Incomplete Reservation")
                        .setMessage("Proceed to Log out?")

                        // For 'Yes' selection
                        .setPositiveButton("Yes, Log out", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(),Login.class);
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
            Intent intent = new Intent(HotelMenuMain.this, PaymentMain.class);
            startActivity(intent);
        }

        // Action Hotels
        private void showHotels() {
            Toast.makeText(getApplicationContext(), "Already in Select Hotels", Toast.LENGTH_SHORT).show();
        }

        // ActionShow Dates
        private void showDates() {
            Intent intent = new Intent(HotelMenuMain.this, CalendarMain.class);
            startActivity(intent);
        }

        // Call and display Dialog Window
        private void showLogout() {
            Warning warning = new Warning();
            warning.show(getSupportFragmentManager(),"warning");
        }
}
