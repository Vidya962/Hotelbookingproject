package com.example.newhotelbookingactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import Fragments.ConfirmFragment;
import Fragments.DatesFragment;

import Fragments.DetailsFragment;
import Fragments.RoomsFragment;

public class BookingRoom extends AppCompatActivity {

  /*  TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;*/

    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment=null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    DatesFragment dateFragment=new DatesFragment();
  //  DatesFragment dateFragment = new DatesFragment();




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_room);

        tabLayout = findViewById(R.id.tab_layout);
        frameLayout = findViewById(R.id.framelayout);


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, dateFragment);
        fragmentTransaction.commit();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Objects.requireNonNull(tabLayout.getTabAt(tab.getPosition()));


                String tag = "android:switcher:" + R.id.framelayout + ":" + position;
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment == null) {
                    switch (position) {
                        case 0:
                            fragment = dateFragment;
                            break;
                        case 1:
                            fragment = new RoomsFragment();
                            break;
                        case 2:
                        fragment = new DetailsFragment();
                            break;
                        case 3:
                            fragment = new ConfirmFragment();
                            break;
                    }


                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment, tag).commit();
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabLayout.getTabAt(tab.getPosition()).setCustomView(null);
            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });
        tabLayout.getTabAt(0);
    }






}