package com.example.myandroidsettingsscreen;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

/**
 * Created by Darush on 7/27/2018.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        // Load our preferences from the xml file
        addPreferencesFromResource(R.xml.app_preferences);
    }

}
