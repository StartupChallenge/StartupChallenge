package com.hoonhooney.sullivan.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hoonhooney.sullivan.R;
import com.hoonhooney.sullivan.activities.FragmentsActivity;

import java.util.ArrayList;

public class SyllableFragment extends Fragment implements View.OnClickListener {

    private FrameLayout btnRecord;
    private TextView textView_result;

    public SyllableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syllable, container, false);

        textView_result = view.findViewById(R.id.textView_syllable_result);

        btnRecord = view.findViewById(R.id.btn_syllable_record);
        btnRecord.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_syllable_record:
                VoiceTask voiceTask = new VoiceTask();
                voiceTask.execute();
                break;
        }
    }

    public class VoiceTask extends AsyncTask<String, Integer, String> {
        static final String TAG = "TAG : VoiceTask";

        private String str = null;
        @Override
        protected String doInBackground(String... strings) {

            try{
                getVoice();
            } catch (Exception e){
                Log.e(TAG, "Exception : ", e);
            }

            return str;
        }

        @Override
        protected void onPostExecute(String result){
            try{

            } catch(Exception e){
                Log.e(TAG, "Exception : ", e);
            }
        }
    }

    private void getVoice(){
        Intent intent = new Intent();
        intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        String language = "ko-KR";

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK){
            ArrayList<String> results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String str = results.get(0);
            textView_result.setText(str);
        }
    }
}