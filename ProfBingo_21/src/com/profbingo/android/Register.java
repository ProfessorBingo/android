package com.profbingo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.profbingo.android.webdata.RestAdapter;
import com.profbingo.android.webdata.WebDataAdapter;

public class Register extends Activity {

    private static final String TAG = "ProfBingo.Register";

    private static final int DIALOG_INFO = 1;
    private static final int DIALOG_ERROR = 2;
    private static final int DIALOG_PASSWORD = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button registerButton = (Button) findViewById(R.id.register_register);
        Button cancelButton = (Button) findViewById(R.id.register_cancel);
        
        final EditText emailText = (EditText) findViewById(R.id.register_email); 
        final EditText firstNameText = (EditText) findViewById(R.id.register_first_name); 
        final EditText lastNameText = (EditText) findViewById(R.id.register_last_name); 
        final EditText passwordText = (EditText) findViewById(R.id.register_password); 
        final EditText passwordConfirmText = (EditText) findViewById(R.id.register_confirm_password); 

        cancelButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Register.this.finish();
            }
        });

        registerButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                WebDataAdapter web = new RestAdapter(getResources());
                if (!passwordText.getText().toString().equals(passwordConfirmText.getText().toString())) {
                    Register.this.showDialog(DIALOG_PASSWORD);
                } else if (web.register(emailText.getText().toString(), firstNameText.getText().toString(), lastNameText.getText().toString(), passwordText.getText().toString())) {
                    Register.this.showDialog(DIALOG_INFO);
                } else {
                    Register.this.showDialog(DIALOG_ERROR);
                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        super.onCreateDialog(id);

        Dialog dialog = null;

        switch (id) {
            case DIALOG_INFO:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You have been registered. Before you can play, you'll need to click the confirmation link in your email.").setTitle("Registration Successful").setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Register.this.finish();
                    }
                });
                dialog = builder.create();
                break;
            case DIALOG_PASSWORD:
                AlertDialog.Builder pb = new AlertDialog.Builder(this);
                pb.setMessage("The passwords you entered don't match.")
                  .setTitle("Wait a minute!")
                  .setPositiveButton("Let's try again", null);
                dialog = pb.create();
                break;
            case DIALOG_ERROR:
                AlertDialog.Builder eb = new AlertDialog.Builder(this);
                eb.setMessage("There was a problem creating a user with the values you specified.")
                  .setTitle("Whoops.")
                  .setPositiveButton("Let's try again", null);
                dialog = eb.create();
                break;
        }
        return dialog;
    }
}
