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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Activity extends AppCompatActivity {

    Button register;
    EditText Fname, Sname, Email, Phone, Password, Confirm_password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register=findViewById(R.id.Register);


        Fname = (EditText) findViewById(R.id.fname);
        Sname = (EditText) findViewById(R.id.sname);
        Email = (EditText) findViewById(R.id.email);
        Phone = (EditText) findViewById(R.id.mobile);
        Password = (EditText) findViewById(R.id.password);
        Confirm_password = (EditText) findViewById(R.id.cpassword);
        mAuth=FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = Fname.getText().toString();
                String lastname = Sname.getText().toString();
                String email = Email.getText().toString();
                String phone = Phone.getText().toString();
                String password = Password.getText().toString();
                String cpassword = Confirm_password.getText().toString();

                reference.setValue("Data Storage");



                if (TextUtils.isEmpty(firstname)){
                    Toast.makeText(Register_Activity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(lastname)){
                    Toast.makeText(Register_Activity.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register_Activity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(Register_Activity.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register_Activity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(cpassword)){
                    Toast.makeText(Register_Activity.this, "ReEnter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password)

                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    String uid = mAuth.getCurrentUser().getUid();

                                    UserModelClass userModelClass = new UserModelClass(firstname,lastname,email,password,cpassword);

                                    rootNode = FirebaseDatabase.getInstance();
                                    reference = rootNode.getReference().child("Admin Users").child(uid);

                                    reference.push().setValue(userModelClass);
                                    Intent intent=new Intent(Register_Activity.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(Register_Activity.this, "Registration done!", Toast.LENGTH_SHORT).show();

                                    startActivity(intent);


                                }else{

                                    Toast.makeText(Register_Activity.this, "Authentication failed", Toast.LENGTH_SHORT).show();



                                }

                            }
                        });










            }
        });
    }
}