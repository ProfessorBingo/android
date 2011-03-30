package com.profbingo.android;

import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class BingoGameComponentListAdapter extends ArrayAdapter<BingoGameComponent> {
	
	
	private BingoGame context;
	
	public BingoGameComponentListAdapter(BingoGame context, int resource, int textViewResourceId, ArrayList<BingoGameComponent> components) {
		super(context, resource, textViewResourceId, components);
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BingoGameComponentListViewElement view;
		if(convertView == null)
			view = new BingoGameComponentListViewElement(context);
		else
			view = (BingoGameComponentListViewElement) super.getView(position, convertView, parent);
		
		
		BingoGameComponent comp = getItem(position);
		
		
		view.labelTextView.setText(comp.label);
		view.descriptionTextView.setText(comp.description);
		//Log.d("PB", "Set Label: " + comp.label);
		
		
		
		
		return view;
		
		
		
	}

}
