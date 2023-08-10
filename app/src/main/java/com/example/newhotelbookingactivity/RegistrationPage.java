package com.example.newhotelbookingactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPage extends AppCompatActivity {

    EditText Fname, Sname, Email, Address, Mobile, City, Password, Confirm_password, Country;
    Button sign;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    FirebaseAuth mAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        sign = (Button) findViewById(R.id.signing);
        Fname = (EditText) findViewById(R.id.fname);
        Sname = (EditText) findViewById(R.id.sname);
        Email = (EditText) findViewById(R.id.email);
        Address = (EditText) findViewById(R.id.address);
        Mobile = (EditText) findViewById(R.id.mobile);
        City = (EditText) findViewById(R.id.city);
        Password = (EditText) findViewById(R.id.password);
        Confirm_password = (EditText) findViewById(R.id.cpassword);
        Country = (EditText) findViewById(R.id.country);

        mAuth=FirebaseAuth.getInstance();




        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if(! validateRfname() | ! validateRsname() | ! validateRemail() |! validateRaddress() |! validateRmobile() |! validateRcity() |
                        ! validateRpassword() |! validateRcpassword() |! validateRcountry()) {
                    return;
                }*/


                String firstname = Fname.getText().toString();
                String lastname = Sname.getText().toString();
                String email = Email.getText().toString();
                String address = Address.getText().toString();
                String mobile = Mobile.getText().toString();
                String city = City.getText().toString();
                String password = Password.getText().toString();
                String cpassword = Confirm_password.getText().toString();
                String country = Country.getText().toString();






                if (TextUtils.isEmpty(firstname)){
                    Toast.makeText(RegistrationPage.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(lastname)){
                    Toast.makeText(RegistrationPage.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegistrationPage.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mobile)){
                    Toast.makeText(RegistrationPage.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(address)){
                    Toast.makeText(RegistrationPage.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegistrationPage.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(cpassword)){
                    Toast.makeText(RegistrationPage.this, "ReEnter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(city)){
                    Toast.makeText(RegistrationPage.this, "Enter City name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(country)){
                    Toast.makeText(RegistrationPage.this, "Enter Country name", Toast.LENGTH_SHORT).show();
                    return;
                }





                mAuth.createUserWithEmailAndPassword(email,password)

                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String uid = mAuth.getCurrentUser().getUid();

                            UserHelperClass userHelperClass = new UserHelperClass(firstname, lastname, email, address, mobile, city, password, cpassword, country);

                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference().child("Users").child(uid);

                            reference.push().setValue(userHelperClass);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            Toast.makeText(RegistrationPage.this, "Registration done!", Toast.LENGTH_SHORT).show();

                            startActivity(intent);


                        }else{

                            Toast.makeText(RegistrationPage.this, "Authentication failed", Toast.LENGTH_SHORT).show();



                        }

                    }
                });













            }






        });

    }


  /*  private Boolean validateRfname() {

        String val = Fname.getText().toString();

        if (val.isEmpty()) {

            Fname.setError("Field Can't be empty");
            return false;
        } else {
           Fname.setError(null);
            return true;
        }

    }
    private Boolean validateRsname() {

        String val = Sname.getText().toString();

        if (val.isEmpty()) {

            Sname.setError("Field Can't be empty");
            return false;
        } else {
            Sname.setError(null);
            return true;
        }

    }
    private Boolean validateRemail() {

        String val = Email.getText().toString();
        String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {

            Email.setError("Field Can't be empty");
            return false;
        } else if(!val.matches(emailPattern)){
           Email.setError("Invalid email address");
            return false;
        }else{
            Email.setError(null);
            return true;
        }

    }
    private Boolean validateRaddress() {

        String val = Address.getText().toString();

        if (val.isEmpty()) {

            Address.setError("Field Can't be empty");
            return false;
        } else {
            Address.setError(null);
            return true;
        }

    }
    private Boolean validateRmobile() {

        String val = Mobile.getText().toString();
        String passwordVal="^"+
                "$";


        if (val.isEmpty()) {

           Mobile.setError("Field Can't be empty");
            return false;
        } else {
           Mobile.setError(null);
            return true;
        }

    }
    private Boolean validateRcity() {

        String val =City.getText().toString();

        if (val.isEmpty()) {

           City.setError("Field Can't be empty");
            return false;
        } else {
            City.setError(null);
            return true;
        }

    }
    private Boolean validateRpassword() {

        String val1 = Password.getText().toString();

        if (val1.length() >=8 ) {

           Password.setError("Field Can't be empty");
            return false;
        } else {
           Password.setError(null);
            return true;
        }

    }
    private Boolean validateRcpassword() {

        String val = Confirm_password.getText().toString();

        if (!.equals(val)) {
            Toast.makeText(RegistrationPage.this, "Password is not matching", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Confirm_password.setError(null);
            return true;
        }

    }
    private Boolean validateRcountry() {

        String val = Country.getText().toString();

        if (val.isEmpty()) {

            Country.setError("Field Can't be empty");
            return false;
        } else {
            Country.setError(null);
            return true;
        }

    }*/


}