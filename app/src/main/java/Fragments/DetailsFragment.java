package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newhotelbookingactivity.R;
import com.google.android.material.tabs.TabLayout;


public class DetailsFragment extends Fragment {

    EditText firstname,lastname,email,phone,address,postcode;
    Button btn_confirm;




    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      //  View view= inflater.inflate(R.layout.fragment_detalis, container, false);
        View view=inflater.inflate(R.layout.fragment_details,container,false);
        firstname=view.findViewById(R.id.firstname);
        lastname=view.findViewById(R.id.lastname);
        email=view.findViewById(R.id.email);
        phone=view.findViewById(R.id.phone);
        address=view.findViewById(R.id.address);
        postcode=view.findViewById(R.id.postcode);
        btn_confirm=view.findViewById(R.id.button);

        Bundle bundle=getArguments();

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle setDatabundle=new Bundle();


                String fname=firstname.getText().toString();
                String lname=lastname.getText().toString();
                String emails=email.getText().toString();
                String phoneno=phone.getText().toString();
                String faddress=address.getText().toString();
                String pincode=postcode.getText().toString();




                if(bundle!=null) {
                    String checkin = bundle.getString("checkindate1");
                    String checkout=bundle.getString("checkoutdate1");
                    int room = bundle.getInt("room1", 0);
                    int child = bundle.getInt("child1", 0);
                    int adult = bundle.getInt("adult1", 0);

                    int roomPrice = bundle.getInt("netRoomPrice", 0);
                    String selectedradio = bundle.getString("selectedradiotext");

                    String total = bundle.getString("total");
                    String roomTax = bundle.getString("roomtax");
                    String extracharge = bundle.getString("otherprice");


                    if (checkin==null && checkout==null &&room ==0 && child ==0 && adult == 0) {
                        Toast.makeText(getActivity(), "values are null", Toast.LENGTH_SHORT).show();
                    }


                    setDatabundle.putString("extracharge", extracharge);
                    setDatabundle.putString("roomTax", roomTax);
                    setDatabundle.putInt("roomPrice", roomPrice);
                    setDatabundle.putString("selectedradio", selectedradio);
                    setDatabundle.putString("total", total);
                    setDatabundle.putString("checkindate2", checkin);
                    setDatabundle.putString("checkoutdate2", checkout);
                    setDatabundle.putInt("room2", room);
                    setDatabundle.putInt("child2", child);
                    setDatabundle.putInt("adult2", adult);


                }
                setDatabundle.putString("fname",fname);
                setDatabundle.putString("lname",lname);
                setDatabundle.putString("emails",emails);
                setDatabundle.putString("phoneno",phoneno);
                setDatabundle.putString("faddress",faddress);
                setDatabundle.putString("pincode",pincode);

             //   Intent intent=new Intent(getActivity(), PaymentActivity.class);
              //  startActivity(intent);




                ConfirmFragment confirmFragment=new ConfirmFragment();
                confirmFragment.setArguments(setDatabundle);


                TabLayout tabLayout=getActivity().findViewById(R.id.tab_layout);
                tabLayout.getTabAt(3).select();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, confirmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });








        return view;
    }
}