package com.hoonhooney.sullivan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoonhooney.sullivan.R;
import com.hoonhooney.sullivan.fragments.PronSupportFragment;
import com.hoonhooney.sullivan.fragments.PronunciationFragment;

import me.relex.circleindicator.CircleIndicator;

public class PronSupportActivity extends AppCompatActivity
{

    FragmentPagerAdapter adapterViewPager;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pron_support);

        TextView title = findViewById(R.id.pronunciation_title);
        title.setText(PronunciationFragment.titlestr);


        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS;




        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            if (PronunciationFragment.titlestr == "받침의 발음"){
                return NUM_ITEMS=13;
            }
            else if (PronunciationFragment.titlestr == "음의 첨가"){
                return NUM_ITEMS =4;
            }
            else{
                return NUM_ITEMS =5;
            }
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return PronSupportFragment.newInstance(position+1);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
    }

}