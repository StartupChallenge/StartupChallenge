package com.hoonhooney.sullivan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hoonhooney.sullivan.R;

import java.util.ArrayList;
import java.util.List;

public class PronSupportFragment extends Fragment {
    private int page;   //fragment page 번호
    private List<String> words = new ArrayList<>(); //음성인식 대상 단어 list

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

        int layoutID = getResources().getIdentifier("pronunciation_form_0"+page, "layout", getContext().getPackageName());
        View view = inflater.inflate(layoutID, container, false);
        return view;
    }
}
