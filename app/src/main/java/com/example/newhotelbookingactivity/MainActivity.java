package com.example.newhotelbookingactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

        TextView register;
        EditText email,password;
        FirebaseAuth mAuth;
        Button login;





        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            register=(TextView) findViewById(R.id.Signup);
            login=(Button) findViewById(R.id.Login);
            email=(EditText) findViewById(R.id.lemail);
            password=(EditText) findViewById(R.id.lpassword);
            mAuth=FirebaseAuth.getInstance();

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,RegistrationPage.class);
                    startActivity(intent);
                }
            });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String Email = email.getText().toString();
                    String Password = password.getText().toString();




                    if (TextUtils.isEmpty(Email)){
                        Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(Password)){
                        Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                        return;
                    }



                    mAuth.signInWithEmailAndPassword(Email,Password)

                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                       Intent intent=new Intent(MainActivity.this,BookingRoom.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });




                }
            });






        }
    }