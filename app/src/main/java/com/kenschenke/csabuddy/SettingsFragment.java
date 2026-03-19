package com.kenschenke.csabuddy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    CheckBox enableAccessibilityCheckbox;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SettingsFragment.
     */
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        // Get the checkbox from the layout
        enableAccessibilityCheckbox = rootView.findViewById(R.id.accessibilityModeCheckbox);

        // Load user preferences
        Constants.loadPrefs(getContext());

        // Wait until preferences are loaded before accessing them
        boolean isAccessibilityMode = false;
        do {
            if (getActivity() != null && Constants.isPrefsLoaded) {
                isAccessibilityMode = Constants.prefs.getBoolean("accessibilityMode", false);
            }
        } while (!Constants.isPrefsLoaded);

        // Set the checkbox state based on the loaded preference
        enableAccessibilityCheckbox.setChecked(isAccessibilityMode);

        // Set a listener to update preferences when the checkbox state changes
        enableAccessibilityCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (getActivity() != null) {
                Constants.prefs.edit().putBoolean("accessibilityMode", isChecked).apply();
            }
        });
        return rootView;
    }
}