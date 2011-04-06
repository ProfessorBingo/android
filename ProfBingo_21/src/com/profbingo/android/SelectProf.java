package com.profbingo.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.profbingo.android.model.Category;
import com.profbingo.android.model.Professor;
import com.profbingo.android.webdata.RestAdapter;
import com.profbingo.android.webdata.WebDataAdapter;


public class SelectProf extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_prof);
        
        Button playButton = (Button) findViewById(R.id.select_prof_play);
        AutoCompleteTextView profText = (AutoCompleteTextView) findViewById(R.id.select_prof_prof);
        AutoCompleteTextView catText = (AutoCompleteTextView) findViewById(R.id.select_prof_category);
        
        WebDataAdapter web = new RestAdapter(getResources());
        web.login(getIntent().getExtras().getString("authCode"));
        
        playButton.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                startActivity(new Intent(SelectProf.this, BingoGame.class));
            }
        });
        
        ArrayAdapter<Professor> profAdapter = new ArrayAdapter<Professor>(this, R.layout.autocomplete_entry, web.getProfessors());
        profText.setAdapter(profAdapter);
        
        ArrayAdapter<Category> catAdapter = new ArrayAdapter<Category>(this, R.layout.autocomplete_entry, web.getCategories());
        catText.setAdapter(catAdapter);
    }
}
