package com.profbingo.android;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ProfBingoPrefs extends PreferenceActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Inflate XML resource, using parent inflater.
		addPreferencesFromResource(R.xml.profbingo_prefs);


	}
}
