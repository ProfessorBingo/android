package com.profbingo.android;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProfBingo extends Activity implements OnSharedPreferenceChangeListener {

	protected SharedPreferences defaultSharedPreferences;
	protected String authKey = "";
	protected String passwordHash = "";

	public static final int LOGIN_DIALOG = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setupPreferences();
		setupButtons();

	}

	private void setupButtons() {
		Button login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Login Call
				login();
			}
		});

	}

	private void setupPreferences() {
		// Load Preferences
		defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);

	}

	public void onSharedPreferenceChanged(SharedPreferences prefs, String arg1) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inf = this.getMenuInflater();
		inf.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {

		case R.id.prefs_menu_item:
			Intent prefsIntent = new Intent(this, ProfBingoPrefs.class);
			startActivity(prefsIntent);
			break;
		}
		return true;
	}

	private boolean login() {
		// Create Dialog
		ProgressDialog pd = ProgressDialog.show(ProfBingo.this, "Logging in", "", true);

		// Validate the user credentials...
		String user = defaultSharedPreferences.getString(getString(R.string.key_email_prefs), "DEFAULT_USER");
		String pass = defaultSharedPreferences.getString(getString(R.string.key_pass_prefs), "DEFAULT_PASS");
		// Build Map for conversion to JSON

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", user);
		map.put("password", NetUtil.hashStringSHA(pass + user));

		// Post HTTP Call.
		JSONObject jsonResult = NetUtil.postJsonData(map, "http://profbingo.heroku.com/login");
		Log.d("PB", "HTTP login post returned: " + jsonResult);

		try {
			String authCode = jsonResult.getString("authcode");
			// Save the Auth Code
			Editor e = defaultSharedPreferences.edit();
			e.putString("authcode", authCode);
			e.commit();

			// Dismiss dialog and close
			pd.dismiss();
			return true;

		} catch (Exception e) {
			Log.d("PB", "Login Failed, no authcode key found in the JSON result");
			e.printStackTrace();

			// NO Auth Code Found.. Login Fail.
		}

		pd.dismiss();

		return false;

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog d;
		switch (id) {
		case (LOGIN_DIALOG):
			ProgressDialog progressDialog = new ProgressDialog(this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMessage("Loading...");
			progressDialog.setCancelable(true);
			d = progressDialog;
			break;

		}
		return super.onCreateDialog(id);
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		super.onPrepareDialog(id, dialog);
	}

}