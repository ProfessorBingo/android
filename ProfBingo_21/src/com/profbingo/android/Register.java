package com.profbingo.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Register extends Activity {

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
                Register.this.finish();
            }
        });
    }
}
