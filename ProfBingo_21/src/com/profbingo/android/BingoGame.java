package com.profbingo.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
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
		
		setupBoard();;
		
	}


	protected TableLayout bingoBoardTable;
	

	private void setupBoard() {
		bingoBoardTable = (TableLayout) findViewById(R.id.bingo_game_table);
		
		DisplayMetrics metrics = new DisplayMetrics(); 
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
//		
//		bingoBoardTable.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 50) );
		Log.d("PB", "Table Height: " + bingoBoardTable.getLayoutParams().height);
		Log.d("PB", "Table width: " + bingoBoardTable.getWidth());
		bingoBoardTable.getLayoutParams().height = metrics.widthPixels;
		Log.d("PB", "Table Height: " + bingoBoardTable.getLayoutParams().height);
		
		
		addButtons();
		
		
	}
	
	protected Button[][] bingoSquaresButtons = new Button[NUM_ROWS_COLS][NUM_ROWS_COLS];
	
	private void addButtons() {
		int count = 1;
		for(int i =0; i<NUM_ROWS_COLS; i++){
			TableRow row = new TableRow(this);
			row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT, 1) );
			for(int j=0; j<NUM_ROWS_COLS; j++){
				Button b = new Button(this);
				bingoSquaresButtons[i][j] = b;
				b.setText(""+ count++);
				b.setTag(""+i+j);
				b.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				row.addView(b);
				
			}
			bingoBoardTable.addView(row);
			
		}
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	
		
		
	}
	

}
