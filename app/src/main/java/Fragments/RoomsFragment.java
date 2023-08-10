package Fragments;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newhotelbookingactivity.ExtraAdapter;
import com.example.newhotelbookingactivity.R;
import com.example.newhotelbookingactivity.UserAdapter;
import com.example.newhotelbookingactivity.UserExtraClass;
import com.example.newhotelbookingactivity.UserRoomClass;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RoomsFragment extends Fragment implements ExtraAdapter.SumChangeListener{

    private UserAdapter userAdapter;
//
    private ExtraAdapter extraAdapter;

    private RecyclerView recyclerView;
//
    private RecyclerView recyclerView2;
    DatabaseReference db;
    //
    DatabaseReference db1;

    //
    List<UserRoomClass> list;



    //
    private List<UserExtraClass> list1 = new ArrayList<>();


    //

   // private Map<String, String> itemCostMap = new HashMap<>();

 //   private Map<String,String> itemCostMap=new HashMap<>();

    //
    TextView txt_roomprice;
    TextView txt_roomtype;
    TextView txt_roomtax;
    //TextView txtbreakfast;
   // TextView txtlaundry;
   // TextView txtrentalcar;
  //  TextView txtassistant;
    TextView txttotal, txt_otherfacility;
    Button btn_details;
   // CheckBox cb_breakfast, cb_laundry, cb_rentalcar, cb_assistant;
  //  private boolean[] initialCheckBoxState = {false, false, false, false};
    int otherprice = 0;
    int total = 0;
    int bigtotal = 0;
    int roomprice = 0;

    int otherfacility=0;
    int Duration =0;
    Bundle bundle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }





    public RoomsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView2=view.findViewById(R.id.recycleer);


        txt_roomtype=view.findViewById(R.id.txtroomtype);
        txt_roomprice=view.findViewById(R.id.txtroomprice);
        txt_roomtax=view.findViewById(R.id.txtroomtax);
        txt_otherfacility=view.findViewById(R.id.otherfacility);
        txttotal=view.findViewById(R.id.total);
      //  txtbreakfast=view.findViewById(R.id.txtbreakfast);
     //   txtlaundry=view.findViewById(R.id.txtlaundry);
      //  txtassistant=view.findViewById(R.id.txtassistant);
      //  txtrentalcar=view.findViewById(R.id.txtrentcar);
        btn_details=view.findViewById(R.id.button);
     //   cb_breakfast=view.findViewById(R.id.breakfast);
     //   cb_laundry=view.findViewById(R.id.laundry);
     //   cb_rentalcar=view.findViewById(R.id.rentalcar);
      //  cb_assistant=view.findViewById(R.id.assistant);


      //  cb_breakfast.setCompoundDrawablePadding(3);
      //  cb_laundry.setCompoundDrawablePadding(3);
     //   cb_rentalcar.setCompoundDrawablePadding(3);
     //   cb_assistant.setCompoundDrawablePadding(3);
      //  txtbreakfast.setText("100");
     //   txtlaundry.setText("80");
      //  txtrentalcar.setText("500");
      //  txtassistant.setText("200");
        bundle = getArguments();
        if(bundle!=null) {
            Duration = bundle.getInt("duration", 0);
        }



        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userAdapter=new UserAdapter(getContext(),this);
        recyclerView.setAdapter(userAdapter);



        db = FirebaseDatabase.getInstance().getReference("rooms");
        db.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dsnap: snapshot.getChildren()
                     ) {
                    UserRoomClass urf = dsnap.getValue(UserRoomClass.class);
                    if(urf!= null) {
                        list.add(urf);
                        userAdapter.setData(list);

                    }
                    }


                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();

            }
        });



       recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
       // extraAdapter=new ExtraAdapter(getContext(),this);
        extraAdapter=new ExtraAdapter(requireContext(), list1,  this );
       // extraAdapter=new ExtraAdapter(getContext(),this);
        recyclerView2.setAdapter(extraAdapter);


        db1=FirebaseDatabase.getInstance().getReference().child("extras");


        db1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserExtraClass item=snapshot.getValue(UserExtraClass.class);
                   // UserExtraClass item = snapshot.getValue(UserExtraClass.class);
                    list1.add(item);
                   // int otherfacility=Integer.parseInt(txt_otherfacility.getText().toString());
                  //  int netextraprice=otherfacility*Duration;



                }
                extraAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    /*    list1=new ArrayList<>();
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        extraAdapter=new ExtraAdapter(getContext(),this);
        recyclerView2.setAdapter(extraAdapter);




        db1=FirebaseDatabase.getInstance().getReference().child("extras");
        db1.getRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap:snapshot.getChildren()
                ){
                    UserExtraClass efg=snap.getValue(UserExtraClass.class);
                    if(efg!=null){
                        list1.add(efg);
                       extraAdapter.setData(list1);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();

            }
        });*/



        //




