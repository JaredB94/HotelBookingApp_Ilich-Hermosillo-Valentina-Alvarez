package com.example.hotelview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelview.calendardetails.CalendarMain;
import com.example.hotelview.hoteldetails.HotelDetailsMain;

public class Login extends AppCompatActivity {
    EditText text_email , text_password;
    Button btnSubmit_login;
    TextView signupLink;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.logo_ic_launcher_foreground);

        text_email=findViewById(R.id.text_email);
        text_password=findViewById(R.id.text_password);
        btnSubmit_login = findViewById(R.id.btnSubmit_login);
        signupLink = findViewById(R.id.signupLink);
        dbHelper = new DBHelper(this);

        // The action of 'btnSubmit' will retrieve the user's info to be processed
        btnSubmit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailCheck = text_email.getText().toString();
                String passCheck = text_password.getText().toString();

                // Method to retrieve data from a database to be compared
                Cursor cursor = dbHelper.getData();

                // If user data is not in database, display "No entries Exists"
                if(cursor.getCount() == 0){
                    Toast.makeText(Login.this,"No entries Exists",Toast.LENGTH_LONG).show();
                }

                HotelDetailsMain m = null;
                // If login is successful, proceed to ListHotel activity
                if (loginCheck(cursor,emailCheck,passCheck)) {

                    // Move to Calendar Activity
                    Intent intent = new Intent(Login.this, CalendarMain.class);
                    startActivity(intent);
                }

                // If login fails, do not proceed
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credential", Toast.LENGTH_SHORT).show();
                }
                // Close db connection
                dbHelper.close();
            }
        });

        // Method to redirect the user to Signup window
        signupLink=findViewById(R.id.signupLink);
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

    }

    // Method to verify if the email and password provided does match with any of the records in the database
    public static boolean loginCheck(Cursor cursor,String emailCheck,String passCheck) {
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(emailCheck)) {
                if (cursor.getString(2).equals(passCheck)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}