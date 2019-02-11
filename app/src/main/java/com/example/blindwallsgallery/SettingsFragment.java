package com.example.blindwallsgallery;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

/**
 * Class for the settings screen
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    private static final String TAG = SettingsFragment.class.getSimpleName();

    /**
     * Creates the settings screen
     * @param bundle Unused bundle object
     * @param s Unused string for commenting
     */
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        Log.d(TAG, "onCreatePreferences: called");
        addPreferencesFromResource(R.xml.pref_blindwalls);
    }
}
