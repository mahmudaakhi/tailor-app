package com.example.doorsteptailors;

import android.database.Cursor;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    private TabLayout tablayout;
    private ViewPager viewpager;
    TailorListItems tailorListItemsNew;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //for navigation bar
        drawerLayout =findViewById(R.id.drawerID);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//      // enabling action bar app icon and behaving it as toggle button
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        //for tab bar
        tablayout = findViewById(R.id.tabLayoutId);


//        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
       // tablayout.setupWithViewPager(viewpager);


    }
    class MyPagerAdapter extends FragmentPagerAdapter{

        String[] text ={"BestRated","Favourites","Discount"};

        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            if(i == 0)
            {
                return new BestRated();
            }
            if(i == 1)
            {
              return new Favourites();
            }
            if (i == 2)
            {
                return new Discounts();
            }
            return null;
        }

        @Override
        public int getCount() {
            return text.length;
        }
        public CharSequence getPageTitle(int position)
        {
            return text[position];
        }
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.right_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }



    }


