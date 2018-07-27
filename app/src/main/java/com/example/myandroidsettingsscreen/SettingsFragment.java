package com.example.myandroidsettingsscreen;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.preference.Preference;
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

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {

        // See if the preference is our custom preference
        DialogFragment dialogFragment = null;
        if (preference instanceof TimePreference) {
            // Create a new instance of TimePreference
            dialogFragment = new TimePreferenceDialogFragmentCompat().newInstance(preference.getKey());
        }

        // If it was our custom preference
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(),
                    "androidx.preference.PreferenceFragment.DIALOG");
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }
}
