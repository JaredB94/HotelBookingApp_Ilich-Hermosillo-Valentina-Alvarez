package com.example.hotelview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText signupName , signupPhone , signupEmail, signupPassword;
    TextView loginLink;
    DBHelper dbHelper;
    Button signUpAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName=findViewById(R.id.signupName);
        signupPhone=findViewById(R.id.signupPhone);
        signupEmail=findViewById(R.id.signupEmail);
        signupPassword=findViewById(R.id.signupPassword);
        signUpAcc = findViewById(R.id.signUpAcc);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.logo_ic_launcher_foreground);

        dbHelper = new DBHelper(this);

        signUpAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupName.getText().toString();
                String number = signupPhone.getText().toString();
                String email = signupEmail.getText().toString();
                String pass = signupPassword.getText().toString();
                boolean b =dbHelper.insetUserData(name,number,email,pass);
                if (b){
                    Toast.makeText(SignUp.this,"Data inserted",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp.this,Login.class);
                    startActivity(i);
                }else {
                    Toast.makeText(SignUp.this,"Failed To insert Data",Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginLink=findViewById(R.id.loginLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });
    }
}
