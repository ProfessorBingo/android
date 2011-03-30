package com.profbingo.android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

public class BingoTable extends TableLayout {

	public BingoTable(Context context) {
		super(context);
		this.initComponent(context);
		
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		if (w != 0) {
			h = w;
			this.getLayoutParams().height = w;
			Log.d("PB", "Set BingoTable Height: " + w);
		}

		super.onSizeChanged(w, h, oldw, oldh);

	}

	private void initComponent(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.bingo_table, null, false);
		this.addView(v);
	}

}
