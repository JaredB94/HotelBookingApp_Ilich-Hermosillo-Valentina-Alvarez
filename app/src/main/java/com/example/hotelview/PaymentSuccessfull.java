package com.example.hotelview;

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
import android.widget.Toast;

import com.example.hotelview.calendardetails.CalendarMain;
import com.example.hotelview.hotelmenudetails.HotelMenuMain;
import com.example.hotelview.paymentdetails.PaymentMain;

public class PaymentSuccessfull extends AppCompatActivity {

    Button btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successfull);

        btnProfile = findViewById(R.id.btnProfile);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccessfull.this,UserProfile.class);
                startActivity(intent);
            }
        });
    }

    // Menu options Display
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
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
            builder.setTitle("Thank you for using Hotel Booking App")
                    .setMessage("Book a New Reservation?")

                    // For 'Yes' selection
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getActivity(),CalendarMain.class);
                            getActivity().startActivity(intent);
                        }
                    })

                    // For 'No' selection
                    .setNegativeButton("No, Log out", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            getActivity().startActivity(intent);
                        }
                    });
            return builder.create();
        }
    }

    // Action Payement
    private void showPayment() {
        Intent intent = new Intent(PaymentSuccessfull.this, PaymentMain.class);
        startActivity(intent);
    }

    // Action Hotels
    private void showHotels() {
        Intent intent = new Intent(PaymentSuccessfull.this, HotelMenuMain.class);
        startActivity(intent);
    }

    // ActionShow Dates
    private void showDates() {
        Intent intent = new Intent(PaymentSuccessfull.this, CalendarMain.class);
        startActivity(intent);
    }

    // Call and display Dialog Window
    private void showLogout() {
        CalendarMain.Warning warning = new CalendarMain.Warning();
        warning.show(getSupportFragmentManager(),"warning");
    }
}