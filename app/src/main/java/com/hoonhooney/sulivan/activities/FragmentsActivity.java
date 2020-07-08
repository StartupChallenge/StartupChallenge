package com.hoonhooney.sulivan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.hoonhooney.sulivan.R;
import com.hoonhooney.sulivan.SwipeViewPager;
import com.hoonhooney.sulivan.fragments.ChatbotFragment;
import com.hoonhooney.sulivan.fragments.PronunciationFragment;
import com.hoonhooney.sulivan.fragments.SentencesFragment;
import com.hoonhooney.sulivan.fragments.SettingsFragment;
import com.hoonhooney.sulivan.fragments.SyllableFragment;

public class FragmentsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private SwipeViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager_session);
        viewPager.setPagingEnabled(false);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        Intent intent = getIntent();
        int session = intent.getIntExtra("SESSION", 0);
        Log.d("TAG", "session : "+session);

        tabLayout.selectTab(tabLayout.getTabAt(session));
        viewPager.setCurrentItem(session);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);

    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int _numOfTabs;

        public PagerAdapter(FragmentManager fm, int numOfTabs) {
            super(fm);
            this._numOfTabs = numOfTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return getString(R.string.session_syllable);
                case 1:
                    return getString(R.string.session_pronunciation);
                case 2:
                    return getString(R.string.session_sentences);
                case 3:
                    return getString(R.string.session_chatbot);
                case 4:
                    return getString(R.string.session_settings);
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment tab;

            switch (position) {
                case 0:
                    tab = new SyllableFragment();
                    break;
                case 1:
                    tab = new PronunciationFragment();
                    break;
                case 2:
                    tab = new SentencesFragment();
                    break;
                case 3:
                    tab = new ChatbotFragment();
                    break;
                case 4:
                    tab = new SettingsFragment();
                    break;
                default:
                    tab = null;
            }

            assert tab != null;
            return tab;
        }

        @Override
        public int getCount() {
            return _numOfTabs;
        }
    }

}
