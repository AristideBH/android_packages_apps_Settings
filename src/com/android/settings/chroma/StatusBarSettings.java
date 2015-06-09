package com.android.settings.chroma;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class StatusBarSettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String DOUBLE_TAP_SLEEP_GESTURE = "double_tap_sleep_gesture";
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.chroma_settings_navigation);

       // Status bar double-tap to sleep
	mStatusBarDoubleTapSleepGesture = (SwitchPreference) getPreferenceScreen().findPreference(DOUBLE_TAP_SLEEP_GESTURE);
	mStatusBarDoubleTapSleepGesture.setChecked((Settings.System.getInt(getActivity().getApplicationContext().getContentResolver(),
	Settings.System.DOUBLE_TAP_SLEEP_GESTURE, 0) == 1));
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        ContentResolver cr = getActivity().getContentResolver();
	boolean value = (Boolean) objValue;
        if (preference == mStatusBarDoubleTapSleepGesture) {
		Settings.System.putInt(cr,
		Settings.System.DOUBLE_TAP_SLEEP_GESTURE, value ? 1: 0);
		return true;
	}
        return false;
    }
}
