package Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.newhotelbookingactivity.R;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DatesFragment extends Fragment {
    private TextView cdtextdate,cotextdate,edbooking,txtroom,txtrooms,textduration,noofadults,totaladults,noofchildren,totalchildren;
    Button btnadd,btnsubstract,btnadd1,btnsubstract1,btnadd2,btnsubstract2,selectrooms;
    Calendar mycalendar;
    DatePickerDialog.OnDateSetListener dateSetListener1,dateSetListener2;



    public DatesFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dates,container,false);

        //
        cdtextdate=view.findViewById(R.id.in_date);
        cotextdate=view.findViewById(R.id.out_date);
        edbooking=view.findViewById(R.id.bookingdate);
        btnadd=view.findViewById(R.id.add);
        btnsubstract=view.findViewById(R.id.subtract);
        btnadd1=view.findViewById(R.id.add1);
        btnsubstract1=view.findViewById(R.id.subtract1);
        btnadd2=view.findViewById(R.id.add2);
        btnsubstract2=view.findViewById(R.id.subtract2);
        selectrooms=view.findViewById(R.id.selectroom);
        txtroom=view.findViewById(R.id.textroomno);
        txtrooms=view.findViewById(R.id.totalrooms);
        textduration=view.findViewById(R.id.duration);
        noofadults=view.findViewById(R.id.adults);
        totaladults=view.findViewById(R.id.noadults);
        noofchildren=view.findViewById(R.id.children);
        totalchildren=view.findViewById(R.id.totalchildren);
        mycalendar=Calendar.getInstance();
        edbooking.setFocusable(false);
        edbooking.setEnabled(false);
        txtroom.setText("0");
        txtrooms.setText("0");
        noofadults.setText("0");
        noofchildren.setText("0");
        totaladults.setText("0");
        totaladults.setText("0");

        int year=mycalendar.get(Calendar.YEAR);
        int month=mycalendar.get(Calendar.MONTH);
        int day=mycalendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        edbooking.setText(currentDate);


        //
        cdtextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Calendar calendar=Calendar.getInstance();
                //
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePicker datePicker=new DatePicker(getContext());
                datePicker.setMinDate(calendar.getTimeInMillis());

                DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String checkindate=String.format("%02d/%02d/%04d",dayOfMonth,month+1,year);
                        cdtextdate.setText(checkindate);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),null);
                datePickerDialog.getDatePicker().setMinDate(datePicker.getMinDate());
                datePickerDialog.show();


            }
        });


        cotextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                DatePicker datePicker=new DatePicker(getContext());
                datePicker.setMinDate(calendar.getTimeInMillis());



                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String checkoutdate=String.format("%02d/%02d/%04d",dayOfMonth,month+1,year);
                        cotextdate.setText(checkoutdate);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));


                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),null);
                datePickerDialog.getDatePicker().setMinDate(datePicker.getMinDate());
                datePickerDialog.show();


            }

        });

        cotextdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String dateStr1=cdtextdate.getText().toString();
                String dateStr2=cotextdate.getText().toString();
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");



                try {

                    Date date1=sdf.parse(dateStr1);
                    Date date2=sdf.parse(dateStr2);
                    if(date1.equals(date2)){
                        textduration.setText("1"+"night");
                    }
                    else{
                        Long diffInMs=date2.getTime()-date1.getTime();
                        Long diffInDays= TimeUnit.MILLISECONDS.toDays(diffInMs);
                        textduration.setText(diffInDays+ "nights");
                    }


                }
                catch(ParseException e){
                    e.printStackTrace();


                }

            }
        });

        edbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String edbookingdate=String.format("%02d/%02d/%04d",dayOfMonth,month+1,year);
                        edbooking.setText(edbookingdate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval=txtroom.getText().toString();
                int val=Integer.parseInt(currentval);
                val++;
                txtroom.setText(String.valueOf(val));
                txtrooms.setText(String.valueOf(val));
            }
        });


        btnsubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval=txtroom.getText().toString();
                int val=Integer.parseInt(currentval);
                val--;
                txtroom.setText(String.valueOf(val));
                txtrooms.setText(String.valueOf(val));
            }
        });


        btnadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval=noofadults.getText().toString();
                int val=Integer.parseInt(currentval);
                val++;
                noofadults.setText(String.valueOf(val));
                totaladults.setText(String.valueOf(val));
            }
        });


        btnsubstract1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval=noofadults.getText().toString();
                int val=Integer.parseInt(currentval);
                val--;
                noofadults.setText(String.valueOf(val));
                totaladults.setText(String.valueOf(val));
            }
        });

        btnadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval=noofchildren.getText().toString();
                int val=Integer.parseInt(currentval);
                val++;
                noofchildren.setText(String.valueOf(val));
                totalchildren.setText(String.valueOf(val));
            }
        });


        btnsubstract2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentval=noofchildren.getText().toString();
                int val=Integer.parseInt(currentval);
                val--;
                noofchildren.setText(String.valueOf(val));
                totalchildren.setText(String.valueOf(val));
            }
        });


        selectrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkin=cdtextdate.getText().toString();
                String checkout=cotextdate.getText().toString();
                String booking=edbooking.getText().toString();

                int duration=0;
                String str=textduration.getText().toString();
                Pattern pattern=Pattern.compile("\\d+");
                Matcher matcher=pattern.matcher(str);
                if(matcher.find()){
                    duration=Integer.parseInt(matcher.group());
                }
                int rooms=Integer.parseInt(txtroom.getText().toString());
                int adults=Integer.parseInt(noofadults.getText().toString());
                int childs=Integer.parseInt(noofchildren.getText().toString());



                Bundle bundle=new Bundle();
                bundle.putString("checkin",checkin);
                bundle.putString("checkout",checkout);
                bundle.putInt("rooms",rooms);
                bundle.putInt("adults",adults);
                bundle.putInt("duration",duration);
                bundle.putInt("childs",childs);



                RoomsFragment roomsFragment=new RoomsFragment();
                roomsFragment.setArguments(bundle);

                TabLayout tabLayout=getActivity().findViewById(R.id.tab_layout);
                tabLayout.getTabAt(1).select();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, roomsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();









            }
        });



        return view;

    }



    public static int getDaysDifference(Date sdate,Date edate){
        if(sdate==null||edate==null){
            return  0;
        }
        else {
            return (int) ((edate.getTime()-sdate.getTime()) /(1000*60*60*24));

        }

    }

    public String getCheckin(){
        return  cdtextdate.getText().toString();
    }
    public String getCheckout(){
        return cotextdate.getText().toString();
    }

    public int getRoomno(){
        return Integer.parseInt(txtroom.getText().toString());
    }

    public int getAdultno(){
        return Integer.parseInt(noofadults.getText().toString());
    }

    public int getChildno(){
        return Integer.parseInt(noofchildren.getText().toString());
    }
}

