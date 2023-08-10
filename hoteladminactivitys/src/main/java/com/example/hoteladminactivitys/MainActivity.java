package com.example.hoteladminactivitys;

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

    Button next;
    TextView sign;
    EditText email,password;
    FirebaseAuth mAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next=findViewById(R.id.Login);
        sign=findViewById(R.id.Signup);
        email=findViewById(R.id.lemail);
        password=findViewById(R.id.lpassword);
        mAuth=FirebaseAuth.getInstance();


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Register_Activity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adminEmail = email.getText().toString();
                String adminPassword = password.getText().toString();
                mAuth = FirebaseAuth.getInstance();





              //  if (TextUtils.isEmpty(adminEmail)){
                //    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                  //  return;
                //}

                //if (TextUtils.isEmpty(adminPassword)){
                  //  Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                   // return;
                //}



                mAuth.signInWithEmailAndPassword(adminEmail, adminPassword)

                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(MainActivity.this, Admin_Activity.class);
                                    intent.putExtra("keyusername",adminEmail);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });





            }
        });



    }
}