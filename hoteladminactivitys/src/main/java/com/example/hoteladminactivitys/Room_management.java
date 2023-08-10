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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Room_management extends AppCompatActivity {

    Button add;
    EditText roomtype,cost,discounts;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    RecyclerView recyclerView;

    ArrayList<UserRoomClass> userItemsArrayList;

    RoomRecyclerAdapter adapter;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_management);


        add=findViewById(R.id.addbutton);
        roomtype=findViewById(R.id.roomtype);
        cost=findViewById(R.id.roomcost);
        discounts=findViewById(R.id.discount);


        reference= FirebaseDatabase.getInstance().getReference("rooms");

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userItemsArrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new RoomRecyclerAdapter(Room_management.this,userItemsArrayList,reference);
        recyclerView.setAdapter(adapter);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String room_type=roomtype.getText().toString();
                String room_cost=cost.getText().toString();
                String room_discount=discounts.getText().toString();



                UserRoomClass userRoomClass = new UserRoomClass(room_type,room_cost,room_discount);
                String id = reference.push().getKey();


                reference.child(id).setValue(userRoomClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Room_management.this, "values are added", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Room_management.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                });









            }
        });

         readData();
    }

    private void readData() {
        reference.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userItemsArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    UserRoomClass users=dataSnapshot.getValue(UserRoomClass.class);
                    userItemsArrayList.add(users);
                    users.setRoomkey(dataSnapshot.getKey());


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

 /*  private void readData() {

        reference.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userItemsArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    UserRoomClass users=dataSnapshot.getValue(UserRoomClass.class);
                    userItemsArrayList.add(users);
                    users.setRoomkey(dataSnapshot.getKey());




                }


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }*/


}
