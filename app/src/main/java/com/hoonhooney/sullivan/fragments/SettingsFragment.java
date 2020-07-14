package com.hoonhooney.sullivan.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.hoonhooney.sullivan.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}