package com.profbingo.android.model;


public class Professor implements Comparable<Professor> {
    
    private int mId;
    private String mName;
    
    public Professor(int id, String name) {
        mId = id;
        mName = name;
    }
    
    public int getId() { return mId; }
    public void setId(int id) { mId = id; }
    
    public String getName() { return mName; }
    public void setName(String name) { mName = name; }

    public int compareTo(Professor another) { return getName().compareTo(another.getName()); }
    
    public String toString() { return getName(); }
}
