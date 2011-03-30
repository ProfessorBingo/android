package com.profbingo.android.model;

import java.util.ArrayList;


public class GameBoard {
    
    private static final int SIZE = 5;
    
    private ArrayList<Mannerism> mMannerisms;
    
    public GameBoard() {
        mMannerisms = new ArrayList<Mannerism>((int) Math.pow(SIZE, 2));
    }
    
    public void set(int index, Mannerism m) {
        mMannerisms.set(index - 1, m);
    }
    
    public Mannerism get(int index) {
        return mMannerisms.get(index - 1);
    }
}
