package com.hoonhooney.sullivan.fragments;

import android.content.Intent;
import android.graphics.Color;
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
import com.hoonhooney.sullivan.VoiceTask;

import java.util.ArrayList;
import java.util.Arrays;

import static com.hoonhooney.sullivan.VoiceTask.VOICE_TASK;

public class SyllableFragment extends Fragment
        implements View.OnClickListener {
    static final String TAG = "TAG : SyllableFragment";


    final static int[] ChoSung   = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
    final static int[] JungSung = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };

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
                // 구글 마이크 부르기
                VoiceTask voiceTask = new VoiceTask(this);
                voiceTask.execute();
                break;

            case R.id.input_consonant:

            case R.id.input_vowel:
                inputChar(view);
                break;
        }
    }

//    녹음 결과에 대한 feedback 제공
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK && requestCode == VOICE_TASK){
            ArrayList<String> results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            int code_consonant = 0;
            String strConsonant = textView_consonant.getText().toString();
            String[] arrConsonants = getString(R.string.char_consonants).split(" ");
            for (int i = 0; i < arrConsonants.length; i++){
                if (strConsonant.equals(arrConsonants[i]))
                    code_consonant = i;
            }
            
            int code_vowel = ((int)textView_vowel.getText().charAt(0)) - 12623;

            strForATry = ""+combine(code_consonant, code_vowel);

            if (results != null){
                //구글 마이크에서 받아온 String
                String strResult = results.get(0);

                int cho = getCho(strResult);
                int jung = getJung(strResult);

                textView_result.setText("'"+strResult+"'");

                Log.w("jung = code_vowel", Boolean.toString(jung==code_vowel));
                Log.w("jung ", Integer.toString(jung));
                Log.w("code_vowel ", Integer.toString(code_vowel));

                if(code_consonant==cho && code_vowel == jung){
                    textView_feedback.setText(getString(R.string.feedback_good));
                    textView_feedback.setTextColor(Color.GREEN);
                }
                else if(code_consonant != cho && code_vowel == jung){
                    switch(code_consonant){
                        case 0:
                            textView_feedback.setText(getString(R.string.feedback_bad_0));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 1:
                            textView_feedback.setText(getString(R.string.feedback_bad_1));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 2:
                            textView_feedback.setText(getString(R.string.feedback_bad_2));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 3:
                            textView_feedback.setText(getString(R.string.feedback_bad_3));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 4:
                            textView_feedback.setText(getString(R.string.feedback_bad_4));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 5:
                            textView_feedback.setText(getString(R.string.feedback_bad_5));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 6:
                            textView_feedback.setText(getString(R.string.feedback_bad_6));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 7:
                            textView_feedback.setText(getString(R.string.feedback_bad_7));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 8:
                            textView_feedback.setText(getString(R.string.feedback_bad_8));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 9:
                            textView_feedback.setText(getString(R.string.feedback_bad_9));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 10:
                            textView_feedback.setText(getString(R.string.feedback_bad_10));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 11:
                            textView_feedback.setText(getString(R.string.feedback_bad_11));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 12:
                            textView_feedback.setText(getString(R.string.feedback_bad_12));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 13:
                            textView_feedback.setText(getString(R.string.feedback_bad_13));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 14:
                            textView_feedback.setText(getString(R.string.feedback_bad_14));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 15:
                            textView_feedback.setText(getString(R.string.feedback_bad_15));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 16:
                            textView_feedback.setText(getString(R.string.feedback_bad_16));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 17:
                            textView_feedback.setText(getString(R.string.feedback_bad_17));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 18:
                            textView_feedback.setText(getString(R.string.feedback_bad_18));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                    }
                }
                else if(code_consonant == cho && code_vowel != jung){
                    switch(code_vowel){
                        case 0:
                            textView_feedback.setText(getString(R.string.feedback_bad_19));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 1:
                            textView_feedback.setText(getString(R.string.feedback_bad_20));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 2:
                            textView_feedback.setText(getString(R.string.feedback_bad_21));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 3:
                            textView_feedback.setText(getString(R.string.feedback_bad_22));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 4:
                            textView_feedback.setText(getString(R.string.feedback_bad_23));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 5:
                            textView_feedback.setText(getString(R.string.feedback_bad_24));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 6:
                            textView_feedback.setText(getString(R.string.feedback_bad_25));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 7:
                            textView_feedback.setText(getString(R.string.feedback_bad_26));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 8:
                            textView_feedback.setText(getString(R.string.feedback_bad_27));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 9:
                            textView_feedback.setText(getString(R.string.feedback_bad_28));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 10:
                            textView_feedback.setText(getString(R.string.feedback_bad_29));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 11:
                            textView_feedback.setText(getString(R.string.feedback_bad_30));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 12:
                            textView_feedback.setText(getString(R.string.feedback_bad_31));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 13:
                            textView_feedback.setText(getString(R.string.feedback_bad_32));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 14:
                            textView_feedback.setText(getString(R.string.feedback_bad_33));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 15:
                            textView_feedback.setText(getString(R.string.feedback_bad_34));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 16:
                            textView_feedback.setText(getString(R.string.feedback_bad_35));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 17:
                            textView_feedback.setText(getString(R.string.feedback_bad_36));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 18:
                            textView_feedback.setText(getString(R.string.feedback_bad_37));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 19:
                            textView_feedback.setText(getString(R.string.feedback_bad_38));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                        case 20:
                            textView_feedback.setText(getString(R.string.feedback_bad_39));
                            textView_feedback.setTextColor(Color.RED);
                            break;
                    }
                }
                else if (code_consonant != cho && code_vowel != jung){
                    textView_feedback.setText(getString(R.string.feedback_bad));
                    textView_feedback.setTextColor(Color.RED);
                }

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

    public static int getCho(String s) {
        char c = s.charAt(0);
        int cho = (((c-0xAC00)-(c-0xAC00)%28)/28)/21;
        return cho;
    }

    public static int getJung(String s) {
        char c = s.charAt(0);
        int jung = (((c-0xAC00)-(c-0xAC00)%28)/28)%21;
        return jung;
    }

}