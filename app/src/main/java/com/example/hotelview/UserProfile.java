package com.example.hotelview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelview.calendardetails.CalendarHelper;
import com.example.hotelview.calendardetails.CalendarMain;
import com.example.hotelview.hoteldetails.HotelDetailsHelper;
import com.example.hotelview.hotelmenudetails.HotelMenuMain;
import com.example.hotelview.paymentdetails.PaymentHelper;
import com.example.hotelview.paymentdetails.PaymentMain;

public class UserProfile extends AppCompatActivity {

    TextView txtName, txtEmail, txtFrom, txtTo, txtHotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);
        txtHotelName = findViewById(R.id.txtHotelName);

        // Add Hotel Logo to menu bar and home app icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.logo_ic_launcher_foreground);

        // Retrieve user's name from SharedPreferences
        String userName = getIntent().getStringExtra("HolderName");



        // Retrieve User
        PaymentHelper us = new PaymentHelper(this);
        Cursor cursor0 = us.getData();
        if (cursor0 != null && cursor0.moveToFirst()) {
            @SuppressLint("Range")
            String dUser = cursor0.getString(cursor0.getColumnIndex("txtHolderName"));
            txtName.setText(dUser);
            cursor0.close();
        }

        // Retrieve Number of People
        PaymentHelper em = new PaymentHelper(this);
        Cursor cursor = em.getData();
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range")
            String dPeople = cursor.getString(cursor.getColumnIndex("displayNumP"));
            txtEmail.setText(dPeople);
            cursor.close();
        }

        // Retrieve Dates
        CalendarHelper ch = new CalendarHelper(this);
        Cursor cursor2 = ch.getData();
        if (cursor2 != null && cursor2.moveToFirst()) {
            // Retrieve Check-In Date
            @SuppressLint("Range") String selectedCheckInDate = cursor2.getString(cursor2.getColumnIndex("txtCheckInDate"));
            txtFrom.setText(selectedCheckInDate);

            // Retrieve Check-Out Date
            @SuppressLint("Range") String selectedCheckOutDate = cursor2.getString(cursor2.getColumnIndex("txtCheckOutDate"));
            txtTo.setText(selectedCheckOutDate);
            cursor2.close();
        }

        // Retrieve Hotel Name
        HotelDetailsHelper ho = new HotelDetailsHelper(this);
        Cursor cursor3 = ho.getData();
        if (cursor != null && cursor3.moveToFirst()) {
            @SuppressLint("Range")
            String TitleHotel = cursor3.getString(cursor3.getColumnIndex("txtTitleHotel"));
            txtHotelName.setText(TitleHotel);
            cursor3.close();
        }
    }

    // Menu options Display
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    // Display Options Available
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();

        switch (title) {
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
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Thank you for Using this App!!")
                    .setMessage("Proceed to Log out?")

                    // For 'Yes' selection
                    .setPositiveButton("Yes, Log out", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            getActivity().startActivity(intent);
                        }
                    })

                    // For 'No' selection
                    .setNegativeButton("No, make another reservation", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Great!! Let's continue", Toast.LENGTH_SHORT).show();
                        }
                    });
            return builder.create();
        }
    }

    // Action Payment
    private void showPayment() {
        Intent intent = new Intent(UserProfile.this, HotelMenuMain.class);
        startActivity(intent);
    }

    // Action Hotels
    private void showHotels() {
        Intent intent = new Intent(UserProfile.this, HotelMenuMain.class);
        startActivity(intent);
    }

    // Action Show Dates
    private void showDates() {
        Intent intent = new Intent(UserProfile.this, CalendarMain.class);
        startActivity(intent);
    }

    // Call and display Dialog Window
    private void showLogout() {
        CalendarMain.Warning warning = new CalendarMain.Warning();
        warning.show(getSupportFragmentManager(), "warning");
    }
}
