package com.example.newhotelbookingactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PaymentActivity extends AppCompatActivity {

    Button pay_now;

    EditText editcardno, editcvv, editcardname;
    TextView editexpiry;
    private DatabaseReference db;
    private UserData2 ud2;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        pay_now=(Button)findViewById(R.id.paynow);
        editcardno = findViewById(R.id.editcardno);
        editcvv = findViewById(R.id.editcvv);
        editcardname = findViewById(R.id.editname);
        editexpiry = findViewById(R.id.editexpiry);


        db = FirebaseDatabase.getInstance().getReference("UserData2");

        editexpiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PaymentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String expiryDate = String.format("%02d/%02d/%04d", dayOfMonth, month+1, year);
                                editexpiry.setText(expiryDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });





        pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent2=new Intent(PaymentActivity.this,ThankyouActivity.class);


                Intent intent = getIntent();
                String Fname= intent.getStringExtra("firstname");
                String Lname=intent.getStringExtra("lastname");
                String Email= intent.getStringExtra("email");
                String Mob = intent.getStringExtra("phone");
                String Add =intent.getStringExtra("address");
                String Indate=intent.getStringExtra("checkin");
                String Outdate=intent.getStringExtra("checkout");
                String Netpay = intent.getStringExtra("total");
                String Roomtype=  intent.getStringExtra("selectedradio");
                String Discount= intent.getStringExtra("discount");
                int Roomno= intent.getIntExtra("rooms",0);
                int Adultno= intent.getIntExtra("adults",0);
                int Childno=intent.getIntExtra("childs",0);
                String Extracharge= intent.getStringExtra("extracharge");
                String Promo =intent.getStringExtra("promo");
                String roomprice = intent.getStringExtra("roomprice");
                String roomtax = intent.getStringExtra("tax");


                ud2 = new UserData2(Fname, Lname, Email, Mob, Add);
                db.push().setValue(ud2);
                Toast.makeText(PaymentActivity.this, "Booking Success", Toast.LENGTH_SHORT).show();


                intent2.putExtra("fname", Fname);
                intent2.putExtra("lname", Lname);
                intent2.putExtra("emails", Email);
                intent2.putExtra("phoneno", Mob);
                intent2.putExtra("faddress", Add);
                intent2.putExtra("indate", Indate);
                intent2.putExtra("outdate", Outdate);
                intent2.putExtra("total", Netpay);
                intent2.putExtra("roomtype", Roomtype);
                intent2.putExtra("rooms", Roomno);
                intent2.putExtra("adults",Adultno);
                intent2.putExtra("childs",Childno);
                intent2.putExtra("extracharge",Extracharge);
                intent2.putExtra("promo",Promo);
                intent2.putExtra("roomprice",roomprice);
                intent2.putExtra("tax",roomtax);
                intent2.putExtra("discount", Discount);




                startActivity(intent2);
            }
        });
    }
}