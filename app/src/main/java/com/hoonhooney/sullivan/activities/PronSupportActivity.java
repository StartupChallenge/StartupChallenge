package com.hoonhooney.sullivan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import com.hoonhooney.sullivan.R;
import com.hoonhooney.sullivan.VoiceTask;
import com.hoonhooney.sullivan.fragments.PronSupportFragment;
import com.hoonhooney.sullivan.fragments.PronunciationFragment;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

import static com.hoonhooney.sullivan.VoiceTask.VOICE_TASK;

public class PronSupportActivity extends AppCompatActivity
{
    FragmentPagerAdapter adapterViewPager;
    TextView title, textView_feedback, textView_pron_result;
    public List<List<String>> examples = new ArrayList<>();
    private int currentIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pron_support);

        TextView title = findViewById(R.id.pronunciation_title);

        textView_feedback = findViewById(R.id.textView_pron_feedback);
        textView_pron_result = findViewById(R.id.textView_pron_result);

        title.setText(PronunciationFragment.titlestr);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);

        findViewById(R.id.btn_pron_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 구글 마이크 부르기
                VoiceTask voiceTask = new VoiceTask(PronSupportActivity.this);
                voiceTask.execute();
            }
        });

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIdx = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //VoiceTask 결과 받는 부분
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == VOICE_TASK){
            ArrayList<String> results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (results != null){
                //구글 마이크에서 받아온 String
                String strResult = results.get(0);

                //텍스트 검사에 따른 피드백 보이기 (예시)
                textView_pron_result.setText(strResult);

                if (examples.get(currentIdx).contains(strResult))
                {
                    textView_feedback.setText(R.string.feedback_good);
                    textView_feedback.setTextColor(Color.GREEN);
                }
                else
                {
                    textView_feedback.setText(R.string.feedback_bad);
                    textView_feedback.setTextColor(Color.RED);
                }
            }
        }
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