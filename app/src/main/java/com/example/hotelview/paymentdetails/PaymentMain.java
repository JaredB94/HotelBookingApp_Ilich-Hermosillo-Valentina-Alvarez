package com.example.hotelview.paymentdetails;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelview.HotelMenuAdapter;
import com.example.hotelview.Login;
import com.example.hotelview.PaymentSuccessfull;
import com.example.hotelview.R;
import com.example.hotelview.SignUp;
import com.example.hotelview.UserProfile;
import com.example.hotelview.calendardetails.CalendarHelper;
import com.example.hotelview.calendardetails.CalendarMain;
import com.example.hotelview.hoteldetails.HotelDetailsHelper;
import com.example.hotelview.hotelmenudetails.HotelMenuConstructor;
import com.example.hotelview.hotelmenudetails.HotelMenuMain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PaymentMain extends AppCompatActivity {
    TextView displayRoomType, displayHotelName, displayCheckInDate, displayCheckOutDate, displayNumNight, displayNumP, displayFinalPrice;
    EditText txtHolderName, txtCardNumber, displayExpiration, txtCvc;
    Button btnPayment;

    String TH;
    int NN =0;
    int FinalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payment);

        PaymentHelper paymentHelper = new PaymentHelper(this);

        btnPayment = findViewById(R.id.btnPayment);

        // Objects retrieved from DB
        displayRoomType = findViewById(R.id.displayRoomType);
        displayHotelName = findViewById(R.id.displayHotelName);
        displayCheckInDate = findViewById(R.id.displayCheckInDate);
        displayCheckOutDate = findViewById(R.id.displayCheckOutDate);
        displayNumNight = findViewById(R.id.displayNumNight);
        displayNumP = findViewById(R.id.displayNumP);
        displayFinalPrice = findViewById(R.id.displayFinalPrice);

        // Objects typed by user input
        txtHolderName = findViewById(R.id.txtHolderName);
        txtCardNumber = findViewById(R.id.txtCardNumber);
        displayExpiration = findViewById(R.id.displayExpiration);
        txtCvc = findViewById(R.id.txtCvc);

        // Add Hotel Logo to menu bar and home app icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.logo_ic_launcher_foreground);

        // Retrieve Intent from previous activities
        Bundle extras = getIntent().getExtras();
        String roomType = null;

        // Retrieve RoomType
        if (extras != null) {
            roomType = extras.getString("RoomType");
            displayRoomType.setText("\t" + roomType);

            // Retrieve txtTitleHotel
            HotelDetailsHelper hd = new HotelDetailsHelper(this);
            Cursor cursor = hd.getData();
            if (cursor != null && cursor.moveToFirst()) {
                // Retrieve HotelName
                @SuppressLint("Range") String txtTitleHotel = cursor.getString(cursor.getColumnIndex("txtTitleHotel"));
                displayHotelName.setText("\t" + txtTitleHotel);

                TH = txtTitleHotel;

                cursor.close();
            }



            // Calendar Details
            CalendarHelper ch = new CalendarHelper(this);
            Cursor cursor2 = ch.getData();
            if (cursor2 != null && cursor2.moveToFirst()) {
                // Retrieve Check-In Date
                @SuppressLint("Range") String selectedCheckInDate = cursor2.getString(cursor2.getColumnIndex("txtCheckInDate"));
                displayCheckInDate.setText("\t" + selectedCheckInDate);

                // Retrieve Check-Out Date
                @SuppressLint("Range") String selectedCheckOutDate = cursor2.getString(cursor2.getColumnIndex("txtCheckOutDate"));
                displayCheckOutDate.setText("\t" + selectedCheckOutDate);

                // Retrieve No. Nights
                @SuppressLint("Range") int nNights = cursor2.getInt(cursor2.getColumnIndex("txtnumNights"));
                displayNumNight.setText("\t" + nNights);

                // Retrieve No. Persons
                @SuppressLint("Range") int nPersons = cursor2.getInt(cursor2.getColumnIndex("txtnumPersons"));
                displayNumP.setText("\t" + nPersons);

                // Display Final Price
                if (TH.equals("The Listel Hotel Vancouver")) {
                    FinalPrice = nNights*55;
                    displayFinalPrice.setText(String.valueOf("\t" + FinalPrice).toString());

                } else if (TH.equals("The Hotel Belmont Vancouver")) {
                    FinalPrice = nNights*67;
                    displayFinalPrice.setText(String.valueOf("\t" + FinalPrice).toString());

                } else if (TH.equals("The Burrard Vancouver")) {
                    FinalPrice = nNights*70;
                    displayFinalPrice.setText(String.valueOf("\t"+ FinalPrice).toString());
                }

                cursor2.close();
            }
        }


        // DB Insert
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HotelName = displayHotelName.getText().toString();
                String CheckInDate = displayCheckInDate.getText().toString();
                String CheckOutDate = displayCheckOutDate.getText().toString();
                String NumP = displayNumP.getText().toString();
                String RoomType = displayRoomType.getText().toString();
                String FinalPrice = displayFinalPrice.getText().toString();
                String NumNight = displayNumNight.getText().toString();

                String HolderName = txtHolderName.getText().toString();
                String CardNumber = txtCardNumber.getText().toString();
                String Expiration = displayExpiration.getText().toString();
                String Cvc = txtCvc.getText().toString();

                PaymentConstructor paymentDetails = new PaymentConstructor(
                        HolderName, HotelName, RoomType, CheckInDate, CheckOutDate,
                        NumP, NumNight,
                        Expiration, CardNumber, Cvc, FinalPrice
                );

                boolean result = paymentHelper.insertUserData(HolderName, HotelName, RoomType, CheckInDate, CheckOutDate,
                        NumP, NumNight, Expiration, CardNumber, Cvc, FinalPrice);

                if (result) {
                    Toast.makeText(PaymentMain.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                    // Pass Intent to User Profile
                    Intent intent = new Intent(PaymentMain.this, PaymentSuccessfull.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(PaymentMain.this, "Failed to Insert Data", Toast.LENGTH_SHORT).show();
                }
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

    // Action Payment
    private void showPayment() {
        Toast.makeText(getApplicationContext(), "Already in Payment Window", Toast.LENGTH_SHORT).show();
    }

    // Action Hotels
    private void showHotels() {
        Intent intent = new Intent(PaymentMain.this, HotelMenuMain.class);
        startActivity(intent);
    }

    // Action Show Dates
    private void showDates() {
        Intent intent = new Intent(PaymentMain.this, CalendarMain.class);
        startActivity(intent);
    }

    // Call and display Dialog Window
    private void showLogout() {
        Warning warning = new Warning();
        warning.show(getSupportFragmentManager(), "warning");
    }
}