//



     /*   cb_breakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                roomprice = getSum();
                if (isChecked) {
                    if(userAdapter.getSelectedposition()!= -1){


                        otherprice += Integer.parseInt(txtbreakfast.getText().toString());
                        total  += Duration * Integer.parseInt(txtbreakfast.getText().toString());
                        Drawable checkmark = ContextCompat.getDrawable(getContext(), R.drawable.baseline_check_24);

                        cb_breakfast.setButtonDrawable(checkmark);

                    }
                    else{
                        Toast.makeText(getContext(), "Please select Room", Toast.LENGTH_SHORT).show();
                        cb_breakfast.setChecked(false);

                    }


                } else {
                    otherprice -= Integer.parseInt(txtbreakfast.getText().toString());
                    total -= Duration*Integer.parseInt(txtbreakfast.getText().toString());


                    cb_breakfast.setChecked(false);
                }
                bigtotal = roomprice+total;
                txt_otherfacility.setText(String.valueOf(otherprice));
                txttotal.setText(String.valueOf(bigtotal));



            }
        });

        cb_laundry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                roomprice = getSum();
                if (isChecked) {
                    if(userAdapter.getSelectedposition()!= -1){

                        otherprice += Integer.parseInt(txtlaundry.getText().toString());
                        total += Duration*Integer.parseInt(txtlaundry.getText().toString());
                        Drawable checkmark2 = ContextCompat.getDrawable(getContext(), R.drawable.baseline_check_24);

                        cb_laundry.setButtonDrawable(checkmark2);

                    }
                    else{
                        Toast.makeText(getContext(), "Please select Room", Toast.LENGTH_SHORT).show();
                        cb_laundry.setChecked(false);

                    }





                } else {
                    otherprice -= Integer.parseInt(txtlaundry.getText().toString());
                    total -= Duration*Integer.parseInt(txtlaundry.getText().toString());


                    cb_laundry.setChecked(false);


                }
                bigtotal = roomprice+total;
                txt_otherfacility.setText(String.valueOf(otherprice));
                txttotal.setText(String.valueOf(bigtotal));



            }
        });

        cb_rentalcar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                roomprice = getSum();
                if (isChecked) {
                    if(userAdapter.getSelectedposition()!= -1){

                        otherprice += Integer.parseInt(txtrentalcar.getText().toString());
                        total += Duration* Integer.parseInt(txtrentalcar.getText().toString());
                        Drawable checkmark3 = ContextCompat.getDrawable(getContext(), R.drawable.baseline_check_24);

                        cb_rentalcar.setButtonDrawable(checkmark3);

                    }
                    else{
                        Toast.makeText(getContext(), "Please select Room", Toast.LENGTH_SHORT).show();
                        cb_rentalcar.setChecked(false);

                    }



                } else {
                    otherprice -=  Integer.parseInt(txtrentalcar.getText().toString());
                    total -= Duration* Integer.parseInt(txtrentalcar.getText().toString());


                    cb_rentalcar.setChecked(false);
                }
                bigtotal = roomprice+total;
                txt_otherfacility.setText(String.valueOf(otherprice));
                txttotal.setText(String.valueOf(bigtotal));



            }
        });

        cb_assistant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                roomprice = getSum();
                if (isChecked) {
                    if(userAdapter.getSelectedposition()!= -1){

                        otherprice += Integer.parseInt(txtassistant.getText().toString());
                        total += Duration*Integer.parseInt(txtassistant.getText().toString());
                        Drawable checkmark4 = ContextCompat.getDrawable(getContext(), R.drawable.baseline_check_24);

                        cb_assistant.setButtonDrawable(checkmark4);

                    }
                    else{

                        Toast.makeText(getContext(), "Please select Room", Toast.LENGTH_SHORT).show();
                        cb_assistant.setChecked(false);

                    }




                } else {
                    otherprice -= Integer.parseInt(txtassistant.getText().toString());
                    total -= Duration*Integer.parseInt(txtassistant.getText().toString());


                    cb_assistant.setChecked(false);
                }
                bigtotal = roomprice+total;
                txt_otherfacility.setText(String.valueOf(otherprice));
                txttotal.setText(String.valueOf(bigtotal));



            }
        });*/






        userAdapter.setOnRadioButtonSelectedListener(new UserAdapter.OnRadioButtonSelectedListener() {
            @Override
            public void onRadioButtonSelected(int position) {
                UserRoomClass rdf = list.get(position);
                String selectText = rdf.getRoom_type();
                double totalamt = 0.0;

                if (position != -1){

                        totalamt = getRoomamount();
                        txttotal.setText(String.valueOf(totalamt));



                }
                txt_roomtype.setText(selectText);
                Toast.makeText(getContext(), "Selected: " + selectText, Toast.LENGTH_SHORT).show();
            }
        });

        //









   /*   extraAdapter.setOnCheckedChangeListener(new ExtraAdapter.OnCheckedChangeListener() {

          @Override
          public void onCheckedChanged(int position) {
              UserExtraClass efg = list1.get(position);
             String selectextra = efg.getCost();
              int amttotal = 0;

              if (position != -1) {
                 // amttotal =getSum();

                  txt_otherfacility.setText(selectextra);
                  amttotal= Integer.parseInt(txt_otherfacility.getText().toString());
                  txttotal.setText(String.valueOf(amttotal));
                  Toast.makeText(getContext(), "Selected: " + selectextra, Toast.LENGTH_SHORT).show();
              }


              //


         //    txt_otherfacility.setText(selectextra);
             // Toast.makeText(getContext(), "Selected: " + selectextra, Toast.LENGTH_SHORT).show();
          }
      });*/

        db1=FirebaseDatabase.getInstance().getReference().child("extras");













        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();


                if (bundle != null) {
                    String checkindate = bundle.getString("checkin");
                    String checkoutdate = bundle.getString("checkout");
                    int room1 = bundle.getInt("rooms",0);
                    int adult1 = bundle.getInt("adults",0);
                    int child1 = bundle.getInt("childs",0);


                    if(checkindate==null && checkoutdate==null && room1==0 && adult1==0 && child1==0){
                        Toast.makeText(getActivity(), "value is null", Toast.LENGTH_SHORT).show();
                    }
                    bundle2.putString("checkindate1", checkindate);
                    bundle2.putString("checkoutdate1", checkoutdate);
                    bundle2.putInt("room1", room1);
                    bundle2.putInt("adult1", adult1);
                    bundle2.putInt("child1", child1);




                }






                int selectedPosition = userAdapter.getSelectedposition();
                if (selectedPosition != -1) {
                    UserRoomClass rdf = list.get(selectedPosition);
                    String selectedradiotext = rdf.getRoom_type();
                    int roomprice = parseInt(txt_roomprice.getText().toString());
                    int netRoomPrice = roomprice*Duration;
                    String roomtax = txt_roomtax.getText().toString();
                    bundle2.putString("selectedradiotext", selectedradiotext);
                    bundle2.putInt("netRoomPrice", netRoomPrice);
                    bundle2.putString("roomtax", roomtax);
                } else {
                    Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                }

                //

     /*           int selectedPosition1=extraAdapter.getSelectedposition();
               if(selectedPosition1 !=-1){
                    UserExtraClass urf=list1.get(selectedPosition1) ;
                    String selectedchecktext=urf.getCost();
                    int otherfacility=parseInt(txt_otherfacility.getText().toString());
                  //  int netextraprice=otherfacility*Duration;
                    bundle2.putString("selectedchecktext",selectedchecktext);
                  //  bundle2.putInt("netextraprice", netextraprice);
                   bundle2.putInt("otherfacility",otherfacility);
                }
                else{
                    Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT).show();
               }*/





            /*  int selectedPosition1=extraAdapter.getSelectedposition();
                if(selectedPosition1 !=-1){

                    UserExtraClass urf=list1.get(selectedPosition1) ;
                    int otherfacility=parseInt(txt_otherfacility.getText().toString());
                    int netextraprice=otherfacility*Duration;

                    bundle2.putInt("netextraprice", netextraprice);
                    bundle2.putInt("otherfacility",otherfacility);

                }*/





                //
                String total = txttotal.getText().toString();
                String otherprice = txt_otherfacility.getText().toString();


                bundle2.putString("otherprice", otherprice);
                bundle2.putString("total", total);
               DetailsFragment detalisFragment=new DetailsFragment();
                detalisFragment.setArguments(bundle2);

                TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
                tabLayout.getTabAt(2).select();


                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, detalisFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();




            }
        });


        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }




    public void setTextToSecondTextView(String rprice, String rtax) {
        if (txt_roomprice != null && txt_roomtax != null) {
            txt_roomprice.setText(rprice);
            txt_roomtax.setText(rtax);
        }


    }



    public double getSum() {
        double sum = 0.0;
        if(txt_roomprice!=null){
            double price1 =Double.parseDouble(txt_roomprice.getText().toString());
           // int price3=parseInt(txt_otherfacility.getText().toString());
            //
            double price3=Double.parseDouble(txt_otherfacility.getText().toString());
            //
            double netroomprice = Duration*price1;
            double otherfacility=Duration*price3;

            double percent =Double.parseDouble(txt_roomtax.getText().toString());
            double price2 = (netroomprice*percent)/100;
            double price4=price2+otherfacility;
            sum = netroomprice+price4;
        }

        return sum;
    }

    @Override
    public void onSumChanged(int sum1) {
        if (userAdapter.getSelectedposition()!=-1){
            txt_otherfacility.setText(String.valueOf(sum1));
            double totalAmount = getSum();
            txttotal.setText(String.valueOf(totalAmount));
        }
        else{
            Toast.makeText(requireContext(), "Select Room plz", Toast.LENGTH_SHORT).show();
        }


    }

 /*   @Override
    public void onSumChanged(int sum1) {
        txt_otherfacility.setText(String.valueOf(sum1));


    }*/


