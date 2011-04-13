package com.profbingo.android;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.profbingo.android.model.GameBoard;
import com.profbingo.android.model.Mannerism;

public class BingoGameComponentListAdapter extends ArrayAdapter<BingoGameComponent> {
	
	
	private BingoGame context;
	private GameBoard mBoard;
	
	public BingoGameComponentListAdapter(BingoGame context, int resource, int textViewResourceId, ArrayList<BingoGameComponent> components, GameBoard board) {
		super(context, resource, textViewResourceId, components);
		this.context = context;
		mBoard = board;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BingoGameComponentListViewElement view;
		if(convertView == null)
			view = new BingoGameComponentListViewElement(context);
		else
			view = (BingoGameComponentListViewElement) super.getView(position, convertView, parent);
		
		
		final BingoGameComponent comp = getItem(position);
		
		Mannerism m = mBoard.get(position + 1);
		
		view.labelTextView.setText(comp.label);
		if (m == null) {
		      view.descriptionTextView.setText("MANNERISM WAS NULL");
		} else {
		      view.descriptionTextView.setText(m.getText());
		}

		//Log.d("PB", "Set Label: " + comp.label);
		
		view.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				comp.markSelection();
				return false;
			}
		});
		
		return view;	
	}
}
