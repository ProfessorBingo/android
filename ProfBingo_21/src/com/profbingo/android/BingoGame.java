package com.profbingo.android;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
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

		addComponents();

	}

	protected BingoGameComponent[][] bingoSquaresComponents = new BingoGameComponent[NUM_ROWS_COLS][NUM_ROWS_COLS];
	protected ArrayList<BingoGameComponent> bingoComponentArrayList = new ArrayList<BingoGameComponent>(25);

	/**
	 * Builds BingoGameComponents. Components are then used to build Buttons to be placed in the table at the top of screen.
	 * Also constructs the listview adapter for the bottom of the screen and then adds the ListView elements.
	 */
	private void addComponents() {
		int count = 0;

		for (int i = 0; i < NUM_ROWS_COLS; i++) {
			TableRow row = new TableRow(this);
			row.setTag("Row" + i);
			row.setLayoutParams(new TableRow.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

			for (int j = 0; j < NUM_ROWS_COLS; j++) {
				BingoGameComponent comp = new BingoGameComponent(this);
				// Configure object
				comp.id = ("Square" + i ) + j;
				comp.row = i;
				comp.col = j;
				comp.position = count++;
				comp.label = "" + count;
				comp.configure();
				
				
				bingoComponentArrayList.add(comp);
				bingoSquaresComponents[i][j] = comp;
				row.addView(comp.button);

			}

			bingoBoardTable.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
					TableRow.LayoutParams.FILL_PARENT, 1));
			Log.d("PB", "Added Bingo Row: " + row.getTag());
		}

		bingoBoardTable.setShrinkAllColumns(true);
		bingoBoardTable.setStretchAllColumns(true);
		
		//Add the compoment elements to the list view
		buildListView();
		
		
	}

	
	protected BingoGameComponentListAdapter componentListAdapter;
	protected ListView componentListView;
	
	private void buildListView() {
		componentListAdapter = new BingoGameComponentListAdapter(this, R.layout.bingogame_info_listview_component_element, R.id.description_textview, bingoComponentArrayList);
		componentListView = (ListView) findViewById(R.id.bingogame_info_listview);
		componentListView.setAdapter(componentListAdapter);
		
		
		//TODO Setup on click listeners...
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}



	
}
