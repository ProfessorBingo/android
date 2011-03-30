package com.profbingo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.profbingo.android.webdata.RestAdapter;
import com.profbingo.android.webdata.WebDataAdapter;

public class Home extends Activity {

    private static final int DIALOG_BAD_LOGIN = 1;

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
                    Intent intent = new Intent(Home.this, SelectProf.class);
                    intent.putExtra("authCode", authcode);
                    startActivity(intent);
                } else {
                    showDialog(DIALOG_BAD_LOGIN);
                }
            }
        });
        
        registerButton.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Register.class));
            }
        });
        
        // For Testing Only
        startActivity(  new Intent(this, BingoGame.class));
        
        
        
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        super.onCreateDialog(id);

        Dialog dialog = null;

        if (id == DIALOG_BAD_LOGIN) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Invalid login credentials. Please make sure you've registered and verified your email address.")
                   .setTitle("Login Problem")
                   .setPositiveButton("OK", null);
            dialog = builder.create();
        }
        return dialog;
    }

}
