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
import com.hoonhooney.sullivan.CharacterListDialog;
import com.hoonhooney.sullivan.R;
import com.hoonhooney.sullivan.SwipeViewPager;
import com.hoonhooney.sullivan.fragments.ChatbotFragment;
import com.hoonhooney.sullivan.fragments.PronunciationFragment;
import com.hoonhooney.sullivan.fragments.SentencesFragment;
import com.hoonhooney.sullivan.fragments.SettingsFragment;
import com.hoonhooney.sullivan.fragments.SyllableFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class FragmentsActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, CharacterListDialog.CharItemClickListener {
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

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());

    }

//    fragment에서 pageStack 불러오기 위한 getter
    public Stack<Integer> getPageStack(){return pageStack;}

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
            if(pageStack.contains(prePageIndex)){
                pageStack.remove((Integer) prePageIndex);
            }
            pageStack.push(prePageIndex);
            Log.d(TAG, "pageStack : "+pageStack);
        }
        prePageIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

//    SyllableFragment에서 연 CharacterListDialog 결과값 전달
    @Override
    public void onItemClicked(char result, String style) {
        Log.d(TAG, "result : "+result);

        SyllableFragment syllableFragment = (SyllableFragment)pagerAdapter.fragments.get(0);

        int resId = getResources().getIdentifier(""+result, "string", getPackageName());
        java.util.Map<Character, Integer> map = new HashMap<Character,Integer>(){
            {
                put('ㄱ',R.drawable.consonant_1);
                put('ㄲ',R.drawable.consonant_2);
                put('ㄴ',R.drawable.consonant_3);
                put('ㄷ',R.drawable.consonant_4);
                put('ㄸ',R.drawable.consonant_5);
                put('ㄹ',R.drawable.consonant_6);
                put('ㅁ',R.drawable.consonant_7);
                put('ㅂ',R.drawable.consonant_8);
                put('ㅃ',R.drawable.consonant_9);
                put('ㅅ',R.drawable.consonant_10);
                put('ㅆ',R.drawable.consonant_11);
                put('ㅇ',R.drawable.consonant_12);
                put('ㅈ',R.drawable.consonant_13);
                put('ㅉ',R.drawable.consonant_14);
                put('ㅊ',R.drawable.consonant_15);
                put('ㅋ',R.drawable.consonant_16);
                put('ㅌ',R.drawable.consonant_17);
                put('ㅍ',R.drawable.consonant_18);
                put('ㅎ',R.drawable.consonant_19);
                put('ㅏ',R.drawable.vowel_1);
                put('ㅑ',R.drawable.vowel_2);
                put('ㅓ',R.drawable.vowel_3);
                put('ㅕ',R.drawable.vowel_4);
                put('ㅗ',R.drawable.vowel_5);
                put('ㅛ',R.drawable.vowel_6);
                put('ㅜ',R.drawable.vowel_7);
                put('ㅠ',R.drawable.vowel_8);
                put('ㅡ',R.drawable.vowel_9);
                put('ㅣ',R.drawable.vowel_10);
                put('ㅢ',R.drawable.vowel_11);
                put('ㅐ',R.drawable.vowel_12);
                put('ㅒ',R.drawable.vowel_13);
                put('ㅔ',R.drawable.vowel_14);
                put('ㅖ',R.drawable.vowel_15);
                put('ㅘ',R.drawable.vowel_16);
                put('ㅙ',R.drawable.vowel_17);
                put('ㅚ',R.drawable.vowel_18);
                put('ㅝ',R.drawable.vowel_19);
                put('ㅞ',R.drawable.vowel_20);
                put('ㅟ',R.drawable.vowel_21);
            }
        };
        if (style.equals(getString(R.string.consonant))) {
            syllableFragment.textView_consonant.setText(""+result);
            //그림과 설명 바뀜 추가
            syllableFragment.imageView_consonant.setImageResource(map.get(result));
            syllableFragment.textView_explain_consonant.setText(getString(resId));
        }
        else{
            syllableFragment.textView_vowel.setText(""+result);
            //그림과 설명 바뀜 추가
            syllableFragment.imageView_vowel.setImageResource(map.get(result));
            syllableFragment.textView_explain_vowel.setText(getString(resId));
        }

        syllableFragment.textView_feedback.setText("");
        syllableFragment.textView_result.setText(getString(R.string.try_record));
    }

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
