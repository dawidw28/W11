package com.example.w11.gallery

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.example.w11.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(p0: Bundle?, p1: String?) {
        addPreferencesFromResource(R.xml.pref_settings)
    }


}