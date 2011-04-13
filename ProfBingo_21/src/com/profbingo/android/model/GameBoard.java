package com.profbingo.android.model;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;


public class GameBoard {
    
    private static final int SIZE = 5;
    
    private ArrayList<Mannerism> mMannerisms;
    
    public GameBoard() {
        mMannerisms = new ArrayList<Mannerism>((int) Math.pow(SIZE, 2));
        for (int i = 0; i < Math.pow(SIZE, 2); i++) {
            mMannerisms.add(null);
        }
    }
    
    public void set(int index, Mannerism m) {
        mMannerisms.set(index - 1, m);
    }
    
    public Mannerism get(int index) {
        return mMannerisms.get(index - 1);
    }
    
    public boolean willCauseBingo(int index) {
        return all(verticals(index)) || all(horizontals(index)) || diagonelBingo(index);
    }
    
    private boolean diagonelBingo(int index) {
        ArrayList<Mannerism> possible1 = new ArrayList<Mannerism>();
        possible1.add(get(1));
        possible1.add(get(7));
        possible1.add(get(13));
        possible1.add(get(19));
        possible1.add(get(25));
        ArrayList<Mannerism> possible2 = new ArrayList<Mannerism>();
        possible2.add(get(5));
        possible2.add(get(9));
        possible2.add(get(13));
        possible2.add(get(17));
        possible2.add(get(21));
        
        if (!possible1.contains(get(index)) && !possible2.contains(get(index))) return false;
        possible1.remove(get(index));
        possible2.remove(get(index));
        
        return all(possible1) || all(possible2);
    }
    
    public boolean mark(int index) {
        boolean result = willCauseBingo(index);
        get(index).setMarked(true);
        return result;
    }
    
    public void unmark(int index) {
        get(index).setMarked(false);
    }
    
    private Mannerism getZeroBased(int index) {
        return get(index + 1);
    }
    
    private List<Mannerism> diagonels(int index) {
        ArrayList<Mannerism> result =  new ArrayList<Mannerism>();
        for (int i = index % SIZE + index / SIZE; i < Math.pow(SIZE, 2); i += SIZE + 1) {
            result.add(getZeroBased(i));
        }
        return result;
    }
    
    private List<Mannerism> horizontals(int index) {        
        index --;
        int startIndex = index / SIZE * SIZE;
        int endIndex = startIndex + SIZE;
        
        List<Mannerism> result = new ArrayList<Mannerism>(mMannerisms.subList(startIndex, endIndex));
        result.remove(getZeroBased(index));
        return result;
    }
    
    private List<Mannerism> verticals(int index) {
        ArrayList<Mannerism> result = new ArrayList<Mannerism>();
        for (int i = (index - 1) % SIZE; i < Math.pow(SIZE, 2); i += SIZE) {
            if (i != index - 1) result.add(getZeroBased(i));
        }
        
        return result;
    }
    
    private boolean all(List<Mannerism> set) {
        boolean result = true;
        for (Mannerism b : set) {
            result = !b.isMarked() ? false : result;
        }
        return result;
    }
}