//
 /*   private void handleCheckBoxChecked(CheckBox checkBox) {
        int[] checkedPrices = {
                Integer.parseInt(txt_otherfacility.getText().toString())};

        int checkBoxPrice = getCheckBoxPrice(checkBox);
        checkedPrices[getCheckBoxIndex(checkBox)] = checkBoxPrice;
        otherprice += checkBoxPrice;
        updateTotal();

    }

    private void handleCheckBoxUnchecked(CheckBox checkBox) {

        int[] checkedPrices = {Integer.parseInt(txt_otherfacility.getText().toString())};
        int checkBoxPrice = getCheckBoxPrice(checkBox);
        checkedPrices[getCheckBoxIndex(checkBox)] = 0;
        otherprice -= checkBoxPrice;
        updateTotal();
    }



    private int getCheckBoxPrice(CheckBox checkBox) {

        return Integer.parseInt(txt_otherfacility.getText().toString());
    }


    private int getCheckBoxIndex(CheckBox checkBox) {
        return 0;

    }

    public void updateTotal() {
        int[] checkedPrices = {Integer.parseInt(txt_otherfacility.getText().toString())};

        int otherprice = getCheckedPricesSum();


        total += checkedPrices[0] * Duration;


        bigtotal = roomprice + total;
        txt_otherfacility.setText(String.valueOf(otherprice));
        txttotal.setText(String.valueOf(bigtotal));
    }

    private int getCheckedPricesSum() {
        int[] checkedPrices = {Integer.parseInt(txt_otherfacility.getText().toString())};
        int sum = 0;
        for (int i = 0; i < checkedPrices.length; i++) {
            sum += checkedPrices[i];
        }
        return sum;
    }*/

    //

  /*  private void handleCheckBoxChecked(CheckBox checkBox) {
        int[] checkedPrices = {
                //Integer.parseInt(txtbreakfast.getText().toString()), Integer.parseInt(txtlaundry.getText().toString()), Integer.parseInt(txtrentalcar.getText().toString()), Integer.parseInt(txtassistant.getText().toString())};
        Integer.parseInt(txt_otherfacility.getText().toString())};
        int checkBoxPrice = getCheckBoxPrice(checkBox);
        checkedPrices[getCheckBoxIndex(checkBox)] = checkBoxPrice;
        otherprice += checkBoxPrice;
        updateTotal();
    }

    private void handleCheckBoxUnchecked(CheckBox checkBox) {

        int[] checkedPrices = {Integer.parseInt(txtbreakfast.getText().toString()), Integer.parseInt(txtlaundry.getText().toString()), Integer.parseInt(txtrentalcar.getText().toString()), Integer.parseInt(txtassistant.getText().toString())};
        int checkBoxPrice = getCheckBoxPrice(checkBox);
        checkedPrices[getCheckBoxIndex(checkBox)] = 0;
        otherprice -= checkBoxPrice;
        updateTotal();
    }*/




 /*   private int getCheckBoxPrice(CheckBox checkBox) {
        if (checkBox == cb_breakfast) {
            return Integer.parseInt(txtbreakfast.getText().toString());
        } else if (checkBox == cb_laundry) {
            return Integer.parseInt(txtlaundry.getText().toString());
        } else if (checkBox == cb_rentalcar) {
            return Integer.parseInt(txtrentalcar.getText().toString());
        } else if (checkBox == cb_assistant) {
            return Integer.parseInt(txtassistant.getText().toString());
        }
        return 0;
    }

    private int getCheckBoxIndex(CheckBox checkBox) {
        if (checkBox == cb_breakfast) {
            return 0;
        } else if (checkBox == cb_laundry) {
            return 1;
        } else if (checkBox == cb_rentalcar) {
            return 2;
        } else if (checkBox == cb_assistant) {
            return 3;
        }
        return -1;
    }

    public void updateTotal(){
        int[] checkedPrices = {Integer.parseInt(txtbreakfast.getText().toString()), Integer.parseInt(txtlaundry.getText().toString()), Integer.parseInt(txtrentalcar.getText().toString()), Integer.parseInt(txtassistant.getText().toString())};

        int otherprice = getCheckedPricesSum();

        if (cb_breakfast.isChecked()) {
            total += checkedPrices[0] * Duration;
        }
        if (cb_laundry.isChecked()) {
            total += checkedPrices[1] * Duration;
        }
        if (cb_rentalcar.isChecked()) {
            total += checkedPrices[2] * Duration;
        }
        if (cb_assistant.isChecked()) {
            total += checkedPrices[3] * Duration;
        }

        bigtotal = roomprice + total;
        txt_otherfacility.setText(String.valueOf(otherprice));
        txttotal.setText(String.valueOf(bigtotal));






    }*/

 /*   private int getCheckedPricesSum() {
        int[] checkedPrices = {Integer.parseInt(txtbreakfast.getText().toString()), Integer.parseInt(txtlaundry.getText().toString()), Integer.parseInt(txtrentalcar.getText().toString()), Integer.parseInt(txtassistant.getText().toString())};
        int sum = 0;
        for (int i = 0; i < checkedPrices.length; i++) {
            sum += checkedPrices[i];
        }
        return sum;
    }*/

  //
 /*   public void setTextToSecondTextView(String ecost) {

        if (txt_otherfacility != null ) {
            txt_otherfacility.setText(ecost);

        }
    }*/

    //

    private double getRoomamount(){
        double netroomprice=0.0;
        if(txt_roomprice!=null){
            double price1 =Double.parseDouble(txt_roomprice.getText().toString());
            double roomprice = Duration*price1;

            double percent =Double.parseDouble(txt_roomtax.getText().toString());
            double price2 = (roomprice*percent)/100;
             netroomprice = roomprice + price2;


        }
        return netroomprice;

    }
}

