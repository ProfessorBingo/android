package com.profbingo.android;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableRow;

public class BingoGameComponent {

	Button button;
	String label;
	String description = "Long winded mannerism description text\nCan have new lines so the list view should grow.";
	String id;
	int row;
	int col;
	int position;

	BingoGame game;

	public BingoGameComponent(BingoGame cont) {
		game = cont;

	}
	
	public void configure() {
		// Build button;
		button = new Button(game);
		button.setText(label);
		button.setTag(id);
		//button.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		BingoButtonClickListener listener = new BingoButtonClickListener(button); 
		button.setOnClickListener(listener);
		button.setOnLongClickListener(listener);		
		
	}
	
	

	class BingoButtonClickListener implements OnClickListener, OnLongClickListener {

		Button button;

		public BingoButtonClickListener(Button b) {
			button = b;

		}

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d("PB", "Clicked Bingo Square: Grid:" + button.getTag() + " Label:"+label);
			game.componentListView.setSelection(position);

		}

		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Log.d("PB", "Long Clicked Bingo Square: Grid:" + button.getTag() + " Label:"+label);
			return false;
		}

	}

}
