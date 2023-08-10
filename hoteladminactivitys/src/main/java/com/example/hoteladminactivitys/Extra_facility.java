package com.example.hoteladminactivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Extra_facility extends AppCompatActivity {

    Button add;
    EditText facility,cost;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    RecyclerView recyclerView;

    ArrayList<UserExtraClass> extraItemsArrayList;

    ExtraRecyclerAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_facility);




        add=findViewById(R.id.extraid);
        facility=findViewById(R.id.efacility);
        cost=findViewById(R.id.ecost);


        reference= FirebaseDatabase.getInstance().getReference();

        recyclerView=findViewById(R.id.extrarecyclerView);
         //recyclerView.setHasFixedSize(true);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        extraItemsArrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new ExtraRecyclerAdapter(Extra_facility.this,extraItemsArrayList,reference);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String extrafacility=facility.getText().toString();
                String extracost=cost.getText().toString();




                UserExtraClass userExtraClass = new UserExtraClass(extrafacility,extracost);

                rootNode = FirebaseDatabase.getInstance();
                reference=rootNode.getReference().child("extras");
                reference.push().setValue(userExtraClass);


                Toast.makeText(Extra_facility.this, "values are added", Toast.LENGTH_SHORT).show();






            }
        });

        readData();
    }

    private void readData() {

        reference.getRef().child("extras").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                extraItemsArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    UserExtraClass extras=dataSnapshot.getValue(UserExtraClass.class);
                    extraItemsArrayList.add(extras);
                    extras.setExtrakey(dataSnapshot.getKey());




                }


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}