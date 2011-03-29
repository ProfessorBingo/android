package com.profbingo.android;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class BingoGame extends Activity {

	public static final int NUM_ROWS_COLS = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bingo_game);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		
		
		setupBoard();
		;

	}

	protected TableLayout bingoBoardTable;

	private void setupBoard() {
		bingoBoardTable = (TableLayout) findViewById(R.id.bingo_game_table);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		bingoBoardTable.getLayoutParams().height = metrics.widthPixels - 30;
		//		
		// bingoBoardTable.setLayoutParams(new
		// ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 50) );
		// Log.d("PB", "Table Height: " +
		// bingoBoardTable.getLayoutParams().height);
		// Log.d("PB", "Table width: " + bingoBoardTable.getWidth());

		addButtons();

	}

	protected Button[][] bingoSquaresButtons = new Button[NUM_ROWS_COLS][NUM_ROWS_COLS];

	private void addButtons() {
		int count = 1;

		for (int i = 0; i < NUM_ROWS_COLS; i++) {
			TableRow row = new TableRow(this);
			row.setTag("Row" + i);
			row.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

			for (int j = 0; j < NUM_ROWS_COLS; j++) {
				Button b = new Button(this);
				bingoSquaresButtons[i][j] = b;
				b.setText("" + count++);
				b.setTag("Square" + i + j);
				b.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				row.addView(b);

			}

			bingoBoardTable.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
					TableRow.LayoutParams.FILL_PARENT, 1));
			Log.d("PB", "Added Bingo Row: " + row.getTag());
		}

		bingoBoardTable.setShrinkAllColumns(true);
		bingoBoardTable.setStretchAllColumns(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

}
