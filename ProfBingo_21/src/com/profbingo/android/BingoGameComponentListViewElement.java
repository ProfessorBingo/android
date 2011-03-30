package com.profbingo.android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BingoGameComponentListViewElement extends LinearLayout {

	public TextView labelTextView;
	public TextView descriptionTextView;
	
	
	public BingoGameComponentListViewElement(Context context) {
		super(context);
		LayoutInflater inf = ((Activity)context).getLayoutInflater();
		inf.inflate(R.layout.bingogame_info_listview_component_element, this);
		labelTextView = (TextView) findViewById(R.id.label_textview);
		descriptionTextView = (TextView) findViewById(R.id.description_textview);
	
	
	}


}
