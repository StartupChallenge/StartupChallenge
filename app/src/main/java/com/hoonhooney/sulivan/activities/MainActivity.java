package com.hoonhooney.sulivan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hoonhooney.sulivan.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_main);
    }

    public void SessionClick(View view) {
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
}