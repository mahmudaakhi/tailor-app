package com.example.doorsteptailors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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

    ListView listView;

    DatabaseHelper databaseHelper;
    TailorListItems tailorListItemsNew;

    ArrayList<TailorListItems> tailorListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //for navigation bar
        drawerLayout =findViewById(R.id.drawerID);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for tab bar
        tablayout = findViewById(R.id.tabLayoutId);
        viewpager = findViewById(R.id.viewPagerId);


        tailorListItems = new ArrayList<>();
        databaseHelper=new DatabaseHelper(this);

        LoadData();

//        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
      //  tablayout.setupWithViewPager(viewpager);



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
                return new BestRated(tailorListItems);
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

    public void LoadData()
    {
        Cursor cursor = databaseHelper.showAllData();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No data is available in database",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                tailorListItemsNew = new TailorListItems();
                //listData.add(cursor.getString(0)+"."+cursor.getString(1)+"\n"+cursor.getString(5)+"\n"+cursor.getString(3)+" BDT");
                //Toast.makeText(this,"Data is available in database and data is:"+cursor.getString(7),Toast.LENGTH_LONG).show();
                tailorListItemsNew.setTailorName(cursor.getString(7));
                tailorListItemsNew.setAvailability(cursor.getString(3));
                tailorListItemsNew.setRating(cursor.getString(10));
                //Toast.makeText(this,"Data is available in database and data is:"+tailorListItemsNew.getTailorName(),Toast.LENGTH_LONG).show();
                tailorListItems.add(tailorListItemsNew);
            }
        }

    }

}
