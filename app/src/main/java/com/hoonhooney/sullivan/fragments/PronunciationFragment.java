package com.hoonhooney.sullivan.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hoonhooney.sullivan.R;
import com.hoonhooney.sullivan.activities.PronSupportActivity;

public class PronunciationFragment extends Fragment implements View.OnClickListener {

    static public String titlestr;

    public PronunciationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pronunciation, container, false);

        view.findViewById(R.id.btn_pron_support_characters).setOnClickListener(this);
        view.findViewById(R.id.btn_Equalization).setOnClickListener(this);
        view.findViewById(R.id.btn_hard_sound).setOnClickListener(this);
        view.findViewById(R.id.btn_add_sound).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Activity activity = null;

        switch (view.getId()){
            case R.id.btn_pron_support_characters:
                titlestr = "받침의 발음";
                activity = new PronSupportActivity();
                break;
            case R.id.btn_Equalization:
                titlestr = "음의 동화";
                activity = new PronSupportActivity();
                break;
            case R.id.btn_hard_sound:
                titlestr = "경음화";
                activity = new PronSupportActivity();
                break;
            case R.id.btn_add_sound:
                titlestr = "음의 첨가";
                activity = new PronSupportActivity();
                break;
//                추후 다른 메뉴 활성화시 더 추가할 것
        }

        startActivity(new Intent(getContext(), activity.getClass()));
    }
}