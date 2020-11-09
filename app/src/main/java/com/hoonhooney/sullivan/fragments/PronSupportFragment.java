package com.hoonhooney.sullivan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hoonhooney.sullivan.R;

import java.util.ArrayList;
import java.util.List;

public class PronSupportFragment extends Fragment {
    private int page;   //fragment page 번호
    private List<String> words = new ArrayList<>(); //음성인식 대상 단어 list
    TextView explanation1, explanation2, explanation3, explanation4, explanation5, explanation6, explanation7,
    explanation8, explanation9, example1, example2, example3, example4, textView_result, textView_feedback;
    private FrameLayout btnRecord;

    public static PronSupportFragment newInstance(int page) {
        PronSupportFragment fragment = new PronSupportFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("someInt", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int[] id ;
        String[][] strarr;

        if (PronunciationFragment.titlestr == "받침의 발음"){
            id = new int[]{1, 2, 3, 2, 2, 3, 4, 4, 5, 4, 4, 4, 4};
            strarr = new String[][]{
                    {"s1_explanation1","s1_explanation2","s1_example1","s1_example2"},
                    {"s2_explanation1", "s2_explanation2", "s2_explanation3", "s2_explanation4", "s2_explanation5", "s2_explanation6"
                            , "s2_explanation7", "s2_example1", "s2_example2"},
                    {"s3_explanation1", "s3_explanation2", "s3_explanation2", "s3_explanation4", "s3_explanation5", "s3_explanation6"
                            , "s3_explanation7", "s3_explanation8", "s3_explanation9", "s3_example1", "s3_example2"},
                    {"s4_explanation1", "s4_explanation2", "s4_explanation3", "s4_explanation4", "s4_explanation5", "s4_explanation6"
                            , "s4_explanation7", "s4_example1", "s4_example2"},
                    {"s5_explanation1", "s5_explanation2", "s5_explanation3", "s5_explanation4", "s5_explanation5", "s5_explanation6"
                            , "s5_explanation7", "s5_example1", "s5_example2"},
                    {"s6_explanation1", "s6_explanation2", "s6_explanation2", "s6_explanation4", "s6_explanation5", "s6_explanation6"
                            , "s6_explanation7", "s6_explanation8", "s6_explanation9", "s6_example1", "s6_example2"},
                    {"s7_explanation1", "s7_example1", "s7_example2"},
                    {"s8_explanation1", "s8_example1", "s8_example2"},
                    {"s9_explanation1", "s9_example1", "s9_example2", "s9_explanation2", "s9_example3", "s9_example4"},
                    {"s10_explanation1", "s10_example1", "s10_example2"},
                    {"s11_explanation1", "s11_example1", "s11_example2"},
                    {"s12_explanation1", "s12_example1", "s12_example2"},
                    {"s13_explanation1", "s13_example1", "s13_example2"}
            };
        }
        else if (PronunciationFragment.titlestr == "음의 동화"){
            id = new int[]{6, 4, 4, 4, 4};
            strarr = new String[][]{
                    {"e1_explanation1", "e1_explanation2", "e1_explanation3", "e1_explanation4", "e1_explanation5", "e1_example1", "e1_example2"},
                    {"e2_explanation1", "e2_example1", "e2_example2"},
                    {"e3_explanation1", "e3_example1", "e3_example2"},
                    {"e4_explanation1", "e4_example1", "e4_example2"},
                    {"e5_explanation1", "e5_example1", "e5_example2"},
            };
        }
        else if (PronunciationFragment.titlestr == "경음화"){
            id = new int[]{4, 5, 4, 4, 4};
            strarr = new String[][]{
                    {"h1_explanation1", "h1_example1", "h1_example2"},
                    {"h2_explanation1", "h2_example1", "h2_example2", "h2_explanation2", "h2_example3", "h2_example4"},
                    {"h3_explanation1", "h3_example1", "h3_example2"},
                    {"h4_explanation1", "h4_example1", "h4_example2"},
                    {"h5_explanation1", "h5_example1", "h5_example2"}
            };
        }
        else{
            id = new int[]{4, 4, 4, 4};
            strarr = new String[][]{
                    {"a1_explanation1", "a1_example1", "a1_example2"},
                    {"a2_explanation1", "a2_example1", "a2_example2"},
                    {"a3_explanation1", "a3_example1", "a3_example2"},
                    {"a4_explanation1", "a4_example1", "a4_example2"}
            };
        }
        int layoutID = getResources().getIdentifier("pronunciation_form_0"+id[page-1], "layout", getContext().getPackageName());
        int idx = page-1;

        View view = inflater.inflate(layoutID, container, false);

        if(id[page-1]==1){
            explanation1 = view.findViewById(R.id.explanation1);
            explanation2 = view.findViewById(R.id.explanation2);
            example1 = view.findViewById(R.id.example1);
            example2 = view.findViewById(R.id.example2);

            explanation1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][0],"string", getContext().getPackageName())));
            explanation2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][1],"string", getContext().getPackageName())));
            example1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][2],"string", getContext().getPackageName())));
            example2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][3],"string", getContext().getPackageName())));
        }
        else if(id[page-1]==2){
            explanation1 = view.findViewById(R.id.explanation1);
            explanation2 = view.findViewById(R.id.explanation2);
            explanation3 = view.findViewById(R.id.explanation3);
            explanation4 = view.findViewById(R.id.explanation4);
            explanation5 = view.findViewById(R.id.explanation5);
            explanation6 = view.findViewById(R.id.explanation6);
            explanation7 = view.findViewById(R.id.explanation7);
            example1 = view.findViewById(R.id.example1);
            example2 = view.findViewById(R.id.example2);

            explanation1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][0],"string", getContext().getPackageName())));
            explanation2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][1],"string", getContext().getPackageName())));
            explanation3.setText(getString(getResources()
                    .getIdentifier(strarr[idx][2],"string", getContext().getPackageName())));
            explanation4.setText(getString(getResources()
                    .getIdentifier(strarr[idx][3],"string", getContext().getPackageName())));
            explanation5.setText(getString(getResources()
                    .getIdentifier(strarr[idx][4],"string", getContext().getPackageName())));
            explanation6.setText(getString(getResources()
                    .getIdentifier(strarr[idx][5],"string", getContext().getPackageName())));
            explanation7.setText(getString(getResources()
                    .getIdentifier(strarr[idx][6],"string", getContext().getPackageName())));
            example1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][7],"string", getContext().getPackageName())));
            example2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][8],"string", getContext().getPackageName())));
        }
        else if(id[page-1]==3){
            explanation1 = view.findViewById(R.id.explanation1);
            explanation2 = view.findViewById(R.id.explanation2);
            explanation3 = view.findViewById(R.id.explanation3);
            explanation4 = view.findViewById(R.id.explanation4);
            explanation5 = view.findViewById(R.id.explanation5);
            explanation6 = view.findViewById(R.id.explanation6);
            explanation7 = view.findViewById(R.id.explanation7);
            explanation8 = view.findViewById(R.id.explanation8);
            explanation9 = view.findViewById(R.id.explanation9);
            example1 = view.findViewById(R.id.example1);
            example2 = view.findViewById(R.id.example2);

            explanation1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][0],"string", getContext().getPackageName())));
            explanation2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][1],"string", getContext().getPackageName())));
            explanation3.setText(getString(getResources()
                    .getIdentifier(strarr[idx][2],"string", getContext().getPackageName())));
            explanation4.setText(getString(getResources()
                    .getIdentifier(strarr[idx][3],"string", getContext().getPackageName())));
            explanation5.setText(getString(getResources()
                    .getIdentifier(strarr[idx][4],"string", getContext().getPackageName())));
            explanation6.setText(getString(getResources()
                    .getIdentifier(strarr[idx][5],"string", getContext().getPackageName())));
            explanation7.setText(getString(getResources()
                    .getIdentifier(strarr[idx][6],"string", getContext().getPackageName())));
            explanation8.setText(getString(getResources()
                    .getIdentifier(strarr[idx][7],"string", getContext().getPackageName())));
            explanation9.setText(getString(getResources()
                    .getIdentifier(strarr[idx][8],"string", getContext().getPackageName())));
            example1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][9],"string", getContext().getPackageName())));
            example2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][10],"string", getContext().getPackageName())));
        }
        else if(id[page-1]==4){
            explanation1 = view.findViewById(R.id.explanation1);
            example1 = view.findViewById(R.id.example1);
            example2 = view.findViewById(R.id.example2);

            explanation1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][0],"string", getContext().getPackageName())));
            example1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][1],"string", getContext().getPackageName())));
            example2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][2],"string", getContext().getPackageName())));

        }
        else if(id[page-1]==5){
            explanation1 = view.findViewById(R.id.explanation1);
            explanation2 = view.findViewById(R.id.explanation2);
            example1 = view.findViewById(R.id.example1);
            example2 = view.findViewById(R.id.example2);
            example3 = view.findViewById(R.id.example3);
            example4 = view.findViewById(R.id.example4);

            explanation1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][0],"string", getContext().getPackageName())));
            explanation2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][3],"string", getContext().getPackageName())));
            example1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][1],"string", getContext().getPackageName())));
            example2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][2],"string", getContext().getPackageName())));
            example3.setText(getString(getResources()
                    .getIdentifier(strarr[idx][4],"string", getContext().getPackageName())));
            example4.setText(getString(getResources()
                    .getIdentifier(strarr[idx][5],"string", getContext().getPackageName())));
        }
        else{
            explanation1 = view.findViewById(R.id.explanation1);
            explanation2 = view.findViewById(R.id.explanation2);
            explanation3 = view.findViewById(R.id.explanation3);
            explanation4 = view.findViewById(R.id.explanation4);
            explanation5 = view.findViewById(R.id.explanation5);
            example1 = view.findViewById(R.id.example1);
            example2 = view.findViewById(R.id.example2);

            explanation1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][0],"string", getContext().getPackageName())));
            explanation2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][1],"string", getContext().getPackageName())));
            explanation3.setText(getString(getResources()
                    .getIdentifier(strarr[idx][2],"string", getContext().getPackageName())));
            explanation4.setText(getString(getResources()
                    .getIdentifier(strarr[idx][3],"string", getContext().getPackageName())));
            explanation5.setText(getString(getResources()
                    .getIdentifier(strarr[idx][4],"string", getContext().getPackageName())));
            example1.setText(getString(getResources()
                    .getIdentifier(strarr[idx][5],"string", getContext().getPackageName())));
            example2.setText(getString(getResources()
                    .getIdentifier(strarr[idx][6],"string", getContext().getPackageName())));
        }

        return view;
    }
}
