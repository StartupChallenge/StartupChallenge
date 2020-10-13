package com.hoonhooney.sullivan.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hoonhooney.sullivan.R;
import com.hoonhooney.sullivan.activities.PronSupportActivity;

public class PronunciationFragment extends Fragment implements View.OnClickListener {

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
        view.findViewById(R.id.btn_pron_duple_supports).setOnClickListener(this);
        view.findViewById(R.id.btn_pron_support_1).setOnClickListener(this);
        view.findViewById(R.id.btn_pron_support_2).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Activity activity = null;

        switch (view.getId()){
            case R.id.btn_pron_support_characters:
                activity = new PronSupportActivity();
                break;
//                추후 다른 메뉴 활성화시 더 추가할 것
        }

        startActivity(new Intent(getContext(), activity.getClass()));
    }
}