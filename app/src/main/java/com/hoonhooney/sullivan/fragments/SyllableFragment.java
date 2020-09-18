package com.hoonhooney.sullivan.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hoonhooney.sullivan.CharacterListDialog;
import com.hoonhooney.sullivan.R;

import java.util.ArrayList;

public class SyllableFragment extends Fragment
        implements View.OnClickListener {
    static final String TAG = "TAG : SyllableFragment";

    private FrameLayout btnRecord, input_consonant, input_vowel;
    public TextView textView_consonant, textView_vowel,
            textView_explain_consonant, textView_explain_vowel,
            textView_feedback, textView_result;
    public ImageView imageView_consonant, imageView_vowel;

    private String strForATry;

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

        input_consonant = view.findViewById(R.id.input_consonant);
        input_vowel = view.findViewById(R.id.input_vowel);
        input_consonant.setOnClickListener(this);
        input_vowel.setOnClickListener(this);

        textView_consonant = view.findViewById(R.id.textView_consonant);
        textView_vowel = view.findViewById(R.id.textView_vowel);
        textView_explain_consonant = view.findViewById(R.id.textView_explain_consonant);
        textView_explain_consonant.setText(getString(getResources()
                .getIdentifier(textView_consonant.getText().toString(), "string", getContext().getPackageName())));
        textView_explain_vowel = view.findViewById(R.id.textView_explain_vowel);
        textView_explain_vowel.setText(getString(getResources()
                .getIdentifier(textView_vowel.getText().toString(), "string", getContext().getPackageName())));
        textView_feedback = view.findViewById(R.id.textView_syllable_feedback);

        imageView_consonant = view.findViewById(R.id.imageView_consonant);

        imageView_vowel = view.findViewById(R.id.imageView_vowel);

        strForATry = "가";

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_syllable_record:
                VoiceTask voiceTask = new VoiceTask();
                voiceTask.execute();
                break;

            case R.id.input_consonant:

            case R.id.input_vowel:
                inputChar(view);
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

//    녹음 결과에 대한 feedback 제공
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK){
            ArrayList<String> results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String str = results.get(0);
            
            int code_consonant = 0;
            String strConsonant = textView_consonant.getText().toString();
            String[] arrConsonants = getString(R.string.char_consonants).split(" ");
            for (int i = 0; i < arrConsonants.length; i++){
                if (strConsonant.equals(arrConsonants[i]))
                    code_consonant = i;
            }
            
            int code_vowel = ((int)textView_vowel.getText().charAt(0)) - 12623;

            strForATry = ""+combine(code_consonant, code_vowel);

            textView_result.setText("'"+str+"'");

            if (strForATry.equals(str)){
                textView_feedback.setText(getString(R.string.feedback_good));
                textView_feedback.setTextColor(Color.GREEN);
            }else{
                textView_feedback.setText(getString(R.string.feedback_bad));
                textView_feedback.setTextColor(Color.RED);
            }

        }
    }

    private void inputChar(View view){
        String strForList = "";
        String title = "";

        if (view.getId() == R.id.input_consonant){
            strForList = getString(R.string.char_consonants);
            title = getString(R.string.consonant);
        }else{
            strForList = getString(R.string.char_vowels);
            title = getString(R.string.vowel);
        }

        String[] arrForList = strForList.split(" ");

        CharacterListDialog dialog = new CharacterListDialog(getContext(), arrForList, title);
        dialog.show(getChildFragmentManager(), CharacterListDialog.TAG);
    }

//    자음 + 모음 합 연산
    public static char combine(int x1, int x2) {
        int x = (x1 * 21 * 28) + (x2 * 28);
        return (char) (x + 0xAC00);
    }
}