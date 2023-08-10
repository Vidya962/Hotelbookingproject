package com.example.newhotelbookingactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThankyouActivity extends AppCompatActivity {

    Button visit_again;
    TextView txt_checkin, txt_checkout, txt_roomno, txt_adults, txt_childrens, txt_discount, txt_netamont, txt_promo, txt_extracharge, txt_roomtype, txt_fname, txt_lname, txt_phone, txt_address, txt_email, txt_roomprice, txt_roomtax;
    DatabaseReference db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        visit_again=(Button)findViewById(R.id.btn_again);
        txt_checkin = findViewById(R.id.txt_checkin);
        txt_checkout = findViewById(R.id.txt_checkout);
        txt_roomno = findViewById(R.id.txt_Roomno);
        txt_adults = findViewById(R.id.txt_adults);
        txt_childrens = findViewById(R.id.txt_children);
        txt_discount = findViewById(R.id.txt_discount);
        txt_roomprice = findViewById(R.id.txt_roomprice);
        txt_roomtax = findViewById(R.id.txt_roomtax);
        txt_netamont = findViewById(R.id.txt_netamount);
        txt_extracharge = findViewById(R.id.txt_extracharge);
        txt_promo = findViewById(R.id.txt_promo);
        txt_roomtype = findViewById(R.id.txt_roomtype);
        txt_fname = findViewById(R.id.txt_fname);
        txt_lname = findViewById(R.id.txt_lname);
        txt_address = findViewById(R.id.txt_address);
        txt_email = findViewById(R.id.txt_email);
        txt_phone = findViewById(R.id.txt_phone);



        visit_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThankyouActivity.this, MainActivity.class));
            }
        });

        Intent intent = getIntent();
        String Fname = intent.getStringExtra("fname");
        String Lname = intent.getStringExtra("lname");
        String Email = intent.getStringExtra("emails");
        String Address = intent.getStringExtra("faddress");
        String Phone = intent.getStringExtra("phoneno");
        String checkin = intent.getStringExtra("indate");
        String checkout = intent.getStringExtra("outdate");
        int rooms = intent.getIntExtra("rooms",0);
        int adults = intent.getIntExtra("adults",0);
        int childs = intent.getIntExtra("childs",0);
        String selectedradiotext = intent.getStringExtra("roomtype");
        String applieddiscount = intent.getStringExtra("promo");
        String extrafacilitycharge = intent.getStringExtra("extracharge");
        String nettotal = intent.getStringExtra("total");
        String roomPrice = intent.getStringExtra("roomprice");
        String roomTax = intent.getStringExtra("tax");
        String Discount = intent.getStringExtra("discount");
        txt_fname.setText(Fname);
        txt_lname.setText(Lname);
        txt_address.setText(Address);
        txt_email.setText(Email);
        txt_phone.setText(Phone);
        txt_roomprice.setText(roomPrice);
        txt_roomtax.setText(roomTax);
        txt_checkin.setText(checkin);
        txt_checkout.setText(checkout);
        txt_roomtype.setText(selectedradiotext);
        txt_roomno.setText(String.valueOf(rooms));
        txt_adults.setText(String.valueOf(adults));
        txt_childrens.setText(String.valueOf(childs));
        txt_discount.setText(Discount);
        txt_promo.setText(applieddiscount);
        txt_extracharge.setText(extrafacilitycharge + "/-");
        txt_netamont.setText(nettotal + "/-");

        db = FirebaseDatabase.getInstance().getReference();



    }
}