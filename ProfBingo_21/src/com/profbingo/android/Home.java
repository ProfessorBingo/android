package com.profbingo.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.profbingo.android.webdata.RestAdapter;
import com.profbingo.android.webdata.WebDataAdapter;

public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        final Button loginButton = (Button) findViewById(R.id.home_login);
        final Button registerButton = (Button) findViewById(R.id.home_register);

        final EditText emailText = (EditText) findViewById(R.id.home_email);
        final EditText passwordText = (EditText) findViewById(R.id.home_password);

        loginButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                WebDataAdapter web = new RestAdapter(getResources());
                String authcode = web.login(emailText.getText().toString(), passwordText.getText().toString());

                if (web.isLoggedIn()) {

                } else {

                }
            }
        });
    }

}
