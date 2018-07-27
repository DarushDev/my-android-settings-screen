package com.example.myandroidsettingsscreen;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragmentCompat;

/**
 * Created by Darush on 7/27/2018.
 */
public class TimePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    private TimePicker mTimePicker;

    public static TimePreferenceDialogFragmentCompat newInstance(String key) {
        final TimePreferenceDialogFragmentCompat fragment =
                new TimePreferenceDialogFragmentCompat();
        final Bundle bundle = new Bundle(1);
        bundle.putString(ARG_KEY, key);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mTimePicker = view.findViewById(R.id.edit);

        // Exception when there is no TimePicker
        if (mTimePicker == null) {
            throw new IllegalStateException("Dialog view must contain" +
            " a TimePicker with id 'edit'");
        }

        // Get the time from default shared preferences
        Integer minutesAfterMidNight = null;
        DialogPreference preference = getPreference();
        if (preference instanceof TimePreference) {
            minutesAfterMidNight = ((TimePreference) preference).getTime();
        }

        // Set TimePicker time
        if (minutesAfterMidNight != null) {
            int hours = minutesAfterMidNight / 60;
            int minutes = minutesAfterMidNight % 60;
            boolean is24Hour = DateFormat.is24HourFormat(getContext());

            mTimePicker.setCurrentHour(hours);
            mTimePicker.setCurrentMinute(minutes);
            mTimePicker.setIs24HourView(is24Hour);
        }

    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // Value to be saved
            int hours = mTimePicker.getCurrentHour();
            int minutes = mTimePicker.getCurrentMinute();
            int minutesAfterMidNight = (hours * 60) + minutes;

            // Find the preference and save the value
            DialogPreference preference = getPreference();
            if (preference instanceof TimePreference) {
                TimePreference timePreference = ((TimePreference) preference);

                // This allows the client to ignore the user value.
                if (timePreference.callChangeListener(minutesAfterMidNight)) {
                    // Save the value
                    timePreference.setTime(minutesAfterMidNight);
                }
            }
        }
    }



}
