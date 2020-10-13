package com.hoonhooney.sullivan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.hoonhooney.sullivan.R;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "TAG : MainActivity";

    private Boolean isExitFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //splash screen
        setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_main);
    }

//    세션 클릭 시
    public void sessionClick(View view) {
        Intent intent = new Intent(this, FragmentsActivity.class);

        switch(view.getId()){
            case R.id.btn_syllable:
                intent.putExtra("SESSION", 0);
                break;
            case R.id.btn_pronunciation:
                intent.putExtra("SESSION", 1);
                break;
            case R.id.btn_sentences:
                intent.putExtra("SESSION", 2);
                break;
            case R.id.btn_chatbot:
                intent.putExtra("SESSION", 3);
                break;
            case R.id.btn_settings:
                intent.putExtra("SESSION", 4);
                break;
        }

        startActivity(intent);
    }

//    뒤로 가기
    @Override
    public void onBackPressed() {

        if(isExitFlag){
            finish();
        } else {
            isExitFlag = true;
            Toast.makeText(this, getString(R.string.on_back_pressed),  Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExitFlag = false;
                }
            }, 2000);
        }
    }
}