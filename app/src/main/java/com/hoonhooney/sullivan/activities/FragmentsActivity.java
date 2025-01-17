package com.hoonhooney.sullivan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.hoonhooney.sullivan.R;
import com.hoonhooney.sullivan.SwipeViewPager;
import com.hoonhooney.sullivan.fragments.ChatbotFragment;
import com.hoonhooney.sullivan.fragments.PronunciationFragment;
import com.hoonhooney.sullivan.fragments.SentencesFragment;
import com.hoonhooney.sullivan.fragments.SettingsFragment;
import com.hoonhooney.sullivan.fragments.SyllableFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FragmentsActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener {
    static final String TAG = "TAG : FragmentsActivity";

    private TabLayout tabLayout;
    private SwipeViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private Stack<Integer> pageStack;
    private boolean isBack = false;
    private int prePageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        pageStack = new Stack<Integer>();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager_session);
        viewPager.setPagingEnabled(false);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        //session 가져오기
        Intent intent = getIntent();
        int session = intent.getIntExtra("SESSION", 0);
        Log.d(TAG, "session : "+session);

        tabLayout.selectTab(tabLayout.getTabAt(session));
        prePageIndex = session;
        viewPager.setCurrentItem(session);
        viewPager.addOnPageChangeListener(this);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        tabLayout.setupWithViewPager(viewPager);

    }

//    PagerAdapter
    public class PagerAdapter extends FragmentStatePagerAdapter {
        int _numOfTabs;
        List<Fragment> fragments = new ArrayList<Fragment>();
        String[] titles = getResources().getStringArray(R.array.sessions);

        public PagerAdapter(FragmentManager fm, int numOfTabs) {
            super(fm);
            this._numOfTabs = numOfTabs;

            fragments.add(new SyllableFragment());
            fragments.add(new PronunciationFragment());
            fragments.add(new SentencesFragment());
            fragments.add(new ChatbotFragment());
            fragments.add(new SettingsFragment());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return _numOfTabs;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

//    이전에 눌렀던 session stack에 저장
    @Override
    public void onPageSelected(int position) {
        if (!isBack){
            pageStack.push(prePageIndex);
            Log.d(TAG, "pageStack : "+pageStack);
        }
        prePageIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

//    뒤로가기 누르면 stack에서 현재 session 정보를 pop
    @Override
    public void onBackPressed(){
        if (pageStack.isEmpty()){
            Log.d(TAG, "pageStack is empty");
            super.onBackPressed();
        }else{
            Log.d(TAG, "pageStack is not empty : "+pageStack);
            isBack = true;
            int index = pageStack.pop();
            viewPager.setCurrentItem(index);
            isBack = false;
        }
    }

}
