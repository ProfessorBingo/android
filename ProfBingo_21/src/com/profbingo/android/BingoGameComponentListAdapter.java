package com.profbingo.android;

import android.widget.ArrayAdapter;

public class BingoGameComponentListAdapter extends ArrayAdapter {
	
	
	private BingoGameListView context;
	
	public CarListAdapter(CarTracker context, int resource, int textViewResourceId, ArrayList<Car> cars) {
		super(context, resource, textViewResourceId, cars);
		this.context = context;
	}

}
