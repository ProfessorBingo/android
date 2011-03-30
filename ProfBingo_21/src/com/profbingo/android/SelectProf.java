package com.profbingo.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SelectProf extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_prof);
        
        Button playButton = (Button) findViewById(R.id.select_prof_play);
        
        playButton.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                startActivity(new Intent(SelectProf.this, BingoGame.class));
            }
        });
    }
}
