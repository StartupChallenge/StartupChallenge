package com.hoonhooney.sullivan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.util.Log;

import androidx.fragment.app.Fragment;

//구글 마이크 부르는 Task
public class VoiceTask extends AsyncTask<String, Integer, String> {
    static final String TAG = "TAG : VoiceTask";
    public static final int VOICE_TASK = 2;

    @SuppressLint("StaticFieldLeak")
    private Activity mActivity = null;
    private Fragment mFragment = null;

    public VoiceTask(Activity activity){
        mActivity = activity;
    }

    public VoiceTask(Fragment fragment){
        mFragment = fragment;
    }

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

    private void getVoice(){
        Intent intent = new Intent();
        intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        String language = "ko-KR";

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);

        if (mActivity != null)
            mActivity.startActivityForResult(intent, VOICE_TASK);
        else if (mFragment != null)
            mFragment.startActivityForResult(intent, VOICE_TASK);
    }
}
