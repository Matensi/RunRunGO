package com.matensi.runrungo.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.matensi.runrungo.R

class SettinsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preference, rootKey)
    }

}