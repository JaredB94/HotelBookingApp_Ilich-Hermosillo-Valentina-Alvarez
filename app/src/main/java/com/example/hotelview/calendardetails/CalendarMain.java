package com.example.hotelview.calendardetails;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelview.paymentdetails.PaymentMain;
import com.example.hotelview.hotelmenudetails.HotelMenuMain;
import com.example.hotelview.Login;
import com.example.hotelview.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;

public class CalendarMain extends AppCompatActivity {
    //Declaration of Objects
    Button btnCheckIn, btnCheckOut, btnNext;
    ImageView btnCalendarBack;
    TextView txtCheckInDate, txtCheckOutDate, txtNumberNights;
    EditText txtnumberPerson;
    String selectedCheckInDate;
    String selectedCheckOutDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Get ID references from the .xml file
        btnCheckIn = findViewById(R.id.btnCheckIn);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        btnNext = findViewById(R.id.btnNext);

        //btnCalendarBack = findViewById(R.id.btnCalendarBack);
        txtCheckInDate = findViewById(R.id.txtCheckInDate);
        txtCheckOutDate = findViewById(R.id.txtCheckOutDate);
        txtnumberPerson = findViewById(R.id.txtnumPersons);
        txtNumberNights = findViewById(R.id.txtnumNights);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.logo_ic_launcher_foreground);

        // CheckIn button
        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showCheckInCalendar();
            }});

        // CheckOut button
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showCheckOutCalendar();
            }});

        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personNText = txtnumberPerson.getText().toString();
                int personN;

                try {
                    personN = Integer.parseInt(personNText);
                } catch (NumberFormatException e) {
                    // Handle invalid input (non-integer)
                    Toast.makeText(getApplicationContext(), "Invalid number of persons", Toast.LENGTH_SHORT).show();
                    return;
                }


                int numberNights = calculateDateDifference(selectedCheckInDate, selectedCheckOutDate);
                if (numberNights > 0) {
                    CalendarHelper c_Helper = new CalendarHelper(CalendarMain.this);
                    boolean isInserted = c_Helper.insertUserData(selectedCheckInDate, selectedCheckOutDate, numberNights, personN);

                    if (isInserted) {
                        Intent intent = new Intent(CalendarMain.this, HotelMenuMain.class);
                        intent.putExtra("selectedCheckInDate", txtCheckInDate.getText());
                        intent.putExtra("selectedCheckOutDate", txtCheckOutDate.getText());
                        intent.putExtra("numberNights",numberNights);
                        intent.putExtra("personN", personN);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Date Confirmed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to insert data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid date selection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        // Display Calendar view for Check In
        public void showCheckInCalendar() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
                selectedCheckInDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                Toast.makeText(getApplicationContext(), "Check-In Added", Toast.LENGTH_SHORT).show();

                // Print selected check in from text view
                txtCheckInDate.setText(selectedCheckInDate);
            }, year, month, day);

            datePickerDialog.show();
        }

        // Display Calendar view for Check Out
        public void showCheckOutCalendar() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
                selectedCheckOutDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                Toast.makeText(getApplicationContext(), "Check-Out Added", Toast.LENGTH_SHORT).show();

                // Print selected check in from text view
                txtCheckOutDate.setText(selectedCheckOutDate);
            }, year, month, day);
            datePickerDialog.show();
        }

        // Calculate Difference of Selected Dates
        private int calculateDateDifference(String checkInDate, String checkOutDate) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
            try {
                Date date1 = dateFormat.parse(checkInDate);
                Date date2 = dateFormat.parse(checkOutDate);

                long diff = date2.getTime() - date1.getTime();
                int nights = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                return nights;
            } catch (ParseException e) {
                e.printStackTrace();
                return -1;
            }
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
            Intent intent = new Intent(CalendarMain.this, PaymentMain.class);
            startActivity(intent);
        }

        // Action Hotels
        private void showHotels() {
            Intent intent = new Intent(CalendarMain.this, HotelMenuMain.class);
            startActivity(intent);
        }

        // ActionShow Dates
        private void showDates() {
            Toast.makeText(getApplicationContext(), "Already in Select Dates", Toast.LENGTH_SHORT).show();
        }

        // Call and display Dialog Window
        private void showLogout() {
            Warning warning = new Warning();
            warning.show(getSupportFragmentManager(),"warning");
        }
    }
