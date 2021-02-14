package com.example.mykotlinapp

import android.os.Bundle
import android.widget.ToggleButton
import androidx.preference.PreferenceFragmentCompat
import kotlinx.android.synthetic.*

class ColourSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}