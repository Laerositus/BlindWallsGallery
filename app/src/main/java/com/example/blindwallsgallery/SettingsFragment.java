package com.example.blindwallsgallery;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Class for the settings screen
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    /**
     * Creates the settings screen
     * @param bundle Unused bundle object
     * @param s Unused string for commenting
     */
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_blindwalls);
    }
}
