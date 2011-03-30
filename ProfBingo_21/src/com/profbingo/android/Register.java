package com.profbingo.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Register extends Activity {
    
    private static final String TAG = "ProfBingo.Register";
    
    private static final int DIALOG_INFO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button registerButton = (Button) findViewById(R.id.register_register);
        Button cancelButton = (Button) findViewById(R.id.register_cancel);
        
        cancelButton.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                Register.this.finish();
            }
        });
        
        registerButton.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                Register.this.showDialog(DIALOG_INFO);
            }
        });
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
        super.onCreateDialog(id);

        Dialog dialog = null;

        if (id == DIALOG_INFO) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You have been registered. Before you can play, you'll need to click the confirmation link in your email.")
                   .setTitle("Registration Successful")
                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    
                    public void onClick(DialogInterface dialog, int which) {
                        Register.this.finish();
                    }
                });
            dialog = builder.create();
        }
        return dialog;
    }
}
