package com.example.hoteladminactivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Admin_Activity extends AppCompatActivity {

    TextView Room_management,Promo_management,extrafacility,logout,username;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Room_management=findViewById(R.id.Room_manage);
        Promo_management=findViewById(R.id.promo);
        extrafacility=findViewById(R.id.extra);
        logout=findViewById(R.id.logout);
        username=findViewById(R.id.username);


        String email=getIntent().getStringExtra("keyusername");
        username.setText(email);


        Room_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_Activity.this, Room_management.class);
                startActivity(intent);
            }
        });


        Promo_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_Activity.this, Promo_management.class);
                startActivity(intent);

            }
        });

        extrafacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_Activity.this,Extra_facility.class);
                startActivity(intent);


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Admin_Activity.this,MainActivity.class);
                startActivity(intent);


            }
        });

    }
}