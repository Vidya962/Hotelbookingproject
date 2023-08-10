package Fragments;

import static java.util.Arrays.compare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newhotelbookingactivity.PaymentActivity;
import com.example.newhotelbookingactivity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ConfirmFragment extends Fragment {
    private EditText edit_roomprice, edit_roomtax, edit_extrafacility, edit_discount, edit_discountamt, edit_total, edit_grandtotal;

    private Spinner promospinner;
    Bundle bundle;

    Button make_payment;

    private DatabaseReference mDataRef;


    //
   // private Map<String, String> itemCostMap = new HashMap<>();

    private Map<String,String> itemCostMap=new HashMap<>();

    //

   List<String> list;
    // EditText textview1;
    // TextView textview2;


    // textview2 = findViewById(YourId2);


    public ConfirmFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

        promospinner = view.findViewById(R.id.promospinner);
        edit_total = view.findViewById(R.id.edit_total);
        edit_discount = view.findViewById(R.id.edit_discount);
        edit_discountamt = view.findViewById(R.id.edit_discountamt);
        edit_grandtotal = view.findViewById(R.id.edit_grandtotal);
        edit_roomprice = view.findViewById(R.id.edit_roomprice);
        edit_extrafacility = view.findViewById(R.id.edit_extrafacility);
        edit_roomtax = view.findViewById(R.id.edit_roomtax);

        edit_extrafacility.setEnabled(false);
        edit_roomtax.setEnabled(false);
        edit_roomprice.setEnabled(false);
        edit_grandtotal.setEnabled(false);
        edit_total.setEnabled(false);
        edit_discountamt.setEnabled(false);
        edit_discount.setEnabled(false);

        make_payment = view.findViewById(R.id.payment);


        bundle = getArguments();
        if (bundle != null) {

            String Extracharge = bundle.getString("extracharge");
            String total = bundle.getString("total");
            int Roomprice = bundle.getInt("roomPrice", 0);
            String Roomtax = bundle.getString("roomTax");

            edit_roomprice.setText(String.valueOf(Roomprice));
            edit_roomtax.setText(Roomtax);
            edit_extrafacility.setText(Extracharge);
            edit_total.setText(total);
        }

        mDataRef=FirebaseDatabase.getInstance().getReference("promos");
        
        fetchdata();
     /*   ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.PromoCodes, R.layout.spinner_codes);
        arrayAdapter.setDropDownViewResource(R.layout.my_spinner_dropdown);
        promospinner.setAdapter(arrayAdapter);


        promospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String discountcode = parent.getItemAtPosition(position).toString();
                int discountpercent = getPercentageDiscount(discountcode);
                edit_discount.setText(String.valueOf(discountpercent));


                String total = edit_total.getText().toString();
                if(!total.isEmpty()){
                    double totalamt = Double.parseDouble(total);
                    double netamt = totalamt *(100-discountpercent)/100;
                    edit_grandtotal.setText(String.valueOf(netamt));
                    edit_discountamt.setText(String.valueOf((totalamt*discountpercent)/100));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


      */


        //  textview1 = view.findViewById(text1);
        //  textview2 = findViewById(YourId2);

        list = new ArrayList();
       // FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
       // DatabaseReference mDataRef = mDatabase.getReference().child("promos");

        mDataRef=FirebaseDatabase.getInstance().getReference("promos");




     /*   mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String name  = String.valueOf(ds.child(snapshot.getKey()).child("name"));
                    list.add(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        */




//

        /*


        mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                   // String name = String.valueOf(ds.child(snapshot.getKey()).child("promocode"));
                  //  list.add(name);

                    //list.add(ds.child(snapshot.getKey()).child("promocode");
                    String name=(ds.child("promocode").getValue().toString());
                    list.add(name);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        */
        //


        /*ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.PromoCodes, R.layout.spinner_codes);
        arrayAdapter.setDropDownViewResource(R.layout.my_spinner_dropdown);
        promospinner.setAdapter(arrayAdapter);*/

       // promospinner =view.findViewById(R.id.promospinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.my_spinner_dropdown, list);
        promospinner.setAdapter(adapter);





/*

        promospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedValue = list.get(position);
                 compare(selectedValue);

                String selectedItem = list.get(position);
                String correspondingCost = itemCostMap.get(selectedItem);
                if (correspondingCost != null) {
                    edit_discount.setText(correspondingCost);
                }

               // String selectedValue = (String) list.get(position);
              //  compare(selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



            public void compare(String selectedValue) {
                mDataRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        itemCostMap.clear();

                        for(DataSnapshot ds : snapshot.getChildren()) {
                            if(ds.child(snapshot.getKey()).child("name").equals(selectedValue)){
                                String quantity = String.valueOf(ds.child("discount"));
                                edit_discount.setText(quantity);
                                break;
                            }
                        }

                        /*    String promocode = ds.child("promocode").getValue(String.class);
                            String discount = ds.child("discount").getValue(String.class);


                            if (promocode != null && discount != null) {
                                list.add(promocode);
                                itemCostMap.put(promocode,discount);
                            }

                        }*/

             //      }

              //     @Override
                 //   public void onCancelled(@NonNull DatabaseError error) {
                //    }
             //   });
         //   }
    //    });
















        /*   for(DataSnapshot ds : snapshot.getChildren()) {
               if(ds.child(snapshot.getKey()).child("name").equals(selectedValue)){
                   String rate = String.valueOf(ds.child(snapshot.getKey()).child("product_rate"));
                   String quantity = String.valueOf(ds.child(snapshot.getKey()).child("product_stock_qty"));
                   textview1.setText(rate);
                   textview2.setText(quantity);
                   break;
               }
           }*/



     /*   promospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String discountcode = parent.getItemAtPosition(position).toString();
                int discountpercent = getPercentageDiscount(discountcode);
                edit_discount.setText(String.valueOf(discountpercent));

                String total = edit_total.getText().toString();
                if(!total.isEmpty()){
                    double totalamt = Double.parseDouble(total);
                    double netamt = totalamt *(100-discountpercent)/100;
                    edit_grandtotal.setText(String.valueOf(netamt));
                    edit_discountamt.setText(String.valueOf((totalamt*discountpercent)/100));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/



        make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent intent = new Intent(requireActivity(), PaymentActivity.class);

               String checkin=  bundle.getString("checkindate2");
                String checkout=bundle.getString("checkoutdate2");
                int rooms =bundle.getInt("room2",0);
                int adults =bundle.getInt("adult2",0);
                int childs =bundle.getInt("child2",0);




                String first=bundle.getString("fname");
                String last=bundle.getString("lname");
                String emailss=bundle.getString("emails");
                String phonenos=bundle.getString("phoneno");
                String fulladdress=bundle.getString("faddress");
                String extracharge = bundle.getString("extracharge");
                String selectedradio = bundle.getString("selectedradio");
                String discount = edit_discount.getText().toString();
                String promo = promospinner.getSelectedItem().toString();
                String total = edit_grandtotal.getText().toString();
                String roomprice = edit_roomprice.getText().toString();
                String tax = edit_roomtax.getText().toString();


                intent.putExtra("discount", discount);
                intent.putExtra("promo", promo);
                intent.putExtra("total", total);
                intent.putExtra("roomprice", roomprice);
                intent.putExtra("tax", tax);
                intent.putExtra("firstname", first);
                intent.putExtra("lastname", last);
                intent.putExtra("phone", phonenos);
                intent.putExtra("email", emailss);
                intent.putExtra("address",fulladdress);
                intent.putExtra("rooms", rooms);
                intent.putExtra("adults", adults);
                intent.putExtra("childs", childs);
                intent.putExtra("selectedradio", selectedradio );
                intent.putExtra("extracharge", extracharge);
                intent.putExtra("checkin", checkin);
                intent.putExtra("checkout", checkout);
                startActivity(intent);
            }



        });


        return view;
    }

    private void fetchdata() {



        mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear(); // Clear existing data
                itemCostMap.clear(); // Clear existing item-cost mapping



                // Loop through the Firebase data and add item names and their costs to lists and maps
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String itemName = snapshot.child("promocode").getValue(String.class);
                    String itemCost = snapshot.child("discount").getValue(String.class);



                    if (itemName != null && itemCost != null) {
                        list.add(itemName);
                        itemCostMap.put(itemName, itemCost);
                    }
                }



                // Populate the Spinner with the retrieved item names
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                        getActivity(), android.R.layout.simple_spinner_item,list);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                promospinner.setAdapter(spinnerAdapter);
             //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.my_spinner_dropdown, list);
              //  promospinner.setAdapter(adapter);



                // Set Spinner item selection listener to display the corresponding item cost in TextView
                promospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = list.get(position);
                        String correspondingCost = itemCostMap.get(selectedItem);
                        if (correspondingCost != null) {
                            edit_discount.setText(correspondingCost);
                        }
                        String total = edit_total.getText().toString();

                        if(!total.isEmpty()){
                            double totalamt = Double.parseDouble(total);
                            double discountpercent= Double.parseDouble(edit_discount.getText().toString());

                            double netamt = totalamt *(100-discountpercent)/100;
                            edit_grandtotal.setText(String.valueOf(netamt));
                            edit_discountamt.setText(String.valueOf((totalamt*discountpercent)/100));
                        }


                    }



                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Handle when no item is selected in the Spinner
                        edit_discount.setText("");
                    }
                });
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur while retrieving data
                Log.e("Firebase Error", databaseError.getMessage());
            }
        });

    }




}


  /*  private int getPercentageDiscount(String discountcode) {

        int discountpercent=0;
        switch(discountcode){
            case "NEWUSER25":
                discountpercent=25;
                break;
            case "CANVAS20":
                discountpercent=20;
                break;
            case "VALENTINE30":
                discountpercent=30;
                break;
            case "HAPPYSTAY40":
                discountpercent=40;
                break;
        }
        return discountpercent;



    } */
