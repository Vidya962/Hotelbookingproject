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

public class Promo_management extends AppCompatActivity {

    Button add;
    EditText Promocode,Discount;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    RecyclerView recyclerView;

    ArrayList<UserPromoClass> promoItemsArrayList;

    PromoRecyclerAdapter adapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_management);


        add=findViewById(R.id.promobtn);
        Promocode=findViewById(R.id.promoid);
        Discount=findViewById(R.id.discountid);


        reference= FirebaseDatabase.getInstance().getReference();

        recyclerView=findViewById(R.id.promorecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        promoItemsArrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new PromoRecyclerAdapter(Promo_management.this,promoItemsArrayList,reference);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code=Promocode.getText().toString();
                String pdiscount=Discount.getText().toString();




                UserPromoClass userPromoClass = new UserPromoClass(code,pdiscount);

                rootNode = FirebaseDatabase.getInstance();
                reference=rootNode.getReference().child("promos");
                reference.push().setValue(userPromoClass);


                Toast.makeText(Promo_management.this, "values are added", Toast.LENGTH_SHORT).show();






            }
        });

        readData();
    }

    private void readData() {

        reference.getRef().child("promos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                promoItemsArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    //UserRoomClass users=dataSnapshot.getValue(UserRoomClass.class);
                    //userItemsArrayList.add(users);

                    UserPromoClass promos=dataSnapshot.getValue(UserPromoClass.class);
                    promoItemsArrayList.add(promos);
                    promos.setPromokey(dataSnapshot.getKey());




                }


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}