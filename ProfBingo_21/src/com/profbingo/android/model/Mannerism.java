package com.profbingo.android.model;


public class Mannerism implements Comparable<Mannerism> {
    
    private int mId;
    private String mText;
    
    public Mannerism(int id, String text) {
        mId = id;
        mText = text;
    }
    
    public int getId() { return mId; }
    public void setId(int id) { mId = id; }
    
    public String getText() { return mText; }
    public void setText(String text) { mText = text; }

    public int compareTo(Mannerism another) { return getId() - another.getId(); }
    
    public String toString() { return getText(); }
}
