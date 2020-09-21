package com.example.doorsteptailors;

import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class TailorOrderDetails extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailor_order_details);

        tabLayout=findViewById(R.id.tabLayoutId);

        viewPager.setAdapter(new MypagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


    }
    class MypagerAdapter extends FragmentPagerAdapter{

        String[] text={"Received","Accepted","Completed"};
        public MypagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if(i==0)
            {
                return  new Received_orders();

            }
            if(i==1)
            {
                return new Accecpted_orders();
            }
            if(i==2)
            {
                return new Completed_orders();
            }
            return null;
        }

        @Override
        public int getCount() {
            return text.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return text[position];
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.right_menu,menu);
        return true;
    }
}
