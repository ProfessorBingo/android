package com.profbingo.android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BingoGameListView extends LinearLayout {

	private TextView labelTextView;
	private TextView descriptionTextView;
	
	
	public BingoGameListView(Context context) {
		super(context);
		LayoutInflater inf = ((Activity)context).getLayoutInflater();
		inf.inflate(R.layout.bingo_info_listviewcomponent, this);
		labelTextView = (TextView) findViewById(R.id.label_textview);
		descriptionTextView = (TextView) findViewById(R.id.description_textview);
	
	
	}


}
