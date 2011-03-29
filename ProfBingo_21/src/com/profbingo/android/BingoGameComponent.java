package com.profbingo.android;

import android.widget.Button;

public class BingoGameComponent {
	
	Button button;
	String label;
	String description;
	int id;
	
	
	public BingoGameComponent(int id, String label, String description){
		this.id = id;
		this.label = label;
		this.description = description;
		
	}

}
